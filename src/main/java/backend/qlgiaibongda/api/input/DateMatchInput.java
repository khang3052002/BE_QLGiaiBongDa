package backend.qlgiaibongda.api.input;

import java.sql.Date;

public class DateMatchInput {
    private Long idTranDau;
    private Date time;

    public boolean checkValidInfo(){
        boolean check = true;
        if(idTranDau == null || time == null){
            check = false;
        }
        return  check;
    }

    public Long getIdTranDau() {
        return idTranDau;
    }

    public void setIdTranDau(Long idTranDau) {
        this.idTranDau = idTranDau;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
