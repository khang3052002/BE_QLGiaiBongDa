package backend.qlgiaibongda.dto;

import java.sql.Date;

public class PlayerFreeDTO {
    private Long id_cauthu;
    private Date thoiDiemKetThuc;


    public Boolean checkValidInfo(){
        Boolean check = true;
        if(id_cauthu == null || thoiDiemKetThuc == null)
        {
            check = false;
        }
        return check;
    }
    public Long getId_cauthu() {
        return id_cauthu;
    }

    public void setId_cauthu(Long id_cauthu) {
        this.id_cauthu = id_cauthu;
    }

    public Date getThoiDiemKetThuc() {
        return thoiDiemKetThuc;
    }

    public void setThoiDiemKetThuc(Date thoiDiemKetThuc) {
        this.thoiDiemKetThuc = thoiDiemKetThuc;
    }
}
