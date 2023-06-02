package backend.qlgiaibongda.repository;

import backend.qlgiaibongda.entity.QuyDinhMuaGiaiEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuyDinhMuaGiaiRepository extends JpaRepository<QuyDinhMuaGiaiEntity, Long> {
}
