package com.example.demo2.dao;

import com.example.demo2.domain.Direccion;
import com.example.demo2.domain.Estudiante;
import com.example.demo2.domain.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoDireccionInt extends JpaRepository<Direccion, Integer> {

    @Query("SELECT e FROM Direccion e WHERE e.estudiante = :estudiante")
    Direccion encontrarDireccionEstudiante(@Param("estudiante") Estudiante estudiante);

    @Query("SELECT e FROM Direccion e WHERE e.profesor = :profesor")
    Direccion encontrarDireccionProfesor(@Param("profesor") Profesor profesor);

}
