package com.yankovltd.tunes.web.interceptor;

import com.yankovltd.tunes.service.StatsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class DeleteUserInterceptor implements HandlerInterceptor {

    private final static Logger LOGGER = LoggerFactory.getLogger(DeleteUserInterceptor.class);
    private final StatsService statsService;

    public DeleteUserInterceptor(StatsService statsService) {
        this.statsService = statsService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String result = statsService.onRequestDeleteUser();
        if (result.contains(request.getRemoteUser())) {
            String url = request.getRequestURL().toString();
            String id = url.substring(url.lastIndexOf("/") + 1);
            LOGGER.info("User: " + result + id);
            return true;
        }
        return false;
    }
}
