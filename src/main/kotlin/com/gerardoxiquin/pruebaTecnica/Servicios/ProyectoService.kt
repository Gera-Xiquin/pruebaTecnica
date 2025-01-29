package com.gerardoxiquin.pruebaTecnica.Servicios

import com.gerardoxiquin.pruebaTecnica.Modelos.Proyecto
import com.gerardoxiquin.pruebaTecnica.Repositorios.ProyectoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service
class ProyectoService(@Autowired val proyectoRepository: ProyectoRepository) {

    fun getAllProyectos(): List<Proyecto> = proyectoRepository.findAll()

    fun getProyectoByCodigo(codigo: String): Proyecto? = proyectoRepository.findByCodigo(codigo)

    fun saveProyecto(proyecto: Proyecto): Proyecto = proyectoRepository.save(proyecto)

    fun updateProyecto(proyecto: Proyecto): Proyecto = proyectoRepository.save(proyecto)

    fun deleteProyecto(id: Long) = proyectoRepository.deleteById(id)
}