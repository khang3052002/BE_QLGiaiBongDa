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
    private CauThuDoiBongKey key;


    @ManyToOne
    @MapsId("IdDoiBong")
    @JoinColumn(name = "id_doibong")
    private DoiBongEntity DoiBong;

    @ManyToOne
    @MapsId("IdCauThu")
    @JoinColumn(name = "id_cauthu")
    private CauThuEntity CauThu;


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

    public DoiBongEntity getDoiBong() {
        return DoiBong;
    }

    public void setDoiBong(DoiBongEntity doiBong) {
        DoiBong = doiBong;
    }

    public CauThuEntity getCauThu() {
        return CauThu;
    }

    public void setCauThu(CauThuEntity cauThu) {
        CauThu = cauThu;
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

    public CauThuDoiBongEntity(CauThuDoiBongKey key, DoiBongEntity doiBong, CauThuEntity cauThu, Date thoiDiemKetThuc, int tongSoBanThang) {
        this.key = key;
        DoiBong = doiBong;
        CauThu = cauThu;
        ThoiDiemKetThuc = thoiDiemKetThuc;
        TongSoBanThang = tongSoBanThang;
    }
}
