package backend.qlgiaibongda.entity.KetQuaTranDau;


import backend.qlgiaibongda.entity.*;
import jakarta.persistence.*;

@Entity
@Table(name="ketquatradau")
public class KetQuaTranDauEntity extends BaseEntity {

    @EmbeddedId
    private KetQuaTranDauKey id;

    @OneToOne
    @MapsId("id_TranDau")
    @JoinColumn(name = "id_trandau", referencedColumnName = "id")
    private TranDauEntity TranDau;


    @ManyToOne
    @JoinColumn(name="id_cauthu")
    private CauThuEntity CauThu;

    @ManyToOne
    @JoinColumn(name="id_doibong")
    private DoiBongEntity DoiBong;

    @ManyToOne
    @JoinColumn(name="id_loaibanthang")
    private LoaiBanThangEntity LoaiBanThang;
}
