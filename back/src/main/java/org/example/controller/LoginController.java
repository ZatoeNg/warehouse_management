package org.example.controller;

import com.google.code.kaptcha.Producer;
import org.example.beans.*;
import org.example.service.AuthService;
import org.example.service.UserService;
import org.example.utils.DigestUtil;
import org.example.utils.TokenUtils;
import org.example.utils.WarehouseConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

//ResponseBody 与Controller
@RestController
public class LoginController {

    @Resource(name="captchaProducer")
    private Producer producer;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @RequestMapping("/captcha/captchaImage")
    public void captchaImage(HttpServletResponse response) throws IOException {

        //  将验证码图片写给前端
        ServletOutputStream outputStream = null;
        try {
            // 生成验证码图片的文件
            String text = producer.createText();

            // 使用验证码文本生成图片 --在内存
            BufferedImage image = producer.createImage(text);

            //将验证码保存到redis中,设置验证码过期时间
            redisTemplate.opsForValue().set(text,"",60*30, TimeUnit.SECONDS);

            // 设置响应正文image/jpeg
            response.setContentType("image/jpeg");

//            将验证码写入输出流
            outputStream = response.getOutputStream();
            ImageIO.write(image,"jpg",outputStream);
            //         刷新
            outputStream.flush();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally{
            if(outputStream!=null){
                outputStream.close();
            }
        }

    }

    /*
     * 接收并封装loginUser对象
    */

    @Autowired
    private UserService userService;

    @Autowired
//    注入token对象
    private TokenUtils tokenUtils;

    @RequestMapping("/login")
    public Result login(@RequestBody  LoginUser loginUser){

        String verificationCode = loginUser.getVerificationCode();
        if (!redisTemplate.hasKey(verificationCode)) {
            return Result.err(Result.CODE_ERR_BUSINESS,"验证码错误");
        }

        User user = userService.queryUserByCode(loginUser.getUserCode());

        if(user!=null){
            if(user.getUserState().equals(WarehouseConstants.USER_STATE_PASS)){
                String userPwd = loginUser.getUserPwd();

//                进行加密
                userPwd = DigestUtil.hmacSign(userPwd);

                if(userPwd.equals(user.getUserPwd())){
//                    生成Jwt token
                    CurrentUser currentUser = new CurrentUser(user.getUserId(),user.getUserCode(),user.getUserName());
                    String token = tokenUtils.loginSign(currentUser, userPwd);
                    return Result.ok("登录成功",token);
                }else{
                    return Result.err(Result.CODE_ERR_BUSINESS,"密码错误!");
                }

            }else{
                return Result.err(Result.CODE_ERR_BUSINESS,"账号未审核!");
            }
        }else{
            return Result.err(Result.CODE_ERR_BUSINESS,"账号不存在!");
        }
    }


    @RequestMapping("/curr-user")
    public Result currentUser(@RequestHeader(WarehouseConstants.HEADER_TOKEN_NAME) String token){
        CurrentUser currentUser = tokenUtils.getCurrentUser(token);

        return Result.ok(currentUser);
    }

    @Autowired
    private AuthService authService;

//    加载用户权限菜单树的URL
    @RequestMapping("/user/auth-list")
    public Result loadAuthTree(@RequestHeader(WarehouseConstants.HEADER_TOKEN_NAME) String token){
//        拿到登录用户id
        CurrentUser currentUser = tokenUtils.getCurrentUser(token);
        int userId = currentUser.getUserId();

//执行业务
        List<Auth> auths = authService.queryAuthTreeByUid(userId);

        return Result.ok(auths);
    }

//    登出
    @RequestMapping("/logout")
    public Result logout(@RequestHeader(WarehouseConstants.HEADER_TOKEN_NAME) String token){
//        从redis删除token
        redisTemplate.delete(token);
        return Result.ok("退出系统");
    }
}
