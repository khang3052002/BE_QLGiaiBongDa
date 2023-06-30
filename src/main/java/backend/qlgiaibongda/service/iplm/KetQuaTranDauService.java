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
import backend.qlgiaibongda.entity.CauThuGhiBan.CauThuGhiBanEntity;
import backend.qlgiaibongda.entity.CauThuGhiBan.CauThuGhiBanKey;
import backend.qlgiaibongda.entity.cauthu_doibong.CauThuDoiBongEntity;
import backend.qlgiaibongda.repository.*;
import backend.qlgiaibongda.repository.MuaGiaiRepository.MuaGiaiRepository;
import backend.qlgiaibongda.service.IKetQuaTranDauService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
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

    @Autowired
    private CauThuGhiBanRepository cauThuGhiBanRepository;
    @Autowired
    private MuaGiaiRepository muaGiaiRepository;
    @Autowired
    private CauThuDoiBongRepository cauThuDoiBongRepository;
    @Override
    @Transactional
    public ResponseEntity<ResponeObject> updateMatchResult(ListMatchResultInput listMatchResultInput, Long id_trandau, Integer flagHoa0_0) {
        if(flagHoa0_0==0)
        {
            List<KetQuaTranDauDTO> dsKetQuaTranDauDTO = new ArrayList<>();
            KetQuaTranDauEntity ketQuaTranDauEntity = null;
            for(MatchResultInput matchResultInput : listMatchResultInput.getDsBanThang()){

                Long idTranDau = matchResultInput.getIdTranDau();
                Long idDoiGhiBan = matchResultInput.getIdDoiGhiBan();
                Long idCauThuGhiBan = matchResultInput.getIdCauThuGhiBan();
                Long thoiDiemGhiBan = matchResultInput.getThoiDiemGhiBan();
                Long idLoaiBanThanGhiBan = matchResultInput.getIdLoaiBanThang();

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

                LoaiBanThangEntity loaiBanThangEntity = loaiBanThangRepository.findById(idLoaiBanThanGhiBan).orElse(null);
                if(loaiBanThangEntity == null){
                    return GenResponse.gen(HttpStatus.NOT_FOUND, "FAIL", "Match"+idTranDau+": Type of goal not found", "");
                }

                DoiBongEntity doiGhiBan = null;

                if (ketQuaTranDauEntity.getDoiNha().getId() == idDoiGhiBan) {
                    doiGhiBan = ketQuaTranDauEntity.getDoiNha();
                    if(loaiBanThangEntity.getTen().equals("Phản lưới"))
                    {
                        Integer sbtDoiKhach = ketQuaTranDauEntity.getSbtDoiKhach();
                        ketQuaTranDauEntity.setSbtDoiKhach(sbtDoiKhach+1);
                    }
                    else{
                        Integer sbtDoiNha = ketQuaTranDauEntity.getSbtDoiNha();
                        ketQuaTranDauEntity.setSbtDoiNha(sbtDoiNha + 1);
                    }
                }

                if (ketQuaTranDauEntity.getDoiKhach().getId() == idDoiGhiBan) {
                    doiGhiBan = ketQuaTranDauEntity.getDoiKhach();
                    if(loaiBanThangEntity.getTen().equals("Phản lưới"))
                    {
                        Integer sbtDoiNha = ketQuaTranDauEntity.getSbtDoiNha();
                        ketQuaTranDauEntity.setSbtDoiNha(sbtDoiNha + 1);
                    }
                    else {
                        Integer sbtDoiKhach = ketQuaTranDauEntity.getSbtDoiKhach();
                        ketQuaTranDauEntity.setSbtDoiKhach(sbtDoiKhach + 1);
                    }
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



                //add to cauthughibanmuagiai:
                if(!loaiBanThangEntity.getTen().equals("Phản lưới"))
                {
                    CauThuGhiBanEntity cauThuGhiBanEntity = null;
                    boolean checkCauThuDaGhiBan  = cauThuGhiBanRepository.existsByCauThuAndDoiBongAndMuaGiai(cauThuEntity,doiGhiBan,muaGiaiEntity);

                    if(!checkCauThuDaGhiBan){
                        CauThuGhiBanKey cauThuGhiBanKey = new CauThuGhiBanKey();
                        cauThuGhiBanKey.setIdDoibong(doiGhiBan.getId());
                        cauThuGhiBanKey.setIdCauthu(cauThuEntity.getId());
                        cauThuGhiBanEntity = new CauThuGhiBanEntity();
                        cauThuGhiBanEntity.setCauThuGhiBanKey(cauThuGhiBanKey);
                        cauThuGhiBanEntity.setCauThu(cauThuEntity);
                        cauThuGhiBanEntity.setDoiBong(doiGhiBan);
                        cauThuGhiBanEntity.setMuaGiai(muaGiaiEntity);
                        cauThuGhiBanEntity.setSoBanThang(1);
                    }else{
                        cauThuGhiBanEntity = cauThuGhiBanRepository.findCauThuGhiBanEntityByCauThuAndDoiBongAndAndMuaGiai(cauThuEntity,doiGhiBan,muaGiaiEntity);
                        cauThuGhiBanEntity.setSoBanThang(cauThuGhiBanEntity.getSoBanThang()+1);
                    }
                    cauThuGhiBanEntity = cauThuGhiBanRepository.save(cauThuGhiBanEntity);
                }

                // cập nhật tổng số bàn thắng của CauThu_DoiBong

//                CauThuDoiBongEntity cauThuDoiBongEntity = cauThuDoiBongRepository.
//




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
//                muaGiaiEntity.setCacCauThuGhiBan();
                muaGiaiEntity = muaGiaiRepository.save(muaGiaiEntity);
                KetQuaTranDauDTO ketQuaTranDauDTO = convertToKetQuaTranDau(ketQuaTranDauEntity);
                dsKetQuaTranDauDTO.add(ketQuaTranDauDTO);
            }
            // cập nhật trạng thái kqtđ: "Đã cập nhật kết quả"
            ketQuaTranDauEntity.setTrangThai("Đã cập nhật kết quả");
            ketQuaTranDauRepository.save(ketQuaTranDauEntity);
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
            TranDauEntity tranDauEntity = tranDauRepository.findById(id).orElse(null);
            KetQuaTranDauEntity ketQuaTranDauEntity = tranDauEntity.getKetQuaTranDau();
//            KetQuaTranDauEntity ketQuaTranDauEntity = ketQuaTranDauRepository.findById(id).orElse(null);
            if(ketQuaTranDauEntity == null){
                return GenResponse.gen(HttpStatus.NOT_FOUND, "FAIL", "MatchResult "+id+ " not found", "");
            }
            tranDauEntity.setThoiGianNhanStart(new Timestamp(System.currentTimeMillis()));


            ketQuaTranDauEntity.setTrangThai("Đang thi đấu");
            ketQuaTranDauEntity.setSbtDoiNha(0);
            ketQuaTranDauEntity.setSbtDoiKhach(0);

            tranDauRepository.save(tranDauEntity);
            ketQuaTranDauEntity = ketQuaTranDauRepository.save(ketQuaTranDauEntity);

            dsKetQuaTranDauDTO.add(convertToKetQuaTranDau(ketQuaTranDauEntity));
        }

        return GenResponse.gen(HttpStatus.OK, "OK", "Start MatchResults succeed!",dsKetQuaTranDauDTO );

    }

    @Override
    public ResponseEntity<ResponeObject> endMatch(ListIdMatchResult listIdMatchResult) {

        List<KetQuaTranDauDTO> dsKetQuaTranDauDTO = new ArrayList<>();
        for(Long id: listIdMatchResult.getDsIDKetQuaTranDau()) {
            TranDauEntity tranDauEntity = tranDauRepository.findById(id).orElse(null);
            KetQuaTranDauEntity ketQuaTranDauEntity = tranDauEntity.getKetQuaTranDau();
//            KetQuaTranDauEntity ketQuaTranDauEntity = ketQuaTranDauRepository.findById(id).orElse(null);
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

