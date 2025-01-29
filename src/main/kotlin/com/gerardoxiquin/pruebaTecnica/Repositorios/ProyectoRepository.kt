package com.gerardoxiquin.pruebaTecnica.Repositorios

import com.gerardoxiquin.pruebaTecnica.Modelos.Proyecto
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProyectoRepository : JpaRepository<Proyecto, Long> {
    fun findByCodigo(codigo: String): Proyecto?
}