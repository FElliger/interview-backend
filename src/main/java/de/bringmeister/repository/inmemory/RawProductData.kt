package de.bringmeister.repository.inmemory

import de.bringmeister.model.Product

internal class RawProductData(
        val id: String,
        val name: String,
        val description: String,
        val sku: String
) {
    private var prices: List<RawPriceData> = emptyList()

    fun addPrice(rawPrice: RawPriceData) {
        prices = prices.plus(rawPrice)
    }

    fun toProduct(): Product {
        return Product(id, name, description, sku, prices.map { p -> p.unit to p.price }.toMap())
    }
}
