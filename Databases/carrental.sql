-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 17, 2024 at 06:52 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `carrental`
--

-- --------------------------------------------------------

--
-- Table structure for table `car`
--

CREATE TABLE `car` (
  `regPlate` varchar(15) NOT NULL,
  `passengers` int(10) DEFAULT NULL,
  `kilometers` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `car`
--

INSERT INTO `car` (`regPlate`, `passengers`, `kilometers`) VALUES
('11-C-690', 4, 320000),
('181-D-1234', 4, 50000),
('201-L-91011', 7, 75000);

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `customerID` int(4) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `eircode` varchar(7) DEFAULT NULL,
  `phoneNo` varchar(15) DEFAULT NULL,
  `DOB` date DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `driverNum` varchar(10) DEFAULT NULL,
  `review` int(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`customerID`, `name`, `password`, `eircode`, `phoneNo`, `DOB`, `email`, `driverNum`, `review`) VALUES
(1, 'John Doe', 'password123', 'D01AB12', '+353 12 345 456', '1990-05-15', 'john@example.com', '1234567890', 4),
(2, 'Jane Smith', 'qwerty123', 'D02CD34', '+353 21 987 654', '1985-08-25', 'jane@example.com', '0987654321', 5),
(3, 'Michael Johnson', 'mypassword', 'D03EF56', '+353 51 555 555', '1978-12-10', 'michael@example.com', '1122334455', 3),
(4, 'Jacob', 'wordpass', 'E3278DE', '085 234 5432', '1986-04-24', 'Jacob@email.com', '1234ABCD12', 2);

-- --------------------------------------------------------

--
-- Table structure for table `heavyvehicle`
--

CREATE TABLE `heavyvehicle` (
  `regPlate` varchar(15) NOT NULL,
  `maxLoad` int(11) DEFAULT NULL,
  `trainingRequired` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `heavyvehicle`
--

INSERT INTO `heavyvehicle` (`regPlate`, `maxLoad`, `trainingRequired`) VALUES
('192-G-5678', 620, 0);

-- --------------------------------------------------------

--
-- Table structure for table `invoice`
--

CREATE TABLE `invoice` (
  `invoiceID` int(11) NOT NULL,
  `reservationID` int(11) DEFAULT NULL,
  `customerID` int(11) DEFAULT NULL,
  `cost` float DEFAULT NULL,
  `deposit` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `invoice`
--

INSERT INTO `invoice` (`invoiceID`, `reservationID`, `customerID`, `cost`, `deposit`) VALUES
(1, 1, 2, 2000, 400),
(2, 2, 3, 4000, 500);

-- --------------------------------------------------------

--
-- Table structure for table `location`
--

CREATE TABLE `location` (
  `locationID` int(11) NOT NULL,
  `county` char(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `city` char(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `location`
--

INSERT INTO `location` (`locationID`, `county`, `city`) VALUES
(1, 'Dublin', 'DublinCity'),
(2, 'Cork', 'CorkCity'),
(3, 'Galway', 'GalwayCity'),
(4, 'Donegal', 'Letterkenny'),
(5, 'Kerry', 'Tralee'),
(6, 'Westmeath', 'Athlone'),
(7, 'Laois', 'Portlaoise');

-- --------------------------------------------------------

--
-- Table structure for table `reservation`
--

CREATE TABLE `reservation` (
  `reservationID` int(11) NOT NULL,
  `rentalDate` date DEFAULT NULL,
  `daysRented` int(11) DEFAULT NULL,
  `ifPickedUp` tinyint(1) DEFAULT NULL,
  `ifReturned` tinyint(1) DEFAULT NULL,
  `datePickedUp` date DEFAULT NULL,
  `regPlate` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `reservation`
--

INSERT INTO `reservation` (`reservationID`, `rentalDate`, `daysRented`, `ifPickedUp`, `ifReturned`, `datePickedUp`, `regPlate`) VALUES
(1, '2024-02-15', 5, 1, 0, '2024-02-10', '192-G-5678'),
(2, '2024-02-20', 3, 1, 0, '2024-02-18', '11-C-690');

-- --------------------------------------------------------

--
-- Table structure for table `vehicle`
--

CREATE TABLE `vehicle` (
  `regPlate` varchar(15) NOT NULL,
  `brand` char(20) DEFAULT NULL,
  `locationID` int(11) DEFAULT NULL,
  `available` tinyint(1) DEFAULT NULL,
  `engineSize` float DEFAULT NULL,
  `insuranceRate` float DEFAULT NULL,
  `model` char(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `vehicle`
--

INSERT INTO `vehicle` (`regPlate`, `brand`, `locationID`, `available`, `engineSize`, `insuranceRate`, `model`) VALUES
('11-C-690', 'BMW', 7, 1, 3, 420, '330D'),
('181-D-1234', 'Toyota', 1, 1, 1.6, 250, 'Corrola'),
('192-G-5678', 'Ford', 2, 0, 6, 300, 'Raptor'),
('201-L-91011', 'Honda', 3, 1, 1.8, 280, 'Civic');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `car`
--
ALTER TABLE `car`
  ADD PRIMARY KEY (`regPlate`);

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`customerID`);

--
-- Indexes for table `heavyvehicle`
--
ALTER TABLE `heavyvehicle`
  ADD PRIMARY KEY (`regPlate`);

--
-- Indexes for table `invoice`
--
ALTER TABLE `invoice`
  ADD PRIMARY KEY (`invoiceID`),
  ADD KEY `reservationID` (`reservationID`),
  ADD KEY `customerID` (`customerID`);

--
-- Indexes for table `location`
--
ALTER TABLE `location`
  ADD PRIMARY KEY (`locationID`);

--
-- Indexes for table `reservation`
--
ALTER TABLE `reservation`
  ADD PRIMARY KEY (`reservationID`),
  ADD KEY `regPlate` (`regPlate`);

--
-- Indexes for table `vehicle`
--
ALTER TABLE `vehicle`
  ADD PRIMARY KEY (`regPlate`),
  ADD KEY `locationID` (`locationID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `customer`
--
ALTER TABLE `customer`
  MODIFY `customerID` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `invoice`
--
ALTER TABLE `invoice`
  MODIFY `invoiceID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `location`
--
ALTER TABLE `location`
  MODIFY `locationID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `reservation`
--
ALTER TABLE `reservation`
  MODIFY `reservationID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `car`
--
ALTER TABLE `car`
  ADD CONSTRAINT `car_ibfk_1` FOREIGN KEY (`regPlate`) REFERENCES `vehicle` (`regPlate`);

--
-- Constraints for table `heavyvehicle`
--
ALTER TABLE `heavyvehicle`
  ADD CONSTRAINT `heavyvehicle_ibfk_1` FOREIGN KEY (`regPlate`) REFERENCES `vehicle` (`regPlate`);

--
-- Constraints for table `invoice`
--
ALTER TABLE `invoice`
  ADD CONSTRAINT `invoice_ibfk_1` FOREIGN KEY (`reservationID`) REFERENCES `reservation` (`reservationID`),
  ADD CONSTRAINT `invoice_ibfk_2` FOREIGN KEY (`customerID`) REFERENCES `customer` (`customerID`);

--
-- Constraints for table `reservation`
--
ALTER TABLE `reservation`
  ADD CONSTRAINT `reservation_ibfk_1` FOREIGN KEY (`regPlate`) REFERENCES `vehicle` (`regPlate`);

--
-- Constraints for table `vehicle`
--
ALTER TABLE `vehicle`
  ADD CONSTRAINT `vehicle_ibfk_1` FOREIGN KEY (`locationID`) REFERENCES `location` (`locationID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
