package vn.titv.webbansach_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import vn.titv.webbansach_backend.dao.NguoiDungRepository;
import vn.titv.webbansach_backend.entity.NguoiDung;
import vn.titv.webbansach_backend.entity.Quyen;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {
    private NguoiDungRepository nguoiDungRepository;

    @Autowired
    public UserService(NguoiDungRepository nguoiDungRepository){
        this.nguoiDungRepository = nguoiDungRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //layas người dùng từ database
        NguoiDung nguoiDung = nguoiDungRepository.findByTenDangNhap(username);
        if(nguoiDung == null){
            throw new UsernameNotFoundException("Người Dùng Không Tồn Tại!");
        }

        User user = new User(nguoiDung.getTenDangNhap(), nguoiDung.getMatKhau(), rolesToAuthorities(nguoiDung.getDanhSachQuyen()));
        return user;
    }

    private Collection<? extends GrantedAuthority> rolesToAuthorities(List<Quyen> danhSachQuyen) {
       return  danhSachQuyen.stream().map(quyen -> new SimpleGrantedAuthority(quyen.getTenQuyen())).collect(Collectors.toList());
        /**
         * giải thích chi tiết đoạn code trên:
         * DangSachQuyen.stream() => bien list này thành 1 đối tượng stream trong java8 trờ lên
         * .map()           => có thể hiều nhưng là hàm map bên javascript, ánh xạ từng đối tượng và return 1 cái gì đó
         * .map( quyen -> new SimpleGrantedAuthority(quyen.getTenQuyen())) => ở đây hàm map ánh xạ từng Quyền trong listQuyen và return về 1 đối tượng đó là SimpleGrantedAuthority
         * .collect(Collector.toList())     => hiểu đơn giản là đoạn này sẽ gom nhưng đối tượng mà hàm map() return về và gộp nó thành 1 cái list mới
         */
    }


}
