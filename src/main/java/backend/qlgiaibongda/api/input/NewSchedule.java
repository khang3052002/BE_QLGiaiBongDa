package backend.qlgiaibongda.api.input;

import java.sql.Date;

public class NewSchedule {

    private Long idQuanLy;
    private Long idMuaGiai;
    private Date thoiGianTao;


    public boolean checkValidInfo(){
        boolean check = true;
        if(idQuanLy == null || idMuaGiai == null){
            check = false;
        }
        return check;
    }

    public Long getIdQuanLy() {
        return idQuanLy;
    }

    public void setIdQuanLy(Long idQuanLy) {
        this.idQuanLy = idQuanLy;
    }

    public Long getIdMuaGiai() {
        return idMuaGiai;
    }

    public void setIdMuaGiai(Long idMuaGiai) {
        this.idMuaGiai = idMuaGiai;
    }

    public Date getThoiGianTao() {
        return thoiGianTao;
    }

    public void setThoiGianTao(Date thoiGianTao) {
        this.thoiGianTao = thoiGianTao;
    }
}
