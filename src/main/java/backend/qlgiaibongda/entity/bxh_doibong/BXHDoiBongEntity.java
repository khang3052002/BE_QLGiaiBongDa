package backend.qlgiaibongda.entity.bxh_doibong;

import backend.qlgiaibongda.entity.BangXepHangEntity;
import backend.qlgiaibongda.entity.CauThuEntity;
import backend.qlgiaibongda.entity.DoiBongEntity;
import jakarta.persistence.*;
import org.hibernate.annotations.Formula;
import org.springframework.data.annotation.Transient;

@Entity
@Table(name = "bxh_doibong")
public class BXHDoiBongEntity  {

    @EmbeddedId
    private BXHDoiBongKey key;

    @ManyToOne
    @MapsId("idDoiBong")
    @JoinColumn(name = "id_doibong")
    private DoiBongEntity doiBong;

    @ManyToOne
    @MapsId("idBXH")
    @JoinColumn(name = "id_bxh")
    private BangXepHangEntity bxh;

    @Column(name = "tranthang")
    private Integer tranThang;
    @Column(name = "tranthua")
    private Integer tranThua;
    @Column(name = "tranhoa")
    private Integer tranHoa;

    @Column(name="vong")
    private Integer vong;

    public Integer getVong() {
        return vong;
    }

    @Column(name="phaluoi")
    private Integer phaLuoi;
    @Column(name="thungluoi")
    private Integer thungLuoi;


    @Column(name = "hieuso")
    private Integer hieuSo;



    @Column(name="diem")
    private Integer diem;

    public Integer getDiem() {
        return diem;
    }

    public void setDiem(Integer diem) {
        this.diem = diem;
    }

    public void setVong(Integer vong) {
        this.vong = vong;
    }


    public BXHDoiBongKey getKey() {
        return key;
    }

    public void setKey(BXHDoiBongKey key) {
        this.key = key;
    }

    public DoiBongEntity getDoiBong() {
        return doiBong;
    }

    public void setDoiBong(DoiBongEntity doiBong) {
        this.doiBong = doiBong;
    }

    public BangXepHangEntity getBXH() {
        return bxh;
    }

    public void setBXH(BangXepHangEntity BXH) {
        this.bxh = BXH;
    }

    public Integer getTranThang() {
        return tranThang;
    }

    public void setTranThang(Integer tranThang) {
        this.tranThang = tranThang;
    }

    public Integer getTranThua() {
        return tranThua;
    }

    public void setTranThua(Integer tranThua) {
        this.tranThua = tranThua;
    }

    public Integer getTranHoa() {
        return tranHoa;
    }

    public void setTranHoa(Integer tranHoa) {
        this.tranHoa = tranHoa;
    }

    public Integer getHieuSo() {
        return hieuSo;
    }

    public void setHieuSo(Integer hieuSo) {
        this.hieuSo = hieuSo;
    }



    public Integer getPhaLuoi() {
        return phaLuoi;
    }

    public void setPhaLuoi(Integer phaLuoi) {
        this.phaLuoi = phaLuoi;
    }

    public Integer getThungLuoi() {
        return thungLuoi;
    }

    public void setThungLuoi(Integer thungLuoi) {
        this.thungLuoi = thungLuoi;
    }

}
