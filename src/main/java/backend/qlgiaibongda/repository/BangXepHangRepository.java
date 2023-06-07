package backend.qlgiaibongda.repository;

import backend.qlgiaibongda.entity.BangXepHangEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BangXepHangRepository extends JpaRepository<BangXepHangEntity, Long> {
}
