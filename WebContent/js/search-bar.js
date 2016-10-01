window.onload = function() {
	var input = $('#field');
	
	input.keydown(function(e) {
		if(e.keyCode == 13) {
			var text = input.val();
			$.get('SearchServlet', {query:text}, function(responseText) {
				console.log(responseText);
			});
		}
	});
	
}