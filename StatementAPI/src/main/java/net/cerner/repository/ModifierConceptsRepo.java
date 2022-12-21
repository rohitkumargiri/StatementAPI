package net.cerner.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import net.cerner.entity.ModifierConcepts;
@Repository
public interface ModifierConceptsRepo extends CrudRepository<ModifierConcepts, Long>{

}
