package com.api.desarrollo.repository;

import com.api.desarrollo.entitys.Publicacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublicacionRepository extends JpaRepository<Publicacion,Integer> {
}
