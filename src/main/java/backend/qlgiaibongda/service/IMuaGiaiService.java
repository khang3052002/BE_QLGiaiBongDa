package backend.qlgiaibongda.service;

import backend.qlgiaibongda.dto.DangKyThamGiaGiaiDTO;
import backend.qlgiaibongda.dto.MuaGiaiDTO;
import backend.qlgiaibongda.dto.ResponeObject;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.http.ResponseEntity;

import org.springframework.data.domain.Pageable;

public interface IMuaGiaiService {
    ResponseEntity<ResponeObject> createLeagueWithNewRule(MuaGiaiDTO muaGiaiDTO);

    ResponseEntity<ResponeObject> getAllLeague(Pageable pageable);

    ResponseEntity<ResponeObject> getLeagueById(Long id);

    ResponseEntity<ResponeObject> updateLeague(MuaGiaiDTO muaGiaiDTO);

    ResponseEntity<ResponeObject> registerJoinLeague(DangKyThamGiaGiaiDTO dangKyThamGiaGiaiDTO);

    ResponseEntity<ResponeObject> getLeagueOnRequest(Pageable pageable, String keyword, Integer trangThai);
    int totalItem() ;

    ResponseEntity<ResponeObject> getRankingOfLeague(Long idMuagiai);

    ResponseEntity<ResponeObject> createLeagueWithOldRule(MuaGiaiDTO muaGiaiDTO);
}
