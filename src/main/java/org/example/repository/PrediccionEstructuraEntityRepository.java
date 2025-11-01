package org.example.repository;

import org.example.model.PrediccionEstructuraEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrediccionEstructuraEntityRepository extends JpaRepository<PrediccionEstructuraEntity, Long> {
    List<PrediccionEstructuraEntity> findBySecuencia(String secuencia);
}
