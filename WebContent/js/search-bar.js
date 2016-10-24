window.addEventListener("load", function () {
    var input = $('#field');
    var total = $('#priceTotal');
    input.keydown(function (e) {
        if (e.keyCode === 13) {
            var text = input.val();
            total = 0;
            $.get('search-orders.do', {
                query: text
            }, function (responseText) {
                $('#orderTable tr').remove();
                $('#total tr').remove();
                var orders = '';
                var orderHeader = '<tr><th>Date</th><th>Name</th><th>Price</th></tr>';
                var totalHeader = '<tr><th>Total</th></tr>';
                $.each(responseText, function (key, value) {

                    orders += '<tr><td>' + value.dueDate + '</td><td>'
                            + value.firstName + " " + value.lastName
                            + '</td><td>' + value.price + '</td></tr>';
                    total += total + value.price;
                });
                var totalRow = '<tr><td>' + total + '</td></tr>';
                $('#orderTable').append(orderHeader + orders);
                $('#total').append(totalHeader + totalRow);
            });
        }
    });
    var modal = $('#modal');
    $('#orderTable').find('tr').click(function () {
        var rowIndex = $(this).index();
        $.post('get-order-data.do', {
            index: rowIndex
        }, function () {
            $('.modalContent').load('modal-form.jsp');
            modal[0].style.display = 'block';
        });
    });

    window.onclick = function (event) {
        if (event.target === modal) {
            modal.style.display = 'none';
        }
    };

});




