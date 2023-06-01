package backend.qlgiaibongda.service.iplm;

import backend.qlgiaibongda.dto.TeamDTO;
import backend.qlgiaibongda.entity.DoiBongEntity;
import backend.qlgiaibongda.mapper.TeamMapper;
import backend.qlgiaibongda.repository.DoiBongRepository;
import backend.qlgiaibongda.service.ITeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class TeamService implements ITeamService {

    @Autowired
    private DoiBongRepository doiBongRepository;

    private TeamMapper teamMapper;

    @Override
    public List<TeamDTO> findAll(Pageable pageable) {
        List<TeamDTO> results = new ArrayList<>();
        List<DoiBongEntity> entities = doiBongRepository.findAll(pageable).getContent();
        for(DoiBongEntity entity: entities){
            TeamDTO newDTO = teamMapper.INSTANCE.toDTO(entity);
            results.add(newDTO);
        }
        return results;
    }

    @Override
    public int totalItem() {
        return (int)doiBongRepository.count();
    }
}
