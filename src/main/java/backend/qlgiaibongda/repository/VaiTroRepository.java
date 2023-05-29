package backend.qlgiaibongda.repository;

import backend.qlgiaibongda.entity.VaiTroEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface VaiTroRepository extends JpaRepository<VaiTroEntity, Long> {
    Optional<VaiTroEntity> findByCode(String code);

}
