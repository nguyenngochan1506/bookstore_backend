package vn.titv.webbansach_backend.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import vn.titv.webbansach_backend.dao.NguoiDungRepository;
import vn.titv.webbansach_backend.entity.NguoiDung;
import vn.titv.webbansach_backend.entity.Quyen;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtService {
    private static final String KEY_SECRET = "MTIzNDU2NDU5OThEMzIxM0F6eGMzNTE2NTQzMjEzMjE2NTQ5OHEzMTNhMnMxZDMyMnp4M2MyMQ==";
    @Autowired
    private UserService userService;
    //theo kênh Amigoscode

    public boolean isTokenValid(String token, UserDetails userDetails){
        final String userName = extractUsername(token);
        return userName.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }

    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }

    private Claims extractAllClaims(String token){
        return Jwts.parser()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsTFunction){
        final Claims claims = extractAllClaims(token);
        return claimsTFunction.apply(claims);
    }

    private Key getSignInKey() {
        byte[] bytesKey = Decoders.BASE64.decode(KEY_SECRET);
        return Keys.hmacShaKeyFor(bytesKey);
    }
    public String generateToken(NguoiDung nguoiDung){


        return generateToken(new HashMap<>(), nguoiDung);
    }
    public String generateToken(Map<String, Object> extraClaims, NguoiDung nguoiDung){
        UserDetails userDetails = userService.loadUserByUsername(nguoiDung.getTenDangNhap());
        if(userDetails != null && !userDetails.getAuthorities().isEmpty()){
            extraClaims.put("idUser", nguoiDung.getMaNguoiDung());
            List<? extends GrantedAuthority> list  = userDetails.getAuthorities().stream().toList();
            for(var grantedAuthority:list){
                if(grantedAuthority.getAuthority().equals("ADMIN")){
                    extraClaims.put("isAdmin", true);
                }
                if(grantedAuthority.getAuthority().equals("USER")){
                    extraClaims.put("isUser", true);
                }
            }
        }
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000*60*60*24 ))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }


    //theo cách thầy Tùng
//
//    // Tạo jwt dựa trên username (tạo thông tin cần trả về cho FE khi đăng nhập thành công)
//    public String generateToken(String username) {
//        Map<String, Object> claims = new HashMap<>();
////        claims.put("isAdmin", true);
//        return createToken(claims, username);
//    }
//
//    // Toạ jwt với các claims đã chọn
//    private String createToken(Map<String, Object> claims, String username) {
//        return Jwts.builder()
//                .setClaims(claims)
//                .setSubject(username)
//                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis() + 30 * 60 * 1000) ) // Hết hạn sau 30 phút
//                .signWith(SignatureAlgorithm.HS256, getSigneKey())
//                .compact();
//    }
//    // Lấy key_secret
//    private Key getSigneKey() {
//        byte[] keyByte = Decoders.BASE64.decode(KEY_SECRET);
//        return Keys.hmacShaKeyFor(keyByte);
//    }
//
//    // Trích xuất thông tin (lấy ra tất cả thông số)
//    private Claims extractAllClaims(String token) {
//        //parseClaimsJws(token).getBody()
//        return Jwts.parser().setSigningKey(getSigneKey()).build().parseClaimsJws(token).getBody();
//    }
//
//    // Trích xuất thông tin cụ thể nhưng triển khai tổng quát (Method Generic)
//    public <T> T extractClaims(String token, Function<Claims, T> claimsTFunction) {
//        final Claims claims = extractAllClaims(token);
//        return claimsTFunction.apply(claims);
//    }
//    // Lấy ra thời gian hết hạn
//    public Date extractExpiration(String token) {
//        return extractClaims(token, Claims::getExpiration);
//    }
//
//    // Lấy ra username
//    public String extractUsername(String token) {
//        return extractClaims(token, Claims::getSubject);
//    }
//
//    // Kiểm tra token đó hết hạn chưa
//    private Boolean isTokenExpired(String token) {
//        return extractExpiration(token).before(new Date());
//    }
//
//    // Kiểm tra tính hợp lệ của token
//    public Boolean validateToken(String token, UserDetails userDetails) {
//        final String username = extractUsername(token);
//        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
//    }


}
