package backend.qlgiaibongda.entity.KetQuaTranDau;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.sql.Date;

@Embeddable
public class KetQuaTranDauKey implements Serializable {
    @Column(name = "id_trandau")
    private Long id_TranDau;
    @Column(name="thoidiemghiban")
    private Date ThoiDiemGhiBan;

    public Long getId_TranDau() {
        return id_TranDau;
    }

    public void setId_TranDau(Long id_TranDau) {
        this.id_TranDau = id_TranDau;
    }

    public Date getThoiDiemGhiBan() {
        return ThoiDiemGhiBan;
    }

    public void setThoiDiemGhiBan(Date thoiDiemGhiBan) {
        ThoiDiemGhiBan = thoiDiemGhiBan;
    }
}
