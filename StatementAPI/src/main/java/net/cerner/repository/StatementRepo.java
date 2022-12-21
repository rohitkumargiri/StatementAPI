package net.cerner.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import net.cerner.entity.Statement;

@Repository
public interface StatementRepo extends JpaRepository<Statement, Serializable>{
	@Query("delete from Statement b where b.id=:id")
	void deleteStateMent(@Param("id") String title);
}
