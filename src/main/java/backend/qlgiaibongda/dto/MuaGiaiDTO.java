package backend.qlgiaibongda.dto;

import backend.qlgiaibongda.dto.QuyDinhDTO.QuyDinhCauThuDTO;
import backend.qlgiaibongda.dto.QuyDinhDTO.QuyDinhTinhDiemDTO;
import jakarta.persistence.Column;

import java.sql.Date;

public class MuaGiaiDTO {
    private String ten;
    private Long id_nguoitao;
    private Date thoiDiemBatDau;
    private Date thoiDiemKetThuc;
    private QuyDinhCauThuDTO quyDinhCauThu;
    private QuyDinhTinhDiemDTO quyDinhTinhDiem;

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public Long getId_nguoitao() {
        return id_nguoitao;
    }

    public void setId_nguoitao(Long id_nguoitao) {
        this.id_nguoitao = id_nguoitao;
    }

    public Date getThoiDiemBatDau() {
        return thoiDiemBatDau;
    }

    public void setThoiDiemBatDau(Date thoiDiemBatDau) {
        this.thoiDiemBatDau = thoiDiemBatDau;
    }

    public Date getThoiDiemKetThuc() {
        return thoiDiemKetThuc;
    }

    public void setThoiDiemKetThuc(Date thoiDiemKetThuc) {
        this.thoiDiemKetThuc = thoiDiemKetThuc;
    }

    public QuyDinhCauThuDTO getQuyDinhCauThu() {
        return quyDinhCauThu;
    }

    public void setQuyDinhCauThu(QuyDinhCauThuDTO quyDinhCauThu) {
        this.quyDinhCauThu = quyDinhCauThu;
    }

    public QuyDinhTinhDiemDTO getQuyDinhTinhDiem() {
        return quyDinhTinhDiem;
    }

    public void setQuyDinhTinhDiem(QuyDinhTinhDiemDTO quyDinhTinhDiem) {
        this.quyDinhTinhDiem = quyDinhTinhDiem;
    }
}
