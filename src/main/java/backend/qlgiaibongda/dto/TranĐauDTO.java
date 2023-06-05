package backend.qlgiaibongda.dto;

import backend.qlgiaibongda.entity.DoiBongEntity;
import backend.qlgiaibongda.entity.LichThiDauEntity;
import backend.qlgiaibongda.entity.VongEntity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.sql.Date;

public class TranĐauDTO {
    private Long id;
    private Date ThoiGian;
    private TeamDTO DoiNha;
    private TeamDTO DoiKhach;
    private Long idVong;
    private Long idLichThiDau;
    private KetQuaTranDauDTO ketQuaTranDau;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getThoiGian() {
        return ThoiGian;
    }

    public void setThoiGian(Date thoiGian) {
        ThoiGian = thoiGian;
    }

    public TeamDTO getDoiNha() {
        return DoiNha;
    }

    public void setDoiNha(TeamDTO doiNha) {
        DoiNha = doiNha;
    }

    public TeamDTO getDoiKhach() {
        return DoiKhach;
    }

    public void setDoiKhach(TeamDTO doiKhach) {
        DoiKhach = doiKhach;
    }

    public Long getIdVong() {
        return idVong;
    }

    public void setIdVong(Long idVong) {
        this.idVong = idVong;
    }

    public Long getIdLichThiDau() {
        return idLichThiDau;
    }

    public void setIdLichThiDau(Long idLichThiDau) {
        this.idLichThiDau = idLichThiDau;
    }

    public KetQuaTranDauDTO getKetQuaTranDau() {
        return ketQuaTranDau;
    }

    public void setKetQuaTranDau(KetQuaTranDauDTO ketQuaTranDau) {
        this.ketQuaTranDau = ketQuaTranDau;
    }
}
