package backend.qlgiaibongda.service.iplm;

import backend.qlgiaibongda.converter.GenericConverter;
import backend.qlgiaibongda.dto.*;
import backend.qlgiaibongda.entity.CauThuEntity;
import backend.qlgiaibongda.entity.DoiBongEntity;
import backend.qlgiaibongda.entity.ViTriEntity;
import backend.qlgiaibongda.entity.cauthu_doibong.CauThuDoiBongEntity;
import backend.qlgiaibongda.repository.CauThuRepository;
import backend.qlgiaibongda.repository.DoiBongRepository;
import backend.qlgiaibongda.service.IGeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class GeneralService implements IGeneralService {
    @Autowired
    private CauThuRepository cauThuRepository;
    @Autowired
    private DoiBongRepository doiBongRepository;
    @Override
    public ResponseEntity<ResponeObject> searchPlayerAndTeamByKey(String keyword) {
        List<CauThuEntity> listCauThuEntity = cauThuRepository.findAllByHoTenContainsIgnoreCase(keyword);
        List<DoiBongEntity> doiBongEntityList = doiBongRepository.findAllByTenContainsIgnoreCase(keyword);

        searchPlayerandTeamResponeDTO respone = new searchPlayerandTeamResponeDTO();
        List<CauThuDTO> cauThuDTOList = new ArrayList<>();
        List<TeamDTO> teamDTOList = new ArrayList<>();

        if(!listCauThuEntity.isEmpty())
        {
            for (CauThuEntity cauThu : listCauThuEntity) {
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

                    cauThuDTOList.add(cauThuDTO);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                            .body(new ResponeObject("FAIL", "FAIL", e.getMessage()));
                }
            }
        }
        if(!doiBongEntityList.isEmpty())
        {
            for(DoiBongEntity entity: doiBongEntityList){
                TeamDTO teamDTO = convertToTeamDTO(entity);
                if(teamDTO != null){
                    teamDTOList.add(teamDTO);
                }
            }
        }
        respone.setListCauThu(cauThuDTOList);
        respone.setListDoiBong(teamDTOList);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponeObject("OK", "Tìm kiếm thnh công", respone));

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
