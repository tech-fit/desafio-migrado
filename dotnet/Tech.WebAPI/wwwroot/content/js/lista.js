$(function () {
    $.getJSON("https://localhost:5001/api/alimentos", response => {
        var $table = $("table");
        response.map(item => {
            $table.append(`<tr>
                            <td>${item.nome}</td>
                            <td>${DefaultValue(item.peso)}</td>
                            <td>${DefaultValue(item.caloria)}</td>
                            <td>${DefaultValue(item.carboidrato)}</td>
                            <td>${DefaultValue(item.proteina)}</td>
                            <td>${DefaultValue(item.gorduraTotal)}</td>
                            <td>${DefaultValue(item.gorduraSaturada)}</td>
                            <td>${DefaultValue(item.sodio)}</td>
                            <td>${DefaultValue(item.tags)}</td>
                        </tr>`);

            function DefaultValue(value) {
                if (value)
                    return value;
                return "";
            }
        });
    });
});