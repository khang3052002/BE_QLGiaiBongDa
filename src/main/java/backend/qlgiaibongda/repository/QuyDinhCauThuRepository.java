package backend.qlgiaibongda.repository;

import backend.qlgiaibongda.entity.QuyDinhCauThuEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuyDinhCauThuRepository extends JpaRepository<QuyDinhCauThuEntity, Long> {
}
