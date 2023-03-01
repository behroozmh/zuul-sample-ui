//package ir.pt.struct.filter;
//
//import com.netflix.zuul.ZuulFilter;
//import com.netflix.zuul.context.RequestContext;
//import ir.pt.struct.config.Dashboard;
//import ir.pt.struct.util.Tools;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//@Component
//public class SessionTimeoutPreFilter extends ZuulFilter {
//    private Logger logger = LoggerFactory.getLogger(SessionTimeoutPreFilter.class);
//    private Dashboard dashboard;
//    private Tools tools;
//
//    public SessionTimeoutPreFilter(Dashboard dashboard, Tools tools) {
//        this.dashboard = dashboard;
//        this.tools = tools;
//    }
//
//
//    @Override
//    public String filterType() {
//        return "pre";
//    }
//
//    @Override
//    public int filterOrder() {
//        return 1;
//    }
//
//    @Override
//    public boolean shouldFilter() {
//        RequestContext ctx = RequestContext.getCurrentContext();
//        HttpServletRequest request = ctx.getRequest();
//        if (request.getServletPath().equalsIgnoreCase("/authorize/login/getSessionTimeout"))
//            return true;
//        else return false;
//    }
//
//    @Override
//    public Object run() {
//        RequestContext ctx = RequestContext.getCurrentContext();
//        HttpServletRequest request = ctx.getRequest();
//        HttpServletResponse response = ctx.getResponse();
//        //
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication != null && authentication.isAuthenticated()) {
//            HttpSession httpSession = request.getSession(false);
//            //
//            Integer sessionTimeOut = 10;
//            httpSession.setMaxInactiveInterval(sessionTimeOut);
//            httpSession.setAttribute("sessionTimeOut", sessionTimeOut);
//        }
//        logger.info("####### ZUUL try getSessionTimeout user={} #############", authentication != null ? authentication.getName() : "");
//        return null;
//    }
//}
