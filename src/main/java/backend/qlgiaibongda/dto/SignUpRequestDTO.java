package backend.qlgiaibongda.dto;

import java.sql.Date;
//import java.util.S;

public class SignUpRequestDTO {
    private String hoTen;
    private Date ngaySinh;
    private String taiKhoan;
    private String matKhau;
    private String codeVaiTro;



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

    public String getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(String taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getCodeVaiTro() {
        return codeVaiTro;
    }

    public void setCodeVaiTro(String codeVaiTro) {
        this.codeVaiTro = codeVaiTro;
    }
}
