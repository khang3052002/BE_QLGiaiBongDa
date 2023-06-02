package backend.qlgiaibongda.api.input;

import backend.qlgiaibongda.dto.CauThuDTO;

import java.util.List;

public class NewTeamPlayerInput {
    private Long idDoi;
    private List<CauThuDTO> dsCauThuMoi;

    public Boolean checkValidInfo(){
        Boolean check = true;
        if(idDoi == null){
            return false;
        }
        for(CauThuDTO player : dsCauThuMoi){
            if(!player.checkValidInfo()){
                check = false;
                break;
            }
        }

        return check;
    }

    public Long getIdDoi() {
        return idDoi;
    }

    public void setIdDoi(Long idDoi) {
        this.idDoi = idDoi;
    }

    public List<CauThuDTO> getDsCauThuMoi() {
        return dsCauThuMoi;
    }

    public void setDsCauThuMoi(List<CauThuDTO> dsCauThuMoi) {
        this.dsCauThuMoi = dsCauThuMoi;
    }
}
