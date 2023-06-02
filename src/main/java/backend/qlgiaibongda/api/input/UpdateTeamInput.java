package backend.qlgiaibongda.api.input;

public class UpdateTeamInput {
    private Long id;
    private Long idQuanLy;
    private Long idSanNha;
    private String ten;
    private String hinhAnh;
    private int namThanhLap;

    public Boolean checkValidInfo(){
        Boolean check = true;
        if( id ==null || idQuanLy == null || idSanNha == null || ten == null || hinhAnh == null || namThanhLap==0  ){
            check = false;
        }

        return check;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdQuanLy() {
        return idQuanLy;
    }

    public void setIdQuanLy(Long idQuanLy) {
        this.idQuanLy = idQuanLy;
    }

    public Long getIdSanNha() {
        return idSanNha;
    }

    public void setIdSanNha(Long idSanNha) {
        this.idSanNha = idSanNha;
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
