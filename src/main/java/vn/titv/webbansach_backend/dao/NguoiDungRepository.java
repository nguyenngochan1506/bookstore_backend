package vn.titv.webbansach_backend.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import vn.titv.webbansach_backend.entity.NguoiDung;

@RepositoryRestResource(path = "nguoi-dung")
public interface NguoiDungRepository extends JpaRepository<NguoiDung, Integer> {
    public boolean existsByTenDangNhap(String tenDangNhap);
    public boolean existsByEmail(String email);
    public NguoiDung findByMaNguoiDung(int maNguoiDung);
    public NguoiDung findByTenDangNhap(String tenDangNhap);
    public NguoiDung findByEmail(String email);
    public Page<NguoiDung> findByDanhSachQuyen_TenQuyen(String tenQuyen, Pageable pageable);
    //hàm này trả về tất cả người dùng theo 1 quyền nào đó, với từ khoá = 1 trong những thông tin của user
    @Query("SELECT nd FROM NguoiDung nd " +
            "JOIN nd.danhSachQuyen quyen " +
            "WHERE quyen.tenQuyen = :tenQuyen " +
            "AND (nd.maNguoiDung = :maNguoiDung OR " +
            "LOWER(nd.hoDem) LIKE LOWER(CONCAT('%', :hoDem, '%')) OR " +
            "LOWER(nd.ten) LIKE LOWER(CONCAT('%', :ten, '%')) OR " +
            "LOWER(nd.tenDangNhap) LIKE LOWER(CONCAT('%', :tenDangNhap, '%')) OR " +
            "LOWER(nd.email) LIKE LOWER(CONCAT('%', :email, '%')) OR " +
            "LOWER(nd.soDienThoai) LIKE LOWER(CONCAT('%', :soDienThoai, '%')))")
    public Page<NguoiDung> timKiemTatCaNguoiDungTheoQuyenVaTuKhoa(String tenQuyen, int maNguoiDung, String hoDem, String ten, String tenDangNhap, String email, String soDienThoai, Pageable pageable);
}
