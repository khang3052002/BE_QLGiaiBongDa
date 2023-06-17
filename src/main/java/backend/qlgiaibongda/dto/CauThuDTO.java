package backend.qlgiaibongda.dto;

import jakarta.persistence.Column;

import java.sql.Date;
import java.util.ArrayList;

public class CauThuDTO  {
    private Long id;
    private Long idDoi;
    private String hoTen;
    private Date ngaySinh;
    private String quocTich;
    private String hinhAnh;
    private String queQuan;
    private Integer  age;
    private Integer soAo;
    private String maDinhDanh;
    private String trangThai;
    private String loaiCauThu;

    private Date thoiDiemBatDau;
    private Date thoiDiemKetThuc;
    private int tongSoBanThang;

    public Boolean checkValidInfo_EditPlayer(){
        Boolean check = true;
        if( id ==null || hoTen == null || ngaySinh == null || quocTich == null ||
                hinhAnh == null || queQuan==null || 
                loaiCauThu == null || viTri == null || soAo == null)
        {
            check = false;
        }

        return check;
    }

    public Integer getSoAo() {
        return soAo;
    }

    public void setSoAo(Integer soAo) {
        this.soAo = soAo;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    private String[] viTri;

    public Boolean checkValidInfo(){
        Boolean check = true;
        if(hoTen == null || ngaySinh == null || quocTich == null || hinhAnh == null || queQuan==null
                || loaiCauThu==null || thoiDiemKetThuc==null || viTri == null || maDinhDanh == null || soAo == null){
            check = false;
        }

        return check;
    }

    public Date getThoiDiemBatDau() {
        return thoiDiemBatDau;
    }

    public void setThoiDiemBatDau(Date thoiDiemBatDau) {
        this.thoiDiemBatDau = thoiDiemBatDau;
    }

    public Date getThoiDiemKetThuc() {
        return thoiDiemKetThuc;
    }

    public void setThoiDiemKetThuc(Date thoiDiemKetThuc) {
        this.thoiDiemKetThuc = thoiDiemKetThuc;
    }

    public int getTongSoBanThang() {
        return tongSoBanThang;
    }

    public void setTongSoBanThang(int tongSoBanThang) {
        this.tongSoBanThang = tongSoBanThang;
    }

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

    public String getMaDinhDanh() {
        return maDinhDanh;
    }

    public void setMaDinhDanh(String maDinhDanh) {
        this.maDinhDanh = maDinhDanh;
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

    public String[] getViTri() {
        return viTri;
    }

    public void setViTri(String[] viTri) {
        this.viTri = viTri;
    }
}
