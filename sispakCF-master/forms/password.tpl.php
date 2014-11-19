<div class='content'>
	<h4>Ganti Password</h4>
	<div id='error' class='alert alert-error'></div>
	<form class='form-horizontal' onsubmit="return false;">
		<div class='control-group'>
			<label class='control-label'>Password Lama</label>
			<div class='controls'>
				<input type="password" id='oldpw'/>
			</div>
		</div>
		<div class='control-group'>
			<label class='control-label'>Password</label>
			<div class='controls'>
				<input type="password" id='pw'/>
			</div>
		</div>
		<div class='control-group'>
			<label class='control-label'>Konfirmasi Password</label>
			<div class='controls'>
				<input type="password" id='confpw'/>
			</div>
		</div>
		<div class='control-group'>
			<div class='controls'>
				<a href='#' class='btn btn-success' id='saveBtn'>Ganti Password</a>
			</div>
		</div>
	</form>
</div>
<script type="text/javascript">
	$('#error').hide();
	$('#saveBtn').click(function(){
		var p = {
			oldpw: $("#oldpw").val(),
			pw: $("#pw").val(),
			confpw: $("#confpw").val()
		};

		console.log(p);
		$('#error').hide();
		if(p.oldpw == '')
		{
			$('#error').show().html('password lama harus diisi');
			return false;
		}

		if(p.pw == '')
		{
			$('#error').show().html('password harus diisi');
			return false;
		}

		if(p.pw != p.confpw){
			$('#error').show().html('Konfirmasi Password tidak sesuai');
			return false;
		}

		var self = this;
		$(self).attr('disabled', 'disabled');
		request('password_change', p, function(r){
			$(self).removeAttr('disabled');
			if(!r.success){
				$('#error').show().html(r.message);
				return;
			}

			alert('Password berhasil diganti.');
		});
		return false;
	})
</script>