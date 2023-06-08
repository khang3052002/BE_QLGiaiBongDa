package backend.qlgiaibongda.service.iplm;

import backend.qlgiaibongda.api.input.NewSchedule;
import backend.qlgiaibongda.converter.GenResponse;
import backend.qlgiaibongda.converter.GenericConverter;
import backend.qlgiaibongda.dto.*;
import backend.qlgiaibongda.dto.QuyDinhDTO.VongDauDTO;
import backend.qlgiaibongda.entity.*;
import backend.qlgiaibongda.repository.*;
import backend.qlgiaibongda.repository.MuaGiaiRepository.MuaGiaiRepository;
import backend.qlgiaibongda.service.IScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.*;

@Service
public class ScheduleService implements IScheduleService {
    @Autowired
    private LichThiDauRepository lichThiDauRepository;

    @Autowired
    private QuanLiRepository quanLiRepository;

    @Autowired
    private MuaGiaiRepository muaGiaiRepository;

    @Autowired
    private DoiBongRepository doiBongRepository;

    @Autowired
    private QuyDinhSoLuongDoiRepository quyDinhSoLuongDoiRepository;

    @Autowired
    private TranDauRepository tranDauRepository;

    @Autowired
    private VongRepository vongRepository;

    @Autowired
    private KetQuaTranDauRepository ketQuaTranDauRepository;


    @Override
    @Transactional
    public ResponseEntity<ResponeObject> createNewSchedule(NewSchedule newSchedule) {



        Long idQuanLy = newSchedule.getIdQuanLy();
        Long idMuaGiaId = newSchedule.getIdMuaGiai();
        LichThiDauEntity lichThiDauEntity = new LichThiDauEntity();

        QuanLyEntity quanLyEntity = quanLiRepository.findById(idQuanLy).orElse(null);
        if(quanLyEntity == null){
            return GenResponse.gen(HttpStatus.NOT_FOUND, "FAIL", "Manager not found!", "");
        }else{
            if(!quanLyEntity.getVaiTro().getCode().equals("QLGD")){
                return GenResponse.gen(HttpStatus.FORBIDDEN, "FAIL", "This Manager is not allow to create schedule!", "");
            }
        }

        MuaGiaiEntity muaGiaiEntity = muaGiaiRepository.findById(idMuaGiaId).orElse(null);
        List<DoiBongEntity> dsDoiThamGia = new ArrayList<>();
        if(muaGiaiEntity == null){
            return GenResponse.gen(HttpStatus.NOT_FOUND, "FAIL", "Season not found!", "");

        }else{
            if(muaGiaiEntity.getTrangThai() !=0){
                return GenResponse.gen(HttpStatus.CONFLICT, "FAIL", "The season is currently ongoing or has concluded!", "");
            }
            // giai dau dang dang ky
            if(muaGiaiEntity.getLichThiDau() != null){
                lichThiDauEntity = muaGiaiEntity.getLichThiDau();
                //delete before create new:
                Map<String, VongEntity> mapVongDau = Collections.synchronizedMap(new HashMap<>());
                for(TranDauEntity tranDau: lichThiDauEntity.getListTranDauCuaLichThiDau()){
                    mapVongDau.put(tranDau.getVong().getTenVong(), tranDau.getVong());
                    tranDauRepository.delete(tranDau);
                }
                for(VongEntity vongDauDel: mapVongDau.values()){
                    vongRepository.delete(vongDauDel);
                }
            }

            Date now = new Date(System.currentTimeMillis());
            if(muaGiaiEntity.getThoiDiemKetThuc().compareTo(now) < 0){
                return GenResponse.gen(HttpStatus.CONFLICT, "FAIL", "Season has ended!", "");

            }

            if(muaGiaiEntity.getThoiDiemBatDau().compareTo(now) < 0){
                return GenResponse.gen(HttpStatus.CONFLICT, "FAIL", "The football season is currently underway.", "");

            }


            int totalTeam = 0;
            List< HoSoDangKyEntity> hoSoDangKyEntities = muaGiaiEntity.getCacHoSoDangKy();
            for(HoSoDangKyEntity hoso: hoSoDangKyEntities){
                if(hoso.getTrangThai().equals("Đã duyệt")){
                    dsDoiThamGia.add(hoso.getDoiBong());
                    totalTeam++;
                }
            }

            int slDoiQuyDinh = muaGiaiEntity.getQuyDinhMuaGiai().getQuyDinhSoLuongDoi().getSoLuongDoi();
            if(totalTeam != slDoiQuyDinh){
                return GenResponse.gen(HttpStatus.CONFLICT, "FAIL", "Not enough team!", "");
            }

        }


        lichThiDauEntity.setQuanLyTaoLich(quanLyEntity);
        lichThiDauEntity.setMuaGiai(muaGiaiEntity);
        lichThiDauEntity.setThoiGianTao(new Date(System.currentTimeMillis()));

        List<TranDauEntity> dsTranDau = new ArrayList<>();
        List<VongEntity> dsVong = new ArrayList<>();
        //create schedule:
        int soVong = 0;
        int numberOfTeams = dsDoiThamGia.size();

        //xao tron doi:
        Collections.shuffle(dsDoiThamGia);
        //luot di
        for (int i = 0; i < numberOfTeams - 1; i++) {
            soVong = i + 1;
            VongEntity vongEntity = new VongEntity();
            vongEntity.setTenVong("Vòng "+soVong);
            vongEntity = vongRepository.save(vongEntity);

            List<TranDauEntity> tranDauCuaVong = new ArrayList<>();
            for (int j = 0; j < numberOfTeams / 2; j++) {
                int team1 = j;
                int team2 = numberOfTeams - 1 - j;

                DoiBongEntity doiNhaTD = dsDoiThamGia.get(team1);
                DoiBongEntity doiKhachTD = dsDoiThamGia.get(team2);

                TranDauEntity tranDauEntity = new TranDauEntity();
                tranDauEntity.setDoiNha(doiNhaTD);
                tranDauEntity.setDoiKhach(doiKhachTD);
                tranDauEntity.setVong(vongEntity);

                KetQuaTranDauEntity ketQuaTranDauEntity = new KetQuaTranDauEntity();
                ketQuaTranDauEntity.setDoiNha(doiNhaTD);
                ketQuaTranDauEntity.setDoiKhach(doiKhachTD);
                ketQuaTranDauEntity.setTrangThai("Chưa thi đấu");

                ketQuaTranDauEntity=ketQuaTranDauRepository.save(ketQuaTranDauEntity);

                tranDauEntity.setKetQuaTranDau(ketQuaTranDauEntity);
                tranDauEntity = tranDauRepository.save(tranDauEntity);

                ketQuaTranDauEntity.setTranDau(tranDauEntity);
                ketQuaTranDauEntity=ketQuaTranDauRepository.save(ketQuaTranDauEntity);


                dsTranDau.add(tranDauEntity);
                tranDauCuaVong.add(tranDauEntity);

            }
            vongEntity.setListTranDauCuaVong(tranDauCuaVong);
            vongRepository.save(vongEntity);
            dsVong.add(vongEntity);

            DoiBongEntity lastTeam = dsDoiThamGia.remove(numberOfTeams - 1);
            dsDoiThamGia.add(1, lastTeam);
        }

        //luotve
        for (int i = 0; i < numberOfTeams - 1; i++) {
            soVong = soVong + 1;
            VongEntity vongEntity = new VongEntity();
            vongEntity.setTenVong("Vòng "+soVong);
            vongEntity = vongRepository.save(vongEntity);

            List<TranDauEntity> tranDauCuaVong = new ArrayList<>();
            for (int j = 0; j < numberOfTeams / 2; j++) {
                int team1 = numberOfTeams - 1 - j;
                int team2 = j;

                DoiBongEntity doiNhaTD = dsDoiThamGia.get(team1);
                DoiBongEntity doiKhachTD = dsDoiThamGia.get(team2);

                TranDauEntity tranDauEntity = new TranDauEntity();
                tranDauEntity.setDoiNha(doiNhaTD);
                tranDauEntity.setDoiKhach(doiKhachTD);
                tranDauEntity.setVong(vongEntity);

                KetQuaTranDauEntity ketQuaTranDauEntity = new KetQuaTranDauEntity();
                ketQuaTranDauEntity.setDoiNha(doiNhaTD);
                ketQuaTranDauEntity.setDoiKhach(doiKhachTD);
                ketQuaTranDauEntity.setTrangThai("Chưa thi đấu");

                ketQuaTranDauEntity.setTranDau(tranDauEntity);
                ketQuaTranDauEntity = ketQuaTranDauRepository.save(ketQuaTranDauEntity);

                tranDauEntity.setKetQuaTranDau(ketQuaTranDauEntity);
                tranDauEntity = tranDauRepository.save(tranDauEntity);
                dsTranDau.add(tranDauEntity);
                tranDauCuaVong.add(tranDauEntity);
            }

            vongEntity.setListTranDauCuaVong(tranDauCuaVong);
            vongRepository.save(vongEntity);
            dsVong.add(vongEntity);


            DoiBongEntity lastTeam = dsDoiThamGia.remove(numberOfTeams - 1);
            dsDoiThamGia.add(1, lastTeam);
//            DoiBongEntity firstTeam = dsDoiThamGia.remove(0);
//            dsDoiThamGia.add(firstTeam);
        }

        lichThiDauEntity.setListTranDauCuaLichThiDau(dsTranDau);
        lichThiDauEntity = lichThiDauRepository.save(lichThiDauEntity);

        for(TranDauEntity tranDau: dsTranDau){
            tranDau.setLichThiDau(lichThiDauEntity);
            tranDauRepository.save(tranDau);
        }

        muaGiaiEntity.setLichThiDau(lichThiDauEntity);
        muaGiaiRepository.save(muaGiaiEntity);

        LichThiDauDTO lichThiDauDTO = convertToLichThiDauDTO(lichThiDauEntity);

        return GenResponse.gen(HttpStatus.OK, "OK", "Create schedule succeed", lichThiDauDTO);
    }


    @Override
    public ResponseEntity<ResponeObject> getSchedule(Long id) {
        LichThiDauEntity lichThiDauEntity = lichThiDauRepository.findById(id).orElse(null);
        if(lichThiDauEntity == null){
            return  GenResponse.gen(HttpStatus.NOT_FOUND, "FAIL","Schedule not found","");
        }

        LichThiDauDTO lichThiDauDTO = convertToLichThiDauDTO(lichThiDauEntity);
        return  GenResponse.gen(HttpStatus.OK, "OK","Get schedule succeed",lichThiDauDTO);

    }

    public LichThiDauDTO convertToLichThiDauDTO(LichThiDauEntity lichThiDauEntity) {
        LichThiDauDTO lichThiDauDTO = new LichThiDauDTO();
        List<VongDauDTO> dsVongDauDTO = new ArrayList<>();


        Map<String, VongEntity> mapVong = Collections.synchronizedMap(new HashMap<>());
        for(TranDauEntity trandau: lichThiDauEntity.getListTranDauCuaLichThiDau()){
            mapVong.put(trandau.getVong().getTenVong(), trandau.getVong());
        }

        //try again:
        List<VongEntity> dsVong = new ArrayList<>(mapVong.values());
        dsVong.sort((v1,v2)->{
            String tenVong1 =  v1.getTenVong();
            String tenVong2 = v2.getTenVong();

            int num1 = Integer.parseInt(tenVong1.split("Vòng ")[1]);
            int num2 = Integer.parseInt(tenVong2.split("Vòng ")[1]);

            return num1 - num2;
        });

        for (VongEntity vongDau : dsVong) {
            VongDauDTO vongDauDTO = new VongDauDTO();
            vongDauDTO.setId(vongDau.getId());
            vongDauDTO.setTenVong(vongDau.getTenVong());
            List<TranĐauDTO> dsTranDauDTO = new ArrayList<>();
            for (TranDauEntity tranDau : vongDau.getListTranDauCuaVong()) {
                TranĐauDTO tranDauDTO = new TranĐauDTO();
                tranDauDTO.setId(tranDau.getId());
                try {
                    DoiBongEntity doiNhaEntt = tranDau.getDoiNha();
                    ManagerDTO qlDoiNha = GenericConverter.convert(doiNhaEntt.getQuanLy(), ManagerDTO.class);
                    FieldDTO sanNha = GenericConverter.convert(doiNhaEntt.getSanBong(), FieldDTO.class);
                    TeamDTO doiNha = GenericConverter.convert(doiNhaEntt, TeamDTO.class);
                    doiNha.setQuanLy(qlDoiNha);
                    doiNha.setSanNha(sanNha);

                    DoiBongEntity doiKhachEntt = tranDau.getDoiKhach();
                    ManagerDTO qlDoiKhach = GenericConverter.convert(doiKhachEntt.getQuanLy(), ManagerDTO.class);
                    FieldDTO sanKhach = GenericConverter.convert(doiKhachEntt.getSanBong(), FieldDTO.class);
                    TeamDTO doiKhach = GenericConverter.convert(doiKhachEntt, TeamDTO.class);
                    doiKhach.setQuanLy(qlDoiKhach);
                    doiKhach.setSanNha(sanKhach);

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
                tranDauDTO.setIdVong(vongDau.getId());
                tranDauDTO.setTenVong(vongDau.getTenVong());
                tranDauDTO.setIdLichThiDau(lichThiDauEntity.getId());
                dsTranDauDTO.add(tranDauDTO);
            }
            vongDauDTO.setCacTranDau(dsTranDauDTO);
            dsVongDauDTO.add(vongDauDTO);
        }

        lichThiDauDTO.setIdMuaGiaID(lichThiDauEntity.getMuaGiai().getId());
        lichThiDauDTO.setCacVongDau(dsVongDauDTO);

        return lichThiDauDTO;
    }
}
