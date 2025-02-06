package com.gerardoxiquin.pruebaTecnica.Modelos

import jakarta.persistence.*

@Entity
@Table(name = "rubros")
data class Rubro(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val nombre: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "proyecto_id")
    val proyecto: Proyecto?
)