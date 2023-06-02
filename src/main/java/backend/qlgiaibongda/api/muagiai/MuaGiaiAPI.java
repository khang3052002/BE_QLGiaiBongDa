package backend.qlgiaibongda.api.muagiai;

import backend.qlgiaibongda.dto.MuaGiaiDTO;
import backend.qlgiaibongda.dto.ResponeObject;
import backend.qlgiaibongda.service.iplm.MuaGiaiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/muagiai")
public class MuaGiaiAPI {
    @Autowired
    private MuaGiaiService muaGiaiService;

    @PostMapping("/taogiaidau")
    public ResponseEntity<ResponeObject> createLeague(@RequestBody MuaGiaiDTO muaGiaiDTO)
    {
        return muaGiaiService.createLeague(muaGiaiDTO);
    }
    @GetMapping("")
    public ResponseEntity<ResponeObject> getAllLeague()
    {
        return muaGiaiService.getAllLeague();
    }
    @GetMapping("/chitiet")
    public ResponseEntity<ResponeObject> getLeagueById(@RequestParam("id") Long id)
    {
        return muaGiaiService.getLeagueById(id);
    }
    @PutMapping("/capnhat")
    public ResponseEntity<ResponeObject> updateLeague(@RequestBody MuaGiaiDTO muaGiaiDTO)
    {
        return muaGiaiService.updateLeague(muaGiaiDTO);
    }
}
