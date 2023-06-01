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
    private String hoTen;

    @Column(name = "ngaysinh")
    private Date ngaySinh;
    @Column(name = "quoctich")
    private String quocTich;
    @Column(name = "hinhanh")
    private String hinhAnh;
    @Column(name = "quequan")
    private String queQuan;

    @Column(name = "trangthai")
    private String trangThai;
    @Column(name = "loaicauthu")
    private String loaiCauThu;

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
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getQuocTich() {
        return quocTich;
    }

    public void setQuocTich(String quocTich) {
        this.quocTich = quocTich;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public String getQueQuan() {
        return queQuan;
    }

    public void setQueQuan(String queQuan) {
        this.queQuan = queQuan;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getLoaiCauThu() {
        return loaiCauThu;
    }

    public void setLoaiCauThu(String loaiCauThu) {
        this.loaiCauThu = loaiCauThu;
    }

    public List<KetQuaTranDauEntity> getDSBanThangCauThu_TranDau() {
        return DSBanThangCauThu_TranDau;
    }

    public void setDSBanThangCauThu_TranDau(List<KetQuaTranDauEntity> DSBanThangCauThu_TranDau) {
        this.DSBanThangCauThu_TranDau = DSBanThangCauThu_TranDau;
    }

}
