package com.github.Luiztins1.mixs.security;

import com.github.Luiztins1.mixs.model.entity.UserAuth;
import com.github.Luiztins1.mixs.service.UserAuthService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class LoginSocialSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private final UserAuthService userAuthService;
    private final static String AlphaNumericString =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
            + "0123456789"
            + "abcdefghijklmnopqrstuvxyz";

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication) throws ServletException, IOException {

        //Captura token do authentication Google.
        OAuth2AuthenticationToken authenticationToken = (OAuth2AuthenticationToken) authentication;

        //Pega o usuário que vem no token.
        OAuth2User oauth2User = authenticationToken.getPrincipal();

        //Pega o campo "email" do usuário.
        String email = oauth2User.getAttribute("email");

        //Procura usuário pelo email.
        UserAuth user = userAuthService.findByEmail(email);
        
        //Defini o usuário e nossa authentication.
        CustomAuthentication customAuthentication = new CustomAuthentication(user);

        //Captura o contexto de security e define baseado em nossa authentication.
        SecurityContextHolder.getContext().setAuthentication(customAuthentication);

        super.onAuthenticationSuccess(request, response, customAuthentication);
    }

}
