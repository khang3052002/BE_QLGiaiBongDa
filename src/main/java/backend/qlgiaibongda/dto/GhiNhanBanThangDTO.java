package backend.qlgiaibongda.dto;

import java.sql.Date;

public class GhiNhanBanThangDTO {
    private Long id;
    private Long idCauThu;
    private String tenCauThu;
    private Long idDoi;
    private String tenDoi;
    private LoaiBanThangDTO loaiBanThang;
    private Long thoiDiemGhiBan;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdCauThu() {
        return idCauThu;
    }

    public void setIdCauThu(Long idCauThu) {
        this.idCauThu = idCauThu;
    }

    public String getTenCauThu() {
        return tenCauThu;
    }

    public void setTenCauThu(String tenCauThu) {
        this.tenCauThu = tenCauThu;
    }

    public Long getIdDoi() {
        return idDoi;
    }

    public void setIdDoi(Long idDoi) {
        this.idDoi = idDoi;
    }

    public String getTenDoi() {
        return tenDoi;
    }

    public void setTenDoi(String tenDoi) {
        this.tenDoi = tenDoi;
    }

    public LoaiBanThangDTO getLoaiBanThang() {
        return loaiBanThang;
    }

    public void setLoaiBanThang(LoaiBanThangDTO loaiBanThang) {
        this.loaiBanThang = loaiBanThang;
    }

    public Long getThoiDiemGhiBan() {
        return thoiDiemGhiBan;
    }

    public void setThoiDiemGhiBan(Long thoiDiemGhiBan) {
        this.thoiDiemGhiBan = thoiDiemGhiBan;
    }
}
