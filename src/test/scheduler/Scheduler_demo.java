package scheduler;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Scheduler_demo {

    @Test
    public void demo() throws Exception {
        SchedulerFactory factory = new StdSchedulerFactory();

        Scheduler scheduler = factory.getScheduler();


        JobDetail jobDetail = JobBuilder.newJob(JobTest.class).withDescription("this is a ram job").
                withIdentity("jobTest", "jobTestGrip").build();

        Trigger trigger=TriggerBuilder.newTrigger().withDescription("").build();
        scheduler.start();


    }
}
