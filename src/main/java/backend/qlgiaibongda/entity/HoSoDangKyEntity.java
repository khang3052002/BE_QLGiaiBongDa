package backend.qlgiaibongda.entity;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "hosodangky")
public class HoSoDangKyEntity extends BaseEntity {

    @Column(name = "thoigiantao")
    private Date thoiGianTao;

    @Column(name = "trangthai")
    private String trangThai;

    @ManyToOne
    @JoinColumn(name = "id_muagiai")
    private MuaGiaiEntity MuaGiai;

    @ManyToOne
    @JoinColumn(name = "id_doibong")
    private DoiBongEntity DoiBong;

    //ChiTietHoSoDangKy
    @ManyToMany(mappedBy = "CacHoSoDangKy")
    private List<CauThuEntity> CacCauThu = new ArrayList<>();

    // QuanLy - HoSoDangKy
    @ManyToOne
    @JoinColumn(name="id_quanly")
    private QuanLyEntity quanLyDkiHoSo;

    ////////////////////////////////GETTER SETTER///////////////////////////////////////////


    public List<CauThuEntity> getCacCauThu() {
        return CacCauThu;
    }

    public void setCacCauThu(List<CauThuEntity> cacCauThu) {
        CacCauThu = cacCauThu;
    }

    public DoiBongEntity getDoiBong() {
        return DoiBong;
    }

    public void setDoiBong(DoiBongEntity doiBong) {

        DoiBong = doiBong;
    }


    public MuaGiaiEntity getMuaGiai() {
        return MuaGiai;
    }

    public void setMuaGiai(MuaGiaiEntity muaGiai) {
        MuaGiai = muaGiai;
    }

    public HoSoDangKyEntity() {
    }

    public Date getThoiGianTao() {
        return thoiGianTao;
    }

    public void setThoiGianTao(Date thoiGianTao) {
        this.thoiGianTao = thoiGianTao;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public QuanLyEntity getQuanLyDkiHoSo() {
        return quanLyDkiHoSo;
    }

    public void setQuanLyDkiHoSo(QuanLyEntity quanLyDkiHoSo) {
        this.quanLyDkiHoSo = quanLyDkiHoSo;
    }
}
