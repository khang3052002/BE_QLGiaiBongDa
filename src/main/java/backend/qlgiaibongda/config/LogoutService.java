package backend.qlgiaibongda.config;

import backend.qlgiaibongda.entity.TokenEntity;
import backend.qlgiaibongda.repository.TokenRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

@Service
public class LogoutService implements LogoutHandler {
    @Autowired
    private TokenRepository tokenRepository;
    @Override
    public void logout(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication)
    {
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
            return;
        }
        jwt = authHeader.substring(7);
//        System.out.println(jwt);

        TokenEntity tokenEntity = tokenRepository.findByToken(jwt);
        tokenEntity.setRevoked(true);
        tokenEntity.setExpired(true);
        tokenRepository.save(tokenEntity);
        SecurityContextHolder.clearContext();
    }
}
