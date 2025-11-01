package org.example.repository;

import org.example.model.AlineamientoSecuenciasEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlineamientoSecuenciasEntityRepository extends JpaRepository<AlineamientoSecuenciasEntity, Long> {
    List<AlineamientoSecuenciasEntity> findBySecuencia1AndSecuencia2(String secuencia1, String secuencia2);
}
