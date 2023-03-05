package com.isa.Backend.config;

import com.isa.Backend.exception.InvalidTokenFormatException;
import com.isa.Backend.exception.JwtInterceptorException;
import com.isa.Backend.exception.TokenBlacklistedException;
import com.isa.Backend.service.BlackListingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
@Slf4j
public class JwtInterceptor implements HandlerInterceptor {


    private final BlackListingService blackListingService;

    private final UserRequestScopedBean userRequestScopedBean;

    public JwtInterceptor(BlackListingService blackListingService, UserRequestScopedBean userRequestScopedBean) {
        this.blackListingService = blackListingService;
        this.userRequestScopedBean = userRequestScopedBean;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        try {
            String token = request.getHeader("Authorization");
            if (token == null || !token.startsWith("Bearer ")) {
                System.out.println(token);
                throw new InvalidTokenFormatException("Invalid token format: " + token);
            }

            if (blackListingService.getJwtBlackList(token) != null) {
                log.error("JwtInterceptor: Token is blacklisted");
                throw new TokenBlacklistedException("Token is blacklisted: " + token);
            }

            userRequestScopedBean.setJwt(token);
            return true;
        }  catch (Exception e) {
        log.error("JwtInterceptor - Exception : {} ", e.getMessage());
        throw new JwtInterceptorException("Error in JwtInterceptor", e);
        }
    }
}
