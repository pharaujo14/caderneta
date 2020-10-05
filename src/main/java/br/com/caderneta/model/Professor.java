package br.com.caderneta.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "professores")
public class Professor {
	
	@Id
	@GeneratedValue
	@Getter @Setter
	private Long id;
	
	@Getter @Setter
	@Column(length = 50, nullable = false)
	private String nome;
	
	@Getter @Setter
	@Column(length = 150, nullable = false)
	private String sobrenome;
	
	@Getter @Setter
	@Column(length = 100, nullable = false)
	private String email;
	
	@Getter @Setter
	@Column(length = 11, nullable = false)
	private String cpf;
	
	@Getter @Setter
	@Column()
	private String foto;
	
	@Getter @Setter
	@Column(nullable = false)
	private String senha;
	
	public Professor() {}

	public Professor(Long id, String nome, String sobrenome, String cpf, String foto, String senha) {
		this.id = id;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.cpf = cpf;
		this.foto = foto;
		this.senha = senha;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Professor other = (Professor) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	
	
	
	

}
