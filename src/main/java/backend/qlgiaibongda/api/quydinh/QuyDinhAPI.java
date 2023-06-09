package backend.qlgiaibongda.api.quydinh;

import backend.qlgiaibongda.dto.ResponeObject;
import backend.qlgiaibongda.service.IQuyDinhMuaGiaiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/quydinh")
public class QuyDinhAPI {
    @Autowired
    private IQuyDinhMuaGiaiService quyDinhMuaGiaiService;
    @GetMapping("")
    public ResponseEntity<ResponeObject> getQuyDinhCuaAllMuaGiai()
    {
        return quyDinhMuaGiaiService.getQuyDinhCuaAllMuaGiai();
    }

}
