package backend.qlgiaibongda.service;

import backend.qlgiaibongda.api.input.ListIdMatchResult;
import backend.qlgiaibongda.api.input.ListMatchResultInput;
import backend.qlgiaibongda.api.input.MatchResultInput;
import backend.qlgiaibongda.dto.ResponeObject;
import org.springframework.http.ResponseEntity;

public interface IKetQuaTranDauService {
    ResponseEntity<ResponeObject> updateMatchResult(ListMatchResultInput listMatchResultInput, Long id_trandau, Integer flagHoa0_0);

    ResponseEntity<ResponeObject> getOne(Long id);

    ResponseEntity<ResponeObject> startMatch(ListIdMatchResult listIdMatchResult);
    ResponseEntity<ResponeObject> endMatch(ListIdMatchResult listIdMatchResult);
}
