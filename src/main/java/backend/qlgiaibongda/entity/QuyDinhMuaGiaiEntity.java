package backend.qlgiaibongda.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "quydinhmuagiai")
public class QuyDinhMuaGiaiEntity extends BaseEntity {
    @OneToOne
    @JoinColumn(name = "id_quydinhtinhdiem")
    private QuyDinhTinhDiemEntity QuyDinhTinhDiem;

    @OneToOne
    @JoinColumn(name = "id_quydinhuutienxephang")
    private QuyDinhUuTienXepHangEntity QuyDinhUuTienXepHang;

    @OneToOne
    @JoinColumn(name = "id_quydinhcauthu")
    private QuyDinhCauThuEntity QuyDinhCauThu;


    @OneToOne
    @JoinColumn(name = "id_quydinhbanthang")
    private QuyDinhBanThangEntity QuyDinhBanThang;

    @OneToMany(mappedBy = "QuyDinhMuaGiai")
    private List<MuaGiaiEntity> CacMuaGiai = new ArrayList<>();


    public List<MuaGiaiEntity> getCacMuaGiai() {
        return CacMuaGiai;
    }

    public void setCacMuaGiai(List<MuaGiaiEntity> cacMuaGiai) {
        CacMuaGiai = cacMuaGiai;
    }

    public QuyDinhMuaGiaiEntity() {
    }

    public QuyDinhTinhDiemEntity getQuyDinhTinhDiem() {
        return QuyDinhTinhDiem;
    }

    public void setQuyDinhTinhDiem(QuyDinhTinhDiemEntity quyDinhTinhDiem) {
        QuyDinhTinhDiem = quyDinhTinhDiem;
    }

    public QuyDinhUuTienXepHangEntity getQuyDinhUuTienXepHang() {
        return QuyDinhUuTienXepHang;
    }

    public void setQuyDinhUuTienXepHang(QuyDinhUuTienXepHangEntity quyDinhUuTienXepHang) {
        QuyDinhUuTienXepHang = quyDinhUuTienXepHang;
    }

    public QuyDinhCauThuEntity getQuyDinhCauThu() {
        return QuyDinhCauThu;
    }

    public void setQuyDinhCauThu(QuyDinhCauThuEntity quyDinhCauThu) {
        QuyDinhCauThu = quyDinhCauThu;
    }

    public QuyDinhBanThangEntity getQuyDinhBanThang() {
        return QuyDinhBanThang;
    }

    public void setQuyDinhBanThang(QuyDinhBanThangEntity quyDinhBanThang) {
        QuyDinhBanThang = quyDinhBanThang;
    }
}
