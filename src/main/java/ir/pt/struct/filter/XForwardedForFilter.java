//package ir.pt.struct.filter;
//
//import com.netflix.zuul.ZuulFilter;
//import com.netflix.zuul.context.RequestContext;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.http.HttpServletRequest;
//
//@Component
//public class XForwardedForFilter extends ZuulFilter {
//    private static final String X_FORWARDED_FOR = "X-Forwarded-For";
//
//    @Override
//    public Object run() {
//        RequestContext ctx = RequestContext.getCurrentContext();
//
//        // Rely on HttpServletRequest to retrieve the correct remote address from upstream X-Forwarded-For header
//        HttpServletRequest request = ctx.getRequest();
//        String remoteAddr = request.getRemoteAddr();
//
//        // Pass remote address downstream by setting X-Forwarded for header again on Zuul request
//        //log.debug("Settings X-Forwarded-For to: {}", remoteAddr);
//        System.out.println("Settings X-Forwarded-For to: "+ remoteAddr);
//        ctx.getZuulRequestHeaders().put(X_FORWARDED_FOR, remoteAddr);
//
//        return null;
//    }
//
//    @Override
//    public boolean shouldFilter() {
//        return true;
//    }
//
//    @Override
//    public String filterType() {
//        return "pre";
//    }
//
//    @Override
//    public int filterOrder() {
//        return 2;
//    }
//}
