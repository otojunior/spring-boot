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
public interface ItemRepository extends CrudRepository<Item, Long> {
	public List<Item> findByValido(Boolean valido);
	public List<String> findNomeByValido(Boolean valido);
}
