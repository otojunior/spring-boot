/**
 * 
 */
package sample.batch.data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

/**
 * @author Oto Soares Coelho Junior (oto.coelho-junior@serpro.gov.br)
 *
 */
@Entity
public class Item implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Version
	private Long versao;
	
	@Column(nullable=false, length=30)
	private String nome;
	
	@Column(nullable=false)
	private Boolean valido;

	/**
	 * 
	 */
	public Item() {
	}

	/**
	 * @param id
	 * @param versao
	 * @param nome
	 * @param valido
	 */
	public Item(String nome, Boolean valido) {
		this.nome = nome;
		this.valido = valido;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @return the valido
	 */
	public Boolean getValido() {
		return valido;
	}

	/**
	 * @return the versao
	 */
	public Long getVersao() {
		return versao;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	
	
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	/**
	 * @param valido the valido to set
	 */
	public void setValido(Boolean valido) {
		this.valido = valido;
	}

	/**
	 * @param versao the versao to set
	 */
	public void setVersao(Long versao) {
		this.versao = versao;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Item [id=" + id + ", versao=" + versao + ", nome=" + nome + ", valido=" + valido + "]";
	}
}
