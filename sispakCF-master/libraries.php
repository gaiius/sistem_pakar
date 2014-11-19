<?php
/**
 *  Copyright Konglie (lee@konglie.web.id)
 *
 * Aplikasi ini diberikan apa adanya, tanpa ada jaminan dalam bentuk apapun.
 * Perubahan dan distribusi kode diizinkan dalam bentuk apapun,
 * Namun HARUS DENGAN MENYERTAKAN INFORMASI HAK CIPTA INI.
 *
 */

require("database.php");

define('APP_NAME', 'Sispak Metode CF');
define('APP_VERSION', '0.1');
define('APP_FUNCTION_PREFIX', 'gais');
define('WEB_BASE_URL', '/sispakCF/');

global $SS;
session_start();
$SS =& $_SESSION['_sispak_app_'];
if(!is_array($SS))
	$SS = array();

// load controls
foreach(scandir('controls') as $ctl)
{
	if(preg_match("/\.ctl\.php$/", $ctl))
		include_once("controls/$ctl");
}

function toStr($obj)
{
        ob_start();
        print_r($obj);

        return ob_get_clean();
}

function args($idx = -1)
{
	$q = $_GET['q'];
	$q = explode('/', $q);

	if($idx < 0)
		return $q;

	return $q[$idx];
}

function servePage(){
	$q = $_GET['q'];
	$q = explode('/', $q);
	
	if(!isAjax() && $q[0] != 'static')
		outputPlain(fetch('mainPage'));
	
	serveAjax();
}

function pageBody()
{
	$q = $_GET['q'];
	if($q == '')
		$q = 'home';

	$q = explode('/', $q);
	$tpl = join('.', $q) . '.tpl.php';

	if(!loggedIn()){
		$tpl = 'login.php';
	}

	if(!file_exists('forms/' . $tpl))
		return '404.tpl.php';

	return $tpl;
}

function ajaxPage($main, $sub)
{
	$f = "$main.$sub";
	$page = fetch($f);
	
	exit(json_encode($page));
}

function serveAjax()
{
	$q = $_GET['q'];
	$q = explode('/', $q);
	if($q[0] == '')
		array_shift($q);
		
	$main = $q[0];
	$sub = $q[1];
	
	if($_POST['page'] == 1)
		ajaxPage($main, $sub);
		
		
	$handler = APP_FUNCTION_PREFIX . '_' . join('_', $q);
	if(!function_exists($handler))
		ajaxOutput("Permintaan tidak dimengerti $handler");
		
	$result = $handler();
	ajaxOutput($result);
}

function ajaxOutput($r)
{
	exit(json_encode($r));
}

function outputPlain($html)
{
	exit($html);
}

function fetch($tpl)
{
	$tpl = preg_replace("/\.$/", '', $tpl);
	$f = isAjax() ? "forms/$tpl.php" : "forms/$tpl.tpl.php";
	if(!file_exists($f))
		return "Halaman tidak ditemukan $tpl $f";
	
	ob_start();
	require($f);

	return ob_get_clean();
}
	
function isAjax()
{
	return $_SERVER['HTTP_X_REQUESTED_WITH'] == 'XMLHttpRequest';
}

function cleanUpPost()
{
	foreach($_POST as &$v) $v = mysql_escape_string(trim($v));
}

function formatTanggal($date)
{
	$t = strtotime($date);
	$months = array(
		'', "Januari", "Februari", "Maret", "April", "Mei",
                "Juni", "Juli", "Agustus", "September",
                "Oktober", "November", "Desember"
	);
	if($date === null)
	{
		array_shift($months);
		return $months;
	}
	
	$d = date('d', $t);
	$m = $months[date('n', $t)];
	$y = date('Y', $t);
	
	return "$d $m $y";
}

function idDayNames()
{
	return array('Ming', 'Sen', 'Sel', 'Rab', 'Kam', 'Jum', 'Sab');
}

function masterSequence()
{
	global $dbd;
	return $dbd->seqNextVal('mtable_seq');
}

function trxSequence()
{
	global $dbd;
	return $dbd->seqNextVal('trx_seq');
}

function uri($url = null)
{
	if($url == null)
		return WEB_BASE_URL;

	$url = preg_replace("/^\//", '', $url);

	return WEB_BASE_URL . $url;
}
