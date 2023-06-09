package backend.qlgiaibongda.entity.cauthu_doibong;

import backend.qlgiaibongda.entity.BaseEntity;
import backend.qlgiaibongda.entity.CauThuEntity;
import backend.qlgiaibongda.entity.DoiBongEntity;
import backend.qlgiaibongda.entity.HoSoDangKyEntity;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cauthu_doibong")
public class CauThuDoiBongEntity  {
    @EmbeddedId
    CauThuDoiBongKey key;

    @ManyToOne
    @MapsId("IdCauThu")
    @JoinColumn(name = "id_cauthu")
    private CauThuEntity cauThuDB;

    @ManyToOne
    @MapsId("IdDoiBong")
    @JoinColumn(name = "id_doibong")
    private DoiBongEntity doiBongCT;


    @Column(name = "thoidiemketthuc")
    private Date thoiDiemKetThuc;

    @Column(name = "tongsobanthang")
    private int tongSoBanThang;


    public CauThuDoiBongKey getKey() {
        return key;
    }

    public void setKey(CauThuDoiBongKey key) {
        this.key = key;
    }

    public CauThuEntity getCauThuDB() {
        return cauThuDB;
    }

    public void setCauThuDB(CauThuEntity cauThuDB) {
        this.cauThuDB = cauThuDB;
    }

    public DoiBongEntity getDoiBongCT() {
        return doiBongCT;
    }

    public void setDoiBongCT(DoiBongEntity doiBongCT) {
        this.doiBongCT = doiBongCT;
    }


    public Date getThoiDiemKetThuc() {
        return thoiDiemKetThuc;
    }

    public void setThoiDiemKetThuc(Date thoiDiemKetThuc) {
        this.thoiDiemKetThuc = thoiDiemKetThuc;
    }

    public int getTongSoBanThang() {
        return tongSoBanThang;
    }

    public void setTongSoBanThang(int tongSoBanThang) {
        this.tongSoBanThang = tongSoBanThang;
    }

    public CauThuDoiBongEntity() {
    }

    public CauThuDoiBongEntity(CauThuDoiBongKey key, CauThuEntity cauThuDB, DoiBongEntity doiBongCT, Date thoiDiemBatDau, Date thoiDiemKetThuc, int tongSoBanThang) {
        this.key = key;
        this.cauThuDB = cauThuDB;
        this.doiBongCT = doiBongCT;
        this.thoiDiemKetThuc = thoiDiemKetThuc;
        this.tongSoBanThang = tongSoBanThang;
    }


}
