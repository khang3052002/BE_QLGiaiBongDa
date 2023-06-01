package backend.qlgiaibongda.service;

import backend.qlgiaibongda.dto.TeamDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ITeamService {
    List<TeamDTO> findAll(Pageable pageable);
    int totalItem();
}
