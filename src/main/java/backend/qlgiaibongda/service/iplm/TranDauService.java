package backend.qlgiaibongda.service.iplm;

import backend.qlgiaibongda.api.input.DateMatchInput;
import backend.qlgiaibongda.api.input.UpdateMatchInput;
import backend.qlgiaibongda.converter.GenResponse;
import backend.qlgiaibongda.converter.GenericConverter;
import backend.qlgiaibongda.dto.*;
import backend.qlgiaibongda.entity.*;
import backend.qlgiaibongda.repository.TranDauRepository;
import backend.qlgiaibongda.service.ITranDauService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class TranDauService implements ITranDauService {
    @Autowired
    private TranDauRepository tranDauRepository;

    @Override
    public ResponseEntity<ResponeObject> getMatch(Long id) {
        TranDauEntity tranDauEntity = tranDauRepository.findById(id).orElse(null);
        if(tranDauEntity == null){
            return  GenResponse.gen(HttpStatus.NOT_FOUND, "FAIL", "Match not found", "");
        }
        TranĐauDTO tranDauDTO = convertToTranDauDTO(tranDauEntity);
        return GenResponse.gen(HttpStatus.OK, "OK", "Get match succeed!", tranDauDTO);
    }

    @Override
    public ResponseEntity<ResponeObject> updateTime(UpdateMatchInput updateMatchInput) {

        List<TranDauEntity> dsTranDau = new ArrayList<>();

        for(DateMatchInput dateMatchInput: updateMatchInput.getDsTranDau()){
            Long idTranDauLong = dateMatchInput.getIdTranDau();
            Date time = dateMatchInput.getTime();

            TranDauEntity tranDauEntity = tranDauRepository.findById(idTranDauLong).orElse(null);
            if(tranDauEntity != null){
                tranDauEntity.setThoiGian(time);
                dsTranDau.add(tranDauEntity);
            }else{
                return GenResponse.gen(HttpStatus.NOT_FOUND, "FAIL","Match "+idTranDauLong +" not found!","");
            }
        }

        List<TranĐauDTO> dsTranDauTranĐauDTO = new ArrayList<>();
        for (TranDauEntity tranDau: dsTranDau){
            tranDauRepository.save(tranDau);
            dsTranDauTranĐauDTO.add(convertToTranDauDTO(tranDau));
        }


        return GenResponse.gen(HttpStatus.OK, "OK", "Update match succeed!", dsTranDauTranĐauDTO);
    }


    public TranĐauDTO convertToTranDauDTO(TranDauEntity tranDau){
        TranĐauDTO tranDauDTO = new TranĐauDTO();
        tranDauDTO.setId(tranDau.getId());
        Long idMuaGiai = tranDau.getLichThiDau().getMuaGiai().getId();
        try {
            DoiBongEntity doiNhaEntt = tranDau.getDoiNha();
            ManagerDTO qlDoiNha = GenericConverter.convert(doiNhaEntt.getQuanLy(), ManagerDTO.class);
            FieldDTO sanNha = GenericConverter.convert(doiNhaEntt.getSanBong(), FieldDTO.class);
            TeamDTO doiNha = GenericConverter.convert(doiNhaEntt, TeamDTO.class);
            doiNha.setQuanLy(qlDoiNha);
            doiNha.setSanNha(sanNha);

            //cauthu doi nha se thi dau:
            for(HoSoDangKyEntity hosoEntity: doiNhaEntt.getCacHoSoDangKy()){
                if(hosoEntity.getMuaGiai().getId() == idMuaGiai && hosoEntity.getTrangThai().equals("Đã duyệt")){
                    if(hosoEntity.getCacCauThu() != null) {
                        List<CauThuDTO> cauThuDTOList = new ArrayList<>();
                        for (CauThuEntity cauThuEntity : hosoEntity.getCacCauThu()) {
                            CauThuDTO cauThuDTO = GenericConverter.convert(cauThuEntity, CauThuDTO.class);
                            cauThuDTOList.add(cauThuDTO);
                        }
                        doiNha.setDanhSachCauThuDangThiDau(cauThuDTOList);
                        break;
                    }
                }
            }


            DoiBongEntity doiKhachEntt = tranDau.getDoiKhach();
            ManagerDTO qlDoiKhach = GenericConverter.convert(doiKhachEntt.getQuanLy(), ManagerDTO.class);
            FieldDTO sanKhach = GenericConverter.convert(doiKhachEntt.getSanBong(), FieldDTO.class);
            TeamDTO doiKhach = GenericConverter.convert(doiKhachEntt, TeamDTO.class);
            doiKhach.setQuanLy(qlDoiKhach);
            doiKhach.setSanNha(sanKhach);


            //cauthu doi khach se thi dau:
            for(HoSoDangKyEntity hosoEntity: doiKhachEntt.getCacHoSoDangKy()){
                if(hosoEntity.getMuaGiai().getId() == idMuaGiai && hosoEntity.getTrangThai().equals("Đã duyệt")){
                    if(hosoEntity.getCacCauThu() != null){
                        List<CauThuDTO> cauThuDTOList = new ArrayList<>();
                        for(CauThuEntity cauThuEntity: hosoEntity.getCacCauThu()){
                            CauThuDTO cauThuDTO = GenericConverter.convert(cauThuEntity, CauThuDTO.class);
                            cauThuDTOList.add(cauThuDTO);
                        }
                        doiKhach.setDanhSachCauThuDangThiDau(cauThuDTOList);
                        break;
                    }
                }
            }

            tranDauDTO.setDoiNha(doiNha);
            tranDauDTO.setDoiKhach(doiKhach);
            tranDauDTO.setThoiGian(tranDau.getThoiGian());



            KetQuaTranDauDTO ketQuaTranDauDTO = GenericConverter.convert(tranDau.getKetQuaTranDau(), KetQuaTranDauDTO.class);
            ketQuaTranDauDTO.setIdDoiNha(doiNha.getId());
            ketQuaTranDauDTO.setIdDoiKhach(doiKhach.getId());


            KetQuaTranDauEntity ketQuaTranDauEntity = tranDau.getKetQuaTranDau();

            List<GhiNhanBanThangEntity> dsBanThangEtt = ketQuaTranDauEntity.getDsBanThang();
            if(dsBanThangEtt != null){
                List<GhiNhanBanThangDTO> dsBanThangDTO = new ArrayList<>();
                for(GhiNhanBanThangEntity banThang: dsBanThangEtt){
                    GhiNhanBanThangDTO banThangDTO = GenericConverter.convert(banThang, GhiNhanBanThangDTO.class);
                    banThangDTO.setLoaiBanThang(GenericConverter.convert(banThang.getLoaiBanThang(), LoaiBanThangDTO.class));
                    banThangDTO.setIdCauThu(banThang.getCauThu().getId());
                    banThangDTO.setTenCauThu(banThang.getCauThu().getHoTen());
                    banThangDTO.setIdDoi(banThang.getDoiBong().getId());
                    banThangDTO.setTenDoi(banThang.getDoiBong().getTen());
                    banThangDTO.setThoiDiemGhiBan(banThang.getThoiDiemGhiBan());
                    dsBanThangDTO.add(banThangDTO);
                }
                ketQuaTranDauDTO.setDsBanThang(dsBanThangDTO);
            }

            tranDauDTO.setKetQuaTranDau(ketQuaTranDauDTO);

        } catch (Exception e) {
            System.out.println("convert tran dau khong thanh cong");
        }
        tranDauDTO.setIdVong(tranDau.getVong().getId());
        tranDauDTO.setTenVong(tranDau.getVong().getTenVong());
        tranDauDTO.setIdLichThiDau(tranDau.getLichThiDau().getId());

        return tranDauDTO;
    }


}
