package backend.qlgiaibongda.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "quydinhtinhdiem")
public class QuyDinhTinhDiemEntity extends BaseEntity{

    @Column(name = "thang")
    private int Thang;
    @Column(name = "thua")
    private int Thua;
    @Column(name = "hoa")
    private int Hoa;

    public QuyDinhTinhDiemEntity() {
    }

    public int getThang() {
        return Thang;
    }

    public void setThang(int thang) {
        Thang = thang;
    }

    public int getThua() {
        return Thua;
    }

    public void setThua(int thua) {
        Thua = thua;
    }

    public int getHoa() {
        return Hoa;
    }

    public void setHoa(int hoa) {
        Hoa = hoa;
    }
}
