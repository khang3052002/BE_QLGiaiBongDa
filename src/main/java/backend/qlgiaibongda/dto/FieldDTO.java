package backend.qlgiaibongda.dto;

public class FieldDTO {

    private Long id;

    private String tenSan;
    private String diaDiem;

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
}
