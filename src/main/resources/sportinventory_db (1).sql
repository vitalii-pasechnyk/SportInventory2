-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Хост: 127.0.0.1
-- Час створення: Гру 01 2024 р., 17:37
-- Версія сервера: 10.4.32-MariaDB
-- Версія PHP: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База даних: `sportinventory_db`
--

-- --------------------------------------------------------

--
-- Структура таблиці `bonus_card`
--

CREATE TABLE `bonus_card` (
  `id` bigint(20) NOT NULL,
  `balance` double NOT NULL,
  `card_number` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Дамп даних таблиці `bonus_card`
--

INSERT INTO `bonus_card` (`id`, `balance`, `card_number`) VALUES
(1, 15, '25801415'),
(3, 11, '25801113'),
(4, 10, '25801115');

-- --------------------------------------------------------

--
-- Структура таблиці `bonus_cards`
--

CREATE TABLE `bonus_cards` (
  `id` int(11) NOT NULL,
  `card_number` varchar(255) NOT NULL,
  `balance` decimal(10,2) DEFAULT 0.00,
  `creation_date` date DEFAULT curdate()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Структура таблиці `bookings`
--

CREATE TABLE `bookings` (
  `id` int(11) NOT NULL,
  `customer_id` int(11) NOT NULL,
  `equipment_id` int(11) NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date NOT NULL,
  `status` enum('Booked','Returned','Canceled') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Структура таблиці `customers`
--

CREATE TABLE `customers` (
  `id` int(11) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `phone_number` varchar(20) DEFAULT NULL,
  `bonus_card_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Структура таблиці `equipment`
--

CREATE TABLE `equipment` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `category` tinyint(4) NOT NULL,
  `rental_price` double NOT NULL,
  `quantity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Дамп даних таблиці `equipment`
--

INSERT INTO `equipment` (`id`, `name`, `category`, `rental_price`, `quantity`) VALUES
(1, 'Roketka', 0, 20, 5),
(2, 'Football\'s ball', 0, 50, 5);

-- --------------------------------------------------------

--
-- Структура таблиці `rental`
--

CREATE TABLE `rental` (
  `id` bigint(20) NOT NULL,
  `end_date` date DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `inventory_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `equipment_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Дамп даних таблиці `rental`
--

INSERT INTO `rental` (`id`, `end_date`, `start_date`, `status`, `inventory_id`, `user_id`, `equipment_id`) VALUES
(1, '2024-11-30', '2024-12-31', 'BOOKED', 1, 1, 0),
(2, '2024-12-05', '2024-11-30', 'BOOKED', NULL, 1, 1),
(3, '2024-12-05', '2024-11-30', 'BOOKED', NULL, 2, 1),
(4, '2024-12-07', '2024-12-06', 'BOOKED', NULL, 2, 1),
(5, '2024-12-15', '2024-12-10', 'BOOKED', NULL, 2, 1),
(6, '2025-01-19', '2025-01-01', 'BOOKED', NULL, 3, 1),
(7, '2025-01-21', '2025-01-20', 'BOOKED', NULL, 3, 1),
(8, '2025-01-23', '2025-01-22', 'BOOKED', NULL, 3, 1),
(9, '2025-01-25', '2025-01-24', 'BOOKED', NULL, 3, 1),
(10, '2025-01-25', '2025-01-24', 'BOOKED', NULL, 4, 2);

-- --------------------------------------------------------

--
-- Структура таблиці `statistics`
--

CREATE TABLE `statistics` (
  `id` bigint(20) NOT NULL,
  `total_income` decimal(38,2) DEFAULT NULL,
  `total_expenses` decimal(10,2) DEFAULT 0.00,
  `most_popular_item_id` int(11) DEFAULT NULL,
  `most_popular_item` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Структура таблиці `user`
--

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `bonus_card_id` bigint(20) DEFAULT NULL,
  `balance` decimal(38,2) NOT NULL,
  `rental_count` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Дамп даних таблиці `user`
--

INSERT INTO `user` (`id`, `email`, `name`, `password`, `first_name`, `last_name`, `bonus_card_id`, `balance`, `rental_count`) VALUES
(1, 'w.pasechnyk@gmail.com', NULL, NULL, 'Vitalii', 'Pasechnyk', NULL, 0.00, 0),
(2, 'w.pasechnyk@gmail.com', NULL, NULL, 'Vitalii', 'Pasechnyk', NULL, 50.00, 1),
(3, 'w.pasechnyk@gmail.com', NULL, NULL, 'Vitalii', 'P', NULL, 210.00, 4),
(4, 'w.pasechnyk@gmail.com', NULL, NULL, 'VitaIii', 'Pasechnyk', 3, 1010.00, 1);

--
-- Індекси збережених таблиць
--

--
-- Індекси таблиці `bonus_card`
--
ALTER TABLE `bonus_card`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK8hxgxih8w94vw2d1nw0plryrf` (`card_number`);

--
-- Індекси таблиці `bonus_cards`
--
ALTER TABLE `bonus_cards`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `card_number` (`card_number`);

--
-- Індекси таблиці `bookings`
--
ALTER TABLE `bookings`
  ADD PRIMARY KEY (`id`),
  ADD KEY `customer_id` (`customer_id`),
  ADD KEY `equipment_id` (`equipment_id`);

--
-- Індекси таблиці `customers`
--
ALTER TABLE `customers`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`),
  ADD KEY `bonus_card_id` (`bonus_card_id`);

--
-- Індекси таблиці `equipment`
--
ALTER TABLE `equipment`
  ADD PRIMARY KEY (`id`);

--
-- Індекси таблиці `rental`
--
ALTER TABLE `rental`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKm6f1r8a0m7w8n5upyjslprj25` (`user_id`);

--
-- Індекси таблиці `statistics`
--
ALTER TABLE `statistics`
  ADD PRIMARY KEY (`id`),
  ADD KEY `most_popular_item_id` (`most_popular_item_id`);

--
-- Індекси таблиці `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKhn3oeax94mk1oku3yj25m6u0a` (`bonus_card_id`);

--
-- AUTO_INCREMENT для збережених таблиць
--

--
-- AUTO_INCREMENT для таблиці `bonus_card`
--
ALTER TABLE `bonus_card`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT для таблиці `bonus_cards`
--
ALTER TABLE `bonus_cards`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT для таблиці `bookings`
--
ALTER TABLE `bookings`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT для таблиці `customers`
--
ALTER TABLE `customers`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT для таблиці `equipment`
--
ALTER TABLE `equipment`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT для таблиці `rental`
--
ALTER TABLE `rental`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT для таблиці `statistics`
--
ALTER TABLE `statistics`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT для таблиці `user`
--
ALTER TABLE `user`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Обмеження зовнішнього ключа збережених таблиць
--

--
-- Обмеження зовнішнього ключа таблиці `bookings`
--
ALTER TABLE `bookings`
  ADD CONSTRAINT `bookings_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `bookings_ibfk_2` FOREIGN KEY (`equipment_id`) REFERENCES `equipment` (`id`) ON DELETE CASCADE;

--
-- Обмеження зовнішнього ключа таблиці `customers`
--
ALTER TABLE `customers`
  ADD CONSTRAINT `customers_ibfk_1` FOREIGN KEY (`bonus_card_id`) REFERENCES `bonus_cards` (`id`) ON DELETE SET NULL;

--
-- Обмеження зовнішнього ключа таблиці `rental`
--
ALTER TABLE `rental`
  ADD CONSTRAINT `FKm6f1r8a0m7w8n5upyjslprj25` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Обмеження зовнішнього ключа таблиці `statistics`
--
ALTER TABLE `statistics`
  ADD CONSTRAINT `statistics_ibfk_1` FOREIGN KEY (`most_popular_item_id`) REFERENCES `equipment` (`id`) ON DELETE SET NULL;

--
-- Обмеження зовнішнього ключа таблиці `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `FK75xvtqk9mofcf1vgsc3pvivw9` FOREIGN KEY (`bonus_card_id`) REFERENCES `bonus_card` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
