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
    private int TranThang;
    @Column(name = "tranthua")
    private int TranThua;
    @Column(name = "tranhoa")
    private int TranHoa;
    @Column(name = "hieuso")
    private int HieuSo;
    @Column(name = "hang")
    private int Hang;

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
        return TranThang;
    }

    public void setTranThang(int tranThang) {
        TranThang = tranThang;
    }

    public int getTranThua() {
        return TranThua;
    }

    public void setTranThua(int tranThua) {
        TranThua = tranThua;
    }

    public int getTranHoa() {
        return TranHoa;
    }

    public void setTranHoa(int tranHoa) {
        TranHoa = tranHoa;
    }

    public int getHieuSo() {
        return HieuSo;
    }

    public void setHieuSo(int hieuSo) {
        HieuSo = hieuSo;
    }

    public int getHang() {
        return Hang;
    }

    public void setHang(int hang) {
        Hang = hang;
    }
}
