package backend.qlgiaibongda.entity;

import backend.qlgiaibongda.entity.KetQuaTranDau.KetQuaTranDauEntity;
import backend.qlgiaibongda.entity.bxh_doibong.BXHDoiBongEntity;
import backend.qlgiaibongda.entity.cauthu_doibong.CauThuDoiBongEntity;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "doibong")
public class DoiBongEntity extends BaseEntity{

    @Column(name = "ten")
    private String Ten;
    @Column(name = "hinhanh")
    private String HinhAnh;

    @Column(name = "namthanhlap")
    private int NamThanhLap;


    //CauThu_DoiBong
    @OneToMany(mappedBy = "DoiBongCT")
    private List<CauThuDoiBongEntity> DoiBongCauThu;

    //BangXepHang
    @OneToMany(mappedBy = "DoiBong")
    private List<BXHDoiBongEntity> DoiBongBXH;

    @OneToMany(mappedBy = "DoiBong")
    private List<HoSoDangKyEntity> CacHoSoDangKy = new ArrayList<>();

    @OneToOne(mappedBy = "DoiBong")
    private QuanLyEntity QuanLy;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_sannha", referencedColumnName = "id")
    private SanBongEntity SanBong;
    @OneToMany(mappedBy = "DoiNha")
    private List<TranDauEntity> DSTranDauSanNha = new ArrayList();
    @OneToMany(mappedBy = "DoiKhach")
    private List<TranDauEntity> DSTranDauSanKhach = new ArrayList();

    @OneToMany(mappedBy = "DoiBong")
    private List<KetQuaTranDauEntity> DSBanThangDoiBong_TranDau = new ArrayList<>();


    ////////////////////////////////GETTER SETTER///////////////////////////////////////////

    public List<HoSoDangKyEntity> getCacHoSoDangKy() {
        return CacHoSoDangKy;
    }

    public void setCacHoSoDangKy(List<HoSoDangKyEntity> cacHoSoDangKy) {
        CacHoSoDangKy = cacHoSoDangKy;
    }

    public List<BXHDoiBongEntity> getDoiBongBXH() {
        return DoiBongBXH;
    }

    public void setDoiBongBXH(List<BXHDoiBongEntity> doiBongBXH) {
        DoiBongBXH = doiBongBXH;
    }

    public List<CauThuDoiBongEntity> getDoiBongCauThu() {
        return DoiBongCauThu;
    }

    public void setDoiBongCauThu(List<CauThuDoiBongEntity> doiBongCauThu) {
        DoiBongCauThu = doiBongCauThu;
    }

    public String getTen() {
        return Ten;
    }

    public void setTen(String ten) {
        Ten = ten;
    }

    public String getHinhAnh() {
        return HinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        HinhAnh = hinhAnh;
    }

    public int getNamThanhLap() {
        return NamThanhLap;
    }

    public void setNamThanhLap(int namThanhLap) {
        NamThanhLap = namThanhLap;
    }

    public QuanLyEntity getQuanLy() {
        return QuanLy;
    }

    public void setQuanLy(QuanLyEntity quanLy) {
        QuanLy = quanLy;
    }

    public SanBongEntity getSanBong() {
        return SanBong;
    }

    public void setSanBong(SanBongEntity sanBong) {
        SanBong = sanBong;
    }

    public List<TranDauEntity> getDSTranDauSanNha() {
        return DSTranDauSanNha;
    }

    public void setDSTranDauSanNha(List<TranDauEntity> DSTranDauSanNha) {
        this.DSTranDauSanNha = DSTranDauSanNha;
    }

    public List<TranDauEntity> getDSTranDauSanKhach() {
        return DSTranDauSanKhach;
    }

    public void setDSTranDauSanKhach(List<TranDauEntity> DSTranDauSanKhach) {
        this.DSTranDauSanKhach = DSTranDauSanKhach;
    }

    public List<KetQuaTranDauEntity> getDSBanThangDoiBong_TranDau() {
        return DSBanThangDoiBong_TranDau;
    }

    public void setDSBanThangDoiBong_TranDau(List<KetQuaTranDauEntity> DSBanThangDoiBong_TranDau) {
        this.DSBanThangDoiBong_TranDau = DSBanThangDoiBong_TranDau;
    }
}
