package com.univr.pump.insulinpump;

import com.univr.pump.insulinpump.sensors.Battery;
import com.univr.pump.insulinpump.sensors.NTC;
import com.univr.pump.insulinpump.sensors.Tank;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableScheduling
@SpringBootApplication
public class InsulinPumpApplication {

    public static void main(String[] args) {
        SpringApplication.run(InsulinPumpApplication.class, args);
    }

}
