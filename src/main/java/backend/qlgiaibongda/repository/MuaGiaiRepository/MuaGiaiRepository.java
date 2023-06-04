package backend.qlgiaibongda.repository.MuaGiaiRepository;

import backend.qlgiaibongda.entity.MuaGiaiEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MuaGiaiRepository extends JpaRepository<MuaGiaiEntity, Long>
{

}
