package backend.qlgiaibongda.service;

import backend.qlgiaibongda.api.input.UpdateMatchInput;
import backend.qlgiaibongda.dto.ResponeObject;
import org.springframework.http.ResponseEntity;

public interface ITranDauService {

    ResponseEntity<ResponeObject> getMatch(Long id);
    ResponseEntity<ResponeObject> updateTime(UpdateMatchInput updateMatchInput);


    ResponseEntity<ResponeObject> getAllMatchByTeamAndLeague(Long idMuaGiai,Long idDoiBong, String trangThai);
}
