package backend.qlgiaibongda.entity;

import backend.qlgiaibongda.entity.KetQuaTranDau.KetQuaTranDauEntity;
import backend.qlgiaibongda.entity.cauthu_doibong.CauThuDoiBongEntity;
import jakarta.persistence.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "cauthu")
public class CauThuEntity extends BaseEntity{

    @Column(name = "hoten")
    private String HoTen;

    @Column(name = "ngaysinh")
    private Date NgaySinh;
    @Column(name = "quoctich")
    private String QuocTich;
    @Column(name = "hinhanh")
    private String HinhAnh;
    @Column(name = "quequan")
    private String QueQuan;

    @Column(name = "trangthai")
    private String TrangThai;
    @Column(name = "loaicauthu")
    private String LoaiCauThu;

    //CauThu_ViTri
    @ManyToMany
    @JoinTable(name="cauthu_vitri",
                joinColumns = @JoinColumn(name = "id_cauthu"),
                inverseJoinColumns = @JoinColumn(name = "id_vitri"))
    private List<ViTriEntity> CacViTri = new ArrayList<>();

    //CauThu_DoiBong
    @OneToMany(mappedBy = "CauThuDB")
    private List<CauThuDoiBongEntity> CauThuDoiBong;

    //CHITIETHOSODANGKY
    @ManyToMany
    @JoinTable(name = "chitiethosodangky",
                joinColumns = @JoinColumn(name = "id_cauthu"),
                inverseJoinColumns = @JoinColumn(name = "id_hoso"))
    private List<HoSoDangKyEntity> CacHoSoDangKy = new ArrayList<>();


    // Relationship CauThu - KetQuaTranDau
    // Cau thu ghi ban o nhieu thoi diem trong tran dau
    @OneToMany(mappedBy = "CauThu")
    private List<KetQuaTranDauEntity> DSBanThangCauThu_TranDau = new ArrayList<>();


    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String trangThai) {
        TrangThai = trangThai;
    }

    public List<HoSoDangKyEntity> getCacHoSoDangKy() {
        return CacHoSoDangKy;
    }

    public void setCacHoSoDangKy(List<HoSoDangKyEntity> cacHoSoDangKy) {
        CacHoSoDangKy = cacHoSoDangKy;
    }

    public List<ViTriEntity> getCacViTri() {
        return CacViTri;
    }

    public List<CauThuDoiBongEntity> getCauThuDoiBong() {
        return CauThuDoiBong;
    }

    public void setCauThuDoiBong(List<CauThuDoiBongEntity> cauThuDoiBong) {
        CauThuDoiBong = cauThuDoiBong;
    }

    public void setCacViTri(List<ViTriEntity> cacViTri) {
        CacViTri = cacViTri;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String hoTen) {
        HoTen = hoTen;
    }

    public Date getNgaySinh() {
        return NgaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        NgaySinh = ngaySinh;
    }

    public String getQuocTich() {
        return QuocTich;
    }

    public void setQuocTich(String quocTich) {
        QuocTich = quocTich;
    }

    public String getHinhAnh() {
        return HinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        HinhAnh = hinhAnh;
    }

    public String getQueQuan() {
        return QueQuan;
    }

    public void setQueQuan(String queQuan) {
        QueQuan = queQuan;
    }

    public String getLoaiCauThu() {
        return LoaiCauThu;
    }

    public void setLoaiCauThu(String loaiCauThu) {
        LoaiCauThu = loaiCauThu;
    }

    public List<KetQuaTranDauEntity> getDSBanThangCauThu_TranDau() {
        return DSBanThangCauThu_TranDau;
    }

    public void setDSBanThangCauThu_TranDau(List<KetQuaTranDauEntity> DSBanThangCauThu_TranDau) {
        this.DSBanThangCauThu_TranDau = DSBanThangCauThu_TranDau;
    }
}
