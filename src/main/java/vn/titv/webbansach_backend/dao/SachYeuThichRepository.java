package vn.titv.webbansach_backend.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.titv.webbansach_backend.entity.HinhAnh;
import vn.titv.webbansach_backend.entity.SachYeuThich;

@Repository
public interface SachYeuThichRepository extends JpaRepository<SachYeuThich, Integer> {
}
