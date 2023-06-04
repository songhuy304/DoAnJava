$(document).ready(function() {
    $('.quantity').on('change', function() {
        let productId = $(this).attr('data-id');
        let quantity = $(this).val();

        $.ajax({
            type: 'GET',
            url: '/cart/updateCart/' + productId + '/' + quantity,
            success: function(response) {
               location.href='/cart';
            },
            error: function(xhr, status, error) {
                // Xử lý lỗi (nếu có)
            }
        });
    });
    $('#capnhatgiohang').on('click',function (){
        location.reload(); // Tải lại trang
    });
    $('#checkout').on('click', function() {
        $('#thongtincanha').css("display", "block");
        $('html, body').animate({
            scrollTop: $('#thongtincanha').offset().top
        }, 500);
    });
});;