//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package backend.qlgiaibongda.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
        name = "quanly"
)
public class QuanLyEntity extends BaseEntity {
    @Column(name = "hoten", columnDefinition = "nvarchar(255)")
    private String hoTen;
    @Column(name = "ngaysinh")
    private Date ngaySinh;
    @Column(name = "taikhoan")
    private String taiKhoan;
    @Column(name = "matkhau")
    private String matKhau;
    @OneToOne(mappedBy = "QuanLy")
    private DoiBongEntity DoiBong;
    @ManyToOne
    @JoinColumn(name = "id_vaitro")
    private VaiTroEntity VaiTro;
    @OneToMany(mappedBy = "QuanLyTaoLich")
    private List<LichThiDauEntity> dsLichThiDauTaoBoiQuanLi = new ArrayList();

    @OneToMany(mappedBy = "quanLyDkiHoSo")
    private List<HoSoDangKyEntity> DSHoSoDKYCuaQuanLy = new ArrayList<>();


    public VaiTroEntity getVaiTro() {
        return this.VaiTro;
    }

    public String getHoTen() {
        return this.hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public Date getNgaySinh() {
        return this.ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getTaiKhoan() {
        return this.taiKhoan;
    }

    public void setTaiKhoan(String taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    public String getMatKhau() {
        return this.matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public DoiBongEntity getDoiBong() {
        return DoiBong;
    }

    public void setDoiBong(DoiBongEntity doiBong) {
        DoiBong = doiBong;
    }

    public void setVaiTro(VaiTroEntity vaiTro) {
        VaiTro = vaiTro;
    }

    public List<LichThiDauEntity> getDsLichThiDauTaoBoiQuanLi() {
        return dsLichThiDauTaoBoiQuanLi;
    }

    public void setDsLichThiDauTaoBoiQuanLi(List<LichThiDauEntity> dsLichThiDauTaoBoiQuanLi) {
        this.dsLichThiDauTaoBoiQuanLi = dsLichThiDauTaoBoiQuanLi;
    }
}
