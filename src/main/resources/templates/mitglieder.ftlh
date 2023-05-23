<#import "/spring.ftl" as spring/>

<!DOCTYPE html>
<html lang="de">
   	<head>
   	
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Clean Blog - Start Bootstrap Theme</title>
        <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
        <!-- Font Awesome icons (free version)-->
        <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
        <!-- Google fonts-->
        <link href="https://fonts.googleapis.com/css?family=Lora:400,700,400italic,700italic" rel="stylesheet" type="text/css" />
        <link href="https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800" rel="stylesheet" type="text/css" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="css/styles.css" rel="stylesheet" />

   	
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		
      	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css"> 
  		
  		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
  		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>

		<script type="text/javascript">
      		$(function(){$.get("http://localhost:9090/menu", function(data){$("#menudiv").html(data);});});
      	</script>
      	
  		<script type="text/javascript">
      		$(function(){
      			// Delete row on delete button click
      			$(document).on("click", ".delete", function(){
      		        $(this).parents("tr").remove();
      				$(".add-new").removeAttr("disabled");
      		    });
      			// Edit row on edit button click
      			$(document).on("click", ".edit", function(){		
      		        $(this).parents("tr").find("td:not(:last-child)").each(function(){
      					$(this).html('<input type="text" class="form-control" value="' + $(this).text() + '">');
      				});		
      				$(this).parents("tr").find(".add, .edit").toggle();
      				$(".add-new").attr("disabled", "disabled");
      		    });      			
      			// Show row on view button click
      			$(document).on("click", ".view", function(){		
      				var id = $(this).closest("tr").find("td:eq(0)").text();
      				window.location.href = "http://localhost:8082/positionen/id/" + id;
      		    });       		
      		});
  </script>      	

      	<title>Mitglieder</title>
   </head>   

   <body>
   
        <!-- Navigation-->
        <nav class="navbar navbar-expand-lg navbar-light" id="mainNav">
            <div class="container px-4 px-lg-5">
                <a class="navbar-brand" href="http://StattAuto/">StattAuto</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
                    Menu
                    <i class="fas fa-bars"></i>
                </button>
                <div class="collapse navbar-collapse" id="navbarResponsive">
                    <ul class="navbar-nav ms-auto py-4 py-lg-0">
                        <li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4" href="/">Verein</a></li>
                        <li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4" href="admin">Admin</a></li>
                    </ul>
                </div>
            </div>
        </nav>
        <!-- Page Header-->
        <header class="masthead" style="background-image: url('img/mitglieder-bg.jpg')">
            <div class="container position-relative px-4 px-lg-5">
                <div class="row gx-4 gx-lg-5 justify-content-center">
                    <div class="col-md-10 col-lg-8 col-xl-7">
                        <div class="site-heading">
                            <h1>StattAuto</h1>
                            <span class="subheading">Verein</span>
                        </div>
                    </div>
                </div>
            </div>
        </header>   
		<div id="content" class="container"><br>
			<h1>Liste aller Mitglieder</h1>
			<table class="table table-striped" border="1">
			    <tr>
			        <th>Vorname</th>
			        <th>Name</th>
			        <th>E-Mail</th>
			        <th>Geburtsdatum</th>
			        <th>Pr&uuml;fung der Bonit&auml;t</th>
			        <th>Vertragsart</th>
			        <th>Aktionen</th>
			    </tr>
			    <#list mitglieder as mitglied>
					<tr>
					    <td>${mitglied.vorname}</td>
					    <td>${mitglied.name}</td>
					    <td>${mitglied.email}</td>
					    <td>${mitglied.geburtsdatum}</td>
					    <td>${mitglied.bonitaet}</td>
					    <td>${mitglied.vertragsart}</td>
					    <td>
					        <button type="button" class="edit materials-icons"><i class="fas fa-edit"></i></button>
					        <button type="button" class="delete materials-icons"><i class="far fa-trash-alt"></i></button>
					    </td>
			        </tr>
				</#list>
			</table>
		    <br>
		    <form method="get" action="/antrag" >
		   		<button id="bottom" type="submit" background-color="blue">neues Mitglied aufnehmen</button>
			</form>
		</div>
		
	  <!-- Bootstrap core JavaScript -->
	  <script src="vendor/jquery/jquery.min.js"></script>
	  <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	
	  <!-- Custom scripts for this template -->
	  <script src="js/clean-blog.min.js"></script>		
   </body>   
</html>