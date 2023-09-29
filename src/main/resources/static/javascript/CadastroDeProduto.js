document.addEventListener("DOMContentLoaded", function () {
    const productForm = document.getElementById("productForm");

    productForm.addEventListener("submit", function (event) {
        event.preventDefault();


        const productName = document.getElementById("productName").value;
        const productRating = document.getElementById("productRating").value;
        const productDescription = document.getElementById("productDescription").value;
        const productPrice = document.getElementById("productPrice").value;
        const productStock = document.getElementById("productStock").value;


        console.log("Nome do Produto:", productName);
        console.log("Avaliação:", productRating);
        console.log("Descrição Detalhada:", productDescription);
        console.log("Preço do Produto:", productPrice);
        console.log("Quantidade em Estoque:", productStock);


        productForm.reset();
    });
});