/**
 * 
 */
package sample.batch.data;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

/**
 * @author Oto Soares Coelho Junior (oto.coelho-junior@serpro.gov.br)
 *
 */
public interface SimuladorAmazonS3Repository extends CrudRepository<Item, Long> {
	public List<Item> findByNome(String nome);
}
