package backend.qlgiaibongda.api.AuthAPI;


import backend.qlgiaibongda.dto.ResponeObject;
import backend.qlgiaibongda.dto.SignUpRequestDTO;
import backend.qlgiaibongda.service.iplm.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthAPI {

    @Autowired
    private SignUpService signUpService;
    @PostMapping("/signup")
    public ResponseEntity<ResponeObject> registerUser(@RequestBody SignUpRequestDTO signupRequest)
    {
        return signUpService.registerManager(signupRequest);
    }
}
