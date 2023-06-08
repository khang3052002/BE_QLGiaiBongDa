package backend.qlgiaibongda.dto;

import jakarta.persistence.criteria.CriteriaBuilder;

public class BXH_DoiBongDTO {
    private String ten_doi;
    private Integer xephang;
    private Long id_doibong;
    private Integer diem;
    private Integer hieuSo;
    private Integer phaLuoi;
    private Integer thungLuoi;
    private Integer tranThang;
    private Integer tranHoa;
    private Integer tranThua;
    private Integer vong;

    public String getTen_doi() {
        return ten_doi;
    }

    public void setTen_doi(String ten_doi) {
        this.ten_doi = ten_doi;
    }

    public Integer getXephang() {
        return xephang;
    }

    public void setXephang(Integer xephang) {
        this.xephang = xephang;
    }

    public Long getId_doibong() {
        return id_doibong;
    }

    public void setId_doibong(Long id_doibong) {
        this.id_doibong = id_doibong;
    }

    public Integer getDiem() {
        return diem;
    }

    public void setDiem(Integer diem) {
        this.diem = diem;
    }

    public Integer getHieuSo() {
        return hieuSo;
    }

    public void setHieuSo(Integer hieuSo) {
        this.hieuSo = hieuSo;
    }

    public Integer getPhaLuoi() {
        return phaLuoi;
    }

    public void setPhaLuoi(Integer phaLuoi) {
        this.phaLuoi = phaLuoi;
    }

    public Integer getThungLuoi() {
        return thungLuoi;
    }

    public void setThungLuoi(Integer thungLuoi) {
        this.thungLuoi = thungLuoi;
    }

    public Integer getTranThang() {
        return tranThang;
    }

    public void setTranThang(Integer tranThang) {
        this.tranThang = tranThang;
    }

    public Integer getTranHoa() {
        return tranHoa;
    }

    public void setTranHoa(Integer tranHoa) {
        this.tranHoa = tranHoa;
    }

    public Integer getTranThua() {
        return tranThua;
    }

    public void setTranThua(Integer tranThua) {
        this.tranThua = tranThua;
    }

    public Integer getVong() {
        return vong;
    }

    public void setVong(Integer vong) {
        this.vong = vong;
    }
}
