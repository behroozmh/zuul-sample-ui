package ir.pt.struct.config;

import org.springframework.security.web.csrf.*;
import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.UUID;

public class MyCsrfTokenRepository implements CsrfTokenRepository {
    static final String DEFAULT_CSRF_COOKIE_NAME = "XSRF-TOKEN";

    static final String DEFAULT_CSRF_PARAMETER_NAME = "_csrf";

    static final String DEFAULT_CSRF_HEADER_NAME = "X-XSRF-TOKEN";

    private String parameterName = DEFAULT_CSRF_PARAMETER_NAME;

    private String headerName = DEFAULT_CSRF_HEADER_NAME;

    private String cookieName = DEFAULT_CSRF_COOKIE_NAME;

    private final Method setHttpOnlyMethod;

    private boolean cookieHttpOnly;

    private String cookiePath;

    private static final String DEFAULT_CSRF_TOKEN_ATTR_NAME = HttpSessionCsrfTokenRepository.class
            .getName().concat(".CSRF_TOKEN");

    private String sessionAttributeName = DEFAULT_CSRF_TOKEN_ATTR_NAME;

    public MyCsrfTokenRepository() {
        this.setHttpOnlyMethod = ReflectionUtils.findMethod(Cookie.class, "setHttpOnly", boolean.class);
        if (this.setHttpOnlyMethod != null) {
            this.cookieHttpOnly = true;
        }
    }

    @Override
    public CsrfToken generateToken(HttpServletRequest request) {
        return new DefaultCsrfToken(this.headerName, this.parameterName,
                createNewToken());
    }

    @Override
    public void saveToken(CsrfToken token, HttpServletRequest request,
                          HttpServletResponse response) {
        String tokenValue = token == null ? "" : token.getToken();
        Cookie cookie = new Cookie(this.cookieName, tokenValue);
        cookie.setSecure(request.isSecure());
        if (this.cookiePath != null && !this.cookiePath.isEmpty()) {
            cookie.setPath(this.cookiePath);
        } else {
            cookie.setPath(this.getRequestContext(request));
        }
        if (token == null) {
            cookie.setMaxAge(0);
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.removeAttribute(this.sessionAttributeName);
            }
        } else {
            cookie.setMaxAge(-1);
            HttpSession session = request.getSession();
            session.setAttribute(this.sessionAttributeName, token);
        }
        if (cookieHttpOnly && setHttpOnlyMethod != null) {
            ReflectionUtils.invokeMethod(setHttpOnlyMethod, cookie, Boolean.TRUE);
        }

        response.addCookie(cookie);
    }

    @Override
    public CsrfToken loadToken(HttpServletRequest request) {
        Cookie cookie = WebUtils.getCookie(request, this.cookieName);
        if (cookie == null) {
            return null;
        }
        String token = cookie.getValue();
        if (!StringUtils.hasLength(token)) {
            return null;
        }
        HttpSession session = request.getSession(false);
        if (session == null) {
            return null;
        }

        CsrfToken seCsrfToken= (CsrfToken) session.getAttribute(this.sessionAttributeName);
        if(!seCsrfToken.getToken().equals(token))
            return null;

        return new DefaultCsrfToken(this.headerName, this.parameterName, token);
    }

    /**
     * Sets the name of the HTTP request parameter that should be used to provide a token.
     *
     * @param parameterName the name of the HTTP request parameter that should be used to
     *                      provide a token
     */
    public void setParameterName(String parameterName) {
        Assert.notNull(parameterName, "parameterName is not null");
        this.parameterName = parameterName;
    }

    /**
     * Sets the name of the HTTP header that should be used to provide the token.
     *
     * @param headerName the name of the HTTP header that should be used to provide the
     *                   token
     */
    public void setHeaderName(String headerName) {
        Assert.notNull(headerName, "headerName is not null");
        this.headerName = headerName;
    }

    /**
     * Sets the name of the cookie that the expected CSRF token is saved to and read from.
     *
     * @param cookieName the name of the cookie that the expected CSRF token is saved to
     *                   and read from
     */
    public void setCookieName(String cookieName) {
        Assert.notNull(cookieName, "cookieName is not null");
        this.cookieName = cookieName;
    }

    /**
     * Sets the HttpOnly attribute on the cookie containing the CSRF token.
     * The cookie will only be marked as HttpOnly if both <code>cookieHttpOnly</code> is <code>true</code> and the underlying version of Servlet is 3.0 or greater.
     * Defaults to <code>true</code> if the underlying version of Servlet is 3.0 or greater.
     * NOTE: The {@link Cookie#setHttpOnly(boolean)} was introduced in Servlet 3.0.
     *
     * @param cookieHttpOnly <code>true</code> sets the HttpOnly attribute, <code>false</code> does not set it (depending on Servlet version)
     * @throws IllegalArgumentException if <code>cookieHttpOnly</code> is <code>true</code> and the underlying version of Servlet is less than 3.0
     */
    public void setCookieHttpOnly(boolean cookieHttpOnly) {
        if (cookieHttpOnly && setHttpOnlyMethod == null) {
            throw new IllegalArgumentException("Cookie will not be marked as HttpOnly because you are using a version of Servlet less than 3.0. NOTE: The Cookie#setHttpOnly(boolean) was introduced in Servlet 3.0.");
        }
        this.cookieHttpOnly = cookieHttpOnly;
    }

    private String getRequestContext(HttpServletRequest request) {
        String contextPath = request.getContextPath();
        return contextPath.length() > 0 ? contextPath : "/";
    }

    /**
     * Factory method to conveniently create an instance that has
     * {@link #setCookieHttpOnly(boolean)} set to false.
     *
     * @return an instance of CookieCsrfTokenRepository with
     * {@link #setCookieHttpOnly(boolean)} set to false
     */
    public static CookieCsrfTokenRepository withHttpOnlyFalse() {
        CookieCsrfTokenRepository result = new CookieCsrfTokenRepository();
        result.setCookieHttpOnly(false);
        return result;
    }

    private String createNewToken() {
        return UUID.randomUUID().toString();
    }

    /**
     * Set the path that the Cookie will be created with. This will override the default functionality which uses the
     * request context as the path.
     *
     * @param path the path to use
     */
    public void setCookiePath(String path) {
        this.cookiePath = path;
    }

    /**
     * Get the path that the CSRF cookie will be set to.
     *
     * @return the path to be used.
     */
    public String getCookiePath() {
        return this.cookiePath;
    }
}