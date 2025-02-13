package com.itu.coworking.config;
import com.itu.coworking.model.Utilisateur;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class RoleInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        Object utilisateur = session.getAttribute("utilisateur");

        if (utilisateur == null) {
            response.sendRedirect("/");
            return false;
        }

        String role = ((Utilisateur) utilisateur).getProfil().getProfil();
        String requestURI = request.getRequestURI();


        if (requestURI.startsWith("/admin") && !"Administrateur".equals(role)) {
            response.sendRedirect("/access-denied");
            return false;
        }

        // Vérifier l'accès aux pages client
        if (requestURI.startsWith("/client") && !"Client".equals(role)) {
            response.sendRedirect("/access-denied");
            return false;
        }

        return true;
    }
}
