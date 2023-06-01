package backend.qlgiaibongda.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "vitri")
public class ViTriEntity extends BaseEntity{
    @Column(name = "vitri")
    private String viTri;
    @Column(name="code")
    private String code;
    @ManyToMany(mappedBy = "CacViTri")
    private List<CauThuEntity> CacCauThu = new ArrayList<>();

    public String getViTri() {
        return viTri;
    }

    public void setViTri(String viTri) {
        this.viTri = viTri;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
