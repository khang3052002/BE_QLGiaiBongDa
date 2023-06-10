package backend.qlgiaibongda.dto;

import jakarta.persistence.Column;

import java.sql.Date;

public class ManagerDTO {

    private  Long id;
    private String hoTen;
    private Date ngaySinh;
    private String hinhAnh;

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public String getHoTen() {
        return hoTen;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
