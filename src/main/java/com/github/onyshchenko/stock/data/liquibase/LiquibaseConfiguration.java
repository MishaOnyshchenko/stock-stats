package com.github.onyshchenko.stock.data.liquibase;

import liquibase.integration.spring.SpringLiquibase;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Slf4j
@Configuration
public class LiquibaseConfiguration {

    @Autowired
    private DataSource dataSource;

    @Value("${spring.datasource.liquibase.change-log}")
    private String changelogFile;

    @SneakyThrows
    @Bean
    public SpringLiquibase liquibase() {
        log.info("[liquibase] Using a real instance of liquibase");
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setDataSource(dataSource);
        liquibase.setChangeLog(changelogFile);

        return liquibase;
    }
}