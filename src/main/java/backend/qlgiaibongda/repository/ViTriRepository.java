package backend.qlgiaibongda.repository;

import backend.qlgiaibongda.entity.ViTriEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ViTriRepository extends JpaRepository<ViTriEntity, Long> {
    Optional<ViTriEntity> findByCode(String code);
}
