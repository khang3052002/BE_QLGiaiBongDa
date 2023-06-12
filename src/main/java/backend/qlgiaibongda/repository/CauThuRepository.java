package backend.qlgiaibongda.repository;

import backend.qlgiaibongda.entity.CauThuEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CauThuRepository extends JpaRepository<CauThuEntity, Long> {

    boolean existsByMaDinhDanh(String madDinhDanh);
    List<CauThuEntity> findAllByHoTenContainsIgnoreCase(String keyword);

    List<CauThuEntity> findAllByTrangThai(String trangThai);
    boolean existsByIdAndTrangThai(Long id, String trangThai);

}
