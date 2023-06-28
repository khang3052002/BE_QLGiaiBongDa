package backend.qlgiaibongda.dto;

import java.util.ArrayList;
import java.util.List;

public class ListPlayerDTO {
    private Integer page;
    private  Integer totalPage;
    private List<CauThuDTO> listPlayerDto = new ArrayList<>();

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public List<CauThuDTO> getListPlayerDto() {
        return listPlayerDto;
    }

    public void setListPlayerDto(List<CauThuDTO> listPlayerDto) {
        this.listPlayerDto = listPlayerDto;
    }
}
