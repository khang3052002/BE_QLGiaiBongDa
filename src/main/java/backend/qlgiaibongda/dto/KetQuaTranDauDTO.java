package backend.qlgiaibongda.dto;


import java.util.List;

public class KetQuaTranDauDTO {
    private Long id;
    private Long idDoiNha;
    private Long idDoiKhach;

    private Integer sbtDoiNha;

    private Integer sbtDoiKhach;

    private String trangThai;

    private List<GhiNhanBanThangDTO> dsBanThang;


    public List<GhiNhanBanThangDTO> getDsBanThang() {
        return dsBanThang;
    }

    public void setDsBanThang(List<GhiNhanBanThangDTO> dsBanThang) {
        this.dsBanThang = dsBanThang;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdDoiNha() {
        return idDoiNha;
    }

    public void setIdDoiNha(Long idDoiNha) {
        this.idDoiNha = idDoiNha;
    }

    public Long getIdDoiKhach() {
        return idDoiKhach;
    }

    public void setIdDoiKhach(Long idDoiKhach) {
        this.idDoiKhach = idDoiKhach;
    }

    public Integer getSbtDoiNha() {
        return sbtDoiNha;
    }

    public void setSbtDoiNha(Integer sbtDoiNha) {
        this.sbtDoiNha = sbtDoiNha;
    }

    public Integer getSbtDoiKhach() {
        return sbtDoiKhach;
    }

    public void setSbtDoiKhach(Integer sbtDoiKhach) {
        this.sbtDoiKhach = sbtDoiKhach;
    }
}
