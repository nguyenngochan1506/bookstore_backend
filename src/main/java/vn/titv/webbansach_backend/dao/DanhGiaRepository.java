package vn.titv.webbansach_backend.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import vn.titv.webbansach_backend.entity.DanhGia;

import java.util.List;

@RepositoryRestResource(path = "danh-gia")
public interface DanhGiaRepository extends JpaRepository<DanhGia, Long> {
    public List<DanhGia> findAllBySach_MaSach(int maSach);
    public int countDanhGiaBySach_MaSach(int maSach);
}
