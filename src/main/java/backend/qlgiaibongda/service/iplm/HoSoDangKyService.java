package backend.qlgiaibongda.service.iplm;

import backend.qlgiaibongda.converter.GenericConverter;
import backend.qlgiaibongda.dto.CauThuDTO;
import backend.qlgiaibongda.dto.HoSoDangKyDTO;
import backend.qlgiaibongda.dto.ResponeObject;
import backend.qlgiaibongda.dto.TuChoiHoSoDTO;
import backend.qlgiaibongda.entity.*;
import backend.qlgiaibongda.entity.cauthu_doibong.CauThuDoiBongEntity;
import backend.qlgiaibongda.repository.CauThuDoiBongRepository;
import backend.qlgiaibongda.repository.DoiBongRepository;
import backend.qlgiaibongda.repository.HoSoDangKyRepository;
import backend.qlgiaibongda.repository.MuaGiaiRepository.MuaGiaiRepository;
import backend.qlgiaibongda.service.IHoSoDangKyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HoSoDangKyService implements IHoSoDangKyService {
    @Autowired
    private MuaGiaiRepository muaGiaiRepository;
    @Autowired
    private HoSoDangKyRepository hoSoDangKyRepository;
    @Autowired
    private CauThuDoiBongRepository cauThuDoiBongRepository;
    @Autowired
    private DoiBongRepository doiBongRepository;

    @Override
    public ResponseEntity<ResponeObject> getHoSoDangKyByMuaGiai(Long id_muagiai) {
        MuaGiaiEntity muaGiaiEntity = muaGiaiRepository.findById(id_muagiai).orElse(null);
        if(muaGiaiEntity!=null)
        {
            List<HoSoDangKyEntity> listHoSoDangKy = muaGiaiEntity.getCacHoSoDangKy();
            List<HoSoDangKyDTO> listHoSoDangKyDTO = new ArrayList<>();
            if(listHoSoDangKy.size() == 0 || listHoSoDangKy == null)
            {
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponeObject("OK","Chưa có hồ sơ đăng ký nào","")
                );
            }
            else{
                for(HoSoDangKyEntity hoSoDangKyEntity : listHoSoDangKy)
                {
                    try
                    {
                        HoSoDangKyDTO hoSoDangKyDTO = GenericConverter.convert(hoSoDangKyEntity, HoSoDangKyDTO.class);
                        hoSoDangKyDTO.setId_giai(muaGiaiEntity.getId());
                        hoSoDangKyDTO.setId_quanly(hoSoDangKyEntity.getQuanLyDkiHoSo().getId());
                        hoSoDangKyDTO.setTen_quanly(hoSoDangKyEntity.getQuanLyDkiHoSo().getHoTen());
                        hoSoDangKyDTO.setId_doibong(hoSoDangKyEntity.getDoiBong().getId());
                        hoSoDangKyDTO.setTen_doibong(hoSoDangKyEntity.getDoiBong().getTen());
                        hoSoDangKyDTO.setGhiChu(hoSoDangKyEntity.getGhiChu());
                        DoiBongEntity doiBongEntity = hoSoDangKyEntity.getDoiBong();

                        List<CauThuDTO> listCauThuDTO = new ArrayList<>();
                        for(CauThuEntity cauthu: hoSoDangKyEntity.getCacCauThu())
                        {
                            // chỉ lấy những cầu thủ trong hồ sơ đăng kí với trạng thái còn thi đấu ở đội bóng đó

                            CauThuDoiBongEntity cauThuDoiBongEntity = cauThuDoiBongRepository.findCauThuDoiBongEntityByCauThuDBAndDoiBongCT(cauthu,doiBongEntity);

                            Integer isInTeam = cauThuDoiBongEntity.isInTeam();
                            if(isInTeam == 1)
                            {
                                CauThuDTO cauThuDTO = GenericConverter.convert(cauthu, CauThuDTO.class);
                                List<ViTriEntity> listVitri = cauthu.getCacViTri();
                                List<String> str_roles = new ArrayList<>();
                                listVitri.forEach(vitri->{
                                    str_roles.add(vitri.getCode());
                                });
                                cauThuDTO.setViTri(str_roles.toArray(new String[0]));
                                cauThuDTO.setAge(cauthu.calculateAge());
                                cauThuDTO.setSoAo(cauThuDoiBongEntity.getSoAo());
                                listCauThuDTO.add(cauThuDTO);
                            }
                        }
                        hoSoDangKyDTO.setDsCauThuDangKy(listCauThuDTO);
                        listHoSoDangKyDTO.add(hoSoDangKyDTO);
                    }catch (Exception ex)
                    {
                        System.out.println(ex.getMessage());
                    }
                }
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponeObject("OK","SUCCESS",listHoSoDangKyDTO)
                );
            }


        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponeObject("FAIL","Mùa giải không tồn tại","")
        );
    }

    @Override
    public ResponseEntity<ResponeObject> getDetailHoSoDangKyByID(Long idHoso) {
        HoSoDangKyEntity hoSoDangKyEntity = hoSoDangKyRepository.findById(idHoso).orElse(null);
        if(hoSoDangKyEntity!=null) {
            try {
                HoSoDangKyDTO hoSoDangKyDTO = GenericConverter.convert(hoSoDangKyEntity, HoSoDangKyDTO.class);
                hoSoDangKyDTO.setId_giai(hoSoDangKyEntity.getMuaGiai().getId());
                hoSoDangKyDTO.setGhiChu(hoSoDangKyEntity.getGhiChu());
                hoSoDangKyDTO.setId_quanly(hoSoDangKyEntity.getQuanLyDkiHoSo().getId());
                hoSoDangKyDTO.setTen_quanly(hoSoDangKyEntity.getQuanLyDkiHoSo().getHoTen());
                hoSoDangKyDTO.setId_doibong(hoSoDangKyEntity.getDoiBong().getId());
                hoSoDangKyDTO.setTen_doibong(hoSoDangKyEntity.getDoiBong().getTen());
                DoiBongEntity doiBongEntity = hoSoDangKyEntity.getDoiBong();
                List<CauThuDTO> listCauThuDTO = new ArrayList<>();
                for (CauThuEntity cauthu : hoSoDangKyEntity.getCacCauThu()) {
                    CauThuDoiBongEntity cauThuDoiBongEntity = cauThuDoiBongRepository.findCauThuDoiBongEntityByCauThuDBAndDoiBongCT(cauthu,doiBongEntity);

                    Integer isInTeam = cauThuDoiBongEntity.isInTeam();
                    if(isInTeam == 1)
                    {
                        CauThuDTO cauThuDTO = GenericConverter.convert(cauthu, CauThuDTO.class);
                        List<ViTriEntity> listVitri = cauthu.getCacViTri();
                        List<String> str_roles = new ArrayList<>();
                        listVitri.forEach(vitri -> {
                            str_roles.add(vitri.getCode());
                        });
                        cauThuDTO.setViTri(str_roles.toArray(new String[0]));
                        cauThuDTO.setAge(cauthu.calculateAge());
                        cauThuDTO.setSoAo(cauThuDoiBongEntity.getSoAo());
                        listCauThuDTO.add(cauThuDTO);
                    }
                }
                hoSoDangKyDTO.setDsCauThuDangKy(listCauThuDTO);
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponeObject("OK","SUCCESS",hoSoDangKyDTO)
                );

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponeObject("FAIL","Không tồn tại hồ sơ này","")
        );
    }

    @Override
    public ResponseEntity<ResponeObject> duyetHoSoDangKy(Long idHoso) {
        HoSoDangKyEntity hoSoDangKyEntity = hoSoDangKyRepository.findById(idHoso).orElse(null);
        if(hoSoDangKyEntity!=null)
        {
            MuaGiaiEntity muaGiaiEntity = hoSoDangKyEntity.getMuaGiai();
            if(hoSoDangKyEntity.getTrangThai().equals("Chờ duyệt"))
            {

                Long soLuongDoiHienTai = hoSoDangKyRepository.countByTrangThaiAndMuaGiai("Đã duyệt", muaGiaiEntity);
                int soLuongDoiQuyDinh = muaGiaiEntity.getQuyDinhMuaGiai().getQuyDinhSoLuongDoi().getSoLuongDoi();
                if(soLuongDoiHienTai < soLuongDoiQuyDinh) // số lượng đội đã duyệt hiện tại so với số luowjgn đội quy định của giải
                {
                    // Cho đăng kí
                    hoSoDangKyEntity.setTrangThai("Đã duyệt");
                    hoSoDangKyRepository.save(hoSoDangKyEntity);
                    return ResponseEntity.status(HttpStatus.OK).body(
                            new ResponeObject("OK","Đã duyệt hồ sơ",""));
                }
                return ResponseEntity.status(HttpStatus.CONFLICT).body(
                        new ResponeObject("FAIL","Đã đủ số lượng đội tham gia, duyệt hồ sơ thất bại","")
                );
            }
            else{
                return ResponseEntity.status(HttpStatus.CONFLICT).body(
                        new ResponeObject("FAIL","Hồ sơ đã được "+ (hoSoDangKyEntity.getTrangThai()).toLowerCase()+ " trước đó","")
                );
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponeObject("FAIL","Không tồn tại hồ sơ","")
        );
    }

    @Override
    public ResponseEntity<ResponeObject> tuChoiHoSoDangKy(TuChoiHoSoDTO tuChoiHoSoDTO) {
        HoSoDangKyEntity hoSoDangKyEntity = hoSoDangKyRepository.findById(tuChoiHoSoDTO.getId_hoso()).orElse(null);
        if(hoSoDangKyEntity!=null)
        {
            if(hoSoDangKyEntity.getTrangThai().equals("Chờ duyệt"))
            {
                hoSoDangKyEntity.setTrangThai("Từ chối");
                if(tuChoiHoSoDTO.getGhiChu()!=null)
                {
                    hoSoDangKyEntity.setGhiChu(tuChoiHoSoDTO.getGhiChu());
                }
                else{
                    hoSoDangKyEntity.setGhiChu("");
                }
                hoSoDangKyRepository.save(hoSoDangKyEntity);
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponeObject("OK","Đã từ chối hồ sơ",""));

            }
            else{
                return ResponseEntity.status(HttpStatus.CONFLICT).body(
                        new ResponeObject("FAIL","Hồ sơ đã được "+ (hoSoDangKyEntity.getTrangThai()).toLowerCase()+ " trước đó","")
                );
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponeObject("FAIL","Không tồn tại hồ sơ","")
        );
    }

    @Override
    public ResponseEntity<ResponeObject> huyHoSoDangKyByQLDB(Long idHoso) {
        HoSoDangKyEntity hoSoDangKyEntity = hoSoDangKyRepository.findById(idHoso).orElse(null);
        if(hoSoDangKyEntity!=null)
        {
            if(hoSoDangKyEntity.getTrangThai().equals("Chờ duyệt"))
            {
                hoSoDangKyEntity.setTrangThai("Hủy");
                hoSoDangKyRepository.save(hoSoDangKyEntity);
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponeObject("OK","Đã hủy hồ sơ",""));

            }
            else{
                return ResponseEntity.status(HttpStatus.CONFLICT).body(
                        new ResponeObject("FAIL","Hồ sơ đã được "+ (hoSoDangKyEntity.getTrangThai()).toLowerCase()+ " trước đó","")
                );
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponeObject("FAIL","Không tồn tại hồ sơ","")
        );




    }

    @Override
    public ResponseEntity<ResponeObject> getHoSoDangKy_DoiBong(Long idDoibong) {

        DoiBongEntity doiBongEntity = doiBongRepository.findById(idDoibong).orElse(null);
        if(doiBongEntity!=null)
        {
            List<HoSoDangKyEntity> hoSoDangKyEntityList = doiBongEntity.getCacHoSoDangKy();
            List<HoSoDangKyDTO> listHoSoDangKyDTO = new ArrayList<>();
            if(hoSoDangKyEntityList.size() == 0 || hoSoDangKyEntityList == null)
            {
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponeObject("OK","Chưa có hồ sơ đăng ký nào","")
                );
            }
            else{
                for(HoSoDangKyEntity hoSoDangKyEntity : hoSoDangKyEntityList)
                {
                    try
                    {
                        HoSoDangKyDTO hoSoDangKyDTO = GenericConverter.convert(hoSoDangKyEntity, HoSoDangKyDTO.class);
                        hoSoDangKyDTO.setId_giai(hoSoDangKyEntity.getMuaGiai().getId());
                        hoSoDangKyDTO.setId_quanly(hoSoDangKyEntity.getQuanLyDkiHoSo().getId());
                        hoSoDangKyDTO.setTen_quanly(hoSoDangKyEntity.getQuanLyDkiHoSo().getHoTen());
                        hoSoDangKyDTO.setId_doibong(hoSoDangKyEntity.getDoiBong().getId());
                        hoSoDangKyDTO.setTen_doibong(hoSoDangKyEntity.getDoiBong().getTen());
                        hoSoDangKyDTO.setGhiChu(hoSoDangKyEntity.getGhiChu());
//                        DoiBongEntity doiBongEntity = hoSoDangKyEntity.getDoiBong();

                        List<CauThuDTO> listCauThuDTO = new ArrayList<>();
                        for(CauThuEntity cauthu: hoSoDangKyEntity.getCacCauThu())
                        {
                            // chỉ lấy những cầu thủ trong hồ sơ đăng kí với trạng thái còn thi đấu ở đội bóng đó

                            CauThuDoiBongEntity cauThuDoiBongEntity = cauThuDoiBongRepository.findCauThuDoiBongEntityByCauThuDBAndDoiBongCT(cauthu,doiBongEntity);

                            Integer isInTeam = cauThuDoiBongEntity.isInTeam();
                            if(isInTeam == 1)
                            {
                                CauThuDTO cauThuDTO = GenericConverter.convert(cauthu, CauThuDTO.class);
                                List<ViTriEntity> listVitri = cauthu.getCacViTri();
                                List<String> str_roles = new ArrayList<>();
                                listVitri.forEach(vitri->{
                                    str_roles.add(vitri.getCode());
                                });
                                cauThuDTO.setViTri(str_roles.toArray(new String[0]));
                                cauThuDTO.setAge(cauthu.calculateAge());
                                cauThuDTO.setSoAo(cauThuDoiBongEntity.getSoAo());
                                listCauThuDTO.add(cauThuDTO);
                            }
                        }
                        hoSoDangKyDTO.setDsCauThuDangKy(listCauThuDTO);
                        listHoSoDangKyDTO.add(hoSoDangKyDTO);
                    }catch (Exception ex)
                    {
                        System.out.println(ex.getMessage());
                    }
                }
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponeObject("OK","SUCCESS",listHoSoDangKyDTO)
                );
            }
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponeObject("FAIL","Đội bóng không tồn tại","")
        );

    }
}
