package backend.qlgiaibongda.service;

import backend.qlgiaibongda.api.input.NewTeamPlayerInput;
import backend.qlgiaibongda.dto.CauThuDTO;
import backend.qlgiaibongda.dto.ResponeObject;
import org.springframework.http.ResponseEntity;

public interface ICauThuService {
    CauThuDTO save(CauThuDTO cauThuDTO);

    ResponseEntity<ResponeObject> addNewListTeamPlayer(NewTeamPlayerInput teamPlayerInput);
    ResponseEntity<ResponeObject> getPlayerByID(Long id);

    ResponseEntity<ResponeObject> addNewPlayer(CauThuDTO cauThuDTO) ;

    ResponseEntity<ResponeObject> editPlayer(CauThuDTO cauThuDTO);

    ResponseEntity<ResponeObject> searchAllPlayerByNameOrRole(String keyword, String roles);
}
