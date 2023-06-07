package backend.qlgiaibongda.dto;

public class BXHDoiBongDTO {
    private Long id_doi;
    private String ten_doi;

    private Integer vong;
    private Integer hieuso_test;
    private Integer tranThang;
    private Integer tranThua;
    private Integer tranHoa;
    private Integer phaLuoi;
    private Integer thungLuoi;

    public Long getId_doi() {
        return id_doi;
    }

    public void setId_doi(Long id_doi) {
        this.id_doi = id_doi;
    }

    public String getTen_doi() {
        return ten_doi;
    }

    public void setTen_doi(String ten_doi) {
        this.ten_doi = ten_doi;
    }

    public Integer getVong() {
        return vong;
    }

    public void setVong(Integer vong) {
        this.vong = vong;
    }

    public Integer getHieuso_test() {
        return hieuso_test;
    }

    public void setHieuso_test(Integer hieuso_test) {
        this.hieuso_test = hieuso_test;
    }

    public Integer getTranThang() {
        return tranThang;
    }

    public void setTranThang(Integer tranThang) {
        this.tranThang = tranThang;
    }

    public Integer getTranThua() {
        return tranThua;
    }

    public void setTranThua(Integer tranThua) {
        this.tranThua = tranThua;
    }

    public Integer getTranHoa() {
        return tranHoa;
    }

    public void setTranHoa(Integer tranHoa) {
        this.tranHoa = tranHoa;
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
}
