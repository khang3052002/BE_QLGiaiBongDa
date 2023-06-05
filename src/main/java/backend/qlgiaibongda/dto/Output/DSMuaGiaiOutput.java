package backend.qlgiaibongda.dto.Output;

import backend.qlgiaibongda.dto.MuaGiaiDTO;

import java.util.ArrayList;
import java.util.List;

public class DSMuaGiaiOutput {
    private int page;
    private int totalPage;
    private Object listResult;

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

    public Object getListResult() {
        return listResult;
    }

    public void setListResult(Object listResult) {
        this.listResult = listResult;
    }
}
