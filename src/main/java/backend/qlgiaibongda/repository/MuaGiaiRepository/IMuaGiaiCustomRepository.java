package backend.qlgiaibongda.repository.MuaGiaiRepository;

import backend.qlgiaibongda.entity.MuaGiaiEntity;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import java.util.List;
//@NoRepositoryBean
public interface IMuaGiaiCustomRepository {
    List<MuaGiaiEntity> findLeaguesWithFiltered(String keyword, Integer trangthai);
}