package backend.qlgiaibongda.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "quydinhbanthang")
public class QuyDinhBanThangEntity extends BaseEntity{

    @Column(name = "thoidiemghibantoida")
    private int thoiDiemGhiBanToiDa;

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

    public int getThoiDiemGhiBanToiDa() {
        return thoiDiemGhiBanToiDa;
    }

    public void setThoiDiemGhiBanToiDa(int thoiDiemGhiBanToiDa) {
        this.thoiDiemGhiBanToiDa = thoiDiemGhiBanToiDa;
    }
}
