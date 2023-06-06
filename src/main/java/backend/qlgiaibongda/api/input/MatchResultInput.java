package backend.qlgiaibongda.api.input;

import java.sql.Date;

public class MatchResultInput {
    private Long idTranDau;
    private Long thoiDiemGhiBan;
    private Long idCauThuGhiBan;
    private Long idDoiGhiBan;
    private Long idLoaiBanThang;


    public boolean checkValidInfo(){
        boolean check = true;
        if(idTranDau == null || thoiDiemGhiBan == null || idCauThuGhiBan == null || idDoiGhiBan == null || idLoaiBanThang == null){
            check = false;
        }

        return check;
    }


    public Long getIdTranDau() {
        return idTranDau;
    }

    public void setIdTranDau(Long idTranDau) {
        this.idTranDau = idTranDau;
    }

    public Long getThoiDiemGhiBan() {
        return thoiDiemGhiBan;
    }

    public void setThoiDiemGhiBan(Long thoiDiemGhiBan) {
        this.thoiDiemGhiBan = thoiDiemGhiBan;
    }

    public Long getIdCauThuGhiBan() {
        return idCauThuGhiBan;
    }

    public void setIdCauThuGhiBan(Long idCauThuGhiBan) {
        this.idCauThuGhiBan = idCauThuGhiBan;
    }

    public Long getIdDoiGhiBan() {
        return idDoiGhiBan;
    }

    public void setIdDoiGhiBan(Long idDoiGhiBan) {
        this.idDoiGhiBan = idDoiGhiBan;
    }

    public Long getIdLoaiBanThang() {
        return idLoaiBanThang;
    }

    public void setIdLoaiBanThang(Long idLoaiBanThang) {
        this.idLoaiBanThang = idLoaiBanThang;
    }
}
