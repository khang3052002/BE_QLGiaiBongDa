package backend.qlgiaibongda.service.iplm;

import backend.qlgiaibongda.api.input.NewTeamPlayerInput;
import backend.qlgiaibongda.converter.CauThuConverter;
import backend.qlgiaibongda.converter.GenericConverter;
import backend.qlgiaibongda.dto.CauThuDTO;
import backend.qlgiaibongda.dto.ResponeObject;
import backend.qlgiaibongda.entity.CauThuEntity;
import backend.qlgiaibongda.entity.DoiBongEntity;
import backend.qlgiaibongda.entity.ViTriEntity;
import backend.qlgiaibongda.entity.cauthu_doibong.CauThuDoiBongEntity;
import backend.qlgiaibongda.entity.cauthu_doibong.CauThuDoiBongKey;
import backend.qlgiaibongda.repository.CauThuDoiBongRepository;
import backend.qlgiaibongda.repository.CauThuRepository;
import backend.qlgiaibongda.repository.DoiBongRepository;
import backend.qlgiaibongda.repository.ViTriRepository;
import backend.qlgiaibongda.service.ICauThuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    @Autowired
    private ViTriRepository viTriRepository;


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

    @Override
    public ResponseEntity<ResponeObject> addNewListTeamPlayer(NewTeamPlayerInput teamPlayerInput) {
        Long idDoi = teamPlayerInput.getIdDoi();
        List<CauThuDTO> players = teamPlayerInput.getDsCauThuMoi();
        List<CauThuDTO> result = new ArrayList<>();

        for(CauThuDTO player:players){
            player.setIdDoi(idDoi);
            CauThuDTO temp = save(player);
            if(temp == null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponeObject("FAIL", "idDoi not found!", "") );
            }else{
                result.add(temp);
            }
        }

        return ResponseEntity.status(HttpStatus.OK).body(new ResponeObject("OK", "Add all new players succeed", result));
    }

    @Override
    public ResponseEntity<ResponeObject> getPlayerByID(Long id) {


        CauThuEntity cauthu = cauThuRepository.findById(id).get();
        if(cauthu != null)
        {
            try
            {
                CauThuDTO cauThuDTO = GenericConverter.convert(cauthu, CauThuDTO.class);
                List<ViTriEntity> listVitriEntity =  cauthu.getCacViTri();
                List<String> listVitri = new ArrayList<>();
                listVitriEntity.forEach(viTriEntity -> {
                    listVitri.add(viTriEntity.getCode());
                });
                cauThuDTO.setViTri(listVitri.toArray(new String[0]));
                return ResponseEntity.status(HttpStatus.OK).body(new ResponeObject("OK","Get player succesfull",cauThuDTO));

            }
            catch (Exception ex)
            {
                System.out.println(ex.getMessage());
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponeObject("FAIL","Player not exsists",""));


    }

    @Override
    public ResponseEntity<ResponeObject> addNewPlayer(CauThuDTO cauThuDTO)  {
        try
        {
            CauThuEntity cauThuEntity = GenericConverter.convert(cauThuDTO,CauThuEntity.class);
            String[] listViTri = cauThuDTO.getViTri();
            List<ViTriEntity> listVitriEntity = new ArrayList<>();
            Arrays.asList(listViTri).forEach(vitri -> {
                ViTriEntity viTri = viTriRepository.findByCode(vitri).get();
                if(viTri != null)
                {
                    listVitriEntity.add(viTri);
                }
            });
            cauThuEntity.setCacViTri(listVitriEntity);
            cauThuRepository.save(cauThuEntity);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponeObject("OK","Add player succesful",cauThuDTO));
        }catch (Exception ex)
        {
            System.out.println(ex.getMessage());

        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponeObject("FAIL","Add player FAIL",cauThuDTO));

    }

    @Override
    public ResponseEntity<ResponeObject> editPlayer(CauThuDTO cauThuDTO) {
        CauThuEntity cauThuEntity = cauThuRepository.findById(cauThuDTO.getId()).get();
        if(cauThuEntity != null)
        {
            try
            {
//                cauThuEntity = GenericConverter.convert(cauThuDTO, CauThuEntity.class);
                cauThuEntity.setHoTen(cauThuDTO.getHoTen());
                cauThuEntity.setNgaySinh(cauThuDTO.getNgaySinh());
                cauThuEntity.setQuocTich(cauThuDTO.getQuocTich());
                cauThuEntity.setHinhAnh(cauThuDTO.getHinhAnh());
                cauThuEntity.setQueQuan(cauThuDTO.getQueQuan());
                cauThuEntity.setTrangThai(cauThuDTO.getTrangThai());
                cauThuEntity.setLoaiCauThu(cauThuDTO.getLoaiCauThu());

                if(cauThuDTO.getViTri() != null) // cập nhật vị trí
                {
                    String[] listVitri = cauThuDTO.getViTri();
                    if(listVitri.length>0)
                    {
                        cauThuEntity.getCacViTri().clear();
                        List<ViTriEntity> listVitriEntity = new ArrayList<>();
                        Arrays.asList(listVitri).forEach(vitri->{
                            ViTriEntity viTri = viTriRepository.findByCode(vitri).get();
                            if(viTri != null)
                            {
                                listVitriEntity.add(viTri);
                            }
                        });
                        cauThuEntity.setCacViTri(listVitriEntity);
                    }
                }
                cauThuRepository.save(cauThuEntity);
                return ResponseEntity.status(HttpStatus.OK).body(new ResponeObject("OK","Update player succesful",cauThuDTO));

            }catch (Exception ex)
            {
                System.out.println(ex.getMessage());
            }
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponeObject("FAIL","Update player fail",""));

    }
}
