//package ir.pt.struct.handler;
//
//import org.springframework.boot.autoconfigure.web.ErrorController;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//@Controller
//public class CustomErrorController implements ErrorController {
//
//    @ExceptionHandler
//    @RequestMapping("/error")
//    public String handleError() {
//        //do something like logging
//        return getErrorPath();
//    }
//
//    @RequestMapping("/accessDenied")
//    public String handleAccessDenied() {
//        //do something like logging
//        return "accessDenied";
//    }
//
//    public String getErrorPath() {
//        return "/error";
//    }
//
//}
