$(document).ready(function () {

    $("#btnSubmit").click(function (event) {
        event.preventDefault();

        fire_ajax_submit();

    });

});

function fire_ajax_submit() {

    var form = $('#fileUploadForm')[0];

    var data = new FormData(form);

    $.ajax({
        type: "POST",
        enctype: 'multipart/form-data',
        url: "http://localhost:8001/files",
        data: data,
        processData: false,
        contentType: false,
        cache: false,
        timeout: 600000,
        success: function (data) {

            console.log("SUCCESS : ", data);

        },
        error: function (e) {

            $("#result").text(e.responseText);
            console.log("ERROR : ", e);
        }
    });

}