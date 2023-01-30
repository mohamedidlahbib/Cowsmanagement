-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : mar. 03 jan. 2023 à 21:26
-- Version du serveur : 10.4.22-MariaDB
-- Version de PHP : 8.1.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `cowsmanagement`
--

-- --------------------------------------------------------

--
-- Structure de la table `animal_health`
--

CREATE TABLE `animal_health` (
  `cowid` int(40) NOT NULL,
  `event` varchar(255) NOT NULL,
  `diagnosis` varchar(255) NOT NULL,
  `traitement` varchar(255) NOT NULL,
  `costoft` int(255) NOT NULL,
  `date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `animal_health`
--

INSERT INTO `animal_health` (`cowid`, `event`, `diagnosis`, `traitement`, `costoft`, `date`) VALUES
(1, 'fiver', 'A.A.V', 'DDD', 40, '2022-11-28');

-- --------------------------------------------------------

--
-- Structure de la table `cowsmodel`
--

CREATE TABLE `cowsmodel` (
  `cowsid` int(50) NOT NULL,
  `color` varchar(50) NOT NULL,
  `age` varchar(50) NOT NULL,
  `date` date NOT NULL,
  `weightatbirth` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `cowsmodel`
--

INSERT INTO `cowsmodel` (`cowsid`, `color`, `age`, `date`, `weightatbirth`) VALUES
(1, 'bbbb', '1', '2022-12-07', '40'),
(2, 'black/white', '1', '2022-12-07', '40'),
(3, 'white', '2', '2022-12-14', '20');

-- --------------------------------------------------------

--
-- Structure de la table `employee`
--

CREATE TABLE `employee` (
  `EmployeeId` int(10) NOT NULL,
  `CIN` int(40) NOT NULL,
  `Absence` varchar(40) NOT NULL,
  `date` date NOT NULL,
  `salary` int(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `employee`
--

INSERT INTO `employee` (`EmployeeId`, `CIN`, `Absence`, `date`, `salary`) VALUES
(1, 12345, 'oui', '2023-01-03', 123);

-- --------------------------------------------------------

--
-- Structure de la table `foodmodel`
--

CREATE TABLE `foodmodel` (
  `id` int(10) NOT NULL,
  `type` varchar(255) NOT NULL,
  `quantity` double NOT NULL,
  `date` date NOT NULL,
  `price` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `foodmodel`
--

INSERT INTO `foodmodel` (`id`, `type`, `quantity`, `date`, `price`) VALUES
(2, 'Feed', 22, '2022-12-01', 2),
(3, 'Straw', 22, '2023-01-02', 3),
(7, 'Feed', 33, '2022-12-04', 44);

-- --------------------------------------------------------

--
-- Structure de la table `milkpro`
--

CREATE TABLE `milkpro` (
  `cowid` int(10) NOT NULL,
  `ammilk` int(40) NOT NULL,
  `pmmilk` int(40) NOT NULL,
  `totalmilk` int(40) NOT NULL,
  `date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `milkpro`
--

INSERT INTO `milkpro` (`cowid`, `ammilk`, `pmmilk`, `totalmilk`, `date`) VALUES
(1, 12, 20, 32, '2022-12-04'),
(2, 12, 23, 35, '2022-12-04');

-- --------------------------------------------------------

--
-- Structure de la table `milksales`
--

CREATE TABLE `milksales` (
  `name` varchar(40) NOT NULL,
  `CINClient` varchar(40) NOT NULL,
  `Quantity` float NOT NULL,
  `date` date NOT NULL,
  `Price` float NOT NULL,
  `Total` int(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `milksales`
--

INSERT INTO `milksales` (`name`, `CINClient`, `Quantity`, `date`, `Price`, `Total`) VALUES
('hamid', '55', 44, '2023-01-11', 78, 988);

-- --------------------------------------------------------

--
-- Structure de la table `users`
--

CREATE TABLE `users` (
  `email_address` varchar(40) NOT NULL,
  `user_fullName` varchar(40) NOT NULL,
  `user_password` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `users`
--

INSERT INTO `users` (`email_address`, `user_fullName`, `user_password`) VALUES
('hamid@gmail.com', 'hamid', '2004'),
('mohssin@gmail.com', 'mohssin', '12345'),
('abdlali@gmail.com', 'abdlali', '1234'),
('hamza@gmail.com', 'hamza', '12345'),
('abdlali@gmail.com', 'abdlali', '123456'),
('admin@gmail.com', 'Admin1', '123456'),
('xjkbck', 'gf', '123');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `animal_health`
--
ALTER TABLE `animal_health`
  ADD PRIMARY KEY (`cowid`);

--
-- Index pour la table `cowsmodel`
--
ALTER TABLE `cowsmodel`
  ADD PRIMARY KEY (`cowsid`);

--
-- Index pour la table `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`EmployeeId`);

--
-- Index pour la table `foodmodel`
--
ALTER TABLE `foodmodel`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `milkpro`
--
ALTER TABLE `milkpro`
  ADD PRIMARY KEY (`cowid`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `animal_health`
--
ALTER TABLE `animal_health`
  MODIFY `cowid` int(40) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT pour la table `cowsmodel`
--
ALTER TABLE `cowsmodel`
  MODIFY `cowsid` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=78;

--
-- AUTO_INCREMENT pour la table `employee`
--
ALTER TABLE `employee`
  MODIFY `EmployeeId` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `milkpro`
--
ALTER TABLE `milkpro`
  MODIFY `cowid` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
