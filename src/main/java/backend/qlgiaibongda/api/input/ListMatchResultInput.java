package backend.qlgiaibongda.api.input;

import java.util.List;

public class ListMatchResultInput {
    private Integer flag_Hoa_0_banthang;
    private List<MatchResultInput> dsBanThang;

    public boolean checkValidInfo(){
        boolean check  = true;
        if(flag_Hoa_0_banthang == 0) // khong phải trận hoòa 0-0 thì dsBanThang != null
        {
            if(dsBanThang == null){
                check = false;
            }
            for(MatchResultInput matchResultInput: dsBanThang){
                if(!matchResultInput.checkValidInfo()){
                    check = false;
                    break;
                }
            }
        }
        else{
            // nếu là trận hòa 0-0 thì dsBanThang = []
            if(dsBanThang.size() !=0)
            {
                check = false;
            }
        }
        return check;

    }

    public Integer getFlag_Hoa_0_banthang() {
        return flag_Hoa_0_banthang;
    }

    public void setFlag_Hoa_0_banthang(Integer flag_Hoa_0_banthang) {
        this.flag_Hoa_0_banthang = flag_Hoa_0_banthang;
    }

    public List<MatchResultInput> getDsBanThang() {
        return dsBanThang;
    }

    public void setDsBanThang(List<MatchResultInput> dsBanThang) {
        this.dsBanThang = dsBanThang;
    }
}
