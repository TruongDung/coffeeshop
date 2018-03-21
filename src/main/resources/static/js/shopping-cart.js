$(function() {
    $(".btn-refresh-shopping-cart").click(function(e) {
        e.preventDefault();

        var self = $(this);
        var productId = self.attr("productId");
        var quantity = self.closest("tr").find(".quantity").val();
        var obj = {
            quantity: quantity,
            productId: productId
        };

        $.ajax({
            type: "POST",
            data: JSON.stringify(obj),
            url: "/shopping-cart",
            contentType: "application/json; charset=utf-8",
            success: function() {
                location.reload();
            }
        });
    });

    $(".btn-remove-shopping-cart").click(function(e) {
        e.preventDefault();

        var self = $(this);
        var productId = self.attr("productId");

        $.ajax({
            type: "DELETE",
            url: "/shopping-cart/" + productId,
            contentType: "application/json; charset=utf-8",
            success: function() {
                location.reload();
            }
        });
    });

    $("#placeOrder-btn").click(function(e) {
        e.preventDefault();

        $.ajax({
            type: "POST",
            url: "/placeOrder",
            contentType: "application/json; charset=utf-8",
            success: function() {
                // window.location.href = "/";
            }
        });
    });
});