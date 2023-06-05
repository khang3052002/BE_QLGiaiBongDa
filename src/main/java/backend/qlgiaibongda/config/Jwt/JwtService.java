package backend.qlgiaibongda.config.Jwt;

import backend.qlgiaibongda.entity.QuanLyEntity;
import backend.qlgiaibongda.repository.QuanLiRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.sql.Date;
import java.util.stream.Collectors;

@Service
public class JwtService {

    @Autowired
    private QuanLiRepository quanLiRepository;
    @Value("${app.jwtSecret}")
    private  String jwtSecret;
    @Value("${app.jwtExpiration}")
    private int jwtExpirationInMs;





    public String generateToken(String username)
    {
        QuanLyEntity user = quanLiRepository.findByTaiKhoan(username).get();
        Collection<SimpleGrantedAuthority> authorities  = (Collection<SimpleGrantedAuthority>) user.getAuthorities();
        Date expiryDate = new Date(System.currentTimeMillis() + jwtExpirationInMs);

        Algorithm algorithm = Algorithm.HMAC256(jwtSecret.getBytes());

        return JWT.create()
                .withClaim("roles",authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()) )
                .withSubject(username)
                .withIssuedAt(new Date(System.currentTimeMillis()))
                .withExpiresAt(expiryDate)
                .sign(algorithm);

    }


}
