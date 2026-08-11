/* ==========================================================================
   Cadastro de visita integrado a API (etapa 9)
   Carrega os selects a partir do banco e envia o cadastro via POST.
   ========================================================================== */

// Preenche um <select> com dados vindos de um endpoint da API.
function carregarSelect(idSelect, endpoint, campoTexto) {
    fetch(endpoint)
        .then(function (resposta) {
            return resposta.json();
        })
        .then(function (dados) {
            const select = document.getElementById(idSelect);
            dados.content.forEach(function (item) {
                const opcao = document.createElement("option");
                opcao.value = item.id;
                opcao.textContent = item[campoTexto];
                select.appendChild(opcao);
            });
        })
        .catch(function () {
            console.error("Erro ao carregar " + endpoint);
        });
}

// Ao abrir a página, carrega os três selects com dados reais
carregarSelect("area", "/areas", "nomeArea");
carregarSelect("processo", "/processos", "nomeProcesso");
carregarSelect("usuario", "/usuarios", "nomeUsuario");

// Marca campo como inválido; retorna true se estiver OK
function validarCampo(idCampo, condicaoInvalida) {
    const campo = document.getElementById(idCampo);
    if (condicaoInvalida) {
        campo.classList.add("invalido");
        return false;
    } else {
        campo.classList.remove("invalido");
        return true;
    }
}

// Envio do formulário
document.getElementById("formVisita").addEventListener("submit", function (evento) {
    evento.preventDefault();

    const area = document.getElementById("area").value;
    const processo = document.getElementById("processo").value;
    const usuario = document.getElementById("usuario").value;
    const data = document.getElementById("data").value;

    let valido = true;
    valido = validarCampo("campoArea", area === "") ? valido : false;
    valido = validarCampo("campoProcesso", processo === "") ? valido : false;
    valido = validarCampo("campoUsuario", usuario === "") ? valido : false;
    valido = validarCampo("campoData", data === "") ? valido : false;

    if (!valido) return;

    const novaVisita = {
        dataVisita: data,
        area: { id: parseInt(area) },
        processo: { id: parseInt(processo) },
        usuario: { id: parseInt(usuario) }
    };

    fetch("/visitas", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(novaVisita)
    })
        .then(function (resposta) {
            if (!resposta.ok) {
                throw new Error("Não foi possível cadastrar a visita.");
            }
            return resposta.json();
        })
        .then(function () {
            const aviso = document.getElementById("aviso");
            aviso.textContent = "Visita cadastrada com sucesso!";
            aviso.className = "aviso sucesso";
            document.getElementById("formVisita").reset();
        })
        .catch(function (erro) {
            const aviso = document.getElementById("aviso");
            aviso.textContent = erro.message;
            aviso.className = "aviso sucesso";
            aviso.style.background = "#fdecea";
            aviso.style.color = "#c0392b";
        });
});