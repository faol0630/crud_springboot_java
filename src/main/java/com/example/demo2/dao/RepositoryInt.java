package com.example.demo2.dao;

import com.example.demo2.domain.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface RepositoryInt extends JpaRepository<Estudiante, Integer> {

        @Query("SELECT e FROM Estudiante e WHERE e.nombre = :nombre")
        List<Estudiante> buscarPorNombre(@Param("nombre") String nombre);

        @Query("SELECT e FROM Estudiante e WHERE e.apellido = :apellido")
        List<Estudiante> buscarPorApellido(@Param("apellido") String apellido);

        @Query("SELECT e FROM Estudiante e WHERE e.id = :id")
        List<Estudiante> buscarPorId(@Param("id") int id);

        @Query("SELECT e FROM Estudiante e ORDER BY e.id ASC")
        List<Estudiante> estudiantesOrdenadosPorId();

        @Query("SELECT e FROM Estudiante e ORDER BY e.nombre ASC")
        List<Estudiante> estudiantesOrdenadosPorNombre();

        @Query("SELECT e FROM Estudiante e ORDER BY e.apellido ASC")
        List<Estudiante> estudiantesOrdenadosPorApellido();

        @Query("SELECT e FROM Estudiante e ORDER BY e.correo ASC")
        List<Estudiante> estudiantesOrdenadosPorCorreo();

        @Query("SELECT e FROM Estudiante e WHERE e.nombre = :nombre AND e.apellido = :apellido")
        List<Estudiante> buscarPorNombreYApellido(@Param("nombre") String nombre,@Param("apellido") String apellido);

        @Query("SELECT e FROM Estudiante e WHERE e.nombre = :nombre OR e.apellido = :apellido")
        List<Estudiante> buscarPorNombreOApellido(@Param("nombre") String nombre,@Param("apellido") String apellido);

        @Modifying
        @Transactional
        @Query(
                value = "INSERT INTO estudiante_curso VALUES( :estudiante_id, :curso_id);",
                nativeQuery = true
        )
        void agregarCursoAEstudiante(@Param("estudiante_id") Integer estudiante_id, @Param("curso_id") Integer curso_id);

        @Modifying
        @Transactional
        @Query(
                value = "DELETE FROM estudiante_curso WHERE estudiante_id = :estudiante_id AND curso_id = :curso_id",
                nativeQuery = true
        )
        void borrarCursoAEstudiante(@Param("estudiante_id") Integer estudiante_id, @Param("curso_id") Integer curso_id);

}
