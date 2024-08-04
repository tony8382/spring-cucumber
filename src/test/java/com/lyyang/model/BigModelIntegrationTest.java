package com.lyyang.model;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
class BigModelIntegrationTest {
    static List<BigModel> models = new ArrayList<>();

    @BeforeAll
    static void beforeAll() {
        for (int i = 0; i < 50000; i++) {
            BigModel b = new BigModel();
            b.setAField(String.valueOf(i));
            b.setBField(i + "GG");
            b.setCField(i + "GG");
            b.setDField(i + "EEE");
            models.add(b);
        }
    }

    @Test
    void checkTime() {


        LocalDateTime s1 = LocalDateTime.now();
        List<SmallModel> m1 = new ArrayList<>();
        for (BigModel model : models) {
            SmallModel s = new SmallModel();
            s.setAField(Integer.parseInt(model.getAField()));
            m1.add(s);
        }
        LocalDateTime s2 = LocalDateTime.now();
        log.info("EE1:{}", Duration.between(s1, s2).toMillis());

        LocalDateTime s3 = LocalDateTime.now();
        List<SmallModel> m2 = models.stream().map(bigModel -> {
            return SmallModel.builder().aField(Integer.valueOf(bigModel.getAField())).build();
        }).collect(Collectors.toList());
        LocalDateTime s4 = LocalDateTime.now();
        log.info("EE2:{}", Duration.between(s3, s4).toMillis());

    }
}