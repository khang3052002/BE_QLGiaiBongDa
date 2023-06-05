package backend.qlgiaibongda.dto.QuyDinhDTO;

import backend.qlgiaibongda.dto.TranĐauDTO;

import java.util.List;

public class VongDauDTO {
    private Long id;
    private String tenVong;

    private List<TranĐauDTO> cacTranDau;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTenVong() {
        return tenVong;
    }

    public void setTenVong(String tenVong) {
        this.tenVong = tenVong;
    }

    public List<TranĐauDTO> getCacTranDau() {
        return cacTranDau;
    }

    public void setCacTranDau(List<TranĐauDTO> cacTranDau) {
        this.cacTranDau = cacTranDau;
    }
}
