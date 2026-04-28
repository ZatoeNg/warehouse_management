package org.example.filter;

import com.alibaba.fastjson.JSON;
import org.example.beans.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.example.utils.WarehouseConstants.HEADER_TOKEN_NAME;

public class LoginCheckFilter implements Filter {

    private StringRedisTemplate redisTemplate;

    public void setRedisTemplate(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
//白名单直接放行
        List<String> urls = new ArrayList<>();
        urls.add("/captcha/captchaImage");
        urls.add("/login");
        urls.add("/product/img-upload");
//        过滤器拦截到的当前接口
        String servletPath = request.getServletPath();

        if(urls.contains(servletPath) || servletPath.contains("/img/upload")){
            filterChain.doFilter(request,response);
            return;
        }


//其他请求都校验
        String token = request.getHeader(HEADER_TOKEN_NAME);


        if (StringUtils.hasText(token) && redisTemplate.hasKey(token)){
            filterChain.doFilter(request,response);
            return;
        }

        Result result = Result.err(Result.CODE_ERR_UNLOGINED, "未登录");
        String jsonString = JSON.toJSONString(result);
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.write(jsonString);
        out.flush();
        out.close();


    }
}
