package backend.qlgiaibongda.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "quydinhuutienxephang")
public class QuyDinhUuTienXepHangEntity extends BaseEntity{
    @Column(name = "sotranthang")
    private int soTranThang;
    @Column(name = "sotranthua")
    private int soTranThua;
    @Column(name = "sotranhoa")
    private int soTranHoa;
    @OneToOne(mappedBy = "QuyDinhUuTienXepHang")
    private QuyDinhMuaGiaiEntity quyDinhMuaGiai;

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
}
