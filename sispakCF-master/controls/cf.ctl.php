<?php
/**
 *  Copyright Konglie (lee@konglie.web.id)
 *
 * Aplikasi ini diberikan apa adanya, tanpa ada jaminan dalam bentuk apapun.
 * Perubahan dan distribusi kode diizinkan dalam bentuk apapun,
 * Namun HARUS DENGAN MENYERTAKAN INFORMASI HAK CIPTA INI.
 *
 */

function hitungCF($g){
	$kandidat = array();

	if(!is_array($g))
		return $kandidat;
	
	foreach($g as $id)
	{
		$kerusakan = cariKerusakanDariGejala($id);
		foreach($kerusakan as $p)
		{
			$idp = $p['id_komponen'];
			if(!isset($kandidat[$idp]))
				$kandidat[$idp] = array();
				
			$cf = cariCF($id, $idp);
			$kandidat[$idp][] = $cf;
		}
	}

	$cfAkhir = array();
	foreach($kandidat as $p => $c)
	{
		sort($c);
		$cfAkhir[$p] = calcTotCF($c) * 100; // jadikan persen
	}

	arsort($cfAkhir);
	return $cfAkhir;
}

function cariCF($idGejala, $idKomponen){
	$sql = "SELECT nilai_cf FROM relasi WHERE id_gejala = '$idGejala' AND id_komponen = '$idKomponen'";
	$res = getOneRow($sql);
	
	return $res['nilai_cf'];
}

function cariKerusakanDariGejala($idGejala){
	$sql = "SELECT * FROM relasi a JOIN komponen b ON a.id_komponen = b.id_komponen WHERE a.id_gejala = '$idGejala'";
	return getRows($sql);
}

function calcCF($x, $y)
{
	$t = $x + ($y * (1 - $x));
	return $t;
}

function calcTotCF($g)
{
	if(count($g) <= 1)
		return $g[0];	

	$cfIJ = null;
	$n = count($g);
	for($i = 0; $i < $n - 1; $i++)
	{
		$j = $i + 1;
		if($cfIJ == null)
			$cfIJ = $g[$i];
			
		$cfIJ = calcCF($cfIJ, $g[$j]);
	}
	
	return $cfIJ;
}

function getKerusakan($idKomponen){
	return getOneRow("SELECT * FROM komponen WHERE id_komponen = '$idKomponen'");
}

function gais_sispak_cf($keluhan = null){
	if($keluhan == null){
		$keluhan = explode(';', $_POST['selected']);
	}
	$hasil = hitungCF($keluhan);

	$res = array();
	foreach ($hasil as $idKomponen => $persentase) {
		$rsk = getKerusakan($idKomponen);
		$rsk['_PERSENTASE_'] = number_format($persentase, 2);
		$res[] = $rsk;
	}

	return $res;
}