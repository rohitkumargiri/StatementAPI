package net.cerner.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import net.cerner.entity.AdditionalChecks;
@Repository
public interface AdditionalChecksRepo extends CrudRepository<AdditionalChecks, Long>{

}
