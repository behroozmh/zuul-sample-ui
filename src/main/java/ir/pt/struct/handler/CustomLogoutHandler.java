package ir.pt.struct.handler;

import ir.pt.struct.config.Dashboard;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
@Component
public class CustomLogoutHandler implements LogoutHandler {
    @Autowired
    private Dashboard dashboard;

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        if (authentication != null) {
            OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) authentication.getDetails();
            HttpSession currentSessionFromRequest = request.getSession(false);
            if (currentSessionFromRequest != null)
                currentSessionFromRequest.invalidate();
            //
            SecurityContextHolder.clearContext();
            RestTemplate restTemplate = new RestTemplate();
            MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
            ResponseEntity responseEntity = restTemplate.postForEntity(dashboard.getMY_AUTH_LOGOUT_URL(),
                    createHttpEntity(formData, details), null);

            if (responseEntity.getStatusCode() == HttpStatus.OK ||
                    responseEntity.getStatusCode() == HttpStatus.FOUND ||
                    responseEntity.getStatusCode() == HttpStatus.TEMPORARY_REDIRECT) {
                log.info("####### Success logout {} #### code {}", authentication.getName(), responseEntity.getStatusCode());
                //
            }
        }
    }

    private HttpEntity createHttpEntity(Object postModel, OAuth2AuthenticationDetails oauthDetail) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", "Bearer " + oauthDetail.getTokenValue());
        return new HttpEntity(postModel, httpHeaders);
    }
}
