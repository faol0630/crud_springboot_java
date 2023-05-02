package com.example.demo2.controller;

import com.example.demo2.domain.Curso;
import com.example.demo2.domain.Estudiante;
import com.example.demo2.domain.Profesor;
import com.example.demo2.service.ServiceCursoInt;
import com.example.demo2.service.ServiceInt;
import com.example.demo2.service.ServiceProfesorInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
public class CursoController {

    @Autowired
    private ServiceCursoInt serviceCursoInt;
    @Autowired
    private ServiceInt serviceEstudianteInt;

    @Autowired
    private ServiceProfesorInt serviceProfesorInt;


    @GetMapping("/lista_cursos")
    public String cursosDesdeMySQL(Model model){

        Long totalCursos = serviceCursoInt.cantidadCursos();
        model.addAttribute("totalCursos", totalCursos);

        List<Curso> cursos;
        cursos = serviceCursoInt.obtenerTodosCursos();
        model.addAttribute("cursos", cursos);

        return "curso/cursos_lista";
    }

    @GetMapping("/curso_modificar")
    public String irAgregarCurso(Curso curso){
        return "curso/modificar_curso";
    }

    @PostMapping("/agregar_curso")
    public String agregarCurso(@Valid Curso curso, Errors errors, Model model){
        //respetar el orden de los parametros: Entity, Errors, Model
        if (errors.hasErrors()){
            return "curso/modificar_curso";
        }
        serviceCursoInt.GuardarCurso(curso);
        return "redirect:/lista_cursos";
    }

    @GetMapping("/editar_curso/{id_curso}")
    public String editarCurso(Curso curso, Model model){
        curso = serviceCursoInt.encontrarCurso(curso);
        model.addAttribute("curso", curso);
        return "curso/modificar_curso";

    }

    @GetMapping("/curso_buscar")
    public String irABuscarCurso(Curso curso){
        return "curso/buscar_curso";
    }

    @GetMapping("/curso_id/{id_curso}")
    public String buscarCursoPorId(Curso curso, Model model){

        if (curso.getId_curso() == null){
            return "curso/buscar_curso";
        }

        List<Curso> cursos = serviceCursoInt.buscarPorId(curso);
        model.addAttribute("cursos", cursos);
        return "curso/buscar_curso";
    }

    @GetMapping("/curso_nombre/{nombre}")
    public String buscarCursoPorNombre(Curso curso, Model model){

        List<Curso> cursos = serviceCursoInt.buscarPorNombre(curso);
        model.addAttribute("cursos", cursos);
        return "curso/buscar_curso";
    }



    @GetMapping("/eliminar_curso/{id_curso}")
    public String eliminarCurso(Curso curso){
        serviceCursoInt.eliminarCurso(curso.getId_curso());
        return "redirect:/lista_cursos";
    }

    @GetMapping("/eliminar_cursos")
    public String eliminarTodosCursos(){
        serviceCursoInt.eliminarTodosCursos();
        return "redirect:/lista_cursos";

    }

    @GetMapping("/total_cursos")
    public Long totalCursos(){
        return serviceCursoInt.cantidadCursos();
    }

    @GetMapping("/curso_id")
    public String cursosOrdenadosPorId(Model model){

        Long totalCursos = serviceCursoInt.cantidadCursos();
        model.addAttribute("totalCursos", totalCursos);

        List<Curso> cursos;
        cursos = serviceCursoInt.cursosOrdenadosId();
        model.addAttribute("cursos", cursos);

        return "curso/cursos_lista";
    }

    @GetMapping("/curso_nombre")
    public String cursosOrdenadosPorNombre(Model model){

        Long totalCursos = serviceCursoInt.cantidadCursos();
        model.addAttribute("totalCursos", totalCursos);

        List<Curso> cursos;
        cursos = serviceCursoInt.cursosOrdenadosNombre();
        model.addAttribute("cursos", cursos);

        return "curso/cursos_lista";
    }

    @GetMapping("/curso_duracion")
    public String cursosOrdenadosPorDuracion(Model model){

        Long totalCursos = serviceCursoInt.cantidadCursos();
        model.addAttribute("totalCursos", totalCursos);

        List<Curso> cursos;
        cursos = serviceCursoInt.cursosOrdenadosDuracion();
        model.addAttribute("cursos", cursos);

        return "curso/cursos_lista";
    }

    @GetMapping("/cursos_matriculados/{estudiante}")
    public String cursosMatriculados( Model model, Estudiante estudiante){

        estudiante = serviceEstudianteInt.obtenerEstudiante(estudiante.getId());
        List<Curso> cursos = estudiante.getCursos();
        model.addAttribute("cursos", cursos);
        model.addAttribute("estudiante", estudiante);

        if (cursos.isEmpty()){
            return "estudiante/no_cursos_matriculados";
        }else{
            return "estudiante/matriculados";

        }

    }

    @GetMapping("/curso_intro/{estudiante}")
    public String asignarCursosAEstudiante(Estudiante estudiante, Model model){

        Long totalCursos = serviceCursoInt.cantidadCursos();
        model.addAttribute("totalCursos", totalCursos);

        estudiante = serviceEstudianteInt.obtenerEstudiante(estudiante.getId());
        model.addAttribute("estudiante", estudiante);

        List<Curso> cursos;
        cursos = serviceCursoInt.obtenerTodosCursos();
        model.addAttribute("cursos", cursos);

        return "curso/escoger_cursos";
    }

    @PostMapping("/agregar_curso_estudiante")
    public String agregarCursoAEstudiante(@RequestParam Integer id_estudiante, @RequestParam Integer id_curso){

        Estudiante estudiante = serviceEstudianteInt.obtenerEstudiante(id_estudiante);
        Curso curso = serviceCursoInt.obtenerCurso(id_curso);
        List<Curso> cursos = estudiante.getCursos();

        if (!cursos.contains(curso)){
            serviceEstudianteInt.agregarCursoAEstudiante(id_estudiante, id_curso);

        }
        return "redirect:/lista_index";

    }

    @GetMapping("/eliminar_curso_estudiante")
    public String eliminarCursoAEstudiante(@RequestParam Integer id_estudiante, @RequestParam Integer id_curso){

        serviceEstudianteInt.borrarCursoAEstudiante(id_estudiante, id_curso);

        return "redirect:/lista_index";
    }

    //NO IMPLEMENTADO EN THYMELEAF:
    @GetMapping("/buscar_profesor_del_curso")
    public Profesor buscarProfesorDelCurso(@RequestParam Integer id_curso, Model model){

        Profesor profesor = serviceProfesorInt.buscarProfesorDelCurso(id_curso);
        model.addAttribute("profesor", profesor);

        return profesor;
    }

}
