package backend.qlgiaibongda.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "quydinhtinhdiem")
public class QuyDinhTinhDiemEntity extends BaseEntity{

    @Column(name = "thang")
    private int thang;
    @Column(name = "thua")
    private int thua;
    @Column(name = "hoa")
    private int hoa;
    @OneToMany(mappedBy = "QuyDinhTinhDiem")
    private List<QuyDinhMuaGiaiEntity> listQuyDinhMuaGiai = new ArrayList<>();

    public QuyDinhTinhDiemEntity() {
    }

    public int getThang() {
        return thang;
    }

    public void setThang(int thang) {
        this.thang = thang;
    }

    public int getThua() {
        return thua;
    }

    public void setThua(int thua) {
        this.thua = thua;
    }

    public int getHoa() {
        return hoa;
    }

    public void setHoa(int hoa) {
        this.hoa = hoa;
    }

    public List<QuyDinhMuaGiaiEntity> getListQuyDinhMuaGiai() {
        return listQuyDinhMuaGiai;
    }

    public void setListQuyDinhMuaGiai(List<QuyDinhMuaGiaiEntity> listQuyDinhMuaGiai) {
        this.listQuyDinhMuaGiai = listQuyDinhMuaGiai;
    }
}
