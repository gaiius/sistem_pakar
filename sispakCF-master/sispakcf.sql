-- phpMyAdmin SQL Dump
-- version 3.4.10.1deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Waktu pembuatan: 08. Februari 2014 jam 14:47
-- Versi Server: 5.5.22
-- Versi PHP: 5.3.10-1ubuntu3

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `sispakcf`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `gejala`
--

CREATE TABLE IF NOT EXISTS `gejala` (
  `id_gejala` varchar(5) NOT NULL,
  `id_handphone` varchar(5) NOT NULL,
  `nm_gejala` varchar(128) NOT NULL,
  `deskripsi` text NOT NULL,
  `ya` varchar(5) NOT NULL,
  `tidak` varchar(10) NOT NULL,
  PRIMARY KEY (`id_gejala`,`id_handphone`),
  KEY `id_handphone` (`id_handphone`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `gejala`
--

INSERT INTO `gejala` (`id_gejala`, `id_handphone`, `nm_gejala`, `deskripsi`, `ya`, `tidak`) VALUES
('B0010', 'A0010', 'Nama gejala dengan kode B0010', 'Deskripsi gejala dengan kode B0010', 'C0010', 'B0020'),
('B0020', 'A0010', 'Nama gejala dengan kode B0020', 'Deskripsi gejala dengan kode B0020', 'C0020', 'B0030'),
('B0030', 'A0010', 'Nama gejala dengan kode B0030', 'Deskripsi gejala dengan kode B0030', 'C0060', 'B0040'),
('B0040', 'A0010', 'Nama gejala dengan kode B0040', 'Deskripsi gejala dengan kode B0040', 'C0080', 'B0050'),
('B0050', 'A0010', 'Nama gejala dengan kode B0050', 'Deskripsi gejala dengan kode B0050', 'C0110', ''),
('B0060', 'A0020', 'Nama gejala dengan kode B0060', 'Deskripsi gejala dengan kode B0060', 'C0120', 'B0070'),
('B0070', 'A0020', 'Nama gejala dengan kode B0070', 'Deskripsi gejala dengan kode B0070', 'C0160', 'B0080'),
('B0080', 'A0020', 'Nama gejala dengan kode B0080', 'Deskripsi gejala dengan kode B0080', 'C0200', ''),
('C0010', 'A0010', 'Nama gejala dengan kode C0010', 'Deskripsi gejala dengan kode C0010', 'D0010', ''),
('C0020', 'A0010', 'Nama gejala dengan kode C0020', 'Deskripsi gejala dengan kode C0020', 'D0020', 'C0030'),
('C0030', 'A0010', 'Nama gejala dengan kode C0030', 'Deskripsi gejala dengan kode C0030', 'D0030', 'C0040'),
('C0040', 'A0010', 'Nama gejala dengan kode C0040', 'Deskripsi gejala dengan kode C0040', 'D0050', 'C0050'),
('C0050', 'A0010', 'Nama gejala dengan kode C0050', 'Deskripsi gejala dengan kode C0050', 'D0060', ''),
('C0060', 'A0010', 'Nama gejala dengan kode C0060', 'Deskripsi gejala dengan kode C0060', 'D0070', 'C0070'),
('C0070', 'A0010', 'Nama gejala dengan kode C0070', 'Deskripsi gejala dengan kode C0070', 'D0080', ''),
('C0080', 'A0010', 'Nama gejala dengan kode C0080', 'Deskripsi gejala dengan kode C0080', 'D0090', 'C0090'),
('C0090', 'A0010', 'Nama gejala dengan kode C0090', 'Deskripsi gejala dengan kode C0090', 'C0100', 'D0100'),
('C0100', 'A0010', 'Nama gejala dengan kode C0100', 'Deskripsi gejala dengan kode C0100', 'D0110', ''),
('C0110', 'A0010', 'Nama gejala dengan kode C0110', 'Deskripsi gejala dengan kode C0110', 'D0120', ''),
('C0120', 'A0020', 'Nama gejala dengan kode C0120', 'Deskripsi gejala dengan kode C0120', 'D0140', 'C0130'),
('C0130', 'A0020', 'Nama gejala dengan kode C0130', 'Deskripsi gejala dengan kode C0130', 'D0160', 'C0140'),
('C0140', 'A0020', 'Nama gejala dengan kode C0140', 'Deskripsi gejala dengan kode C0140', 'D0190', 'C0150'),
('C0150', 'A0020', 'Nama gejala dengan kode C0150', 'Deskripsi gejala dengan kode C0150', 'D0200', ''),
('C0160', 'A0020', 'Nama gejala dengan kode C0160', 'Deskripsi gejala dengan kode C0160', 'D0210', 'C0170'),
('C0170', 'A0020', 'Nama gejala dengan kode C0170', 'Deskripsi gejala dengan kode C0170', 'D0230', 'C0180'),
('C0180', 'A0020', 'Nama gejala dengan kode C0180', 'Deskripsi gejala dengan kode C0180', 'D0240', 'C0190'),
('C0190', 'A0020', 'Nama gejala dengan kode C0190', 'Deskripsi gejala dengan kode C0190', 'D0250', ''),
('C0200', 'A0020', 'Nama gejala dengan kode C0200', 'Deskripsi gejala dengan kode C0200', 'D0260', ''),
('D0010', 'A0010', 'Nama gejala dengan kode D0010', 'Deskripsi gejala dengan kode D0010', 'E0010', ''),
('D0020', 'A0010', 'Nama gejala dengan kode D0020', 'Deskripsi gejala dengan kode D0020', 'E0020', 'E0030'),
('D0030', 'A0010', 'Nama gejala dengan kode D0030', 'Deskripsi gejala dengan kode D0030', 'E0040', 'D0040'),
('D0040', 'A0010', 'Nama gejala dengan kode D0040', 'Deskripsi gejala dengan kode D0040', 'E0050', ''),
('D0050', 'A0010', 'Nama gejala dengan kode D0050', 'Deskripsi gejala dengan kode D0050', 'E0060', 'E0070'),
('D0060', 'A0010', 'Nama gejala dengan kode D0060', 'Deskripsi gejala dengan kode D0060', 'E0080', ''),
('D0070', 'A0010', 'Nama gejala dengan kode D0070', 'Deskripsi gejala dengan kode D0070', 'E0090', 'E0100'),
('D0080', 'A0010', 'Nama gejala dengan kode D0080', 'Deskripsi gejala dengan kode D0080', 'E0110', ''),
('D0090', 'A0010', 'Nama gejala dengan kode D0090', 'Deskripsi gejala dengan kode D0090', 'E0120', ''),
('D0100', 'A0010', 'Nama gejala dengan kode D0100', 'Deskripsi gejala dengan kode D0100', 'E0130', ''),
('D0110', 'A0010', 'Nama gejala dengan kode D0110', 'Deskripsi gejala dengan kode D0110', 'E0140', ''),
('D0120', 'A0010', 'Nama gejala dengan kode D0120', 'Deskripsi gejala dengan kode D0120', 'E0150', ''),
('D0140', 'A0020', 'Nama gejala dengan kode D0140', 'Deskripsi gejala dengan kode D0140', 'E0160', 'E0170'),
('D0150', 'A0020', 'Nama gejala dengan kode D0150', 'Deskripsi gejala dengan kode D0150', 'E0180', ''),
('D0160', 'A0020', 'Nama gejala dengan kode D0160', 'Deskripsi gejala dengan kode D0160', 'E0190', ''),
('D0170', 'A0020', 'Nama gejala dengan kode D0170', 'Deskripsi gejala dengan kode D0170', 'E0200', ''),
('D0180', 'A0020', 'Nama gejala dengan kode D0180', 'Deskripsi gejala dengan kode D0180', 'E0210', ''),
('D0190', 'A0020', 'Nama gejala dengan kode D0190', 'Deskripsi gejala dengan kode D0190', 'E0220', ''),
('D0200', 'A0020', 'Nama gejala dengan kode D0200', 'Deskripsi gejala dengan kode D0200', 'E0230', ''),
('D0210', 'A0020', 'Nama gejala dengan kode D0210', 'Deskripsi gejala dengan kode D0210', 'E0240', ''),
('D0220', 'A0020', 'Nama gejala dengan kode D0220', 'Deskripsi gejala dengan kode D0220', 'E0250', ''),
('D0230', 'A0020', 'Nama gejala dengan kode D0230', 'Deskripsi gejala dengan kode D0230', 'E0260', 'E0270'),
('D0240', 'A0020', 'Nama gejala dengan kode D0240', 'Deskripsi gejala dengan kode D0240', 'E0280', ''),
('D0250', 'A0020', 'Nama gejala dengan kode D0250', 'Deskripsi gejala dengan kode D0250', 'E0290', ''),
('D0260', 'A0020', 'Nama gejala dengan kode D0260', 'Deskripsi gejala dengan kode D0260', 'E0300', ''),
('E0010', 'A0010', 'Nama gejala dengan kode E0010', 'Deskripsi gejala dengan kode E0010', '', ''),
('E0020', 'A0010', 'Nama gejala dengan kode E0020', 'Deskripsi gejala dengan kode E0020', '', ''),
('E0030', 'A0010', 'Nama gejala dengan kode E0030', 'Deskripsi gejala dengan kode E0030', '', ''),
('E0040', 'A0010', 'Nama gejala dengan kode E0040', 'Deskripsi gejala dengan kode E0040', '', ''),
('E0050', 'A0010', 'Nama gejala dengan kode E0050', 'Deskripsi gejala dengan kode E0050', '', ''),
('E0060', 'A0010', 'Nama gejala dengan kode E0060', 'Deskripsi gejala dengan kode E0060', '', ''),
('E0070', 'A0010', 'Nama gejala dengan kode E0070', 'Deskripsi gejala dengan kode E0070', '', ''),
('E0080', 'A0010', 'Nama gejala dengan kode E0080', 'Deskripsi gejala dengan kode E0080', '', ''),
('E0090', 'A0010', 'Nama gejala dengan kode E0090', 'Deskripsi gejala dengan kode E0090', '', ''),
('E0100', 'A0010', 'Nama gejala dengan kode E0100', 'Deskripsi gejala dengan kode E0100', '', ''),
('E0110', 'A0010', 'Nama gejala dengan kode E0110', 'Deskripsi gejala dengan kode E0110', '', ''),
('E0120', 'A0010', 'Nama gejala dengan kode E0120', 'Deskripsi gejala dengan kode E0120', '', ''),
('E0130', 'A0010', 'Nama gejala dengan kode E0130', 'Deskripsi gejala dengan kode E0130', '', ''),
('E0140', 'A0010', 'Nama gejala dengan kode E0140', 'Deskripsi gejala dengan kode E0140', '', ''),
('E0150', 'A0010', 'Nama gejala dengan kode E0150', 'Deskripsi gejala dengan kode E0150', '', ''),
('E0160', 'A0020', 'Nama gejala dengan kode E0160', 'Deskripsi gejala dengan kode E0160', '', ''),
('E0170', 'A0020', 'Nama gejala dengan kode E0170', 'Deskripsi gejala dengan kode E0170', '', ''),
('E0180', 'A0020', 'Nama gejala dengan kode E0180', 'Deskripsi gejala dengan kode E0180', '', ''),
('E0190', 'A0020', 'Nama gejala dengan kode E0190', 'Deskripsi gejala dengan kode E0190', '', ''),
('E0200', 'A0020', 'Nama gejala dengan kode E0200', 'Deskripsi gejala dengan kode E0200', '', ''),
('E0210', 'A0020', 'Nama gejala dengan kode E0210', 'Deskripsi gejala dengan kode E0210', '', ''),
('E0220', 'A0020', 'Nama gejala dengan kode E0220', 'Deskripsi gejala dengan kode E0220', '', ''),
('E0230', 'A0020', 'Nama gejala dengan kode E0230', 'Deskripsi gejala dengan kode E0230', '', ''),
('E0240', 'A0020', 'Nama gejala dengan kode E0240', 'Deskripsi gejala dengan kode E0240', '', ''),
('E0250', 'A0020', 'Nama gejala dengan kode E0250', 'Deskripsi gejala dengan kode E0250', '', ''),
('E0260', 'A0020', 'Nama gejala dengan kode E0260', 'Deskripsi gejala dengan kode E0260', '', ''),
('E0270', 'A0020', 'Nama gejala dengan kode E0270', 'Deskripsi gejala dengan kode E0270', '', ''),
('E0280', 'A0020', 'Nama gejala dengan kode E0280', 'Deskripsi gejala dengan kode E0280', '', ''),
('E0290', 'A0020', 'Nama gejala dengan kode E0290', 'Deskripsi gejala dengan kode E0290', '', ''),
('E0300', 'A0020', 'Nama gejala dengan kode E0300', 'Deskripsi gejala dengan kode E0300', '', '');

-- --------------------------------------------------------

--
-- Struktur dari tabel `handphone`
--

CREATE TABLE IF NOT EXISTS `handphone` (
  `id_handphone` varchar(5) NOT NULL,
  `nm_handphone` varchar(128) NOT NULL,
  PRIMARY KEY (`id_handphone`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `handphone`
--

INSERT INTO `handphone` (`id_handphone`, `nm_handphone`) VALUES
('A0010', 'Nama Handphone dengan kode A0010'),
('A0020', 'Nama Handphone dengan kode A0020');

-- --------------------------------------------------------

--
-- Struktur dari tabel `komponen`
--

CREATE TABLE IF NOT EXISTS `komponen` (
  `id_komponen` varchar(5) NOT NULL,
  `nm_komponen` varchar(128) NOT NULL,
  `deskripsi` text NOT NULL,
  `pencegahan` text NOT NULL,
  PRIMARY KEY (`id_komponen`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `komponen`
--

INSERT INTO `komponen` (`id_komponen`, `nm_komponen`, `deskripsi`, `pencegahan`) VALUES
('K0010', 'PMIC', 'PMIC kepanjangan dari Power Management Integrated Circuit adalah salah satu IC yang berfungsi untuk mengelola kebutuhan daya dari sistem host. Tugas PMIC termasuk ke dalam battery management, voltage regulation, dan charging function.', '1. Cabutlah kabel charger handphone jika baterai handphone sudah terisi penuh. Jangan terlalu lama di-charge akan membuat handphone over voltage.\n\n2. Gunakanlah charger sesuai dengan jenis handphone. Jangan biasakan menggunakan charger lain walaupun ada daya yang masuk atau satu merk, karena daya yang keluar dari setiap charger berbeda dapat menyebabkan over voltage.\n\n3. Jika anda sedang mencharge baterei, sebaiknya ponsel dalam keadaan mati. Agar handphone dapat mengisi daya dengan sempurna tidak terganggu dengan fungsi lain yang membutuhkan daya sehingga terjadi pertukaran arus masuk.'),
('K0020', 'Oscillator', 'Komponen ini adalah sebuah osilator jenis kristal yang berfungsi menghasilkan satu gelombang dengan frekuensi 32768 Hz. Bila komponen ini bermasalah bisa mengakibatkan tidak normalnya handphone, seperti handphone tidak bisa menyala, masalah pada jam hanphone, dan lain lain. ', '1. Letakkan handphone pada suhu stabil ruangan 20-25 derajat celcius. 2. Jangan letakkan handphone pada alat elektronik seperti laptop atau pc karena dapat mengganggu frekuensi clock dari oscilator crystal.'),
('K0030', 'PBA Main', 'PBA Main (Print Board Assembly) Main adalah adalah sebuah papan yang penuh dengan sirkuit dari logam yang menghubungkan komponen elektronik satu sama lain tanpa kabel.', '1. Jangan letakkan di tempat lembab atau terlalu panas.\n2. Jangan sampai terkena cairan kecuali thinner karena akan menyebabkan korosi sehingga tidak dapat menyalurkan arus pada masing-masing komponen.'),
('K0040', 'LCD', 'LCD (Liquid Cyrstal Display) adalah adalah suatu jenis media tampilan yang menggunakan kristal cair sebagai penampil utama. Pada LCD  terdapat banyak sekali titik cahaya (piksel) yang terdiri dari satu buah kristal cair sebagai sebuah titik cahaya. Walau disebut sebagai titik cahaya, kristal cair ini tidak memancarkan cahaya sendiri. Sumber cahaya di dalam sebuah perangkat LCD adalah lampu neon berwarna putih di bagian belakang susunan kristal cair.', '1. Jangan sampai terbentur, kedudukan, atau jatuh karena lcd sangat tipis dapat menyebabkan lcd pecah.\n2. Jangan terkena cairan apapun.\n3. Jangan letakkan di tempat lembab karena bisa menyebabkan udara menjadi air dan lcd pun akan berembun.\n4. Gunakan anti gores untuk mencegah terjadinya gores pada layar handphone anda.'),
('K0050', 'IC Flash', 'IC Flash adalah salah satu integrated circuit yang berfungsi sebagai tempat menyimpan program-program yang terdapat di handphone secara permanen dan tidak dapat hilang walaupun daya dimatikan.  IC Flash juga disebut IC. EEPROM (Electrically Erasable Programmable Read Only Memory). \n\n\n', '1. Hanya install program oficially dan aplikasi sesuai dengan jenis handphone.\n2. Segera recharging handphone anda jika sudah ada tanda-tanda akan habis daya.'),
('K0060', 'Battery', 'Battery adalah alat listrik-kimiawi yang menyimpan energi dan mengeluarkan tenaganya dalam bentuk listrik. Sebuah baterai biasanya terdiri dari tiga komponen penting, yaitu batang karbon sebagai anode (kutub positif baterai, seng (Zn)) sebagai katode (kutub negatif baterai), pasta sebagai elektrolit (penghantar).', '1. Janganlah membiasakan menunggu baterai habis saat akan mengisi kembali baterai handphone karena akan membuat baterai cepat rusak. Jika baterai sudah terlihat hampir kosong, cepatlah mengisi/charge lagi. 2. Janganlah membiasakan menggunakan charger yang sembarangan walaupun dalam satu merk. Hal ini karena daya pada charger baterai itu berbeda-beda sehingga akan mengakibatkan baterai handphone cepat rusak. Beda daya pada baterai handphone sangat mempengaruhi kapasitas baterai saat melakukan pengisian. 3. Cabutlah kabel charger handphone jika baterai handphone sudah terisi penuh. Jangan terlalu lama di-charge karena akan membuat baterai bocor dan baterai jadi cepat habis. 4. Jika anda sedang mengecas baterei, sebaiknya ponsel dalam keadaan mati. Jika ponsel menyala akan terjadi arus keluar masuk secara bergantian yang akibatnya akan merusak molekul dalam baterai. Ini juga akan mengakibatkan terjadinya memori effect pada baterai. Apalagi saat kita mencharge, lantas ponsel digunakan untuk koneksi, itu akan menambah beban pada proses kimia dalam baterai.'),
('K0070', 'IF Connector', 'singkatan dari InterFace connector berfungsi sebagai penghubung antara handphone dengan media lainnya, misal adaptor, earphone, atau usb cable.', '1. Gunakan selalu adaptor ataupun usb cable yang sesuai dengan jenis handphonenya.\n2. Jaga kebersihan handphone karena if connector rentan terkena cairan atau debu.\n3. Jangan pernah mencongkel if connector karena terdapat pin-pin yang halus yang mudah patah.'),
('K0080', 'IC Call Processor', 'IC Call Processor adalah salah satu integrated circuit yang berfungsi mengolah semua kegiatan fungsi telephone/lainnya (yang berhubungan dengan sinyal)', '1. Jangan letakkan handphone dengan alat elektronik seperti radio, pc, ataupun laptop.\n2. Jangan men-charge handphone hingga semalaman.'),
('K0090', 'Speaker', 'adalah transduser yang mengubah sinyal elektrik ke frekuensi audio (suara) dengan cara menggetarkan komponennya yang berbentuk membran untuk menggetarkan udara sehingga terjadilah gelombang suara sampai di kendang telinga kita dan dapat kita dengar sebagai suara.', '1. Jangan mendengarkan suara dengan volume maksimal secara terus menerus.\n2. Jaga kebersihan handphone anda karena debu atau cairan dapat merusak handphone anda ataupun menghalangi keluaran suasa sehingga menjadi kecil.'),
('K0100', 'Audio Receiver', 'Salah satu outputan yang berupa suara dari lawan bicara pada saat melakukan panggilan.', '1. Jaga kebersihan handphone anda, karena debu ataupun cairan dapat merusak handphone anda, atau menghalangi keluaran suara sehingga saura yang kita dengan menjadi kecil.\n2. Jangan gunakan dengan volume maksimal secara terus menerus.'),
('K0110', 'Touch Panel', 'Biasa disebut juga touchscreen atau layar sentuh merupakan ebuah perangkat input  yang bekerja dengan adanya sentuhan tampilan layar menggunakan jari atau pena digital. Antarmuka layar sentuh, di mana pengguna mengoperasikan sistem komputer dengan menyentuh gambar atau tulisan di layar itu sendiri, merupakan cara yang paling mudah untuk mengoperasikan perangkat dan kini semakin banyak digunakan dalam berbagai aplikasi.', '1. Selalu sentuh layar dengan pena atau tangan yang bersih.\n2. Jangan sampai tertekan karena tekanan tinggi seperti kedudukan atau kebentur dapat menyebabkan pecah.\n3. Selalu gunakan pena ataupun perangkat lain yang sesuai dengan jenis handphonennya.'),
('K0120', 'IC RF', 'Merupakan komponen pengolah sinyal yang masuk atau yang keluar dan memperkuat sinyal pada selisih frekuensi. Fungsi lain IF IC adalah sebagai mikser dan detektor. Sebagai mikser, IF IC mencampurkan sinyal operator dengan sinyal ponsel. Sebagai detektor, IF IC memangkas sinyal pembawa dan sinyal audio yang kemudian dialirkan ke IC audio sehingga terjadi selisih. Selisih tersebut diperkuat oleh IF IC sebagai detektor untuk memangkas sinyal pembawa dan sinyal audio yang kemudian dialirkan ke IC audio. Jika komponen ini rusak, ponsel tidak dapat menerima sinyal.', '1. Gunakan Selalu Chasing asli dari handphone.\n2. Jangan terlalu sering gonta-ganti chasing dapat menyebabkan switch antena rusak dan kemudian berpengaruh terhadap ic rf'),
('K0130', 'IC NFC', 'kepanjangan dari Integrated Circuit Near Field Communication merupakan ic yang diartikan secara harfiah sebagai Komunikasi Medan Dekat adalah seperangkat teknologi konektivitas nirkabel berbasis teknologi Radio Frequency Identification (RFID) yang menggunakan induksi medan magnet untuk memungkinkan komunikasi antar perangkat elektronik dalam jarak yang dekat.', 'Jangan sampai tertekan, kena air atapun jatuh karena dapat menyebabkan kerusakan pada handphone.'),
('K0140', 'Camera', 'Salah satu outputan yang berupa suara dari lawan bicara pada saat melakukan panggilan.', '1. Jangan terlalu sering menggunakan flash camera karena dapat merusak optik camera.\n2. Jangan tertekan, tekanan terlalu tinggi dapat merusak optik kamera juga.\n3. Jangan mengcapture gambar dengan sinar matahari terlalu tajam, karena dapat merusak optik.'),
('K0150', 'Slot Sim card ', 'Slot Sim Card adalah tempat menyisipkan sim card biasanya terbuat dari plastik dan besi.', '1. Masukkan sim card sesuai dengan ukuran slotnya dan petunjuk penempatan yang terdapat di chasing handphone.\n2. Jangan congkel slot menggunakan benda tajam karena dapat mengakibatkan pin-pin dalam slot patah.\n3. Hanya masukkan sim card bukan micro sd atau lainnya, karena jika sudah masuk untuk mengeluarkannya akan mengakibatkan pin-pin dalam slot patah.'),
('K0160', 'Battery', 'Battery adalah alat listrik-kimiawi yang menyimpan energi dan mengeluarkan tenaganya dalam bentuk listrik. Sebuah baterai biasanya terdiri dari tiga komponen penting, yaitu batang karbon sebagai anode (kutub positif baterai, seng (Zn)) sebagai katode (kutub negatif baterai), pasta sebagai elektrolit (penghantar).', '1. Janganlah membiasakan menunggu baterai habis saat akan mengisi kembali baterai handphone karena akan membuat baterai cepat rusak. Jika baterai sudah terlihat hampir kosong, cepatlah mengisi/charge lagi. 2. Janganlah membiasakan menggunakan charger yang sembarangan walaupun dalam satu merk. Hal ini karena daya pada charger baterai itu berbeda-beda sehingga akan mengakibatkan baterai handphone cepat rusak. Beda daya pada baterai handphone sangat mempengaruhi kapasitas baterai saat melakukan pengisian. 3. Cabutlah kabel charger handphone jika baterai handphone sudah terisi penuh. Jangan terlalu lama di-charge karena akan membuat baterai bocor dan baterai jadi cepat habis. 4. Jika anda sedang mengecas baterei, sebaiknya ponsel dalam keadaan mati. Jika ponsel menyala akan terjadi arus keluar masuk secara bergantian yang akibatnya akan merusak molekul dalam baterai. Ini juga akan mengakibatkan terjadinya memori effect pada baterai. Apalagi saat kita mencharge, lantas ponsel digunakan untuk koneksi, itu akan menambah beban pada proses kimia dalam baterai.'),
('K0170', 'IF Connector', 'singkatan dari InterFace connector berfungsi sebagai penghubung antara handphone dengan media lainnya, misal adaptor, earphone, atau usb cable.', '1. Gunakan selalu adaptor ataupun usb cable yang sesuai dengan jenis handphonenya.\n2. Jaga kebersihan handphone karena if connector rentan terkena cairan atau debu.\n3. Jangan pernah mencongkel if connector karena terdapat pin-pin yang halus yang mudah patah.'),
('K0180', 'PMIC', 'PMIC kepanjangan dari Power Management Integrated Circuit adalah salah satu IC yang berfungsi untuk mengelola kebutuhan daya dari sistem host. Tugas PMIC termasuk ke dalam battery management, voltage regulation, dan charging function.', '1. Cabutlah kabel charger handphone jika baterai handphone sudah terisi penuh. Jangan terlalu lama di-charge akan membuat handphone over voltage. 2. Gunakanlah charger sesuai dengan jenis handphone. Jangan biasakan menggunakan charger lain walaupun ada daya yang masuk atau satu merk, karena daya yang keluar dari setiap charger berbeda dapat menyebabkan over voltage. 3. Jika anda sedang mencharge baterei, sebaiknya ponsel dalam keadaan mati. Agar handphone dapat mengisi daya dengan sempurna tidak terganggu dengan fungsi lain yang membutuhkan daya sehingga terjadi pertukaran arus masuk.'),
('K0190', 'PBA Main ', 'PBA Main (Print Board Assembly) Main adalah adalah sebuah papan yang penuh dengan sirkuit dari logam yang menghubungkan komponen elektronik satu sama lain tanpa kabel.', '1. Jangan letakkan di tempat lembab atau terlalu panas. 2. Jangan sampai terkena cairan kecuali thinner karena akan menyebabkan korosi sehingga tidak dapat menyalurkan arus pada masing-masing komponen.'),
('K0200', 'LCD', '	LCD (Liquid Cyrstal Display) adalah adalah suatu jenis media tampilan yang menggunakan kristal cair sebagai penampil utama. Pada LCD terdapat banyak sekali titik cahaya (piksel) yang terdiri dari satu buah kristal cair sebagai sebuah titik cahaya. Walau disebut sebagai titik cahaya, kristal cair ini tidak memancarkan cahaya sendiri. Sumber cahaya di dalam sebuah perangkat LCD adalah lampu neon berwarna putih di bagian belakang susunan kristal cair.', '1. Jangan sampai terbentur, kedudukan, atau jatuh karena lcd sangat tipis dapat menyebabkan lcd pecah. 2. Jangan terkena cairan apapun. 3. Jangan letakkan di tempat lembab karena bisa menyebabkan udara menjadi air dan lcd pun akan berembun. 4. Gunakan anti gores untuk mencegah terjadinya gores pada layar handphone anda. '),
('K0210', 'IC Flash', 'IC Flash adalah salah satu integrated circuit yang berfungsi sebagai tempat menyimpan program-program yang terdapat di handphone secara permanen dan tidak dapat hilang walaupun daya dimatikan. IC Flash juga disebut IC. EEPROM (Electrically Erasable Programmable Read Only Memory). ', '1. Hanya install program oficially dan aplikasi sesuai dengan jenis handphone. 2. Segera recharging handphone anda jika sudah ada tanda-tanda akan habis daya.'),
('K0220', 'Oscillator ', 'Komponen ini adalah sebuah osilator jenis kristal yang berfungsi menghasilkan satu gelombang dengan frekuensi 32768 Hz. Bila komponen ini bermasalah bisa mengakibatkan tidak normalnya handphone, seperti handphone tidak bisa menyala, masalah pada jam hanphone, dan lain lain.\n', '1. Letakkan handphone pada suhu stabil ruangan 20-25 derajat celcius. \n2. Jangan letakkan handphone pada alat elektronik seperti laptop atau pc karena dapat mengganggu frekuensi clock dari oscilator crystal.'),
('K0230', 'IC Call Processor', 'IC Call Processor adalah salah satu integrated circuit yang berfungsi mengolah semua kegiatan fungsi telephone/lainnya (yang berhubungan dengan sinyal)', '1. Jangan letakkan handphone dengan alat elektronik seperti radio, pc, ataupun laptop. 2. Jangan men-charge handphone hingga semalaman.'),
('K0240', 'IC RF', 'Biasa disebut juga touchscreen atau layar sentuh merupakan ebuah perangkat input  yang bekerja dengan adanya sentuhan tampilan layar menggunakan jari atau pena digital. Antarmuka layar sentuh, di mana pengguna mengoperasikan sistem komputer dengan menyentuh gambar atau tulisan di layar itu sendiri, merupakan cara yang paling mudah untuk mengoperasikan perangkat dan kini semakin banyak digunakan dalam berbagai aplikasi.', '1. Selalu sentuh layar dengan pena atau tangan yang bersih.\n2. Jangan sampai tertekan karena tekanan tinggi seperti kedudukan atau kebentur dapat menyebabkan pecah.\n3. Selalu gunakan pena ataupun perangkat lain yang sesuai dengan jenis handphonennya.'),
('K0250', 'IC Bluetooth/ Wifi', 'adalah komponen yang digunakan sebagai otak perangkat yang mengatur fungsi dari bluetooth dan wifi.', '1. Gunakan pada jaringan wifi terpercaya karena dapat menyebabkan rentannya masuknya virus.\n2. Selalu install software officialy yang diberikan pihak Samsung Service, karena software bukan dari Samsung kadang menyebabkan fungsi wifi ataupun bluetooth rusak.'),
('K0260', 'Touch Panel', 'Biasa disebut juga touchscreen atau layar sentuh merupakan ebuah perangkat input  yang bekerja dengan adanya sentuhan tampilan layar menggunakan jari atau pena digital. Antarmuka layar sentuh, di mana pengguna mengoperasikan sistem komputer dengan menyentuh gambar atau tulisan di layar itu sendiri, merupakan cara yang paling mudah untuk mengoperasikan perangkat dan kini semakin banyak digunakan dalam berbagai aplikasi.', '1. Selalu sentuh layar dengan pena atau tangan yang bersih.\n2. Jangan sampai tertekan karena tekanan tinggi seperti kedudukan atau kebentur dapat menyebabkan pecah.\n3. Selalu gunakan pena ataupun perangkat lain yang sesuai dengan jenis handphonennya.'),
('K0270', 'S-Pen', 'stylus adalah alat menulis digital. Hal ini juga bisa menjadi aksesori handphone yang digunakan untuk membantu dalam melakukan navigasi atau memberikan lebih presisi ketika menggunakan layar sentuh. Pena digital ini bisa dipakai untuk melakukan berbagai macam hal seperti menggambar, menulis, atau mengontrol playback video. S-Pen milik Samsung memiliki tingkat akurasi dan respons yang sangat baik, termasuk dalam hal sensitivitas terhadap tekanan yang membuat tebal-tipisnya hasil coretan menjadi bervariasi.', '1. Jangan sampai patah ataupun rusak pada bagian ujung pena.\n2. Gunakan hanya pada jenis handphonenya karena ujung pena tersebut memiliki sensor yang rentan jika digunakan untuk model lain ataupun benda lain.'),
('K0280', 'Camera', 'Sensor optik yang menangkap gambar atau objek untuk kemudian diolah oleh bagian rangkaian dan diteruskan ke handphone. ', '1. Jangan terlalu sering menggunakan flash camera karena dapat merusak optik camera.\n2. Jangan tertekan, tekanan terlalu tinggi dapat merusak optik kamera juga.\n3. Jangan mengcapture gambar dengan sinar matahari terlalu tajam, karena dapat merusak optik.'),
('K0290', 'Microphone', 'Alat input untuk memasukkan suara ke dalam handphone. Bekerja dengan cara mengubah getaran suara menjadi getaran listrik audio analog. Sinyal analog selanjutnya diubah menjadi sinyal digital audio oleh Digital Signal Processor(DSP). ', '1. Jaga kebersihan handphone, karena debu bisa menutupi lubang microphone sehingga menyebabkan suara tidak terdengar\n2. Beberapa microphone memiliki karet pelindung sebagai anti grounding, jangan biarkan handphone panas terlalu sering karena dapat menyebabkan karet ini meleleh.'),
('K0300', 'Slot Sim card ', 'Slot Sim Card adalah tempat menyisipkan sim card biasanya terbuat dari plastik dan besi.', '1. Masukkan sim card sesuai dengan ukuran slotnya dan petunjuk penempatan yang terdapat di chasing handphone.\n2. Jangan congkel slot menggunakan benda tajam karena dapat mengakibatkan pin-pin dalam slot patah.\n3. Hanya masukkan sim card bukan micro sd atau lainnya, karena jika sudah masuk untuk mengeluarkannya akan mengakibatkan pin-pin dalam slot patah.');

-- --------------------------------------------------------

--
-- Struktur dari tabel `relasi`
--

CREATE TABLE IF NOT EXISTS `relasi` (
  `id_gejala` varchar(5) NOT NULL,
  `id_komponen` varchar(5) NOT NULL,
  `nilai_cf` decimal(3,3) NOT NULL,
  PRIMARY KEY (`id_gejala`,`id_komponen`),
  KEY `id_komponen` (`id_komponen`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `relasi`
--

INSERT INTO `relasi` (`id_gejala`, `id_komponen`, `nilai_cf`) VALUES
('B0010', 'K0010', 0.400),
('B0020', 'K0020', 0.800),
('B0020', 'K0030', 0.800),
('B0020', 'K0040', 0.200),
('B0020', 'K0050', 0.400),
('B0020', 'K0060', 0.200),
('B0020', 'K0070', 0.200),
('B0020', 'K0080', 0.600),
('B0030', 'K0090', 0.200),
('B0030', 'K0100', 0.200),
('B0030', 'K0110', 0.200),
('B0040', 'K0120', 0.400),
('B0040', 'K0130', 0.400),
('B0040', 'K0140', 0.600),
('B0050', 'K0150', 0.200),
('B0060', 'K0160', 0.200),
('B0060', 'K0170', 0.200),
('B0060', 'K0180', 0.400),
('B0060', 'K0190', 0.400),
('B0060', 'K0200', 0.200),
('B0060', 'K0210', 0.400),
('B0060', 'K0220', 0.400),
('B0060', 'K0230', 0.200),
('B0070', 'K0240', 0.800),
('B0070', 'K0250', 0.400),
('B0070', 'K0260', 0.400),
('B0070', 'K0270', 0.400),
('B0070', 'K0280', 0.400),
('B0070', 'K0290', 0.200),
('B0080', 'K0300', 0.200),
('C0010', 'K0010', 0.800),
('C0020', 'K0020', 0.600),
('C0020', 'K0030', 0.400),
('C0030', 'K0040', 0.400),
('C0030', 'K0050', 0.200),
('C0040', 'K0060', 0.800),
('C0040', 'K0070', 0.400),
('C0050', 'K0080', 0.800),
('C0060', 'K0090', 0.400),
('C0060', 'K0100', 0.400),
('C0070', 'K0110', 0.400),
('C0080', 'K0120', 0.600),
('C0090', 'K0130', 0.800),
('C0100', 'K0140', 0.200),
('C0110', 'K0150', 0.400),
('C0120', 'K0160', 0.600),
('C0120', 'K0170', 0.800),
('C0120', 'K0180', 0.200),
('C0130', 'K0190', 0.200),
('C0130', 'K0200', 0.400),
('C0130', 'K0210', 0.200),
('C0140', 'K0220', 0.200),
('C0150', 'K0230', 0.600),
('C0160', 'K0240', 0.600),
('C0160', 'K0250', 0.200),
('C0170', 'K0260', 0.800),
('C0170', 'K0270', 0.600),
('C0180', 'K0280', 0.800),
('C0190', 'K0290', 0.400),
('C0200', 'K0300', 0.400),
('D0010', 'K0010', 0.200),
('D0020', 'K0020', 0.200),
('D0020', 'K0030', 0.600),
('D0030', 'K0040', 0.800),
('D0040', 'K0050', 0.800),
('D0050', 'K0060', 0.600),
('D0050', 'K0070', 0.800),
('D0060', 'K0080', 0.200),
('D0070', 'K0090', 0.600),
('D0070', 'K0100', 0.600),
('D0080', 'K0110', 0.800),
('D0090', 'K0120', 0.200),
('D0100', 'K0130', 0.200),
('D0110', 'K0140', 0.800),
('D0120', 'K0150', 0.600),
('D0140', 'K0160', 0.800),
('D0140', 'K0170', 0.400),
('D0150', 'K0180', 0.600),
('D0160', 'K0190', 0.600),
('D0170', 'K0200', 0.800),
('D0180', 'K0210', 0.800),
('D0190', 'K0220', 0.800),
('D0200', 'K0230', 0.600),
('D0210', 'K0240', 0.400),
('D0220', 'K0250', 0.600),
('D0230', 'K0260', 0.200),
('D0230', 'K0270', 0.200),
('D0240', 'K0280', 0.600),
('D0250', 'K0290', 0.600),
('D0260', 'K0300', 0.600),
('E0010', 'K0010', 0.600),
('E0020', 'K0020', 0.400),
('E0030', 'K0030', 0.200),
('E0030', 'K0040', 0.600),
('E0050', 'K0050', 0.600),
('E0060', 'K0060', 0.400),
('E0070', 'K0070', 0.600),
('E0080', 'K0080', 0.400),
('E0090', 'K0090', 0.800),
('E0100', 'K0100', 0.800),
('E0110', 'K0110', 0.600),
('E0120', 'K0120', 0.800),
('E0130', 'K0130', 0.600),
('E0140', 'K0140', 0.400),
('E0150', 'K0150', 0.800),
('E0160', 'K0160', 0.400),
('E0170', 'K0170', 0.600),
('E0180', 'K0180', 0.800),
('E0190', 'K0190', 0.800),
('E0200', 'K0200', 0.600),
('E0210', 'K0210', 0.600),
('E0220', 'K0220', 0.600),
('E0230', 'K0230', 0.800),
('E0240', 'K0240', 0.200),
('E0250', 'K0250', 0.800),
('E0270', 'K0260', 0.600),
('E0270', 'K0270', 0.800),
('E0280', 'K0280', 0.600),
('E0290', 'K0290', 0.800),
('E0300', 'K0300', 0.800);

-- --------------------------------------------------------

--
-- Struktur dari tabel `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `username` varchar(32) NOT NULL,
  `password` varchar(32) NOT NULL,
  PRIMARY KEY (`username`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `user`
--

INSERT INTO `user` (`username`, `password`) VALUES
('admin', '21232f297a57a5a743894a0e4a801fc3');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
