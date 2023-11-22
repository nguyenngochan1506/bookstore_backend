package vn.titv.webbansach_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vn.titv.webbansach_backend.service.TheLoaiServiceImpl;

@RestController
public class TheLoaiController {
    private TheLoaiServiceImpl theLoaiService;
    @Autowired
    public TheLoaiController(TheLoaiServiceImpl theLoaiService){
        this.theLoaiService = theLoaiService;
    }
    @GetMapping("/the-loai/search/getMaTheLoaiByName")
    public ResponseEntity<?> getMaTheLoaiByName(@RequestParam String tenTheLoai){
        return ResponseEntity.ok(theLoaiService.getMaTheLoaiByName(tenTheLoai));
    }
}
