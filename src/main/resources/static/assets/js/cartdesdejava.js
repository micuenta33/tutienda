  var quantity = 0;
  function addToCart(product) {
        console.log('product', product);

        // Realizar la llamada al backend para agregar el producto al carrito
       fetch('/add', {
              method: 'POST',
              headers: {
                  'Content-Type': 'application/json',
              },
              body: JSON.stringify(product)
        })
        .then(response => {
            if (response.ok) {
                console.log('response status:', response.status);
                // Obtener la cantidad de productos en el carrito desde la respuesta
                return response.json();
            } else {
                console.error('Error adding to cart:', response.status);
            }
        })
        .then(data => {

              quantity = data.quantityProduct;
              updateCart(quantity);

        })
        .catch(error => console.error('Error adding to cart:', error));
}
function updateCart(quantity){
            var cartCountElement = document.getElementById("cart-count");
            console.log('quantity:',  quantity);
            if (quantity > 0) {
                   cartCountElement.innerText = quantity;
               } else {
                   // Si no hay productos en el carrito, puedes mostrar un contador vacío o ocultar el contador
                   cartCountElement.innerText = '';
               }
}
updateCart(quantity);

