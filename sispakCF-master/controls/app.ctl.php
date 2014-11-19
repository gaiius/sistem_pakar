<?php

/**
 *  Copyright Konglie (lee@konglie.web.id)
 *
 * Aplikasi ini diberikan apa adanya, tanpa ada jaminan dalam bentuk apapun.
 * Perubahan dan distribusi kode diizinkan dalam bentuk apapun,
 * Namun HARUS DENGAN MENYERTAKAN INFORMASI HAK CIPTA INI.
 *
 */

// tampilkan halaman admin
// halaman ditaruh di folder forms/admin
function gais_admin(){
	return fetch("admin/{$_POST['page']}");
}

function gais_next_komponen_id(){
	$max = getOneRow("SELECT max(id_komponen) AS idk FROM komponen");
	$id = $max['idk'];
	$id = preg_replace("/[^0-9]/", '', $id);
	$id = $id + 1;

	return 'K' . str_pad($id, 4, '0', STR_PAD_LEFT);
}

function gais_komponen_tambah(){
	$id = gais_next_komponen_id();
	$nm = mysql_real_escape_string($_POST['nmKomponen']);
	$ds = mysql_real_escape_string($_POST['deskripsi']);
	$cg = mysql_real_escape_string($_POST['pencegahan']);

	$sql = "INSERT INTO komponen(id_komponen, nm_komponen, deskripsi, pencegahan) VALUES('$id', '$nm', '$ds', '$cg')";
	if(executeQuery($sql)){
		return array('success' => true);
	} else {
		return array('success' => false, 'error' => mysql_error());
	}
}

function gais_komponen_edit(){
	$id = mysql_real_escape_string($_POST['idKomponen']);
	$nm = mysql_real_escape_string($_POST['nmKomponen']);
	$ds = mysql_real_escape_string($_POST['deskripsi']);
	$cg = mysql_real_escape_string($_POST['pencegahan']);

	$sql = "UPDATE komponen SET nm_komponen = '$nm', deskripsi = '$ds', pencegahan = '$cg' WHERE id_komponen = '$id'";
	if(executeQuery($sql)){
		return array('success' => true, 'x'=> $sql);
	} else {
		return array('success' => false, 'error' => mysql_error());
	}
}

function gais_komponen_list(){
	//return getRows("SELECT * FROM komponen ORDER BY id_komponen ASC");
	$sql = "SELECT * FROM komponen ORDER BY id_komponen ASC";
        if(isset($_POST['groups']))
                $sql = "SELECT * FROM komponen GROUP BY nm_komponen ORDER BY id_komponen ASC";
        return getRows($sql);
}

function gais_komponen_hapus(){
	$id = mysql_real_escape_string($_POST['id_komponen']);
	$sql = "DELETE FROM komponen WHERE id_komponen = '$id'";
	if(executeQuery($sql))
		return "Data Berhasil dihapus";
	else
		return "Data Gagal dihapus: " + mysql_error();
}

// ============handphone
function gais_next_handphone_id(){
	$max = getOneRow("SELECT max(id_handphone) AS idk FROM handphone");
	$id = $max['idk'];
	$id = preg_replace("/[^0-9]/", '', $id);
	$id = $id + 1;

	return 'H' . str_pad($id, 4, '0', STR_PAD_LEFT);
}

function gais_handphone_tambah(){
	$id = gais_next_handphone_id();
	$nm = mysql_real_escape_string($_POST['nmHandphone']);

	$sql = "INSERT INTO handphone(id_handphone, nm_handphone) VALUES('$id', '$nm')";
	if(executeQuery($sql)){
		return array('success' => true);
	} else {
		return array('success' => false, 'error' => mysql_error());
	}
}

function gais_handphone_edit(){
	$id = mysql_real_escape_string($_POST['idHandphone']);
	$nm = mysql_real_escape_string($_POST['nmHandphone']);

	$sql = "UPDATE handphone SET nm_handphone = '$nm' WHERE id_handphone = '$id'";
	if(executeQuery($sql)){
		return array('success' => true, 'x'=> $sql);
	} else {
		return array('success' => false, 'error' => mysql_error());
	}
}

function gais_handphone_list(){
	return getRows("SELECT * FROM handphone ORDER BY id_handphone ASC");
}

function gais_handphone_hapus(){
	$id = mysql_real_escape_string($_POST['id_handphone']);
	$sql = "DELETE FROM handphone WHERE id_handphone = '$id'";
	if(executeQuery($sql))
		return "Data Berhasil dihapus";
	else
		return "Data Gagal dihapus: " + mysql_error();
}
//============

//=================gejala
function gais_next_pertanyaan_id(){
	$max = getOneRow("SELECT max(id_gejala) AS idk FROM gejala");
	$id = $max['idk'];
	$id = preg_replace("/[^0-9]/", '', $id);
	$id = $id + 1;

	return 'P' . str_pad($id, 4, '0', STR_PAD_LEFT);
}

function gais_pertanyaan_tambah(){
	$idkmp = mysql_real_escape_string($_POST['idHandphone']);
	$id = gais_next_pertanyaan_id();
	$nm = mysql_real_escape_string($_POST['nmPertanyaan']);
	$desc = mysql_real_escape_string($_POST['deskripsi']);

	$sql = "INSERT INTO gejala(id_handphone, id_gejala, nm_gejala, deskripsi)
	    VALUES('$idkmp', '$id', '$nm', '$desc')";
	if(executeQuery($sql)){
		return array('success' => true);
	} else {
		return array('success' => false, 'error' => mysql_error());
	}
}

function gais_pertanyaan_list(){
	$filter = '';
	if($_POST['handphone'] != ''){
		$idk = mysql_real_escape_string($_POST['handphone']);
		$filter = "WHERE a.id_handphone = '$idk'";
	}

	return getRows("
		SELECT
			*
		FROM
			gejala a
		LEFT JOIN handphone b ON a.id_handphone = b.id_handphone
		$filter
		ORDER BY a.id_gejala ASC");
}

function gais_pertanyaan_hapus(){
	$id = mysql_real_escape_string($_POST['id_gejala']);
	$sql = "DELETE FROM gejala WHERE id_gejala = '$id'";
	if(executeQuery($sql))
		return "Data Berhasil dihapus";
	else
		return "Data Gagal dihapus: " + mysql_error();
}

function gais_pertanyaan_edit(){
	$idkmp = mysql_real_escape_string($_POST['idHandphone']);
	$id = mysql_real_escape_string($_POST['idPertanyaan']);
	$nm = mysql_real_escape_string($_POST['nmPertanyaan']);
	$desc = mysql_real_escape_string($_POST['deskripsi']);

	$sql = "UPDATE gejala SET
			nm_gejala = '$nm',
			id_handphone = '$idkmp',
			deskripsi = '$desc'
		WHERE id_gejala = '$id'";
	if(executeQuery($sql)){
		return array('success' => true, 'x'=> $sql);
	} else {
		return array('success' => false, 'error' => mysql_error());
	}
}

//===================
//=================relasi
function gais_relasi_list(){
	return getRows("SELECT
		*
		FROM relasi a
		LEFT JOIN gejala b ON a.id_gejala = b.id_gejala
		LEFT JOIN komponen c ON a.id_komponen = c.id_komponen
		LEFT JOIN handphone d ON b.id_handphone = d.id_handphone
	");
}

function gais_relasi_tambah(){
	$idPertanyaan = mysql_real_escape_string($_POST['idPertanyaan']);
	$idKomponen = mysql_real_escape_string($_POST['idKomponen']);
	$cf = mysql_real_escape_string($_POST['cf']);

	$sql = "INSERT INTO relasi(id_gejala, id_komponen, nilai_cf) VALUES('$idPertanyaan', '$idKomponen', '$cf')";
	if(executeQuery($sql)){
		return array('success' => true);
	} else {
		return array('success' => false, 'error' => mysql_error());
	}
}

function gais_relasi_edit(){
	$idPertanyaan = mysql_real_escape_string($_POST['idPertanyaan']);
	$idKomponen = mysql_real_escape_string($_POST['idKomponen']);
	$cf = mysql_real_escape_string($_POST['cf']);

	$sql = "UPDATE relasi SET nilai_cf = '$cf' WHERE id_gejala = '$idPertanyaan' AND id_komponen = '$idKomponen'";
	if(executeQuery($sql)){
		return array('success' => true);
	} else {
		return array('success' => false, 'error' => mysql_error());
	}
}

function gais_relasi_hapus(){
	$idPertanyaan = mysql_real_escape_string($_POST['id_gejala']);
	$idKomponen = mysql_real_escape_string($_POST['id_komponen']);
	$sql = "DELETE FROM relasi WHERE id_gejala = '$idPertanyaan' AND id_komponen = '$idKomponen'";
	if(executeQuery($sql))
		return "Data Berhasil dihapus";
	else
		return "Data Gagal dihapus: " + mysql_error();
}

function gais_password_change(){
	global $SS;

	$pw = md5($_POST['pw']);
	$oldpw = md5($_POST['oldpw']);

	if($pw == $oldpw){
		return array(
			'success' => false,
			'message' => 'Password baru dan lama sama'
		);
	}

	$user = $SS['uinfo']['username'];
	$sql = "UPDATE user SET password = '$pw' WHERE username = '$user' AND password = '$oldpw'";
	executeQuery($sql);
	if(mysql_affected_rows() > 0){
		return array(
			'success' => true
		);
	} else {
		return array(
			'success' => false,
			'message' => 'Password lama Salah'
		);
	}
}
