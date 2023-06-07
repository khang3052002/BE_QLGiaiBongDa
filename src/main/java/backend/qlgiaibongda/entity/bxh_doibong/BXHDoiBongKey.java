package backend.qlgiaibongda.entity.bxh_doibong;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class BXHDoiBongKey implements Serializable {

    @Column(name = "id_doibong")
    private Long idDoiBong;
    @Column(name = "id_bxh")
    private Long idBXH;

    public BXHDoiBongKey() {
    }

    public Long getIdDoiBong() {
        return idDoiBong;
    }

    public void setIdDoiBong(Long idDoiBong) {
        this.idDoiBong = idDoiBong;
    }

    public BXHDoiBongKey(Long idDoiBong, Long idBXH) {
        this.idDoiBong = idDoiBong;
        this.idBXH = idBXH;
    }

    public Long getIdBXH() {
        return idBXH;
    }

    public void setIdBXH(Long idBXH) {
        this.idBXH = idBXH;
    }
}
