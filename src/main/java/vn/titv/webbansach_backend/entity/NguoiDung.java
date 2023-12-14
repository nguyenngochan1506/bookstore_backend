package vn.titv.webbansach_backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
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
    @NotNull
    @Column(name = "ten_dang_nhap")
    private String tenDangNhap;
    @NotNull
    @Column(name = "mat_khau", length = 512)
    private String matKhau;

    @Column(name = "gioi_tinh")
    private Character gioiTinh;
    @Email
    @Column(name = "email")
    private String email;

    @Column(name = "so_dien_thoai")
    private String soDienThoai;

    @Column(name = "dia_chi_giao_hang")
    private String diaChiGiaoHang;

    @Column(name = "dia_chi_mua_hang")
    private String diaChiMuaHang;

    @Column(name = "da_kich_hoat")
    private boolean daKichHoat ;

    @Column(name = "ma_kich_hoat")
    private String maKichHoat;
    @Column(name = "avatar", columnDefinition =  "LONGTEXT")
    @Lob
    private String avatar;

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
            fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH}
    )
    @JoinTable(
            name = "nguoidung_quyen",
            joinColumns = @JoinColumn(name = "ma_nguoi_dung"),
            inverseJoinColumns = @JoinColumn(name = "ma_quyen")
    )
    private List<Quyen> danhSachQuyen;

    @OneToMany(mappedBy = "nguoiDung",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    private List<DonHang> danhSachDonHang;


}
