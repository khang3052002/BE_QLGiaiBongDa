package backend.qlgiaibongda.repository;

import backend.qlgiaibongda.entity.DoiBongEntity;
import backend.qlgiaibongda.entity.SanBongEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SanBongRepository extends JpaRepository<SanBongEntity, Long> {


    List<SanBongEntity> findAllByDoiBongIsNull();
}
