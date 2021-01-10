package com.zxw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 启动程序
 *
 * @author Zhouxw
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class DesignApplication {
    public static void main(String[] args) {
        SpringApplication.run(DesignApplication.class, args);
        System.out.println("服务启动成功！");
    }
}
