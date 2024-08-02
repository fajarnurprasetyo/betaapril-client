package `in`.blackant.betaapril.models

import java.util.Date

data class Order(
    val id: Int,
    val date: Date,
    val paid: Float,
    val products: Array<OrderProduct>?,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Order

        return id == other.id
    }

    override fun hashCode(): Int {
        return id
    }
}
