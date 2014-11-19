<div class='content'>
	<h4>Harap Login terlebih dahulu</h4>
	<div id='error' class='alert alert-error'></div>
	<form class='form-horizontal' onsubmit="alert('s'); return false;">
		<div class='control-group'>
			<label class='control-label'>Username</label>
			<div class='controls'>
				<input type="text" id='loginUsername'/>
			</div>
		</div>
		<div class='control-group'>
			<label class='control-label'>Password</label>
			<div class='controls'>
				<input type="password" id='loginPassword'/>
			</div>
		</div>
		<div class='control-group'>
			<div class='controls'>
				<a href='#' class='btn btn-success' id='loginBtn'>Login</a>
			</div>
		</div>
	</form>
</div>
<script type="text/javascript">
	$('#error').hide();
	$('#loginBtn').click(function(){
		var p = {
			username: $("#loginUsername").val(),
			password: $("#loginPassword").val()
		};

		$('#error').hide();
		if(p.username == '')
		{
			$('#error').show().html('username harus diisi');
			return false;
		}

		if(p.password == '')
		{
			$('#error').show().html('password harus diisi');
			return false;
		}

		var self = this;
		$(self).attr('disabled', 'disabled');
		request('login', p, function(r){
			if(!r.success){
				$(self).removeAttr('disabled');
				$('#error').show().html(r.message);
				return;
			}
			$(self).html('Login berhasil, harap tunggu...');
			window.location.reload();
		});
		return false;
	})
</script>