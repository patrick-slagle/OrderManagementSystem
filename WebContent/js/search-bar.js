window.onload = function() {
	var input = $('#field');
        var total = $('#priceTotal');
	input.keydown(function(e) {

		if (e.keyCode == 13) {
			var text = input.val();
                        total = 0;
			$.get('SearchServlet', {
				query : text
			}, function(responseText) {
                                $('#orderTable tr').remove();
                                $('#total tr').remove();
                                var orders = '';
                                var orderHeader = '<tr><th>Date</th><th>Name</th><th>Price</th></tr>';
                                var totalHeader = '<tr><th>Total</th></tr>';
				$.each(responseText, function(key, value) {

					console.log(value.firstName);
					orders += '<tr><td>' + value.dueDate + '</td><td>'
							+ value.firstName + " " + value.lastName
							+ '</td><td>' + value.price + '</td></tr>';
                                        total +=  total + value.price;
				});
                                var totalRow = '<tr><td>'+total+'</td></tr>';
                                $('#orderTable').append(orderHeader + orders);
                                $('#total').append(totalHeader + totalRow);
			});
		}
		
	});

}