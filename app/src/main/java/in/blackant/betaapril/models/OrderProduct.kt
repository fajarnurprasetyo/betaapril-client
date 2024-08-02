    package `in`.blackant.betaapril.models

data class OrderProduct(
    val id: Int,
    val orderId: Int,
    val order: Order,
    val productId: String,
    val product: Product,
    val quantity: Int,
)
