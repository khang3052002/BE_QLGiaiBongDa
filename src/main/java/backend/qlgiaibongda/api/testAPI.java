package backend.qlgiaibongda.api;

import backend.qlgiaibongda.dto.ResponeObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class testAPI {
    @PostMapping("")
    public ResponseEntity<ResponeObject> test()
    {
        return ResponseEntity.status(HttpStatus.OK).body(new ResponeObject("HAHAH","Authenticated HAHA","TEST"));
    }
}
