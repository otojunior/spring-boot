/**
 * 
 */
package sample.batch.data;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 
 * @author Oto Soares Coelho Junior (oto.coelho-junior@serpro.gov.br)
 *
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class ItemRepositoryTest {
	private static final Logger LOG = LoggerFactory.getLogger(ItemRepositoryTest.class);
	
	@Autowired private TestEntityManager entityManager;
	@Autowired private ItemRepository repository;
	
	@Test
	public void testFindByValido() {
		entityManager.persist(new Item("item 1", Boolean.FALSE));
		entityManager.persist(new Item("item 2", Boolean.TRUE));
		entityManager.persist(new Item("item 3", Boolean.TRUE));
		entityManager.persist(new Item("item 41", Boolean.TRUE));
		
		List<Item> validos = repository.findByValido(Boolean.TRUE);
		assertEquals(3, validos.size());
	}
	
	@Test
	public void testFindNomeByValido() {
		entityManager.persist(new Item("item 1", Boolean.FALSE));
		entityManager.persist(new Item("item 2", Boolean.TRUE));
		entityManager.persist(new Item("item 3", Boolean.TRUE));
		entityManager.persist(new Item("item 41", Boolean.TRUE));
		
		List<String> validos = repository.findNomeByValido(Boolean.TRUE);
		assertEquals(3, validos.size());
	}
	
	@Test
	public void testLogging() {
		System.out.println("error: " + LOG.isErrorEnabled());
		System.out.println("warn: " + LOG.isWarnEnabled());
		System.out.println("info: " + LOG.isInfoEnabled());
		System.out.println("debug: " + LOG.isDebugEnabled());
		System.out.println("trace: " + LOG.isTraceEnabled());
		LOG.debug("teste");
	}
	
	
}
