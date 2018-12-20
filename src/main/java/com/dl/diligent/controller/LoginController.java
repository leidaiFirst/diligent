package com.dl.diligent.controller;

import com.dl.diligent.dto.MiniAppLoginParam;
import com.dl.diligent.dto.UserTokenResult;
import com.dl.diligent.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/api/system/")
public class LoginController {

    @Resource
    private LoginService loginService;

    @PostMapping("/wechat/login")
    public ResponseEntity login(@Valid @RequestBody MiniAppLoginParam miniAppLoginParam) throws Exception {
        UserTokenResult userRegisterResult = loginService.login(miniAppLoginParam);
        return ResponseEntity.ok(userRegisterResult);
    }


    @PostMapping("/wechat/refresh")
    public ResponseEntity refreshForWechat(@Valid @RequestBody MiniAppLoginParam miniAppLoginParam) throws Exception {
       String authCode = loginService.refresh(miniAppLoginParam);
       return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, authCode).build();
    }

    @GetMapping("/wechat/getQrcode")
    public ResponseEntity getQrcode() throws Exception {
        return ResponseEntity.ok(loginService.getQrcode());
    }
}
