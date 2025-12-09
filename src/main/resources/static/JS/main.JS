// Função para confirmar exclusão
function confirmarExclusao(event) {
    if (!confirm("Tem certeza que deseja banir este jogador do sistema?")) {
        event.preventDefault();
    }
}

// Função AJAX para atualizar placar sem recarregar a tela
function atualizarScore(event, url, spanId, operacao) {
    event.preventDefault();
    fetch(url, { method: 'POST' }).then(response => {
        if (response.ok) {
            const spanElement = document.getElementById(spanId);
            let valorAtual = parseInt(spanElement.innerText);
            if (operacao === 'somar') spanElement.innerText = valorAtual + 1;
            else if (operacao === 'subtrair' && valorAtual > 0) spanElement.innerText = valorAtual - 1;

            // Efeito visual
            spanElement.style.color = operacao === 'somar' ? '#66fcf1' : '#ff4444';
            setTimeout(() => { spanElement.style.color = ''; }, 500);
        }
    });
}

// Função para preencher o Modal de Edição (CORRIGIDA)
function prepararEdicao(botao) {
    var id = botao.getAttribute('data-id');
    var nick = botao.getAttribute('data-nick');
    var nome = botao.getAttribute('data-nome');
    var email = botao.getAttribute('data-email');

    document.getElementById('editId').value = id;
    document.getElementById('editNick').value = nick;
    document.getElementById('editNome').value = nome;
    document.getElementById('editEmail').value = email;
}