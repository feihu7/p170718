window.hrefOverride = "http://localhost:10718";

$(document).ready(function() {

    $.ajax("../hello")
        .done(function(data) {
            $("#abc").text(data
                          );
        });
    // fullScreen();
    
});