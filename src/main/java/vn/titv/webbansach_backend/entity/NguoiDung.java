package vn.titv.webbansach_backend.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
@Data
@Entity
@Table(name = "nguoi_dung")
public class NguoiDung {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_nguoi_dung")
    private int maNguoiDung;

    @Column(name = "ho_dem")
    private String hoDem;

    @Column(name = "ten")
    private String ten;

    @Column(name = "ten_dang_nhap")
    private String tenDangNhap;

    @Column(name = "mat_khau", length = 512)
    private String matKhau;

    @Column(name = "gioi_tinh")
    private boolean gioiTinh;

    @Column(name = "email")
    private String email;

    @Column(name = "so_dien_thoai")
    private String soDienThoai;

    @Column(name = "dia_chi_giao_hang")
    private String diaChiGiaoHang;

    @Column(name = "dia_chi_mua_hang")
    private String diaChiMuaHang;

    @OneToMany(mappedBy = "nguoiDung",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH}
    )
    private List<DanhGia> danhSachDanhGia;

    @OneToMany(mappedBy = "nguoiDung",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH}
    )
    private List<SachYeuThich> danhSachYeuThich;

    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH}
    )
    @JoinTable(
            name = "nguoidung_quyen",
            joinColumns = @JoinColumn(name="ma_nguoi_dung"),
            inverseJoinColumns = @JoinColumn(name = "ma_quyen")
    )
    private List<Quyen> danhSachQuyen;

    @OneToMany(mappedBy = "nguoiDung",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    private List<DonHang> danhSachDonHang;

}
