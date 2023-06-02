package backend.qlgiaibongda.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="quydinhsoluongdoi")
public class QuyDinhSoLuongDoiEntity extends BaseEntity{
    @Column(name = "soluongdoi")
    private int soLuongDoi;

    @OneToOne(mappedBy = "QuyDinhSoLuongDoi")
    private QuyDinhMuaGiaiEntity quyDinhMuaGiai;
    public int getSoLuongDoi() {
        return soLuongDoi;
    }

    public void setSoLuongDoi(int soLuongDoi) {
        this.soLuongDoi = soLuongDoi;
    }

    public QuyDinhMuaGiaiEntity getQuyDinhMuaGiai() {
        return quyDinhMuaGiai;
    }

    public void setQuyDinhMuaGiai(QuyDinhMuaGiaiEntity quyDinhMuaGiai) {
        this.quyDinhMuaGiai = quyDinhMuaGiai;
    }
}
