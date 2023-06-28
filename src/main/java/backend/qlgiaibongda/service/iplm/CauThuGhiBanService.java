package backend.qlgiaibongda.service.iplm;

import backend.qlgiaibongda.converter.GenResponse;
import backend.qlgiaibongda.dto.CauThuGhiBanDTO;
import backend.qlgiaibongda.dto.ResponeObject;
import backend.qlgiaibongda.entity.CauThuGhiBan.CauThuGhiBanEntity;
import backend.qlgiaibongda.entity.MuaGiaiEntity;
import backend.qlgiaibongda.repository.CauThuGhiBanRepository;
import backend.qlgiaibongda.repository.MuaGiaiRepository.MuaGiaiRepository;
import backend.qlgiaibongda.service.ICauThuGhiBanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CauThuGhiBanService implements ICauThuGhiBanService {

    @Autowired
    private CauThuGhiBanRepository cauThuGhiBanRepository;

    @Autowired
    private MuaGiaiRepository muaGiaiRepository;
    @Override
    public ResponseEntity<ResponeObject> layDSCauThuGhiBan(Long idMuaGiai) {
        MuaGiaiEntity muaGiaiEntity = muaGiaiRepository.findById(idMuaGiai).orElse(null);

        if(muaGiaiEntity == null){
            return GenResponse.gen(HttpStatus.NOT_FOUND, "FAIL", "MuaGiai not found!", "");
        }

        List<CauThuGhiBanEntity> dsCauThuGhiBan = muaGiaiEntity.getCacCauThuGhiBan();



        List<CauThuGhiBanDTO> dsCauThuGhiBanDTO = new ArrayList<>();

        for(CauThuGhiBanEntity cauThuGhiBan: dsCauThuGhiBan){
            CauThuGhiBanDTO cauThuGhiBanDTO = new CauThuGhiBanDTO();
//            cauThuGhiBanDTO.setId(cauThuGhiBan.getId());
            cauThuGhiBanDTO.setIdCauThu(cauThuGhiBan.getCauThu().getId());
            cauThuGhiBanDTO.setTenCauThu(cauThuGhiBan.getCauThu().getHoTen());
            cauThuGhiBanDTO.setIdDoi(cauThuGhiBan.getDoiBong().getId());
            cauThuGhiBanDTO.setTenDoi(cauThuGhiBan.getDoiBong().getTen());
            cauThuGhiBanDTO.setSoLuongBanThang(cauThuGhiBan.getSoBanThang());
            dsCauThuGhiBanDTO.add(cauThuGhiBanDTO);
        }

        dsCauThuGhiBanDTO.sort((c1,c2)->{
            return c1.getSoLuongBanThang() - c2.getSoLuongBanThang();
        });


        return GenResponse.gen(HttpStatus.OK, "OK", "Lay danh sach cau thu ghi ban thanh cong!", dsCauThuGhiBanDTO);
    }
}
