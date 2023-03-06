package ir.pt.struct.filter;

import ir.pt.struct.config.Dashboard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE)
public class HostCheckPreFilter implements Filter {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final Dashboard dashboard;

    public HostCheckPreFilter(Dashboard dashboard) {
        this.dashboard = dashboard;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest) req;
        String hosts = dashboard.getALLOW_HOST();
        String host = request.getHeader("host");
        String reqReferer = request.getHeader("Referer");
        String reqUrl = request.getRequestURI();
        log.info("######## host= " + host + " ## Referer= " + reqReferer
                + " ## getRequestURI=" + reqUrl + " ########");
        if (!matchHostsWhiteList(host, hosts)) {
            throw new ServletException("Host failed: Host is not valid");
        }
        chain.doFilter(request, response);
    }

    private boolean matchHostsWhiteList(String reqValue, String whiteList) {
        if (reqValue == null || reqValue.isEmpty() || "".equalsIgnoreCase(reqValue)
                || whiteList.equalsIgnoreCase("*"))
            return true;
        whiteList = StringUtils.trimAllWhitespace(whiteList);
        Set<String> whiteListOrigin = StringUtils.commaDelimitedListToSet(whiteList);
        if (whiteListOrigin != null && whiteListOrigin.size() > 0) {
            return whiteListOrigin.stream().anyMatch(f -> f.equalsIgnoreCase(reqValue));
        }
        return false;
    }

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void destroy() {
    }
}
