package de.bringmeister.model

data class Product(
        val id: String,
        val name: String,
        val description: String,
        val sku: String,
        val prices: Map<String, Price>
)