package org.generation.blogvagalume.repository;

import java.util.List;

import org.generation.blogvagalume.model.PostagemVagalume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PostagemRepository extends JpaRepository<PostagemVagalume, Long> {
	public List<PostagemVagalume> findAllByTituloContainingIgnoreCase(@Param("titulo") String titulo);
}
