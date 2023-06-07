package backend.qlgiaibongda.api.input;

import java.util.List;

public class ListMatchResultInput {

    private List<MatchResultInput> dsBanThang;

    public boolean checkValidInfo(){
        boolean check  = true;
        if(dsBanThang == null){
            check = false;
        }
        for(MatchResultInput matchResultInput: dsBanThang){
            if(!matchResultInput.checkValidInfo()){
                check = false;
                break;
            }
        }

        return check;

    }

    public List<MatchResultInput> getDsBanThang() {
        return dsBanThang;
    }

    public void setDsBanThang(List<MatchResultInput> dsBanThang) {
        this.dsBanThang = dsBanThang;
    }
}
