package backend.qlgiaibongda.dto;

import backend.qlgiaibongda.dto.QuyDinhDTO.VongDauDTO;

import java.util.List;

public class LichThiDauDTO {
    private Long idMuaGiaID;

    private List<VongDauDTO> cacVongDau;

    public Long getIdMuaGiaID() {
        return idMuaGiaID;
    }

    public void setIdMuaGiaID(Long idMuaGiaID) {
        this.idMuaGiaID = idMuaGiaID;
    }

    public List<VongDauDTO> getCacVongDau() {
        return cacVongDau;
    }

    public void setCacVongDau(List<VongDauDTO> cacVongDau) {
        this.cacVongDau = cacVongDau;
    }
}
