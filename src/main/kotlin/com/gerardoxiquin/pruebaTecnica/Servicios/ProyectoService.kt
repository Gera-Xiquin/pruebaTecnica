package com.gerardoxiquin.pruebaTecnica.Servicios

import com.gerardoxiquin.pruebaTecnica.Modelos.Proyecto
import com.gerardoxiquin.pruebaTecnica.Repositorios.ProyectoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service
class ProyectoService(@Autowired val proyectoRepository: ProyectoRepository) {

    fun getAllProyectos(): List<Proyecto> = proyectoRepository.findAll()

    fun getProyectoByCodigo(codigo: String): Proyecto? = proyectoRepository.findByCodigo(codigo)

    fun saveProyecto(proyecto: Proyecto) {
        if(proyecto.codigo?.length==0) {
            val nuevoCodigo = generarCodigoProyecto()
            val proyectoConCodigo = proyecto.copy(codigo = nuevoCodigo) // Asigna el ID personalizado
            proyectoRepository.save(proyectoConCodigo)
        }else{
            proyectoRepository.save(proyecto)
        }
    }

    private fun generarCodigoProyecto(): String {
        // Obtiene el último código en la base de datos
        val ultimoProyecto = proyectoRepository.findFirstByOrderByCodigoDesc()
        return if (ultimoProyecto != null) {
            val numeroActual = ultimoProyecto.codigo!!.substringAfter("P-").toInt()
            "P-${String.format("%04d", numeroActual + 1)}" // Incrementa el número
        } else {
            "P-0001" // Si no hay registros, empieza en P-0001
        }
    }

    fun updateProyecto(proyecto: Proyecto): Proyecto = proyectoRepository.save(proyecto)

    fun deleteProyecto(id: Long) = proyectoRepository.deleteById(id)
}