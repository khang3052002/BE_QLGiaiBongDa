package backend.qlgiaibongda.api.muagiai;

import backend.qlgiaibongda.dto.MuaGiaiDTO;
import backend.qlgiaibongda.dto.ResponeObject;
import backend.qlgiaibongda.service.iplm.MuaGiaiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
