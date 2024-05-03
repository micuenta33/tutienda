class CarItem {
    constructor(product, quantity, price) {
        this.product = product;
        this.quantity = quantity;
        this.price = price;
    }

    getPriceQuantity() {
        const totalPrice = this.quantity * this.product.price;
        return parseFloat(totalPrice.toFixed(2));
    }
}