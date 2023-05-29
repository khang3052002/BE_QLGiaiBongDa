package backend.qlgiaibongda.entity;

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
    private List<CauThuDoiBongEntity> CacCauThu;


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
