package backend.qlgiaibongda.repository;

import backend.qlgiaibongda.entity.CauThuEntity;
import backend.qlgiaibongda.entity.DoiBongEntity;
import backend.qlgiaibongda.entity.QuanLyEntity;
import backend.qlgiaibongda.entity.SanBongEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DoiBongRepository extends JpaRepository<DoiBongEntity, Long> {
    Optional<DoiBongEntity> findBySanBongId(Long sanBongId);

//    List<CauThuEntity>
}
