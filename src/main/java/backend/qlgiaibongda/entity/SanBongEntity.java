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
    private String tenSan;

    @Column(name = "hinhanh")
    private String hinhAnh;
    @Column(name = "diadiem", columnDefinition = "nvarchar(255)")
    private String diaDiem;
    @OneToOne(mappedBy = "sanBong")
    private DoiBongEntity doiBong;


    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public String getTenSan() {
        return tenSan;
    }

    public void setTenSan(String tenSan) {
        this.tenSan = tenSan;
    }

    public String getDiaDiem() {
        return diaDiem;
    }

    public void setDiaDiem(String diaDiem) {
        this.diaDiem = diaDiem;
    }

    public DoiBongEntity getDoiBong() {
        return doiBong;
    }

    public void setDoiBong(DoiBongEntity doiBong) {
        this.doiBong = doiBong;
    }
}
