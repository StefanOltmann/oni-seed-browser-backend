package de.stefan_oltmann.oni.dto;

import kotlinx.serialization.Serializable;
import model.World

@Serializable
data class SearchResponse(
    val worlds: List<World>,
    val count: Int = worlds.size
)