$(document).ready(function() {
    
    $("#view-nav > a").click(function(e) {
        var k = $(this).attr('href');
        app.view = k;
        return false;
    });
    
    if ($.fn.inlineEdit)
        $(".editable").inlineEdit();
    
});

function fullScreen() {
    var hideTop = function() {
        $("#proj-info").slideUp({
            done: function() {
                $("#top").addClass("hide");
            }
        });
        $("#footbar").fadeOut();
    };

    var hTimeout;
    hTimeout = setTimeout(hideTop, 3000);
    
    $("#top").hover(function(e) {
        clearTimeout(hTimeout);
        hTimeout = null;
        
        $("#top").removeClass("hide");
        $("#proj-info").fadeIn();
        $("#footbar").fadeIn();
    }, function() {
        // TODO kill short-delay opener
        
        // enable auto-hide
        hTimeout = setTimeout(hideTop, 2000);
    });
}