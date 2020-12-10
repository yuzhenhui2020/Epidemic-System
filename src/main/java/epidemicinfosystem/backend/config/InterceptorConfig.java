package epidemicinfosystem.backend.config;

import epidemicinfosystem.backend.interceptor.TokenInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer{
    private TokenInterceptor tokenInterceptor;

    //构造方法
    public InterceptorConfig(TokenInterceptor tokenInterceptor){
        this.tokenInterceptor = tokenInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        List<String> excludePath = new ArrayList<>();
        excludePath.add("/user/regist"); //注册
        excludePath.add("/user/login"); //登录
        excludePath.add("/user/logout"); //登出
        excludePath.add("/user/getSecureQuestion");
        excludePath.add("/user/changePassword");
        excludePath.add("/condition/setCondition");
        excludePath.add("/condition/getCondition");
        excludePath.add("/application/submitCondition");
        registry.addInterceptor(tokenInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(excludePath);
        WebMvcConfigurer.super.addInterceptors(registry);

    }


}
