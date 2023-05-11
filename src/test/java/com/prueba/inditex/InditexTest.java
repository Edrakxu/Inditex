package com.prueba.inditex;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.UriComponentsBuilder;

import com.prueba.inditex.dto.PriceDto;
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class InditexTest {

	@Autowired
    private TestRestTemplate restTemplate;
    
    @Test //Test 1: petición a las 10:00 del día 14 del producto 35455   para la brand 1 (ZARA)
    public void test1() {
        String appDate = "2020-06-14T10:00:00";
        Long productId = 35455L;
        Long brandId = 1L;
        
        String uri = String.format("/prices/getPrice/%d/%d/%s",brandId, productId, appDate);
        
        ResponseEntity<PriceDto> response = restTemplate.getForEntity(uri,PriceDto.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        PriceDto priceDto = response.getBody();

        assertNotNull(priceDto);
        assertEquals(35.50, priceDto.getPrice());
    }

    @Test //Test 2: petición a las 16:00 del día 14 del producto 35455   para la brand 1 (ZARA)
    public void test2() {
        String appDate = "2020-06-14T16:00:00";
        Long productId = 35455L;
        Long brandId = 1L;
        
        String uri = String.format("/prices/getPrice/%d/%d/%s",brandId, productId, appDate);
        
        ResponseEntity<PriceDto> response = restTemplate.getForEntity(uri,PriceDto.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        PriceDto priceDto = response.getBody();

        assertNotNull(priceDto);
        assertEquals(25.45, priceDto.getPrice());
    }

    @Test //Test 3: petición a las 21:00 del día 14 del producto 35455   para la brand 1 (ZARA)
    public void test3() {
        String appDate = "2020-06-14T21:00:00";
        Long productId = 35455L;
        Long brandId = 1L;
        
        String uri = String.format("/prices/getPrice/%d/%d/%s",brandId, productId, appDate);
        
        ResponseEntity<PriceDto> response = restTemplate.getForEntity(uri,PriceDto.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        PriceDto priceDto = response.getBody();

        assertNotNull(priceDto);
        assertEquals(35.50, priceDto.getPrice());
    }

    @Test //Test 4: petición a las 10:00 del día 15 del producto 35455   para la brand 1 (ZARA)
    public void test4() {
        String appDate = "2020-06-15T10:00:00";
        Long productId = 35455L;
        Long brandId = 1L;
        
        String uri = String.format("/prices/getPrice/%d/%d/%s",brandId, productId, appDate);
        
        ResponseEntity<PriceDto> response = restTemplate.getForEntity(uri,PriceDto.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        PriceDto priceDto = response.getBody();

        assertNotNull(priceDto);
        assertEquals(30.50, priceDto.getPrice());
    }

    @Test //Test 5: petición a las 21:00 del día 16 del producto 35455   para la brand 1 (ZARA)
    public void test5() {
        String appDate = "2020-06-16T21:00:00";
        Long productId = 35455L;
        Long brandId = 1L;
        
        String uri = String.format("/prices/getPrice/%d/%d/%s",brandId, productId, appDate);
        
        ResponseEntity<PriceDto> response = restTemplate.getForEntity(uri,PriceDto.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        PriceDto priceDto = response.getBody();

        assertNotNull(priceDto);
        assertEquals(38.95, priceDto.getPrice());
    }
}
