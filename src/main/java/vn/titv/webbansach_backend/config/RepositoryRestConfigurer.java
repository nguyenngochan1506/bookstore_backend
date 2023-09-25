package vn.titv.webbansach_backend.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.metamodel.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import vn.titv.webbansach_backend.entity.NguoiDung;

@Configuration
public class RepositoryRestConfigurer implements org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer {
   @Autowired
    private EntityManager entityManager;

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        //hiển thi id của tất cả các entity
        config.exposeIdsFor(entityManager.getMetamodel().getEntities().stream().map(Type::getJavaType).toArray(Class[]::new));

        /*
        nếu chỉ muốn chặn cho 1 entity cụ thể
        cofig.exposeIdsFor(entityName.class)
         */

        //chặn http cho từng entity
        //vd: ngăn không cho người dùng chỉnh sửa hoặc xoá dữ liêu
        disableHttpMethods(NguoiDung.class, config, new HttpMethod[]{HttpMethod.DELETE, HttpMethod.PUT});



    }

    /**
     * phương thức chặn không cho try cập những http methods đc chỉ định
     * @param c
     * @param config
     * @param methods
     */
    private void disableHttpMethods(Class c, RepositoryRestConfiguration config, HttpMethod[] methods){
        config.getExposureConfiguration()
                .forDomainType(c)
                .withItemExposure(((metdata, httpMethods) -> httpMethods.disable(methods)))
                .withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(methods));
    }
}
