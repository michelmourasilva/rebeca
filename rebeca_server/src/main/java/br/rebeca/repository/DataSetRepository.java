package br.rebeca.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.rebeca.model.EndPoint;

@Repository
public interface DataSetRepository extends JpaRepository<EndPoint, Long> {


}
