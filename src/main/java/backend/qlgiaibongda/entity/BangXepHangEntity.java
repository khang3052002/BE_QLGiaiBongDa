package backend.qlgiaibongda.entity;

import backend.qlgiaibongda.entity.bxh_doibong.BXHDoiBongEntity;
import backend.qlgiaibongda.entity.cauthu_doibong.CauThuDoiBongEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "bangxephang")
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
