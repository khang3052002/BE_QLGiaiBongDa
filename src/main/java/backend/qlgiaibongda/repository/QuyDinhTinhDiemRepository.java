package backend.qlgiaibongda.repository;

import backend.qlgiaibongda.entity.QuyDinhTinhDiemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuyDinhTinhDiemRepository extends JpaRepository<QuyDinhTinhDiemEntity,Long> {
}
