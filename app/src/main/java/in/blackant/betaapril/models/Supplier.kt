package `in`.blackant.betaapril.models

data class Supplier(
    val id: String?,
    val name: String,
    val address: String,
    val phone: String,
    val products: Array<Product>?
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Supplier

        return id == other.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}
