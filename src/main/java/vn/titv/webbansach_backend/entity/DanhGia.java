package vn.titv.webbansach_backend.entity;

import jakarta.persistence.*;
import lombok.Data;
@Entity
@Table(name = "danh_gia")
@Data
public class DanhGia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_danh_gia")
    private long maDanhGia;

    @Column(name = "diem_xep_hang")
    private float diemXepHang;//1->5

    @Column(name = "nhan_xet")
    private String nhanXet;

    @ManyToOne(
            cascade = {CascadeType.PERSIST, CascadeType.DETACH,
                    CascadeType.MERGE, CascadeType.REFRESH}
    )
    @JoinColumn(name = "sach", nullable = false)
    private Sach sach;


    @ManyToOne(
            cascade = {CascadeType.PERSIST, CascadeType.DETACH,
                    CascadeType.MERGE, CascadeType.REFRESH}
    )
    @JoinColumn(name = "ma_nguoi_dung", nullable = false)
    private NguoiDung nguoiDung;
}
