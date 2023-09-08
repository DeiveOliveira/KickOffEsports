let usuario = {
    nome: document.getElementById('nome').value,
    email: document.getElementById('email').value,
    cpf: document.getElementById('cpf').value,
    senha: document.getElementById('senha').value,
    role: document.getElementById('role').value
};

fetch('/cadastrar', {
    method: 'POST',
    headers: {
        'Content-Type': 'application/json',
    },
    body: JSON.stringify(usuario),
})
.then(response => response.json())
.then(data => console.log(data))
.catch((error) => console.error('Error:', error));