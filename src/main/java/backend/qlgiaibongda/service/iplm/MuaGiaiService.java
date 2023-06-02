package backend.qlgiaibongda.service.iplm;

import backend.qlgiaibongda.converter.GenericConverter;
import backend.qlgiaibongda.dto.MuaGiaiDTO;
import backend.qlgiaibongda.dto.QuyDinhDTO.QuyDinhCauThuDTO;
import backend.qlgiaibongda.dto.QuyDinhDTO.QuyDinhTinhDiemDTO;
import backend.qlgiaibongda.dto.ResponeObject;
import backend.qlgiaibongda.entity.*;
import backend.qlgiaibongda.repository.*;
import backend.qlgiaibongda.service.IMuaGiaiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MuaGiaiService implements IMuaGiaiService {
    @Autowired
    private QuanLiRepository quanLiRepository;
    @Autowired
    private MuaGiaiRepository muaGiaiRepository;
    @Autowired
    private QuyDinhMuaGiaiRepository quyDinhMuaGiaiRepository;
    @Autowired
    private QuyDinhCauThuRepository quyDinhCauThuRepository;
    @Autowired
    private QuyDinhTinhDiemRepository quyDinhTinhDiemRepository;

    @Override
    public ResponseEntity<ResponeObject> createLeague(MuaGiaiDTO muaGiaiDTO) {
        try
        {
            MuaGiaiEntity muaGiaiEntity = GenericConverter.convert(muaGiaiDTO, MuaGiaiEntity.class);
            QuanLyEntity quanLyMuaGiai = quanLiRepository.findById(muaGiaiDTO.getId_nguoitao()).get();
            if(quanLyMuaGiai != null)
            {
                muaGiaiEntity.setQuanLyMuaGiai(quanLyMuaGiai);
            }

            QuyDinhCauThuEntity quyDinhCauThuEntity = GenericConverter.convert(muaGiaiDTO.getQuyDinhCauThu(), QuyDinhCauThuEntity.class);
            QuyDinhTinhDiemEntity quyDinhTinhDiemEntity = GenericConverter.convert(muaGiaiDTO.getQuyDinhTinhDiem(), QuyDinhTinhDiemEntity.class);

            quyDinhCauThuRepository.save(quyDinhCauThuEntity);
            quyDinhTinhDiemRepository.save(quyDinhTinhDiemEntity);

            // Set quy dinh cho mua giai
            QuyDinhMuaGiaiEntity quyDinhMuaGiai = new QuyDinhMuaGiaiEntity();

            quyDinhMuaGiai.setQuyDinhCauThu(quyDinhCauThuEntity);
            quyDinhMuaGiai.setQuyDinhTinhDiem(quyDinhTinhDiemEntity);

            quyDinhMuaGiaiRepository.save(quyDinhMuaGiai);

            muaGiaiEntity.setQuyDinhMuaGiai(quyDinhMuaGiai);

            muaGiaiRepository.save(muaGiaiEntity);

            return ResponseEntity.status(HttpStatus.OK).body(new ResponeObject("OK","Create league succesful",muaGiaiDTO));


        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ResponeObject("FAIL","Create league fail",""));


    }
}
