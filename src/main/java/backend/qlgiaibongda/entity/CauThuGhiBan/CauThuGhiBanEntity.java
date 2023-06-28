package backend.qlgiaibongda.entity.CauThuGhiBan;

import backend.qlgiaibongda.entity.*;
import jakarta.persistence.*;

@Entity
@Table(name = "cauthughibanmuagiai")
public class CauThuGhiBanEntity {
//    @OneToOne
//    @JoinColumn(name = "id_cauthu")
//        private CauThuEntity cauThu;
//
//    @OneToOne
//    @JoinColumn(name = "id_doi")
//    private DoiBongEntity doiBong;

    @EmbeddedId
    private CauThuGhiBanKey cauThuGhiBanKey;


//    @ManyToOne
    @ManyToOne
    @MapsId("idCauthu")
    @JoinColumn(name = "id_cauthu")
    private CauThuEntity cauThu;

    @ManyToOne
    @MapsId("idDoibong")
    @JoinColumn(name = "id_doibong")
    private DoiBongEntity doiBong;


    @Column(name = "sobanthang")
    private int soBanThang;

    @ManyToOne
    @JoinColumn(name = "id_muagiai")
    private MuaGiaiEntity muaGiai;


    public CauThuGhiBanKey getCauThuGhiBanKey() {
        return cauThuGhiBanKey;
    }

    public void setCauThuGhiBanKey(CauThuGhiBanKey cauThuGhiBanKey) {
        this.cauThuGhiBanKey = cauThuGhiBanKey;
    }

    public CauThuGhiBanEntity() {
    }

    public CauThuEntity getCauThu() {
        return cauThu;
    }

    public void setCauThu(CauThuEntity cauThu) {
        this.cauThu = cauThu;
    }

    public DoiBongEntity getDoiBong() {
        return doiBong;
    }

    public void setDoiBong(DoiBongEntity doiBong) {
        this.doiBong = doiBong;
    }

    public MuaGiaiEntity getMuaGiai() {
        return muaGiai;
    }

    public void setMuaGiai(MuaGiaiEntity muaGiai) {
        this.muaGiai = muaGiai;
    }

    public int getSoBanThang() {
        return soBanThang;
    }

    public void setSoBanThang(int soBanThang) {
        this.soBanThang = soBanThang;
    }
}
