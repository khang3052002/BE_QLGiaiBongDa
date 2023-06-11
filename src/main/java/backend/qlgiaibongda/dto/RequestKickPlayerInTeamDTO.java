package backend.qlgiaibongda.dto;

public class RequestKickPlayerInTeamDTO {
    private Long id_cauthu;
    private Long id_doibong;

    public Long getId_cauthu() {
        return id_cauthu;
    }
    public Boolean checkValidInfo(){
        Boolean check = true;
        if( id_cauthu ==null || id_doibong == null ){
            check = false;
        }

        return check;
    }
    public void setId_cauthu(Long id_cauthu) {
        this.id_cauthu = id_cauthu;
    }

    public Long getId_doibong() {
        return id_doibong;
    }

    public void setId_doibong(Long id_doibong) {
        this.id_doibong = id_doibong;
    }
}
