package backend.qlgiaibongda.repository;

import backend.qlgiaibongda.entity.CauThuEntity;
import backend.qlgiaibongda.entity.DoiBongEntity;
import backend.qlgiaibongda.entity.cauthu_doibong.CauThuDoiBongEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CauThuDoiBongRepository extends JpaRepository<CauThuDoiBongEntity, Long> {
    CauThuDoiBongEntity findCauThuDoiBongEntityByCauThuDBAndInTeam(CauThuEntity cauThu, Integer inTeam);
    CauThuDoiBongEntity findCauThuDoiBongEntityByCauThuDBAndDoiBongCT(CauThuEntity cauThu, DoiBongEntity doiBongEntity);


    CauThuDoiBongEntity findCauThuDoibongEntityByCauThuDBAndDoiBongCTAndInTeam(CauThuEntity cauThu, DoiBongEntity doiBongEntity, Integer inTeam);

}
