package backend.qlgiaibongda.entity.CauThuGhiBan;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class CauThuGhiBanKey  implements Serializable {

    @Column(name="id_cauthu")
    private  Long idCauthu;
    @Column(name="id_doibong")
    private Long idDoibong;
//    @Column(name="id_muagiai")
//    private Long id_muagiai;

    public Long getIdCauthu() {
        return idCauthu;
    }

    public void setIdCauthu(Long idCauthu) {
        this.idCauthu = idCauthu;
    }

    public Long getIdDoibong() {
        return idDoibong;
    }

    public void setIdDoibong(Long idDoibong) {
        this.idDoibong = idDoibong;
    }


//    public Long getId_muagiai() {
//        return id_muagiai;
//    }
//
//    public void setId_muagiai(Long id_muagiai) {
//        this.id_muagiai = id_muagiai;
//    }
}
