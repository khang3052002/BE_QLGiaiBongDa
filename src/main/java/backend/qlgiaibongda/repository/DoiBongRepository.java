package backend.qlgiaibongda.repository;

import backend.qlgiaibongda.entity.CauThuEntity;
import backend.qlgiaibongda.entity.DoiBongEntity;
import backend.qlgiaibongda.entity.QuanLyEntity;
import backend.qlgiaibongda.entity.SanBongEntity;
import backend.qlgiaibongda.entity.cauthu_doibong.CauThuDoiBongEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DoiBongRepository extends JpaRepository<DoiBongEntity, Long> {
    Optional<DoiBongEntity> findBySanBongId(Long sanBongId);
    List<DoiBongEntity> findAllByTenContainsIgnoreCase(String keyword);
}
