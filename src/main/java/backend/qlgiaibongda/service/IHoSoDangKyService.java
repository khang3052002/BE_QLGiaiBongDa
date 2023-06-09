package backend.qlgiaibongda.service;

import backend.qlgiaibongda.dto.ResponeObject;
import org.springframework.http.ResponseEntity;

public interface IHoSoDangKyService {
    ResponseEntity<ResponeObject> getHoSoDangKyByMuaGiai(Long id_muagiai);

    ResponseEntity<ResponeObject> getDetailHoSoDangKyByID(Long idHoso);

    ResponseEntity<ResponeObject> duyetHoSoDangKy(Long idHoso);
}
