document.addEventListener("DOMContentLoaded", function () {
    const userList = document.getElementById("userList");
    const users = [
      { nome: "Usuário 1", email: "usuario1@example.com",status: "Ativo" ,grupo: "Grupo A", editar:"Editar" },
      { nome: "Usuário 2", email: "usuario2@example.com",status: "Ativo" ,grupo: "Grupo B", editar:"Editar" },
      // Adicione mais objetos de usuário conforme necessário
    ];
    users.forEach(user => {
      const row = document.createElement("tr");
      row.innerHTML = `
        <td>${user.nome}</td>
        <td>${user.email}</td>
        <td><div class="switch__container">
              <input id="switch-shadow" class="switch switch--shadow" type="checkbox">
              <label for="switch-shadow"></label>
            </div>
        </td>
        <td>${user.grupo}</td>
        <td>${user.editar}</td>
      `;
      userList.appendChild(row);
    });
  });


