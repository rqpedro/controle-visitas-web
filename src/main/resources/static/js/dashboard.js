/* ==========================================================================
   Dashboard - listagem de visitas vinda da API (etapa 9)
   Substitui os dados simulados por chamadas reais ao back-end.
   ========================================================================== */

// Converte a data ISO (2021-01-20) para o formato brasileiro (20/01/2021)
function formatarData(dataIso) {
    if (!dataIso) return "";
    const partes = dataIso.split("-"); // [ano, mes, dia]
    return partes[2] + "/" + partes[1] + "/" + partes[0];
}

// Busca as visitas na API e monta a tabela
function carregarVisitas() {
    fetch("/visitas")
        .then(function (resposta) {
            if (!resposta.ok) {
                throw new Error("Falha ao carregar visitas.");
            }
            return resposta.json();
        })
        .then(function (dados) {
            // A API retorna paginado: a lista fica em "content"
            const visitas = dados.content;
            renderizarTabela(visitas);
        })
        .catch(function (erro) {
            const corpo = document.getElementById("corpoTabela");
            corpo.innerHTML =
                '<tr><td colspan="6" style="text-align:center;color:#c0392b;">' +
                erro.message + "</td></tr>";
        });
}

// Desenha a tabela a partir da lista de visitas
function renderizarTabela(visitas) {
    const corpo = document.getElementById("corpoTabela");
    corpo.innerHTML = "";

    if (!visitas || visitas.length === 0) {
        corpo.innerHTML =
            '<tr><td colspan="6" style="text-align:center;color:#6b7280;">Nenhuma visita cadastrada.</td></tr>';
        return;
    }

    visitas.forEach(function (v) {
        const linha = document.createElement("tr");
        linha.innerHTML =
            "<td>" + v.id + "</td>" +
            "<td>" + v.area.nomeArea + "</td>" +
            "<td>" + v.processo.nomeProcesso + "</td>" +
            "<td>" + v.usuario.nomeUsuario + "</td>" +
            "<td>" + formatarData(v.dataVisita) + "</td>" +
            '<td><button class="botao-excluir" onclick="excluirVisita(' + v.id + ')">Excluir</button></td>';
        corpo.appendChild(linha);
    });
}

// Exclui uma visita na API (DELETE) e recarrega a tabela
function excluirVisita(id) {
    const confirmar = confirm("Deseja realmente excluir esta visita?");
    if (!confirmar) return;

    fetch("/visitas/" + id, { method: "DELETE" })
        .then(function (resposta) {
            if (!resposta.ok) {
                throw new Error("Não foi possível excluir a visita.");
            }
            carregarVisitas(); // atualiza a lista após excluir
        })
        .catch(function (erro) {
            alert(erro.message);
        });
}

// Ao carregar a página, busca as visitas
carregarVisitas();