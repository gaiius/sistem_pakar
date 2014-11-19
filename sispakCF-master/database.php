<?php
/**
 *  Copyright Konglie (lee@konglie.web.id)
 *
 * Aplikasi ini diberikan apa adanya, tanpa ada jaminan dalam bentuk apapun.
 * Perubahan dan distribusi kode diizinkan dalam bentuk apapun,
 * Namun HARUS DENGAN MENYERTAKAN INFORMASI HAK CIPTA INI.
 *
 */

//=====================DATABASE CONNECTION===================

$dbHost = 'localhost';
$dbUser = 'root';
$dbPass = '';
$dbName = 'sispakcf';

mysql_connect($dbHost, $dbUser, $dbPass) or die('<h1>Database Connection Error</h1>');
mysql_selectdb($dbName) or die('<h1>Database read Error</h1>');
//===================END DATABASE CONNECTION=================

function getRows($sql)
{
	$rows = array();
	$q = mysql_query($sql);
	if(!$q)
		return $rows;

	while($r = mysql_fetch_assoc($q))
		$rows[] = $r;

	return $rows;
}

function getOneRow($sql)
{
	$q = mysql_query($sql);
	if(!$q)
		return false;

	return mysql_fetch_assoc($q);
}

function executeQuery($sql)
{
	$s = explode(';', $sql);

	foreach($s as $sql)
	{
		if(trim($sql) == '')
			continue;

		$q = mysql_query($sql);
		if(!$q)
			return false;
	}
	return true;
}

function getNextAutoIncrement($table)
{
	$query = "SHOW TABLE STATUS WHERE name = '$table'";
	return $rows['Auto_increment'] + 1;
}
