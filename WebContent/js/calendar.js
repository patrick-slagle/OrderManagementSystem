/**
 * 
 */

window.onload = function() {
	$.get('OrdersToJSONServlet', function(responseText) {
		console.log(responseText);
	});
};