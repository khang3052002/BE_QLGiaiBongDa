package backend.qlgiaibongda.api.ketquatrandau;

import backend.qlgiaibongda.api.input.ListIdMatchResult;
import backend.qlgiaibongda.api.input.ListMatchResultInput;
import backend.qlgiaibongda.api.input.MatchResultInput;
import backend.qlgiaibongda.dto.ResponeObject;
import backend.qlgiaibongda.service.IKetQuaTranDauService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ketquatrandau")
public class KetQuaTranDauAPI {

    @Autowired
    private IKetQuaTranDauService ketQuaTranDauService;

    @GetMapping("/{id}")
    public ResponseEntity<ResponeObject> getMatchResult(@PathVariable("id") Long id){
        return ketQuaTranDauService.getOne(id);
    }
    @PutMapping
    public ResponseEntity<ResponeObject> updateMatchResult(@RequestBody ListMatchResultInput listMatchResultInput){
        if(!listMatchResultInput.checkValidInfo()){
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new ResponeObject("FAIL", "Invalid Info", ""));
        }

        return ketQuaTranDauService.updateMatchResult(listMatchResultInput);
    }

    @PutMapping("/batdau")
    public ResponseEntity<ResponeObject> startMatch(@RequestBody ListIdMatchResult listIdMatchResult){
        return ketQuaTranDauService.startMatch(listIdMatchResult);
    }

    @PutMapping("/ketthuc")
    public ResponseEntity<ResponeObject> endMatch(@RequestBody ListIdMatchResult listIdMatchResult){
        return ketQuaTranDauService.endMatch(listIdMatchResult);
    }
}
