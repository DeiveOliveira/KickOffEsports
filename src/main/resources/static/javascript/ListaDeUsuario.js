document.addEventListener("DOMContentLoaded", function () {
    const userList = document.getElementById("userList");
    const users = [
            { id: 1, nome: "Usuário 1", email: "usuario1@example.com", status: "Ativo", grupo: "Grupo A", editar: "Editar" },
        { id: 2, nome: "Usuário 2", email: "usuario2@example.com", status: "Ativo", grupo: "Grupo B", editar: "Editar" },
        // Adicione mais objetos de usuário conforme necessário
    ];

    users.forEach(user => {
        const row = document.createElement("tr");
        row.innerHTML = `
            <td>${user.nome}</td>
            <td>${user.email}</td>
            <td>
                <button id="btnAtualizar_${user.id}"
                        type="button"
                        class="btn btn-success status-button"
                        data-user-id="${user.id}"
                        onclick="atualizarStatus();">
                    Ativo
                </button>
                <button id="btnInativo_${user.id}"
                        type="button"
                        class="btn btn-danger status-button"
                        data-user-id="${user.id}"
                        onclick="atualizarStatus();">
                    Inativo
                </button>
            </td>
            <td>${user.grupo}</td>
            <td>${user.editar}</td>
        `;
        userList.appendChild(row);
    });
});

