package se.mueller.webservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.mueller.webservice.entities.Director;

@Repository
public interface DirectorRepository extends JpaRepository<Director, Long> {



}
