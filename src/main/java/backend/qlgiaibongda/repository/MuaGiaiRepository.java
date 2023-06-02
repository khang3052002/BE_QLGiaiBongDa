package backend.qlgiaibongda.repository;

import backend.qlgiaibongda.entity.MuaGiaiEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MuaGiaiRepository extends JpaRepository<MuaGiaiEntity, Long> {
}
