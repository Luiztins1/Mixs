package com.github.Luiztins1.mixs.security;

import com.github.Luiztins1.mixs.model.entity.UserAuth;
import com.github.Luiztins1.mixs.service.UserAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SecurityService {

    public UserAuth getUserAuth(){
        var authenticate = SecurityContextHolder.getContext().getAuthentication();

        if(authenticate instanceof CustomAuthentication customAuth) return customAuth.getUserAuth();
        return null;
    }
}
