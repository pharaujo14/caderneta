package br.com.caderneta.model;

import java.util.Arrays;

import br.com.caderneta.exceptions.ObjectNotFoundException;
import lombok.Getter;

@Getter
public enum RoleEnum {
	
	PROFESSOR(0, "PROFESSOR"),
	ALUNO(1, "ALUNO");
	
	private int id;
	private String name;
	
	private RoleEnum(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public String getRoleName() {
		return "ROLE_" + this.name;
	}
	
	public static RoleEnum fromId(int id) {
		return Arrays
				.stream(RoleEnum.values())
				.filter(v -> v.getId() == id)
				.findFirst()
				.orElseThrow( () -> new ObjectNotFoundException("Role de id: " + id +  " não encontrado"));
	}
	

	
	

}
