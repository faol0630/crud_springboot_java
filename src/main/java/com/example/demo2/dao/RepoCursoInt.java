package com.example.demo2.dao;

import com.example.demo2.domain.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepoCursoInt extends JpaRepository<Curso, Integer> {

    @Query("SELECT e FROM Curso e ORDER BY e.id_curso ASC")
    List<Curso> cursosOrdenadosPorId();

    @Query("SELECT e FROM Curso e ORDER BY e.nombre ASC")
    List<Curso> cursosOrdenadosPorNombre();

    @Query("SELECT e FROM Curso e ORDER BY e.duracion DESC")
    List<Curso> cursosOrdenadosPorDuracion();

    @Query("SELECT e FROM Curso e WHERE e.id_curso = :id")
    List<Curso> buscarPorId(@Param("id")int id);

    @Query("SELECT e FROM Curso e WHERE e.nombre = :nombre")
    List<Curso> buscarPorNombre(@Param("nombre") String nombre);

}
