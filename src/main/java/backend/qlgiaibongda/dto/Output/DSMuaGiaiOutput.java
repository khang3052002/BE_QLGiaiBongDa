package backend.qlgiaibongda.dto.Output;

import backend.qlgiaibongda.dto.MuaGiaiDTO;

import java.util.ArrayList;
import java.util.List;

public class DSMuaGiaiOutput {
    private int page;
    private int totalPage;
    private List<MuaGiaiDTO> listResult = new ArrayList<>();

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

    public List<MuaGiaiDTO> getListResult() {
        return listResult;
    }

    public void setListResult(List<MuaGiaiDTO> listResult) {
        this.listResult = listResult;
    }
}
