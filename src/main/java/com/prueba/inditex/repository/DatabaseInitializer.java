package com.prueba.inditex.repository;

import java.time.LocalDateTime;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.prueba.inditex.model.Price;

@Configuration
public class DatabaseInitializer {

  private final PriceRepository priceRepository;

  @Autowired
  public DatabaseInitializer(PriceRepository priceRepository) {
    this.priceRepository = priceRepository;
  }

  @PostConstruct
  public void init() {
    priceRepository.save(new Price( 1L, LocalDateTime.of(2020, 6, 14, 0, 0, 0),
            LocalDateTime.of(2020, 12, 31, 23, 59, 59), 1, 35455L, 0, 35.50, "EUR"));
    priceRepository.save(new Price( 1L, LocalDateTime.of(2020, 6, 14, 15, 0, 0),
            LocalDateTime.of(2020, 6, 14, 18, 30, 0), 2, 35455L, 1, 25.45, "EUR"));
    priceRepository.save(new Price(1L, LocalDateTime.of(2020, 6, 15, 0, 0, 0),
            LocalDateTime.of(2020, 6, 15, 11, 0, 0), 3, 35455L, 1, 30.50, "EUR"));
    priceRepository.save(new Price(1L, LocalDateTime.of(2020, 6, 15, 16, 0, 0),
            LocalDateTime.of(2020, 12, 31, 23, 59, 59), 4, 35455L, 1, 38.95, "EUR"));
  }
}
