package backend.qlgiaibongda.repository;

import backend.qlgiaibongda.entity.LichThiDauEntity;
import backend.qlgiaibongda.entity.MuaGiaiEntity;
import backend.qlgiaibongda.entity.QuanLyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LichThiDauRepository extends JpaRepository<LichThiDauEntity, Long> {
}
