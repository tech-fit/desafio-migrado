$("button").on("click", () => {
    $.ajax({
        url: "https://localhost:5001/api/alimento",
        type: "POST",
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify({
            "Nome": $("form").find("[name='Nome']").val(),
            "Peso": $("form").find("[name='Peso']").val(),
            "Caloria": $("form").find("[name='Caloria']").val(),
            "Carboidrato": $("form").find("[name='Carboidrato']").val(),
            "Proteina": $("form").find("[name='Proteina']").val(),
            "GorduraTotal": $("form").find("[name='GorduraTotal']").val(),
            "GorduraSaturada": $("form").find("[name='GorduraSaturada']").val(),
            "FibraAlimentar": $("form").find("[name='FibraAlimentar']").val(),
            "Sodio": $("form").find("[name='Sodio']").val()
        }),
        success: response => {

            alert(response.message);
        }
    });
})
