	window.addEventListener('load', function() {
		console.log('hi');
		var modal = $('#modal');
		console.log($('#orderTable'));
		$('#orderTable').find('tr').click(function() {
			var rowIndex = $(this).index();
			$.post('GetSingleOrder', {
				index : rowIndex
			}, function() {
				$('.modalContent').load('modal-form.jsp');
				modal[0].style.display = 'block';
			}); 
		});
	});

	window.onclick = function(event) {
		if (event.target == modal) {
			modal.style.display = 'none';
		}
	}