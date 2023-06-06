package backend.qlgiaibongda.api.trandau;

import backend.qlgiaibongda.api.input.UpdateMatchInput;
import backend.qlgiaibongda.converter.GenResponse;
import backend.qlgiaibongda.dto.ResponeObject;
import backend.qlgiaibongda.service.ITranDauService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/trandau")
public class TranDauAPI {

    @Autowired
    private ITranDauService tranDauService;


    @GetMapping("/{id}")
    public ResponseEntity<ResponeObject> getMatch(@PathVariable("id") Long id){
        return tranDauService.getMatch(id);
    }

    @PutMapping
    public ResponseEntity<ResponeObject> updateTime(@RequestBody UpdateMatchInput updateMatchInput){
        if(!updateMatchInput.checkValidInput()){
            return GenResponse.gen(HttpStatus.CONFLICT, "FAIL", "Invalid Input!", "");
        }

        return tranDauService.updateTime(updateMatchInput);
    }


}
