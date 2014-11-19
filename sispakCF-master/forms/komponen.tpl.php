<div class='row' style='overflow: hidden'>
	<div id='itemNavigator'>
		<div class='span9' id='itemListView'>
			<div style='padding: 10px 0'>
				<a class='btn btn-success' id='newItemBtn'>
					Buat Komponen Baru
				</a>
			</div>
			<table class='table table-condensed'>
				<thead>
					<tr>
						<th>ID Komponen</th>
						<th>Nama Komponen</th>
						<th>Deskripsi</th>
						<th>Pencegahan</th>
						<th width=120>Aksi</th>
					</tr>
				</thead>
				<tbody id='itemList'></tbody>
			</table>
		</div>
		<div class='span9' id='itemForm'>
			<h5 id='formTitle'>Tambah Data Komponen</h5>
			<form class='form-horizontal' id='dataForm' onsubmit="return false;">
				<div class='control-group'>
					<label class='control-label'>ID Komponen</label>
					<div class='controls'>
						<input type='text' id='idKomponen' readonly="readonly">
					</div>
				</div>
				<div class='control-group'>
					<label class='control-label'>Nama Komponen</label>
					<div class='controls'>
						<input type='text' id='nmKomponen'>
					</div>
				</div>
				<div class='control-group'>
					<label class='control-label'>Deskripsi</label>
					<div class='controls'>
						<textarea id='deskripsi'></textarea>
					</div>
				</div>
				<div class='control-group'>
					<label class='control-label'>Pencegahan</label>
					<div class='controls'>
						<textarea id='pencegahan'></textarea>
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
function loadItems(){
	request('komponen/list', {}, function(r){
		var table = $('#itemList');

		try{
			var dt = $(table).parents('table').dataTable();
			dt.fnDestroy();
		} catch (e){}

		$('tr', table).remove();

		for(var i = 0; i < r.length; i++){
			var data = r[i];
			var row = $('<tr>').appendTo(table).data('item', data);
			$('<td>').appendTo(row).html(data.id_komponen);
			$('<td>').appendTo(row).html(data.nm_komponen);
			$('<td>').appendTo(row).html(data.deskripsi);
			$('<td>').appendTo(row).html(data.pencegahan);

			var cell = $('<td>').appendTo(row);
			var editBtn = $('<button>').addClass('btn btn-warning').text('Edit').appendTo(cell);
			$(editBtn).click(function(){
				var data = $(this).parents('tr').data('item');
				console.log(data);
				$('#idKomponen').attr('value', data.id_komponen);
				$('#nmKomponen').attr('value', data.nm_komponen);
				$('#deskripsi').html(data.deskripsi);
				$('#pencegahan').html(data.pencegahan);
				$('#formTitle').html('Edit Data Komponen');
				OPR_MODE = MODE_UPDATE;
				showForm();
			});
			$(cell).append("&nbsp;");
			var delBtn = $('<button>').addClass('btn btn-danger').text('Hapus').appendTo(cell);
			$(delBtn).click(function(){
				var data = $(this).parents('tr').data('item');
				var nmKomponen = data.nm_komponen;
				confirm('Hapus komponen ' + nmKomponen + ' ?', function(){
					request('komponen/hapus', data, function(r){
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
	$('input, textarea', '#dataForm').each(function(){
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
	$('#nmKomponen').attr('value', '');
	$('#formTitle').html('Tambah Data Komponen');
	$('#pencegahan, #deskripsi').html('');
	OPR_MODE = MODE_INSERT;
	request('next/komponen/id', {}, function(r){
		showForm();
		$('#idKomponen').attr('value', r);
	})
});

$('#resetBtn').click(function(){
	resetForm();
})

$('#saveBtn').click(function(){
	var values = getFormValues();
	for(var id in values){
		if(values[id] == '')
		{
			var div = $('#' + id).parents('.control-group');
			div.addClass('error');
			var span = $('<span>').addClass('help-inline').html('harus diisi').appendTo(
				$('.controls', div)
			);
			return false;
		}
	}

	var path = 'komponen/tambah';
	if(OPR_MODE == MODE_UPDATE)
		path = 'komponen/edit';

	request(path, values, function(r){
		if(!r.success)
		{
			alert(r.error);
			return;
		}

		alert('Data Berhasil disimpan', function(){
			showList();
			loadItems();
		});
	});
	return false;
});

</script>