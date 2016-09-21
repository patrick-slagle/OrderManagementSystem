	<form class="orderForm">
				<input name="first-name" class="topBotInput" type="text"
					value="${ selectedOrder.firstName }" readonly />
				<input name="last-name" class="topBotInput" id="lastName"
					type="text" value="${ selectedOrder.lastName }" readonly />
				<input name="phone" class="topBotInput" id="phoneNumber" type="text"
					value="${ selectedOrder.phoneNumber }" readonly />
				<br>
				<input name="email" class="twoCenterInput" type="text"
					value="${ selectedOrder.email }" readonly />
				<input name="due-date" class="twoCenterInput" id="dueDate"
					value="${ selectedOrder.dueDate }" readonly />
				<br>
				<br>
				<input name="product" class="centerInput" type="text"
					value="${ selectedOrder.product }" readonly />
				<br>
				<input name="price" class="centerInput" type="text"
					value="${ selectedOrder.price }" readonly />
				<br>
				<textarea name="comments" class="centerInput" id="comments"
				 readonly>${ selectedOrder.comments }</textarea>
				<br>
			</form>