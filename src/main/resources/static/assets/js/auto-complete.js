	$(document).ready(function() {
        $("#search").autocomplete({
            source: function(request, response) {
                $.ajax({
                    url: "/product/name/"+ request.term,
                    dataType: "json",
                    data: {
                        term: request.term
                    },
                    success: function(data) {
                        response($.map(data, function(item) {
                            return {
                                label: item.name, // Este es el texto que se mostrará en el campo de búsqueda
                                value: item.id // Este es el valor que se enviará cuando se seleccione un elemento del autocomplete
                            };
                        }));
                    },
                });
            },
            minLength: 3 // Número mínimo de caracteres para que se active el autocomplete
        });
    });