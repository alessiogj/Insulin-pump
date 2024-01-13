package com.univr.pump.insulinpump.service;

import com.univr.pump.insulinpump.InsulinPumpApplication;
import com.univr.pump.insulinpump.model.InsulinMachine;
import com.univr.pump.insulinpump.repository.InsulinMachineRepository;
import com.univr.pump.insulinpump.service.InsulinMachineService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = InsulinPumpApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class InsulinMachineServiceTest {

    //TODO: testare il service
}
