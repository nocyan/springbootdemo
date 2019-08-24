package com.nocyan.springbootdemo.filter;

import com.nocyan.springbootdemo.pojo.User;
import com.nocyan.springbootdemo.pojo.UserAuth;
import com.nocyan.springbootdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Objects;

@WebFilter(filterName = "cookieVerifyFilter", urlPatterns = "/*")
public class CookieVerifyFilter implements Filter {
    @Autowired
    private UserService userService;

    private String[] ignores = new String[]{"/js/", "/css/"};

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        if(isIgnore(httpServletRequest)){
            chain.doFilter(request,response);
            return;
        }
        User user = null;
        Cookie[] cookies = httpServletRequest.getCookies();
        String identifier = null;
        Integer authType = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (identifier != null && authType != null) break;

                if (Objects.equals(cookie.getName(), "identifier")) {
                    identifier = cookie.getValue();
                }

                if (Objects.equals(cookie.getName(), "auth_type")) {
                    authType = Integer.parseInt(cookie.getValue());
                }
            }
        }
        UserAuth userAuth = userService.checkUserAuth(identifier, authType);
        if (userAuth != null) {
            user = userService.getUser(userAuth.getUid());
        }
        httpServletRequest.getSession().setAttribute("user", user);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }

    private boolean isIgnore(HttpServletRequest request) {
        boolean isIgnore = false;
        String uri = request.getRequestURI();
        for (String ignore:ignores) {
            if(uri.startsWith(ignore)){
                isIgnore=true;
                break;
            }
        }
        return isIgnore;
    }
}
