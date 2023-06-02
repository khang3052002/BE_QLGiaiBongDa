package backend.qlgiaibongda.api.input;

import backend.qlgiaibongda.dto.CauThuDTO;

import java.util.List;

public class NewTeamPlayerInput {
    private Long idDoi;
    private List<CauThuDTO> dsCauThuMoi;

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
