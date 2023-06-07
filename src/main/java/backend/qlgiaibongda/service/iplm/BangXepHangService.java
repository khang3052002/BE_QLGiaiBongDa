package backend.qlgiaibongda.service.iplm;

import backend.qlgiaibongda.entity.BangXepHangEntity;
import backend.qlgiaibongda.entity.DoiBongEntity;
import backend.qlgiaibongda.entity.HoSoDangKyEntity;
import backend.qlgiaibongda.entity.MuaGiaiEntity;
import backend.qlgiaibongda.entity.bxh_doibong.BXHDoiBongEntity;
import backend.qlgiaibongda.entity.bxh_doibong.BXHDoiBongKey;
import backend.qlgiaibongda.repository.BXHDoiBongRepository;
import backend.qlgiaibongda.repository.BangXepHangRepository;
import backend.qlgiaibongda.repository.HoSoDangKyRepository;
import backend.qlgiaibongda.repository.MuaGiaiRepository.MuaGiaiRepository;
import backend.qlgiaibongda.service.IBangXepHangService;
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

                bxhDoiBongEntityList.add(bxhDoiBongEntity);

            }
            bxhDoiBongRepository.saveAll(bxhDoiBongEntityList);
            //isHaveSlot = true;
            return true;
        }


        return false;
    }
}
