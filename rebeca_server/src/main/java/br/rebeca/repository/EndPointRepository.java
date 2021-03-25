

package br.rebeca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.rebeca.model.EndPoint;

@Repository
public interface EndPointRepository extends JpaRepository<EndPoint, Long> {


}
