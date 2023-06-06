package backend.qlgiaibongda.api.input;

import java.sql.Date;
import java.util.List;

public class UpdateMatchInput {

    private List<DateMatchInput> dsTranDau;

    public boolean checkValidInput(){
        boolean check = true;
        if(dsTranDau == null){
            check = false;
        }else{
             for(DateMatchInput dateMatchInput: dsTranDau)
             {
                 if(!dateMatchInput.checkValidInfo()){
                     check = false;
                     break;
                 }
             }
        }

        return  check;
    }

    public List<DateMatchInput> getDsTranDau() {
        return dsTranDau;
    }

    public void setDsTranDau(List<DateMatchInput> dsTranDau) {
        this.dsTranDau = dsTranDau;
    }
}
