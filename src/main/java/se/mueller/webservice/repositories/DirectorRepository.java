package se.mueller.webservice.repositories;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import se.mueller.webservice.dtos.Directordto;
import se.mueller.webservice.entities.Director;

import java.util.List;


@Repository
public interface DirectorRepository extends JpaRepository<Director, Long>, JpaSpecificationExecutor<Directordto> {


}
