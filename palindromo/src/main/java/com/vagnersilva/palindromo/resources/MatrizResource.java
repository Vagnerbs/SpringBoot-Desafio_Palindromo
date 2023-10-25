package com.vagnersilva.palindromo.resources;

import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.vagnersilva.palindromo.services.MatrizService;

//Notação para indicar que esta classe é um controlador REST
@RestController
//Notação para mapear solicitações web para /matrizes
@RequestMapping(value = "/matrizes")
public class MatrizResource {

	// Injeção de dependência do serviço de matriz
	@Autowired
	private MatrizService service;
	
    // Endpoint Post, busca a matriz e salva os palindromos
    @PostMapping("/resposta")
    public ResponseEntity<Set<String>> encontrarESalvarPalindromos(){

        return ResponseEntity.ok().body(service.encontrarESalvarPalindromos());
    }
    
    //Endpoint, retorna os palindromos
    @GetMapping("/resposta")
    public ResponseEntity<List<String>> getPalindromos(){

        return ResponseEntity.ok().body(service.getPalindromos());
    }

}
