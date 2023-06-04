package backend.qlgiaibongda.entity;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "muagiai")
public class MuaGiaiEntity extends BaseEntity {

    @Column(name = "ten")
    private String ten;

    @Column(name = "thoidiembatdau")
    private Date thoiDiemBatDau;
    @Column(name = "thoidiemketthuc")
    private Date thoiDiemKetThuc;

    @Column(name="trangthai")
    private Integer trangThai;
    // Co 3 loai:
    // 0: Đang đăng ký
    // 1: Hoạt động
    // 2: Kết thúc

    @OneToOne
    @JoinColumn(name="id_bangxephang")
    private BangXepHangEntity bxh;

    @OneToMany(mappedBy = "muaGiai")
    private List<HoSoDangKyEntity> CacHoSoDangKy = new ArrayList<>();

    @OneToMany(mappedBy = "MuaGiai")
    private List<CauThuGhiBanEntity> CacCauThuGhiBan = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name="id_nguoitao")
    private QuanLyEntity quanLyMuaGiai;

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
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public Date getThoiDiemBatDau() {
        return thoiDiemBatDau;
    }

    public void setThoiDiemBatDau(Date thoiDiemBatDau) {
        this.thoiDiemBatDau = thoiDiemBatDau;
    }

    public Date getThoiDiemKetThuc() {
        return thoiDiemKetThuc;
    }

    public void setThoiDiemKetThuc(Date thoiDiemKetThuc) {
        this.thoiDiemKetThuc = thoiDiemKetThuc;
    }

    public LichThiDauEntity getLichThiDau() {
        return LichThiDau;
    }

    public void setLichThiDau(LichThiDauEntity lichThiDau) {
        LichThiDau = lichThiDau;
    }

    public QuanLyEntity getQuanLyMuaGiai() {
        return quanLyMuaGiai;
    }

    public void setQuanLyMuaGiai(QuanLyEntity quanLyMuaGiai) {
        this.quanLyMuaGiai = quanLyMuaGiai;
    }

    public Integer getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Integer trangThai) {
        this.trangThai = trangThai;
    }

    public MuaGiaiEntity() {
    }
}
