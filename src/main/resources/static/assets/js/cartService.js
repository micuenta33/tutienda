// Lógica del carrito
class Cart {
    constructor() {
        const storedCart = JSON.parse(localStorage.getItem('cart'));
        if (storedCart) {
            this.carItems = storedCart.carItems;
            this.quantityProduct = storedCart.quantityProduct;
        } else {
            this.carItems = [];
            this.quantityProduct = 0;
        }
    }

    static getInstance() {
        return Cart.instance || new Cart();
    }
}

class CarItem {
    constructor(product, quantity, price) {
        this.product = product;
        this.quantity = quantity;
        this.price = price;
    }

    getPriceQuantity() {
        return this.quantity * this.price;
    }
}

// Crear una instancia de Cart
const cart = Cart.getInstance();

function addToCart(product) {
    console.log('Product added to cart:', product);
    // Buscar si el producto ya está en el carrito
    var existingItem = findCartItemByProduct(product);

    if (existingItem !== null) {
        // Si el producto ya está en el carrito, incrementar la cantidad
        existingItem.quantity++;
    } else {
        // Si el producto no está en el carrito, agregarlo como nuevo CarItem
        var newItem = new CarItem(product, 1, product.price);
        cart.carItems.push(newItem);
        // Actualizar la cantidad total de productos en el carrito
        cart.quantityProduct++;
    }
    localStorage.setItem('cart', JSON.stringify(cart));
    console.log('Cart updated:', cart);
    renderCartItems(); // Renderizar los elementos del carrito en la vista
    updateCartQuantity(); // Actualizar la cantidad del carrito en la vista
}

// Función de utilidad para buscar un CarItem por producto dentro del carrito
function findCartItemByProduct(product) {
    for (var i = 0; i < cart.carItems.length; i++) {
        var item = cart.carItems[i];
        if (item.product.id === product.id) {
            return item;
        }
    }
    return null;
}

// Función para renderizar los elementos del carrito
function renderCartItems() {

    updateCartQuantityFromLocalStorage();
    const cartTableBody = document.querySelector('#cartTable tbody');
    // Limpiar el cuerpo de la tabla antes de volver a agregar los elementos
    cartTableBody.innerHTML = '';
    // Iterar sobre los elementos del carrito y agregarlos al cuerpo de la tabla
    cart.carItems.forEach((item, index) => {
        item = new CarItem(item.product, item.quantity, item.price);
        // Crear una nueva fila de la tabla
        const cartTableRow = document.createElement('tr');
        cartTableRow.innerHTML = `
            <td class="col-2"><img class="img-fluid" src="${item.product.imagePrimary}" alt="Product Image"></td>
            <td class="col-2">${item.product.name}</td>
            <td class="col-2">
                <button onclick="decrementQuantity(${index})">-</button>
                <span id="quantity_${item.product.id}">${item.quantity}</span>
                <button onclick="incrementQuantity(${index})">+</button>
            </td>
            <td class="col-2">${item.price.toLocaleString('es-ES', { minimumFractionDigits: 2 })}&euro;</td>
            <td class="col-2">${item.getPriceQuantity().toLocaleString('es-ES', { minimumFractionDigits: 2 })}&euro;</td>
            <td class="col-2"><button class="btn-danger" onclick="removeItemFromCart(${index})">Eliminar</button></td>
        `;
        cartTableBody.appendChild(cartTableRow);
    });
        updateTotalCart();
}
// Función para eliminar un elemento del carrito
function removeItemFromCart(index) {
    cart.carItems.splice(index, 1); // Eliminar el elemento del carrito
    cart.quantityProduct--;
    updateTotalCart();
    updateCartQuantityFromLocalStorage();
    localStorage.setItem('cart', JSON.stringify(cart));
    renderCartItems(); // Renderizar los elementos del carrito en la vista
}

// Función para incrementar la cantidad de un producto en el carrito
function incrementQuantity(index) {
    cart.carItems[index].quantity++;
    updateTotalCart();
    updateCartQuantityFromLocalStorage();
    localStorage.setItem('cart', JSON.stringify(cart));
    renderCartItems(); // Renderizar los elementos del carrito en la vista
}

// Función para decrementar la cantidad de un producto en el carrito
function decrementQuantity(index) {
    cart.carItems[index].quantity--;
    if (cart.carItems[index].quantity <= 0) {
        cart.carItems.splice(index, 1); // Eliminar el elemento si la cantidad es 0 o menos
        cart.quantityProduct--;
    }
    updateTotalCart();
    updateCartQuantityFromLocalStorage();
    localStorage.setItem('cart', JSON.stringify(cart));
    renderCartItems(); // Renderizar los elementos del carrito en la vista
}

// Función para calcular el total del carrito
function calculateTotalCart() {
     // Recuperar el carrito del localStorage
       const storedCart = JSON.parse(localStorage.getItem('cart'));
       let total = 0;

       // Verificar si hay un carrito almacenado
       if (storedCart && storedCart.carItems) {
           // Iterar sobre los elementos del carrito
           for (const item of storedCart.carItems) {
               // Multiplicar el precio por la cantidad y sumar al total
               total += item.price * item.quantity;
           }
       }
       console.log(total)
       // Formatear el total como moneda
       const formattedTotal = total.toLocaleString('es-ES', { style: 'currency', currency: 'EUR', minimumFractionDigits: 2 });
       // Retornar el total formateado del carrito
       return formattedTotal;
}

// Función para actualizar el total del carrito en la interfaz de usuario
function updateTotalCart() {
    // Calcular el total del carrito
    const totalCart = calculateTotalCart().toLocaleString('es-ES', { style: 'currency', currency: 'EUR', minimumFractionDigits: 2 });
    console.log("total",totalCart)
    // Actualizar el total del carrito en la interfaz de usuario
    document.getElementById('totalPrice').textContent = totalCart;
}
document.addEventListener('DOMContentLoaded', updateTotalCart);

// Función para obtener la cantidad del carrito desde localStorage y actualizar el elemento HTML
function updateCartQuantityFromLocalStorage() {
    // Obtener los datos del carrito del localStorage
    const cartDataString = localStorage.getItem('cart');

    // Verificar si hay datos en el localStorage
    if (cartDataString) {
        // Parsear la cadena JSON para obtener el objeto del carrito
        const cartData = JSON.parse(cartDataString);

        // Acceder a la cantidad del carrito
        const cartQuantity = cartData.quantityProduct;

        // Actualizar el contenido del elemento HTML con la cantidad del carrito
        document.getElementById('cart-count').textContent = cartQuantity;
    } else {
        // Si no hay datos en el localStorage, establecer la cantidad en 0
        document.getElementById('cart-count').textContent = '0';
    }

}

// Llamar a la función para actualizar la cantidad del carrito al cargar la página
updateCartQuantityFromLocalStorage();

// Llamar a renderCartItems al cargar la página para mostrar los elementos del carrito
renderCartItems();

