function fazerLogin() {

    const login = document.getElementById("login").value;
    const senha = document.getElementById("senha").value;
    const mensagem = document.getElementById("mensagem");

    mensagem.innerText = "";

    fetch("http://localhost:8082/administradores/login", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            login: login,
            senha: senha
        })
    })
    .then(response => response.text())
    .then(texto => {

        if (!texto) {
            mensagem.innerText = "Usuário ou senha incorretos!";
            return;
        }

        const data = JSON.parse(texto);

        if (data && data.id) {
            window.location.href = "/dashboard.html";
        } else {
            mensagem.innerText = "Usuário ou senha incorretos!";
        }
    })
    .catch(error => {
        mensagem.innerText = "Erro ao tentar realizar login.";
    });
}