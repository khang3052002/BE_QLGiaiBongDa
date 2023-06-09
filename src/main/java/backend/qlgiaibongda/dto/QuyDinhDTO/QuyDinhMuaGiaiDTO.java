package backend.qlgiaibongda.dto.QuyDinhDTO;

public class QuyDinhMuaGiaiDTO {
    private Long id_quydinh;
    private Long id_muagiai;
    private String ten_giai;
    private QuyDinhTinhDiemDTO quyDinhTinhDiemDTO;
    private QuyDinhSoLuongDoiDTO quyDinhSoLuongDoiDTO;
    private  QuyDinhCauThuDTO quyDinhCauThuDTO;

    public Long getId_quydinh() {
        return id_quydinh;
    }

    public void setId_quydinh(Long id_quydinh) {
        this.id_quydinh = id_quydinh;
    }

    public Long getId_muagiai() {
        return id_muagiai;
    }

    public void setId_muagiai(Long id_muagiai) {
        this.id_muagiai = id_muagiai;
    }

    public String getTen_giai() {
        return ten_giai;
    }

    public void setTen_giai(String ten_giai) {
        this.ten_giai = ten_giai;
    }

    public QuyDinhTinhDiemDTO getQuyDinhTinhDiemDTO() {
        return quyDinhTinhDiemDTO;
    }

    public void setQuyDinhTinhDiemDTO(QuyDinhTinhDiemDTO quyDinhTinhDiemDTO) {
        this.quyDinhTinhDiemDTO = quyDinhTinhDiemDTO;
    }

    public QuyDinhSoLuongDoiDTO getQuyDinhSoLuongDoiDTO() {
        return quyDinhSoLuongDoiDTO;
    }

    public void setQuyDinhSoLuongDoiDTO(QuyDinhSoLuongDoiDTO quyDinhSoLuongDoiDTO) {
        this.quyDinhSoLuongDoiDTO = quyDinhSoLuongDoiDTO;
    }

    public QuyDinhCauThuDTO getQuyDinhCauThuDTO() {
        return quyDinhCauThuDTO;
    }

    public void setQuyDinhCauThuDTO(QuyDinhCauThuDTO quyDinhCauThuDTO) {
        this.quyDinhCauThuDTO = quyDinhCauThuDTO;
    }
}
