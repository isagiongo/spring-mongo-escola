package com.isagiongo.escola.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.isagiongo.escola.models.Aluno;

public interface AlunoRepository extends MongoRepository<Aluno, String> {
	
}
