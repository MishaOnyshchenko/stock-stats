package com.github.onyshchenko.stock;

import com.github.onyshchenko.stock.service.RunService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication(scanBasePackages = "com.github.onyshchenko.stock")
@EntityScan("com.github.onyshchenko.stock.data.domain")
@EnableJpaRepositories("com.github.onyshchenko.stock.data.repository")
@EnableAsync
@EnableScheduling
public class StockApplication implements CommandLineRunner {

    @Autowired
    RunService runService;

    public static void main(String[] args) {
        SpringApplication.run(StockApplication.class, args);
    }

    @Override
    public void run(String... args) {
        runService.runApp();
    }
}
