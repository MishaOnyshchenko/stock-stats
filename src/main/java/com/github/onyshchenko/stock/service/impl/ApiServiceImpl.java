package com.github.onyshchenko.stock.service.impl;

import com.github.onyshchenko.stock.data.exceptions.ReadApiException;
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
    public String call(String url) {
        String data = null;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new URL(url).openStream()))) {
            if ((data = reader.readLine()) != null) {

                return data;
            } else {
                throw new ReadApiException("Error while reading stocks API!");
            }
        } catch (IOException ex) {
            log.error("Data from API weren't upload: " + ex.getMessage());
            throw new ReadApiException("Error while reading stocks API!");
        }
    }
}
