package backend.qlgiaibongda.dto;

import java.sql.Date;

public class InfoUserRespone {
    private String username;
    private String role;
    private String token;
    private String hoTen;
    private String hinhAnh;
    private Date ngaySinh;
    private Long id_doibong;

    public Long getId_doibong() {
        return id_doibong;
    }

    public void setId_doibong(Long id_doibong) {
        this.id_doibong = id_doibong;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public InfoUserRespone(String username, String role, String token, String hoTen, String hinhAnh, Date ngaySinh, Long id_doibong) {
        this.username = username;
        this.role = role;
        this.token = token;
        this.hoTen = hoTen;
        this.hinhAnh = hinhAnh;
        this.ngaySinh = ngaySinh;
        this.id_doibong = id_doibong;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
