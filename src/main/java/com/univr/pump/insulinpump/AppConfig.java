package com.univr.pump.insulinpump;

import com.univr.pump.insulinpump.sensors.Battery;
import com.univr.pump.insulinpump.sensors.BloodPressure;
import com.univr.pump.insulinpump.sensors.NTC;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public Battery battery() {
        return new Battery(1000, 10);
    }

    @Bean
    public NTC ntc() {
        return new NTC();
    }

    @Bean
    public BloodPressure bloodPressure() {
        return new BloodPressure();
    }
}
