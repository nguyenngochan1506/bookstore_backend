package vn.titv.webbansach_backend.entity;

import jakarta.persistence.*;
import lombok.Data;
@Entity
@Table(name = "chi_tiet_don_hang")
@Data
public class ChiTietDonHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_chi_tiet_don_hang")
    private long maChiTietGioHang;

    @Column(name = "so_luong")
    private int soLuong;

    @Column(name = "gia_ban")
    private double giaBan;
    @Column(name = "gia_niem")
    private double giaNiem;

    @ManyToOne(
            cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH}
    )
    @JoinColumn(name = "sach", nullable = false)
    private Sach sach;

    @ManyToOne(
            cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH}
    )
    @JoinColumn(name = "ma_don_hang", nullable = false)
    private DonHang donHang;
}
