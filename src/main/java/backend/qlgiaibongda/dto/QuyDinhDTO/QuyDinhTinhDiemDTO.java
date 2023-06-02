package backend.qlgiaibongda.dto.QuyDinhDTO;

import jakarta.persistence.Column;

public class QuyDinhTinhDiemDTO {
    private int thang;
    private int thua;
    private int hoa;

    public int getThang() {
        return thang;
    }

    public void setThang(int thang) {
        this.thang = thang;
    }

    public int getThua() {
        return thua;
    }

    public void setThua(int thua) {
        this.thua = thua;
    }

    public int getHoa() {
        return hoa;
    }

    public void setHoa(int hoa) {
        this.hoa = hoa;
    }
}
