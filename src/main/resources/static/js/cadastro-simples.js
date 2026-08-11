/* ==========================================================================
   Cadastro simples (área e processo) integrado à API (etapa 9)
   Envia o cadastro via POST usando as informações do CONFIG_FORM da página.
   ========================================================================== */

const formulario = document.getElementById(CONFIG_FORM.idForm);

formulario.addEventListener("submit", function (evento) {
    evento.preventDefault();

    const campo = document.getElementById(CONFIG_FORM.idCampo);
    const valor = document.getElementById(CONFIG_FORM.idInput).value;
    const aviso = document.getElementById("aviso");

    // Campo obrigatório
    if (valor.trim() === "") {
        campo.classList.add("invalido");
        return;
    }
    campo.classList.remove("invalido");

    // Monta o corpo dinamicamente: { "nomeArea": valor } ou { "nomeProcesso": valor }
    const corpo = {};
    corpo[CONFIG_FORM.campoApi] = valor.trim();

    fetch(CONFIG_FORM.endpoint, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(corpo)
    })
        .then(function (resposta) {
            if (!resposta.ok) {
                throw new Error("Não foi possível cadastrar. Verifique os dados.");
            }
            return resposta.json();
        })
        .then(function () {
            aviso.textContent = CONFIG_FORM.mensagemSucesso;
            aviso.className = "aviso sucesso";
            formulario.reset();
        })
        .catch(function (erro) {
            aviso.textContent = erro.message;
            aviso.className = "aviso sucesso";
            aviso.style.background = "#fdecea";
            aviso.style.color = "#c0392b";
        });
});