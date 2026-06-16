const API = "http://localhost:8082/livros";

window.onload = listarLivros;

let livrosCarregados = [];

function limparErros() {
    document.getElementById("erroTitulo").innerText = "";
    document.getElementById("erroAutor").innerText = "";
    document.getElementById("erroEditora").innerText = "";
}

function mostrarMensagem(texto, sucesso = true) {
    const mensagem = document.getElementById("mensagem");

    mensagem.innerText = texto;
    mensagem.style.color = sucesso ? "green" : "red";
    mensagem.style.fontWeight = "bold";
}

function validarCampos() {
    limparErros();

    const titulo = document.getElementById("titulo").value.trim();
    const autor = document.getElementById("autor").value.trim();
    const editora = document.getElementById("editora").value.trim();

    let possuiErro = false;

    if (!titulo) {
        document.getElementById("erroTitulo").innerText =
            "Título é obrigatório.";
        possuiErro = true;
    }

    if (!autor) {
        document.getElementById("erroAutor").innerText =
            "Autor é obrigatório.";
        possuiErro = true;
    }

    if (!editora) {
        document.getElementById("erroEditora").innerText =
            "Editora é obrigatória.";
        possuiErro = true;
    }

    return !possuiErro;
}

function cadastrarLivro() {
    mostrarMensagem("");

    if (!validarCampos()) {
        return;
    }

    const titulo = document.getElementById("titulo").value.trim();
    const autor = document.getElementById("autor").value.trim();
    const editora = document.getElementById("editora").value.trim();

    fetch(API, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            titulo: titulo,
            autor: autor,
            editora: editora,
            disponivel: true
        })
    })
    .then(response => {
        if (!response.ok) {
            throw new Error("Erro ao cadastrar livro.");
        }

        return response.json();
    })
    .then(() => {
        limparFormulario();
        mostrarMensagem("Livro cadastrado com sucesso!", true);
        listarLivros();
    })
    .catch(() => {
        mostrarMensagem("Erro ao cadastrar livro.", false);
    });
}

function prepararEdicaoPorId(id) {
    const livro = livrosCarregados.find(l => l.id === id);

    if (!livro) {
        mostrarMensagem("Livro não encontrado para edição.", false);
        return;
    }

    document.getElementById("idLivro").value = livro.id;
    document.getElementById("titulo").value = livro.titulo;
    document.getElementById("autor").value = livro.autor;
    document.getElementById("editora").value = livro.editora;
    document.getElementById("disponivelLivro").value = livro.disponivel;

    document.getElementById("btnCadastrar").style.display = "none";
    document.getElementById("btnAlterar").style.display = "inline-block";

    limparErros();
    mostrarMensagem("");
}

function alterarLivro() {
    mostrarMensagem("");

    if (!validarCampos()) {
        return;
    }

    const id = document.getElementById("idLivro").value;
    const titulo = document.getElementById("titulo").value.trim();
    const autor = document.getElementById("autor").value.trim();
    const editora = document.getElementById("editora").value.trim();
    const disponivel =
        document.getElementById("disponivelLivro").value === "true";

    fetch(API + "/" + id, {
        method: "PUT",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            titulo: titulo,
            autor: autor,
            editora: editora,
            disponivel: disponivel
        })
    })
    .then(response => {
        if (!response.ok) {
            throw new Error("Erro ao alterar livro.");
        }

        return response.json();
    })
    .then(() => {
        limparFormulario();
        mostrarMensagem("Livro alterado com sucesso!", true);
        listarLivros();
    })
    .catch(() => {
        mostrarMensagem("Erro ao alterar livro.", false);
    });
}

function excluirLivro(id) {
    if (!confirm("Deseja excluir este livro?")) {
        return;
    }

    fetch(API + "/" + id, {
        method: "DELETE"
    })
    .then(response => {
        if (!response.ok) {
            throw new Error("Erro ao excluir livro.");
        }
    })
    .then(() => {
        limparFormulario();
        mostrarMensagem("Livro excluído com sucesso!", true);
        listarLivros();
    })
    .catch(() => {
        mostrarMensagem("Erro ao excluir livro.", false);
    });
}

function buscarLivro() {
    const texto = document.getElementById("buscaLivro").value.trim();
    const tipo = document.getElementById("tipoBusca").value;

    mostrarMensagem("");

    if (!texto) {
        listarLivros();
        return;
    }

    fetch(API + "/buscar/" + tipo + "?" + tipo + "=" + encodeURIComponent(texto))
        .then(response => {
            if (!response.ok) {
                let mensagem = "";

                if (tipo === "titulo") {
                    mensagem = "Livro não encontrado.";
                } else if (tipo === "autor") {
                    mensagem = "Autor não encontrado.";
                } else if (tipo === "editora") {
                    mensagem = "Editora não encontrada.";
                }

                montarMensagemNaoEncontrado(mensagem);
                return;
            }

            return response.json();
        })
        .then(livros => {
            if (livros) {
                montarTabela(livros);
            }
        });
}

function listarLivros() {
    fetch(API)
        .then(response => response.json())
        .then(dados => {
            dados.sort((a, b) =>
                a.titulo.localeCompare(b.titulo, "pt-BR")
            );

            montarTabela(dados);
        });
}

function listarDisponiveis() {
    fetch(API + "/disponiveis")
        .then(response => response.json())
        .then(dados => {
            dados.sort((a, b) =>
                a.titulo.localeCompare(b.titulo, "pt-BR")
            );

            montarTabela(dados);
        });
}

function listarEmprestados() {
    fetch(API + "/emprestados")
        .then(response => response.json())
        .then(dados => {
            dados.sort((a, b) =>
                a.titulo.localeCompare(b.titulo, "pt-BR")
            );

            montarTabela(dados);
        });
}

function montarTabela(livros) {
    const tabela = document.getElementById("tabelaLivros");

    livrosCarregados = livros;
    tabela.innerHTML = "";

    livros.forEach(livro => {
        tabela.innerHTML += `
            <tr>
                <td>${livro.id}</td>
                <td>${livro.titulo}</td>
                <td>${livro.autor}</td>
                <td>${livro.editora}</td>
                <td>${livro.disponivel ? "Sim" : "Não"}</td>
                <td>
                    <button onclick="prepararEdicaoPorId(${livro.id})">
                        Editar
                    </button>

                    <button onclick="excluirLivro(${livro.id})">
                        Excluir
                    </button>
                </td>
            </tr>
        `;
    });
}

function montarMensagemNaoEncontrado(mensagem) {
    const tabela = document.getElementById("tabelaLivros");

    tabela.innerHTML = `
        <tr>
            <td colspan="6" style="color: red; font-weight: bold;">
                ${mensagem}
            </td>
        </tr>
    `;
}

function limparFormulario() {
    document.getElementById("idLivro").value = "";
    document.getElementById("titulo").value = "";
    document.getElementById("autor").value = "";
    document.getElementById("editora").value = "";
    document.getElementById("disponivelLivro").value = "";

    document.getElementById("btnCadastrar").style.display = "inline-block";
    document.getElementById("btnAlterar").style.display = "none";

    limparErros();
}


