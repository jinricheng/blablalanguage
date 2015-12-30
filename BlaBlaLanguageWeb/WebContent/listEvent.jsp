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
  <title>BlaBlaLanguage - Meetings</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
  
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <link href="css/metro/pink/jtable.css" rel="stylesheet" type="text/css" />
  <link href="css/jquery-ui.min.css" rel="stylesheet" type="text/css" />
  
  <script src="js/jquery-ui.js" type="text/javascript"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
  <script src="js/jquery.jtable.js" type="text/javascript"></script>
  
  
<style>
    /* Set height of the grid so .sidenav can be 100% (adjust if needed) */
    .row.content {height: 1500px}
    
     .navbar {
      margin-bottom: 0;
      border-radius: 0;
      /*background-color: #7E1350;*/
      background-color: purple;
      color: black;
    }
    
     .active bla {
      background-color: purple;
    }
    
    /* Set gray background color and 100% height */
    .sidenav {
      background-color: #f1f1f1;
      height: 100%;
    }
    
    .blabla{
      background-color: #f1f1f1;
      height: 100%;
    }
    /* Set black background color, white text and some padding */
    footer {
      background-color: #555;
      color: white;
      padding: 15px;
    }
    
</style> 
  <script type="text/javascript">
        $(document).ready(function() {
                $('#Events').jtable({
                        title : 'Meetings',
                        actions : {
                                listAction : 'eventController?action=list',
                                //createAction : 'Controller?action=create',
                                updateAction : 'eventController?action=update',
                                deleteAction : 'eventController?action=delete'
                                                
                        },
                        fields : {
                                id : {
                                        title : 'Id',
                                        width : '5%',
                                        key : true,
                                        list : true,
                                        create : true
                                },
                                name : {
                                    title : 'name',
                                    width : '30%',
                                    edit : true
                            	},        
                                estab : {
                                        title : 'estab',
                                        width : '10%',
                                        edit : false                                     
                                },
                                lang : {
                                        title : 'lang',
                                        width : '10%',
                                        edit : false
                                },
                                tim : {
                                    title : 'tim',
                                    width : '30%',
                                    edit : false
                            	}       
                                                 
                        }
                });
                $('#Events').jtable('load');
        });
</script>  
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
        <li><a href="editUser.jsp">My profile</a></li>
        <li class="active bla"><a href="listEvent.jsp">Meetings</a></li>
        <li ><a href="listEstablishment.jsp">Establishments</a></li>
        <li><a href="logout.jsp">Exit</a></li>             
              
        <li><a href="#section2"></a></li>             
      </ul><br>
    </div>

    <div class="col-sm-9">
     <h3>Your Meetings</h3>
     <hr>
     <form action="createEvent.jsp" method="post">
     	<button type="submit" class="btn btn-success" style="background-color: purple;" >Add New</button>
     </form>
	 
	<br/>
	<div style="text-align: center;">
	        <!-- >h4>jQuery jTable Setup in java</h4> -->
	        <div id="Events"></div>
	</div>

    </div>
  </div>
</div>

<footer class="container-fluid">
  <p>Footer Text</p>
</footer>

</body>
</html>
