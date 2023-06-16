package backend.qlgiaibongda.api.input;

import backend.qlgiaibongda.dto.FieldDTO;
import backend.qlgiaibongda.dto.SanBongDTO;

public class NewTeamInput {
    private Long idQuanLy;
//    private Long idSanNha;
    private SanBongDTO sanbong;
    private String ten;
    private String hinhAnh;
    private int namThanhLap;


    public Boolean checkValidInfo(){
        Boolean check = true;
        if(idQuanLy == null || ten == null || hinhAnh == null || namThanhLap==0 || sanbong== null ){
            check = false;
        }
        return check;
    }

    public Long getIdQuanLy() {
        return idQuanLy;
    }

    public SanBongDTO getSanbong() {
        return sanbong;
    }

    public void setSanbong(SanBongDTO sanbong) {
        this.sanbong = sanbong;
    }

    public void setIdQuanLy(Long idQuanLy) {
        this.idQuanLy = idQuanLy;
    }

//    public Long getIdSanNha() {
//        return idSanNha;
//    }
//
//    public void setIdSanNha(Long idSanNha) {
//        this.idSanNha = idSanNha;
//    }

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
