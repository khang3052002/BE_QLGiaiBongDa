//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package backend.qlgiaibongda.entity;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "lichthidau")
@EntityListeners({AuditingEntityListener.class})
public class LichThiDauEntity extends BaseEntity {
    @Column(name = "thoigiantao")
    @CreatedDate
    private Date thoiGianTao;
    @OneToMany(mappedBy = "lichThiDau")
    private List<TranDauEntity> listTranDauCuaLichThiDau = new ArrayList();
    @ManyToOne
    @JoinColumn(name = "id_nguoitao")
    private QuanLyEntity QuanLyTaoLich;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_muagiai",referencedColumnName = "id")
    private MuaGiaiEntity MuaGiai;

    public Date getThoiGianTao() {
        return thoiGianTao;
    }

    public void setThoiGianTao(Date thoiGianTao) {
        this.thoiGianTao = thoiGianTao;
    }

    public MuaGiaiEntity getMuaGiai() {
        return MuaGiai;
    }

    public void setMuaGiai(MuaGiaiEntity muaGiai) {
        MuaGiai = muaGiai;
    }

    public List<TranDauEntity> getListTranDauCuaLichThiDau() {
        return listTranDauCuaLichThiDau;
    }

    public void setListTranDauCuaLichThiDau(List<TranDauEntity> listTranDauCuaLichThiDau) {
        this.listTranDauCuaLichThiDau = listTranDauCuaLichThiDau;
    }

    public QuanLyEntity getQuanLyTaoLich() {
        return QuanLyTaoLich;
    }

    public void setQuanLyTaoLich(QuanLyEntity quanLyTaoLich) {
        QuanLyTaoLich = quanLyTaoLich;
    }
}
