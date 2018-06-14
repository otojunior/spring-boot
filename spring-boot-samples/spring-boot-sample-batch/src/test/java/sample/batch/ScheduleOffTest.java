/**
 * 
 */
package sample.batch;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Oto Soares Coelho Junior (oto.coelho-junior@serpro.gov.br)
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest({
	"sample.batch.schedule.importUserJob=false"
})
public class ScheduleOffTest {
	private static final Logger LOG = LoggerFactory.getLogger(ScheduleOffTest.class);
	
	@Test
	public void testScheduleOff() {
		LOG.debug("Schedule off");
	}
}
