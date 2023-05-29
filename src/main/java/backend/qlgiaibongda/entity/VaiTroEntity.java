//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package backend.qlgiaibongda.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;

@Entity
@Table(name = "vaitro")
public class VaiTroEntity extends BaseEntity {
    @Column(name = "tenvaitro")
    private String tenVaiTro;
    @Column(name = "code")
    private String code;
    @OneToMany(mappedBy = "VaiTro")
    private List<QuanLyEntity> listQuanLy;



    public List<QuanLyEntity> getListQuanLy() {
        return this.listQuanLy;
    }

    public String getTenVaiTro() {
        return this.tenVaiTro;
    }

    public String getCode() {
        return this.code;
    }

    public void setTenVaiTro(String tenVaiTro) {
        tenVaiTro = tenVaiTro;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setListQuanLy(List<QuanLyEntity> listQuanLy) {
        this.listQuanLy = listQuanLy;
    }
}
