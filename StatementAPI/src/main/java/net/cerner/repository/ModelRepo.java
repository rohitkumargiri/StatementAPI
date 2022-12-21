package net.cerner.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import net.cerner.entity.Model;
@Repository
public interface ModelRepo extends CrudRepository<Model, Long>{

}
