package vn.titv.webbansach_backend.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.titv.webbansach_backend.entity.DanhGia;
import vn.titv.webbansach_backend.entity.HinhAnh;

@Repository
public interface DanhGiaRepository extends JpaRepository<DanhGia, Long> {
}
