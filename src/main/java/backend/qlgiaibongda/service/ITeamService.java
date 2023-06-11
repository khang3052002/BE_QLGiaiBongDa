package backend.qlgiaibongda.service;

import backend.qlgiaibongda.api.input.NewTeamInput;
import backend.qlgiaibongda.api.input.UpdateTeamInput;
import backend.qlgiaibongda.dto.CauThuDTO;
import backend.qlgiaibongda.dto.RequestKickPlayerInTeamDTO;
import backend.qlgiaibongda.dto.ResponeObject;
import backend.qlgiaibongda.dto.TeamDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ITeamService {
    List<TeamDTO> findAll(Pageable pageable);
    int totalItem();

    ResponseEntity<ResponeObject> findById(Long id);

    ResponseEntity<ResponeObject> save(NewTeamInput newTeamInput);

    ResponseEntity<ResponeObject> updateTeam(UpdateTeamInput updateTeamInput);

    ResponseEntity<ResponeObject> getPlayerOfTeamWithRoles(Long idTeam, String roles);

    ResponseEntity<ResponeObject> getPlayerOfTeam(Long idTeam);

    ResponseEntity<ResponeObject> kickPlayerInTeam(RequestKickPlayerInTeamDTO requestKickPlayerInTeamDTO);
}
