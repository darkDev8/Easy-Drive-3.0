-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: May 27, 2021 at 06:47 PM
-- Server version: 10.4.19-MariaDB
-- PHP Version: 8.0.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `easydrive`
--

-- --------------------------------------------------------

--
-- Table structure for table `Files`
--

CREATE TABLE `Files` (
  `id` int(11) NOT NULL,
  `fid` int(11) NOT NULL,
  `name` text NOT NULL,
  `size` int(11) NOT NULL,
  `content` longblob NOT NULL,
  `uploadDate` text NOT NULL,
  `createDate` text NOT NULL,
  `category` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `Files`
--

INSERT INTO `Files` (`id`, `fid`, `name`, `size`, `content`, `uploadDate`, `createDate`, `category`) VALUES
(28, 23, 'qt.sh', 62, 0x23212f62696e2f73680a0a7375646f202f6f70742f5174352e31342e302f546f6f6c732f517443726561746f722f62696e2f2e2f717463726561746f720a, '2021/05/27', '2021-05-21', 'Linux executable file(script)');

-- --------------------------------------------------------

--
-- Table structure for table `History`
--

CREATE TABLE `History` (
  `id` int(11) NOT NULL,
  `fid` int(11) NOT NULL,
  `command` text NOT NULL,
  `date` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `History`
--

INSERT INTO `History` (`id`, `fid`, `command`, `date`) VALUES
(34, 23, 'history', '2021/05/26'),
(35, 23, 'count History', '2021/05/26'),
(36, 23, 'exit', '2021/05/26'),
(37, 23, 'lsc', '2021/05/26'),
(38, 23, 'cd Videos', '2021/05/26'),
(39, 23, 'lsf', '2021/05/26'),
(40, 23, 'info 22', '2021/05/26'),
(41, 23, 'cat AnyDesk', '2021/05/26'),
(42, 23, 'cat \"y2mate.com - FAST AND FURIOUS 9 Trailer 2 2021_1080p.mp4\"', '2021/05/26'),
(43, 23, 'cd Documents/tmp', '2021/05/26'),
(44, 23, 'cat 20-intel.conf', '2021/05/26'),
(45, 23, 'cat file1.cs', '2021/05/26'),
(46, 23, 'clear', '2021/05/26'),
(47, 23, 'cat config.conf', '2021/05/26'),
(48, 23, 'pwd', '2021/05/26'),
(49, 23, 'add names.txt', '2021/05/26'),
(50, 23, 'update 24 testa.mp3', '2021/05/26'),
(51, 23, 'xit', '2021/05/26'),
(52, 23, 'add qt.sh', '2021/05/26'),
(53, 23, 'info 25', '2021/05/26'),
(54, 23, 'cd Documents/programming', '2021/05/26'),
(55, 23, 'update 25 Ashkin-0098-Asheghetam-128.mp3', '2021/05/26'),
(56, 23, 'cd Downloads', '2021/05/26'),
(57, 23, 'add xvideos.com_fdf775176a7c0092abdd8a0d9eb02ba2.mp4', '2021/05/26'),
(58, 23, 'rm 26', '2021/05/26'),
(59, 23, 'cd ..', '2021/05/26'),
(60, 23, 'cd ../', '2021/05/26'),
(61, 23, 'cd', '2021/05/26'),
(62, 23, 'cd /', '2021/05/26'),
(63, 23, 'c d../', '2021/05/26'),
(64, 23, 'cd Desktop', '2021/05/26'),
(65, 23, 'sfi chrome-agimnkijcaahngcdmfeangaknmldooml-Default.desktop', '2021/05/26'),
(66, 23, 'sfi \"Big Naturals - JMac Skylar Vox - Registered Nurse Naturals.mp4\"', '2021/05/26'),
(67, 23, 'sfi qt.sh', '2021/05/26'),
(68, 23, 'cat qt.sh', '2021/05/26'),
(69, 23, 'sfi Ashkin-0098-Asheghetam-128.mp3 \"[FIX] How to Solve java.lang.OutOfMemoryError Java Heap Space.mp4\"', '2021/05/26'),
(70, 23, 'info 22 18', '2021/05/26'),
(71, 23, 'get 22 .', '2021/05/26'),
(72, 23, 'sfi \"5.77 MB   David Guetta, Bebe Rexha & J Balvin - Say My Name (Binayz Remix).mp3\"', '2021/05/26'),
(73, 23, 'cd Downlaods', '2021/05/26'),
(74, 23, 'sfi \"Hottest Venezuelan 18 yo Teen Gets Destroyed, Amazing Ass.mp4\"', '2021/05/26'),
(75, 23, 'id c#file.cs', '2021/05/26'),
(76, 23, 'update 18 JetBrains_2021.mp4', '2021/05/26'),
(77, 23, 'get 18 .', '2021/05/26'),
(78, 23, 'cat c#file.cs', '2021/05/26'),
(79, 23, '[2;1;1;120;120;1;0xclear', '2021/05/26'),
(80, 23, 'id qt.sh', '2021/05/26'),
(81, 23, 'update 25 JetBrains_2021.mp4', '2021/05/26'),
(82, 23, 'rma', '2021/05/26'),
(83, 23, 's', '2021/05/26'),
(84, 23, 'exit y', '2021/05/26'),
(85, 23, 'exit -y', '2021/05/26'),
(86, 23, 'cd tmp', '2021/05/27'),
(87, 23, 'envs', '2021/05/27'),
(88, 23, 'wc source.cs', '2021/05/27'),
(89, 23, 'echo \"bye.\"', '2021/05/27');

-- --------------------------------------------------------

--
-- Table structure for table `Users`
--

CREATE TABLE `Users` (
  `id` int(11) NOT NULL,
  `user` text NOT NULL,
  `pass` text NOT NULL,
  `passHint` text NOT NULL,
  `createDate` text NOT NULL,
  `space` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `Users`
--

INSERT INTO `Users` (`id`, `user`, `pass`, `passHint`, `createDate`, `space`) VALUES
(23, 'mahdi', 'G6ljC7a5GQGpOiBa4x7H/vKaAMJoPa2UzTU70ezgr7jVN6e8+wC2Kw==', 'no hint', '2021', 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `Files`
--
ALTER TABLE `Files`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `History`
--
ALTER TABLE `History`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `Users`
--
ALTER TABLE `Users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `Files`
--
ALTER TABLE `Files`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;

--
-- AUTO_INCREMENT for table `History`
--
ALTER TABLE `History`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=90;

--
-- AUTO_INCREMENT for table `Users`
--
ALTER TABLE `Users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
