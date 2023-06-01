package backend.qlgiaibongda.dto;

import jakarta.persistence.Column;

import java.sql.Date;

public class CauThuDTO  {
    private String HoTen;
    private Date NgaySinh;
    private String QuocTich;
    private String HinhAnh;
    private String QueQuan;
    private String TrangThai;
    private String LoaiCauThu;

    private Long idDoi;

    private Long id;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdDoi() {
        return idDoi;
    }

    public void setIdDoi(Long idDoi) {
        this.idDoi = idDoi;
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

    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String trangThai) {
        TrangThai = trangThai;
    }

    public String getLoaiCauThu() {
        return LoaiCauThu;
    }

    public void setLoaiCauThu(String loaiCauThu) {
        LoaiCauThu = loaiCauThu;
    }
}
