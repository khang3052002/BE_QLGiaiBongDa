package backend.qlgiaibongda.dto;

import java.util.ArrayList;
import java.util.List;

public class searchPlayerandTeamResponeDTO {
    private List<CauThuDTO> listCauThu = new ArrayList<>();
    private  List<TeamDTO> listDoiBong = new ArrayList<>();

    public List<CauThuDTO> getListCauThu() {
        return listCauThu;
    }

    public void setListCauThu(List<CauThuDTO> listCauThu) {
        this.listCauThu = listCauThu;
    }

    public List<TeamDTO> getListDoiBong() {
        return listDoiBong;
    }

    public void setListDoiBong(List<TeamDTO> listDoiBong) {
        this.listDoiBong = listDoiBong;
    }
}
