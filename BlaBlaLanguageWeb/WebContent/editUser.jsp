<%
	Models.User u = new Models.User();
    if (session.getAttribute("user") == null) {
    	response.sendRedirect(request.getContextPath()+"/index.jsp");
    }else{
    	u = (Models.User) request.getSession().getAttribute("user");   	
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>



  <title>BlaBlaLanguage - Edit User</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="css/bootstrap.min.css">
  <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css"> 
  <script src="js/jquery.min.js"></script>
  <script src="js/bootstrap.min.js"></script>
  <link rel="stylesheet" href="css/screen.css">
  <script src="js/jquery.validate.js"></script>
  <script src="js/jquery.mask.min.js" type="text/javascript"></script>
  <script src="js/jquery-ui.js" type="text/javascript"></script>
  
  <style>
    /* Set height of the grid so .sidenav can be 100% (adjust if needed) */
    .row.content {height: 1500px}
    
    /* Set gray background color and 100% height */
    .sidenav {
      background-color: #f1f1f1;
      height: 100%;
    }
    
    /* Set black background color, white text and some padding */
    footer {
      background-color: #555;
      color: white;
      padding: 15px;
    }
    
    .error { color:red !important; }
    
    /* On small screens, set height to 'auto' for sidenav and grid */
    @media screen and (max-width: 767px) {
      .sidenav {
        height: auto;
        padding: 15px;
      }
      .row.content {height: auto;} 
    }
  </style>
</head>
<body>
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
      <a class="navbar-brand" href="#"><%=u.getLogin()%></a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav">
        <!-- >li class="active"><a href="#">Home</a></li>
        <li><a href="#">About</a></li>
        <li><a href="#">Projects</a></li>
        <li><a href="#">Contact</a></li-->
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li><a id="btnOpenLogin" href="#"><span class="glyphicon glyphicon-log-in"></span> <%=u.getName()%></a></li>
      </ul>
    </div>
  </div>
</nav>
<div class="container-fluid">
  <div class="row content">
    <div class="col-sm-3 sidenav">
      <h4>BlaBlaLanguage</h4>
      <ul class="nav nav-pills nav-stacked">
        <li class="active"><a href="editUser.jsp">My profile</a></li>
        <li><a href="#section2">Meetings</a></li>
        <li ><a href="listEstablishment.jsp">Establishments</a></li>
        <li><a href="logout.jsp">Exit</a></li>
              
        <li><a href="#section2"></a></li>             
      </ul><br>
    </div>

    <div class="col-sm-9">
      <h4><small>Please, fill the form bellow</small></h4>
      <hr>
       <form role="form"  id="frmDefault" style="width: 500px;" action="userController" method="POST"  >
		  
		  <div class="form-group">
		    <label for="name">Name:</label>
		    <input type="text" class="form-control small" id="name" name="name" required value="<%=u.getName()%>" />
		  </div>
		   <div class="form-group">
		    <label for="email">Birthday:</label>
		    <input type="text" class="form-control" id="bday" name="bday" required value="<%=u.getBirthday()%>" />
		  </div>		  	 
		  
		  <button type="submit" class="btn btn-success">Submit</button>
		</form>
    </div>
  </div>
</div>

	<script type="text/javascript" >
	/*
	$.validator.setDefaults({
		submitHandler: function() {
			alert("submitted!");
		}
	});
*/
	  $(document).ready(function() {
		// validate the comment form when it is submitted
		//$("#frmDefault").validate();

		$('#bday').mask('00/00/0000');
		$( "#bday" ).datepicker({ dateFormat: 'dd/mm/yy' }).mask("99/99/9999");

		$( "#bday" ).blur(function() {
			if(!isValidDate($( "#bday" ).val())){
				$( "#bday" ).val("");
			}
		});

		$("#frmDefault").validate({
			rules: {
				name: "required",
				pwd: {
					required: true,
					minlength: 5
				},
				bday: {
					required: true,
					minlength: 10
				},
				cpwd: {
					required: true,
					minlength: 5,
					equalTo: "#pwd"
				},
				em: {
					required: true,
					email: true
				}
				
			},
			messages: {
				name: "Please enter your name",
				bday: "Please enter your birthday",
				password: {
					required: "Please provide a password",
					minlength: "Your password must be at least 5 characters long"
				},
				confirm_password: {
					required: "Please provide a password",
					minlength: "Your password must be at least 5 characters long",
					equalTo: "Please enter the same password as above"
				},
				email: "Please enter a valid email address"				
			}
		});

		
		/*
		// validate signup form on keyup and submit
		$("#signupForm").validate({
			rules: {
				firstname: "required",
				lastname: "required",
				username: {
					required: true,
					minlength: 2
				},
				password: {
					required: true,
					minlength: 5
				},
				confirm_password: {
					required: true,
					minlength: 5,
					equalTo: "#password"
				},
				email: {
					required: true,
					email: true
				},
				topic: {
					required: "#newsletter:checked",
					minlength: 2
				},
				agree: "required"
			},
			messages: {
				firstname: "Please enter your firstname",
				lastname: "Please enter your lastname",
				username: {
					required: "Please enter a username",
					minlength: "Your username must consist of at least 2 characters"
				},
				password: {
					required: "Please provide a password",
					minlength: "Your password must be at least 5 characters long"
				},
				confirm_password: {
					required: "Please provide a password",
					minlength: "Your password must be at least 5 characters long",
					equalTo: "Please enter the same password as above"
				},
				email: "Please enter a valid email address",
				agree: "Please accept our policy",
				topic: "Please select at least 2 topics"
			}
		});

		// propose username by combining first- and lastname
		$("#username").focus(function() {
			var firstname = $("#firstname").val();
			var lastname = $("#lastname").val();
			if (firstname && lastname && !this.value) {
				this.value = firstname + "." + lastname;
			}
		});

		//code to hide topic selection, disable for demo
		var newsletter = $("#newsletter");
		// newsletter topics are optional, hide at first
		var inital = newsletter.is(":checked");
		var topics = $("#newsletter_topics")[inital ? "removeClass" : "addClass"]("gray");
		var topicInputs = topics.find("input").attr("disabled", !inital);
		// show when newsletter is checked
		newsletter.click(function() {
			topics[this.checked ? "removeClass" : "addClass"]("gray");
			topicInputs.attr("disabled", !this.checked);
		}); */
	});
	</script>

</body>
</html>
