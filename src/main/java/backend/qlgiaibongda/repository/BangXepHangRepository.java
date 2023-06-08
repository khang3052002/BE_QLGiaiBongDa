package backend.qlgiaibongda.repository;

import backend.qlgiaibongda.dto.BXH_DoiBongDTO;
import backend.qlgiaibongda.entity.BangXepHangEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BangXepHangRepository extends JpaRepository<BangXepHangEntity, Long> {


//    @Query(value ="call get_bxh_muagiai(:id_bxh)" ,nativeQuery = true)
//    List<Object> getBXH_MuaGiai(@Param("id_bxh") Long id_bxh);
}
