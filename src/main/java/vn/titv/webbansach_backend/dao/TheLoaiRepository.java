package vn.titv.webbansach_backend.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.RequestParam;
import vn.titv.webbansach_backend.entity.TheLoai;

@RepositoryRestResource(path = "the-loai")
public interface TheLoaiRepository extends JpaRepository<TheLoai, Integer> {
    public TheLoai findByTenTheLoai(String tenTheLoai);
    @Query("select maTheLoai from TheLoai where tenTheLoai = :tenTheLoai")
    public Integer getMaTheLoaiByName(@RequestParam String tenTheLoai);
}
