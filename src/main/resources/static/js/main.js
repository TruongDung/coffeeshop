$(function() {
    $(".add-to-cart-btn").click(function(e) {
        e.preventDefault();
        var self = $(this);
        $.ajax({
            url: "/cart",
            type: "POST",
            data: self.attr("productId"),
            contentType: "application/json; charset=utf-8",
            success: function() {
                //do nothing
            }
        });
    });
});