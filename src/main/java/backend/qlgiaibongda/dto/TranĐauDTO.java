package backend.qlgiaibongda.dto;

import backend.qlgiaibongda.entity.DoiBongEntity;
import backend.qlgiaibongda.entity.LichThiDauEntity;
import backend.qlgiaibongda.entity.VongEntity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class TranĐauDTO {
    private Long id;
    private Timestamp ThoiGian;

    private String thoiGianVietNam;
    private TeamDTO DoiNha;
    private TeamDTO DoiKhach;
    private Long idVong;
    private String tenVong;
    private Long idLichThiDau;
    private KetQuaTranDauDTO ketQuaTranDau;

    public String getThoiGianVietNam() {
        return thoiGianVietNam;
    }

    public void setThoiGianVietNam(String thoiGianVietNam) {
        this.thoiGianVietNam = thoiGianVietNam;
    }

    public String getTenVong() {
        return tenVong;
    }

    public void setTenVong(String tenVong) {
        this.tenVong = tenVong;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getThoiGian() {
        return ThoiGian;
    }

    public void setThoiGian(Timestamp thoiGian) {
        ThoiGian = thoiGian;

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        thoiGianVietNam = dateFormat.format(thoiGian);
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
