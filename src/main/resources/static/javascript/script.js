// script.js

document.addEventListener("DOMContentLoaded", function () {
    const productForm = document.getElementById("productForm");

    productForm.addEventListener("submit", function (event) {
        event.preventDefault();

        // Obtenha os valores do formulário
        const productName = document.getElementById("productName").value;
        const productRating = document.getElementById("productRating").value;
        const productDescription = document.getElementById("productDescription").value;
        const productPrice = document.getElementById("productPrice").value;
        const productStock = document.getElementById("productStock").value;

        // Faça algo com os dados do formulário, por exemplo, envie para o servidor
        console.log("Nome do Produto:", productName);
        console.log("Avaliação:", productRating);
        console.log("Descrição Detalhada:", productDescription);
        console.log("Preço do Produto:", productPrice);
        console.log("Quantidade em Estoque:", productStock);

        // Limpe o formulário (opcional)
        productForm.reset();
    });
});