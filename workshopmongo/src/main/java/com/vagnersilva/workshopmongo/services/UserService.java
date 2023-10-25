package com.vagnersilva.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vagnersilva.workshopmongo.domain.User;
import com.vagnersilva.workshopmongo.dto.UserDTO;
import com.vagnersilva.workshopmongo.repository.UserRepository;
import com.vagnersilva.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class UserService {
	
	//Abaixo implemento minha Interface para poder fazer operações com os métodos que tenho acesso através dela
	/*@Autowired faço a implementação de forma automática apenas passando,
	 * modificador de acesso/nome da classe que desejo implementar/ nome do objeto para fazer as 
	 * operações, como no exemplo abaixo, isso é mecanismo de injeção de depencia de forma automatica do 
	 * Spring*/
	@Autowired
	private UserRepository repo;
	
	//findAll - Método responsável por retornar todos usuários do Banco de dados
	public List<User> findAll(){
		return repo.findAll();
	}
	
	//Método para buscar o usuário através do ID e retornar ou não a exceção ObjectNotFoundException
	public User findById (String id) {
		//se o usúario não existir o método retorna nulo
		Optional<User> user = repo.findById(id);
		
		if(!user.isPresent()) {
			//Abaxo passo minha mensagem de exceção
			throw new ObjectNotFoundException("Objeto não encontrado");
		}
		
		return user.get();
	}
	
	//Método para inserir um usuario
	public User insert(User obj) {
		return repo.insert(obj);
	}
	
	//Aqui instancio com meu UserDTO para ter acesso aos metodos get e fazer a inserção dos objetos
	public User fromDTO(UserDTO objDto) {
		return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
	}
	
}
