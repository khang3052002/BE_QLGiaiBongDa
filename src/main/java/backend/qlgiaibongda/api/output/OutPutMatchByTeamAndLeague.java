package backend.qlgiaibongda.api.output;

import backend.qlgiaibongda.dto.TranĐauDTO;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.List;

public class OutPutMatchByTeamAndLeague {

    private List<TranĐauDTO> dsTranDaus;
    private Integer tranThang;
    private Integer tranHoa;
    private Integer tranThua;

    public List<TranĐauDTO> getDsTranDaus() {
        return dsTranDaus;
    }

    public void setDsTranDaus(List<TranĐauDTO> dsTranDaus) {
        this.dsTranDaus = dsTranDaus;
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
}
