

package br.rebeca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.rebeca.model.EndPoint;

@Repository
public interface EndPointRepository extends JpaRepository<EndPoint, Long> {

	List<EndPoint> findAllByNoProjeto(String noProjeto);

}
