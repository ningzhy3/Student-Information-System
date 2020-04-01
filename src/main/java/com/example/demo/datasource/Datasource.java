package com.example.demo.datasource;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ClassName:Datasource
 * PackgeName:com.example.demo.datasource
 * Description:
 *
 * @Date:2020-03-20 22:32
 * Author:ningzhy3@gmail.com
 */
@Configuration
public class Datasource {
    @Bean
    @ConfigurationProperties("app.datasource")

    public HikariDataSource hikariDataSource() {
        return DataSourceBuilder
                .create()
                .type(HikariDataSource.class)
                .build();
    }
}
