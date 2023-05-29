package backend.qlgiaibongda.api.doibong;

import backend.qlgiaibongda.dto.CauThuDTO;
import backend.qlgiaibongda.service.ICauThuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doibong")
public class DoiBongAPI {

    @Autowired
    private ICauThuService cauThuService;

    @PostMapping
    public String themDoiBong(){
        return "them doi bong";
    }

    @PostMapping("/cauthu")
    public CauThuDTO themMoiCauThu(@RequestBody CauThuDTO cauThuDTO){
        return cauThuService.save(cauThuDTO);
    }

}
