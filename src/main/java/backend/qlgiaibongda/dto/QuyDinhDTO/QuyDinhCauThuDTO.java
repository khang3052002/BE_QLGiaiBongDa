package backend.qlgiaibongda.dto.QuyDinhDTO;

import jakarta.persistence.Column;

public class QuyDinhCauThuDTO {
    private int soLuongCauThuToiDa;
    private int soLuongCauThuToiThieu;
    private int soLuongCauThuNuocNgoaiToiDa;
    private int tuoiToiThieu;
    private int tuoiToiDa;

    public int getSoLuongCauThuToiDa() {
        return soLuongCauThuToiDa;
    }

    public void setSoLuongCauThuToiDa(int soLuongCauThuToiDa) {
        this.soLuongCauThuToiDa = soLuongCauThuToiDa;
    }

    public int getSoLuongCauThuToiThieu() {
        return soLuongCauThuToiThieu;
    }

    public void setSoLuongCauThuToiThieu(int soLuongCauThuToiThieu) {
        this.soLuongCauThuToiThieu = soLuongCauThuToiThieu;
    }

    public int getSoLuongCauThuNuocNgoaiToiDa() {
        return soLuongCauThuNuocNgoaiToiDa;
    }

    public void setSoLuongCauThuNuocNgoaiToiDa(int soLuongCauThuNuocNgoaiToiDa) {
        this.soLuongCauThuNuocNgoaiToiDa = soLuongCauThuNuocNgoaiToiDa;
    }

    public int getTuoiToiThieu() {
        return tuoiToiThieu;
    }

    public void setTuoiToiThieu(int tuoiToiThieu) {
        this.tuoiToiThieu = tuoiToiThieu;
    }

    public int getTuoiToiDa() {
        return tuoiToiDa;
    }

    public void setTuoiToiDa(int tuoiToiDa) {
        this.tuoiToiDa = tuoiToiDa;
    }
}
