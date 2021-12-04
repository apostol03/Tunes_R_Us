package com.yankovltd.tunes.web.interceptor;

import com.yankovltd.tunes.service.StatsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class StatusPanelInterceptor implements HandlerInterceptor {

    private final static Logger LOGGER = LoggerFactory.getLogger(StatusPanelInterceptor.class);
    private final StatsService statsService;

    public StatusPanelInterceptor(StatsService statsService) {
        this.statsService = statsService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String result = statsService.onRequestPeekPanel();
        if (result.contains(request.getRemoteUser())) {
            LOGGER.info("User: " + result);
            return true;
        }
        return false;
    }
}
