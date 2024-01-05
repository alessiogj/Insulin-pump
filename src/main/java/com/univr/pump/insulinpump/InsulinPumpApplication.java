package com.univr.pump.insulinpump;

import com.univr.pump.insulinpump.model.Battery;
import com.univr.pump.insulinpump.model.NTC;
import com.univr.pump.insulinpump.model.Tank;
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

    @Bean
    public Battery battery() {
        return new Battery(1000, 10);
    }

    @Bean
    public Tank tank() {
        return new Tank(500, 500);
    }

    @Bean
    public NTC ntc() {
        return new NTC();
    }

    @Scheduled(fixedRate = 5000)
    public void decrBattery() {
        Battery battery = battery();
        battery.discharge();
        System.out.println("Battery: " + battery.getCurrentCapacity());
    }

    @Scheduled(fixedRate = 5000)
    public void newTemp() {
        Battery battery = battery();
        NTC ntc = ntc();
        if(battery.getCurrentCapacity() == 0) {
            ntc.reset();
            return;
        }
        ntc.modifyTemperature();
        if(ntc.isBroken())
            System.out.println("NTC is broken");
        else
            System.out.println("NTC: " + ntc.getTemperature());
    }

}
