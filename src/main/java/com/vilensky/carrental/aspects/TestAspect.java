package com.vilensky.carrental.aspects;

import com.vilensky.carrental.dto.RentalOrderDTO;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;

import java.time.ZonedDateTime;

@Aspect
@Configuration
@Slf4j
public class TestAspect {
    @Before("execution(* com.vilensky.carrental.controllers.*.*(..))")
    public void testCatch(JoinPoint joinPoint){
        log.info("licence number: {}", joinPoint.getArgs());


//    @Around("execution(* com.vilensky.carrental.controllers.RentalOrderController.*(..))")
//    public ResponseEntity<RentalOrderDTO> testCatch2(ProceedingJoinPoint joinPoint) throws Throwable {
//        String s = (String)joinPoint.getArgs()[0];
//
//        log.info("entering aspect {}", s);
//
//        Object o=joinPoint.proceed(joinPoint.getArgs());
//        log.info("leaving aspect");
//        ResponseEntity<Object> rentalOrderDTOResponseEntity = (ResponseEntity<Object>)o;
//        RentalOrderDTO dto = (RentalOrderDTO) rentalOrderDTOResponseEntity.getBody();
//        dto.setRentEnd(ZonedDateTime.now().minusMonths(50));
//        return ResponseEntity.ok(dto);
    }
}
