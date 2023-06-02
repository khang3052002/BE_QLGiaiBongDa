package backend.qlgiaibongda.api.doibong;

import backend.qlgiaibongda.api.input.NewTeamInput;
import backend.qlgiaibongda.api.input.NewTeamPlayerInput;
import backend.qlgiaibongda.api.input.UpdateTeamInput;
import backend.qlgiaibongda.api.output.AllTeamOuput;
import backend.qlgiaibongda.api.output.ErrorOutput;
import backend.qlgiaibongda.dto.CauThuDTO;
import backend.qlgiaibongda.dto.ResponeObject;
import backend.qlgiaibongda.dto.TeamDTO;
import backend.qlgiaibongda.service.ICauThuService;
import backend.qlgiaibongda.service.ITeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/doibong")
public class DoiBongAPI {

    @Autowired
    private ICauThuService cauThuService;

    @Autowired
    private ITeamService teamService;

    @GetMapping
    public ResponseEntity<ResponeObject> getAllTeam(@RequestParam("page") int page,
                           @RequestParam("limit") int limit){
        AllTeamOuput result = new AllTeamOuput();
        result.setPage(page);
        page--;
        Pageable pageable = PageRequest.of(page, limit);
        result.setListResult(teamService.findAll(pageable));
        result.setTotalPage((int) Math.ceil((double) teamService.totalItem()/ limit));
        return ResponseEntity.status(HttpStatus.OK).body(new ResponeObject("OK", "Get list teams succeed", result));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponeObject> getTeam(@PathVariable("id") Long id){
        return teamService.findById(id);
    }

    @PostMapping
    public ResponseEntity<ResponeObject> addNewTeam(@RequestBody NewTeamInput teamInput){
        if(!teamInput.checkValidInfo()){
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new ResponeObject("FAIL", "Invalid Info", ""));
        }
        return teamService.save(teamInput);
    }

    //did not exist
    @PostMapping("/cauthu")
    public ResponseEntity<ResponeObject> addNewPlayer(@RequestBody NewTeamPlayerInput newTeamPlayerInput){
        if(!newTeamPlayerInput.checkValidInfo()){
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new ResponeObject("FAIL", "Invalid Info", ""));
        }
        return cauThuService.addNewListTeamPlayer(newTeamPlayerInput);
    }

    @PutMapping
    public ResponseEntity<ResponeObject> updateTeam(@RequestBody UpdateTeamInput updateTeamInput) {
        if(!updateTeamInput.checkValidInfo()){
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new ResponeObject("FAIL", "Invalid Info", ""));
        }
        return teamService.updateTeam(updateTeamInput);
    }

}
