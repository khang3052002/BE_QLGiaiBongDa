//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package backend.qlgiaibongda.entity;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "trandau")
public class TranDauEntity extends BaseEntity {
    private Date ThoiGian;
    @ManyToOne
    @JoinColumn(name = "id_doinha")
    private DoiBongEntity DoiNha;
    @ManyToOne
    @JoinColumn(name = "id_doikhach")
    private DoiBongEntity DoiKhach;
    @ManyToOne
    @JoinColumn(name = "id_vongdau")
    private VongEntity Vong;
    @ManyToOne
    @JoinColumn(name = "id_lichthidau")
    private LichThiDauEntity lichThiDau;



    //Relation ship KQTranDau - Tran Dau
    @OneToOne(mappedBy = "tranDau")
    private KetQuaTranDauEntity KetQuaTranDau;

    public Date getThoiGian() {
        return ThoiGian;
    }

    public void setThoiGian(Date thoiGian) {
        ThoiGian = thoiGian;
    }

    public DoiBongEntity getDoiNha() {
        return DoiNha;
    }

    public void setDoiNha(DoiBongEntity doiNha) {
        DoiNha = doiNha;
    }

    public DoiBongEntity getDoiKhach() {
        return DoiKhach;
    }

    public void setDoiKhach(DoiBongEntity doiKhach) {
        DoiKhach = doiKhach;
    }

    public VongEntity getVong() {
        return Vong;
    }
    public void setVong(VongEntity vong) {
        Vong = vong;
    }
    public LichThiDauEntity getLichThiDau() {
        return lichThiDau;
    }
    public void setLichThiDau(LichThiDauEntity lichThiDau) {
        this.lichThiDau = lichThiDau;
    }

    public KetQuaTranDauEntity getKetQuaTranDau() {
        return KetQuaTranDau;
    }

    public void setKetQuaTranDau(KetQuaTranDauEntity ketQuaTranDau) {
        KetQuaTranDau = ketQuaTranDau;
    }
}
