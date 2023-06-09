package backend.qlgiaibongda.service.iplm;

import backend.qlgiaibongda.converter.GenericConverter;
import backend.qlgiaibongda.dto.CauThuDTO;
import backend.qlgiaibongda.dto.HoSoDangKyDTO;
import backend.qlgiaibongda.dto.ResponeObject;
import backend.qlgiaibongda.entity.CauThuEntity;
import backend.qlgiaibongda.entity.HoSoDangKyEntity;
import backend.qlgiaibongda.entity.MuaGiaiEntity;
import backend.qlgiaibongda.entity.ViTriEntity;
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

                        List<CauThuDTO> listCauThuDTO = new ArrayList<>();
                        for(CauThuEntity cauthu: hoSoDangKyEntity.getCacCauThu())
                        {
                            CauThuDTO cauThuDTO = GenericConverter.convert(cauthu, CauThuDTO.class);
                            List<ViTriEntity> listVitri = cauthu.getCacViTri();
                            List<String> str_roles = new ArrayList<>();
                            listVitri.forEach(vitri->{
                                str_roles.add(vitri.getCode());
                            });
                            cauThuDTO.setViTri(str_roles.toArray(new String[0]));
                            listCauThuDTO.add(cauThuDTO);

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
                hoSoDangKyDTO.setId_quanly(hoSoDangKyEntity.getQuanLyDkiHoSo().getId());
                hoSoDangKyDTO.setTen_quanly(hoSoDangKyEntity.getQuanLyDkiHoSo().getHoTen());
                hoSoDangKyDTO.setId_doibong(hoSoDangKyEntity.getDoiBong().getId());
                hoSoDangKyDTO.setTen_doibong(hoSoDangKyEntity.getDoiBong().getTen());

                List<CauThuDTO> listCauThuDTO = new ArrayList<>();
                for (CauThuEntity cauthu : hoSoDangKyEntity.getCacCauThu()) {
                    CauThuDTO cauThuDTO = GenericConverter.convert(cauthu, CauThuDTO.class);
                    List<ViTriEntity> listVitri = cauthu.getCacViTri();
                    List<String> str_roles = new ArrayList<>();
                    listVitri.forEach(vitri -> {
                        str_roles.add(vitri.getCode());
                    });
                    cauThuDTO.setViTri(str_roles.toArray(new String[0]));
                    listCauThuDTO.add(cauThuDTO);

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
            if(hoSoDangKyEntity.getTrangThai().equals("Chờ duyệt"))
            {
                hoSoDangKyEntity.setTrangThai("Đã duyệt");
                hoSoDangKyRepository.save(hoSoDangKyEntity);
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponeObject("OK","Đã duyệt hồ sơ","")
                );
            }
            else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponeObject("FAIL","Hồ sơ đã được duyệt trước đó","")
                );
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponeObject("FAIL","Không tồn tại hồ sơ","")
        );
    }
}
