<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Spam Email V1.0.0 - HMQIWT</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
<link
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
	rel="stylesheet"
	integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN"
	crossorigin="anonymous">

<style>
.data-list {
	max-height: 300px;
	background: #ffffff;
	padding: 10px;
	border-radius: 5px !important;
	overflow-y: scroll;
}
</style>
</head>
<body>
	<div class="container p-3 mb-2 bg-light text-dark">

		<form action="getListEmail" method="POST"
			enctype="multipart/form-data">
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<i class="fa fa-user-circle-o" aria-hidden="true"></i>
						<label for="exampleInputEmail1">Email address</label> <input
							type="email" name="email" class="form-control"
							id="exampleInputEmail1" aria-describedby="emailHelp"
							placeholder="Enter email">
					</div>
					<div class="form-group">
						<i class="fa fa-key" aria-hidden="true"></i>
						<label for="exampleInputPassword1">Password</label> <input
							type="password" name="pass" class="form-control" id="exampleInputPassword1"
							placeholder="Password">
					</div>
					<div class="form-group">
						<i class="fa fa-header" aria-hidden="true"></i>
						<label for="subject">Subject</label> <input type="text"
							class="form-control" id="subject" name="subject" placeholder="Subject...">
					</div>

					<div class="form-group">
						<i class="fa fa-inbox" aria-hidden="true"></i>
						<label for="content">Content</label> <input type="text"
							class="form-control" id="content" name="content" placeholder="Content...">
					</div>

					<div class="form-group">
						<i class="fa fa-file-code-o" aria-hidden="true"></i>
						<label for="files">File Upload</label> <input type="file"
							class="form-control-file" name="files" multiple>
					</div>
					<div class="form-group row">
						<label for="time" class="col-md-4 col-form-label"><i class="fa fa-calendar" aria-hidden="true"></i>
						Time
							delay:</label>
						<div class="col-md-8">
							<input type="text" class="form-control" id="time"
								placeholder="Time delay">
						</div>
					</div>
					<input type="submit" value="Send"
						class="btn btn-outline-dark col-md-12">
				</div>
				<div class="col-md-6">

					<div class="form-group">
						<i class="fa fa-list" aria-hidden="true"></i>
						<label for="txtEmail">List Email</label>
						<textarea name="txtEmail" class="form-control" id="txtEmail"
							rows="5"></textarea>
					</div>
					<button type="button" onclick="putInTable()"
						class="btn btn-dark col-md-12"><i class="fa fa-plus-square-o" aria-hidden="true"></i>
						Add Email</button>


					<br>
					<div class="data-list">
						<table class="table">
							<thead>
								<tr>
									<th scope="col">#</th>
									<th scope="col">EMAIL</th>
									<th scope="col">STATUS</th>
								</tr>
							</thead>
							<tbody>

							</tbody>
						</table>
					</div>
				</div>
			</div>
		</form>





	</div>

	<script type="text/javascript">
		function putInTable() {
			var data = document.getElementById("txtEmail").value.split("\n");
			for (var i = 0; i <= data.length - 1; i++) {
				var trE = document.createElement('tr');
				var thE = document.createElement('th');
				thE.setAttribute('scope', 'row');
				const val = document.createTextNode(i + 1);
				thE.appendChild(val);

				var tdE = document.createElement('td');
				const val1 = document.createTextNode(data[i]);
				tdE.appendChild(val1);

				var tdE1 = document.createElement('td');
				var DOM_img = document.createElement("img");
				DOM_img.src = "https://i.imgur.com/deDRVbQ.png";
				tdE1.appendChild(DOM_img);

				trE.appendChild(thE);
				trE.appendChild(tdE);
				trE.appendChild(tdE1);

				document.querySelector('table tbody').appendChild(trE);
			}

		}
	</script>


</body>
</html>