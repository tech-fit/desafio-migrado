$(function () {
    $.getJSON("https://localhost:5001/api/alimento", response => {
        var $table = $("table");
        response.map(item => {
            $table.append(`<tr>
                            <td>${item.nome}</td>
                            <td>${item.peso}</td>
                            <td>${item.caloria}</td>
                            <td>${item.carboidrato}</td>
                            <td>${item.proteina}</td>
                            <td>${item.gorduraTotal}</td>
                            <td>${item.gorduraSaturada}</td>
                            <td>${item.sodio}</td>
                        </tr>`);
        });
    });
});