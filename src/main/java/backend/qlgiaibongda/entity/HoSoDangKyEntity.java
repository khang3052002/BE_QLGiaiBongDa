package backend.qlgiaibongda.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "hosodangky")
@EntityListeners(AuditingEntityListener.class)
public class HoSoDangKyEntity extends BaseEntity {

    @Column(name = "thoigiantao")
    @CreatedDate
    private Date thoiGianTao;

    @Column(name = "trangthai")
    private String trangThai;

    @ManyToOne
    @JoinColumn(name = "id_muagiai")
    private MuaGiaiEntity muaGiai;

    @ManyToOne
    @JoinColumn(name = "id_doibong")
    private DoiBongEntity doiBong;

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
        return doiBong;
    }

    public void setDoiBong(DoiBongEntity doiBong) {

        this.doiBong = doiBong;
    }


    public MuaGiaiEntity getMuaGiai() {
        return muaGiai;
    }

    public void setMuaGiai(MuaGiaiEntity muaGiai) {
        this.muaGiai = muaGiai;
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
