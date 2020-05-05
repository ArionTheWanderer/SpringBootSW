package ru.itis.springsem.filter;

import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class CustomFilter extends GenericFilterBean {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        String agent = httpRequest.getHeader("User-Agent");

        if (agent.contains("Chrome")) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            throw new IllegalArgumentException();
        }
    }
}
