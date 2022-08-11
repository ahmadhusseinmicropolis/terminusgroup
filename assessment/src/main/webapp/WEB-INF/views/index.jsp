<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>


<html>
<head>

<meta charset="ISO-8859-1">
<title>Terminus Group</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.8.2/angular.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.8.2/angular-route.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="../js/app.js"></script>
<script src="../js/services/user.service.js"></script>
<script src="../js/controllers/user.controller.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css"
	rel="stylesheet">

</head>
<body>

	<div ng-app="my-app" ng-controller="userController as vm">

		<div class="row" style="margin-bottom: 20px; margin-top: 20px">
			<div class="col-md-2"></div>
			<div class="col-md-3">
				<h2>Users</h2>
			</div>
			<div class="col-md-4"></div>
			<div class="col-md-3" style="text-align: left; margin-top: 10px">
				<button type="button" class="btn btn-primary"
					data-target="#userModal" ng-click="vm.showModal()">New
					User</button>
			</div>

		</div>
		<div class="row">
			<div class="col-md-2"></div>
			<div class="col-md-8 text-center">

				<div ng-show="vm.isBusy" class="text-center">Loading...</div>
				<table class="table" style="text-align: left">
					<thead>
						<tr>
							<th scope="col">First Name</th>
							<th scope="col">Last Name</th>
							<th scope="col">Occupation</th>
							<th scope="col">Age</th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<tr ng-repeat="user in vm.users">
							<td ng-click="vm.showEditModal(user)">{{user.firstName}}</td>
							<td ng-click="vm.showEditModal(user)">{{user.lastName}}</td>
							<td ng-click="vm.showEditModal(user)">{{user.occupation}}</td>
							<td ng-click="vm.showEditModal(user)">{{user.age}}</td>
							<td><i class="fa fa-trash-o"
								ng-click="vm.deleteUser(user.id)"></i></td>
						</tr>

					</tbody>
				</table>



				<!-- Modal -->
				<div class="modal fade" id="userModal" tabindex="-1" role="dialog"
					aria-labelledby="userModalLabel" aria-hidden="true">
					<div class="modal-dialog" role="document">
						<div class="modal-content">
							<form novalidate name="newUser" ng-submit="vm.saveUser()">
								<div class="modal-header">
									<h5 class="modal-title" id="userModalLabel">New User</h5>

								</div>
								<div class="modal-body">

									<div class="row"
										style="margin-bottom: 10px; padding-left: 10px; padding-right: 10px">
										<label for="name" class="col-md-3">First Name</label> <input
											class="col-md-7" type="text" id="number" name="number"
											ng-model="vm.newUser.firstName" required />
									</div>
									<div class="form-group row"
										style="padding-left: 10px; padding-right: 10px">
										<label for="name" class="col-md-3">Last Name</label> <input
											class=" col-md-7" type="text" id="firstName" name="firstName"
											ng-model="vm.newUser.lastName" required />
									</div>
									<div class="row"
										style="margin-bottom: 10px; padding-left: 10px; padding-right: 10px">
										<label for="name" class="col-md-3">Occupation</label> <input
											class="col-md-7" type="text" id="number" name="number"
											ng-model="vm.newUser.occupation" required />
									</div>
									<div class="form-group row"
										style="padding-left: 10px; padding-right: 10px">
										<label for="name" class="col-md-3">Age</label> <input
											class=" col-md-7" type="number" id="firstName" name="firstName"
											ng-model="vm.newUser.age" required />
									</div>

								</div>

								<div class="modal-footer">
									<div class="text-danger" style="float: left"
										ng-show="vm.errorMessage">{{vm.errorMessage}}</div>
									<button type="button" class="btn btn-sm" data-dismiss="modal"
										ng-click="vm.hideModal()">Close</button>
									<input type="submit" value="Add" class="btn btn-sm btn-success"
										ng-disabled="newUser.$invalid" />
								</div>

							</form>
						</div>



					</div>
				</div>
			</div>

		</div>
	</div>


</body>
</html>