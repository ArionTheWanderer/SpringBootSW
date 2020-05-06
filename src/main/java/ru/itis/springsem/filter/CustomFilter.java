package ru.itis.springsem.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.GenericFilterBean;
import ru.itis.springsem.model.Role;
import ru.itis.springsem.model.User;
import ru.itis.springsem.repositories.UserRepository;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.Optional;

public class CustomFilter extends GenericFilterBean {

    @Autowired
    UserRepository userRepository;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        Principal principal = httpRequest.getUserPrincipal();

        if (principal != null) {
            Optional<User> userCandidate = userRepository.findUserByEmail(principal.getName());
            if (userCandidate.isPresent()) {
                User user = userCandidate.get();
                if (user.getRole().equals(Role.ADMIN)) {
                    System.out.println("Proceed request");
                    filterChain.doFilter(servletRequest, servletResponse);
                }
            }
        }
        System.out.println("Redirect from users page");
        String path = httpRequest.getContextPath() + "/";
        httpResponse.sendRedirect(path);

    }
}
