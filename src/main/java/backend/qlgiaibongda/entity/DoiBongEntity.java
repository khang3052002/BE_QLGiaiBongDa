package backend.qlgiaibongda.entity;

import backend.qlgiaibongda.entity.bxh_doibong.BXHDoiBongEntity;
import backend.qlgiaibongda.entity.cauthu_doibong.CauThuDoiBongEntity;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "doibong")
@NamedStoredProcedureQueries(
        @NamedStoredProcedureQuery(
                name = "getPlayerInTeamWithPosition",
                procedureName = "new_procedure",
                resultClasses = {CauThuEntity.class},
                parameters = {
                        @StoredProcedureParameter(name="id_team",type = Long.class, mode=ParameterMode.IN),
                        @StoredProcedureParameter(name="roles",type = String.class,mode = ParameterMode.IN)
                }
        )
)
public class DoiBongEntity extends BaseEntity{

    @Column(name = "ten")
    private String ten;
    @Column(name = "hinhanh")
    private String hinhAnh;

    @Column(name = "namthanhlap")
    private int namThanhLap;


    //CauThu_DoiBong
    @OneToMany(mappedBy = "DoiBongCT")
    private List<CauThuDoiBongEntity> DoiBongCauThu;

    //BangXepHang
    @OneToMany(mappedBy = "DoiBong")
    private List<BXHDoiBongEntity> DoiBongBXH;

    @OneToMany(mappedBy = "doiBong")
    private List<HoSoDangKyEntity> CacHoSoDangKy = new ArrayList<>();

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name="id_quanli", referencedColumnName = "id")
    private QuanLyEntity QuanLy;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_sannha", referencedColumnName = "id")
    private SanBongEntity sanBong;
    @OneToMany(mappedBy = "DoiNha")
    private List<TranDauEntity> DSTranDauSanNha = new ArrayList();
    @OneToMany(mappedBy = "DoiKhach")
    private List<TranDauEntity> DSTranDauSanKhach = new ArrayList();

    @OneToMany(mappedBy = "DoiBong")
    private List<GhiNhanBanThangEntity> DSBanThangDoiBong_TranDau = new ArrayList<>();

    @OneToMany(mappedBy = "doiNha")
    private List<KetQuaTranDauEntity> dsKetQuaDoiNha = new ArrayList<>();
    @OneToMany(mappedBy = "doiKhach")
    private List<KetQuaTranDauEntity> dsKetQuaDoiKhach = new ArrayList<>();


    ////////////////////////////////GETTER SETTER///////////////////////////////////////////


    public List<KetQuaTranDauEntity> getDsKetQuaDoiNha() {
        return dsKetQuaDoiNha;
    }

    public void setDsKetQuaDoiNha(List<KetQuaTranDauEntity> dsKetQuaDoiNha) {
        this.dsKetQuaDoiNha = dsKetQuaDoiNha;
    }

    public List<KetQuaTranDauEntity> getDsKetQuaDoiKhach() {
        return dsKetQuaDoiKhach;
    }

    public void setDsKetQuaDoiKhach(List<KetQuaTranDauEntity> dsKetQuaDoiKhach) {
        this.dsKetQuaDoiKhach = dsKetQuaDoiKhach;
    }

    public List<HoSoDangKyEntity> getCacHoSoDangKy() {
        return CacHoSoDangKy;
    }

    public void setCacHoSoDangKy(List<HoSoDangKyEntity> cacHoSoDangKy) {
        CacHoSoDangKy = cacHoSoDangKy;
    }

    public List<BXHDoiBongEntity> getDoiBongBXH() {
        return DoiBongBXH;
    }

    public void setDoiBongBXH(List<BXHDoiBongEntity> doiBongBXH) {
        DoiBongBXH = doiBongBXH;
    }

    public List<CauThuDoiBongEntity> getDoiBongCauThu() {
        return DoiBongCauThu;
    }

    public void setDoiBongCauThu(List<CauThuDoiBongEntity> doiBongCauThu) {
        DoiBongCauThu = doiBongCauThu;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public int getNamThanhLap() {
        return namThanhLap;
    }

    public void setNamThanhLap(int namThanhLap) {
        this.namThanhLap = namThanhLap;
    }

    public QuanLyEntity getQuanLy() {
        return QuanLy;
    }

    public void setQuanLy(QuanLyEntity quanLy) {
        QuanLy = quanLy;
    }

    public SanBongEntity getSanBong() {
        return sanBong;
    }

    public void setSanBong(SanBongEntity sanBong) {
        this.sanBong = sanBong;
    }

    public List<TranDauEntity> getDSTranDauSanNha() {
        return DSTranDauSanNha;
    }

    public void setDSTranDauSanNha(List<TranDauEntity> DSTranDauSanNha) {
        this.DSTranDauSanNha = DSTranDauSanNha;
    }

    public List<TranDauEntity> getDSTranDauSanKhach() {
        return DSTranDauSanKhach;
    }

    public void setDSTranDauSanKhach(List<TranDauEntity> DSTranDauSanKhach) {
        this.DSTranDauSanKhach = DSTranDauSanKhach;
    }

    public List<GhiNhanBanThangEntity> getDSBanThangDoiBong_TranDau() {
        return DSBanThangDoiBong_TranDau;
    }

    public void setDSBanThangDoiBong_TranDau(List<GhiNhanBanThangEntity> DSBanThangDoiBong_TranDau) {
        this.DSBanThangDoiBong_TranDau = DSBanThangDoiBong_TranDau;
    }
}
