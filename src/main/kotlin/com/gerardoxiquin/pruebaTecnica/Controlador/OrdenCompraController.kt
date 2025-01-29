package com.gerardoxiquin.pruebaTecnica.Controlador

import com.gerardoxiquin.pruebaTecnica.Modelos.OrdenCompra
import com.gerardoxiquin.pruebaTecnica.Servicios.OrdenCompraService
import com.gerardoxiquin.pruebaTecnica.Servicios.ProyectoService
import com.gerardoxiquin.pruebaTecnica.Servicios.RubroService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/ordenes")
class OrdenCompraController(
    @Autowired val ordenCompraService: OrdenCompraService,
    @Autowired val proyectoService: ProyectoService,
    @Autowired val rubroService: RubroService
) {
    @GetMapping("/")
    fun listOrdenes(model: Model): String {
        model.addAttribute("ordenes", ordenCompraService.getAllOrdenes())
        return "ordenes/list"
    }

    @GetMapping("/nuevo")
    fun nuevaOrden(model: Model): String {
        model.addAttribute("orden", OrdenCompra(id = null, fecha = "", proveedor = "", monto = 0.0, proyecto = null, rubro = null))
        model.addAttribute("proyectos", proyectoService.getAllProyectos())
        model.addAttribute("rubros", rubroService.getAllRubros())
        return "ordenes/form"
    }

    @PostMapping("/guardar")
    fun guardarOrden(@ModelAttribute orden: OrdenCompra): String {
        ordenCompraService.saveOrden(orden)
        return "redirect:/ordenes/"
    }

    @GetMapping("/eliminar/{id}")
    fun eliminarOrden(@PathVariable id: Long): String {
        ordenCompraService.deleteOrden(id)
        return "redirect:/ordenes/"
    }
}