package com.vagnersilva.workshopmongo.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.vagnersilva.workshopmongo.domain.User;
import com.vagnersilva.workshopmongo.dto.UserDTO;
import com.vagnersilva.workshopmongo.services.UserService;

@RestController
@RequestMapping(value="/users")
public class UserResource {
	
	//Abaixo injeto minha classe service que contém a logica do programa
	@Autowired
	private UserService service;
	
	//@RequestMapping - Mapeia a solicitação http da web
	@RequestMapping(method = RequestMethod.GET)
	//ResponseEntity - encapsula toda uma estrutura necessária  para retornarmos respostas http
	//findAll - busca todos elementos do Banco de daddos
	public ResponseEntity <List<UserDTO>> findAll(){
			
		//Abaixo busco minha lista do service que recuperou os usuarios do Banco de dados e guardo na minha lista
		List<User> list = service.findAll();
			
		//Abaixo converto minha lista do tipo USER para UserDTO usando instrução lambda
		List <UserDTO> listDTO = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
		
		//Aqui devolvo a lista na resposta da requisição
		return ResponseEntity.ok().body(listDTO);
	}
	
	
	//==================Retornar usuario por id======================================================
	
	//@RequestMapping - Mapeia a solicitação http da web através do ID
		@RequestMapping(value = "/{id}",method = RequestMethod.GET)
		//ResponseEntity - encapsula toda uma estrutura necessária  para retornarmos respostas http
		//findById - busca os elementos através do ID 
		//@PathVariable eu falo pro metodo que o ID tem que casar com o ID da URL
		public ResponseEntity<UserDTO> findById(@PathVariable String id){
			
			//Abaixo instancio o objeto usuario recebendo o método findById
			//findById responsavel por verificar as execeções 
			User obj = service.findById(id);
			
							
			//Aqui devolvo a resposta da requisição convetido para UserDTO
			return ResponseEntity.ok().body(new UserDTO(obj));
		}
		
	//======================Método para inserir um novo usuario==========================================
	// Com esse método posso inserir usuarios usando o formato Json através do Postman por exemplo. aula 342	
		
		//@RequestMapping - Mapeia a solicitação e faz um POST nesse caso
		@RequestMapping(method = RequestMethod.POST)
		//ResponseEntity - encapsula toda uma estrutura necessária  para retornarmos respostas http
		//inser - metodo para inserção
		//@RequestBody método para buscar do corpo do UserDTO que foi passado como parametro
		public ResponseEntity<Void> insert(@RequestBody UserDTO objDTO){
			
			//Abaixo converto o tipo UserDTO para o tipo User
			User obj = service.fromDTO(objDTO);
			
			//Agora passo o proprio obj como argumento para o obj, aqui ele fa a inserção no Banco de Dados
			obj = service.insert(obj);
			
			//Abaixo retorno uma resposta vazia, porém com o cabeçalho com a Url do novo recurso criado, isso é boa pratica
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/id").buildAndExpand(obj.getId()).toUri();
			
			return ResponseEntity.created(uri).build();
		}
		
		
		
}
