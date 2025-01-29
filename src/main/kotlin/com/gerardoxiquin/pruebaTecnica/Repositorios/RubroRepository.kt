package com.gerardoxiquin.pruebaTecnica.Repositorios

import com.gerardoxiquin.pruebaTecnica.Modelos.Rubro
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RubroRepository : JpaRepository<Rubro, Long> {
    fun findByNombreAndProyecto_Id(nombre: String, proyectoId: Long): Rubro?
}