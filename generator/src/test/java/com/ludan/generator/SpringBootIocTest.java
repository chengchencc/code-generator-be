package com.ludan.generator;

import com.ludan.generator.service.TestService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

@Import(TestService.class)
@RunWith(SpringRunner.class)
public class SpringBootIocTest {

    @Autowired
    TestService testService;

    @Test
    public void ttt(){
        String s = testService.get();
        Assert.assertEquals(s,"get");
    }

}
