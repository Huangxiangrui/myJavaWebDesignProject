package com.hxr.shopping.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    /**
     * 图片保存路径，自动从yml文件中获取数据
     *   示例： E:/images/
     */
    @Value("${file-save-path}")
    private String fileSavePath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 配置资源映射,如果访问的资源路径是以“/images/”开头的，映射到本机的${file-save-path}这个文件夹内
        registry.addResourceHandler("/images/**")
                .addResourceLocations("file:"+fileSavePath);
        System.out.println(registry);
    }
}