
var isAfterSearch = false;

//The modal window function
function openModal() {

    var span = document.getElementsByClassName("closeModal")[0];
    var modal = $('#modal');
    
    $('#orderTable').find('tr').click(function () {
        var rowIndex = this.rowIndex;
        if (isAfterSearch === false) {
            $.post('get-order-data.do', {
                index: rowIndex,
                hasBeenSearched: isAfterSearch
            }, function () {
                $('.modalContent').load('modal-form.jsp');
                modal[0].style.display = 'block';
            });
        } else if (isAfterSearch === true) {
            $.post('get-order-data.do', {
                index: rowIndex,
                hasBeenSearched: isAfterSearch
            }, function () {
                $('.modalContent').load('modal-form.jsp');
                modal[0].style.display = 'block';
            });
            isAfterSearch = false;
        }
    });

    span.onclick = function () {
        modal[0].style.display = "none";
    };
}
//on page load, add listener to table for modal window
window.addEventListener("load", function () {

    openModal();

    //perform order search, add listener for modal again when finished 
    var input = $('#field');
    var total = $('#priceTotal');
    input.keydown(function (e) {
        search();

        function search() {
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

                    isAfterSearch = true;
                    openModal();
                });
            }
        }
    });
});

