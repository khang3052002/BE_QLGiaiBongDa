package backend.qlgiaibongda.service.iplm;

import backend.qlgiaibongda.converter.GenericConverter;
import backend.qlgiaibongda.dto.BXH_DoiBongDTO;
import backend.qlgiaibongda.dto.DangKyThamGiaGiaiDTO;
import backend.qlgiaibongda.dto.MuaGiaiDTO;
import backend.qlgiaibongda.dto.Output.DSMuaGiaiOutput;
import backend.qlgiaibongda.dto.QuyDinhDTO.QuyDinhCauThuDTO;
import backend.qlgiaibongda.dto.QuyDinhDTO.QuyDinhSoLuongDoiDTO;
import backend.qlgiaibongda.dto.QuyDinhDTO.QuyDinhTinhDiemDTO;
import backend.qlgiaibongda.dto.ResponeObject;
import backend.qlgiaibongda.entity.*;
import backend.qlgiaibongda.repository.*;
import backend.qlgiaibongda.repository.MuaGiaiRepository.MuaGiaiRepository;
import backend.qlgiaibongda.service.IBangXepHangService;
import backend.qlgiaibongda.service.IMuaGiaiService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private IBangXepHangService bangXepHangService;
    @Override
    public ResponseEntity<ResponeObject> createLeagueWithNewRule(MuaGiaiDTO muaGiaiDTO) {
        try
        {
            MuaGiaiEntity muaGiaiEntity = GenericConverter.convert(muaGiaiDTO, MuaGiaiEntity.class);
            QuanLyEntity quanLyMuaGiai = quanLiRepository.findById(muaGiaiDTO.getId_nguoitao()).orElse(null);
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


            quyDinhMuaGiai=quyDinhMuaGiaiRepository.save(quyDinhMuaGiai);

            muaGiaiEntity.setQuyDinhMuaGiai(quyDinhMuaGiai);
            muaGiaiEntity.setTrangThai(0);
            muaGiaiEntity = muaGiaiRepository.save(muaGiaiEntity);
            muaGiaiDTO.setId(muaGiaiEntity.getId());
            muaGiaiDTO.setId_quydinh(quyDinhMuaGiai.getId());
            return ResponseEntity.status(HttpStatus.OK).body(new ResponeObject("OK","Create league succesful",muaGiaiDTO));


        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ResponeObject("FAIL","Create league fail",""));


    }




    public ResponseEntity<ResponeObject> getAllLeague(Pageable pageable) {
        List<MuaGiaiEntity> listLeague = muaGiaiRepository.findAll(pageable).getContent();
        Integer totalItem = totalItem();
        DSMuaGiaiOutput dsMuaGiaiOutput = new DSMuaGiaiOutput();
        dsMuaGiaiOutput.setPage(pageable.getPageNumber()+1);
        dsMuaGiaiOutput.setTotalPage((Integer)(totalItem / pageable.getPageSize()) +
                (totalItem % pageable.getPageSize() > 0 ? 1 : 0));

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
                    QuyDinhSoLuongDoiDTO quyDinhSoLuongDoiDTO = GenericConverter.convert(muaGiai.getQuyDinhMuaGiai().getQuyDinhSoLuongDoi(),QuyDinhSoLuongDoiDTO.class);

                    muaGiaiDTO.setQuyDinhSoLuongDoi(quyDinhSoLuongDoiDTO);
                    muaGiaiDTO.setQuyDinhCauThu(quyDinhCauThuDTO);
                    muaGiaiDTO.setQuyDinhTinhDiem(quyDinhTinhDiemDTO);

                    listLeagueDTO.add(muaGiaiDTO);
                }
                catch (Exception ex)
                {
                    System.out.println(ex.getMessage());
                }

            }
            dsMuaGiaiOutput.setListResult(listLeagueDTO);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponeObject("OK","Get all league succesful",dsMuaGiaiOutput)
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponeObject("OK","Get all league fail","")
        );
    }

    @Override
    public ResponseEntity<ResponeObject> getLeagueById(Long id) {
        MuaGiaiEntity muaGiaiEntity = muaGiaiRepository.findById(id).orElse(null);
        if(muaGiaiEntity!=null)
        {
            try
            {
                MuaGiaiDTO muaGiaiDTO = GenericConverter.convert(muaGiaiEntity, MuaGiaiDTO.class);
                muaGiaiDTO.setId_nguoitao(muaGiaiEntity.getQuanLyMuaGiai().getId());
                QuyDinhCauThuDTO quyDinhCauThuDTO = GenericConverter.convert(muaGiaiEntity.getQuyDinhMuaGiai().getQuyDinhCauThu(),QuyDinhCauThuDTO.class);
                QuyDinhTinhDiemDTO quyDinhTinhDiemDTO = GenericConverter.convert(muaGiaiEntity.getQuyDinhMuaGiai().getQuyDinhTinhDiem(),QuyDinhTinhDiemDTO.class);
                QuyDinhSoLuongDoiDTO quyDinhSoLuongDoiDTO = GenericConverter.convert(muaGiaiEntity.getQuyDinhMuaGiai().getQuyDinhSoLuongDoi(),QuyDinhSoLuongDoiDTO.class);

                muaGiaiDTO.setQuyDinhSoLuongDoi(quyDinhSoLuongDoiDTO);
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
        MuaGiaiEntity muaGiaiEntity = muaGiaiRepository.findById(muaGiaiDTO.getId()).orElse(null);
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

            MuaGiaiEntity muaGiaiEntity = muaGiaiRepository.findById(id_muagiai).orElse(null);
            DoiBongEntity doiBongEntity = doiBongRepository.findById(id_doibong).orElse(null);

            if(muaGiaiEntity == null || doiBongEntity == null)
            {
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(
                        new ResponeObject("FAIL","Mùa giải hoặc đội bóng không tồn tại",""));

            }

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

                Long[] list_id_cauThu = dangKyThamGiaGiaiDTO.getDs_id_cauthu_thamgia();
                List<CauThuEntity> listCauThuThamGia = new ArrayList<>();
                Arrays.asList(list_id_cauThu).forEach(idCauThu ->{
                    CauThuEntity cauThu = cauThuRepository.findById(idCauThu).orElse(null);
                    if(cauThu!=null)
                    {
                        listCauThuThamGia.add(cauThu);
                    }
                });
                Map<Integer,String> mapErr = checkQuyDinhSoLuong(listCauThuThamGia,muaGiaiEntity);
                boolean checkQuyDinhTuoi = checkQuyDinhTuoi(listCauThuThamGia,muaGiaiEntity);
//                boolean checkQuyDinhSoLuong = true;
                if(mapErr.size() > 1)
                {
                    if(mapErr.get(1)!=null)
                    {
                        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                                .body(new ResponeObject("FAIL","Đăng ký thất bại. "+mapErr.get(1),""));

                    }
                    if(mapErr.get(2)!=null)
                    {
                        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                                .body(new ResponeObject("FAIL","Đăng ký thất bại. "+mapErr.get(2),""));

                    }
                    if(mapErr.get(3)!=null)
                    {
                        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                                .body(new ResponeObject("FAIL","Đăng ký thất bại. "+mapErr.get(3),""));

                    }
                }

                if(checkQuyDinhTuoi == false)
                {
                    return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                            .body(new ResponeObject("FAIL","Đăng ký thất bại. Vi phạm quy định về tuổi cầu thủ",""));

                }


                HoSoDangKyEntity hoSoDangKyEntity = new HoSoDangKyEntity();
                hoSoDangKyEntity.setTrangThai("Chờ duyệt");
                hoSoDangKyEntity.setDoiBong(doiBongEntity);
                hoSoDangKyEntity.setMuaGiai(muaGiaiEntity);
                hoSoDangKyEntity.setQuanLyDkiHoSo(doiBongEntity.getQuanLy());

                hoSoDangKyEntity.setCacCauThu(listCauThuThamGia);

                hoSoDangKyRepository.save(hoSoDangKyEntity);
                return ResponseEntity.status(HttpStatus.OK).body(new ResponeObject("OK","Join league succesful",dangKyThamGiaGiaiDTO));

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

    private boolean checkQuyDinhTuoi(List<CauThuEntity> listCauThuThamGia, MuaGiaiEntity muaGiaiEntity) {
        boolean check =true;
        try
        {
            QuyDinhCauThuDTO quyDinhCauThuDTO = GenericConverter.convert(muaGiaiEntity.getQuyDinhMuaGiai().getQuyDinhCauThu(),QuyDinhCauThuDTO.class);

            int tuoiToiDa = quyDinhCauThuDTO.getTuoiToiDa();
            int tuoiToiThieu = quyDinhCauThuDTO.getTuoiToiThieu();
            for(CauThuEntity player: listCauThuThamGia)
            {
                int age = player.calculateAge();
                if(age < tuoiToiThieu || age > tuoiToiDa)
                {
                    check = false;
                    break;
                }
            }

        }
        catch (Exception ex)
        {
            System.out.println(ex);
        }

        return  check;
    }

    Map<Integer,String> checkQuyDinhSoLuong(List<CauThuEntity> listPlayer, MuaGiaiEntity muaGiaiEntity)
    {
        Integer size = listPlayer.size();
        Map<Integer,String> mapErr = new HashMap<>();
        mapErr.put(0,"");
        try {
            QuyDinhCauThuDTO quyDinhCauThuDTO = GenericConverter.convert(muaGiaiEntity.getQuyDinhMuaGiai().getQuyDinhCauThu(),QuyDinhCauThuDTO.class);
            if(size > quyDinhCauThuDTO.getSoLuongCauThuToiDa())
            {
                mapErr.put(1,"Vượt quá giới hạn số lượng cầu thủ cho phép");
            }
            else if(size < quyDinhCauThuDTO.getSoLuongCauThuToiThieu())
            {
                mapErr.put(2,"Không đủ số lượng cầu thủ tối thiểu tham gia giải");

            }
            else{
                // check quy định cầu thủ nhập tịch
                // count số lượng cầu thủ ngoại binh
                int countNgoaiBinh = 0 ;
                for(CauThuEntity player: listPlayer)
                {
                    if(player.getLoaiCauThu().equals("Ngoại binh"))
                    {
                        countNgoaiBinh++;
                    }
                }
                if(countNgoaiBinh > quyDinhCauThuDTO.getSoLuongCauThuNuocNgoaiToiDa())
                {
                    mapErr.put(3,"Số lượng ngoại binh vướt quá giới hạn quy định");
                }

            }
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        return mapErr;

    }

    public Page<MuaGiaiEntity> findLeaguesWithFiltered(Pageable pageable, String keyword, Integer trangthai) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<MuaGiaiEntity> cq = cb.createQuery(MuaGiaiEntity.class);
        Root<MuaGiaiEntity> root = cq.from(MuaGiaiEntity.class);


        // Create a list to store the predicates
        List<Predicate> predicates = new ArrayList<>();

        if(keyword !=null)
        {
            Predicate keywordPredicate = cb.like(root.get("ten"),"%"+keyword+"%");
            predicates.add(keywordPredicate);
        }
        if(trangthai != null)
        {
            Predicate trangThaiPredicate = cb.equal(root.get("trangThai"),trangthai);
            predicates.add(trangThaiPredicate);
        }

        cq.where(cb.and(predicates.toArray(new Predicate[0])));
        TypedQuery<MuaGiaiEntity> query=entityManager.createQuery(cq);
        query.setFirstResult((int)pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());

        List<MuaGiaiEntity> resultList = query.getResultList();

        CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
        countQuery.select(cb.count(countQuery.from(MuaGiaiEntity.class)));
        Long totalCount = entityManager.createQuery(countQuery).getSingleResult();

        return new PageImpl<>(resultList, pageable, totalCount);

    }

    public int totalItem() {
        return (int) muaGiaiRepository.count();
    }

    @Override
    @Transactional
    public ResponseEntity<ResponeObject> getRankingOfLeague(Long idMuagiai) {
        // Get MuaGiaiEntity
        MuaGiaiEntity muaGiaiEntity = muaGiaiRepository.findById(idMuagiai).orElse(null);
        if(muaGiaiEntity!=null)
        {
            BangXepHangEntity bxhEntity = muaGiaiEntity.getBxh();
            List<BXH_DoiBongDTO> bxh = null;
            if(bxhEntity == null)
            {
                // Tạo bảng xếp hạng
                // save bảng xếp hạng vào mua giải đó
                boolean result = bangXepHangService.CreateRanking(muaGiaiEntity);
                System.out.println(result);
                if(result == true)
                {
                    bxh = bangXepHangService.getRanking(idMuagiai);
                }
            }
            else{
                bxh = bangXepHangService.getRanking(idMuagiai);
            }
            if(bxh==null)
            {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponeObject("Fail","Not found ranking of league",""));
            }
            else{
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponeObject("OK","Get ranking succeed",bxh));
            }

        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponeObject("Fail","Test",""));

    }

    @Override
    public ResponseEntity<ResponeObject> createLeagueWithOldRule(MuaGiaiDTO muaGiaiDTO) {
        try
        {
            MuaGiaiEntity muaGiaiEntity = GenericConverter.convert(muaGiaiDTO, MuaGiaiEntity.class);
            QuanLyEntity quanLyMuaGiai = quanLiRepository.findById(muaGiaiDTO.getId_nguoitao()).orElse(null);
            if(quanLyMuaGiai != null)
            {
                muaGiaiEntity.setQuanLyMuaGiai(quanLyMuaGiai);
            }



            // Set quy dinh cho mua giai
            QuyDinhMuaGiaiEntity quyDinhMuaGiai = quyDinhMuaGiaiRepository.findById(muaGiaiDTO.getId_quydinh()).orElse(null);

            if(quyDinhMuaGiai !=null)
            {
                muaGiaiEntity.setQuyDinhMuaGiai(quyDinhMuaGiai);
                muaGiaiEntity.setTrangThai(0);
                muaGiaiEntity = muaGiaiRepository.save(muaGiaiEntity);

                QuyDinhCauThuDTO quyDinhCauThuDTO = GenericConverter.convert(quyDinhMuaGiai.getQuyDinhCauThu(),QuyDinhCauThuDTO.class);
                QuyDinhTinhDiemDTO quyDinhTinhDiemDTO = GenericConverter.convert(quyDinhMuaGiai.getQuyDinhTinhDiem(), QuyDinhTinhDiemDTO.class);
                QuyDinhSoLuongDoiDTO quyDinhSoLuongDoiDTO = GenericConverter.convert(quyDinhMuaGiai.getQuyDinhSoLuongDoi(), QuyDinhSoLuongDoiDTO.class);

                muaGiaiDTO.setId(muaGiaiEntity.getId());
                muaGiaiDTO.setQuyDinhSoLuongDoi(quyDinhSoLuongDoiDTO);
                muaGiaiDTO.setQuyDinhTinhDiem(quyDinhTinhDiemDTO);
                muaGiaiDTO.setQuyDinhCauThu(quyDinhCauThuDTO);

                return ResponseEntity.status(HttpStatus.OK).body(new ResponeObject("OK","Create league succesful",muaGiaiDTO));

            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponeObject("FAIL","Tạo giải thất bại, quy định mùa giải không tồn tại",""));
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ResponeObject("FAIL","Create league fail",""));


    }

    @Override
//    @Transactional
    public ResponseEntity<ResponeObject> startLeague(Long idMuagiai) {
        MuaGiaiEntity muaGiaiEntity = muaGiaiRepository.findById(idMuagiai).orElse(null);
        if(muaGiaiEntity!=null)
        {
            BangXepHangEntity bxhEntity = muaGiaiEntity.getBxh();
            List<BXH_DoiBongDTO> bxh = null;
            if(bxhEntity == null)
            {
                // Tạo bảng xếp hạng
                // save bảng xếp hạng vào mua giải đó
                boolean result = bangXepHangService.CreateRanking(muaGiaiEntity);
                System.out.println(result);
                if(result == true)
                {
                    muaGiaiEntity.setTrangThai(1);
                    muaGiaiRepository.save(muaGiaiEntity);
                    return ResponseEntity.status(HttpStatus.OK).body(
                            new ResponeObject("OK","Kích hoạt mùa giải thành công",""));
                }
                else{
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                            new ResponeObject("OK","Tạo bảng xếp hạng thất bại",""));
                }

            }
            else{
                return ResponseEntity.status(HttpStatus.CONFLICT).body(
                        new ResponeObject("FAIL","Bảng xếp hạng của mùa giải đã được tạo trước đó",""));
            }



        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponeObject("Fail","Không tìm thấy mùa giải này",""));
    }

    public ResponseEntity<ResponeObject> getLeagueOnRequest(Pageable pageable,String keyword, Integer trangThai) {
//        List<MuaGiaiEntity> listMuaGiai = muaGiaiRepository.findByTenContainsIgnoreCase(keyword,trangThai);
        Page<MuaGiaiEntity> pageListMuaGiai = findLeaguesWithFiltered(pageable,keyword,trangThai);
        List<MuaGiaiEntity> listMuaGiai = pageListMuaGiai.getContent();
//        Integer totalItem = pageListMuaGiai.;
        DSMuaGiaiOutput dsMuaGiaiOutput = new DSMuaGiaiOutput();
        dsMuaGiaiOutput.setPage(pageable.getPageNumber()+1);
        dsMuaGiaiOutput.setTotalPage(pageListMuaGiai.getTotalPages());

        if(listMuaGiai.size()>0)
        {
            List<MuaGiaiDTO> listLeagueDTO = new ArrayList<>();
            for(MuaGiaiEntity muaGiai : listMuaGiai)
            {
                try
                {
                    MuaGiaiDTO muaGiaiDTO = GenericConverter.convert(muaGiai, MuaGiaiDTO.class);
                    muaGiaiDTO.setId_nguoitao(muaGiai.getQuanLyMuaGiai().getId());
                    QuyDinhCauThuDTO quyDinhCauThuDTO = GenericConverter.convert(muaGiai.getQuyDinhMuaGiai().getQuyDinhCauThu(),QuyDinhCauThuDTO.class);
                    QuyDinhTinhDiemDTO quyDinhTinhDiemDTO = GenericConverter.convert(muaGiai.getQuyDinhMuaGiai().getQuyDinhTinhDiem(),QuyDinhTinhDiemDTO.class);
                    QuyDinhSoLuongDoiDTO quyDinhSoLuongDoiDTO = GenericConverter.convert(muaGiai.getQuyDinhMuaGiai().getQuyDinhSoLuongDoi(),QuyDinhSoLuongDoiDTO.class);

                    muaGiaiDTO.setQuyDinhSoLuongDoi(quyDinhSoLuongDoiDTO);
                    muaGiaiDTO.setQuyDinhCauThu(quyDinhCauThuDTO);
                    muaGiaiDTO.setQuyDinhTinhDiem(quyDinhTinhDiemDTO);

                    listLeagueDTO.add(muaGiaiDTO);
                }
                catch (Exception ex)
                {
                    System.out.println(ex.getMessage());
                }
            }
            dsMuaGiaiOutput.setListResult(listLeagueDTO);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponeObject("OK","Get all league succesful",dsMuaGiaiOutput)
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponeObject("OK","Not exsists league with key "+ keyword,"")
        );
    }
}
