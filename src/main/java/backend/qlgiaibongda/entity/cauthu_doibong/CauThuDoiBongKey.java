package backend.qlgiaibongda.entity.cauthu_doibong;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.sql.Date;

@Embeddable
public class CauThuDoiBongKey implements Serializable {
    @Column(name = "id_cauthu")
    private Long IdCauThu;

    @Column(name = "id_doibong")
    private Long IdDoiBong;

//    @Column(name = "thoidiembatdau")
//    private Date ThoiDiemBatDau;


//    public Date getThoiDiemBatDau() {
//        return ThoiDiemBatDau;
//    }
//
//    public void setThoiDiemBatDau(Date thoiDiemBatDau) {
//        ThoiDiemBatDau = thoiDiemBatDau;
//    }

    public Long getIdCauThu() {
        return IdCauThu;
    }

    public void setIdCauThu(Long idCauThu) {
        IdCauThu = idCauThu;
    }

    public Long getIdDoiBong() {
        return IdDoiBong;
    }

    public void setIdDoiBong(Long idDoiBong) {
        IdDoiBong = idDoiBong;
    }


    public CauThuDoiBongKey() {
    }

    public CauThuDoiBongKey(Long idCauThu, Long idDoiBong, Date thoiDiemBatDau) {
        IdCauThu = idCauThu;
        IdDoiBong = idDoiBong;
//        ThoiDiemBatDau = thoiDiemBatDau;
    }
}

