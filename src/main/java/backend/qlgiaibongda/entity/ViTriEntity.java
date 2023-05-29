package backend.qlgiaibongda.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "vitri")
public class ViTriEntity extends BaseEntity{
    @Column(name = "vitri")
    private String ViTri;

    @ManyToMany(mappedBy = "CacViTri")
    private List<CauThuEntity> CacCauThu = new ArrayList<>();

    public String getViTri() {
        return ViTri;
    }

    public void setViTri(String viTri) {
        ViTri = viTri;
    }
}
