//
//     $(document).ready(function() {
//     $('#addtocart').click(function(event) {
//         event.preventDefault(); // Ngăn chặn hành vi mặc định của nút submit
//
//         // Lấy dữ liệu từ form
//         const id = $('input[name="id"]').val();
//         const name = $('input[name="name"]').val();
//         const price = $('input[name="price"]').val();
//
//
//         // Tạo đối tượng dữ liệu để gửi lên máy chủ
//         const data = {
//             id: id,
//             name: name,
//             price: price
//
//         };
//
//         // Gửi yêu cầu AJAX
//         $.ajax({
//             url: '/products/add-to-cart',
//             type: 'POST',
//             data: data,
//             success: function(response) {
//
//             },
//             error: function(xhr, status, error) {
//               alert("Thêm Giỏ Hàng Thành Công")
//                 console.error(error);
//             }
//         });
//     });
// });
//
