<div class="modalContainer" id="modal">
<div class="modalContent">

<table>

<tr>
<th>Name</th>
<th>Phone</th>
<th>Email</th>
<th>Due Date</th>
<th>Product</th>
<th>Price</th>
<th>Comments</th>
</tr>

<c:forEach var="orders" items="${ orders }">
<tr>
<td>${ orders.firstName } ${ orders.lastName }</td>
<td>${ orders.phone }</td>
<td>${ orders.email }</td>
<td>${ orders.dueDate }</td>
<td>${ orders.product }</td>
<td>${ orders.price }</td>
<td>${ orders.comments }</td>
</tr>
</c:forEach>

</table>
</div>
</div>