package com.swj.lifelabexport.config.wechat.inteceptor;

import com.swj.lifelabexport.helper.UserContextHelper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 身份验证工具
 */
@Slf4j
@Component
public class JwtInterceptor extends HandlerInterceptorAdapter {


    /**
     * 身份过滤验证
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        final String token = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (StringUtils.isEmpty(token)) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return false;
        }

        try {
            UserContextHelper.setUserContext(token);
        } catch (ExpiredJwtException e) {
            log.error("Auth code expired.authHeader:{}", token);
            response.setStatus(HttpStatus.FORBIDDEN.value());
            return false;
        } catch (UnsupportedJwtException | IllegalArgumentException | MalformedJwtException e) {
            log.error("Auth token :{} is error", token, e);
            response.setStatus(HttpStatus.FORBIDDEN.value());
            return false;
        }

        return true;
    }
}
