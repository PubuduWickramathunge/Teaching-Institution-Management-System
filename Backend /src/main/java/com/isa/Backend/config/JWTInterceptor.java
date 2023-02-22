package com.isa.Backend.config;

import com.isa.Backend.service.BlackListingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
@Slf4j
public class JWTInterceptor implements HandlerInterceptor {


    private final BlackListingService blackListingService;

    private final UserRequestScopedBean userRequestScopedBean;

    public JWTInterceptor(BlackListingService blackListingService, UserRequestScopedBean userRequestScopedBean) {
        this.blackListingService = blackListingService;
        this.userRequestScopedBean = userRequestScopedBean;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        try {
            String token = request.getHeader("Authorization");
            if (token == null || !token.startsWith("Bearer ")) {
                log.error("JwtInterceptor - Invalid token format");
                response.sendError(401);
                return false;
            }

            if (blackListingService.getJwtBlackList(token) != null) {
                log.error("JwtInterceptor: Token is blacklisted");
                response.sendError(401);
                return false;
            }

            userRequestScopedBean.setJwt(token);
            return true;
        } catch (Exception e) {
            log.error("JwtInterceptor - Exception : {} ",e.getMessage());
            response.sendError(401);
            return false;
        }
    }
}
