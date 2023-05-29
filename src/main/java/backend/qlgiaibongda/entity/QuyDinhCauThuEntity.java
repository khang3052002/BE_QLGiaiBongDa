package backend.qlgiaibongda.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "quydinhcauthu")
public class QuyDinhCauThuEntity extends  BaseEntity{

    @Column(name = "soluongcauthutoida")
    private int SoLuongCauThuToiDa;

    @Column(name = "soluongcauthutoithieu")
    private int SoLuongCauThuToiThieu;

    @Column(name = "soluongcauthunuocngoaitoida")
    private int SoLuongCauThuNuocNgoaiToiDa;

    @Column(name = "tuoitoithieu")
    private int TuoiToiThieu;

    @Column(name = "tuoitoida")
    private int TuoiToiDa;

    public QuyDinhCauThuEntity() {
    }

    public int getSoLuongCauThuToiDa() {
        return SoLuongCauThuToiDa;
    }

    public void setSoLuongCauThuToiDa(int soLuongCauThuToiDa) {
        SoLuongCauThuToiDa = soLuongCauThuToiDa;
    }

    public int getSoLuongCauThuToiThieu() {
        return SoLuongCauThuToiThieu;
    }

    public void setSoLuongCauThuToiThieu(int soLuongCauThuToiThieu) {
        SoLuongCauThuToiThieu = soLuongCauThuToiThieu;
    }

    public int getSoLuongCauThuNuocNgoaiToiDa() {
        return SoLuongCauThuNuocNgoaiToiDa;
    }

    public void setSoLuongCauThuNuocNgoaiToiDa(int soLuongCauThuNuocNgoaiToiDa) {
        SoLuongCauThuNuocNgoaiToiDa = soLuongCauThuNuocNgoaiToiDa;
    }

    public int getTuoiToiThieu() {
        return TuoiToiThieu;
    }

    public void setTuoiToiThieu(int tuoiToiThieu) {
        TuoiToiThieu = tuoiToiThieu;
    }

    public int getTuoiToiDa() {
        return TuoiToiDa;
    }

    public void setTuoiToiDa(int tuoiToiDa) {
        TuoiToiDa = tuoiToiDa;
    }
}
