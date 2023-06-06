package backend.qlgiaibongda.api.ketquatrandau;

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
    public ResponseEntity<ResponeObject> updateMatchResult(@RequestBody MatchResultInput matchResultInput){
        if(!matchResultInput.checkValidInfo()){
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new ResponeObject("FAIL", "Invalid Info", ""));
        }

        return ketQuaTranDauService.updateMatchResult(matchResultInput);
    }

    @PutMapping("/batdau/{id}")
    public ResponseEntity<ResponeObject> startMatch(@PathVariable("id") Long id){
        return ketQuaTranDauService.startMatch(id);
    }

    @PutMapping("/ketthuc/{id}")
    public ResponseEntity<ResponeObject> endMatch(@PathVariable("id") Long id){
        return ketQuaTranDauService.endMatch(id);
    }
}
