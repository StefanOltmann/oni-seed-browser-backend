package de.stefan_oltmann.oni.dto;

import de.stefan_oltmann.oni.model.WorldSummary
import kotlinx.serialization.Serializable

@Serializable
data class SearchResponse(
    val page: Int,
    val pageSize: Int,
    val totalPages: Int,
    val totalResults: Int,
    val worlds: List<WorldSummary>
)