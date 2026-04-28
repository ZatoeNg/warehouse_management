package org.example.controller;


import org.example.beans.Auth;
import org.example.beans.Result;
import org.example.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/auth")
@RestController
public class AuthController {
    @Autowired
    private AuthService authService;

//    查询所有权限菜单
    @RequestMapping("/auth-tree")
    public Result loadAllAuthTree(){
        List<Auth> auths = authService.allAuthTree();

        return Result.ok(auths);
    }
}
