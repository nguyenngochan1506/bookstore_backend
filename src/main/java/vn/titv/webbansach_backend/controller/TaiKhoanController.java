package vn.titv.webbansach_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vn.titv.webbansach_backend.entity.NguoiDung;
import vn.titv.webbansach_backend.security.AuthenticationResquest;
import vn.titv.webbansach_backend.service.JwtService;
import vn.titv.webbansach_backend.service.TaiKhoanService;

@RestController
@RequestMapping("/tai-khoan")
public class TaiKhoanController {
    private JwtService jwtService;
    private TaiKhoanService taiKhoanService;
    @Autowired
    public TaiKhoanController(TaiKhoanService taiKhoanService){
        this.taiKhoanService = taiKhoanService;
    }
    @PostMapping("/dang-ky")
    public ResponseEntity<?> dangKyNguoiDung(@Validated @RequestBody NguoiDung nguoiDung){
    ResponseEntity<?> response = taiKhoanService.dangKyNguoiDung(nguoiDung);
    return response;
    }
    @GetMapping("/kich-hoat")
    public ResponseEntity<?> kichHoatTaiKhoan(@RequestParam String email, String maKichHoat){
        ResponseEntity<?> response = taiKhoanService.kichHoatTaiKhoan(email, maKichHoat);
        return response;
    }
    @PostMapping("/dang-nhap")
    public ResponseEntity<?> dangNhap(@RequestBody AuthenticationResquest auth){
        ResponseEntity<?> response = taiKhoanService.dangNhap(auth);
        return response;
    }
}
