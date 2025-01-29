package com.gerardoxiquin.pruebaTecnica.Servicios

import com.gerardoxiquin.pruebaTecnica.Modelos.Donacion
import com.gerardoxiquin.pruebaTecnica.Repositorios.DonacionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class DonacionService(@Autowired private val donacionRepository: DonacionRepository) {

    fun getAllDonaciones(): List<Donacion> {
        return donacionRepository.findAll()
    }

    fun getDonacionById(id: Long): Donacion? {
        return donacionRepository.findById(id).orElse(null)
    }

    fun saveDonacion(donacion: Donacion): Donacion {
        return donacionRepository.save(donacion)
    }

    fun deleteDonacion(id: Long) {
        donacionRepository.deleteById(id)
    }
}