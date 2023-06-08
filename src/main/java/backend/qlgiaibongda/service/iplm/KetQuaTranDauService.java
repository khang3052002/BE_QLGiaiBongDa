package backend.qlgiaibongda.service.iplm;

import backend.qlgiaibongda.api.input.ListIdMatchResult;
import backend.qlgiaibongda.api.input.ListMatchResultInput;
import backend.qlgiaibongda.api.input.MatchResultInput;
import backend.qlgiaibongda.converter.GenResponse;
import backend.qlgiaibongda.converter.GenericConverter;
import backend.qlgiaibongda.dto.GhiNhanBanThangDTO;
import backend.qlgiaibongda.dto.KetQuaTranDauDTO;
import backend.qlgiaibongda.dto.LoaiBanThangDTO;
import backend.qlgiaibongda.dto.ResponeObject;
import backend.qlgiaibongda.entity.*;
import backend.qlgiaibongda.repository.*;
import backend.qlgiaibongda.service.IKetQuaTranDauService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class KetQuaTranDauService implements IKetQuaTranDauService {

    @Autowired
    private TranDauRepository tranDauRepository;

    @Autowired
    private CauThuRepository cauThuRepository;

    @Autowired
    private DoiBongRepository doiBongRepository;

    @Autowired
    private LoaiBanThangRepository loaiBanThangRepository;

    @Autowired
    private GhiNhanBanThangRepository ghiNhanThangRepository;

    @Autowired
    private KetQuaTranDauRepository ketQuaTranDauRepository;

    @Override
    @Transactional
    public ResponseEntity<ResponeObject> updateMatchResult(ListMatchResultInput listMatchResultInput, Long id_trandau, Integer flagHoa0_0) {
        if(flagHoa0_0==0)
        {
            List<KetQuaTranDauDTO> dsKetQuaTranDauDTO = new ArrayList<>();
            for(MatchResultInput matchResultInput : listMatchResultInput.getDsBanThang()){

                Long idTranDau = matchResultInput.getIdTranDau();
                Long idDoiGhiBan = matchResultInput.getIdDoiGhiBan();
                Long idCauThuGhiBan = matchResultInput.getIdCauThuGhiBan();
                Long thoiDiemGhiBan = matchResultInput.getThoiDiemGhiBan();
                Long idLoaiBanThanGhiBan = matchResultInput.getIdLoaiBanThang();

                KetQuaTranDauEntity ketQuaTranDauEntity;
                TranDauEntity tranDauEntity = tranDauRepository.findById(idTranDau).orElse(null);
                if (tranDauEntity == null) {
                    return GenResponse.gen(HttpStatus.NOT_FOUND, "FAIL", "Match"+ idTranDau+ " not found!", "");
                } else {
                    ketQuaTranDauEntity = tranDauEntity.getKetQuaTranDau();
//                if (!ketQuaTranDauEntity.getTrangThai().equals("Đang thi đấu")) {
//                    return GenResponse.gen(HttpStatus.CONFLICT, "FAIL", "Match"+idTranDau+" is not start or ended!", "");
//                }
                    // Cap nhat khi da "Da ket thuc"
                    if (!ketQuaTranDauEntity.getTrangThai().equals("Đã kết thúc")) {
                        return GenResponse.gen(HttpStatus.CONFLICT, "FAIL", "Match"+idTranDau+" is not start or ended!", "");
                    }
                }

                DoiBongEntity doiGhiBan = null;

                if (ketQuaTranDauEntity.getDoiNha().getId() == idDoiGhiBan) {
                    doiGhiBan = ketQuaTranDauEntity.getDoiNha();
                    Integer sbtDoiNha = ketQuaTranDauEntity.getSbtDoiNha();
                    ketQuaTranDauEntity.setSbtDoiNha(sbtDoiNha + 1);
                }

                if (ketQuaTranDauEntity.getDoiKhach().getId() == idDoiGhiBan) {
                    doiGhiBan = ketQuaTranDauEntity.getDoiKhach();
                    Integer sbtDoiKhach = ketQuaTranDauEntity.getSbtDoiKhach();
                    ketQuaTranDauEntity.setSbtDoiKhach(sbtDoiKhach + 1);
                }

                CauThuEntity cauThuEntity = null;
                MuaGiaiEntity muaGiaiEntity = null;
                if (doiGhiBan == null) {
                    return GenResponse.gen(HttpStatus.NOT_FOUND, "FAIL", "Team"+idDoiGhiBan+" not found!", "");
                } else {
                    muaGiaiEntity = tranDauEntity.getLichThiDau().getMuaGiai();
                    Long idMuaGiai = muaGiaiEntity.getId();
                    HoSoDangKyEntity hosoEntity = null;
                    for (HoSoDangKyEntity hoso : doiGhiBan.getCacHoSoDangKy()) {
                        if (hoso.getMuaGiai().getId() == idMuaGiai) {
                            hosoEntity = hoso;
                            break;
                        }

                    }


                    for (CauThuEntity cauThu : hosoEntity.getCacCauThu()) {
                        if (cauThu.getId() == idCauThuGhiBan) {
                            cauThuEntity = cauThu;
                            break;
                        }
                    }

                    if (cauThuEntity == null) {
                        return GenResponse.gen(HttpStatus.NOT_FOUND, "FAIL", "Player"+ idCauThuGhiBan +" is not found in the lineup!", "");
                    }
                }

                GhiNhanBanThangEntity ghiNhanThangEntity = new GhiNhanBanThangEntity();
                //check thoi diem ghi ban:

                if (muaGiaiEntity.getQuyDinhMuaGiai().getQuyDinhBanThang() != null) {
                    Long thoiDiemGhiBanToiDa = muaGiaiEntity.getQuyDinhMuaGiai().getQuyDinhBanThang().getThoiDiemGhiBanToiDa();
                    if (thoiDiemGhiBan > thoiDiemGhiBanToiDa) {
                        {
                            return GenResponse.gen(HttpStatus.CONFLICT, "FAIL", "Match "+idTranDau+" Time is invalid!", "");
                        }
                    }
                }

                LoaiBanThangEntity loaiBanThangEntity = loaiBanThangRepository.findById(idLoaiBanThanGhiBan).orElse(null);
                if(loaiBanThangEntity == null){
                    return GenResponse.gen(HttpStatus.NOT_FOUND, "FAIL", "Match"+idTranDau+": Type of goal not found", "");
                }

                ghiNhanThangEntity.setThoiDiemGhiBan(thoiDiemGhiBan);
                ghiNhanThangEntity.setCauThu(cauThuEntity);
                ghiNhanThangEntity.setDoiBong(doiGhiBan);
                ghiNhanThangEntity.setLoaiBanThang(loaiBanThangEntity);
                ghiNhanThangEntity = ghiNhanThangRepository.save(ghiNhanThangEntity);

                List<GhiNhanBanThangEntity> listGhiNhanBanThang = ketQuaTranDauEntity.getDsBanThang();
                if (listGhiNhanBanThang == null) {
                    listGhiNhanBanThang = new ArrayList<>();
                }
                listGhiNhanBanThang.add(ghiNhanThangEntity);
                ketQuaTranDauEntity.setDsBanThang(listGhiNhanBanThang);

                ketQuaTranDauEntity = ketQuaTranDauRepository.save(ketQuaTranDauEntity);
                ghiNhanThangEntity.setKetQuaTranDau(ketQuaTranDauEntity);
                ghiNhanThangEntity = ghiNhanThangRepository.save(ghiNhanThangEntity);

                List<GhiNhanBanThangEntity> listTranDau_LoaiBanThang = loaiBanThangEntity.getDSKetQuaTranDau_LoaiBanThang();
                if (listTranDau_LoaiBanThang == null) {
                    listTranDau_LoaiBanThang = new ArrayList<>();
                }
                listTranDau_LoaiBanThang.add(ghiNhanThangEntity);
                loaiBanThangEntity.setDSKetQuaTranDau_LoaiBanThang(listTranDau_LoaiBanThang);
                loaiBanThangEntity = loaiBanThangRepository.save(loaiBanThangEntity);

                KetQuaTranDauDTO ketQuaTranDauDTO = convertToKetQuaTranDau(ketQuaTranDauEntity);
                dsKetQuaTranDauDTO.add(ketQuaTranDauDTO);
            }
            return GenResponse.gen(HttpStatus.OK, "OK", "Update MatchResults succeed!", dsKetQuaTranDauDTO);

        }
       else{
            return GenResponse.gen(HttpStatus.OK, "OK", "Update MatchResults succeed!", "Tran dau hoa 0-0");
        }
    }

    @Override
    public ResponseEntity<ResponeObject> getOne(Long id) {
        KetQuaTranDauEntity ketQuaTranDauEntity = ketQuaTranDauRepository.findById(id).orElse(null);
        if(ketQuaTranDauEntity == null){
            return GenResponse.gen(HttpStatus.NOT_FOUND, "FAIL", "MatchResult not found", "");

        }
        return GenResponse.gen(HttpStatus.OK, "OK", "Get MatchResult succeed!", convertToKetQuaTranDau(ketQuaTranDauEntity));
    }

    @Override
    public ResponseEntity<ResponeObject> startMatch(ListIdMatchResult listIdMatchResult) {

        List<KetQuaTranDauDTO> dsKetQuaTranDauDTO = new ArrayList<>();
        for(Long id: listIdMatchResult.getDsIDKetQuaTranDau()){

            KetQuaTranDauEntity ketQuaTranDauEntity = ketQuaTranDauRepository.findById(id).orElse(null);
            if(ketQuaTranDauEntity == null){
                return GenResponse.gen(HttpStatus.NOT_FOUND, "FAIL", "MatchResult "+id+ " not found", "");
            }
            ketQuaTranDauEntity.setTrangThai("Đang thi đấu");
            ketQuaTranDauEntity.setSbtDoiNha(0);
            ketQuaTranDauEntity.setSbtDoiKhach(0);
            ketQuaTranDauEntity = ketQuaTranDauRepository.save(ketQuaTranDauEntity);

            dsKetQuaTranDauDTO.add(convertToKetQuaTranDau(ketQuaTranDauEntity));
        }

        return GenResponse.gen(HttpStatus.OK, "OK", "Start MatchResults succeed!",dsKetQuaTranDauDTO );

    }

    @Override
    public ResponseEntity<ResponeObject> endMatch(ListIdMatchResult listIdMatchResult) {

        List<KetQuaTranDauDTO> dsKetQuaTranDauDTO = new ArrayList<>();
        for(Long id: listIdMatchResult.getDsIDKetQuaTranDau()) {

            KetQuaTranDauEntity ketQuaTranDauEntity = ketQuaTranDauRepository.findById(id).orElse(null);
            if(ketQuaTranDauEntity == null){
                return GenResponse.gen(HttpStatus.NOT_FOUND, "FAIL", "MatchResult "+id+" not found", "");
            }
            ketQuaTranDauEntity.setTrangThai("Đã kết thúc");
            ketQuaTranDauEntity = ketQuaTranDauRepository.save(ketQuaTranDauEntity);
            dsKetQuaTranDauDTO.add(convertToKetQuaTranDau(ketQuaTranDauEntity));
        }

        return GenResponse.gen(HttpStatus.OK, "OK", "End MatchResult succeed!", dsKetQuaTranDauDTO);
    }


    public KetQuaTranDauDTO convertToKetQuaTranDau(KetQuaTranDauEntity ketQuaTranDauEntity){
        KetQuaTranDauDTO ketQuaTranDauDTO = null;
        try {
            ketQuaTranDauDTO = GenericConverter.convert(ketQuaTranDauEntity, KetQuaTranDauDTO.class);

            DoiBongEntity doiNha = ketQuaTranDauEntity.getDoiNha();
            DoiBongEntity doiKhach = ketQuaTranDauEntity.getDoiKhach();

            ketQuaTranDauDTO.setIdDoiNha(doiNha.getId());
            ketQuaTranDauDTO.setIdDoiKhach(doiKhach.getId());


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
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        return ketQuaTranDauDTO;
    }
}

