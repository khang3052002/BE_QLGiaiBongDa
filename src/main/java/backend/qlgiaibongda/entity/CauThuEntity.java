package backend.qlgiaibongda.entity;

import backend.qlgiaibongda.entity.cauthu_doibong.CauThuDoiBongEntity;
import jakarta.persistence.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


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
    private List<CauThuDoiBongEntity> CacDoiBong= new ArrayList<>();

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
}
