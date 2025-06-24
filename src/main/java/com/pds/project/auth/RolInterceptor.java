package com.pds.project.auth;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Base64;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class RolInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        String token = request.getHeader("Authorization");
        if (token == null || token.isBlank()) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Falta token de autorización");
            return false;
        }

        String rol = new String(Base64.getDecoder().decode(token));
        String path = request.getRequestURI();
        request.setAttribute("rolUsuario", rol);

        if (path.startsWith("/admin") && !rol.equals("ADMIN")) {
            if (path.equals("/admin/vehiculos/disponibles")
                    || path.startsWith("/admin/pedidos/getPedidosPorComprador")) {
                return true;
            }
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write("Acceso restringido para ADMIN");
            return false;
        }

        if (path.startsWith("/cliente") && !rol.equals("CLIENTE")) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write("Acceso restringido para CLIENTE");
            return false;
        }

        if (path.startsWith("/vendedor") && !rol.equals("VENDEDOR")) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write("Acceso restringido para VENDEDOR");
            return false;
        }

        return true;
    }
}
