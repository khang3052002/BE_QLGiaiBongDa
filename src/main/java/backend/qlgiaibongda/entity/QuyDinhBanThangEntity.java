package backend.qlgiaibongda.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "quydinhbanthang")
public class QuyDinhBanThangEntity extends BaseEntity{

    @Column(name = "thoidiemghibantoida")
    private int thoiDiemGhiBanToiDa;

    @ManyToMany(mappedBy = "CacQuyDinhBanThang")
    private List<LoaiBanThangEntity> CacLoaiBanThang = new ArrayList<>();

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
