package org.generation.blogvagalume.repository;

import java.util.List;

import org.generation.blogvagalume.model.TemaVagalume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TemaRepository extends JpaRepository<TemaVagalume, Long> {
	public List<TemaVagalume> findAllByDescricaoContainingIgnoreCase(@Param("descricao")String descricao);
}
