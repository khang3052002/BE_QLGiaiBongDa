package backend.qlgiaibongda.service;

import backend.qlgiaibongda.dto.ResponeObject;
import org.springframework.http.ResponseEntity;

public interface IGeneralService {
    ResponseEntity<ResponeObject> searchPlayerAndTeamByKey(String keyword);
}
