class Cart {
    constructor() {
        if (Cart.instance) {
            return Cart.instance;
        }

        this.carItems = [];
        this.quantityProduct = 0;

        Cart.instance = this;
    }

    static getInstance() {
        return Cart.instance || new Cart();
    }

    getTotalCart() {
        let total = 0;
        for (const item of this.carItems) {
            total += item.priceQuantity;
        }
        return total.toFixed(2);
    }
}
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
