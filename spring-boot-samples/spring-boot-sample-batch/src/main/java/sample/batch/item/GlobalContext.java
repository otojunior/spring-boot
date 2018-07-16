/**
 * 
 */
package sample.batch.item;

import java.util.HashMap;
import java.util.Iterator;

import org.springframework.stereotype.Component;

/**
 * @author Oto Soares Coelho Junior (oto.coelho-junior@serpro.gov.br)
 *
 */
@Component
public class GlobalContext extends HashMap<String, Iterator<String>> {
	private static final long serialVersionUID = 1L;
}
