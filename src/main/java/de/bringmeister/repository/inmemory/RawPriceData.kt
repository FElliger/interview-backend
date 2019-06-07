package de.bringmeister.repository.inmemory

import de.bringmeister.model.Price

internal data class RawPriceData (
        val id: String,
        val price: Price,
        val unit: String
)