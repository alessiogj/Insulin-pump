package com.univr.pump.insulinpump;

import com.univr.pump.insulinpump.mock.Patient;
import com.univr.pump.insulinpump.mock.sensors.Battery;
import com.univr.pump.insulinpump.mock.sensors.InsulinPump;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public Battery battery() {
        return new Battery(100, 100);
    }

    @Bean
    public Patient patient() {
        return new Patient();
    }

    @Bean
    public InsulinPump insulinPump() {
        return new InsulinPump();
    }
}
