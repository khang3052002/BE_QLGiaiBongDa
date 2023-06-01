package backend.qlgiaibongda.service;

import backend.qlgiaibongda.api.input.NewTeamInput;
import backend.qlgiaibongda.dto.CauThuDTO;
import backend.qlgiaibongda.dto.TeamDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ITeamService {
    List<TeamDTO> findAll(Pageable pageable);
    int totalItem();

    TeamDTO findById(Long id);

    Object save(NewTeamInput newTeamInput);
}
