<div class='row' style='overflow: hidden'>
	<div id='itemNavigator'>
		<div class='span9' id='itemListView'>
			<div style='padding: 10px 0'>
				<a class='btn btn-success' id='newItemBtn'>
					Buat Gejala Baru
				</a>
			</div>
			<table class='table table-condensed'>
				<thead>
					<tr>
						<th>Handphone</th>
						<th>ID Gejala</th>
						<th>Nama Gejala</th>
						<th>Deskripsi</th>
						<th width=120>Aksi</th>
					</tr>
				</thead>
				<tbody id='itemList'></tbody>
			</table>
		</div>
		<div class='span9' id='itemForm'>
			<h5 id='formTitle'>Tambah Data Gejala</h5>
			<form class='form-horizontal' id='dataForm' onsubmit="return false;">
				<div class='control-group'>
					<label class='control-label'>Handphone</label>
					<div class='controls'>
						<select id='idHandphone'>
							<option selected="selected" class='blank' value=''>--Pilih--</option>
						</select>
					</div>
				</div>
				<div class='control-group'>
					<label class='control-label'>ID Gejala</label>
					<div class='controls'>
						<input type='text' id='idPertanyaan' readonly="readonly">
					</div>
				</div>
				<div class='control-group'>
					<label class='control-label'>Nama Gejala</label>
					<div class='controls'>
						<input type='text' id='nmPertanyaan'>
					</div>
				</div>
				<div class='control-group'>
					<label class='control-label'>Deskripsi</label>
					<div class='controls'>
						<textarea id='deskripsi'></textarea>
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

	request('handphone/list', {}, function(r){
		var sel = $('#idHandphone');
		$('option', sel).not('.blank').remove();
		for(var i = 0; i < r.length; i++){
			$('<option>').attr('value', r[i].id_handphone)
				.text(r[i].nm_handphone).appendTo(sel);
		}
	});

	function loadItems(){
		request('pertanyaan/list', {}, function(r){
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
				$('<td>').appendTo(row).html(data.id_gejala);
				$('<td>').appendTo(row).html(data.nm_gejala);
				$('<td>').appendTo(row).html(data.deskripsi);

				var cell = $('<td>').appendTo(row);
				var editBtn = $('<button>').addClass('btn btn-warning').text('Edit').appendTo(cell);
				$(editBtn).click(function(){
					var data = $(this).parents('tr').data('item');
					$('option', '#idHandphone').removeAttr('selected');
					$('option[value=' + data.id_handphone + ']', '#idHandphone').attr('selected', 'selected');

					$('#idPertanyaan').attr('value', data.id_gejala);
					$('#nmPertanyaan').attr('value', data.nm_gejala);
					$('#deskripsi').html(data.deskripsi);
					$('#formTitle').html('Edit Data Gejala');
					OPR_MODE = MODE_UPDATE;
					showForm();
				});
				$(cell).append("&nbsp;");
				var delBtn = $('<button>').addClass('btn btn-danger').text('Hapus').appendTo(cell);
				$(delBtn).click(function(){
					var data = $(this).parents('tr').data('item');
					var nmPertanyaan = data.nm_gejala;
					confirm('Hapus Gejala ' + nmPertanyaan + ' ?', function(){
						request('pertanyaan/hapus', data, function(r){
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
		$('input, select, textarea', '#dataForm').each(function(){
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
		$('option', '#idKomponen').removeAttr('selected');
		$('option[value=""]', '#idHandphone').attr('selected', 'selected');

		$('#idPertanyaan').attr('value', '');
		$('#nmPertanyaan').attr('value', '');
		$('#deskripsi').html('');
		$('#formTitle').html('Tambah Data Gejala');
		OPR_MODE = MODE_INSERT;
		request('next/Pertanyaan/id', {}, function(r){
			showForm();
			$('#idPertanyaan').attr('value', r);
		})
	});

	$('#resetBtn').click(function(){
		resetForm();
	})

	$('#saveBtn').click(function(){
		var values = getFormValues();
		for(var id in values){
			var div = $('#' + id).parents('.control-group');
			if(values[id] == '' || (id == 'cf' && !/^0\.[0-9]{1,}/.test(values[id])))
			{
				div.addClass('error');
				if($('.controls', div).has('span.help-inline').length < 1)
				{
					var err = (id == 'cf') ? 'CF harus diisi angka desimal misalnya 0.9' : 'harus diisi';
					var span = $('<span>').addClass('help-inline').html(err).appendTo(
						$('.controls', div)
					);
				}
				return false;
			}
			$(div).removeClass('error');
			$('span.help-inline', div).remove();
		}

		var path = 'pertanyaan/tambah';
		if(OPR_MODE == MODE_UPDATE)
			path = 'pertanyaan/edit';

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
