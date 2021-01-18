-- phpMyAdmin SQL Dump
-- version 5.0.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Czas generowania: 19 Sty 2021, 00:15
-- Wersja serwera: 10.4.14-MariaDB
-- Wersja PHP: 7.3.23

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Baza danych: `shop`
--
CREATE DATABASE IF NOT EXISTS `shop` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `shop`;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `authorities`
--

CREATE TABLE `authorities` (
  `id` bigint(20) NOT NULL,
  `name` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Zrzut danych tabeli `authorities`
--

INSERT INTO `authorities` (`id`, `name`) VALUES
(1, 'READ_AUTHORITY'),
(2, 'WRITE_AUTHORITY'),
(3, 'DELETE_AUTHORITY');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `carts`
--

CREATE TABLE `carts` (
  `cart_id` int(11) NOT NULL,
  `public_cart_id` varchar(255) DEFAULT NULL,
  `total_price` double DEFAULT NULL,
  `order_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Zrzut danych tabeli `carts`
--

INSERT INTO `carts` (`cart_id`, `public_cart_id`, `total_price`, `order_id`, `user_id`) VALUES
(1, '5e330229-e80f-4769-a06f-bb19522b7b03', 7376, 1, 2),
(2, '54df0d00-192c-495d-afcb-d19f19cb6a90', 0, NULL, 3),
(3, '3ce31d13-8a85-4640-82af-35e887ca39a7', 0, NULL, 4),
(4, '591f2370-d60a-45b9-a46c-1af1a342a25d', 0, NULL, 5),
(5, '615e1d68-9198-4e55-b6d7-5d5a6b2a562e', 0, NULL, 6),
(6, 'f4acf342-7cb9-40b2-8b6c-ae339ca342b2', 819, 2, 2),
(7, '46c9c446-a26f-4005-af95-65d8a3c71c98', 2457, 3, 2),
(8, '01a7d1c0-e0a0-4150-9c7f-377dfaeb222e', 9354, 4, 2),
(9, 'e8babde8-d0d4-4ad8-9c41-47103718b3c8', 519, 5, 2),
(10, 'e6f89708-1a00-4cef-bd19-b790a33f1b18', 519, 6, 2),
(11, '2e9ad29d-6dfe-407d-a906-f8fe7a6c9ee2', 777, 7, 2),
(12, '9b4eb4e0-0150-42e9-9495-c458ff6bcbc8', 777, 8, 2),
(13, 'bf17ea47-db17-4bdc-b7f1-4073fe21f0fc', 0, NULL, 2);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `cart_items`
--

CREATE TABLE `cart_items` (
  `cart_item_id` int(11) NOT NULL,
  `products_price` double DEFAULT NULL,
  `public_cart_item_id` varchar(255) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `cart_id` int(11) DEFAULT NULL,
  `cart_product_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Zrzut danych tabeli `cart_items`
--

INSERT INTO `cart_items` (`cart_item_id`, `products_price`, `public_cart_item_id`, `quantity`, `cart_id`, `cart_product_id`) VALUES
(1, 5000, '79a35dd1-a9e9-4558-8568-45e667e8e0d0', 2, 1, 1),
(2, 1557, '4e9550ea-1202-4a55-9e7e-145deea18329', 3, 1, 3),
(3, 819, 'b796a5b8-74e3-4432-acef-0e583dd8d2a9', 1, 1, 5),
(4, 819, 'd28a295c-8203-4140-8c0e-88622bd7797c', 1, 6, 5),
(5, 2457, '46d4fed2-6fa0-47d6-a5b8-3f5aaeb2f6c2', 3, 7, 5),
(6, 4095, 'b316e5f1-2040-45f1-95e2-0e0713d6bbe2', 5, 8, 5),
(7, 4440, '83c1e115-597e-4319-9805-f58de276abb5', 5, 8, 10),
(8, 300, 'cbd6e7c6-996c-4214-a4ea-634736a7077c', 2, 8, 2),
(9, 519, '604f6000-4fc3-4d69-aa3a-5ef7c61a0913', 1, 8, 3),
(10, 519, 'b12e677a-a61c-40c3-8719-ac70e447c941', 1, 9, 3),
(11, 519, '80072268-476d-4d42-96e3-12b9e765fcef', 1, 10, 3),
(12, 777, '8c101552-b19f-4306-a68d-985503b272f2', 1, 11, 4),
(13, 777, '90280831-3d1d-4246-a1eb-6f690b88f4f5', 1, 12, 4);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Zrzut danych tabeli `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(5),
(5);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `orders`
--

CREATE TABLE `orders` (
  `order_id` int(11) NOT NULL,
  `public_order_id` varchar(255) DEFAULT NULL,
  `cart_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Zrzut danych tabeli `orders`
--

INSERT INTO `orders` (`order_id`, `public_order_id`, `cart_id`, `user_id`) VALUES
(1, '0a900d44-b722-4003-89f2-287286728f23', 1, 2),
(2, 'd4f98722-03a8-4fc8-90ea-14b64e3c5973', 6, 2),
(3, '6dba8ca4-9d65-4af0-90cc-fd6411d258ad', 7, 2),
(4, '77585bd2-84b8-4507-862d-d5512d0ccd42', 8, 2),
(5, '81a3c73c-397f-44e4-8de9-2c08732ef3a3', 9, 2),
(6, '0e044af8-19f1-4282-9ce7-ca7441230c39', 10, 2),
(7, 'fdd3cd9d-da65-4e50-9cc2-819fd6bd341a', 11, 2),
(8, 'd1abb64a-67ce-4886-b1e3-b944c07d95be', 12, 2);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `products`
--

CREATE TABLE `products` (
  `product_id` int(11) NOT NULL,
  `product_brand` varchar(255) NOT NULL,
  `product_description` varchar(255) NOT NULL,
  `product_model` varchar(255) NOT NULL,
  `product_name` varchar(255) NOT NULL,
  `product_price` double NOT NULL,
  `public_product_id` varchar(255) NOT NULL,
  `quantity_of_stock` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Zrzut danych tabeli `products`
--

INSERT INTO `products` (`product_id`, `product_brand`, `product_description`, `product_model`, `product_name`, `product_price`, `public_product_id`, `quantity_of_stock`) VALUES
(1, 'a', 'desc', 'a', 'a', 2500, 'dccdf70f-9ddc-4570-a21d-59dee6a350d0', 98),
(2, 'b', 'desc', 'b', 'b', 150, '5a36a58e-04ab-4420-81d8-402922c657f0', 98),
(3, 'c', 'desc', 'c', 'c', 519, 'ec20833d-58b4-4259-93b5-ffd3b2ca2458', 94),
(4, 'd', 'desc', 'd', 'd', 777, 'e2dc0f52-f299-4afd-97d1-961b1a663650', 98),
(5, 'e', 'desc', 'e', 'e', 819, '45f01328-3c23-421f-abbd-3c7cd327a733', 90),
(6, 'f', 'desc', 'f', 'f', 1233, '9dec26d7-4866-498d-b280-cf5bad5ac428', 100),
(7, 'g', 'desc', 'g', 'g', 13, 'b47c5e40-05f6-4ab0-a3a2-12f3f9f7af50', 100),
(8, 'h', 'desc', 'h', 'h', 49, '408639d3-1d5e-4c64-b7a1-6d2d76c0cd5e', 100),
(9, 'i', 'desc', 'i', 'i', 111, 'c8c2180c-b5c7-4456-92ac-6438a8027ee8', 100),
(10, 'j', 'desc', 'j', 'j', 888, '7f6a948d-b7c3-4e0f-9908-7f0dda8cb341', 95),
(11, 'k', 'desc', 'k', 'k', 2132, '589d6506-654c-456e-a22a-ca185b1f5206', 100),
(12, 'l', 'desc', 'l', 'l', 3333, '21d5227c-3df4-45df-b6b3-224e1d66213e', 100),
(13, 'm', 'desc', 'm', 'm', 2190, '0e9b59ad-29d8-4227-929a-10ac194d3024', 100),
(14, 'n', 'desc', 'n', 'n', 2313, 'bab22b51-6971-493d-b7d9-8e65e761a99e', 100),
(15, 'o', 'desc', 'o', 'o', 2334, '3762ce51-5730-4dd4-a63e-ef91a8382438', 100),
(16, 'p', 'desc', 'p', 'p', 2555, '4dec1efc-c45b-4480-b7b9-beb02f79b635', 100),
(17, 'r', 'desc', 'r', 'r', 2861, 'bcb03fe6-e432-4faf-a429-381b78e72cad', 100),
(18, 's', 'desc', 's', 's', 3612, '187a4ccd-34cb-4fcd-9d0f-65c898bfff6d', 100),
(19, 't', 'desc', 't', 't', 3612, 'd87b5938-555b-4d21-8671-3bc844ffb0ef', 100),
(20, 'u', 'desc', 'u', 'u', 3612, 'ba18134d-b3a7-467f-b24c-eacd927ff897', 100),
(21, 'w', 'desc', 'w', 'w', 4343, 'd7b20ae1-08e5-4a99-a97d-23c6d827c643', 100),
(22, 'x', 'desc', 'x', 'x', 5555, '11f90ae5-6e03-40cd-a1c6-0bd409821ca0', 100),
(23, 'y', 'desc', 'y', 'y', 6666, '82f52ee3-7c90-49e2-bea3-9e0fd1f12fb7', 100),
(24, 'z', 'desc', 'z', 'z', 7777, 'c92ebf38-fa7a-43af-aea3-717553a6e460', 100);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `roles`
--

CREATE TABLE `roles` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Zrzut danych tabeli `roles`
--

INSERT INTO `roles` (`id`, `name`) VALUES
(4, 'ROLE_ADMIN');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `roles_authorities`
--

CREATE TABLE `roles_authorities` (
  `roles_id` bigint(20) NOT NULL,
  `authorities_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Zrzut danych tabeli `roles_authorities`
--

INSERT INTO `roles_authorities` (`roles_id`, `authorities_id`) VALUES
(4, 1),
(4, 2),
(4, 3);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `users`
--

CREATE TABLE `users` (
  `user_id` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `encrypted_password` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `public_user_id` varchar(255) DEFAULT NULL,
  `telephone` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Zrzut danych tabeli `users`
--

INSERT INTO `users` (`user_id`, `address`, `city`, `email`, `encrypted_password`, `first_name`, `last_name`, `public_user_id`, `telephone`) VALUES
(1, 'Admin', 'Admin', 'test@admin.com', '$2a$10$r1flMeKf38TB3f0g52qrWOryty5N2enz2/WfBTNkDSEsGHW9Vseb2', 'Admin', 'Admin', '4f4a8d64-e030-43b0-ae06-2e7ac62635ba', '999999999'),
(2, 'Grójecka', 'Warszawa', 'test1@gmail.com', '$2a$10$0ESTO4xIItfJJtUWdqFmseuMgI2SCE7P.T0I8XuEUlkXtd4DeiJx.', 'Imie', 'Nazwisko', 'f2d4330c-afd7-41cb-9286-7346450f59d4', '123123123'),
(3, 'Grójecka', 'Warszawa', 'test2@gmail.com', '$2a$10$6I.c1qpm3fEtoMgrNCgkfOJOq5aJrkevGW9015pn4jZYDh9huTKcW', 'Imie', 'Nazwisko', '6f3b2d1e-57c8-4d35-9c91-de9b3da62774', '123123123'),
(4, 'Grójecka', 'Warszawa', 'test3@gmail.com', '$2a$10$dojId1ZZWHi/H8mybwo6J.BGo59COX.f4Fd/GHmI94p.0OGETTmYW', 'Imie', 'Nazwisko', '670b2290-82b2-4189-b419-b4714c5c6b07', '123123123'),
(5, 'Grójecka', 'Warszawa', 'test4@gmail.com', '$2a$10$/f5wy4GE1Bpltbck9JIpEOUbbrpZlluspO9MIUKqaCdct2TcYbTq6', 'Imie', 'Nazwisko', 'c9bc8940-6732-4942-8f54-45bc3f64159b', '123123123'),
(6, 'Grójecka', 'Warszawa', 'test5@gmail.com', '$2a$10$mAwPo.VhkWU7IhuIOnKUW.ZrP5mw6nxW4a4FZFWytFqFKOcBlyaCK', 'Imie', 'Nazwisko', 'e2ae03a1-a728-43fa-9427-c842e4fbacb6', '123123123');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `users_roles`
--

CREATE TABLE `users_roles` (
  `user_id` int(11) NOT NULL,
  `roles_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Zrzut danych tabeli `users_roles`
--

INSERT INTO `users_roles` (`user_id`, `roles_id`) VALUES
(1, 4);

--
-- Indeksy dla zrzutów tabel
--

--
-- Indeksy dla tabeli `authorities`
--
ALTER TABLE `authorities`
  ADD PRIMARY KEY (`id`);

--
-- Indeksy dla tabeli `carts`
--
ALTER TABLE `carts`
  ADD PRIMARY KEY (`cart_id`),
  ADD KEY `FK1scg0ylakb1pltk7ivjpkaa57` (`order_id`),
  ADD KEY `FKb5o626f86h46m4s7ms6ginnop` (`user_id`);

--
-- Indeksy dla tabeli `cart_items`
--
ALTER TABLE `cart_items`
  ADD PRIMARY KEY (`cart_item_id`),
  ADD KEY `FKpcttvuq4mxppo8sxggjtn5i2c` (`cart_id`),
  ADD KEY `FK5l1f8ds58tfth0ma8013ssdf4` (`cart_product_id`);

--
-- Indeksy dla tabeli `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`order_id`),
  ADD KEY `FK594fgx8wpklcf3t41jq3grhlh` (`cart_id`),
  ADD KEY `FK32ql8ubntj5uh44ph9659tiih` (`user_id`);

--
-- Indeksy dla tabeli `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`product_id`);

--
-- Indeksy dla tabeli `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`id`);

--
-- Indeksy dla tabeli `roles_authorities`
--
ALTER TABLE `roles_authorities`
  ADD KEY `FKe4pjsn2c2ttg8bpbe1yk29snn` (`authorities_id`),
  ADD KEY `FKcj918h3ee3qad1xwbx4jvvcgc` (`roles_id`);

--
-- Indeksy dla tabeli `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`);

--
-- Indeksy dla tabeli `users_roles`
--
ALTER TABLE `users_roles`
  ADD KEY `FKa62j07k5mhgifpp955h37ponj` (`roles_id`),
  ADD KEY `FK2o0jvgh89lemvvo17cbqvdxaa` (`user_id`);

--
-- AUTO_INCREMENT dla zrzuconych tabel
--

--
-- AUTO_INCREMENT dla tabeli `carts`
--
ALTER TABLE `carts`
  MODIFY `cart_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT dla tabeli `cart_items`
--
ALTER TABLE `cart_items`
  MODIFY `cart_item_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT dla tabeli `orders`
--
ALTER TABLE `orders`
  MODIFY `order_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT dla tabeli `products`
--
ALTER TABLE `products`
  MODIFY `product_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT dla tabeli `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Ograniczenia dla zrzutów tabel
--

--
-- Ograniczenia dla tabeli `carts`
--
ALTER TABLE `carts`
  ADD CONSTRAINT `FK1scg0ylakb1pltk7ivjpkaa57` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`),
  ADD CONSTRAINT `FKb5o626f86h46m4s7ms6ginnop` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`);

--
-- Ograniczenia dla tabeli `cart_items`
--
ALTER TABLE `cart_items`
  ADD CONSTRAINT `FK5l1f8ds58tfth0ma8013ssdf4` FOREIGN KEY (`cart_product_id`) REFERENCES `products` (`product_id`),
  ADD CONSTRAINT `FKpcttvuq4mxppo8sxggjtn5i2c` FOREIGN KEY (`cart_id`) REFERENCES `carts` (`cart_id`);

--
-- Ograniczenia dla tabeli `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `FK32ql8ubntj5uh44ph9659tiih` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
  ADD CONSTRAINT `FK594fgx8wpklcf3t41jq3grhlh` FOREIGN KEY (`cart_id`) REFERENCES `carts` (`cart_id`);

--
-- Ograniczenia dla tabeli `roles_authorities`
--
ALTER TABLE `roles_authorities`
  ADD CONSTRAINT `FKcj918h3ee3qad1xwbx4jvvcgc` FOREIGN KEY (`roles_id`) REFERENCES `roles` (`id`),
  ADD CONSTRAINT `FKe4pjsn2c2ttg8bpbe1yk29snn` FOREIGN KEY (`authorities_id`) REFERENCES `authorities` (`id`);

--
-- Ograniczenia dla tabeli `users_roles`
--
ALTER TABLE `users_roles`
  ADD CONSTRAINT `FK2o0jvgh89lemvvo17cbqvdxaa` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
  ADD CONSTRAINT `FKa62j07k5mhgifpp955h37ponj` FOREIGN KEY (`roles_id`) REFERENCES `roles` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
