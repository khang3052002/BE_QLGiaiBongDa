package backend.qlgiaibongda.service.iplm;

import backend.qlgiaibongda.converter.GenResponse;
import backend.qlgiaibongda.converter.GenericConverter;
import backend.qlgiaibongda.dto.QuyDinhDTO.QuyDinhCauThuDTO;
import backend.qlgiaibongda.dto.QuyDinhDTO.QuyDinhMuaGiaiDTO;
import backend.qlgiaibongda.dto.QuyDinhDTO.QuyDinhSoLuongDoiDTO;
import backend.qlgiaibongda.dto.QuyDinhDTO.QuyDinhTinhDiemDTO;
import backend.qlgiaibongda.dto.ResponeObject;
import backend.qlgiaibongda.entity.MuaGiaiEntity;
import backend.qlgiaibongda.entity.QuyDinhMuaGiaiEntity;
import backend.qlgiaibongda.repository.QuyDinhMuaGiaiRepository;
import backend.qlgiaibongda.service.IQuyDinhMuaGiaiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuyDinhMuaGiaiService implements IQuyDinhMuaGiaiService {
    @Autowired
    private QuyDinhMuaGiaiRepository quyDinhMuaGiaiRepository;
    @Override
    public ResponseEntity<ResponeObject> getQuyDinhCuaAllMuaGiai() {
        Pageable pageable = PageRequest.of(0, 5, Sort.by(Sort.Direction.DESC, "id"));
        List<QuyDinhMuaGiaiEntity> listQuyDinhMuaGiai = quyDinhMuaGiaiRepository.findAll(pageable).getContent();
//        List<QuyDinhMuaGiaiEntity> listQuyDinhMuaGiai = quyDinhMuaGiaiRepository.findAll();

        if(listQuyDinhMuaGiai.size()>0)
        {
            List<QuyDinhMuaGiaiDTO> listQuyDinhMuaGiaiDTO = new ArrayList<>();
            for(QuyDinhMuaGiaiEntity quyDinhMuaGiai: listQuyDinhMuaGiai)
            {
                try
                {
                    QuyDinhMuaGiaiDTO quyDinhMuaGiaiDTO = new QuyDinhMuaGiaiDTO();

                    quyDinhMuaGiaiDTO.setId_quydinh(quyDinhMuaGiai.getId());
                    if(quyDinhMuaGiai.getQuyDinhTinhDiem() == null || quyDinhMuaGiai.getQuyDinhCauThu() == null || quyDinhMuaGiai.getQuyDinhSoLuongDoi()==null)
                    {
                        continue;
                    }
                    QuyDinhTinhDiemDTO quyDinhTinhDiemDTO = GenericConverter.convert(quyDinhMuaGiai.getQuyDinhTinhDiem(),QuyDinhTinhDiemDTO.class);
                    QuyDinhCauThuDTO quyDinhCauThuDTO = GenericConverter.convert(quyDinhMuaGiai.getQuyDinhCauThu(),QuyDinhCauThuDTO.class);
                    QuyDinhSoLuongDoiDTO quyDinhSoLuongDoiDTO = GenericConverter.convert(quyDinhMuaGiai.getQuyDinhSoLuongDoi(),QuyDinhSoLuongDoiDTO.class);

                    quyDinhMuaGiaiDTO.setQuyDinhCauThuDTO(quyDinhCauThuDTO);
                    quyDinhMuaGiaiDTO.setQuyDinhTinhDiemDTO(quyDinhTinhDiemDTO);
                    quyDinhMuaGiaiDTO.setQuyDinhSoLuongDoiDTO(quyDinhSoLuongDoiDTO);

                    // xu li mua giai
                    List<MuaGiaiEntity> listMuaGiai = quyDinhMuaGiai.getCacMuaGiai();

                    MuaGiaiEntity muaGiaiEntity = listMuaGiai.get(listMuaGiai.size()-1);

                    quyDinhMuaGiaiDTO.setTen_giai(muaGiaiEntity.getTen());
                    quyDinhMuaGiaiDTO.setId_muagiai(muaGiaiEntity.getId());

                    listQuyDinhMuaGiaiDTO.add(quyDinhMuaGiaiDTO);
                }catch (Exception ex)
                {
                    System.out.println(ex.getMessage());
                }
            }

            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponeObject("OK","Danh sách quy định của các mùa giải",listQuyDinhMuaGiaiDTO)

            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponeObject("FAIL","Chưa tồn tại quy định mùa giải nào","")

        );
    }
}
