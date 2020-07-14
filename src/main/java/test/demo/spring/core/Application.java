package test.demo.spring.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import test.demo.spring.core.oauth2.filters.JwtFilter;

import static java.util.Collections.singleton;

@SpringBootApplication
public class Application {

    @Autowired
    JwtFilter filter;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(filter);
        filterRegistrationBean.setUrlPatterns(singleton("/api/hello/*"));
        return filterRegistrationBean;
    }
}
