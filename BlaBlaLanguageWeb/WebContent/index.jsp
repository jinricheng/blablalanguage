<!DOCTYPE html>
<html lang="en">
<head>
  <title>BlaBlaLanguage</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="
http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
  <!-- >script src="
http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script-->

    <script src="js/jquery.min.js"></script>
  <script src="js/bootstrap.min.js"></script>
  <link rel="stylesheet" href="css/screen.css">
  <script src="js/jquery.validate.js"></script>
  <style>
    /* Remove the navbar's default margin-bottom and rounded borders */
    .navbar {
      margin-bottom: 0;
      border-radius: 0;
      /*background-color: #7E1350;*/
      background-color: purple;
      color: black !important;
    }
   

    /* Set height of the grid so .sidenav can be 100% (adjust as needed) */
    .row.content {height: 450px}

    /* Set gray background color and 100% height */
    .sidenav {
      padding-top: 20px;
      background-color: #f1f1f1;
      height: 100%;
      color: black !important;

    }

    /* Set black background color, white text and some padding */
    footer {
      background-color: #e6b800;
      color: white;
      padding: 15px;
    }

    /* On small screens, set height to 'auto' for sidenav and grid */
    @media screen and (max-width: 767px) {
      .sidenav {
        height: auto;
        padding: 15px;
      }
      .row.content {height:auto;}
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
      <a class="navbar-brand" href="#">Bla-Bla-Language</a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav">
        <!--l> class="active"><a href="#">Home</a></li-->
        <!--li><a href="#">About</a></li>
        <li><a href="#">Projects</a></li>
        <li><a href="#">Contact</a></li-->
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li><a id="btnOpenLogin" href="#"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
      </ul>
    </div>
  </div>
</nav>


<div class="container-fluid text-center">

<div  id="loginForm" class="nav navbar2-nav navbar2-right"
style="float:right;background-color:white;">
 <form class="form-inline" role="form" id="frmLogin"
action="LoginController" method="post" >
            <div class="form-group">
              <label for="email">Email:</label>
              <input type="email" class="form-control" id="email"
name="email" placeholder="Enter email">
            </div>
            <div class="form-group">
              <label for="pwd">Password:</label>
              <input type="password" class="form-control" id="pwd"
name="pwd" placeholder="Enter password">
            </div>
            <button type="button" id="btnSubmitLogin" class="btn
btn-default">Submit</button>
            <br/>
            <p><a href="createUser.jsp">if you do not have login click
here</a></p>
            <div id="messageLogin">
            </div>
</form>
</div>

  <div class="row content">
<!--     <div class="col-sm-2 sidenav"> -->
<!--       <p><a href="#">Find yout Meeting</a></p> -->
<!--       p><a href="#">Link</a></p>
<!--       <p><a href="#">Link</a></p-->
<!--     </div> -->
    <div class="col-sm-8 text-left">
      <h1>Bla-Bla-Language</h1>
      <p>Bla-Bla-Language is a tool for the meetings in another language,
facilitating your immersion in another world without traveling.</p>
      <hr>
      <div style="display:float;width:100%;">
        <img class="well" src="
http://fll.blogs.udl.cat/files/2013/02/UdL-EPS.jpg" style="width:33%;" >
        <img class="well" src="
http://fll.blogs.udl.cat/files/2013/02/UdL-EPS.jpg" style="width:33%;" >
        <img class="well" src="
http://fll.blogs.udl.cat/files/2013/02/UdL-EPS.jpg" style="width:33%;" >
      </div>
    <div style="display:float;width:100%;">
    <img class="well" src="
http://fll.blogs.udl.cat/files/2013/02/UdL-EPS.jpg" style="width:33%;" >
    <img class="well" src="
http://fll.blogs.udl.cat/files/2013/02/UdL-EPS.jpg" style="width:33%;" >
    <img class="well" src="
http://fll.blogs.udl.cat/files/2013/02/UdL-EPS.jpg" style="width:33%;" >
     </div>
      <div style="display:float;width:100%;">
        <img class="well" src="
http://fll.blogs.udl.cat/files/2013/02/UdL-EPS.jpg" style="width:33%;" >
        <img class="well" src="
http://fll.blogs.udl.cat/files/2013/02/UdL-EPS.jpg" style="width:33%;" >
        <img class="well" src="
http://fll.blogs.udl.cat/files/2013/02/UdL-EPS.jpg" style="width:33%;" >
      </div>
      <!--h3>Test</h3>
      <p>Lorem ipsum...</p-->
    </div>
    <div class="col-sm-4 sidenav">
      <div class="well">
    <img class="well" src="
http://web.udl.es/dept/macs/sedai/VP/Imagenes/M-UdL.jpg"
style="width:200px;" >
      </div>
      <div class="well">
        <!--p>ADS</p-->
    <img class="well" src="
http://fll.blogs.udl.cat/files/2013/02/UdL-EPS.jpg" style="width:200px;" >

      </div>
    </div>
  </div>
</div>
<br/>

</body>

 <script>
      $(document).ready(function() {

       $("#loginForm").hide();
       $("#frmLogin").validate();

       $("#btnOpenLogin").click(function(e) {

           if($('#loginForm').is(':visible')) {
               $( "#loginForm" ).fadeOut( "slow", function() {
                    // Animation complete
                  });
            }else
            {
              $( "#loginForm" ).fadeIn( "slow", function() {
                    // Animation complete
                  });
            } //e.preventDefault();
             // $('#modal').dialog();
       });


       $("#btnSubmitLogin").click(function( event ) {

           $.ajax({
                url : 'LoginController',
                async: "false",
                type: "get",
                data : {
                    email : $('#email').val(),
                    pwd : $('#pwd').val()
                },
                success : function(responseText) {
                    var json = responseText
                    obj = JSON.parse(json);


                     if(!obj.result){
                         $("#messageLogin").text(obj.name);

                         return false;
                     }
                     else{
                         $("#frmLogin").submit();
                              return true;
                     }
                }
            });



         });

       /*
       $("#btnSubmitLogin").click(function(e) {
          //alert("Entrou");
         //  $("#messageLogin").text("Attention: Login or Pass Wrong!");





       });*/




      });

 </script>

</html>