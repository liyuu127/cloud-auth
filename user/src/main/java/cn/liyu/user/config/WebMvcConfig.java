package cn.liyu.user.config;

import cn.liyu.user.filter.LoggingFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author liyu
 * date 2022/3/17 20:54
 * description
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Bean
    public LoggingFilter loggingFilter() {
        return new LoggingFilter();
    }

    @Bean
    public FilterRegistrationBean<LoggingFilter> filterRegistrationBean(LoggingFilter loggingFilter) {
        FilterRegistrationBean<LoggingFilter> filterFilterRegistrationBean = new FilterRegistrationBean<>();
        filterFilterRegistrationBean.setOrder(Integer.MIN_VALUE);
        filterFilterRegistrationBean.setFilter(loggingFilter);
        filterFilterRegistrationBean.setUrlPatterns(new ArrayList<>(Arrays.asList("/*")));
        return filterFilterRegistrationBean;
    }

}
