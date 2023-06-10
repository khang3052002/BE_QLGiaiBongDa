package backend.qlgiaibongda.api.user;

import backend.qlgiaibongda.dto.ResponeObject;
import backend.qlgiaibongda.service.IGeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/general")
public class UserAPI {
    @Autowired
    private IGeneralService generalService;
    @GetMapping("/search")
    public ResponseEntity<ResponeObject> searchPlayerAndTeamByKey(@RequestParam("keyword") String keyword)
    {
        return generalService.searchPlayerAndTeamByKey(keyword);
    }

}
