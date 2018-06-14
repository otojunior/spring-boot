/**
 * 
 */
package sample.batch;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Oto Soares Coelho Junior (oto.coelho-junior@serpro.gov.br)
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest({
	"sample.batch.schedules=true"
})
public class TesteGeral {
	@Test
	public void teste() {
		try {
			System.out.println("inicio teste");
			Thread.sleep(10_000);	// teste demorado
			System.out.println("fim teste");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
