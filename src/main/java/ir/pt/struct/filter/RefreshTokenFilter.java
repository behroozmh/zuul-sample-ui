package ir.pt.struct.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@Slf4j
@Component
public class RefreshTokenFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        HttpServletResponse response = ctx.getResponse();
        Cookie[] cookies = request.getCookies();
        String randomValue = String.valueOf(ThreadLocalRandom.current().nextInt(10001, 99999));
        if (cookies != null && cookies.length > 0) {
            boolean findBehi = false;
            for (Cookie cookie : cookies) {
                if (cookie.getName().equalsIgnoreCase("behi007")) {
                    cookie.setValue(randomValue);
                    cookie.setPath(request.getContextPath());
                    response.addCookie(cookie);
                    findBehi = true;
                    break;
                }
            }
            if (!findBehi) {
                Cookie cookie= new Cookie("behi007", randomValue);
                cookie.setPath(request.getContextPath());
                response.addCookie(cookie);
            }
        }

        if (request.getServletPath().contains("access")) {
            log.info("##### access token error for user {}");
        }
        Map<String, String> headers = ctx.getZuulRequestHeaders();
        return null;
    }
}
