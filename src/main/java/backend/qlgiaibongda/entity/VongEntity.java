//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package backend.qlgiaibongda.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "vongdau")
public class VongEntity extends BaseEntity {
    @Column(name = "tenvong", columnDefinition = "nvarchar(100)")
    private String tenVong;
    @Column(name = "thethuc", columnDefinition = "nvarchar(255)")
    private String theThuc;
    @OneToMany(mappedBy = "Vong")
    private List<TranDauEntity> listTranDauCuaVong = new ArrayList();


    public String getTenVong() {
        return tenVong;
    }

    public void setTenVong(String tenVong) {
        this.tenVong = tenVong;
    }

    public String getTheThuc() {
        return theThuc;
    }

    public void setTheThuc(String theThuc) {
        this.theThuc = theThuc;
    }

    public List<TranDauEntity> getListTranDauCuaVong() {
        return listTranDauCuaVong;
    }

    public void setListTranDauCuaVong(List<TranDauEntity> listTranDauCuaVong) {
        this.listTranDauCuaVong = listTranDauCuaVong;
    }
}
