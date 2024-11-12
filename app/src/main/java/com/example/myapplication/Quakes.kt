package com.example.myapplication

import androidx.annotation.RequiresFeature

data class Quakes
    (
    val metadata: Metadata,
    val features: List<Terremoto>
)

data class Metadata
    (
    val title: String,
    val count: Int
)

data class Terremoto
    (
    val id: String,
    val properties: Properties,
    val geometry: Geometry
)

data class Properties(
    val mag: Float,
    val place: String,
    val title: String
)

data class Geometry(
    val coordinates: List<Float>
)