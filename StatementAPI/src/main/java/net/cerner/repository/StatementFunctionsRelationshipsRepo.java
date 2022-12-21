package net.cerner.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.cerner.entity.StatementFunctionsRelationships;

@Repository
public interface StatementFunctionsRelationshipsRepo extends JpaRepository<StatementFunctionsRelationships, Serializable>{

}
