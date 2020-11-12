package br.com.caderneta.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.caderneta.exceptions.IdNotFoundException;
import br.com.caderneta.exceptions.IdNotNullException;
import br.com.caderneta.model.Aluno;
import br.com.caderneta.model.RoleEnum;
import br.com.caderneta.model.Usuario;
import br.com.caderneta.repository.AlunoRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AlunoService {

	private final AlunoRepository alunoRepository;
	private final BCryptPasswordEncoder pEnconder;

	public Aluno create(Aluno aluno) {
		aluno = fromCreate(aluno);
		return this.alunoRepository.save(aluno);
	}

	public Aluno update(Aluno novo) throws IdNotNullException, IdNotFoundException {
		Aluno antigo = this.findById(novo.getId());
		
		antigo.setNome(novo.getNome());
		antigo.setSobrenome(novo.getSobrenome());
		antigo.setEmail(novo.getEmail());
		antigo.setCpf(novo.getCpf());
		antigo.setSenha(novo.getSenha());
		
		return this.alunoRepository.save(antigo);

	}
	
	public Aluno findById(Long id) throws IdNotNullException, IdNotFoundException {
		Optional
				.ofNullable(id)
				.orElseThrow( () -> new IdNotNullException("Aluno: " + id));
		
		return this.alunoRepository.findById(id)
					.orElseThrow( () -> new IdNotFoundException("Aluno: " + id));
	}
	
	public List<Aluno> findAll(){
		return this.alunoRepository.findAll();
	}
	
	public void deleteById(Long id) throws IdNotNullException, IdNotFoundException {
		this.findById(id);
		
		this.alunoRepository.deleteById(id);
		
	}
	
		
	public Aluno fromCreate(Aluno aluno) {
		
		return Aluno.builder()
				.nome(aluno.getNome())
				.sobrenome(aluno.getSobrenome())
				.email(aluno.getEmail())
				.cpf(aluno.getCpf())
				.senha(pEnconder.encode(aluno.getSenha())) // ver isso com o Gustavo
				.usuario(
						Usuario.builder()
						.senha(pEnconder.encode(aluno.getSenha()))
						.username(aluno.getEmail())
						.role(RoleEnum.ALUNO)
						.build())
				.build();
				
	}

	
	
}
