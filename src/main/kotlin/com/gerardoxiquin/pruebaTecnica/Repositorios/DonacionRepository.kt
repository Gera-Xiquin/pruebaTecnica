package com.gerardoxiquin.pruebaTecnica.Repositorios

import com.gerardoxiquin.pruebaTecnica.Modelos.Donacion
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DonacionRepository : JpaRepository<Donacion, Long> {
    fun findByRubro_IdAndProyecto_Id(rubroId: Long, proyectoId: Long): List<Donacion>
}