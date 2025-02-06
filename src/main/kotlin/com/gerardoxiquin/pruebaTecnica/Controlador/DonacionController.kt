package com.gerardoxiquin.pruebaTecnica.Controlador

import com.gerardoxiquin.pruebaTecnica.Modelos.Donacion
import com.gerardoxiquin.pruebaTecnica.Servicios.DonacionService
import com.gerardoxiquin.pruebaTecnica.Servicios.ProyectoService
import com.gerardoxiquin.pruebaTecnica.Servicios.RubroService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/donaciones")
class DonacionController(
    @Autowired val donacionService: DonacionService,
    @Autowired val proyectoService: ProyectoService,
    @Autowired val rubroService: RubroService
) {

    @GetMapping("/")
    fun listDonaciones(model: Model): String {
        model.addAttribute("donaciones", donacionService.getAllDonaciones())
        return "donaciones/list"
    }

    @GetMapping("/nuevo")
    fun nuevaDonacion(model: Model): String {
        model.addAttribute("donacion", Donacion(id = null, fecha = "", donante = "", monto = 0.0, proyecto = null, rubro = null))
        model.addAttribute("proyectos", proyectoService.getAllProyectos())
        model.addAttribute("rubros", rubroService.getAllRubros())
        return "donaciones/form"
    }

    @PostMapping("/guardar")
    fun  guardarDonacion(
        @ModelAttribute donacion: Donacion,
        @RequestParam("proyectoId") proyectoId: String,
        @RequestParam("rubroId") rubroId: Long
    ): String {
        val proyecto = proyectoService.getProyectoByCodigo (proyectoId)
            ?: throw IllegalArgumentException("Proyecto no encontrado")
        val rubro = rubroService.getRubroById(rubroId)
            ?: throw IllegalArgumentException("Rubro no encontrado")

        val nuevaDonacion = donacion.copy(proyecto = proyecto, rubro = rubro)
        donacionService.saveDonacion(nuevaDonacion)
        return "redirect:/donaciones/"
    }

    @GetMapping("/eliminar/{id}")
    fun eliminarDonacion(@PathVariable id: Long): String {
        donacionService.deleteDonacion(id)
        return "redirect:/donaciones/"
    }
}