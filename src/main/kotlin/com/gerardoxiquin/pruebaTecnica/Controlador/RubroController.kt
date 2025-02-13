package com.gerardoxiquin.pruebaTecnica.Controlador

import com.gerardoxiquin.pruebaTecnica.Modelos.Rubro
import com.gerardoxiquin.pruebaTecnica.Servicios.ProyectoService
import com.gerardoxiquin.pruebaTecnica.Servicios.RubroService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/rubros")
class RubroController(@Autowired val rubroService: RubroService, @Autowired val proyectoService: ProyectoService) {

    @GetMapping("/")
    fun listRubros(model: Model): String {
        model.addAttribute("rubros", rubroService.getAllRubros())
        return "rubros/list"
    }

    @GetMapping("/nuevo")
    fun nuevoRubro(model: Model): String {
        model.addAttribute("rubro", Rubro(id = null, nombre = "", proyecto = null))
        model.addAttribute("proyectos", proyectoService.getAllProyectos())
        return "rubros/form"
    }

    @PostMapping("/guardar")
    fun guardarRubro(@ModelAttribute rubro: Rubro ,  model: Model): String {
        val rubroNombreExiste = rubroService.findByNombreAndProyecto_Id(rubro.nombre,rubro.proyecto?.id?:0)
        if(rubroNombreExiste == null) {
            rubroService.saveRubro(rubro)
            return "redirect:/rubros/"
        }else{
            model.addAttribute("mensajeError", "El rubro ya existe en ese proyecto.")
            // Vuelve a mostrar el formulario para crear/editar rubro
            // Asegúrate de que en el modelo también incluyas la lista de proyectos, si es necesario
            model.addAttribute("proyectos", proyectoService.getAllProyectos()) // Ejemplo, ajusta según tu lógica
            return "rubros/form"
        }
    }

    @GetMapping("/editar/{id}")
    fun editarRubro(@PathVariable id: Long, model: Model): String {
        val rubro = rubroService.getRubroById(id)
        if (rubro != null) {
            model.addAttribute("rubro", rubro)
            model.addAttribute("proyectos", proyectoService.getAllProyectos())
            return "rubros/form"
        }else {
            return "redirect:/rubros/"
        }
    }

    @GetMapping("/eliminar/{id}")
    fun eliminarRubro(@PathVariable id: Long): String {
        rubroService.deleteRubro(id)
        return "redirect:/rubros/"
    }
}