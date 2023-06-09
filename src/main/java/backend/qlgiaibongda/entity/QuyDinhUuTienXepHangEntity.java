package backend.qlgiaibongda.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "quydinhuutienxephang")
public class QuyDinhUuTienXepHangEntity extends BaseEntity{
    @Column(name = "sotranthang")
    private int soTranThang;
    @Column(name = "sotranthua")
    private int soTranThua;
    @Column(name = "sotranhoa")
    private int soTranHoa;
    @OneToMany(mappedBy = "QuyDinhUuTienXepHang")
    private List<QuyDinhMuaGiaiEntity> listQuyDinhMuaGiai = new ArrayList<>();

    public QuyDinhUuTienXepHangEntity() {
    }

    public int getSoTranThang() {
        return soTranThang;
    }

    public void setSoTranThang(int soTranThang) {
        this.soTranThang = soTranThang;
    }

    public int getSoTranThua() {
        return soTranThua;
    }

    public void setSoTranThua(int soTranThua) {
        this.soTranThua = soTranThua;
    }

    public int getSoTranHoa() {
        return soTranHoa;
    }

    public void setSoTranHoa(int soTranHoa) {
        this.soTranHoa = soTranHoa;
    }

    public List<QuyDinhMuaGiaiEntity> getListQuyDinhMuaGiai() {
        return listQuyDinhMuaGiai;
    }

    public void setListQuyDinhMuaGiai(List<QuyDinhMuaGiaiEntity> listQuyDinhMuaGiai) {
        this.listQuyDinhMuaGiai = listQuyDinhMuaGiai;
    }
}
