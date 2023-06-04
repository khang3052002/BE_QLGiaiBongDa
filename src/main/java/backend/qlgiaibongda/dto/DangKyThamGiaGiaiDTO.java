package backend.qlgiaibongda.dto;

public class DangKyThamGiaGiaiDTO {
    private Long id_doibong;
    private Long id_giai;
    private Long[] ds_id_cauthu_thamgia;

    public Boolean checkValidInfo(){
        Boolean check = true;
        if(id_doibong == null || id_giai == null || ds_id_cauthu_thamgia == null)
        {
            check = false;
        }
        return check;
    }

    public Long getId_doibong() {
        return id_doibong;
    }

    public void setId_doibong(Long id_doibong) {
        this.id_doibong = id_doibong;
    }

    public Long getId_giai() {
        return id_giai;
    }

    public void setId_giai(Long id_giai) {
        this.id_giai = id_giai;
    }

    public Long[] getDs_id_cauthu_thamgia() {
        return ds_id_cauthu_thamgia;
    }

    public void setDs_id_cauthu_thamgia(Long[] ds_id_cauthu_thamgia) {
        this.ds_id_cauthu_thamgia = ds_id_cauthu_thamgia;
    }
}
