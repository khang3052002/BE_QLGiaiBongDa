package backend.qlgiaibongda.repository;

import backend.qlgiaibongda.entity.bxh_doibong.BXHDoiBongEntity;
import backend.qlgiaibongda.entity.bxh_doibong.BXHDoiBongKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BXHDoiBongRepository extends JpaRepository<BXHDoiBongEntity, BXHDoiBongKey> {

}
