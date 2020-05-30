package com.github.onyshchenko.stock.service.impl;

import com.github.onyshchenko.stock.service.ApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;


@Slf4j
@Service
public class ApiServiceImpl implements ApiService {

    @Override
    public synchronized String call(String url) {
        StringBuffer data = new StringBuffer();
        int character;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new URL(url).openStream()))) {
            while ((character = reader.read()) != -1) {
                data.append((char) character);
            }
        } catch (IOException ex) {
            log.error("Data from API weren't upload: " + ex.getMessage());
        }
        return data.toString();
    }
}
