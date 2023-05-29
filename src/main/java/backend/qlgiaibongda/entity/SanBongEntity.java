//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package backend.qlgiaibongda.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "sanbong")
public class SanBongEntity extends BaseEntity {
    @Column(name = "tensan", columnDefinition = "nvarchar(255)")
    private String TenSan;
    @Column(name = "diadiem", columnDefinition = "nvarchar(255)")
    private String DiaDiem;
    @OneToOne(mappedBy = "SanBong")
    private DoiBongEntity DoiBong;



    public String getTenSan() {
        return this.TenSan;
    }

    public String getDiaDiem() {
        return this.DiaDiem;
    }


    public void setTenSan(String tenSan) {
        TenSan = tenSan;
    }

    public void setDiaDiem(String diaDiem) {
        DiaDiem = diaDiem;
    }

    public DoiBongEntity getDoiBong() {
        return DoiBong;
    }

    public void setDoiBong(DoiBongEntity doiBong) {
        DoiBong = doiBong;
    }
}
