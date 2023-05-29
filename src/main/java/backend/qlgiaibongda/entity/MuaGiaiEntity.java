package backend.qlgiaibongda.entity;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "muagiai")
public class MuaGiaiEntity extends BaseEntity {

    @Column(name = "ten")
    private String Ten;

    @Column(name = "thoidiembatdau")
    private Date ThoiDiemBatDau;
    @Column(name = "thoidiemketthuc")
    private Date ThoiDiemKetThuc;


    @OneToOne
    @JoinColumn(name="id_bangxephang")
    private BangXepHangEntity bxh;

    @OneToMany(mappedBy = "MuaGiai")
    private List<HoSoDangKyEntity> CacHoSoDangKy = new ArrayList<>();

    @OneToMany(mappedBy = "MuaGiai")
    private List<CauThuGhiBanEntity> CacCauThuGhiBan = new ArrayList<>();



    @ManyToOne
    @JoinColumn(name = "id_quydinh")
    private QuyDinhMuaGiaiEntity QuyDinhMuaGiai;


    @OneToOne(mappedBy = "MuaGiai")
    private LichThiDauEntity LichThiDau;

    public List<CauThuGhiBanEntity> getCacCauThuGhiBan() {
        return CacCauThuGhiBan;
    }

    public void setCacCauThuGhiBan(List<CauThuGhiBanEntity> cacCauThuGhiBan) {
        CacCauThuGhiBan = cacCauThuGhiBan;
    }

    public List<HoSoDangKyEntity> getCacHoSoDangKy() {
        return CacHoSoDangKy;
    }

    public void setCacHoSoDangKy(List<HoSoDangKyEntity> cacHoSoDangKy) {
        CacHoSoDangKy = cacHoSoDangKy;
    }

    public QuyDinhMuaGiaiEntity getQuyDinhMuaGiai() {
        return QuyDinhMuaGiai;
    }

    public void setQuyDinhMuaGiai(QuyDinhMuaGiaiEntity quyDinhMuaGiai) {
        QuyDinhMuaGiai = quyDinhMuaGiai;
    }

    public BangXepHangEntity getBxh() {
        return bxh;
    }

    public void setBxh(BangXepHangEntity bxh) {
        this.bxh = bxh;
    }

    public String getTen() {
        return Ten;
    }

    public void setTen(String ten) {
        Ten = ten;
    }

    public Date getThoiDiemBatDau() {
        return ThoiDiemBatDau;
    }

    public void setThoiDiemBatDau(Date thoiDiemBatDau) {
        ThoiDiemBatDau = thoiDiemBatDau;
    }

    public Date getThoiDiemKetThuc() {
        return ThoiDiemKetThuc;
    }

    public void setThoiDiemKetThuc(Date thoiDiemKetThuc) {
        ThoiDiemKetThuc = thoiDiemKetThuc;
    }

    public MuaGiaiEntity() {
    }
}
