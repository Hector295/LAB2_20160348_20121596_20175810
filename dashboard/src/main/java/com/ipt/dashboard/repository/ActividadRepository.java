package com.ipt.dashboard.repository;

import com.ipt.dashboard.entity.Actividad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActividadRepository extends JpaRepository<Actividad, Integer> {
    List<Actividad> findByIdproyecto(int idProyecto);

    @Query(value = "select sum(peso) from actividades where estado ='1' and idproyecto = ?1;",nativeQuery = true)
    double getPesosFinales(int idproyecto);

    @Query(value = "select sum(peso) from actividades where idproyecto=?1;", nativeQuery = true)
    double getPesosTotales(int idproyecto);
}
