package backend.qlgiaibongda.service;

import backend.qlgiaibongda.dto.ResponeObject;
import backend.qlgiaibongda.dto.TuChoiHoSoDTO;
import org.springframework.http.ResponseEntity;

public interface IHoSoDangKyService {
    ResponseEntity<ResponeObject> getHoSoDangKyByMuaGiai(Long id_muagiai);

    ResponseEntity<ResponeObject> getDetailHoSoDangKyByID(Long idHoso);

    ResponseEntity<ResponeObject> duyetHoSoDangKy(Long idHoso);

    ResponseEntity<ResponeObject> tuChoiHoSoDangKy(TuChoiHoSoDTO tuChoiHoSoDTO);

    ResponseEntity<ResponeObject> huyHoSoDangKyByQLDB(Long idHoso);

    ResponseEntity<ResponeObject> getHoSoDangKy_DoiBong(Long idDoibong);
}
