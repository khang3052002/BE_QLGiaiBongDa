package backend.qlgiaibongda.api.cauthu;

import backend.qlgiaibongda.dto.CauThuDTO;
import backend.qlgiaibongda.dto.ResponeObject;
import backend.qlgiaibongda.service.ICauThuService;
import backend.qlgiaibongda.service.iplm.CauThuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cauthu")
public class CauThuAPI {
    @Autowired
    private ICauThuService cauThuService;
    @GetMapping("")
    public ResponseEntity<ResponeObject> getPlayerByID(@RequestParam("id") Long id)
    {
        return cauThuService.getPlayerByID(id);
    }
    @PostMapping("")
    public ResponseEntity<ResponeObject> addNewPlayer(@RequestBody CauThuDTO cauThuDTO)
    {
        return cauThuService.addNewPlayer(cauThuDTO);
    }
    @PutMapping("/update")
    public ResponseEntity<ResponeObject> editPlayer(@RequestBody CauThuDTO cauThuDTO)
    {
        if(cauThuDTO.checkValidInfo_EditPlayer() == false)
        {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new ResponeObject("FAIL", "Invalid Info", ""));
        }
        return cauThuService.editPlayer(cauThuDTO);
    }

    @GetMapping("/all")
    public ResponseEntity<ResponeObject> searchAllPlayerByNameOrRole(
            @RequestParam(value="page",required = false) Integer page,
            @RequestParam(value = "limit",required = false) Integer limit,
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(value = "role",required = false) String role)
    {
        return cauThuService.searchAllPlayerByNameOrRole(keyword,role,page,limit);
    }
    @GetMapping("/tudo")
    public ResponseEntity<ResponeObject> getPlayerFree()
    {
        return cauThuService.getPlayerFree();
    }

}
