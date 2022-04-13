package br.org.flem.sac.quartz;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.GenericServlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerUtils;
import org.quartz.impl.StdSchedulerFactory;

/**
 *
 * @author mccosta
 */
public class AgendadorTarefasServlet extends GenericServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        //Trigger trigger = TriggerUtils.makeSecondlyTrigger();
        //trigger.setStartTime(TriggerUtils.getEvenSecondDate(new Date()));  // start on the next even hour
        try {
            super.init(config);
            Scheduler sched = StdSchedulerFactory.getDefaultScheduler();
            sched.start();
            JobDetail jobDetail = new JobDetail("verificaFinalVigencia", Scheduler.DEFAULT_GROUP, VerificaFinalVigencia.class);
            Trigger trigger = TriggerUtils.makeDailyTrigger(20, 00); 
            //Trigger trigger = TriggerUtils.makeSecondlyTrigger();
            //trigger.setStartTime(TriggerUtils.getEvenSecondDate(new Date()));  // start on the next even hour
            trigger.setName("verificaFinalVigencia");
            sched.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException ex) {
            Logger.getLogger(AgendadorTarefasServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void service(ServletRequest arg0, ServletResponse arg1) throws ServletException, IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
