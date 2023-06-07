package backend.qlgiaibongda.api;

import backend.qlgiaibongda.converter.GenericConverter;
import backend.qlgiaibongda.dto.BXHDoiBongDTO;
import backend.qlgiaibongda.dto.ResponeObject;
import backend.qlgiaibongda.entity.DoiBongEntity;
import backend.qlgiaibongda.entity.bxh_doibong.BXHDoiBongEntity;
import backend.qlgiaibongda.entity.bxh_doibong.BXHDoiBongKey;
import backend.qlgiaibongda.repository.BXHDoiBongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/test")
public class testAPI {
    @Autowired
    private BXHDoiBongRepository bxhDoiBongRepository;
    @PostMapping("")
    public ResponseEntity<ResponeObject> test()
    {
        return ResponseEntity.status(HttpStatus.OK).body(new ResponeObject("HAHAH","Authenticated HAHA","TEST"));
    }

    @GetMapping("/{id_doibong}/{id_bxh}")
    public ResponseEntity<ResponeObject> test1(@PathVariable("id_doibong") Long id_doibong, @PathVariable("id_bxh") Long id_bxh)
    {
        BXHDoiBongKey bxhDoiBongKey = new BXHDoiBongKey(id_doibong,id_bxh);
        BXHDoiBongEntity bxhDoiBongEntity = bxhDoiBongRepository.findById(bxhDoiBongKey).get();

        if(bxhDoiBongEntity!=null)
        {
            try
            {
                BXHDoiBongDTO bxhDoiBongDTO = GenericConverter.convert(bxhDoiBongEntity, BXHDoiBongDTO.class);
                DoiBongEntity doiBongEntity = bxhDoiBongEntity.getDoiBong();
                bxhDoiBongDTO.setId_doi(doiBongEntity.getId());
                bxhDoiBongDTO.setTen_doi(doiBongEntity.getTen());
                return ResponseEntity.status(HttpStatus.OK).body(new ResponeObject("HAHAH","Authenticated HAHA",bxhDoiBongDTO));
            }catch (Exception ex)
            {
                System.out.println(ex.getMessage());
            }

        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponeObject("FAIL","FAIL HAHA",""));

    }
}
