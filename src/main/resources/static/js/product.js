$(function() {
    "use strict";

    $("#deleteProductBtn").click(function(e) {
        e.preventDefault();
        $.ajax({
            url: "/product/" + $("#id").val(),
            type: "DELETE",
            success: function(result) {
                window.location.href = "/products";
            }
        });
    });
});