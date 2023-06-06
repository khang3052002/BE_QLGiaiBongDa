package backend.qlgiaibongda.entity;


import backend.qlgiaibongda.entity.*;
import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name="ghinhanbanthang")
public class GhiNhanBanThangEntity extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "id_ketquatrandau")
    private KetQuaTranDauEntity ketQuaTranDau;

    @ManyToOne
    @JoinColumn(name="id_cauthu")
    private CauThuEntity CauThu;

    @ManyToOne
    @JoinColumn(name="id_doibong")
    private DoiBongEntity DoiBong;

    @ManyToOne
    @JoinColumn(name="id_loaibanthang")
    private LoaiBanThangEntity LoaiBanThang;

    @Column(name = "thodiemghiban")
    private Long thoiDiemGhiBan;

    public Long getThoiDiemGhiBan() {
        return thoiDiemGhiBan;
    }

    public void setThoiDiemGhiBan(Long thoiDiemGhiBan) {
        this.thoiDiemGhiBan = thoiDiemGhiBan;
    }

    public KetQuaTranDauEntity getKetQuaTranDau() {
        return ketQuaTranDau;
    }

    public void setKetQuaTranDau(KetQuaTranDauEntity ketQuaTranDau) {
        this.ketQuaTranDau = ketQuaTranDau;
    }

    public CauThuEntity getCauThu() {
        return CauThu;
    }

    public void setCauThu(CauThuEntity cauThu) {
        CauThu = cauThu;
    }

    public DoiBongEntity getDoiBong() {
        return DoiBong;
    }

    public void setDoiBong(DoiBongEntity doiBong) {
        DoiBong = doiBong;
    }

    public LoaiBanThangEntity getLoaiBanThang() {
        return LoaiBanThang;
    }

    public void setLoaiBanThang(LoaiBanThangEntity loaiBanThang) {
        LoaiBanThang = loaiBanThang;
    }
}
