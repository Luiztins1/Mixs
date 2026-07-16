package com.github.Luiztins1.mixs.security;

import com.github.Luiztins1.mixs.model.entity.UserAuth;
import com.github.Luiztins1.mixs.model.mapper.UserAuthMapper;
import com.github.Luiztins1.mixs.service.UserAuthService;
import com.github.Luiztins1.mixs.utils.RandomGenerateUtil;
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
import java.util.List;

@Component
@RequiredArgsConstructor
public class LoginSocialSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private final UserAuthService userAuthService;

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

        if(user == null) user = registerUser(email);

        //Defini o usuário e nossa authentication.
        CustomAuthentication customAuth= new CustomAuthentication(user);

        //Captura o contexto de security e define baseado em nossa authentication.
        SecurityContextHolder.getContext().setAuthentication(customAuth);

        getRedirectStrategy().sendRedirect(request, response, "/api/v1/users");
    }

    public UserAuth registerUser(String email){
        UserAuth user;
        user = new UserAuth();
        user.setLogin(RandomGenerateUtil.generateLogin(8));
        user.setEmail(email);
        user.setPassword(RandomGenerateUtil.generatePassword(8));
        user.setRoles(List.of("USER"));

        userAuthService.registerUserAuth(user);
        return user;
    }
}
