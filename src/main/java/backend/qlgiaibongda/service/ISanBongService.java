package backend.qlgiaibongda.service;

import backend.qlgiaibongda.dto.ResponeObject;
import org.springframework.http.ResponseEntity;

public interface ISanBongService {
    ResponseEntity<ResponeObject> getStadiumsNotRegistered();
}
