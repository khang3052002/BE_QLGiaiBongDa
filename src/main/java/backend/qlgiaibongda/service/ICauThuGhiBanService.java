package backend.qlgiaibongda.service;

import backend.qlgiaibongda.dto.ResponeObject;
import org.springframework.http.ResponseEntity;

public interface ICauThuGhiBanService {
    ResponseEntity<ResponeObject> layDSCauThuGhiBan(Long idMuaGiai);
}
