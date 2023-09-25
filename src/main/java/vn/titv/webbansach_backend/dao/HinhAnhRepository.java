package vn.titv.webbansach_backend.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import vn.titv.webbansach_backend.entity.HinhAnh;

@RepositoryRestResource(path = "hinh-anh")
public interface HinhAnhRepository extends JpaRepository<HinhAnh, Integer> {
}
