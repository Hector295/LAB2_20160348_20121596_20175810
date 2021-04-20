package com.ipt.dashboard.repository;

import com.ipt.dashboard.entity.Actividad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActividadRepository extends JpaRepository<Actividad, Integer> {
    List<Actividad> findByIdproyecto(int idProyecto);

}
