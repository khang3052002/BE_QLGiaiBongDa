package backend.qlgiaibongda.service.iplm;

import backend.qlgiaibongda.api.input.NewTeamPlayerInput;
import backend.qlgiaibongda.converter.CauThuConverter;
import backend.qlgiaibongda.converter.GenResponse;
import backend.qlgiaibongda.converter.GenericConverter;
import backend.qlgiaibongda.dto.CauThuDTO;
import backend.qlgiaibongda.dto.PlayerFreeDTO;
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
import jakarta.persistence.EntityManager;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Date;
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
    @Autowired
    private EntityManager entityManager;


    @Override
    public CauThuDTO save(CauThuDTO cauThuDTO) {
        CauThuEntity cauThuEntity;

        cauThuEntity = cauThuConverter.toEntity(cauThuDTO);

        Long idDoi = cauThuDTO.getIdDoi();

        DoiBongEntity doiBongEntity = doiBongRepository.findById(idDoi).orElse(null);

        if(doiBongEntity == null){
            return null;
        }


        if(cauThuDTO.getViTri() != null)
        {
            String[] listVitri = cauThuDTO.getViTri();
            if(listVitri.length>0)
            {
                List<ViTriEntity> listVitriEntity = new ArrayList<>();
                Arrays.asList(listVitri).forEach(vitri->{
                    ViTriEntity viTri = viTriRepository.findByCode(vitri).orElse(null);
                    if(viTri != null)
                    {
                        listVitriEntity.add(viTri);
                    }
                });
                cauThuEntity.setCacViTri(listVitriEntity);
            }
        }


        cauThuEntity = cauThuRepository.save(cauThuEntity);

        CauThuDoiBongKey cauThuDoiBongKey = new CauThuDoiBongKey( doiBongEntity.getId(), cauThuEntity.getId());

        CauThuDoiBongEntity cauThuDoiBongEntity = new CauThuDoiBongEntity();
        cauThuDoiBongEntity.setKey(cauThuDoiBongKey);
        cauThuDoiBongEntity.setCauThuDB(cauThuEntity);
        cauThuDoiBongEntity.setDoiBongCT(doiBongEntity);
        cauThuDoiBongEntity.setThoiDiemKetThuc(cauThuDTO.getThoiDiemKetThuc());
        cauThuDoiBongEntity.setInTeam(1);
        cauThuDoiBongEntity.setTongSoBanThang(0);
        cauThuDoiBongEntity.setSoAo(cauThuDTO.getSoAo());




        cauThuDoiBongRepository.save(cauThuDoiBongEntity);


        CauThuDTO  cauThuDTORS = cauThuConverter.toDTO(cauThuEntity);

        cauThuDTORS.setThoiDiemBatDau(cauThuDoiBongEntity.getKey().getThoiDiemBatDau());
        cauThuDTORS.setThoiDiemKetThuc(cauThuDTO.getThoiDiemKetThuc());
        cauThuDTORS.setIdDoi(idDoi);
        cauThuDTORS.setSoAo(cauThuDoiBongEntity.getSoAo());
        return cauThuDTORS;
    }

    @Override
    public ResponseEntity<ResponeObject> addNewListTeamPlayer(NewTeamPlayerInput teamPlayerInput) {
        Long idDoi = teamPlayerInput.getIdDoi();

        List<CauThuDTO> newPlayers = teamPlayerInput.getDsCauThuMoi();
        List<PlayerFreeDTO> freePlayers = teamPlayerInput.getDsCauThuTuDo();
        List<CauThuDTO> result = new ArrayList<>();
        DoiBongEntity doiBongEntity = doiBongRepository.findById(idDoi).orElse(null);
        if(doiBongEntity == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponeObject("FAIL", "idDoi not found!", "") );
        }
        if(!newPlayers.isEmpty())
        {
            List<CauThuEntity> cauThuEntityList = new ArrayList<>();
            List<String> listMaDanhDanh = new ArrayList<>();
            for(CauThuDTO player:newPlayers){
                try
                {
                    CauThuEntity cauThuEntity = GenericConverter.convert(player,CauThuEntity.class);

//                CauThuDoiBongEntity ctdb = cauThuDoiBongRepository.findCauThuDoiBongEntityByCauThuDB(cauThuEntity);

                    cauThuEntityList.add(cauThuEntity);

                }catch (Exception exception)
                {
                    System.out.println(exception.getMessage());
                }

                player.setIdDoi(idDoi);
//            CauThuDTO temp = save(player);


                Boolean checkMaDinhDanh = cauThuRepository.existsByMaDinhDanh(player.getMaDinhDanh());
                if(checkMaDinhDanh)
                {
                    return GenResponse.gen(HttpStatus.CONFLICT,"FAIL","Mã định danh đã tồn tại","");
                }
                else{
                    if(listMaDanhDanh.indexOf(player.getMaDinhDanh()) ==-1)
                    {
                        listMaDanhDanh.add(player.getMaDinhDanh());
                    }
                    else{
                        return  GenResponse.gen(HttpStatus.CONFLICT,"FAIL","Thêm danh sách cầu thủ thất bại, danh sách bị trùng lặp mã định danh" + player.getMaDinhDanh() ,"");
                    }
                }
            }

            for(CauThuDTO player:newPlayers)
            {
                CauThuDTO temp = save(player);
            }

        }

        if(!freePlayers.isEmpty())
        {
            boolean checkExsist = true;
            // check hợp lệ tất cả cầu thủ trước
            for(PlayerFreeDTO player: freePlayers)
            {
                // check tất cả cầu thủ tồn tại và cầu thủ đó là "Tự do" trước
                CauThuEntity cauThuEntity = cauThuRepository.findById(player.getId_cauthu()).orElse(null);
                if(cauThuEntity == null)
                {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponeObject("FAIL", "Không tổn tại cầu thủ "+cauThuEntity.getHoTen()+", Thêm cầu thủ tự do thất bại", "") );

                }

                if(cauThuEntity.getTrangThai().equals("Tự do") == false)
                {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND)
                            .body(new ResponeObject("FAIL", "Không tổn tại cầu thủ "+cauThuEntity.getHoTen()+" với trạng thái tự do, Thêm cầu thủ tự do thất bại", "") );
                }

                // hợp lệ
                // check exsists in team old



            }

            // sau khi check hợp lệ, tiến hành xử lí và thêm
            List<CauThuEntity> cauThuEntityList = new ArrayList<>();
            List<CauThuDoiBongEntity> cauThuDoiBongEntities = new ArrayList<>();
            for(PlayerFreeDTO player: freePlayers)
            {
                CauThuEntity cauThuEntity = cauThuRepository.findById(player.getId_cauthu()).orElse(null);
                CauThuDoiBongEntity cauThuDoiBong = cauThuDoiBongRepository.findCauThuDoibongEntityByCauThuDBAndDoiBongCTAndInTeam(cauThuEntity,doiBongEntity,0);

                cauThuEntity.setTrangThai("Thi đấu");
                if(cauThuDoiBong !=null) // tồn tại ở team cũ
                {

                    cauThuDoiBong.setInTeam(1);
//                    cauThuDoiBong.getKey().setThoiDiemBatDau(new Date(System.currentTimeMillis()));
                    cauThuDoiBong.setThoiDiemKetThuc(player.getThoiDiemKetThuc());

//                    cauThuDoiBongEntities.add(cauThuDoiBong);
                    cauThuDoiBongRepository.save(cauThuDoiBong);


                }
                else{
                    // set key cho CauThuDoiBong
                    CauThuDoiBongKey cauThuDoiBongKey = new CauThuDoiBongKey( doiBongEntity.getId(), cauThuEntity.getId());
                    CauThuDoiBongEntity cauThuDoiBongEntity = new CauThuDoiBongEntity();
                    cauThuDoiBongEntity.setKey(cauThuDoiBongKey);
                    cauThuDoiBongEntity.setCauThuDB(cauThuEntity);
                    cauThuDoiBongEntity.setDoiBongCT(doiBongEntity);
                    cauThuDoiBongEntity.setThoiDiemKetThuc(player.getThoiDiemKetThuc());
                    cauThuDoiBongEntity.setInTeam(1);
                    cauThuDoiBongEntity.setTongSoBanThang(0);

//                    cauThuDoiBongEntities.add(cauThuDoiBongEntity);
                    cauThuDoiBongRepository.save(cauThuDoiBongEntity);

                }
//                cauThuEntityList.add(cauThuEntity);

                cauThuRepository.save(cauThuEntity);
            }
//            cauThuRepository.saveAll(cauThuEntityList);
//            cauThuDoiBongRepository.saveAll(cauThuDoiBongEntities);



        }


        return ResponseEntity.status(HttpStatus.OK).body(new ResponeObject("OK", "Add all players succeed", result));
    }

    @Override
    public ResponseEntity<ResponeObject> getPlayerByID(Long id) {


        CauThuEntity cauthu = cauThuRepository.findById(id).orElse(null);
        if(cauthu != null)
        {
            try
            {
                CauThuDTO cauThuDTO = GenericConverter.convert(cauthu, CauThuDTO.class);

                List<CauThuDoiBongEntity> listDoiBong = cauthu.getCauThuDoiBong();

                CauThuDoiBongEntity doiBongEntity =  cauThuDoiBongRepository.findCauThuDoiBongEntityByCauThuDBAndInTeam(cauthu,1);
                if(doiBongEntity != null)
                {
                    cauThuDTO.setIdDoi(doiBongEntity.getDoiBongCT().getId());
                    cauThuDTO.setSoAo(doiBongEntity.getSoAo());
                }
                List<ViTriEntity> listVitriEntity =  cauthu.getCacViTri();
                List<String> listVitri = new ArrayList<>();
                listVitriEntity.forEach(viTriEntity -> {
                    listVitri.add(viTriEntity.getCode());
                });
                cauThuDTO.setViTri(listVitri.toArray(new String[0]));
//                cauThuDTO.setAge(cauthu.calculateAge());
//                System.out.println(cauthu.calculateAge());
                int age = cauthu.calculateAge();
                cauThuDTO.setAge(age);
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
                ViTriEntity viTri = viTriRepository.findByCode(vitri).orElse(null);
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
        CauThuEntity cauThuEntity = cauThuRepository.findById(cauThuDTO.getId()).orElse(null);
        DoiBongEntity doiBongEntity = doiBongRepository.findById(cauThuDTO.getIdDoi()).orElse(null);
        CauThuDoiBongEntity cauThuDoiBongEntity = cauThuDoiBongRepository.findCauThuDoiBongEntityByCauThuDBAndDoiBongCT(cauThuEntity,doiBongEntity);

        if(cauThuDoiBongEntity != null)
        {
            try
            {
//                cauThuEntity = GenericConverter.convert(cauThuDTO, CauThuEntity.class);
                cauThuEntity.setHoTen(cauThuDTO.getHoTen());
                cauThuEntity.setNgaySinh(cauThuDTO.getNgaySinh());
                cauThuEntity.setQuocTich(cauThuDTO.getQuocTich());
                cauThuEntity.setHinhAnh(cauThuDTO.getHinhAnh());
                cauThuEntity.setQueQuan(cauThuDTO.getQueQuan());
//                cauThuEntity.setTrangThai(cauD);
//                cauThuEntity.setTrangThai(cauThuDTO.getTrangThai());
                cauThuEntity.setLoaiCauThu(cauThuDTO.getLoaiCauThu());

                cauThuDoiBongEntity.setSoAo(cauThuDTO.getSoAo());

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
                cauThuDoiBongRepository.save(cauThuDoiBongEntity);
                cauThuDTO.setMaDinhDanh(cauThuEntity.getMaDinhDanh());
                cauThuDTO.setTrangThai(cauThuEntity.getTrangThai());
                return ResponseEntity.status(HttpStatus.OK).body(new ResponeObject("OK","Update player succesful",cauThuDTO));

            }catch (Exception ex)
            {
                System.out.println(ex.getMessage());
            }
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponeObject("FAIL","Update player fail",""));

    }

    @Override
    public ResponseEntity<ResponeObject> searchAllPlayerByNameOrRole(String keyword, String role) {
        try {
            // call procedure
            StoredProcedureQuery spQuery =
                    entityManager.createNamedStoredProcedureQuery("searchAllPlayerByNameOrRole")
                            .setParameter("keyword", keyword)
                            .setParameter("roles", role);
            List<CauThuEntity> listCauThuEntity = spQuery.getResultList();
            if (listCauThuEntity.size() > 0) {
                List<CauThuDTO> listPlayerDto = new ArrayList<>();
                for (CauThuEntity cauThu : listCauThuEntity) {
                    try {

                        // thiếu so thời gian kết thúc với hiện tại
                        CauThuDTO cauThuDTO = GenericConverter.convert(cauThu, CauThuDTO.class);
                        List<ViTriEntity> listVitri = cauThu.getCacViTri();
                        List<String> str_roles = new ArrayList<>();
                        listVitri.forEach(vitri -> {
                            str_roles.add(vitri.getCode());
                        });
                        CauThuDoiBongEntity cauThuDoiBongEntity = cauThuDoiBongRepository.findCauThuDoiBongEntityByCauThuDBAndInTeam(cauThu,1);
                        if(cauThuDoiBongEntity!=null)
                        {
                            cauThuDTO.setIdDoi(cauThuDoiBongEntity.getDoiBongCT().getId());
                            cauThuDTO.setSoAo(cauThuDoiBongEntity.getSoAo());

                        }

                        cauThuDTO.setViTri(str_roles.toArray(new String[0]));
                        cauThuDTO.setAge(cauThu.calculateAge());

                        listPlayerDto.add(cauThuDTO);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                                .body(new ResponeObject("FAIL", "FAIL", e.getMessage()));
                    }
                }

                return ResponseEntity.status(HttpStatus.OK)
                        .body(new ResponeObject("ok", "Get list succeed", listPlayerDto));
            }
        }
        catch (Exception ex)
        {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponeObject("FAIL", "FAIL", ex.getMessage()));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ResponeObject("FAIL","No player exists", ""));

    }

    @Override
    public ResponseEntity<ResponeObject> getPlayerFree() {
        List<CauThuEntity> cauThuEntityList = cauThuRepository.findAllByTrangThai("Tự do");
        List<CauThuDTO> listPlayerDto = new ArrayList<>();

        if (cauThuEntityList.size() > 0) {
            for (CauThuEntity cauThu : cauThuEntityList) {
                try {

                    // thiếu so thời gian kết thúc với hiện tại
                    CauThuDTO cauThuDTO = GenericConverter.convert(cauThu, CauThuDTO.class);
                    List<ViTriEntity> listVitri = cauThu.getCacViTri();
                    List<String> str_roles = new ArrayList<>();
                    listVitri.forEach(vitri -> {
                        str_roles.add(vitri.getCode());
                    });

//                        cauThuDTO.setIdDoi(idTeam);
                    cauThuDTO.setViTri(str_roles.toArray(new String[0]));
                    cauThuDTO.setAge(cauThu.calculateAge());

                    listPlayerDto.add(cauThuDTO);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                            .body(new ResponeObject("FAIL", "FAIL", e.getMessage()));
                }
            }

            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponeObject("ok", "Get list succeed", listPlayerDto));
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponeObject("ok", "Không tồn tại cầu thủ nào", listPlayerDto));
    }
}
