package backend.qlgiaibongda.service.iplm;

import backend.qlgiaibongda.api.input.NewTeamInput;
import backend.qlgiaibongda.api.input.UpdateTeamInput;
import backend.qlgiaibongda.api.output.ErrorOutput;
import backend.qlgiaibongda.converter.GenericConverter;
import backend.qlgiaibongda.dto.*;
import backend.qlgiaibongda.entity.*;
import backend.qlgiaibongda.entity.cauthu_doibong.CauThuDoiBongEntity;
import backend.qlgiaibongda.repository.*;
import backend.qlgiaibongda.service.ITeamService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.StoredProcedureQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


@Service
public class TeamService implements ITeamService {

    @Autowired
    private DoiBongRepository doiBongRepository;

    @Autowired
    private QuanLiRepository quanLiRepository;

    @Autowired
    private SanBongRepository sanBongRepository;
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private CauThuRepository cauThuRepository;

    @Autowired
    private CauThuDoiBongRepository cauThuDoiBongRepository;
    @Override
    public List<TeamDTO> findAll(Pageable pageable) {
        List<TeamDTO> results = new ArrayList<>();
        List<DoiBongEntity> entities = doiBongRepository.findAll(pageable).getContent();
        for(DoiBongEntity entity: entities){
                TeamDTO teamDTO = convertToTeamDTO(entity);
                if(teamDTO != null){
                    results.add(teamDTO);
                }
        }
        return results;
    }

    @Override
    public int totalItem() {
        return (int)doiBongRepository.count();
    }

    @Override
    public ResponseEntity<ResponeObject> findById(Long id) {
        TeamDTO result = null;
        DoiBongEntity entity = doiBongRepository.findById(id).orElse(null);
        if(entity!=null){
            result = convertToTeamDTO(entity);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponeObject("FAIL", "Team not found", ""));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ResponeObject("OK", "Get team succeed!", result));
    }

    @Override
    public ResponseEntity<ResponeObject> save(NewTeamInput newTeamInput) {
        Long idQuanLy = newTeamInput.getIdQuanLy();
        Long idSanNha = newTeamInput.getIdSanNha();

        QuanLyEntity quanLyEntity = quanLiRepository.findById(idQuanLy).orElse(null);
        if(quanLyEntity == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponeObject("FAIL", "No Manager has this id!", ""));

        }else{
            if(quanLyEntity.getDoiBong() != null){
                return ResponseEntity.status(HttpStatus.CONFLICT).body(new ResponeObject("FAIL", "This Manager is managing another team!", ""));

            }else{
                if(quanLyEntity.getVaiTro().getCode().equals("QLGD")){
                    return ResponseEntity.status(HttpStatus.CONFLICT).body(new ResponeObject("FAIL", "This Manager is not allowed to manage football team!", ""));

                }
            }
        }

        SanBongEntity sanBongEntity = sanBongRepository.findById(idSanNha).orElse(null);
        if(sanBongEntity == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponeObject("FAIL", "No SanBong has this id!", ""));

        }else{
            DoiBongEntity entity = doiBongRepository.findBySanBongId(idSanNha).orElse(null);
            if(entity != null){
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body(new ResponeObject("FAIL", "SanBong has been owned by another team!", ""));

            }
        }

        DoiBongEntity entity = null;

        try {
            entity = GenericConverter.convert(newTeamInput, DoiBongEntity.class);
            entity.setSanBong(sanBongEntity);
            entity.setQuanLy(quanLyEntity);
            doiBongRepository.save(entity);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new ResponeObject("FAIL", "Invalid Info!", ""));
        }


        TeamDTO  teamDTO = convertToTeamDTO(entity);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponeObject("OK", "Add new Team succeed", teamDTO));
    }

    @Override
    public ResponseEntity<ResponeObject> updateTeam(UpdateTeamInput updateTeamInput) {



        DoiBongEntity doiBongEntity = doiBongRepository.findById(updateTeamInput.getId()).orElse(null);
        if(doiBongEntity == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponeObject("FAIL", "Team not found!", ""));
        }

        try {
            doiBongEntity = GenericConverter.convert(updateTeamInput,doiBongEntity);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponeObject("FAIL", "Invalid Info", ""));
        }

        QuanLyEntity oldQuanLy = doiBongEntity.getQuanLy();

        if(oldQuanLy.getId() != updateTeamInput.getIdQuanLy()){

            Long idQuanLyNew = updateTeamInput.getIdQuanLy();
            QuanLyEntity quanLyEntity = quanLiRepository.findById(idQuanLyNew).orElse(null);

            if(quanLyEntity == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponeObject("FAIL", "No Manager has this id!", ""));
            }else{
                if(quanLyEntity.getDoiBong() !=null){
                    return ResponseEntity.status(HttpStatus.CONFLICT).body(new ResponeObject("FAIL", "This Manager is managing another team!", ""));

                }else{

                    if(quanLyEntity.getVaiTro().getCode().equals("QLGD")){
                        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ResponeObject("FAIL", "This Manager is not allowed to manage football team!", ""));

                    }

                    oldQuanLy.setDoiBong(null);
                    quanLiRepository.save(oldQuanLy);

                    doiBongEntity.setQuanLy(quanLyEntity);
                    quanLyEntity.setDoiBong(doiBongEntity);
                    quanLiRepository.save(quanLyEntity);
                }
            }
        }


        SanBongEntity oldSanBong = doiBongEntity.getSanBong();
        if(oldSanBong.getId() != updateTeamInput.getIdSanNha()){

            Long idSanBongNew = updateTeamInput.getId();
            SanBongEntity sanBongEntity = sanBongRepository.findById(idSanBongNew).orElse(null);


            if(sanBongEntity == null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ResponeObject("FAIL", "No SanBong has this id!", ""));

            }else{
                if(sanBongEntity.getDoiBong() !=null){
                    return ResponseEntity.status(HttpStatus.CONFLICT).body(new ResponeObject("FAIL", "SanBong has been owned by another team!", ""));

                }else{
                    oldSanBong.setDoiBong(null);
                    sanBongRepository.save(oldSanBong);

                    doiBongEntity.setSanBong(sanBongEntity);
                    sanBongEntity.setDoiBong(doiBongEntity);
                    sanBongRepository.save(sanBongEntity);
                }

            }
        }
        doiBongRepository.save(doiBongEntity);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponeObject("OK", "Update team succeed", updateTeamInput));

    }

    @Override
    public ResponseEntity<ResponeObject> getPlayerOfTeamWithRoles(Long idTeam, String roles) {
        try
        {
            // call procedure
            StoredProcedureQuery spQuery =
                    entityManager.createNamedStoredProcedureQuery("getPlayerInTeamWithPosition")
                            .setParameter("id_team",idTeam)
                            .setParameter("roles",roles);
            List<CauThuEntity> listCauThuEntity = spQuery.getResultList();
            if(listCauThuEntity.size()>0)
            {
                List<CauThuDTO> listPlayerDto = new ArrayList<>();
                listCauThuEntity.forEach(cauThu ->{
                    try {

                        // thiếu so thời gian kết thúc với hiện tại
                        // đã check trong lúc gọi procedure
                        CauThuDTO cauThuDTO = GenericConverter.convert(cauThu, CauThuDTO.class);
                        List<ViTriEntity> listVitri = cauThu.getCacViTri();
                        List<String> str_roles = new ArrayList<>();
                        listVitri.forEach(vitri->{
                            str_roles.add(vitri.getCode());
                        });

                        cauThuDTO.setIdDoi(idTeam);
                        cauThuDTO.setViTri(str_roles.toArray(new String[0]));

                        listPlayerDto.add(cauThuDTO);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                });
                return ResponseEntity.status(HttpStatus.OK)
                        .body(new ResponeObject("ok","Get list succeed", listPlayerDto));
            }
            //example
//            List<CauThuEntity> list = doiBongRepository.getListCauThu();
//            List<Object> listPlayerEntity = doiBongRepository.getPlayerInTeamWithRole(idTeam,"haha",roles);
//            String sqlQuery = "select distinct player.* from cauthu player, cauthu_doibong ctdb, vitri vt, cauthu_vitri ctvt, doibong db " +
//                    "where ctdb.id_doibong = 1 and player.id = ctdb.id_cauthu and db.id = ctdb.id_doibong " +
//                    "and ctvt.id_vitri = vt.id and player.id = ctvt.id_cauthu and ( vt.code = 'CAM')";

//            Query query = entityManager.createNativeQuery(sqlQuery, CauThuEntity.class);
//            List<CauThuEntity> cauThuEntities = query.getResultList();

        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ResponeObject("FAIL","No player exists", ""));

    }

    @Override
    public ResponseEntity<ResponeObject> getPlayerOfTeam(Long idTeam) {
        DoiBongEntity doiBongEntity = doiBongRepository.findById(idTeam).orElse(null);
        if(doiBongEntity !=null)
        {
            List<CauThuDoiBongEntity> cauThuDoiBongEntities = doiBongEntity.getDoiBongCauThu();

            if(cauThuDoiBongEntities == null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponeObject("EMPTY","Team don't have any player",""));
            }

            List<CauThuDTO> players = new ArrayList<>();
            cauThuDoiBongEntities.forEach((ctdb)->{
                try {

                    if(ctdb.getThoiDiemKetThuc().compareTo(new Date(System.currentTimeMillis())) > 0 && ctdb.isInTeam() == 1){
                        CauThuDTO cauThuDTO = GenericConverter.convert(ctdb.getCauThuDB(), CauThuDTO.class);
                        cauThuDTO.setThoiDiemBatDau(ctdb.getKey().getThoiDiemBatDau());
                        cauThuDTO.setThoiDiemKetThuc(ctdb.getThoiDiemKetThuc());
                        cauThuDTO.setTongSoBanThang(ctdb.getTongSoBanThang());
                        cauThuDTO.setIdDoi(ctdb.getDoiBongCT().getId());
                        List<ViTriEntity> listVitri = ctdb.getCauThuDB().getCacViTri();
                        List<String> str_roles = new ArrayList<>();
                        listVitri.forEach(vitri->{
                            str_roles.add(vitri.getCode());
                        });
                        cauThuDTO.setViTri(str_roles.toArray(new String[0]));
                        players.add(cauThuDTO);
                    }

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            });
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponeObject("ok","Get list succeed", players));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ResponeObject("Fail","Không tồn tại đội bóng", ""));



    }

    @Override
    public ResponseEntity<ResponeObject> kickPlayerInTeam(RequestKickPlayerInTeamDTO requestKickPlayerInTeamDTO) {
        Long id_cauthu = requestKickPlayerInTeamDTO.getId_cauthu();
        Long id_doibong = requestKickPlayerInTeamDTO.getId_doibong();

        CauThuEntity cauThuEntity = cauThuRepository.findById(id_cauthu).orElse(null);
        DoiBongEntity doiBongEntity = doiBongRepository.findById(id_doibong).orElse(null);

        if(cauThuEntity == null || doiBongEntity == null)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponeObject("Fail","Không tồn tại đội bóng hoặc cầu thủ", ""));

        }

        // set trạng thái của cầu thủ là tự do
        cauThuEntity.setTrangThai("Tự do");

        // Tìm cầu thủ trong cauthu_doibong ới doibongentity, set trạng thái inTeam = 0 (tức cầu thủ không còn thi đấu
        // cho đội bóng đó và set ngayKetThuc là ngày hiện tại kick

        CauThuDoiBongEntity cauThuDoiBongEntity = cauThuDoiBongRepository.findCauThuDoiBongEntityByCauThuDBAndDoiBongCT(cauThuEntity,doiBongEntity);
        if(cauThuDoiBongEntity == null)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponeObject("Fail","Không tìm thấy cầu thủ trong đội bóng", ""));
        }
        cauThuDoiBongEntity.setInTeam(0);
        cauThuDoiBongEntity.setThoiDiemKetThuc(new Date(System.currentTimeMillis()));


        cauThuRepository.save(cauThuEntity);
        cauThuDoiBongRepository.save(cauThuDoiBongEntity);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponeObject("OK","Xóa cầu thủ khỏi đội bóng thành công", ""));



    }

//    @Override
//    public ResponseEntity<ResponeObject> getPlayerOfTeam(Long idTeam) {
//        DoiBongEntity doiBongEntity = doiBongRepository.findById(idTeam).get();
//        List<CauThuDoiBongEntity> cauThuDoiBongEntities = doiBongEntity.getDoiBongCauThu();
//
//
//    }

//    @Override
//    public ResponseEntity<ResponeObject> getPlayerOfTeam(Long idTeam) {
//
//    }

    public TeamDTO convertToTeamDTO(DoiBongEntity entity){
        if(entity==null){
            return null;
        }
        TeamDTO teamDTO = null;
        try {
            teamDTO = GenericConverter.convert(entity, TeamDTO.class);

            FieldDTO fieldDTO = GenericConverter.convert(entity.getSanBong(), FieldDTO.class);
            ManagerDTO managerDTO = GenericConverter.convert(entity.getQuanLy(), ManagerDTO.class);

            teamDTO.setSanNha(fieldDTO);
            teamDTO.setQuanLy(managerDTO);

            List<CauThuDoiBongEntity> cauThuDoiBongEntities = entity.getDoiBongCauThu();

            if(cauThuDoiBongEntities == null){
                teamDTO.setDanhSachCauThuDangThiDau(null);
                return teamDTO;
            }

            List<CauThuDTO> players = new ArrayList<>();
            cauThuDoiBongEntities.forEach((ctdb)->{
                try {

                    if(ctdb.getThoiDiemKetThuc().compareTo(new Date(System.currentTimeMillis())) > 0 && ctdb.isInTeam() == 1){
                        CauThuDTO cauThuDTO = GenericConverter.convert(ctdb.getCauThuDB(), CauThuDTO.class);
                        cauThuDTO.setThoiDiemBatDau(ctdb.getKey().getThoiDiemBatDau());
                        cauThuDTO.setThoiDiemKetThuc(ctdb.getThoiDiemKetThuc());
                        cauThuDTO.setTongSoBanThang(ctdb.getTongSoBanThang());
                        cauThuDTO.setIdDoi(ctdb.getDoiBongCT().getId());
                        List<ViTriEntity> listVitri = ctdb.getCauThuDB().getCacViTri();
                        List<String> str_roles = new ArrayList<>();
                        listVitri.forEach(vitri->{
                            str_roles.add(vitri.getCode());
                        });
                        cauThuDTO.setViTri(str_roles.toArray(new String[0]));
                        players.add(cauThuDTO);
                    }

                } catch (Exception e) {

                }
            });
            teamDTO.setDanhSachCauThuDangThiDau(players);
        } catch (Exception e) {
            return null;
        }

        return teamDTO;
    }
}
