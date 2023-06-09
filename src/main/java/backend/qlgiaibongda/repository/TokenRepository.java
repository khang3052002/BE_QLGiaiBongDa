package backend.qlgiaibongda.repository;

import backend.qlgiaibongda.entity.QuanLyEntity;
import backend.qlgiaibongda.entity.TokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TokenRepository extends JpaRepository<TokenEntity,Long> {
    List<TokenEntity> findAllByQuanLyAndExpiredIsFalseOrRevokedIsFalse(QuanLyEntity quanLy);
    TokenEntity findByToken(String token);
}
