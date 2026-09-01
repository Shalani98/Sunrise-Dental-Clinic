-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Sep 01, 2026 at 09:45 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `sunrise_dental`
--

-- --------------------------------------------------------

--
-- Table structure for table `appointments`
--

CREATE TABLE `appointments` (
  `appointment_id` int(11) NOT NULL,
  `appointment_no` varchar(50) NOT NULL,
  `patient_id` int(11) NOT NULL,
  `dentist_id` int(11) NOT NULL,
  `treatment_id` int(11) NOT NULL,
  `appointment_date` date NOT NULL,
  `appointment_time` time NOT NULL,
  `status` varchar(50) DEFAULT 'Pending',
  `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `appointments`
--

INSERT INTO `appointments` (`appointment_id`, `appointment_no`, `patient_id`, `dentist_id`, `treatment_id`, `appointment_date`, `appointment_time`, `status`, `created_at`) VALUES
(6, '01', 1, 1, 1, '2026-07-10', '21:25:00', 'Paid', '2026-07-10 15:52:20'),
(7, '7', 1, 1, 2, '2026-07-10', '22:10:00', 'Paid', '2026-07-10 16:41:04'),
(8, 'A1783754406503', 2, 1, 3, '2026-07-11', '12:51:00', 'Paid', '2026-07-11 07:20:06'),
(9, 'A1783760929484', 3, 2, 5, '2026-07-12', '14:40:00', 'Pending', '2026-07-11 09:08:49'),
(10, 'A1783789430062', 4, 2, 2, '2026-07-11', '22:34:00', 'Paid', '2026-07-11 17:03:50'),
(11, 'A1783790810539', 5, 2, 4, '2026-07-25', '22:00:00', 'Paid', '2026-07-11 17:26:50'),
(12, 'A1783791815395', 6, 1, 5, '2026-07-12', '12:13:00', 'Paid', '2026-07-11 17:43:35'),
(13, 'A1788202385626', 7, 16, 1, '2026-09-02', '00:27:00', 'Paid', '2026-08-31 18:53:05'),
(14, 'A1788240224579', 8, 13, 4, '2026-09-02', '10:57:00', 'Paid', '2026-09-01 05:23:44');

-- --------------------------------------------------------

--
-- Table structure for table `bills`
--

CREATE TABLE `bills` (
  `bill_id` int(11) NOT NULL,
  `appointment_no` varchar(20) DEFAULT NULL,
  `consultation_fee` double DEFAULT NULL,
  `treatment_cost` double DEFAULT NULL,
  `total_amount` double DEFAULT NULL,
  `bill_date` date DEFAULT NULL,
  `payment_status` varchar(20) NOT NULL DEFAULT 'Paid'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `bills`
--

INSERT INTO `bills` (`bill_id`, `appointment_no`, `consultation_fee`, `treatment_cost`, `total_amount`, `bill_date`, `payment_status`) VALUES
(1, '2', 6500, 300, 6800, '2026-07-11', 'Paid'),
(2, 'A1783760929484', 2950, 500, 3450, '2026-07-12', 'Paid'),
(3, 'A1783789430062', 1000, 1200, 3200, '2026-07-11', 'Paid'),
(4, 'A1783790810539', 1000, 2655, 3655, '2026-07-15', 'Paid'),
(5, 'A1783791815395', 1000, 1650, 2650, '2026-07-11', 'Paid'),
(6, '01', 1000, 1500, 2500, '2026-08-21', 'Paid'),
(7, '7', 1000, 1999.98, 2999.98, '2026-08-26', 'Paid'),
(8, 'A1783754406503', 1000, 2499.95, 3499.95, '2026-08-26', 'Paid'),
(9, 'A1788202385626', 1000, 6900, 7900, '2026-08-31', 'Paid'),
(10, 'A1788240224579', 1000, 6000, 7000, '2026-09-01', 'Paid');

-- --------------------------------------------------------

--
-- Table structure for table `dentists`
--

CREATE TABLE `dentists` (
  `dentist_id` int(11) NOT NULL,
  `dentist_name` varchar(100) NOT NULL,
  `specialization` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `dentists`
--

INSERT INTO `dentists` (`dentist_id`, `dentist_name`, `specialization`) VALUES
(1, 'Dr. Silva', 'General Dentistry'),
(2, 'Dr. Perera', 'Orthodontist'),
(3, 'Dr. Nimal Fernando', 'General Dentistry'),
(4, 'Dr. Kasun Jayasinghe', 'Orthodontist'),
(5, 'Dr. Tharindu Gunawardena', 'Periodontist'),
(6, 'Dr. Dilshan Wijesinghe', 'Endodontist'),
(7, 'Dr. Chamara Bandara', 'Prosthodontist'),
(8, 'Dr. Sachini Ratnayake', 'Pediatric Dentist'),
(9, 'Dr. Isuru Senanayake', 'Oral Surgeon'),
(10, 'Dr. Kavindu Karunaratne', 'General Dentistry'),
(11, 'Dr. Dinithi Abeysekara', 'Orthodontist'),
(12, 'Dr. Lahiru Dissanayake', 'Periodontist'),
(13, 'Dr. Nadeesha Samarasinghe', 'Endodontist'),
(14, 'Dr. Ruwan Ekanayake', 'Prosthodontist'),
(15, 'Dr. Amaya Wickramasinghe', 'Pediatric Dentist'),
(16, 'Dr. Malith Herath', 'Oral Surgeon'),
(17, 'Dr. Sanduni Weerasinghe', 'General Dentistry'),
(18, 'Dr. Shenali Rodrigo', 'Cosmetic Dentistry'),
(19, 'Dr. Akila De Silva', 'Orthodontist'),
(20, 'Dr. Piumi Amarasinghe', 'Periodontist'),
(21, 'Dr. Dhanushka Pathirana', 'Endodontist'),
(22, 'Dr. Hasini Peiris', 'Prosthodontist'),
(23, 'Dr. Kavisha Nanayakkara', 'Pediatric Dentist'),
(24, 'Dr. Roshan Mendis', 'Oral Surgeon'),
(25, 'Dr. Thilini Alwis', 'Cosmetic Dentistry'),
(26, 'Dr. Janith Rajapaksha', 'General Dentistry'),
(27, 'Dr. Anushka Peries', 'Orthodontist');

-- --------------------------------------------------------

--
-- Table structure for table `patients`
--

CREATE TABLE `patients` (
  `patient_id` int(11) NOT NULL,
  `patient_name` varchar(100) NOT NULL,
  `address` varchar(200) NOT NULL,
  `contact_number` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `patients`
--

INSERT INTO `patients` (`patient_id`, `patient_name`, `address`, `contact_number`) VALUES
(1, 'Test', 'Colombo', '0712345678'),
(2, 'Wenguardian Roy', 'Colombo', '0741212441'),
(3, 'Anderson Kri', 'Wellawatta Colombo', '0841114555'),
(4, 'Geter Dhsn', 'Colombo', '0741121441'),
(5, 'AQnd', 'Dondda', '05521121441'),
(6, 'Ronafg', 'Colombo', '0717521141'),
(7, 'Sanduni Nisansala', 'Colombo', '0412212110'),
(8, 'Sugathadasa Abeygunawardana', 'No9 Sen Street DOndra', '0741147754');

-- --------------------------------------------------------

--
-- Table structure for table `treatments`
--

CREATE TABLE `treatments` (
  `treatment_id` int(11) NOT NULL,
  `treatment_name` varchar(100) NOT NULL,
  `treatment_cost` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `treatments`
--

INSERT INTO `treatments` (`treatment_id`, `treatment_name`, `treatment_cost`) VALUES
(1, 'Consultation', 2000),
(2, 'Cleaning', 5000),
(3, 'Filling', 8000),
(4, 'Root Canal', 25000),
(5, 'Extraction', 10000);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `user_id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  `role` enum('admin','staff') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`user_id`, `username`, `password`, `role`) VALUES
(1, 'admin', '$2a$10$Xx/fi17GbMC52.k8BGuNzeV6V3TQoB4YxE7ODDU2efH9BeJeAjgyS', 'admin'),
(2, 'staff', '$2a$10$XYtPhZvzAgjQZrNmvjH8k.8NaZfbW72stFRfvLWHD9nOHzqA4.VYy', 'staff');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `appointments`
--
ALTER TABLE `appointments`
  ADD PRIMARY KEY (`appointment_id`),
  ADD UNIQUE KEY `appointment_no` (`appointment_no`),
  ADD KEY `patient_id` (`patient_id`),
  ADD KEY `dentist_id` (`dentist_id`),
  ADD KEY `treatment_id` (`treatment_id`);

--
-- Indexes for table `bills`
--
ALTER TABLE `bills`
  ADD PRIMARY KEY (`bill_id`);

--
-- Indexes for table `dentists`
--
ALTER TABLE `dentists`
  ADD PRIMARY KEY (`dentist_id`);

--
-- Indexes for table `patients`
--
ALTER TABLE `patients`
  ADD PRIMARY KEY (`patient_id`);

--
-- Indexes for table `treatments`
--
ALTER TABLE `treatments`
  ADD PRIMARY KEY (`treatment_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `appointments`
--
ALTER TABLE `appointments`
  MODIFY `appointment_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `bills`
--
ALTER TABLE `bills`
  MODIFY `bill_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `dentists`
--
ALTER TABLE `dentists`
  MODIFY `dentist_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;

--
-- AUTO_INCREMENT for table `patients`
--
ALTER TABLE `patients`
  MODIFY `patient_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `treatments`
--
ALTER TABLE `treatments`
  MODIFY `treatment_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `appointments`
--
ALTER TABLE `appointments`
  ADD CONSTRAINT `appointments_ibfk_1` FOREIGN KEY (`patient_id`) REFERENCES `patients` (`patient_id`),
  ADD CONSTRAINT `appointments_ibfk_2` FOREIGN KEY (`dentist_id`) REFERENCES `dentists` (`dentist_id`),
  ADD CONSTRAINT `appointments_ibfk_3` FOREIGN KEY (`treatment_id`) REFERENCES `treatments` (`treatment_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
