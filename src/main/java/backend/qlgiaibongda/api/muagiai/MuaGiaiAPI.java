package backend.qlgiaibongda.api.muagiai;

import backend.qlgiaibongda.dto.DangKyThamGiaGiaiDTO;
import backend.qlgiaibongda.dto.MuaGiaiDTO;
import backend.qlgiaibongda.dto.ResponeObject;
import backend.qlgiaibongda.service.IMuaGiaiService;
import backend.qlgiaibongda.service.iplm.MuaGiaiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/muagiai")
@EnableTransactionManagement
public class MuaGiaiAPI {
    @Autowired
    private IMuaGiaiService muaGiaiService;

    @PostMapping("/taogiaidau_quydinhmoi")
    public ResponseEntity<ResponeObject> createLeagueWithNewRule(@RequestBody MuaGiaiDTO muaGiaiDTO)
    {
        if(muaGiaiDTO.checkValidInfo_CreateLeague() == false)
        {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new ResponeObject("FAIL", "Invalid Info", ""));
        }
        return muaGiaiService.createLeagueWithNewRule(muaGiaiDTO);
    }
    @PostMapping("/taogiaidau_quydinhcu")
    public ResponseEntity<ResponeObject> createLeagueWithOldRule(@RequestBody MuaGiaiDTO muaGiaiDTO)
    {
        if(muaGiaiDTO.checkValidInfo_CreateLeagueWithOldRule() == false)
        {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new ResponeObject("FAIL", "Invalid Info", ""));
        }
        return muaGiaiService.createLeagueWithOldRule(muaGiaiDTO);
    }
    @GetMapping("")
    public ResponseEntity<ResponeObject> getAllLeague(
            @RequestParam(value = "page")  Integer page,
            @RequestParam(value = "limit") Integer limit,
            @RequestParam(value = "keyword",required = false) String keyword,
            @RequestParam(value = "trangthai", required = false) Integer trangThai
    )
    {
        // nếu các param là null all thì get all
        Pageable pageable = PageRequest.of(page - 1, limit);
        if(keyword == null && trangThai == null)
        {
            return muaGiaiService.getAllLeague(pageable);
        }
        else{
            return muaGiaiService.getLeagueOnRequest(pageable,keyword,trangThai);
        }
    }
//    @GetMapping("")
//    public String getLeague(@RequestParam("keyword") String keyword)
//    {
//        return "haha";
//    }
    @GetMapping("/chitiet")
    public ResponseEntity<ResponeObject> getLeagueById(@RequestParam("id") Long id)
    {
        return muaGiaiService.getLeagueById(id);
    }
    @PutMapping("/capnhat")
    public ResponseEntity<ResponeObject> updateLeague(@RequestBody MuaGiaiDTO muaGiaiDTO)
    {
        if(muaGiaiDTO.checkValidInfo_UpdateLeague() == false)
        {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new ResponeObject("FAIL", "Invalid Info", ""));
        }
        return muaGiaiService.updateLeague(muaGiaiDTO);
    }
    @PostMapping("/thamgiagiai")
    public ResponseEntity<ResponeObject> resgisterJoinLeague(@RequestBody DangKyThamGiaGiaiDTO dangKyThamGiaGiaiDTO)
    {
        if(dangKyThamGiaGiaiDTO.checkValidInfo() == false)
        {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new ResponeObject("FAIL", "Invalid Info", ""));
        }
        return muaGiaiService.registerJoinLeague(dangKyThamGiaGiaiDTO);
    }

    @GetMapping("/{id_muagiai}/ranking")
    public ResponseEntity<ResponeObject> getRankingOfLeague(@PathVariable("id_muagiai") Long id_muagiai)
    {
        return muaGiaiService.getRankingOfLeague(id_muagiai);
    }

    // nhấn kích hoạt mùa giải => Tạo lịch, tạo bảng xếp hạng
    @PostMapping("/{id_muagiai}/kichhoat")
    public  ResponseEntity<ResponeObject> startLeague(@PathVariable("id_muagiai") Long id_muagiai)
    {
        return muaGiaiService.startLeague(id_muagiai);
    }



}
