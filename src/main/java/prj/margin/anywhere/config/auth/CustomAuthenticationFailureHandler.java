package prj.margin.anywhere.config.auth;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String errorMessage = "";

        if (exception instanceof BadCredentialsException) errorMessage = "loginId or Password is not correct. please check again";
        else if (exception instanceof InternalAuthenticationServiceException) errorMessage = "Couldn't deal with the problem for internal error. Please ask to administrator";
        else if (exception instanceof UsernameNotFoundException) errorMessage = "There is no account information please check again";
        else if (exception instanceof AuthenticationCredentialsNotFoundException) errorMessage = "request for authentication is rejected";
        else if (exception instanceof SessionAuthenticationException) errorMessage = "currently, this account is active in web";
        else errorMessage = "login process has been failed for unknown reason";
        setDefaultFailureUrl("/login?error=true&exception=" + errorMessage);

        super.onAuthenticationFailure(request, response, exception);
    }
}
