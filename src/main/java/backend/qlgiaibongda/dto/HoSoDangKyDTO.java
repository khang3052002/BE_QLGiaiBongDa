package backend.qlgiaibongda.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HoSoDangKyDTO {
    private Long id;
    private Date thoiGianTao;
    private String trangThai;
    private Long id_giai;
    private String tenGiai;
    private String hinhAnhGiai;
    private Long id_doibong;
    private String ten_doibong;
    private String hinhAnhDoi;
    private Long id_quanly;
    private String ten_quanly;
    private String ghiChu;


    private List<CauThuDTO> dsCauThuDangKy = new ArrayList<>();
    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public String getTenGiai() {
        return tenGiai;
    }

    public void setTenGiai(String tenGiai) {
        this.tenGiai = tenGiai;
    }

    public String getHinhAnhGiai() {
        return hinhAnhGiai;
    }

    public void setHinhAnhGiai(String hinhAnhGiai) {
        this.hinhAnhGiai = hinhAnhGiai;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getThoiGianTao() {
        return thoiGianTao;
    }

    public void setThoiGianTao(Date thoiGianTao) {
        this.thoiGianTao = thoiGianTao;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public Long getId_giai() {
        return id_giai;
    }

    public void setId_giai(Long id_giai) {
        this.id_giai = id_giai;
    }

    public Long getId_doibong() {
        return id_doibong;
    }

    public void setId_doibong(Long id_doibong) {
        this.id_doibong = id_doibong;
    }

    public String getTen_doibong() {
        return ten_doibong;
    }

    public void setTen_doibong(String ten_doibong) {
        this.ten_doibong = ten_doibong;
    }

    public String getHinhAnhDoi() {
        return hinhAnhDoi;
    }

    public void setHinhAnhDoi(String hinhAnhDoi) {
        this.hinhAnhDoi = hinhAnhDoi;
    }

    public Long getId_quanly() {
        return id_quanly;
    }

    public void setId_quanly(Long id_quanly) {
        this.id_quanly = id_quanly;
    }

    public String getTen_quanly() {
        return ten_quanly;
    }

    public void setTen_quanly(String ten_quanly) {
        this.ten_quanly = ten_quanly;
    }

    public List<CauThuDTO> getDsCauThuDangKy() {
        return dsCauThuDangKy;
    }

    public void setDsCauThuDangKy(List<CauThuDTO> dsCauThuDangKy) {
        this.dsCauThuDangKy = dsCauThuDangKy;
    }
}
