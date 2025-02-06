package com.gerardoxiquin.pruebaTecnica.Modelos

import jakarta.persistence.*

@Entity
@Table(name = "proyectos")
data class Proyecto(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(unique = true)
    val codigo: String? = "", // Código autogenerado

    val nombre: String="",
    val municipio: String="",
    val departamento: String="",
    val fechaInicio: String="",
    val fechaFin: String="",
    @OneToMany(mappedBy = "proyecto", cascade = [CascadeType.ALL], orphanRemoval = true)
    val rubros: List<Rubro> = mutableListOf()
)