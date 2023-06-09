package backend.qlgiaibongda.service.iplm;

import backend.qlgiaibongda.dto.ResponeObject;
import backend.qlgiaibongda.dto.SignUpRequestDTO;
import backend.qlgiaibongda.entity.QuanLyEntity;
import backend.qlgiaibongda.entity.VaiTroEntity;
import backend.qlgiaibongda.repository.QuanLiRepository;
import backend.qlgiaibongda.repository.VaiTroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
public class SignUpService {
    @Autowired
    private QuanLiRepository quanLiRepository;
    @Autowired
    private VaiTroRepository vaiTroRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    public ResponseEntity<ResponeObject> registerManager(SignUpRequestDTO signupRequest) {

        Boolean usernameExists = quanLiRepository.existsByTaiKhoan(signupRequest.getTaiKhoan());
        if(!usernameExists)
        {
            QuanLyEntity quanLy = new QuanLyEntity();
            quanLy.setHoTen(signupRequest.getHoTen());
            quanLy.setTaiKhoan(signupRequest.getTaiKhoan());
            quanLy.setMatKhau(passwordEncoder.encode(signupRequest.getMatKhau()));
            quanLy.setNgaySinh(signupRequest.getNgaySinh()); // "2002-05-30"

            VaiTroEntity vaiTro = vaiTroRepository.findByCode(signupRequest.getCodeVaiTro()).orElse(null);
            if(vaiTro!=null)
            {
                quanLy.setVaiTro(vaiTro);

                quanLiRepository.save(quanLy);
                return ResponseEntity.status(HttpStatus.OK).body(new ResponeObject("OK","Register successful",signupRequest));

            }
            else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponeObject("Fail","Vai trò không tồn tại",""));

            }




        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponeObject("FAIL","Register FAIL",""));

    }
}
