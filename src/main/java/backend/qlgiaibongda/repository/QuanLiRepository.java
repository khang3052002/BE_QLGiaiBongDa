package backend.qlgiaibongda.repository;

import backend.qlgiaibongda.entity.QuanLyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface QuanLiRepository extends JpaRepository<QuanLyEntity,Long> {
//    Optional<QuanLyEntity> findByTaiKhoan(String taikhoan);
    Boolean existsByTaiKhoan(String taikhoan);
}
