package backend.qlgiaibongda.service;

import backend.qlgiaibongda.api.input.MatchResultInput;
import backend.qlgiaibongda.dto.ResponeObject;
import org.springframework.http.ResponseEntity;

public interface IKetQuaTranDauService {
    ResponseEntity<ResponeObject> updateMatchResult(MatchResultInput matchResultInput);

    ResponseEntity<ResponeObject> getOne(Long id);

    ResponseEntity<ResponeObject> startMatch(Long id);
    ResponseEntity<ResponeObject> endMatch(Long id);
}
