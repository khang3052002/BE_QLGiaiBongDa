package backend.qlgiaibongda.service.iplm;

import backend.qlgiaibongda.converter.GenericConverter;
import backend.qlgiaibongda.dto.ResponeObject;
import backend.qlgiaibongda.dto.SanBongDTO;
import backend.qlgiaibongda.entity.SanBongEntity;
import backend.qlgiaibongda.repository.SanBongRepository;
import backend.qlgiaibongda.service.ISanBongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SanBongService implements ISanBongService {
    @Autowired
    private SanBongRepository sanBongRepository;
    @Override
    public ResponseEntity<ResponeObject> getStadiumsNotRegistered() {
        List<SanBongEntity> listSanBongEntity = sanBongRepository.findAllByDoiBongIsNull();
        if(listSanBongEntity.size() == 0 || listSanBongEntity==null)
        {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponeObject("OK","Không tồn tại sân bóng nào",""));

        }
        else{
            List<SanBongDTO> sanBongDTOList = new ArrayList<>();
            for(SanBongEntity sanBong:listSanBongEntity)
            {
                try
                {
                    SanBongDTO sanBongDTO = GenericConverter.convert(sanBong, SanBongDTO.class);
                    sanBongDTOList.add(sanBongDTO);

                }catch (Exception ex)
                {
                    System.out.println(ex.getMessage());
                }
            }
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponeObject("OK","Danh sách sân bóng chưa được đăng ký",sanBongDTOList));
        }
    }
}
