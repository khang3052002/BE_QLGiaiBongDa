package backend.qlgiaibongda.service.iplm;

import backend.qlgiaibongda.converter.CauThuConverter;
import backend.qlgiaibongda.dto.CauThuDTO;
import backend.qlgiaibongda.entity.CauThuEntity;
import backend.qlgiaibongda.entity.DoiBongEntity;
import backend.qlgiaibongda.entity.cauthu_doibong.CauThuDoiBongEntity;
import backend.qlgiaibongda.entity.cauthu_doibong.CauThuDoiBongKey;
import backend.qlgiaibongda.repository.CauThuDoiBongRepository;
import backend.qlgiaibongda.repository.CauThuRepository;
import backend.qlgiaibongda.repository.DoiBongRepository;
import backend.qlgiaibongda.service.ICauThuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CauThuService implements ICauThuService {

    @Autowired
    private CauThuRepository cauThuRepository;

    @Autowired
    private DoiBongRepository doiBongRepository;

    @Autowired
    private CauThuDoiBongRepository cauThuDoiBongRepository;

    @Autowired
    private CauThuConverter cauThuConverter;



    @Override
    public CauThuDTO save(CauThuDTO cauThuDTO) {
        CauThuEntity cauThuEntity;

        cauThuEntity = cauThuConverter.toEntity(cauThuDTO);

        Long idDoi = cauThuDTO.getIdDoi();

        DoiBongEntity doiBongEntity = doiBongRepository.findById(idDoi).orElse(null);

        if(doiBongEntity == null){
            return null;
        }

        cauThuEntity = cauThuRepository.save(cauThuEntity);

        CauThuDoiBongKey cauThuDoiBongKey = new CauThuDoiBongKey( doiBongEntity.getId(), cauThuEntity.getId());

        CauThuDoiBongEntity cauThuDoiBongEntity = new CauThuDoiBongEntity();
        cauThuDoiBongEntity.setKey(cauThuDoiBongKey);
        cauThuDoiBongEntity.setCauThuDB(cauThuEntity);
        cauThuDoiBongEntity.setDoiBongCT(doiBongEntity);
        cauThuDoiBongEntity.setThoiDiemKetThuc(cauThuDTO.getThoiDiemKetThuc());
        cauThuDoiBongEntity.setTongSoBanThang(0);


        cauThuDoiBongRepository.save(cauThuDoiBongEntity);



        CauThuDTO  cauThuDTORS = cauThuConverter.toDTO(cauThuEntity);

        cauThuDTORS.setThoiDiemBatDau(cauThuDoiBongEntity.getKey().getThoiDiemBatDau());
        cauThuDTORS.setThoiDiemKetThuc(cauThuDTO.getThoiDiemKetThuc());
        cauThuDTORS.setIdDoi(idDoi);

        return cauThuDTORS;
    }
}
