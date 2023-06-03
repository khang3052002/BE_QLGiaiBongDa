package backend.qlgiaibongda.service.iplm;

import backend.qlgiaibongda.converter.GenericConverter;
import backend.qlgiaibongda.dto.DangKyThamGiaGiaiDTO;
import backend.qlgiaibongda.dto.MuaGiaiDTO;
import backend.qlgiaibongda.dto.QuyDinhDTO.QuyDinhCauThuDTO;
import backend.qlgiaibongda.dto.QuyDinhDTO.QuyDinhSoLuongDoiDTO;
import backend.qlgiaibongda.dto.QuyDinhDTO.QuyDinhTinhDiemDTO;
import backend.qlgiaibongda.dto.ResponeObject;
import backend.qlgiaibongda.entity.*;
import backend.qlgiaibongda.repository.*;
import backend.qlgiaibongda.service.IMuaGiaiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class MuaGiaiService implements IMuaGiaiService {
    @Autowired
    private QuanLiRepository quanLiRepository;
    @Autowired
    private MuaGiaiRepository muaGiaiRepository;
    @Autowired
    private QuyDinhMuaGiaiRepository quyDinhMuaGiaiRepository;
    @Autowired
    private QuyDinhCauThuRepository quyDinhCauThuRepository;
    @Autowired
    private QuyDinhTinhDiemRepository quyDinhTinhDiemRepository;
    @Autowired
    private QuyDinhSoLuongDoiRepository quyDinhSoLuongDoiRepository;
    @Autowired
    private HoSoDangKyRepository hoSoDangKyRepository;
    @Autowired
    private DoiBongRepository doiBongRepository;
    @Autowired
    private CauThuRepository cauThuRepository;
    @Override
    public ResponseEntity<ResponeObject> createLeague(MuaGiaiDTO muaGiaiDTO) {
        try
        {
            MuaGiaiEntity muaGiaiEntity = GenericConverter.convert(muaGiaiDTO, MuaGiaiEntity.class);
            QuanLyEntity quanLyMuaGiai = quanLiRepository.findById(muaGiaiDTO.getId_nguoitao()).get();
            if(quanLyMuaGiai != null)
            {
                muaGiaiEntity.setQuanLyMuaGiai(quanLyMuaGiai);
            }

            QuyDinhCauThuEntity quyDinhCauThuEntity = GenericConverter.convert(muaGiaiDTO.getQuyDinhCauThu(), QuyDinhCauThuEntity.class);
            QuyDinhTinhDiemEntity quyDinhTinhDiemEntity = GenericConverter.convert(muaGiaiDTO.getQuyDinhTinhDiem(), QuyDinhTinhDiemEntity.class);
            QuyDinhSoLuongDoiEntity quyDinhSoLuongDoiEntity = GenericConverter.convert(muaGiaiDTO.getQuyDinhSoLuongDoi(),QuyDinhSoLuongDoiEntity.class);

            quyDinhCauThuRepository.save(quyDinhCauThuEntity);
            quyDinhTinhDiemRepository.save(quyDinhTinhDiemEntity);
            quyDinhSoLuongDoiRepository.save(quyDinhSoLuongDoiEntity);

            // Set quy dinh cho mua giai
            QuyDinhMuaGiaiEntity quyDinhMuaGiai = new QuyDinhMuaGiaiEntity();

            quyDinhMuaGiai.setQuyDinhCauThu(quyDinhCauThuEntity);
            quyDinhMuaGiai.setQuyDinhTinhDiem(quyDinhTinhDiemEntity);
            quyDinhMuaGiai.setQuyDinhSoLuongDoi(quyDinhSoLuongDoiEntity);


            quyDinhMuaGiaiRepository.save(quyDinhMuaGiai);

            muaGiaiEntity.setQuyDinhMuaGiai(quyDinhMuaGiai);

            muaGiaiRepository.save(muaGiaiEntity);

            return ResponseEntity.status(HttpStatus.OK).body(new ResponeObject("OK","Create league succesful",muaGiaiDTO));


        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ResponeObject("FAIL","Create league fail",""));


    }

    @Override
    public ResponseEntity<ResponeObject> getAllLeague() {
        List<MuaGiaiEntity> listLeague = muaGiaiRepository.findAll();
        if(listLeague!=null)
        {
            List<MuaGiaiDTO> listLeagueDTO = new ArrayList<>();
            for(MuaGiaiEntity muaGiai : listLeague)
            {
                try
                {
                    MuaGiaiDTO muaGiaiDTO = GenericConverter.convert(muaGiai, MuaGiaiDTO.class);
                    muaGiaiDTO.setId_nguoitao(muaGiai.getQuanLyMuaGiai().getId());
                    QuyDinhCauThuDTO quyDinhCauThuDTO = GenericConverter.convert(muaGiai.getQuyDinhMuaGiai().getQuyDinhCauThu(),QuyDinhCauThuDTO.class);
                    QuyDinhTinhDiemDTO quyDinhTinhDiemDTO = GenericConverter.convert(muaGiai.getQuyDinhMuaGiai().getQuyDinhTinhDiem(),QuyDinhTinhDiemDTO.class);

                    muaGiaiDTO.setQuyDinhCauThu(quyDinhCauThuDTO);
                    muaGiaiDTO.setQuyDinhTinhDiem(quyDinhTinhDiemDTO);

                    listLeagueDTO.add(muaGiaiDTO);
                }
                catch (Exception ex)
                {
                    System.out.println(ex.getMessage());
                }

            }

            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponeObject("OK","Get all league succesful",listLeagueDTO)
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponeObject("OK","Get all league fail","")
        );
    }

    @Override
    public ResponseEntity<ResponeObject> getLeagueById(Long id) {
        MuaGiaiEntity muaGiaiEntity = muaGiaiRepository.findById(id).get();
        if(muaGiaiEntity!=null)
        {
            try
            {
                MuaGiaiDTO muaGiaiDTO = GenericConverter.convert(muaGiaiEntity, MuaGiaiDTO.class);
                muaGiaiDTO.setId_nguoitao(muaGiaiEntity.getQuanLyMuaGiai().getId());
                QuyDinhCauThuDTO quyDinhCauThuDTO = GenericConverter.convert(muaGiaiEntity.getQuyDinhMuaGiai().getQuyDinhCauThu(),QuyDinhCauThuDTO.class);
                QuyDinhTinhDiemDTO quyDinhTinhDiemDTO = GenericConverter.convert(muaGiaiEntity.getQuyDinhMuaGiai().getQuyDinhTinhDiem(),QuyDinhTinhDiemDTO.class);

                muaGiaiDTO.setQuyDinhCauThu(quyDinhCauThuDTO);
                muaGiaiDTO.setQuyDinhTinhDiem(quyDinhTinhDiemDTO);
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponeObject("OK","Get all league succesful",muaGiaiDTO)
                );
            }
            catch (Exception ex)
            {
                System.out.println(ex.getMessage());
            }

        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponeObject("FAIL","Get league by id fail","")
        );
    }

    @Override
    public ResponseEntity<ResponeObject> updateLeague(MuaGiaiDTO muaGiaiDTO) {
        MuaGiaiEntity muaGiaiEntity = muaGiaiRepository.findById(muaGiaiDTO.getId()).get();
        if(muaGiaiEntity!=null)
        {
            try
            {
                // set những thuộc tính cơ bản
                muaGiaiEntity.setTen(muaGiaiDTO.getTen());
                muaGiaiEntity.setThoiDiemBatDau(muaGiaiDTO.getThoiDiemBatDau());
                muaGiaiEntity.setThoiDiemKetThuc(muaGiaiDTO.getThoiDiemKetThuc());

                // set lại quy định
                QuyDinhMuaGiaiEntity quyDinhMuaGiaiEntity = muaGiaiEntity.getQuyDinhMuaGiai();
                // lấy được các quy định
                QuyDinhCauThuEntity quyDinhCauThuEntity = quyDinhMuaGiaiEntity.getQuyDinhCauThu();
                QuyDinhTinhDiemEntity quyDinhTinhDiemEntity = quyDinhMuaGiaiEntity.getQuyDinhTinhDiem();
                QuyDinhSoLuongDoiEntity quyDinhSoLuongDoiEntity = quyDinhMuaGiaiEntity.getQuyDinhSoLuongDoi();

                quyDinhCauThuEntity = GenericConverter.convert(muaGiaiDTO.getQuyDinhCauThu(),quyDinhCauThuEntity);
                quyDinhTinhDiemEntity = GenericConverter.convert(muaGiaiDTO.getQuyDinhTinhDiem(),quyDinhTinhDiemEntity);
                quyDinhSoLuongDoiEntity = GenericConverter.convert(muaGiaiDTO.getQuyDinhSoLuongDoi(),quyDinhSoLuongDoiEntity);

                quyDinhTinhDiemRepository.save(quyDinhTinhDiemEntity);
                quyDinhCauThuRepository.save(quyDinhCauThuEntity);
                quyDinhSoLuongDoiRepository.save(quyDinhSoLuongDoiEntity);


                muaGiaiRepository.save(muaGiaiEntity);
                return ResponseEntity.status(HttpStatus.OK).body(new ResponeObject("OK","Update league succesful",muaGiaiDTO));

            }
            catch (Exception ex)
            {
                System.out.println(ex.getMessage());
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponeObject("FAIL","Update league fail",""));

    }

    @Override
    public ResponseEntity<ResponeObject> registerJoinLeague(DangKyThamGiaGiaiDTO dangKyThamGiaGiaiDTO) {
        try
        {
            Long id_muagiai = dangKyThamGiaGiaiDTO.getId_giai();
            Long id_doibong = dangKyThamGiaGiaiDTO.getId_doibong();

            MuaGiaiEntity muaGiaiEntity = muaGiaiRepository.findById(id_muagiai).get();
            DoiBongEntity doiBongEntity = doiBongRepository.findById(dangKyThamGiaGiaiDTO.getId_doibong()).get();

            Long soLuongDoiHienTai = hoSoDangKyRepository.countByTrangThaiAndMuaGiai("Đã duyệt", muaGiaiEntity);
            int soLuongDoiQuyDinh = muaGiaiEntity.getQuyDinhMuaGiai().getQuyDinhSoLuongDoi().getSoLuongDoi();
            boolean isHaveSlot = false;
            if(soLuongDoiHienTai < soLuongDoiQuyDinh) // số lượng đội đã duyệt hiện tại so với số luowjgn đội quy định của giải
            {
                // Cho đăng kí
                isHaveSlot = true;
            }
            boolean isTeamExistsInHSoDangKy_MuaGiai = hoSoDangKyRepository.existsByDoiBongAndMuaGiai(doiBongEntity,muaGiaiEntity);

            if(isHaveSlot == true && isTeamExistsInHSoDangKy_MuaGiai == false)
            {
                // cho Đăng ký
                HoSoDangKyEntity hoSoDangKyEntity = new HoSoDangKyEntity();
                hoSoDangKyEntity.setTrangThai("Chờ duyệt");
                hoSoDangKyEntity.setDoiBong(doiBongEntity);
                hoSoDangKyEntity.setMuaGiai(muaGiaiEntity);
                hoSoDangKyEntity.setQuanLyDkiHoSo(doiBongEntity.getQuanLy());

                Long[] list_id_cauThu = dangKyThamGiaGiaiDTO.getDs_id_cauthu_thamgia();
                List<CauThuEntity> listCauThuThamGia = new ArrayList<>();
                Arrays.asList(list_id_cauThu).forEach(idCauThu ->{
                    CauThuEntity cauThu = cauThuRepository.findById(idCauThu).get();
                    if(cauThu!=null)
                    {
                        listCauThuThamGia.add(cauThu);
                    }
                });

                hoSoDangKyEntity.setCacCauThu(listCauThuThamGia);

                hoSoDangKyRepository.save(hoSoDangKyEntity);
                return ResponseEntity.status(HttpStatus.OK).body(new ResponeObject("OK","Update league succesful",dangKyThamGiaGiaiDTO));

            }
            else{
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new ResponeObject("FAIL","Đã đủ đội đăng ký hoặc đã đăng ký giải này rồi",""));

            }
        }catch (Exception ex)
        {
            System.out.println(ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new ResponeObject("FAIL",ex.getMessage(),""));

        }

    }
}
