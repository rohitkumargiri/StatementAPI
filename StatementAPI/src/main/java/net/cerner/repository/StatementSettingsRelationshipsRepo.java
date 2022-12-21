package net.cerner.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.cerner.entity.StatementSettingsRelationships;

@Repository
public interface StatementSettingsRelationshipsRepo extends JpaRepository<StatementSettingsRelationships, Serializable>{

}
