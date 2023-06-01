package backend.qlgiaibongda.api.output;

import backend.qlgiaibongda.dto.TeamDTO;

import java.util.ArrayList;
import java.util.List;

public class AllTeamOuput {
    private int page;
    private int totalPage;
    private List<TeamDTO> listResult = new ArrayList<>();

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<TeamDTO> getListResult() {
        return listResult;
    }

    public void setListResult(List<TeamDTO> listResult) {
        this.listResult = listResult;
    }
}
