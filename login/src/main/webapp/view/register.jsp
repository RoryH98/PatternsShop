<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!doctype html>
<head>
<link rel="stylesheet" href="http://127.0.0.1:127/css/style.css">
<link href="http://127.0.0.1:127/css/bootstrap.min.css" rel="stylesheet"
	id="bootstrap-css">
<script src="http://127.0.0.1:127/js/bootstrap.min.js"></script>
<script src="http://127.0.0.1:127/js/jquery.min.js"></script>
<title>Welcome to FairTicket</title>
</head>
<body>

	<!------ Include the above in your HEAD tag ---------->
	<div id="login-overlay" class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">×</span><span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">Register to
					TicketFare</h4>
			</div>
			<div class="modal-body">
				<div class="row">
					<div class="col-xs-6">
						<div class="well">
							<form method="POST" action="/register"
								enctype="multipart/form-data">
								<div class="form-group">
									<label for="name" class="control-label">Name</label> <input
										type="text" name="name" id="name" class="form-control"
										placeholder="Name" required autofocus value="" required=""
										title="Please enter you name"> <span
										class="help-block"></span>
								</div>
								<div class="form-group">
									<label for="email" class="control-label">Email</label> <input
										type="email" class="form-control" id="email" name="email"
										placeholder="Email" value="" required=""
										title="Please enter your email"> <span
										class="help-block"></span>
								</div>
								<div class="form-group">
									<label for="Shipping_Address" class="control-label">Shipping
										Address</label> <input type="Shipping_Address" class="form-control"
										id="Shipping_Address" name="Shipping_Address"
										placeholder="Shipping Address" value="" required=""
										title="Please enter your shipping address"> <span
										class="help-block"></span>
								</div>
								<div class="form-group">
									<label for="First_Name" class="control-label">First Name</label>
									<input type="First_Name" class="form-control"
										id="fName" name="fname"
										placeholder="Enter First Name" value="" required=""
										title="Please enter your frist name"> <span
										class="help-block"></span>
									<label for="Second_Name" class="control-label">Second Name</label>
									<input type="Second_Name" class="form-control"
										id="lName" name="lname"
										placeholder="Enter Second Name" value="" required=""
										title="Please enter your Second name"> <span
										class="help-block"></span>
									<label for="Address" class="control-label">Address</label>
									<input type="Address" class="form-control"
										id="Address" name="address"
										placeholder="Enter Address" value="" required=""
										title="Please enter your Address"> <span
										class="help-block"></span>
									<label for="Town" class="control-label">Town</label>
									<input type="town" class="form-control"
										id="town" name="town"
										placeholder="Enter Town" value="" required=""
										title="Please enter your Town"> <span
										class="help-block"></span>
									<label for="County" class="control-label">County</label>
									<input type="county" class="form-control"
										id="county" name="county"
										placeholder="Enter County" value="" required=""
										title="Please enter your County"> <span
										class="help-block"></span>
									<label for="Card Number" class="control-label">Card Number</label>
									<input type="text" class="form-control"
										id="number" name="number"
										placeholder="Enter Card Number" value="" required=""
										title="Please enter your card number"> <span
										class="help-block"></span>
									<label for="card_type" class="control-label">Card Type
									</label> <br><select id="choice" name="choice">
										<option  value="1">Visa</option>
										<option value="2">MasterCard</option>
										<option value="3">AmericanExpress</option>
									</select>
									<span
										class="help-block"></span>
									<label for="Expiry_month" class="control-label">Expiry month</label>
									<input type="number" class="form-control"
										id="date" name="date"
										placeholder="Enter month" value="" required=""
										title="Please enter your exipiry month"> <span
										class="help-block"></span>
										<label for="Expiry_year" class="control-label">Expiry Year</label>
									<input type="number" class="form-control"
										id="year" name="year"
										placeholder="Enter month" value="" required=""
										title="Please enter your exipiry month"> <span
										class="help-block"></span>
										
										
								</div>


								<div class="form-group">
									<label for="inputPassword" class="control-label">Password</label>
									<input type="password" class="form-control" id="password"
										name="password" placeholder="Password" value="" required=""
										title="Please enter your password"> <span
										class="help-block"></span>
								</div>
								<div id="loginErrorMsg" class="alert alert-error hide">Wrong
									inputEmail or inputPassword</div>
								<button type="submit" class="btn btn-success btn-block">Register</button>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	</div>
</body>
</html>
