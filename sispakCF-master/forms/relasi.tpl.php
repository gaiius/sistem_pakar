<div class='row' style='overflow: hidden'>
	<div id='itemNavigator'>
		<div class='span9' id='itemListView'>
			<div style='padding: 10px 0'>
				<a class='btn btn-success' id='newItemBtn'>
					Buat Relasi Baru
				</a>
			</div>
			<table class='table table-condensed'>
				<thead>
					<tr>
						<th>Handphone</th>
						<th>Gejala</th>
						<th>Komponen</th>
						<th>Nilai CF</th>
						<th width=120>Aksi</th>
					</tr>
				</thead>
				<tbody id='itemList'></tbody>
			</table>
		</div>
		<div class='span9' id='itemForm'>
			<h5 id='formTitle'>Tambah Data Relasi</h5>
			<form class='form-horizontal' id='dataForm' onsubmit="return false;">
				<div class='control-group'>
					<label class='control-label'>Handphone</label>
					<div class='controls'>
						<input type='text' disabled="disabled" id='handphone'/>
					</div>
				</div>
				<div class='control-group'>
					<label class='control-label'>Nama Gejala</label>
					<div class='controls'>
						<select id='idPertanyaan'>
							<option selected="selected" class='blank' value=''>--Pilih--</option>
						</select>
					</div>
				</div>
				<div class='control-group'>
					<label class='control-label'>Komponen</label>
					<div class='controls'>
						<select id='idKomponen'>
							<option selected="selected" class='blank' value=''>--Pilih--</option>
						</select>
					</div>
				</div>
				<div class='control-group'>
					<label class='control-label'>Nilai CF</label>
					<div class='controls'>
						<input type='text' id='cf'>
					</div>
				</div>
				<div class='control-group'>
					<div class='controls'>
						<a class='btn btn-primary' id='saveBtn'>Simpan</a>
						<a class='btn btn-warning' id='resetBtn'>Reset</a>
						<a class='btn btn' id='cancelBtn'>Batal</a>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
<script type="text/javascript">
	var OPR_MODE = MODE_INSERT;
	var body = $('#itemNavigator');

	request('komponen/list', {}, function(r){
		var sel = $('#idKomponen');
		$('option', sel).not('.blank').remove();
		for(var i = 0; i < r.length; i++){
			$('<option>').attr('value', r[i].id_komponen)
				.text(r[i].nm_komponen).appendTo(sel);
		}
	});

	request('pertanyaan/list', {}, function(r){
		var sel = $('#idPertanyaan');
		$('option', sel).not('.blank').remove();
		for(var i = 0; i < r.length; i++){
			$('<option>')
				.data('pertanyaan', r[i])
				.attr('value', r[i].id_gejala)
				.text(r[i].nm_gejala).appendTo(sel);
		}
	});

	$('#idPertanyaan').change(function(){
		var data = $('option:selected', this).data('pertanyaan');
		var nmHandphone = (typeof data == 'undefined') ? '' : data.nm_handphone;
		$('#handphone').attr('value', nmHandphone);
	});

	function loadItems(){
		request('relasi/list', {}, function(r){
			var table = $('#itemList');

			try{
				var dt = $(table).parents('table').dataTable();
				dt.fnDestroy();
			} catch (e){}

			$('tr', table).remove();

			for(var i = 0; i < r.length; i++){
				var data = r[i];
				var row = $('<tr>').appendTo(table).data('item', data);
				$('<td>').appendTo(row).html(data.nm_handphone);
				$('<td>').appendTo(row).html(data.nm_gejala);
				$('<td>').appendTo(row).html(data.nm_komponen);
				$('<td>').appendTo(row).html(data.nilai_cf * 1);

				var cell = $('<td>').appendTo(row);
				var editBtn = $('<button>').addClass('btn btn-warning').text('Edit').appendTo(cell);
				$(editBtn).click(function(){
					var data = $(this).parents('tr').data('item');
					$('option', '#idKomponen').removeAttr('selected');
					$('option[value=' + data.id_komponen + ']', '#idKomponen').attr('selected', 'selected');
					$('#idKomponen').attr('disabled', 'disabled');

					$('option', '#idPertanyaan').removeAttr('selected');
					$('option[value=' + data.id_gejala + ']', '#idPertanyaan').attr('selected', 'selected');
					$('#idPertanyaan').attr('disabled', 'disabled');

					$('#handphone').attr('value', data.nm_handphone);

					$('#cf').attr('value', data.nilai_cf * 1);
					$('#formTitle').html('Edit Data Relasi');
					OPR_MODE = MODE_UPDATE;
					showForm();
				});
				$(cell).append("&nbsp;");
				var delBtn = $('<button>').addClass('btn btn-danger').text('Hapus').appendTo(cell);
				$(delBtn).click(function(){
					var data = $(this).parents('tr').data('item');
					confirm('Hapus Relasi ' + ' ?', function(){
						request('relasi/hapus', data, function(r){
							alert(r, loadItems);
						})
					})
				})
			}

			$(table).parents('table').dataTable();
		})
	}
	loadItems();

	function showForm(){
		$('#itemListView').hide();
		$('#itemForm').show();
	}

	function showList(){
		$('#itemListView').show();
		$('#itemForm').hide();
	};
	showList();

	function getFormValues(){
		var v = {};
		$('input, select', '#dataForm').each(function(){
			v[ $(this).attr('id') ] = $(this).val();
		});
		return v;
	}

	function resetForm(){
		$('.control-group', '#dataForm').removeClass('error');
		$('.help-inline', '#dataForm').remove();
		$('#dataForm')[0].reset();
	}

	$('#resetBtn').click(function(r){
		resetForm();
	});

	$('#cancelBtn').click(function(r){
		resetForm();
		showList();
	});

	$('#newItemBtn').click(function(){
		resetForm();

		$('#handphone').attr('value', '');
		$('option', '#idKomponen').removeAttr('selected');
		$('option[value=""]', '#idKomponen').attr('selected', 'selected');
		$('#idKomponen').removeAttr('disabled');

		$('option', '#idPertanyaan').removeAttr('selected');
		$('option[value=""]', '#idPertanyaan').attr('selected', 'selected');
		$('#idPertanyaan').removeAttr('disabled');

		$('#cf').attr('value', '');
		$('#formTitle').html('Tambah Data Relasi');
		OPR_MODE = MODE_INSERT;
		showForm();
	});

	$('#resetBtn').click(function(){
		resetForm();
	})

	$('#saveBtn').click(function(){
		var values = getFormValues();
		for(var id in values){
			var div = $('#' + id).parents('.control-group');
			if(values[id] == '' || (id == 'cf' && !/^(-0|0)\.[0-8]{1,}/.test(values[id])))
			{
				div.addClass('error');
				if($('.controls', div).has('span.help-inline').length < 1)
				{
					var err = (id == 'cf') ? 'CF harus diisi angka desimal kurang dari -0.8 s/d 0.8, ' +
						'misalnya 0.7' : 'harus diisi';
					var span = $('<span>').addClass('help-inline').html(err).appendTo(
						$('.controls', div)
					);
				}
				return false;
			}
			$(div).removeClass('error');
			$('span.help-inline', div).remove();
		}

		var path = 'relasi/tambah';
		if(OPR_MODE == MODE_UPDATE)
			path = 'relasi/edit';

		request(path, values, function(r){
			if(!r.success)
			{
				alert(r.error);
				return;
			}

			alert('Data Berhasil disimpan', function(){
				loadItems();
				showList();
			});
		});
		return false;
	});

</script>
