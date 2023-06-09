package backend.qlgiaibongda.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "token")
public class TokenEntity extends BaseEntity {

    private boolean expired;
    private boolean revoked;
    @Column(unique = true)
    private String token;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    public QuanLyEntity quanLy;

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }

    public boolean isRevoked() {
        return revoked;
    }

    public void setRevoked(boolean revoked) {
        this.revoked = revoked;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public QuanLyEntity getQuanLy() {
        return quanLy;
    }

    public void setQuanLy(QuanLyEntity quanLy) {
        this.quanLy = quanLy;
    }
}
