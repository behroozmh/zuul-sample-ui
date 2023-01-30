package ir.pt.struct.handler;

import ir.pt.struct.config.Dashboard;
import ir.pt.struct.util.Tools;
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

@Component
public class CustomLogoutHandler implements LogoutHandler {
    private Dashboard dashboard;
    private Tools tools;

    public CustomLogoutHandler(Dashboard dashboard, Tools tools) {
        this.dashboard = dashboard;
        this.tools = tools;
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        if (authentication != null) {
            HttpSession currentSessionFromRequest = request.getSession(false);
            if (currentSessionFromRequest != null)
                currentSessionFromRequest.invalidate();
            //
            SecurityContextHolder.clearContext();
            RestTemplate restTemplate = new RestTemplate();
            MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
            OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) authentication.getDetails();
            restTemplate.postForEntity(dashboard.getLOGOUT_URL(), tools.createHttpEntity(formData, details), null);
        }
    }
}
