package net.cerner.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import net.cerner.entity.PrimaryConcepts;
@Repository
public interface PrimaryConceptsRepo extends CrudRepository<PrimaryConcepts, Long> {

}
