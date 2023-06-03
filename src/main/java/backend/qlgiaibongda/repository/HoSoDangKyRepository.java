package backend.qlgiaibongda.repository;

import backend.qlgiaibongda.entity.DoiBongEntity;
import backend.qlgiaibongda.entity.HoSoDangKyEntity;
import backend.qlgiaibongda.entity.MuaGiaiEntity;
import backend.qlgiaibongda.service.iplm.MuaGiaiService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HoSoDangKyRepository extends JpaRepository<HoSoDangKyEntity, Long> {
//    Optional<HoSoDangKyEntity> findByDoiBongAndMuaGiai(DoiBongEntity doiBong, MuaGiaiEntity muaGiai);

    Boolean existsByDoiBongAndMuaGiai(DoiBongEntity doiBong, MuaGiaiEntity muaGiai);
//    Integer countByMuaGiaiOrderByTrangThai(MuaGiaiEntity muaGiai,String trangThai);
    Long countByTrangThaiAndMuaGiai(String trangThai, MuaGiaiEntity muaGiai);

}
