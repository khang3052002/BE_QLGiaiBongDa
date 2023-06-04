package backend.qlgiaibongda.service;

import backend.qlgiaibongda.dto.DangKyThamGiaGiaiDTO;
import backend.qlgiaibongda.dto.MuaGiaiDTO;
import backend.qlgiaibongda.dto.ResponeObject;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.http.ResponseEntity;

public interface IMuaGiaiService {
    ResponseEntity<ResponeObject> createLeague(MuaGiaiDTO muaGiaiDTO);

    ResponseEntity<ResponeObject> getAllLeague();

    ResponseEntity<ResponeObject> getLeagueById(Long id);

    ResponseEntity<ResponeObject> updateLeague(MuaGiaiDTO muaGiaiDTO);

    ResponseEntity<ResponeObject> registerJoinLeague(DangKyThamGiaGiaiDTO dangKyThamGiaGiaiDTO);

    ResponseEntity<ResponeObject> getLeagueOnRequest(String keyword, Integer trangThai);
}
