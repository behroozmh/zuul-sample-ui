//package ir.pt.struct.filter;
//
//import com.netflix.zuul.ZuulFilter;
//import com.netflix.zuul.context.RequestContext;
//import ir.pt.struct.config.Dashboard;
//import ir.pt.struct.util.Tools;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//
//@Component
//public class HostCheckPreFilter extends ZuulFilter {
//    private Logger logger = LoggerFactory.getLogger(HostCheckPreFilter.class);
//    private Dashboard dashboard;
//
//    public HostCheckPreFilter(Dashboard dashboard) {
//        this.dashboard = dashboard;
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
//        return -10;
//    }
//
//    @Override
//    public boolean shouldFilter() {
////        RequestContext ctx = RequestContext.getCurrentContext();
////        HttpServletRequest request = ctx.getRequest();
////        if (!request.getHeader("Host").equalsIgnoreCase("*"))
////            return true;
////        else return false;
//        return false;
//    }
//
//    @Override
//    public Object run() {
//        RequestContext ctx = RequestContext.getCurrentContext();
//        try {
//            logger.info("####### ZUUL HostCheckPreFilter try invalid Host #############");
//            ctx.setSendZuulResponse(false);
//            ctx.getResponse().sendRedirect("/error");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//}
