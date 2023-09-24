package vn.titv.webbansach_backend.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.titv.webbansach_backend.entity.ChiTietDonHang;
import vn.titv.webbansach_backend.entity.HinhAnh;

@Repository
public interface HinhAnhRepository extends JpaRepository<HinhAnh, Integer> {
}
