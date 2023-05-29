package backend.qlgiaibongda.entity.cauthu_doibong;

import backend.qlgiaibongda.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;


@Embeddable
public class CauThuDoiBongKey implements Serializable {

    @Column(name = "id_doibong")
    private Long IdDoiBong;

    @Column(name = "id_cauthu")
    private Long IdCauThu;


    @Column(name = "thoidiembatdau")
    private Date ThoiDiemBatDau = new Date(System.currentTimeMillis());


    public Date getThoiDiemBatDau() {
        return ThoiDiemBatDau;
    }

    public void setThoiDiemBatDau(Date thoiDiemBatDau) {
        ThoiDiemBatDau = thoiDiemBatDau;
    }

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


    public CauThuDoiBongKey(Long idDoiBong, Long idCauThu) {
        IdDoiBong = idDoiBong;
        IdCauThu = idCauThu;
    }

    public CauThuDoiBongKey() {
    }


}

