package backend.qlgiaibongda.service;

import backend.qlgiaibongda.dto.MuaGiaiDTO;
import backend.qlgiaibongda.dto.ResponeObject;
import org.springframework.http.ResponseEntity;

public interface IMuaGiaiService {
    ResponseEntity<ResponeObject> createLeague(MuaGiaiDTO muaGiaiDTO);
}
