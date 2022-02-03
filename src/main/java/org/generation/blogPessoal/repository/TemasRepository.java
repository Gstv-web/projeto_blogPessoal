package org.generation.blogPessoal.repository;

import java.util.List;

import org.generation.blogPessoal.model.Temas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemasRepository extends JpaRepository<Temas, Long> {

	public List<Temas> findAllByTagsContainingIgnoreCase (String tags);
	
}
