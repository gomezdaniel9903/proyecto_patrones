package org.example.repository;
import org.example.model.DeteccionMotivosEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeteccionMotivosEntityRepository extends JpaRepository<DeteccionMotivosEntity, Long> {
    List<DeteccionMotivosEntity> findBySecuencia(String secuencia);
}
