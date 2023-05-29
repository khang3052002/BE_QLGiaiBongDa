package backend.qlgiaibongda.entity;

import backend.qlgiaibongda.entity.bxh_doibong.BXHDoiBongEntity;
import backend.qlgiaibongda.entity.cauthu_doibong.CauThuDoiBongEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "doibong")
public class DoiBongEntity extends BaseEntity{

    @Column(name = "ten")
    private String Ten;
    @Column(name = "hinhanh")
    private String HinhAnh;

    @Column(name = "namthanhlap")
    private int NamThanhLap;


    //CauThu_DoiBong
    @OneToMany(mappedBy = "DoiBongCT")
    private List<CauThuDoiBongEntity> DoiBongCauThu;

    //BangXepHang
    @OneToMany(mappedBy = "DoiBong")
    private List<BXHDoiBongEntity> DoiBongBXH;

    @OneToMany(mappedBy = "DoiBong")
    private List<HoSoDangKyEntity> CacHoSoDangKy = new ArrayList<>();




    ////////////////////////////////GETTER SETTER///////////////////////////////////////////

    public List<HoSoDangKyEntity> getCacHoSoDangKy() {
        return CacHoSoDangKy;
    }

    public void setCacHoSoDangKy(List<HoSoDangKyEntity> cacHoSoDangKy) {
        CacHoSoDangKy = cacHoSoDangKy;
    }

    public List<BXHDoiBongEntity> getDoiBongBXH() {
        return DoiBongBXH;
    }

    public void setDoiBongBXH(List<BXHDoiBongEntity> doiBongBXH) {
        DoiBongBXH = doiBongBXH;
    }

    public List<CauThuDoiBongEntity> getDoiBongCauThu() {
        return DoiBongCauThu;
    }

    public void setDoiBongCauThu(List<CauThuDoiBongEntity> doiBongCauThu) {
        DoiBongCauThu = doiBongCauThu;
    }

    public String getTen() {
        return Ten;
    }

    public void setTen(String ten) {
        Ten = ten;
    }

    public String getHinhAnh() {
        return HinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        HinhAnh = hinhAnh;
    }

    public int getNamThanhLap() {
        return NamThanhLap;
    }

    public void setNamThanhLap(int namThanhLap) {
        NamThanhLap = namThanhLap;
    }
}
