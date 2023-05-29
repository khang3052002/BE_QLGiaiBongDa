package backend.qlgiaibongda.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "cauthughibanmuagiai")
public class CauThuGhiBanEntity extends BaseEntity{
    @OneToOne
    @JoinColumn(name = "id_cauthu")
    private CauThuEntity CauThu;

    @OneToOne
    @JoinColumn(name = "id_doi")
    private DoiBongEntity DoiBong;

    @Column(name = "sobanthang")
    private int SoBanThang;

    @ManyToOne
    @JoinColumn(name = "id_muagiai")
    private MuaGiaiEntity MuaGiai;

    public MuaGiaiEntity getMuaGiai() {
        return MuaGiai;
    }

    public void setMuaGiai(MuaGiaiEntity muaGiai) {
        MuaGiai = muaGiai;
    }

    public CauThuGhiBanEntity() {
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

    public int getSoBanThang() {
        return SoBanThang;
    }

    public void setSoBanThang(int soBanThang) {
        SoBanThang = soBanThang;
    }
}
