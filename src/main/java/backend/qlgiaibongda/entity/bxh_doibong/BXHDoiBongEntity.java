package backend.qlgiaibongda.entity.bxh_doibong;

import backend.qlgiaibongda.entity.BangXepHangEntity;
import backend.qlgiaibongda.entity.CauThuEntity;
import backend.qlgiaibongda.entity.DoiBongEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "bxh_doibong")
public class BXHDoiBongEntity  {

    @EmbeddedId
    private BXHDoiBongKey key;

    @ManyToOne
    @MapsId("idDoiBong")
    @JoinColumn(name = "id_doibong")
    private DoiBongEntity DoiBong;

    @ManyToOne
    @MapsId("idBXH")
    @JoinColumn(name = "id_bxh")
    private BangXepHangEntity BXH;

    @Column(name = "tranthang")
    private int tranThang;
    @Column(name = "tranthua")
    private int tranThua;
    @Column(name = "tranhoa")
    private int tranHoa;
    @Column(name = "hieuso")
    private int hieuSo;
    @Column(name = "hang")
    private int hang;

    public BXHDoiBongKey getKey() {
        return key;
    }

    public void setKey(BXHDoiBongKey key) {
        this.key = key;
    }

    public DoiBongEntity getDoiBong() {
        return DoiBong;
    }

    public void setDoiBong(DoiBongEntity doiBong) {
        DoiBong = doiBong;
    }

    public BangXepHangEntity getBXH() {
        return BXH;
    }

    public void setBXH(BangXepHangEntity BXH) {
        this.BXH = BXH;
    }

    public int getTranThang() {
        return tranThang;
    }

    public void setTranThang(int tranThang) {
        this.tranThang = tranThang;
    }

    public int getTranThua() {
        return tranThua;
    }

    public void setTranThua(int tranThua) {
        this.tranThua = tranThua;
    }

    public int getTranHoa() {
        return tranHoa;
    }

    public void setTranHoa(int tranHoa) {
        this.tranHoa = tranHoa;
    }

    public int getHieuSo() {
        return hieuSo;
    }

    public void setHieuSo(int hieuSo) {
        this.hieuSo = hieuSo;
    }

    public int getHang() {
        return hang;
    }

    public void setHang(int hang) {
        this.hang = hang;
    }
}
