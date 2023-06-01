package backend.qlgiaibongda.dto;

import jakarta.persistence.Column;

import java.util.List;

public class TeamDTO  {

    private Long id;

    private String ten;
    private String hinhAnh;

    private int namThanhLap;

    private FieldDTO sanNha;

    private ManagerDTO quanLy;

    private List<CauThuDTO> danhSachCauThuDangThiDau;

    public List<CauThuDTO> getDanhSachCauThuDangThiDau() {
        return danhSachCauThuDangThiDau;
    }

    public void setDanhSachCauThuDangThiDau(List<CauThuDTO> danhSachCauThuDangThiDau) {
        this.danhSachCauThuDangThiDau = danhSachCauThuDangThiDau;
    }

    public ManagerDTO getQuanLy() {
        return quanLy;
    }

    public void setQuanLy(ManagerDTO quanLy) {
        this.quanLy = quanLy;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public FieldDTO getSanNha() {
        return sanNha;
    }

    public void setSanNha(FieldDTO sanNha) {
        this.sanNha = sanNha;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public int getNamThanhLap() {
        return namThanhLap;
    }

    public void setNamThanhLap(int namThanhLap) {
        this.namThanhLap = namThanhLap;
    }
}
