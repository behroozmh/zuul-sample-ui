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
//import javax.servlet.ServletOutputStream;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//
//@Component
//public class MenuGetList2PreFilter extends ZuulFilter {
//    private Logger logger = LoggerFactory.getLogger(MenuGetList2PreFilter.class);
//    private Dashboard dashboard;
//    private Tools tools;
//
//    public MenuGetList2PreFilter(Dashboard dashboard, Tools tools) {
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
//        return 0;
//    }
//
//    @Override
//    public boolean shouldFilter() {
//        RequestContext ctx = RequestContext.getCurrentContext();
//        HttpServletRequest request = ctx.getRequest();
//        if (request.getServletPath().equalsIgnoreCase("/authorize/menu/getList2"))
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
//            if (httpSession != null && httpSession.getAttribute("menuGetList2") != null) {
//                try {
//                    ServletOutputStream obj = (ServletOutputStream) httpSession.getAttribute("menuGetList2");
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        logger.info("####### ZUUL try menuGetList2 user={} #############", authentication != null ? authentication.getName() : "");
//        return null;
//    }
//}
