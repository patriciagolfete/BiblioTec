const API = "http://localhost:8082/emprestimos";

window.onload = listarEmprestimos;

function limparErros() {
    document.getElementById("erroUsuario").innerText = "";
    document.getElementById("erroLivro").innerText = "";
}

function limparMensagem() {
    document.getElementById("mensagem").innerText = "";
}

function mostrarMensagem(texto, sucesso = true) {
    const mensagem = document.getElementById("mensagem");

    mensagem.innerText = texto;
    mensagem.style.color = sucesso ? "green" : "red";
    mensagem.style.fontWeight = "bold";
}

function realizarEmprestimo() {

    const usuarioId = document.getElementById("usuarioId").value;
    const livroId = document.getElementById("livroId").value;

    limparErros();
    limparMensagem();
    
    let possuiErro = false;

    if (!usuarioId) {
        document.getElementById("erroUsuario").innerText =
            "ID do usuário é obrigatório.";
        possuiErro = true;
    }
    else if (Number(usuarioId) < 1) {
        document.getElementById("erroUsuario").innerText =
            "O ID do usuário deve ser maior que 0.";
        possuiErro = true;
    }

    if (!livroId) {
        document.getElementById("erroLivro").innerText =
            "ID do livro é obrigatório.";
        possuiErro = true;
    }
    else if (Number(livroId) < 1) {
        document.getElementById("erroLivro").innerText =
            "O ID do livro deve ser maior que 0.";
        possuiErro = true;
    }

    if (possuiErro) {
        return;
    }

    const hoje = new Date().toISOString().split("T")[0];

    fetch(API, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            dataEmprestimo: hoje,
            usuario: {
                id: Number(usuarioId)
            },
            livro: {
                id: Number(livroId)
            }
        })
    })
    .then(response => {

        if (response.ok) {
            return response.json();
        }

        return response.text().then(mensagem => {

            if (mensagem.includes("Usuário não encontrado")) {
                document.getElementById("erroUsuario").innerText =
                    "Usuário não encontrado.";
            }
            else if (mensagem.includes("Livro não encontrado")) {
                document.getElementById("erroLivro").innerText =
                    "Livro não encontrado.";
            }
            else if (mensagem.includes("Livro indisponível")) {
                document.getElementById("erroLivro").innerText =
                    "Livro indisponível! Não é possível realizar o empréstimo.";
            }
            else if (mensagem.includes("3 empréstimos")) {
                document.getElementById("erroUsuario").innerText =
                    "Usuário já possui 3 empréstimos em aberto.";
            }
            else {
                mostrarMensagem("Erro ao realizar empréstimo.", false);
            }

            throw new Error(mensagem);
        });
    })
    .then(() => {
        document.getElementById("usuarioId").value = "";
        document.getElementById("livroId").value = "";

        listarEmprestimosSemLimparMensagem();

        mostrarMensagem("Empréstimo realizado com sucesso!", true);
    })
    .catch(() => {});
}

function listarEmprestimos() {

    limparMensagem();

    fetch(API + "/relatorio/livros-emprestados")
        .then(response => response.json())
        .then(emprestimos => montarTabela(emprestimos));
}

function listarEmprestimosSemLimparMensagem() {

    fetch(API + "/relatorio/livros-emprestados")
        .then(response => response.json())
        .then(emprestimos => montarTabela(emprestimos));
}

function devolverLivro(id) {

    if (!confirm("Deseja devolver este livro?")) {
        return;
    }

    limparMensagem();

    fetch(API + "/" + id + "/devolver", {
        method: "PUT"
    })
    .then(response => {
        if (!response.ok) {
            throw new Error("Erro ao devolver livro.");
        }

        return response.json();
    })
    .then(() => {
        mostrarMensagem("Livro devolvido com sucesso!", true);
        listarEmprestimosSemLimparMensagem();
    })
    .catch(() => {
        mostrarMensagem("Erro ao devolver livro.", false);
    });
}

function montarTabela(emprestimos) {

    const tabela = document.getElementById("tabelaEmprestimos");

    tabela.innerHTML = "";

    emprestimos.forEach(emprestimo => {

        tabela.innerHTML += `
            <tr>
                <td>${emprestimo.id}</td>
                <td>${emprestimo.usuario?.nome ?? ''}</td>
                <td>${emprestimo.livro?.titulo ?? ''}</td>
                <td>${emprestimo.dataEmprestimo ?? ''}</td>
                <td class="acoes">
                    <button onclick="devolverLivro(${emprestimo.id})">
                        Devolver
                    </button>
                </td>
            </tr>
        `;
    });
}


