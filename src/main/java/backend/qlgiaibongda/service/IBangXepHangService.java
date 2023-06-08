package backend.qlgiaibongda.service;

import backend.qlgiaibongda.dto.BXH_DoiBongDTO;
import backend.qlgiaibongda.entity.MuaGiaiEntity;

import java.util.List;

public interface IBangXepHangService {
    boolean CreateRanking(MuaGiaiEntity muaGiai);

    boolean UpdateRanking(Long id_trandau);


    List<BXH_DoiBongDTO> getRanking(Long idMuagiai);
}
