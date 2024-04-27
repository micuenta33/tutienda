class CartProduct {
    constructor(productId, quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }
}

// Función para agregar un producto al carrito
function addToCart(productId) {
    // Obtener la lista de productos del carrito almacenada en el almacenamiento local
    var cartProducts = JSON.parse(localStorage.getItem('cartProducts')) || [];

    // Buscar si el producto ya está en el carrito
    var existingItem = cartProducts.find(item => item.productId === productId);
        console.log("Producto:", existingItem);

    if (existingItem) {
        // Si el producto ya está en el carrito, aumentar la cantidad
        existingItem.quantity++;

    } else {
        // Si el producto no está en el carrito, crear un nuevo CartProduct y agregarlo
        var newProduct = new CartProduct(productId, 1);
        // Agregar el producto al carrito
        cartProducts.push(newProduct);
    }

    // Guardar la lista actualizada de productos del carrito en el almacenamiento local
    localStorage.setItem('cartProducts', JSON.stringify(cartProducts));

    // Actualizar el contador del carrito
    updateCartCount();

    // Imprimir la lista de productos en el carrito para verificar
    console.log("Productos en el carrito:", cartProducts);
}

// Función para actualizar el contador del carrito
function updateCartCount() {
    // Obtener la lista de productos del carrito almacenada en el almacenamiento local
    var cartProducts = JSON.parse(localStorage.getItem('cartProducts')) || [];

    // Obtener el elemento del contador del carrito
    var cartCountElement = document.getElementById("cart-count");

    // Obtener la cantidad actual de productos en el carrito
    var currentCount = cartProducts.length;

    // Actualizar el texto del contador del carrito
    cartCountElement.innerText = currentCount;

    // Imprimir el nuevo valor del contador para verificar
    console.log("Cart count updated to:", currentCount);
}

// Llamar a la función updateCartCount al cargar la página para actualizar el contador del carrito
updateCartCount();



function getProductCart(){
// Obtener los IDs de los productos del carrito del almacenamiento local
var cartIds = JSON.parse(localStorage.getItem('cartProducts')) || [];
console.log("Cart count updated to:", cartIds);

// Construir la URL con los IDs de los productos como parámetros de consulta
var url = "/cart?";
for (var i = 0; i < cartIds.length; i++) {
url += "ids=" + cartIds[i].productId;
if (i < cartIds.length - 1) {
url += "&";
  }
}
console.log("Cart count updated to:", url);

    document.getElementById("cart-icon").setAttribute("href", url);
     }

// Función para obtener los productos de la base de datos y mostrarlos en el HTML
function displayProductsInCart() {
     var cartIds = JSON.parse(localStorage.getItem('cartProducts')) || [];
       console.log("objeto:", cartIds);

       // Iterar sobre los productos en el carrito y actualizar la cantidad en el HTML
       cartIds.forEach(cartItem => {
           var productId = cartItem.productId;
           console.log("productId:", productId);
           var quantity = cartItem.quantity;
           console.log("quantity:", quantity);

           // Obtener el elemento del input de cantidad del producto en el HTML
           var quantityInput = document.getElementById("quantity-input");
           console.log("input:", quantityInput);

           // Actualizar la cantidad del producto en el HTML
           if (quantityInput) {
               quantityInput.value = quantity;
           }
       });
}

// Llamar a la función para mostrar los productos en el carrito al cargar la página
displayProductsInCart();