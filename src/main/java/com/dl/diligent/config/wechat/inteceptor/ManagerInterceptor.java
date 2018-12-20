package com.swj.lifelabexport.config.wechat.inteceptor;

import com.swj.basic.SwjConfig;
import com.swj.lifelabexport.consts.ZkConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 管理员身份验证工具
 */
@Slf4j
@Component
public class ManagerInterceptor extends HandlerInterceptorAdapter {

    private static  final String ADMIN_KEY = SwjConfig.get(ZkConst.ADMIN_KEY);

    /**
     * 管理员身份过滤验证
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        final String keyword = request.getParameter("keyword");

        if (StringUtils.isEmpty(keyword) || !keyword.equals(ADMIN_KEY)) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return false;
        }
        return true;
    }
}
