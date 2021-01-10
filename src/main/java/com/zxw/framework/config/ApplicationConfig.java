package com.zxw.framework.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * 程序注解配置
 *
 * @author Zhouxw
 */
@Configuration
// 表示通过aop框架暴露该代理对象,AopContext能够访问
@EnableAspectJAutoProxy(exposeProxy = true)
/**
 * 指定要扫描的Mapper类的包的路径
 *
 * @author Zhouxw
 * @date 2021/01/04 11:14
 */
@MapperScan("com.zxw.project.**.mapper")
@EnableAutoConfiguration(exclude = {FreeMarkerAutoConfiguration.class})
public class ApplicationConfig {

}
