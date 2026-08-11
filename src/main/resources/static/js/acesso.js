// Marca ou desmarca um campo como inválido
function marcarErro(idCampo, invalido) {
    const campo = document.getElementById(idCampo);
    if (invalido) {
        campo.classList.add("invalido");
    } else {
        campo.classList.remove("invalido");
    }
}

// Verifica se um texto está vazio (considerando espaços)
function vazio(valor) {
    return valor.trim() === "";
}

// Validação simples de e-mail
function emailValido(valor) {
    const padrao = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return padrao.test(valor.trim());
}

/* ---------- Cadastro ---------- */
document.getElementById("formCadastro").addEventListener("submit", function (evento) {
    evento.preventDefault(); // impede o envio real (não há back-end nesta etapa)

    const nome = document.getElementById("nome").value;
    const email = document.getElementById("email").value;
    const login = document.getElementById("loginCad").value;
    const senha = document.getElementById("senhaCad").value;

    let valido = true;

    // Nome obrigatório
    const nomeInvalido = vazio(nome);
    marcarErro("campoNome", nomeInvalido);
    if (nomeInvalido) valido = false;

    // E-mail válido
    const emailInvalido = !emailValido(email);
    marcarErro("campoEmail", emailInvalido);
    if (emailInvalido) valido = false;

    // Login obrigatório
    const loginInvalido = vazio(login);
    marcarErro("campoLogin", loginInvalido);
    if (loginInvalido) valido = false;

    // Senha com no mínimo 3 caracteres
    const senhaInvalida = senha.trim().length < 3;
    marcarErro("campoSenha", senhaInvalida);
    if (senhaInvalida) valido = false;

    if (valido) {
        const aviso = document.getElementById("avisoCadastro");
        aviso.textContent = "Cadastro realizado com sucesso!";
        aviso.className = "aviso sucesso";
        this.reset();
    }
});

/* ---------- Login ---------- */
document.getElementById("formLogin").addEventListener("submit", function (evento) {
    evento.preventDefault();

    const login = document.getElementById("login").value;
    const senha = document.getElementById("senha").value;

    let valido = true;

    const loginInvalido = vazio(login);
    marcarErro("campoLoginAcesso", loginInvalido);
    if (loginInvalido) valido = false;

    const senhaInvalida = vazio(senha);
    marcarErro("campoSenhaAcesso", senhaInvalida);
    if (senhaInvalida) valido = false;

    if (valido) {
        // Sem back-end nesta etapa: apenas redireciona para o painel
        window.location.href = "dashboard.html";
    }
});
