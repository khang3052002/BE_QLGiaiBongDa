package backend.qlgiaibongda.entity;

import backend.qlgiaibongda.entity.KetQuaTranDau.KetQuaTranDauEntity;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "loaibanthang")
public class LoaiBanThangEntity extends BaseEntity {

    @Column(name = "ten")
    private String ten;
    @Column(name = "mota")
    private String moTa;

    //ChiTietQuyDinhBanThang
    @ManyToMany
    @JoinTable(name = "chitietquydinhbanthang",
                joinColumns = @JoinColumn(name = "id_loaibanthang"),
                inverseJoinColumns = @JoinColumn(name = "id_quydinhbanthang"))
    private List<QuyDinhBanThangEntity> CacQuyDinhBanThang = new ArrayList<>();



    @OneToMany(mappedBy = "LoaiBanThang")
    private List<KetQuaTranDauEntity> DSKetQuaTranDau_LoaiBanThang = new ArrayList<>();

    public List<QuyDinhBanThangEntity> getCacQuyDinhBanThang() {
        return CacQuyDinhBanThang;
    }

    public void setCacQuyDinhBanThang(List<QuyDinhBanThangEntity> cacQuyDinhBanThang) {
        CacQuyDinhBanThang = cacQuyDinhBanThang;
    }

    public LoaiBanThangEntity() {
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public List<KetQuaTranDauEntity> getDSKetQuaTranDau_LoaiBanThang() {
        return DSKetQuaTranDau_LoaiBanThang;
    }

    public void setDSKetQuaTranDau_LoaiBanThang(List<KetQuaTranDauEntity> DSKetQuaTranDau_LoaiBanThang) {
        this.DSKetQuaTranDau_LoaiBanThang = DSKetQuaTranDau_LoaiBanThang;
    }
}
