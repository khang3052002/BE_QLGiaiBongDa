package backend.qlgiaibongda.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name ="ketquatrandau")
public class KetQuaTranDauEntity extends  BaseEntity{

    @OneToMany(mappedBy = "ketQuaTranDau")
    private List<GhiNhanBanThangEntity> dsBanThang = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "id_trandau", referencedColumnName = "id")
    private TranDauEntity tranDau;
    @ManyToOne
    @JoinColumn(name="id_doinha")
    private DoiBongEntity doiNha;

    @ManyToOne
    @JoinColumn(name = "id_doikhach")
    private DoiBongEntity doiKhach;

    private Integer sbtDoiNha;
    private Integer sbtDoiKhach;

    private String trangThai;

    public List<GhiNhanBanThangEntity> getDsBanThang() {
        return dsBanThang;
    }

    public void setDsBanThang(List<GhiNhanBanThangEntity> dsBanThang) {
        this.dsBanThang = dsBanThang;
    }

    public TranDauEntity getTranDau() {
        return tranDau;
    }

    public void setTranDau(TranDauEntity tranDau) {
        this.tranDau = tranDau;
    }

    public DoiBongEntity getDoiNha() {
        return doiNha;
    }

    public void setDoiNha(DoiBongEntity doiNha) {
        this.doiNha = doiNha;
    }

    public DoiBongEntity getDoiKhach() {
        return doiKhach;
    }

    public void setDoiKhach(DoiBongEntity doiKhach) {
        this.doiKhach = doiKhach;
    }

    public Integer getSbtDoiNha() {
        return sbtDoiNha;
    }

    public void setSbtDoiNha(Integer sbtDoiNha) {
        this.sbtDoiNha = sbtDoiNha;
    }

    public Integer getSbtDoiKhach() {
        return sbtDoiKhach;
    }

    public void setSbtDoiKhach(Integer sbtDoiKhach) {
        this.sbtDoiKhach = sbtDoiKhach;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
}
