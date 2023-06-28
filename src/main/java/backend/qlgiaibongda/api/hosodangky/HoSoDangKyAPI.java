package backend.qlgiaibongda.api.hosodangky;

import backend.qlgiaibongda.dto.ResponeObject;
import backend.qlgiaibongda.dto.TuChoiHoSoDTO;
import backend.qlgiaibongda.entity.MuaGiaiEntity;
import backend.qlgiaibongda.service.IHoSoDangKyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/hosodangky")
public class HoSoDangKyAPI {
    @Autowired
    private IHoSoDangKyService hoSoDangKyService;
    @GetMapping("/{id_muagiai}")
    public ResponseEntity<ResponeObject> getHoSoDangKy_MuaGiai(@PathVariable("id_muagiai") Long id_muagiai)
    {
        return hoSoDangKyService.getHoSoDangKyByMuaGiai(id_muagiai);
    }
//    @GetMapping("/{id_muagiai}/doibong")
//    public ResponseEntity<ResponeObject> getHoSoDangKy_MuaGiai(@PathVariable("id_muagiai") Long id_muagiai)
//    {
//        return hoSoDangKyService.getHoSoDangKyByMuaGiai(id_muagiai);
//    }
    @GetMapping("/{id_doibong}/danhsach")
    public ResponseEntity<ResponeObject> getHoSoDangKy_DoiBong(@PathVariable("id_doibong") Long id_doibong)
    {
        return hoSoDangKyService.getHoSoDangKy_DoiBong(id_doibong);
    }
    @GetMapping("/chitiet")
    public ResponseEntity<ResponeObject> getDetailHoSoDangKyByID(@RequestParam("hoso") Long id_hoso)
    {
        return hoSoDangKyService.getDetailHoSoDangKyByID(id_hoso);
    }
    @PutMapping("/duyet")
    public ResponseEntity<ResponeObject> duyetHoSoDangKy(@RequestParam("hoso") Long id_hoso)
    {
        return hoSoDangKyService.duyetHoSoDangKy(id_hoso);
    }
    @PutMapping("/tuchoi")
    public ResponseEntity<ResponeObject> tuChoiHoSoDangKy(@RequestBody TuChoiHoSoDTO tuChoiHoSoDTO)
    {
        return hoSoDangKyService.tuChoiHoSoDangKy(tuChoiHoSoDTO);
    }
    @PutMapping("/huy")
    public ResponseEntity<ResponeObject> huyHoSoDangKyByQLDB(@RequestParam("hoso") Long id_hoso)
    {
        return hoSoDangKyService.huyHoSoDangKyByQLDB(id_hoso);
    }
}
