package backend.qlgiaibongda.entity;

import backend.qlgiaibongda.entity.cauthu_doibong.CauThuDoiBongEntity;
import jakarta.persistence.*;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "cauthu")
@NamedStoredProcedureQueries(
        @NamedStoredProcedureQuery(
                name = "searchAllPlayerByNameOrRole",
                procedureName = "searchAllPlayerByNameOrRole",
                resultClasses = {CauThuEntity.class},
                parameters = {
                        @StoredProcedureParameter(name="keyword",type = String.class, mode=ParameterMode.IN),
                        @StoredProcedureParameter(name="roles",type = String.class,mode = ParameterMode.IN)
                }
        )
)

public class CauThuEntity extends BaseEntity{

    @Column(name = "hoten")
    private String hoTen;


    @Column(name="madinhdanh", unique = true)
    private String maDinhDanh;

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

//    @Transient
//    private Integer age;
//    @Transient
//    public Integer getAge() {
//        System.out.println(283928392);
//
//        Date safeDate = new Date(this.ngaySinh.getTime());
//        if(safeDate !=null)
//        {
//            LocalDate birthday = safeDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//            return Period.between(birthday, LocalDate.now()).getYears();
//
//        }
//        return 0;
////        age = ;
//    }
    public int calculateAge() {
        Date safeDate = new Date(this.ngaySinh.getTime());
        if(safeDate !=null)
        {
            LocalDate birthday = safeDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            return Period.between(birthday, LocalDate.now()).getYears();

        }
        return 0;
    }

    //CauThu_ViTri
    @ManyToMany
    @JoinTable(name="cauthu_vitri",
                joinColumns = @JoinColumn(name = "id_cauthu"),
                inverseJoinColumns = @JoinColumn(name = "id_vitri"))
    private List<ViTriEntity> CacViTri = new ArrayList<>();

    //CauThu_DoiBong
    @OneToMany(mappedBy = "cauThuDB")
    private List<CauThuDoiBongEntity> CauThuDoiBong;

    //CHITIETHOSODANGKY
    @ManyToMany(fetch = FetchType.EAGER,mappedBy = "CacCauThu")
    private List<HoSoDangKyEntity> CacHoSoDangKy = new ArrayList<>();


    // Relationship CauThu - KetQuaTranDau
    // Cau thu ghi ban o nhieu thoi diem trong tran dau
    @OneToMany(mappedBy = "CauThu")
    private List<GhiNhanBanThangEntity> DSBanThangCauThu_TranDau = new ArrayList<>();



//    public void setAge(Integer age) {
//        this.age = age;
//    }

    public String getMaDinhDanh() {
        return maDinhDanh;
    }

    public void setMaDinhDanh(String maDinhDanh) {
        this.maDinhDanh = maDinhDanh;
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

    public List<GhiNhanBanThangEntity> getDSBanThangCauThu_TranDau() {
        return DSBanThangCauThu_TranDau;
    }

    public void setDSBanThangCauThu_TranDau(List<GhiNhanBanThangEntity> DSBanThangCauThu_TranDau) {
        this.DSBanThangCauThu_TranDau = DSBanThangCauThu_TranDau;
    }

}
