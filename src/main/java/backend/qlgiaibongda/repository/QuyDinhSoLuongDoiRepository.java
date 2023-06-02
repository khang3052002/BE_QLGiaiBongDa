package backend.qlgiaibongda.repository;

import backend.qlgiaibongda.entity.QuyDinhSoLuongDoiEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuyDinhSoLuongDoiRepository extends JpaRepository<QuyDinhSoLuongDoiEntity,Long> {
}
