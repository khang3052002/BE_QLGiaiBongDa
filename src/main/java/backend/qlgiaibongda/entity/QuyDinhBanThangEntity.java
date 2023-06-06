package backend.qlgiaibongda.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "quydinhbanthang")
public class QuyDinhBanThangEntity extends BaseEntity{

    @Column(name = "thoidiemghibantoida")
    private Long thoiDiemGhiBanToiDa;

    @ManyToMany(mappedBy = "CacQuyDinhBanThang")
    private List<LoaiBanThangEntity> CacLoaiBanThang = new ArrayList<>();

    @OneToOne(mappedBy = "QuyDinhBanThang")
    private QuyDinhMuaGiaiEntity quyDinhMuaGiai;
    public List<LoaiBanThangEntity> getCacLoaiBanThang() {
        return CacLoaiBanThang;
    }

    public void setCacLoaiBanThang(List<LoaiBanThangEntity> cacLoaiBanThang) {
        CacLoaiBanThang = cacLoaiBanThang;
    }

    public QuyDinhBanThangEntity() {
    }

    public Long getThoiDiemGhiBanToiDa() {
        return thoiDiemGhiBanToiDa;
    }

    public void setThoiDiemGhiBanToiDa(Long thoiDiemGhiBanToiDa) {
        this.thoiDiemGhiBanToiDa = thoiDiemGhiBanToiDa;
    }

    public QuyDinhMuaGiaiEntity getQuyDinhMuaGiai() {
        return quyDinhMuaGiai;
    }

    public void setQuyDinhMuaGiai(QuyDinhMuaGiaiEntity quyDinhMuaGiai) {
        this.quyDinhMuaGiai = quyDinhMuaGiai;
    }
}
