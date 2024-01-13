package com.univr.pump.insulinpump.scheduled;

import com.univr.pump.insulinpump.InsulinPumpApplication;
import com.univr.pump.insulinpump.controller.InsulinMachineController;
import com.univr.pump.insulinpump.mock.Patient;
import com.univr.pump.insulinpump.model.InsulinMachine;
import com.univr.pump.insulinpump.repository.InsulinMachineRepository;
import com.univr.pump.insulinpump.repository.VitalParametersRepository;
import com.univr.pump.insulinpump.service.InsulinMachineService;
import com.univr.pump.insulinpump.service.InsulinMachineServiceTest;
import com.univr.pump.insulinpump.service.VitalParametersService;
import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class ScheduledIntegrationTest {

    @Test
    public void test() {
        //TODO
    }

}
