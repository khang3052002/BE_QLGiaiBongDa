package backend.qlgiaibongda.api.input;

import backend.qlgiaibongda.dto.CauThuDTO;
import backend.qlgiaibongda.dto.PlayerFreeDTO;

import java.util.List;

public class NewTeamPlayerInput {
    private Long idDoi;
    private List<CauThuDTO> dsCauThuMoi;
    private List<PlayerFreeDTO> dsCauThuTuDo;

    public Boolean checkValidInfo(){
        Boolean check = true;
        if(idDoi == null){
            return false;
        }
        if((dsCauThuMoi.isEmpty() && dsCauThuTuDo.isEmpty()))
        {
            return false;
        }
        else{
            if(!dsCauThuMoi.isEmpty()) {
                for (CauThuDTO player : dsCauThuMoi) {
                    if (!player.checkValidInfo()) {
                        check = false;
                        break;
                    }
                }
            }
            if(!dsCauThuTuDo.isEmpty())
            {
                for(PlayerFreeDTO player: dsCauThuTuDo)
                {
                    if(!player.checkValidInfo())
                    {
                        check = false;
                        break;
                    }
                }
            }
        }


        return check;
    }

    public List<PlayerFreeDTO> getDsCauThuTuDo() {
        return dsCauThuTuDo;
    }

    public void setDsCauThuTuDo(List<PlayerFreeDTO> dsCauThuTuDo) {
        this.dsCauThuTuDo = dsCauThuTuDo;
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
