package com.univr.pump.insulinpump;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class InsulinPumpApplicationTests {

    @Test
    void contextLoads() {
        System.out.println("Hello World!");
    }

}
