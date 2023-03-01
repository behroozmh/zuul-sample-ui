package ir.pt.struct.util;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Component;

@Component
public class Tools {
    public HttpEntity createHttpEntity(Object postModel, OAuth2AuthenticationDetails oauthDetail) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", "Bearer " + oauthDetail.getTokenValue());
        return new HttpEntity(postModel, httpHeaders);
    }
}
