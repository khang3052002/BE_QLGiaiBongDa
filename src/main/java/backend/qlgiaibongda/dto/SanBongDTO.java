package backend.qlgiaibongda.dto;

import backend.qlgiaibongda.entity.DoiBongEntity;
import jakarta.persistence.Column;
import jakarta.persistence.OneToOne;

public class SanBongDTO {
    private Long id;
    private String tenSan;
    private String diaDiem;

    private TeamDTO doiBong;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public TeamDTO getDoiBong() {
        return doiBong;
    }

    public void setDoiBong(TeamDTO doiBong) {
        this.doiBong = doiBong;
    }
}
