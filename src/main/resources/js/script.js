document.addEventListener('DOMContentLoaded', () => {
document.getElementById("user-form").addEventListener("submit", function(event) {
    event.preventDefault();

    const formData = new FormData(this);

            // Converte os dados do formulário para o formato x-www-form-urlencoded
            const urlEncodedData = new URLSearchParams();
            formData.forEach((value, key) => {
                urlEncodedData.append(key, value);
            });

    fetch("http://localhost:8080/api/inserir", {
        method: "POST",
        headers: {
                        'Content-Type': 'application/x-www-form-urlencoded', // Define o tipo de conteúdo
                   },
       body: urlEncodedData.toString()
    })
    .then(response => {
        if (response.ok) {
            window.location.href = "/Servico_page.html";
        } else {
            alert("Erro ao cadastrar usuário.");
        }
    });
});
});
