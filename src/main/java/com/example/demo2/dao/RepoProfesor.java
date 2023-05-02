package com.example.demo2.dao;

import com.example.demo2.domain.Curso;
import com.example.demo2.domain.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface RepoProfesor extends JpaRepository <Profesor, Integer> {

    @Query(
            value = "SELECT e FROM curso e WHERE e.id_profesor = :id_profesor",
            nativeQuery = true
    )
    List<Curso> cursosAsignadosAProfesor(@Param("id_profesor") Integer id_profesor);

    @Modifying
    @Transactional
    @Query(
            value = "UPDATE curso SET id_profesor = :id_profesor WHERE id_curso = :id_curso",
            nativeQuery = true
    )
    void agregarProfesorACurso(@Param("id_profesor") Integer id_profesor, @Param("id_curso") Integer id_curso);

    @Modifying
    @Transactional
    @Query(
            value = "UPDATE curso SET id_profesor = null WHERE id_profesor = :id_profesor AND id_curso = :id_curso",
            nativeQuery = true
    )
    void borrarCursoAProfesor(@Param("id_profesor") Integer id_profesor, @Param("id_curso") Integer id_curso);


    @Query("SELECT e FROM Profesor e ORDER BY e.id ")
    List<Profesor> profesoresOrdenadosPorId();

    @Query("SELECT e FROM Profesor e ORDER BY e.nombre ")
    List<Profesor> profesoresOrdenadosPorNombre();

    @Query("SELECT e FROM Profesor e ORDER BY e.apellido ")
    List<Profesor> profesoresOrdenadosPorApellido();

    @Query("SELECT e FROM Profesor e ORDER BY e.correo ")
    List<Profesor> profesoresOrdenadosPorCorreo();

    @Query("SELECT e FROM Profesor e WHERE e.id = :id")
    List<Profesor> buscarPorId(@Param("id")int id);

    @Query("SELECT e FROM Profesor e WHERE e.nombre = :nombre")
    List<Profesor> buscarPorNombre(@Param("nombre") String nombre);


    //NO IMPLEMENTADO EN THYMELEAF:
    @Query(
            value = "SELECT t.name, t.lastname FROM teacher t, curso c WHERE c.id_curso = :id_curso AND c.id_profesor = t.id; ",
            nativeQuery = true
    )
    Profesor buscarProfesorDelCurso(@Param("id_curso") Integer id_curso);

}
