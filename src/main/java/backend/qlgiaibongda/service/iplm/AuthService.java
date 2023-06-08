package backend.qlgiaibongda.service.iplm;

import backend.qlgiaibongda.config.Jwt.JwtService;
import backend.qlgiaibongda.dto.InfoUserRespone;
import backend.qlgiaibongda.dto.LoginRequestDTO;
import backend.qlgiaibongda.dto.ResponeObject;
import backend.qlgiaibongda.entity.QuanLyEntity;
import backend.qlgiaibongda.repository.QuanLiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtService jwtTokenProvider;
    @Autowired
    QuanLiRepository quanLiRepository;

    public ResponseEntity<ResponeObject> loginUser(LoginRequestDTO loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
            loginRequest.getTaiKhoan(),loginRequest.getMatKhau()
        ));
        if(authentication.isAuthenticated())
        {
            String jwtToken = jwtTokenProvider.generateToken(loginRequest.getTaiKhoan());

            QuanLyEntity quanLy = quanLiRepository.findByTaiKhoan(loginRequest.getTaiKhoan()).get();


            InfoUserRespone infoUserRespone = new InfoUserRespone(loginRequest.taiKhoan, quanLy.getVaiTro().getCode(),jwtToken);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponeObject("ok","Authenticated Success",infoUserRespone));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponeObject("fail","Authenticated Fail",loginRequest));

    }
}
