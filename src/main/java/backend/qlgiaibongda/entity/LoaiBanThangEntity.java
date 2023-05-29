package backend.qlgiaibongda.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "loaibanthang")
public class LoaiBanThangEntity extends BaseEntity {

    @Column(name = "ten")
    private String Ten;
    @Column(name = "mota")
    private String MoTa;

    //ChiTietQuyDinhBanThang
    @ManyToMany
    @JoinTable(name = "chitietquydinhbanthang",
                joinColumns = @JoinColumn(name = "id_loaibanthang"),
                inverseJoinColumns = @JoinColumn(name = "id_quydinhbanthang"))
    private List<QuyDinhBanThangEntity> CacQuyDinhBanThang = new ArrayList<>();

    public List<QuyDinhBanThangEntity> getCacQuyDinhBanThang() {
        return CacQuyDinhBanThang;
    }

    public void setCacQuyDinhBanThang(List<QuyDinhBanThangEntity> cacQuyDinhBanThang) {
        CacQuyDinhBanThang = cacQuyDinhBanThang;
    }

    public LoaiBanThangEntity() {
    }

    public String getTen() {
        return Ten;
    }

    public void setTen(String ten) {
        Ten = ten;
    }

    public String getMoTa() {
        return MoTa;
    }

    public void setMoTa(String moTa) {
        MoTa = moTa;
    }
}
