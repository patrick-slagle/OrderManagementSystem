

window.addEventListener('load', function () {
    var modal = $('#modal');
    $('#orderTable').find('tr').click(function () {
        var rowIndex = $(this).index();
        var orderData = $(this).text();
        console.log(firstname);
        $.post('get-order-data.do', {
            data: orderData
        }, function () {
            $('.modalContent').load('modal-form.jsp');
            modal[0].style.display = 'block';
        });
    });
});

window.onclick = function (event) {
    if (event.target == modal) {
        modal.style.display = 'none';
    }
};