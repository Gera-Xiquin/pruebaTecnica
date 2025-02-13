package com.gerardoxiquin.pruebaTecnica.Servicios

import com.gerardoxiquin.pruebaTecnica.Modelos.Rubro
import com.gerardoxiquin.pruebaTecnica.Repositorios.RubroRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class RubroService(@Autowired private val rubroRepository: RubroRepository) {

    fun getAllRubros(): List<Rubro> {
        return rubroRepository.findAll()
    }

    fun getRubroById(id: Long): Rubro? {
        return rubroRepository.findById(id).orElse(null)
    }

    fun saveRubro(rubro: Rubro): Rubro {
        return rubroRepository.save(rubro)
    }

    fun deleteRubro(id: Long) {
        rubroRepository.deleteById(id)
    }
    fun findByNombreAndProyecto_Id(nombre:String,proyectoId :Long):Rubro?{
        return rubroRepository.findByNombreAndProyecto_Id(nombre, proyectoId)
    }
}
