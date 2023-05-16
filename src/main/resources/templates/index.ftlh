<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        
        <title>StattAuto</title>
        <link rel="icon" type="image/x-icon" href="favicon.ico" />
        
        <!-- Font Awesome icons (free version)-->
        <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
        <script type="text/javascript">
            $(document).ready(function() {
                $('.counter').each(function () {
                    $(this).prop('Counter',0).animate({Counter: $(this).text()}, {
                                                                                     duration: 4000,
                                                                                     easing: 'swing',
                                                                                     step: function (now) {
                                                                                        $(this).text(Math.ceil(now));
                                                                                     }
                                                                                  });
            });
        });
        </script> 
        
        <!-- Google fonts-->
        <link href="https://fonts.googleapis.com/css?family=Lora:400,700,400italic,700italic" rel="stylesheet" type="text/css" />
        <link href="https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800" rel="stylesheet" type="text/css" />
        
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="css/styles.css" rel="stylesheet" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
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
                        <li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4" href="mitglieder">Mitglieder</a></li>
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
                            <h1>Verein</h1>
                            <span class="subheading">StattAuto</span>
                        </div>
                    </div>
                </div>
            </div>
        </header>
        <!-- Main Content-->
        <div class="container px-4 px-lg-5">
            <div class="row gx-4 gx-lg-5 justify-content-center">
                <div class="col-md-10 col-lg-8 col-xl-8">
                    <!-- Post preview-->
                    <h2 class="post-title">Vereins Statistik</h2>
                    <div class="row">
                    	<div class="four col-md-3">
							<div class="counter-box">
								<i class="fa fa-group"></i>
								<span class="counter">${mitglieder}</span>
								<p>Mitglieder</p>
							</div>
						</div>
                    	<div class="four col-md-3">
							<div class="counter-box">
								<i class="fa fa-group"></i>
								<span class="counter">${orte}</span>
								<p>Orte</p>
							</div>
						</div>
                    	<div class="four col-md-3">
							<div class="counter-box">
								<i class="fa fa-group"></i>
								<span class="counter">${privatkunden}</span>
								<p>Privatkunden</p>
							</div>
						</div>
                    	<div class="four col-md-3">
							<div class="counter-box">
								<i class="fa fa-group"></i>
								<span class="counter">${firmenkunden}</span>
								<p>Firmenkunden</p>
							</div>
						</div>
					</div>
                    <div class="post-preview">
                        <p class="post-meta">
                            Gepostet von <a href="#!">Hermann Woock</a>
                            am 08. April 2023
                        </p>
                    </div>

                    <!-- Divider-->
                    <hr class="my-4" />
                </div>
            </div>
        </div>
        <!-- Footer-->
        <footer class="border-top">
            <div class="container px-4 px-lg-5">
                <div class="row gx-4 gx-lg-5 justify-content-center">
                    <div class="col-md-10 col-lg-8 col-xl-7">
                        <div class="small text-center text-muted fst-italic">Copyright &copy; oose eG 2023</div>
                    </div>
                </div>
            </div>
        </footer>
        <!-- Bootstrap core JS-->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
        <!-- Core theme JS-->
        <script src="js/scripts.js"></script>
    </body>
</html>
