package vn.titv.webbansach_backend.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import vn.titv.webbansach_backend.entity.DanhGia;

@RepositoryRestResource(path = "danh-gia")
public interface DanhGiaRepository extends JpaRepository<DanhGia, Long> {
}
