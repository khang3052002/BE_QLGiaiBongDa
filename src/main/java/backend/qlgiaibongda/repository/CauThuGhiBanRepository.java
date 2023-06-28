package backend.qlgiaibongda.repository;

import backend.qlgiaibongda.entity.CauThuEntity;
import backend.qlgiaibongda.entity.CauThuGhiBan.CauThuGhiBanEntity;
import backend.qlgiaibongda.entity.CauThuGhiBan.CauThuGhiBanKey;
import backend.qlgiaibongda.entity.DoiBongEntity;
import backend.qlgiaibongda.entity.MuaGiaiEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CauThuGhiBanRepository extends JpaRepository<CauThuGhiBanEntity, CauThuGhiBanKey> {

    CauThuGhiBanEntity findCauThuGhiBanEntityByCauThuAndDoiBongAndAndMuaGiai(CauThuEntity cauThu, DoiBongEntity doiBong, MuaGiaiEntity muaGiai);
    boolean existsByCauThuAndDoiBongAndMuaGiai(CauThuEntity cauThu, DoiBongEntity doiBong, MuaGiaiEntity muaGiai);



}
