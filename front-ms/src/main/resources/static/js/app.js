document.querySelectorAll("[data-edit-author]").forEach((button) => {
    button.addEventListener("click", () => {
        const form = document.getElementById("editar-autor-form");
        form.elements.id.value = button.dataset.id;
        form.elements.nome.value = button.dataset.nome;
        form.elements.nacionalidade.value = button.dataset.nacionalidade;
        form.elements.anoNascimento.value = button.dataset.ano;
    });
});

document.querySelectorAll("[data-edit-book]").forEach((button) => {
    button.addEventListener("click", () => {
        const form = document.getElementById("editar-livro-form");
        form.elements.id.value = button.dataset.id;
        form.elements.titulo.value = button.dataset.titulo;
        form.elements.genero.value = button.dataset.genero;
        form.elements.anoPublicacao.value = button.dataset.ano;
        form.elements.autorId.value = button.dataset.autorId;
        form.elements.disponivel.checked = button.dataset.disponivel === "true";
    });
});

document.querySelectorAll("[data-dialog-open]").forEach((button) => {
    button.addEventListener("click", () => {
        const dialog = document.getElementById(button.dataset.dialogOpen);
        dialog?.showModal();
    });
});

document.querySelectorAll("[data-confirm]").forEach((form) => {
    form.addEventListener("submit", (event) => {
        if (!window.confirm(form.dataset.confirm)) {
            event.preventDefault();
        }
    });
});

document.querySelectorAll("[data-dialog-close]").forEach((button) => {
    button.addEventListener("click", () => {
        button.closest("dialog")?.close();
    });
});

document.querySelectorAll("dialog").forEach((dialog) => {
    dialog.addEventListener("click", (event) => {
        if (event.target === dialog) {
            dialog.close();
        }
    });
});
