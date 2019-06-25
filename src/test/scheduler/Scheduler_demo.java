package scheduler;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.test.context.junit4.SpringRunner;

//@RunWith(SpringRunner.class)
public class Scheduler_demo {

    //    @Test
    public void demo() throws Exception {
        SchedulerFactory factory = new StdSchedulerFactory();

        Scheduler scheduler = factory.getScheduler();

        JobDetail jobDetail = JobBuilder.newJob(JobTest.class).withDescription("this is a ram job").
                withIdentity("jobTest", "jobTestGrip").build();

        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("myTrigger", "group1").withDescription("demo").
                withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(2).repeatForever()).build();
        scheduler.start();

        scheduler.scheduleJob(jobDetail, trigger);

        scheduler.start();

    }

    public static void main(String[] args) throws Exception {
        Scheduler_demo demo = new Scheduler_demo();
        demo.demo();
    }

}
