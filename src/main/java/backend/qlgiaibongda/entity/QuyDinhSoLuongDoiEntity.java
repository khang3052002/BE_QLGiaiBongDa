package backend.qlgiaibongda.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="quydinhsoluongdoi")
public class QuyDinhSoLuongDoiEntity extends BaseEntity{
    @Column(name = "soluongdoi")
    private int soLuongDoi;

    @OneToMany(mappedBy = "QuyDinhSoLuongDoi")
    private List<QuyDinhMuaGiaiEntity> listQuyDinhMuaGiai = new ArrayList<>();
    public int getSoLuongDoi() {
        return soLuongDoi;
    }

    public void setSoLuongDoi(int soLuongDoi) {
        this.soLuongDoi = soLuongDoi;
    }

    public List<QuyDinhMuaGiaiEntity> getListQuyDinhMuaGiai() {
        return listQuyDinhMuaGiai;
    }

    public void setListQuyDinhMuaGiai(List<QuyDinhMuaGiaiEntity> listQuyDinhMuaGiai) {
        this.listQuyDinhMuaGiai = listQuyDinhMuaGiai;
    }
}
