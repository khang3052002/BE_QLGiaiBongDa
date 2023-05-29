package backend.qlgiaibongda.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "quydinhuutienxephang")
public class QuyDinhUuTienXepHangEntity extends BaseEntity{
    @Column(name = "sotranthang")
    private int SoTranThang;
    @Column(name = "sotranthua")
    private int SoTranThua;
    @Column(name = "sotranhoa")
    private int SoTranHoa;

    public QuyDinhUuTienXepHangEntity() {
    }

    public int getSoTranThang() {
        return SoTranThang;
    }

    public void setSoTranThang(int soTranThang) {
        SoTranThang = soTranThang;
    }

    public int getSoTranThua() {
        return SoTranThua;
    }

    public void setSoTranThua(int soTranThua) {
        SoTranThua = soTranThua;
    }

    public int getSoTranHoa() {
        return SoTranHoa;
    }

    public void setSoTranHoa(int soTranHoa) {
        SoTranHoa = soTranHoa;
    }
}
