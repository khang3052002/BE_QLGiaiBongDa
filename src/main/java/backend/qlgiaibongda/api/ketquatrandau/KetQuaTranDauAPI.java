package backend.qlgiaibongda.api.ketquatrandau;

import backend.qlgiaibongda.api.input.ListIdMatchResult;
import backend.qlgiaibongda.api.input.ListMatchResultInput;
import backend.qlgiaibongda.api.input.MatchResultInput;
import backend.qlgiaibongda.dto.ResponeObject;
import backend.qlgiaibongda.entity.TranDauEntity;
import backend.qlgiaibongda.repository.TranDauRepository;
import backend.qlgiaibongda.service.IBangXepHangService;
import backend.qlgiaibongda.service.IKetQuaTranDauService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ketquatrandau")
@EnableTransactionManagement
public class KetQuaTranDauAPI {

    @Autowired
    private IKetQuaTranDauService ketQuaTranDauService;
    @Autowired
    private IBangXepHangService bangXepHangService;
    @Autowired
    private TranDauRepository tranDauRepository;
    @GetMapping("/{id}")
    public ResponseEntity<ResponeObject> getMatchResult(@PathVariable("id") Long id){
        return ketQuaTranDauService.getOne(id);
    }
    @PutMapping("/{id_trandau}/capnhat")
    public ResponseEntity<ResponeObject> updateMatchResult(@PathVariable("id_trandau") Long id_trandau,@RequestBody ListMatchResultInput listMatchResultInput){
        if(!listMatchResultInput.checkValidInfo()){
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new ResponeObject("FAIL", "Invalid Info", ""));
        }
        else {
            TranDauEntity tranDauEntity = tranDauRepository.findById(id_trandau).orElse(null);
            if(tranDauEntity.getLichThiDau().getMuaGiai().getTrangThai() == 1)
            {
                Integer flagHoa0_0 = listMatchResultInput.getFlag_Hoa_0_banthang();
                ResponseEntity<ResponeObject> respone = ketQuaTranDauService.updateMatchResult(listMatchResultInput,id_trandau,flagHoa0_0);
                Boolean checkUpdateRank = bangXepHangService.UpdateRanking(id_trandau);
                if(checkUpdateRank == false)
                {
                    return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED)
                            .body(new ResponeObject("FAIL", "Cập nhật bảng xếp hạng thất bại", ""));
                }
                return respone;
            }
            else{
                return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED)
                        .body(new ResponeObject("FAIL", "Mùa giải chưa bắt đầu!", ""));
            }

        }

    }

    @PutMapping("/batdau")
    public ResponseEntity<ResponeObject> startMatch(@RequestBody ListIdMatchResult listIdMatchResult){
        return ketQuaTranDauService.startMatch(listIdMatchResult);
    }

    @PutMapping("/ketthuc")
    public ResponseEntity<ResponeObject> endMatch(@RequestBody ListIdMatchResult listIdMatchResult){
        return ketQuaTranDauService.endMatch(listIdMatchResult);
    }
}
