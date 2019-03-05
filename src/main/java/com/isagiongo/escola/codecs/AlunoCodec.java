package com.isagiongo.escola.codecs;

import java.util.Date;

import org.bson.BsonReader;
import org.bson.BsonString;
import org.bson.BsonValue;
import org.bson.BsonWriter;
import org.bson.Document;
import org.bson.codecs.Codec;
import org.bson.codecs.CollectibleCodec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;
import org.bson.types.ObjectId;

import com.isagiongo.escola.models.Aluno;
import com.isagiongo.escola.models.Curso;

public class AlunoCodec implements CollectibleCodec<Aluno>{

	private Codec<Document> codec;

	  public AlunoCodec(Codec<Document> codec) {
	    this.codec = codec;
	  }
	  
	@Override
	public void encode(BsonWriter writer, Aluno aluno, EncoderContext encoderContext) {
		ObjectId id = aluno.getId();
		  String nome = aluno.getNome();
		  Date dataNascimento = aluno.getDataNascimento();
		  Curso curso = aluno.getCurso();

		  Document documento = new Document();
		  documento.put("_id", id);
		  documento.put("nome", nome);
		  documento.put("data_nascimento", dataNascimento);
		  documento.put("curso", new Document("nome", curso.getNome()));
	}

	@Override
	public Class<Aluno> getEncoderClass() {
		return Aluno.class;
	}

	@Override
	public Aluno decode(BsonReader reader, DecoderContext decoderContext) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Aluno generateIdIfAbsentFromDocument(Aluno document) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean documentHasId(Aluno aluno) {
		return aluno.getId() == null;
	}

	@Override
	public BsonValue getDocumentId(Aluno aluno) {
		if(!documentHasId(aluno)){
		    throw new IllegalStateException("Esse Document não tem id");
		  }
		  return new BsonString(aluno.getId().toHexString());
	}

}
