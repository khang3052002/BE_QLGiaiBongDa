package backend.qlgiaibongda.service.iplm;

import backend.qlgiaibongda.config.Jwt.JwtService;
import backend.qlgiaibongda.dto.InfoUserRespone;
import backend.qlgiaibongda.dto.LoginRequestDTO;
import backend.qlgiaibongda.dto.ResponeObject;
import backend.qlgiaibongda.entity.QuanLyEntity;
import backend.qlgiaibongda.entity.TokenEntity;
import backend.qlgiaibongda.repository.QuanLiRepository;
import backend.qlgiaibongda.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthService {

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtService jwtTokenProvider;
    @Autowired
    QuanLiRepository quanLiRepository;
    @Autowired
    TokenRepository tokenRepository;

    public ResponseEntity<ResponeObject> loginUser(LoginRequestDTO loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
            loginRequest.getTaiKhoan(),loginRequest.getMatKhau()
        ));
        if(authentication.isAuthenticated())
        {
            String jwtToken = jwtTokenProvider.generateToken(loginRequest.getTaiKhoan());

            QuanLyEntity quanLy = quanLiRepository.findByTaiKhoan(loginRequest.getTaiKhoan()).get();

            List<TokenEntity> listToken = tokenRepository.findAllByQuanLyAndExpiredIsFalseOrRevokedIsFalse(quanLy);
            if(!listToken.isEmpty())
            {
                listToken.forEach(token -> {
                    token.setExpired(true);
                    token.setRevoked(true);
                });
                tokenRepository.saveAll(listToken);
            }
            saveToken(quanLy,jwtToken);
            InfoUserRespone infoUserRespone = new InfoUserRespone(loginRequest.taiKhoan, quanLy.getVaiTro().getCode(),jwtToken, quanLy.getHoTen(), quanLy.getHinhAnh(), quanLy.getNgaySinh());
            return ResponseEntity.status(HttpStatus.OK).body(new ResponeObject("ok","Authenticated Success",infoUserRespone));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponeObject("fail","Authenticated Fail",loginRequest));
    }
    void saveToken(QuanLyEntity quanLy, String jwt)
    {
        TokenEntity tokenEntity = new TokenEntity();
        tokenEntity.setToken(jwt);
        tokenEntity.setExpired(false);
        tokenEntity.setRevoked(false);
        tokenEntity.setQuanLy(quanLy);

        tokenRepository.save(tokenEntity);
    }
}
