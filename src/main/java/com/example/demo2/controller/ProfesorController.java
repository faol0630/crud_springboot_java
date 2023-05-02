package com.example.demo2.controller;

import com.example.demo2.domain.Curso;
import com.example.demo2.domain.Profesor;
import com.example.demo2.service.ServiceCursoInt;
import com.example.demo2.service.ServiceProfesorInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ProfesorController {

    @Autowired
    private ServiceProfesorInt serviceProfesorInt;
    @Autowired
    private ServiceCursoInt serviceCursoInt;


    @GetMapping("/lista_profesores")
    public String profesoresDesdeMySQL(Model model){

        Long totalProfesores = serviceProfesorInt.cantidadProfesores();
        model.addAttribute("totalProfesores", totalProfesores);

        List<Profesor> profesores;
        profesores = serviceProfesorInt.obtenerTodosProfesores();
        model.addAttribute("profesores", profesores);

        return "profesor/profesores_lista";
    }

    @GetMapping("/profesor_modificar")
    public String irAgregarProfesor(Profesor profesor){
        return "profesor/modificar_profesor";
    }

    @PostMapping("/agregar_profesor")
    public String agregarProfesor(@Valid Profesor profesor, Errors errors, Model model){
        //respetar el orden de los parametros: Entity, Errors, Model
        if (errors.hasErrors()){
            return "profesor/modificar_profesor";
        }
        serviceProfesorInt.GuardarProfesor(profesor);
        return "redirect:/nueva_direccion_profesor/" + profesor.getId();
    }

    @GetMapping("/editar_profesor/{id}")
    public String editarProfesor(Profesor profesor, Model model){
        profesor = serviceProfesorInt.encontrarProfesor(profesor);
        model.addAttribute("profesor", profesor);
        return "profesor/modificar_profesor";

    }

    @GetMapping("/profesor_buscar")
    public String irABuscarProfesor(Profesor profesor){
        return "profesor/buscar_profesor";
    }

    @GetMapping("/profesor_id/{id}")
    public String buscarProfesorPorId(Profesor profesor, Model model){

        if (profesor.getId() == null){
            return "profesor/buscar_profesor";
        }

        List<Profesor> profesores = serviceProfesorInt.buscarPorId(profesor);
        model.addAttribute("profesores", profesores);
        return "profesor/buscar_profesor";
    }

    @GetMapping("/profesor_nombre/{nombre}")
    public String buscarProfesorPorNombre(Profesor profesor, Model model){

        List<Profesor> profesores = serviceProfesorInt.buscarPorNombre(profesor);
        model.addAttribute("profesores", profesores);
        return "profesor/buscar_profesor";
    }

    @GetMapping("/eliminar_profesor/{id}")
    public String eliminarProfesor(Profesor profesor){
        serviceProfesorInt.eliminarProfesorPorId(profesor.getId());
        return "redirect:/lista_profesores";
    }

    @GetMapping("/eliminar_profesores")
    public String eliminarTodosProfesores(){
        serviceProfesorInt.eliminarTodosProfesores();
        return "redirect:/lista_profesores";

    }

    @GetMapping("/total_profesores")
    public Long totalProfesores(){
        return serviceProfesorInt.cantidadProfesores();
    }

    @GetMapping("/profesor_id")
    public String profesoresOrdenadosPorId(Model model){

        Long totalProfesores = serviceProfesorInt.cantidadProfesores();
        model.addAttribute("totalProfesores", totalProfesores);

        List<Profesor> profesores;
        profesores = serviceProfesorInt.profesoresOrdenadosPorId();
        model.addAttribute("profesores", profesores);

        return "profesor/profesores_lista";
    }

    @GetMapping("/profesor_nombre")
    public String profesoresOrdenadosPorNombre(Model model){

        Long totalProfesores = serviceProfesorInt.cantidadProfesores();
        model.addAttribute("totalProfesores", totalProfesores);

        List<Profesor> profesores;
        profesores = serviceProfesorInt.profesoresOrdenadosPorNombre();
        model.addAttribute("profesores", profesores);

        return "profesor/profesores_lista";
    }

    @GetMapping("/profesor_apellido")
    public String profesoresOrdenadosPorApellido(Model model){

        Long totalProfesores = serviceProfesorInt.cantidadProfesores();
        model.addAttribute("totalProfesores", totalProfesores);

        List<Profesor> profesores;
        profesores = serviceProfesorInt.profesoresOrdenadosPorApellido();
        model.addAttribute("profesores", profesores);

        return "profesor/profesores_lista";
    }

    @GetMapping("/profesor_correo")
    public String profesoresOrdenadosPorCorreo(Model model){

        Long totalProfesores = serviceProfesorInt.cantidadProfesores();
        model.addAttribute("totalProfesores", totalProfesores);

        List<Profesor> profesores;
        profesores = serviceProfesorInt.profesoresOrdenadosPorCorreo();
        model.addAttribute("profesores", profesores);

        return "profesor/profesores_lista";
    }

    @GetMapping("/curso_profesor_intro")
    public String asignarCursosAProfesor(@RequestParam Integer id_profesor, Model model){

        Long totalCursos = serviceCursoInt.cantidadCursos();
        model.addAttribute("totalCursos", totalCursos);

        Profesor profesor = serviceProfesorInt.obtenerProfesorPorId(id_profesor);
        model.addAttribute("profesor", profesor);

        //todos los cursos:
        List<Curso> temporal;
        temporal = serviceCursoInt.obtenerTodosCursos();

        List<Curso> cursos = new ArrayList<>();

        //cursos que no tienen profesor asignado:
        temporal.forEach(curso -> {
            if (curso.getProfesor() == null){
                cursos.add(curso);
            }
        });


        model.addAttribute("cursos", cursos);

        return "profesor/escoger_cursos_profesor";
    }

    @PostMapping("/agregar_curso_profesor")
    public String agregarCursoAProfesor(@RequestParam Integer id_profesor, @RequestParam Integer id_curso){

        serviceProfesorInt.agregarProfesorACurso(id_profesor, id_curso);
        return "redirect:/lista_profesores";

    }

    @GetMapping("/eliminar_curso_profesor")
    public String eliminarCursoAProfesor(@RequestParam Integer id_profesor, @RequestParam Integer id_curso){

        serviceProfesorInt.borrarCursoAProfesor(id_profesor, id_curso);

        return "redirect:/lista_profesores";
    }

    @GetMapping("/cursos_asignados_profesor")
    public String cursosAsignadosAProfesor(@RequestParam Integer id_profesor, Model model){

        Profesor profesor = serviceProfesorInt.obtenerProfesorPorId(id_profesor);
        List<Curso> cursos = profesor.getCursos();
        model.addAttribute("profesor", profesor);
        model.addAttribute("cursos", cursos);

        if (cursos.isEmpty()){
            return "curso/no_cursos_asignados";
        }else{
            return "curso/asignados";

        }

    }

}
