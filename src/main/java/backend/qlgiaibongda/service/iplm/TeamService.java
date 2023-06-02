package backend.qlgiaibongda.service.iplm;

import backend.qlgiaibongda.api.input.NewTeamInput;
import backend.qlgiaibongda.api.input.UpdateTeamInput;
import backend.qlgiaibongda.api.output.ErrorOutput;
import backend.qlgiaibongda.converter.GenericConverter;
import backend.qlgiaibongda.dto.*;
import backend.qlgiaibongda.entity.DoiBongEntity;
import backend.qlgiaibongda.entity.QuanLyEntity;
import backend.qlgiaibongda.entity.SanBongEntity;
import backend.qlgiaibongda.entity.cauthu_doibong.CauThuDoiBongEntity;
import backend.qlgiaibongda.repository.DoiBongRepository;
import backend.qlgiaibongda.repository.QuanLiRepository;
import backend.qlgiaibongda.repository.SanBongRepository;
import backend.qlgiaibongda.service.ITeamService;
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

                    if(ctdb.getThoiDiemKetThuc().compareTo(new Date(System.currentTimeMillis())) > 0){
                        CauThuDTO cauThuDTO = GenericConverter.convert(ctdb.getCauThuDB(), CauThuDTO.class);
                        cauThuDTO.setThoiDiemBatDau(ctdb.getKey().getThoiDiemBatDau());
                        cauThuDTO.setThoiDiemKetThuc(ctdb.getThoiDiemKetThuc());
                        cauThuDTO.setTongSoBanThang(ctdb.getTongSoBanThang());
                        cauThuDTO.setIdDoi(ctdb.getDoiBongCT().getId());
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
