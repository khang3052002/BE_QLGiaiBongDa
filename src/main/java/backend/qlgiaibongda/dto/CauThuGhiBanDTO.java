package backend.qlgiaibongda.dto;

public class CauThuGhiBanDTO {
    private Long id;
    private Long idCauThu;
    private String tenCauThu;
    private Long idDoi;
    private String tenDoi;
    private int soLuongBanThang;

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

    public int getSoLuongBanThang() {
        return soLuongBanThang;
    }

    public void setSoLuongBanThang(int soLuongBanThang) {
        this.soLuongBanThang = soLuongBanThang;
    }
}
