package vn.titv.webbansach_backend.security;

public class Enpoints {
    public static final String[] PUBLIC_GET_ENPOINTS = {"/sach", "/sach/**"
            , "/hinh-anh"
            , "/danh-gia"
            , "/danh-gia/**"
            ,"/hinh-anh/**"
            ,"/nguoi-dung/search/**", "/tai-khoan/kich-hoat"
            ,"/the-loai", "/the-loai/**"};
    public static final String[] PUBLIC_POST_ENPOINTS = {"/tai-khoan/dang-ky", "/tai-khoan/dang-nhap", "/nguoi-dung/*"};
    public static final String[] USER_PATCH_ENPOINTS = {"/nguoi-dung/**"};
    public static final String[] ADMIN_ENPOINT = {"/nguoi-dung", "/nguoi-dung/**","/sach", "/sach/**", "/hinh-anh", "/hinh-anh/**", "/the-loai", "/the-loai/**"};


}

