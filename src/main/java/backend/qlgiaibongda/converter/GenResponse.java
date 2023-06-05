package backend.qlgiaibongda.converter;

import backend.qlgiaibongda.dto.ResponeObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

public class GenResponse {
    public static ResponseEntity<ResponeObject> gen(HttpStatusCode code , String status, String message, Object data){
        return ResponseEntity.status(code).body(new ResponeObject(status, message, data));
    }
}
