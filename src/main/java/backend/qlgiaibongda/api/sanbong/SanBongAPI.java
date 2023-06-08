package backend.qlgiaibongda.api.sanbong;

import backend.qlgiaibongda.dto.ResponeObject;
import backend.qlgiaibongda.service.ISanBongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sanbong")
public class SanBongAPI {

    @Autowired
    private ISanBongService sanBongService;
    @GetMapping("/chuadangky")
    public ResponseEntity<ResponeObject> getStadiumsNotRegistered()
    {
        return sanBongService.getStadiumsNotRegistered();
    }
}
