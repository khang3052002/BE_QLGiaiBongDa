package backend.qlgiaibongda.entity.cauthu_doibong;

import backend.qlgiaibongda.entity.BaseEntity;
import backend.qlgiaibongda.entity.CauThuEntity;
import backend.qlgiaibongda.entity.DoiBongEntity;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Date;

@Entity
@Table(name = "cauthu_doibong")
@EntityListeners(AuditingEntityListener.class)
public class CauThuDoiBongEntity  {
    @EmbeddedId
    CauThuDoiBongKey key;

    @ManyToOne
    @MapsId("IdCauThu")
    @JoinColumn(name = "id_cauthu")
    private CauThuEntity CauThuDB;

    @ManyToOne
    @MapsId("IdDoiBong")
    @JoinColumn(name = "id_doibong")
    private DoiBongEntity DoiBongCT;



    @Id
    @CreatedDate
    private Date ThoiDiemBatDau;

    @Column(name = "thoidiemketthuc")
    private Date ThoiDiemKetThuc;

    @Column(name = "tongsobanthang")
    private int TongSoBanThang;


    public CauThuDoiBongKey getKey() {
        return key;
    }

    public void setKey(CauThuDoiBongKey key) {
        this.key = key;
    }

    public CauThuEntity getCauThuDB() {
        return CauThuDB;
    }

    public void setCauThuDB(CauThuEntity cauThuDB) {
        CauThuDB = cauThuDB;
    }

    public DoiBongEntity getDoiBongCT() {
        return DoiBongCT;
    }

    public void setDoiBongCT(DoiBongEntity doiBongCT) {
        DoiBongCT = doiBongCT;
    }

    public Date getThoiDiemBatDau() {
        return ThoiDiemBatDau;
    }

    public void setThoiDiemBatDau(Date thoiDiemBatDau) {
        ThoiDiemBatDau = thoiDiemBatDau;
    }

    public Date getThoiDiemKetThuc() {
        return ThoiDiemKetThuc;
    }

    public void setThoiDiemKetThuc(Date thoiDiemKetThuc) {
        ThoiDiemKetThuc = thoiDiemKetThuc;
    }

    public int getTongSoBanThang() {
        return TongSoBanThang;
    }

    public void setTongSoBanThang(int tongSoBanThang) {
        TongSoBanThang = tongSoBanThang;
    }

    public CauThuDoiBongEntity() {
    }

    public CauThuDoiBongEntity(CauThuDoiBongKey key, CauThuEntity cauThuDB, DoiBongEntity doiBongCT, Date thoiDiemBatDau, Date thoiDiemKetThuc, int tongSoBanThang) {
        this.key = key;
        CauThuDB = cauThuDB;
        DoiBongCT = doiBongCT;
        ThoiDiemBatDau = thoiDiemBatDau;
        ThoiDiemKetThuc = thoiDiemKetThuc;
        TongSoBanThang = tongSoBanThang;
    }
}
