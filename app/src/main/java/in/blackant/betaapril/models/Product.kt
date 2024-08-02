package `in`.blackant.betaapril.models

data class Product(
    val id: String,
    val name: String,
    val price: Float,
    val supplierId: String,
    val supplier: Supplier?,
    val orders: Array<OrderProduct>?,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Product

        return id == other.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}
