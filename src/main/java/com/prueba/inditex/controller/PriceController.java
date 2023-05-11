package com.prueba.inditex.controller;


import java.net.URI;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.websocket.ContainerProvider;
import javax.websocket.WebSocketContainer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.inditex.dto.PriceDto;
import com.prueba.inditex.model.Price;

import com.prueba.inditex.repository.PriceRepository;

@RestController
@RequestMapping("/prices")
public class PriceController {
	
	@Autowired
    private PriceRepository priceRepository;
	
	@GetMapping("/getPrice/{brandId}/{productId}/{appDate}")
	public ResponseEntity<PriceDto> getPrice(@PathVariable("brandId") Long brandId,@PathVariable("productId") Long productId,@PathVariable("appDate") String appDate) {
	
		LocalDateTime formatDate = LocalDateTime.parse(appDate);
        List<Price> prices = priceRepository.findByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(
                brandId, productId, formatDate, formatDate);

        if (prices.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        if(prices.size()>1) {
        	prices.sort(Comparator.comparing(Price::getPriority).reversed());
        }
        
        Price price = prices.get(0);

        PriceDto dto = new PriceDto();
        dto.setProductId(price.getProductId().intValue());
        dto.setBrandId(price.getBrandId().intValue());
        dto.setPriceList(price.getPriceList());
        dto.setStartDate(price.getStartDate());
        dto.setEndDate(price.getEndDate());
        dto.setPrice(price.getPrice());
        dto.setCurrency(price.getCurrency());

        return ResponseEntity.ok(dto);
    }
	
	@GetMapping("/getPrice/{brandId}/{productId}")
	public ResponseEntity<PriceDto> getPrice(@PathVariable("brandId") Long brandId,@PathVariable("productId") Long productId) {
	
		return this.getPrice(brandId, productId, LocalDateTime.now().toString());
	}
}
