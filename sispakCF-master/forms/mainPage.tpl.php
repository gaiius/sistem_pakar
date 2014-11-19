<?php global $pageTitle;?>

<!DOCTYPE html>
<html>
<head>
	<title><?=$pageTitle;?></title>
	<link rel='stylesheet' href='static/css/bootstrap.min.css'/>
	<link rel='stylesheet' href='static/css/jquery.dataTables.css'/>
	<link rel='stylesheet' href='static/css/app.css'/>
	
	<script type='text/javascript' src='static/js/jquery-1.9.1.min.js'></script>
	<script type='text/javascript' src='static/js/bootstrap.min.js'></script>
	<script type='text/javascript' src='static/js/spin.min.js'></script>
	<script type='text/javascript' src='static/js/jquery.dataTables.min.js'></script>
	<script type='text/javascript'>
		var BASE_PATH = <?=json_encode(WEB_BASE_URL);?>;
	</script>
	<script type='text/javascript' src='static/js/app.js'></script>
</head>
<body>
<div id='webBody'>
	<div id='mainBlock'>
		<div class='navbar navbar-inverse navbar-fixed-top'>
			<div class='navbar-inner'>
				<div class="nav-collapse collapse">
					<?php
					// penentuan halaman "sekarang" yang sedang aktif
					function cssActive($p){
						$q = $_GET['q'];
						if(!$q) $q = 'home';
						return $p == $q ? 'active' : '';
					}

					$paths = array(
						'handphone' => 'Handphone',
						'komponen' => 'Komponen',
						'gejala' => 'Gejala',
						'relasi' => 'Relasi',
						'password' => 'Password'
					);
					?>
					<ul class="nav" id='mainMenu'>
						<?php
						if(loggedIn()){
							foreach($paths as $k => $v){
								$active = cssActive($k);
								$uri = uri('/' . $k);
								echo "
									<li class='$active'>
										<a href='$uri'>$v</a>
									</li>
								";
							}
							$l = uri('logout');
							echo "
								<li>
									<a href='$l'>Logout</a>
								</li>
							";
						}
						?>
					</ul>
				</div>
			</div>
		</div>

		<header class="jumbotron subhead" id="overview">
			<div class="container">
				<table> 
					<tr> 
						<td valign='middle'><img src='http://placehold.it/100&text=logo+disini'/></td>
						<td valign='middle'><h3>Administrasi Sistem Pakar Metode CF</h3></td>
					</tr>
				</table>
			</div>
		</header>

		<div class='container' style='margin-top: 10px'>
			<?php include( pageBody() ); ?>
		</div>
	</div>
</div>
</body>
</html>
