package backend.qlgiaibongda.dto;

import jakarta.persistence.Column;

public class TeamDTO extends BaseDTO {

    private String ten;
    private String hinhAnh;

    private int namThanhLap;

    private FieldDTO fieldDTO;

    public FieldDTO getFieldDTO() {
        return fieldDTO;
    }

    public void setFieldDTO(FieldDTO fieldDTO) {
        this.fieldDTO = fieldDTO;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public int getNamThanhLap() {
        return namThanhLap;
    }

    public void setNamThanhLap(int namThanhLap) {
        this.namThanhLap = namThanhLap;
    }
}
