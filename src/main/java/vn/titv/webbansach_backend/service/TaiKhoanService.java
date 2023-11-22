package vn.titv.webbansach_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vn.titv.webbansach_backend.dao.NguoiDungRepository;
import vn.titv.webbansach_backend.entity.NguoiDung;
import vn.titv.webbansach_backend.security.AuthenticationResponse;
import vn.titv.webbansach_backend.security.AuthenticationResquest;

import java.util.Random;
import java.util.UUID;

@Service
public class TaiKhoanService {
    private NguoiDungRepository nguoiDungRepository;
    private BCryptPasswordEncoder passwordEncoder;
    private EmailService emailService;
    private JwtService jwtService;
    private UserService userService;
    private AuthenticationManager authenticationManager;


    @Autowired
    public TaiKhoanService(AuthenticationManager authenticationManager, UserService userService, JwtService jwtService ,NguoiDungRepository nguoiDungRepository, EmailService emailService, BCryptPasswordEncoder passwordEncoder) {
        this.nguoiDungRepository = nguoiDungRepository;
        this.emailService = emailService;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }


    public ResponseEntity<?> dangKyNguoiDung(NguoiDung nguoiDung) {
        //kiểm tra tên đăng nhập
        if (nguoiDungRepository.existsByTenDangNhap(nguoiDung.getTenDangNhap())) {
            return ResponseEntity.badRequest().body("Tên đăng nhập đã tồn tại!");
        }
        //kiểm tra email
        if (nguoiDungRepository.existsByEmail(nguoiDung.getEmail())) {
            return ResponseEntity.badRequest().body("Email này đã được sử dụng!");
        }

        //mã hoá mật khẩu
        String encryptPassword = passwordEncoder.encode(nguoiDung.getMatKhau());
        nguoiDung.setMatKhau(encryptPassword);

        //gán và gửi mã kích hoạt
        nguoiDung.setMaKichHoat(taoMaKichHoat());
        nguoiDung.setDaKichHoat(false);


        //lưu người dùng vào hệ thống
        nguoiDungRepository.save(nguoiDung);

        // Gửi email cho người dùng để họ kích hoạt
        guiMaKichHoat(nguoiDung.getEmail(), nguoiDung.getMaKichHoat());

        return ResponseEntity.ok("Đăng ký thành công!");
    }

    //tạo chuỗi 6 số ngẫu nhiên
    public String taoMaKichHoat() {
        return new Random().nextInt((999999 - 100000) + 1) + 100000 +"";
    }

    private void guiMaKichHoat(String email, String maKichHoat) {
        String subject = "Kích hoạt tài khoản tại bookstore!";
        String url = "http://localhost:3000/kich-hoat/"+email+"/"+maKichHoat;
        String text = String.format("""
                <!DOCTYPE html>
                <html>
                <head>
                <title>Page Title</title>
                <style>
                h1{
                color: black;
                text-align: center;
                }
                p{
                font-size: 20px;
                }
                p strong{
                font-style: italic;
                text-decoration: underline;
                }
                </style>
                </head>
                <body>

                <h1>Bạn đã đăng ký tài khoản tại BookStore</h1>
                <p>Vui lòng nhập mã <strong>%s</strong> để kích hoạt tài khoản!</p>
                <p>Hoặc bạn có thể ấn trực tiếp vào link này: <a target="_blank" href="%s">%s</a></p>
                </body>
                </html>
                """, maKichHoat, url, url);

        emailService.sendMessage("nguyenngochandeveloper@gmail.com", email, subject, text);
    }
    //phương thức kích hoạt tài khoản
    public ResponseEntity<?> kichHoatTaiKhoan(String email, String maKichHoat){
        //lấy người dùng ra thông qua emaill;
        NguoiDung nguoiDung = nguoiDungRepository.findByEmail(email);
        if(nguoiDung == null){
            return ResponseEntity.badRequest().body("Tài khoản không tồn tại");
        }
        if(nguoiDung.isDaKichHoat()){
            return ResponseEntity.badRequest().body("Tài khoản này đã được kích hoạt trước đó.");
        }

        if(maKichHoat.equals(nguoiDung.getMaKichHoat())){
            nguoiDung.setDaKichHoat(true);
            nguoiDungRepository.save(nguoiDung);
            return ResponseEntity.ok("Kích hoạt tài khoản thành công!");
        }
        return ResponseEntity.badRequest().body("Mã kích hoạt không chính xác!");
    }

    public ResponseEntity<?> dangNhap(AuthenticationResquest request){
        if(request != null){
            try {
                Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword()));
                if(auth.isAuthenticated()) {
                    String token = jwtService.generateToken(userService.loadUserByUsername(request.getUserName()));

                    AuthenticationResponse response = new AuthenticationResponse();
                    response.setToken(token);
                    return ResponseEntity.ok(response);
                }
            }catch (AuthenticationException e){
                return ResponseEntity.badRequest().body("Tài khoản hoặc mật khẩu không chính xác");
            }
        }
        return ResponseEntity.badRequest().body("Đăng Nhập không thành công!");
    }
}


