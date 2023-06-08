package backend.qlgiaibongda.service.iplm;

import backend.qlgiaibongda.converter.GenericConverter;
import backend.qlgiaibongda.dto.BXH_DoiBongDTO;
import backend.qlgiaibongda.entity.*;
import backend.qlgiaibongda.entity.bxh_doibong.BXHDoiBongEntity;
import backend.qlgiaibongda.entity.bxh_doibong.BXHDoiBongKey;
import backend.qlgiaibongda.repository.BXHDoiBongRepository;
import backend.qlgiaibongda.repository.BangXepHangRepository;
import backend.qlgiaibongda.repository.HoSoDangKyRepository;
import backend.qlgiaibongda.repository.MuaGiaiRepository.MuaGiaiRepository;
import backend.qlgiaibongda.repository.TranDauRepository;
import backend.qlgiaibongda.service.IBangXepHangService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class BangXepHangService implements IBangXepHangService {
    @Autowired
    private BangXepHangRepository bangXepHangRepository;
    @Autowired
    private MuaGiaiRepository muaGiaiRepository;
    @Autowired
    private HoSoDangKyRepository hoSoDangKyRepository;
    @Autowired
    private BXHDoiBongRepository bxhDoiBongRepository;
    @Autowired
    private TranDauRepository tranDauRepository;
    @Autowired
    private EntityManager entityManager;
    @Override
    @Transactional
    public boolean CreateRanking(MuaGiaiEntity muaGiai) {
        // Insert vào bảng "bangxephang"
        Long soLuongDoiHienTai = hoSoDangKyRepository.countByTrangThaiAndMuaGiai("Đã duyệt", muaGiai);
        int soLuongDoiQuyDinh = muaGiai.getQuyDinhMuaGiai().getQuyDinhSoLuongDoi().getSoLuongDoi();
        if(soLuongDoiHienTai == soLuongDoiQuyDinh) // số lượng đội đã duyệt hiện tại so với số luowjgn đội quy định của giải
        {
            // Cho đăng kí
            BangXepHangEntity bangXepHangEntity = new BangXepHangEntity();
            bangXepHangEntity = bangXepHangRepository.save(bangXepHangEntity);

            // Lưu từng đội trong HoSoDangKy giai vao BXH_DoiBong
            muaGiai.setBxh(bangXepHangEntity);
            muaGiaiRepository.save(muaGiai);

            List<HoSoDangKyEntity> listHoSoDangKyEntity = hoSoDangKyRepository.findByTrangThaiAndMuaGiai("Đã duyệt",muaGiai);
            List<BXHDoiBongEntity> bxhDoiBongEntityList = new ArrayList<>();
            for (HoSoDangKyEntity hoSoDangKyEntity: listHoSoDangKyEntity)
            {
                DoiBongEntity doiBongEntity = hoSoDangKyEntity.getDoiBong();
                // Set key
                BXHDoiBongKey key = new BXHDoiBongKey(doiBongEntity.getId(), bangXepHangEntity.getId());
                BXHDoiBongEntity bxhDoiBongEntity = new BXHDoiBongEntity();
                bxhDoiBongEntity.setKey(key);
                bxhDoiBongEntity.setDoiBong(doiBongEntity);
                bxhDoiBongEntity.setBXH(bangXepHangEntity);

                bxhDoiBongEntity.setTranThang(0);
                bxhDoiBongEntity.setTranHoa(0);
                bxhDoiBongEntity.setTranThua(0);
                bxhDoiBongEntity.setPhaLuoi(0);
                bxhDoiBongEntity.setThungLuoi(0);
                bxhDoiBongEntity.setHieuSo(0);
                bxhDoiBongEntity.setDiem(0);
                bxhDoiBongEntity.setVong(0);
                bxhDoiBongEntityList.add(bxhDoiBongEntity);

            }
            bxhDoiBongRepository.saveAll(bxhDoiBongEntityList);
            //isHaveSlot = true;
            return true;
        }


        return false;
    }

    @Override
    @Transactional
    public boolean UpdateRanking(Long id_trandau) {
        TranDauEntity tranDauEntity = tranDauRepository.findById(id_trandau).get();
        if(tranDauEntity!=null)
        {
            KetQuaTranDauEntity ketQuaTranDauEntity = tranDauEntity.getKetQuaTranDau();
            if(!ketQuaTranDauEntity.getTrangThai().equals("Đã kết thúc"))
            {
                return false;
            }
            DoiBongEntity doiNha = ketQuaTranDauEntity.getDoiNha();
            DoiBongEntity doiKhach = ketQuaTranDauEntity.getDoiKhach();

            Integer sbtDoiNha = ketQuaTranDauEntity.getSbtDoiNha();
            Integer sbtDoiKhach = ketQuaTranDauEntity.getSbtDoiKhach();

            LichThiDauEntity lichThiDauEntity = tranDauEntity.getLichThiDau();
            MuaGiaiEntity muaGiaiEntity = lichThiDauEntity.getMuaGiai();
            BangXepHangEntity bangXepHangEntity = muaGiaiEntity.getBxh();


            BXHDoiBongKey keyDoiNha = new BXHDoiBongKey(doiNha.getId(), bangXepHangEntity.getId());
            BXHDoiBongEntity xhDoiNha = bxhDoiBongRepository.findById(keyDoiNha).get();

            BXHDoiBongKey keyDoiKhach = new BXHDoiBongKey(doiKhach.getId(), bangXepHangEntity.getId());
            BXHDoiBongEntity xhDoiKhach = bxhDoiBongRepository.findById(keyDoiKhach).get();



            //handle thang, hoa, thua
            if(sbtDoiNha > sbtDoiKhach) // doi nha WIN, sbt Nha > sbt Khach
            {
                // set Thang - Thua
                xhDoiNha.setTranThang(xhDoiNha.getTranThang()+1);
                xhDoiKhach.setTranThua(xhDoiKhach.getTranThua()+1);
            }
            else if(sbtDoiNha < sbtDoiKhach) // doi nha THUA 1-2
            {
                // set Thang - Thua
                xhDoiNha.setTranThua(xhDoiNha.getTranThua()+1);
                xhDoiKhach.setTranThang(xhDoiKhach.getTranThang()+1);
//                // set pha-thung luoi cho doi NHA
//                xhDoiNha.setPhaLuoi(xhDoiNha.getPhaLuoi()+sbtDoiNha);
//                xhDoiNha.setThungLuoi(xhDoiNha.getThungLuoi()+sbtDoiKhach);
//
//                // set pha-thung luoi cho doi KHACH
//                xhDoiKhach.setPhaLuoi(xhDoiKhach.getPhaLuoi()+sbtDoiKhach);
//                xhDoiKhach.setThungLuoi(xhDoiKhach.getThungLuoi()+sbtDoiNha);
            }
            else{ // 2 doi HOA
                // set Thang - Thua
                xhDoiNha.setTranHoa(xhDoiNha.getTranHoa()+1);
                xhDoiKhach.setTranHoa(xhDoiKhach.getTranHoa()+1);
//                // set pha-thung luoi cho doi NHA
//                xhDoiNha.setPhaLuoi(xhDoiNha.getPhaLuoi()+sbtDoiNha);
//                xhDoiNha.setThungLuoi(xhDoiNha.getThungLuoi()+sbtDoiKhach);
//
//                // set pha-thung luoi cho doi KHACH
//                xhDoiKhach.setPhaLuoi(xhDoiKhach.getPhaLuoi()+sbtDoiKhach);
//                xhDoiKhach.setThungLuoi(xhDoiKhach.getThungLuoi()+sbtDoiNha);
            }

            // set pha-thung luoi cho doi NHA
            xhDoiNha.setPhaLuoi(xhDoiNha.getPhaLuoi()+sbtDoiNha);
            xhDoiNha.setThungLuoi(xhDoiNha.getThungLuoi()+sbtDoiKhach);

            // set pha-thung luoi cho doi KHACH
            xhDoiKhach.setPhaLuoi(xhDoiKhach.getPhaLuoi()+sbtDoiKhach);
            xhDoiKhach.setThungLuoi(xhDoiKhach.getThungLuoi()+sbtDoiNha);

            QuyDinhTinhDiemEntity quyDinhTinhDiem = muaGiaiEntity.getQuyDinhMuaGiai().getQuyDinhTinhDiem();

            // set laji coojt dierm
            xhDoiNha.setDiem(
                    xhDoiNha.getTranThang()* quyDinhTinhDiem.getThang() + xhDoiNha.getTranHoa()
            );
            xhDoiKhach.setDiem(
                    xhDoiKhach.getTranThang()* quyDinhTinhDiem.getThang() + xhDoiKhach.getTranHoa()
            );

            // set hieu so
            // Tinh toan hieu so
            Integer hieuSoDoinha = xhDoiNha.getPhaLuoi() - xhDoiNha.getThungLuoi();
            Integer hieuSoDoiKhach = xhDoiKhach.getPhaLuoi() - xhDoiKhach.getThungLuoi();
            xhDoiNha.setHieuSo(hieuSoDoinha);
            xhDoiKhach.setHieuSo(hieuSoDoiKhach);

            // set Vong
            Integer vongNha = xhDoiNha.getTranHoa() + xhDoiNha.getTranThang() + xhDoiNha.getTranThua();
            Integer vongKhach = xhDoiKhach.getTranHoa() + xhDoiKhach.getTranThang() + xhDoiKhach.getTranThua();
            xhDoiNha.setVong(vongNha);
            xhDoiKhach.setVong(vongKhach);



            bxhDoiBongRepository.save(xhDoiNha);
            bxhDoiBongRepository.save(xhDoiKhach);




            return true;


        }
        return false;
    }

    @Override
    public List<BXH_DoiBongDTO> getRanking(Long idMuagiai) {
        MuaGiaiEntity muaGiaiEntity = muaGiaiRepository.findById(idMuagiai).get();
        if(muaGiaiEntity!=null)
        {
            BangXepHangEntity bangXepHangEntity = muaGiaiEntity.getBxh();

//            List<Object> bxhDoiBongObject = bangXepHangRepository.getBXH_MuaGiai(bangXepHangEntity.getId());

            StoredProcedureQuery spQuery =
                    entityManager.createNamedStoredProcedureQuery("getBXH_MuaGiai")
                            .setParameter("id_bxh",bangXepHangEntity.getId());

            List<BXHDoiBongEntity> listbxhDoiBongEntity = spQuery.getResultList();
//            System.out.println(bxhDoiBongDTO);
//            List<Object> bxhDoiBongObject = null;
            List<BXH_DoiBongDTO> listBXH_DB_DTO = new ArrayList<>();
            if(listbxhDoiBongEntity.size()!=0 && listbxhDoiBongEntity !=null)
            {
                // Convert Object to BXH_DoiBongDTO
                for(BXHDoiBongEntity bxhDoiBong:listbxhDoiBongEntity)
                {
                    try
                    {
                        BXH_DoiBongDTO bxhDoiBongDTO = GenericConverter.convert(bxhDoiBong, BXH_DoiBongDTO.class);
                        bxhDoiBongDTO.setId_doibong(bxhDoiBong.getDoiBong().getId());
                        bxhDoiBongDTO.setXephang(listbxhDoiBongEntity.indexOf(bxhDoiBong)+1);
                        bxhDoiBongDTO.setTen_doi(bxhDoiBong.getDoiBong().getTen());
                        listBXH_DB_DTO.add(bxhDoiBongDTO);
                    }catch (Exception ex)
                    {
                        System.out.println(ex.getMessage());
                    }

                }
                return listBXH_DB_DTO;
            }
            else{
                return null;
            }
        }
        else{
            return null;
        }
    }


}
