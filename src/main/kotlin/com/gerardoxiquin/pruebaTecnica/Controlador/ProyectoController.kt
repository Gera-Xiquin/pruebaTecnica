package com.gerardoxiquin.pruebaTecnica.Controlador

import com.gerardoxiquin.pruebaTecnica.Modelos.Proyecto
import com.gerardoxiquin.pruebaTecnica.Servicios.ProyectoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/proyectos/")
class ProyectoController(@Autowired val proyectoService: ProyectoService) {

    @GetMapping("/")
    fun listProyectos(model: Model): String {
        val proyectos = proyectoService.getAllProyectos()
        model.addAttribute("proyectos", proyectos)
        return "proyectos/list"
    }

    @GetMapping("/nuevo")
    fun nuevoProyecto(model: Model): String {
        model.addAttribute(
            "proyecto",
            Proyecto(codigo = "", nombre = "", municipio = "", departamento = "", fechaInicio = "", fechaFin = "")
        )
        return "proyectos/form"
    }

    @PostMapping("/guardar")
    fun guardarProyecto(@ModelAttribute proyecto: Proyecto): String {
        proyectoService.saveProyecto(proyecto)
        return "redirect:/proyectos/"
    }

    @GetMapping("/editar/{id}")
    fun editarProyecto(@PathVariable id: String, model: Model): String {
        val proyecto = proyectoService.getProyectoByCodigo(id)
        if (proyecto != null) {
            model.addAttribute("proyecto", proyecto)
            return "proyectos/form"
        } else {
            return "redirect:/proyectos/"
        }
    }

    @GetMapping("/eliminar/{id}")
    fun eliminarProyecto(@PathVariable id: Long): String {
        proyectoService.deleteProyecto(id)
        return "redirect:/proyectos/"
    }
}