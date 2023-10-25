package com.vagnersilva.workshopmongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.vagnersilva.workshopmongo.domain.User;

/*Na minha minha interface extendo a Classe MongoRepository porque ele esta dentro do 
 * Spring Data, e já tem varios métodos prontos para uso, isso facilita muito o trabalho
 * e passo como parametro o Tipo da Classe de dominio que ele vai gerenciar, nesse caso
 * a classe a ser gerenciada é a classe User e o segundo parametro passado é o tipo do ID
 * dessa minha classe, se formos lá na classe veremos que o ID é do tipo String
 * */
@Repository
public interface UserRepository extends MongoRepository<User, String> {


}
