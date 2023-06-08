package backend.qlgiaibongda.entity;

import backend.qlgiaibongda.dto.BXHDoiBongDTO;
import backend.qlgiaibongda.dto.BXH_DoiBongDTO;
import backend.qlgiaibongda.entity.bxh_doibong.BXHDoiBongEntity;
import backend.qlgiaibongda.entity.cauthu_doibong.CauThuDoiBongEntity;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "bangxephang")
@NamedStoredProcedureQueries(
        @NamedStoredProcedureQuery(
                name = "getBXH_MuaGiai",
                procedureName = "get_bxh_muagiai",
                resultClasses = {BXHDoiBongEntity.class},
                parameters = {
                        @StoredProcedureParameter(name="id_bxh",type = Long.class, mode=ParameterMode.IN)
                }
        )
)
public class BangXepHangEntity extends BaseEntity {

    //BXH_DOIBONG
    @OneToMany(mappedBy = "bxh")
    private List<BXHDoiBongEntity> BXHDoiBong;

    public List<BXHDoiBongEntity> getBXHDoiBong() {
        return BXHDoiBong;
    }

    public void setBXHDoiBong(List<BXHDoiBongEntity> BXHDoiBong) {
        this.BXHDoiBong = BXHDoiBong;
    }
}
