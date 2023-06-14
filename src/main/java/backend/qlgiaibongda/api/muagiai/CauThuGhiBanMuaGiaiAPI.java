package backend.qlgiaibongda.api.muagiai;

import backend.qlgiaibongda.dto.ResponeObject;
import backend.qlgiaibongda.service.ICauThuGhiBanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cauthughibanmuagiai")
public class CauThuGhiBanMuaGiaiAPI {

    @Autowired
    private ICauThuGhiBanService cauThuGhiBanService;
    @GetMapping("/{idMuaGiai}")
    public ResponseEntity<ResponeObject> getDSCauThuGhiBanMuaGiai(@PathVariable("idMuaGiai") Long idMuaGiai){
        return cauThuGhiBanService.layDSCauThuGhiBan(idMuaGiai);
    }
}
