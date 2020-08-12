-- phpMyAdmin SQL Dump
-- version 4.9.3
-- https://www.phpmyadmin.net/
--
-- Host: localhost:8889
-- Generation Time: Aug 12, 2020 at 01:01 PM
-- Server version: 5.7.26
-- PHP Version: 7.1.32

SET FOREIGN_KEY_CHECKS=0;
SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `library`
--
CREATE DATABASE IF NOT EXISTS `library` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `library`;

-- --------------------------------------------------------

--
-- Table structure for table `author`
--

DROP TABLE IF EXISTS `author`;
CREATE TABLE `author` (
  `id` bigint(20) NOT NULL,
  `first_name` varchar(100) NOT NULL,
  `last_name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `author`
--

INSERT INTO `author` (`id`, `first_name`, `last_name`) VALUES
(2, 'Antoine', 'De Saint-Exupéry'),
(6, 'Stephen', 'King'),
(7, 'Harlan', 'Coben'),
(8, 'Jeff', 'Lemire'),
(9, 'Bill', 'Willingham'),
(21, 'Bertrand', 'Meyer');

-- --------------------------------------------------------

--
-- Table structure for table `document`
--

DROP TABLE IF EXISTS `document`;
CREATE TABLE `document` (
  `id` bigint(20) NOT NULL,
  `category` varchar(20) NOT NULL,
  `description` longtext NOT NULL,
  `isbn` varchar(20) NOT NULL,
  `publication_date` date NOT NULL,
  `title` varchar(250) NOT NULL,
  `type` varchar(10) NOT NULL,
  `author_id` bigint(20) NOT NULL,
  `photo_id` bigint(20) DEFAULT NULL,
  `publisher_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `document`
--

INSERT INTO `document` (`id`, `category`, `description`, `isbn`, `publication_date`, `title`, `type`, `author_id`, `photo_id`, `publisher_id`) VALUES
(4, 'NOVEL', 'Le récit des aventures poétiques et symboliques d\'un petit garçon venu d\'une autre planète, et de sa rencontre avec un aviateur échoué dans le désert du Sahara.', '978-2-07-061275-8', '2007-02-01', 'Le petit prince', 'BOOK', 2, 0, 3),
(15, 'SCIENCE_FICTION', 'Quatre nouvelles puissantes et dérangeantes, quatre personnages confrontés à des situations extrêmes qui vont les faire basculer du côté obscur, plus une nouvelle inédite vraiment inquiétante... Vous qui entrez ici, perdez tout espoir', '978-2-253-19523-8', '2014-05-21', 'Nuit noire, étoiles mortes', 'BOOK', 6, 1, 10),
(16, 'SCIENCE_FICTION', 'Imaginez une brume qui s\'abat soudainement sur une petite ville, une brume si épaisse que les clients d\'un supermarché hésitent à en ressortir. Il n\'en faut pas davantage au maître de l\'épouvante pour nous plonger dans le cauchemar avec un réalisme hallucinant. Chacune des nouvelles de ce volume possède le même pouvoir ensorcelant. Vous serez terrifié par un petit singe en peluche qui joue des cymbales. Vous redouterez de voir surgir le fantôme d\'un... camion. Vous saurez de quoi est capable un naufragé solitaire, lorsque la faim le tenaille et que la drogue décuple son courage. L\'art de faire surgir l\'effrayant ou le surnaturel au cœur d\'un monde rassurant et prosaïque... Les superbes décors du Maine, où vit Stephen King, se révèlent une fois encore peuplés de sortilèges et de malédictions. ', '978-2-253-15159-3', '2006-06-07', 'Brume', 'BOOK', 6, 2, 10),
(17, 'SCIENCE_FICTION', 'Situé dans les montagnes Rocheuses, l\'Overlook Palace passe pour être l\'un des plus beaux lieux du monde. Confort, luxe, volupté. L\'hiver, l\'hôtel est fermé. Coupé du monde par le froid et la neige. Alors, seul l\'habite un gardien. Celui qui a été engagé cet hiver-là s\'appelle Jack Torrance : c\'est un alcoolique, un écrivain raté, qui tente d\'échapper au désespoir. Avec lui vivent sa femme, Wendy, et leur enfant, Danny.Danny qui possède le don de voir, de ressusciter les choses et les êtres que l\'on croit disparus. Ce qu\'il sent, lui, dans les cent dix chambres vides de l\'Overlook Palace, c\'est la présence du démon. Cauchemar ou réalité, le corps de cette femme assassinée ? ces bruits de fête qui dérivent dans les couloirs ? Cette vie si étrange qui anime l\'hôtel ? Un récit envoûtant immortalisé à l\'écran par Stanley Kubrick.', '978-2-253-15162-3', '2007-10-31', 'Shining', 'BOOK', 6, 3, 10),
(19, 'THRILLER', 'Paul Copeland a mis vingt ans pour accepter l\'idée que sa soeur, comme trois autres adolescents cette nuit-là, est morte assassinée dans le camp de vacances du lac Charmaine. Même si deux corps seulement ont été retrouvés dans les bois, les chances de revoir Camille vivante se sont évanouies avec le temps. Aujourd\'hui, Paul est à la morgue et c\'est tout son passé qui lui saute à la gorge. Devant ses yeux, un espoir fou.Le cadavre d\'un homme. L\'autre adolescent porté disparu... ', '978-2-266-20763-8', '2011-09-01', 'Dans les bois', 'BOOK', 7, 4, 11),
(20, 'THRILLER', 'Et si l\'amour repoussait les frontières de la mort ? Une exceptionnelle histoire d\'amour mêlée d\'émotion et de tension dramatique. David Beck et sa femme Elizabeth ont vingt-cinq ans et s\'aiment depuis l\'enfance. Comme chaque année, ils retournent sur les lieux de leur premier baiser, le lac Charmaine, en Pennsylvanie. Mais la baignade tourne au cauchemar. Elizabeth est enlevée, David, assommé.Le cadavre d\'Elizabeth sera retrouvé non loin de là, marqué au fer rouge. Huit ans plus tard, le souvenir de cette nuit d\'effroi hante toujours David. À l\'approche de l\'anniversaire du drame, il reçoit un étrange email anonyme contenant un message codé dont seuls Elizabeth et lui connaissent la clé. Qui le lui a envoyé ? Dans quel but ? Si c\'est une blague, elle est de très mauvais goût : Elizabeth est morte ; son père, Hoyt Parker, un ancien inspecteur de la police new-yorkaise, a formellement identifié son corps.Et pourtant... Le jour suivant, David reçoit un nouveau message, clique sur le lien et découvre le site d\'une caméra de surveillance. Au milieu de la foule, un visage familier... Elizabeth ? Est-elle toujours en vie ? Pourquoi est-elle restée cachée aussi longtemps ? Pour retrouver sa femme, sa quête de vérité va mener David sur les traces d\'un passé au goût amer... ', '978-2-266-20770-6', '2011-09-01', 'Ne le dis à personne', 'BOOK', 7, 5, 11),
(22, 'TECHNOLOGY', 'Réédition d\'un des plus grands classiques de la littérature informatique. Ecrit dans un style d\'une clarté absolue, cet ouvrage réussit le tour de force d\'être à la fois un livre de réflexion approfondie sur les concepts objet et un ouvrage pratique, apportant des réponses concrètes aux questions que se posent tous les programmeurs et concepteurs de logiciels : comment construire des applications logicielles fiables, performantes, faciles à utiliser et à faire évoluer ? Théorie du typage, gestion de la mémoire, techniques d\'héritage, programmation concurrente, objets distribués, persistance et bases de données objet, analyse et conception, réutilisabilité... Tous les aspects du processus de développement logiciel et des technologies objet sont expliqués et discutés dans leurs moindres détails, dans un style brillant, souvent drôle et provocateur.', '978-2-212-67500-9', '2017-10-26', 'Conception et programmation orientée objet', 'BOOK', 21, 6, 12),
(23, 'SCIENCE_FICTION', 'Retrouvons Lucy Weber là où nous l\'avions laissé ! En devenant la nouvelle Black Hammer, la jeune femme prend la relève de son père et endosse les responsabilités qui incombent à de tels pouvoirs. Mais alors qu\'elle s\'apprête à révéler aux héros de la ferme comment ils s\'y sont retrouvés coincés et, surtout, comment s\'en échapper, Lucy disparaît aussi étrangement qu\'elle était apparue ! Piégée dans un monde peuplé de dieux emo, d\'hommes anthropomorphes et autres héros tous plus cinglés les uns que les autres, qui sera en mesure de la sauver ? BLACK HAMMER est la dernière création en date de Jeff LEMIRE.Après l\'anticipation post-apocalyptique de SWEET TOOTH et la science-fiction avec DESCENDER, voici la déclaration d\'amour de l\'auteur canadien pour les super-héros. Imaginée dès 2007, débutée en 2014 avec Dean ORMSTON, la série voit aujourd\'hui le jour et, avec l\'Eisner Award 2017 de la Nouvelle Meilleure Série récemment remporté, signe un nouveau succès pour l\'éditeur Dark Horse. ', '979-10-268-1646-1', '2019-07-05', 'Black Hammer Tome 3 : L\'heure du jugement', 'COMIC', 8, 7, 13),
(24, 'SCIENCE_FICTION', 'Plus inspiré d\'Andersen et des frères Grimm que de Walt Disney, Bill WillinghaM a imaginé avec Fables une saga qui tint ses lecteurs en haleine durant plus de dix ans, mêlant batailles épiques, destins tragiques et relations intimes en une fresque digne des plus grands classiques de la bande dessinée internationale. Découvrez le quotidien de Blanche-Neige, du Grand Méchant Loup, des Trois Petits Cochons, de Pinocchio et de toutes les autres créatures fabuleuses nées du folklore mondial, désormais contraintes de vivre ensemble...Sélectionnée au festival d\'Angoulême et récompensée par quinze Eisner Awards, la plus haute distinction de la bande dessinée américaine, Fables vous est aujourd\'hui proposée dans une édition intégrale en dix volumes.', '979-10-268-1391-0', '2018-01-19', 'Fables intégrale volume 1', 'COMIC', 9, 8, 13);

-- --------------------------------------------------------

--
-- Table structure for table `exemplar`
--

DROP TABLE IF EXISTS `exemplar`;
CREATE TABLE `exemplar` (
  `id` bigint(20) NOT NULL,
  `reference` varchar(45) NOT NULL,
  `document_id` bigint(20) NOT NULL,
  `library_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `exemplar`
--

INSERT INTO `exemplar` (`id`, `reference`, `document_id`, `library_id`) VALUES
(27, 'BOOK_9782070612758_1597229871157', 4, 1),
(28, 'BOOK_9782070612758_1597229873206', 4, 1),
(29, 'BOOK_9782070612758_1597229878304', 4, 25),
(30, 'BOOK_9782070612758_1597229880162', 4, 25),
(31, 'BOOK_9782070612758_1597229882052', 4, 25),
(32, 'BOOK_9782070612758_1597229883942', 4, 25),
(33, 'BOOK_9782070612758_1597229889142', 4, 26),
(34, 'BOOK_9782070612758_1597229890867', 4, 26),
(35, 'BOOK_9782253195238_1597229916527', 15, 25),
(36, 'BOOK_9782253195238_1597229917611', 15, 25),
(37, 'BOOK_9782253195238_1597229923447', 15, 26),
(38, 'BOOK_9782253151593_1597229931728', 16, 25),
(39, 'BOOK_9782253151623_1597229938156', 17, 25),
(40, 'BOOK_9782253151623_1597229939095', 17, 25),
(41, 'BOOK_9782253151623_1597229939875', 17, 25),
(42, 'BOOK_9782253151623_1597229945833', 17, 26),
(43, 'BOOK_9782253151623_1597229946712', 17, 26),
(44, 'BOOK_9782253151623_1597229951412', 17, 1),
(45, 'BOOK_9782266207638_1597229970644', 19, 25),
(46, 'BOOK_9782266207638_1597229973635', 19, 26),
(47, 'BOOK_9782266207706_1597229985471', 20, 26),
(48, 'BOOK_9782266207706_1597229988529', 20, 25),
(49, 'BOOK_9782266207706_1597229989426', 20, 25),
(50, 'BOOK_9782266207706_1597229994522', 20, 1),
(51, 'BOOK_9782212675009_1597230004004', 22, 25),
(52, 'COMIC_9791026816461_1597230020763', 23, 1),
(53, 'COMIC_9791026813910_1597230031324', 24, 26),
(54, 'COMIC_9791026813910_1597230034659', 24, 25);

-- --------------------------------------------------------

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(55),
(55),
(55),
(55),
(55),
(55),
(55),
(55),
(55);

-- --------------------------------------------------------

--
-- Table structure for table `library`
--

DROP TABLE IF EXISTS `library`;
CREATE TABLE `library` (
  `id` bigint(20) NOT NULL,
  `name` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `library`
--

INSERT INTO `library` (`id`, `name`) VALUES
(1, 'Bibliothèque Stephen King'),
(25, 'Grande bibliothèque de Lyon'),
(26, 'Bibliothèque Voltaire');

-- --------------------------------------------------------

--
-- Table structure for table `loan`
--

DROP TABLE IF EXISTS `loan`;
CREATE TABLE `loan` (
  `id` bigint(20) NOT NULL,
  `end_date` date DEFAULT NULL,
  `renewed` bit(1) NOT NULL,
  `start_date` date DEFAULT NULL,
  `exemplar_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `photo`
--

DROP TABLE IF EXISTS `photo`;
CREATE TABLE `photo` (
  `id` bigint(20) NOT NULL,
  `extension` varchar(10) NOT NULL,
  `name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `photo`
--

INSERT INTO `photo` (`id`, `extension`, `name`) VALUES
(0, 'jpg', '978-2-07-061275-8.jpg'),
(1, 'jpg', '978-2-253-19523-8.jpg'),
(2, 'jpg', '978-2-253-15159-3.jpg'),
(3, 'jpg', '978-2-253-15162-3.jpg'),
(4, 'jpg', '978-2-266-20763-8.jpg'),
(5, 'jpg', '978-2-266-20770-6.jpg'),
(6, 'jpg', '978-2-212-67500-9.jpg'),
(7, 'jpg', '979-10-268-1646-1.jpg'),
(8, 'jpg', '979-10-268-1391-0.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `publisher`
--

DROP TABLE IF EXISTS `publisher`;
CREATE TABLE `publisher` (
  `id` bigint(20) NOT NULL,
  `name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `publisher`
--

INSERT INTO `publisher` (`id`, `name`) VALUES
(3, 'Gallimard'),
(10, 'Livre de poche'),
(11, 'Pocket'),
(12, 'Eyrolles'),
(13, 'Urban comics');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(50) NOT NULL,
  `role` varchar(10) NOT NULL,
  `username` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `author`
--
ALTER TABLE `author`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `document`
--
ALTER TABLE `document`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKdm08gx0272u3a8itdalt354f5` (`author_id`),
  ADD KEY `FK4u1bbcqehjkfsq3s4ni83fujj` (`photo_id`),
  ADD KEY `FK7bopjhbrlf99vr3ffs4gm5k87` (`publisher_id`);

--
-- Indexes for table `exemplar`
--
ALTER TABLE `exemplar`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKhsfykd16vqktvcctm4k22p174` (`document_id`),
  ADD KEY `FKerq4cxoaekqq4ygk7safc9nos` (`library_id`);

--
-- Indexes for table `library`
--
ALTER TABLE `library`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `loan`
--
ALTER TABLE `loan`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKfyjd7wjh8wose8rsu6x7ddvb5` (`exemplar_id`),
  ADD KEY `FKxxm1yc1xty3qn1pthgj8ac4f` (`user_id`);

--
-- Indexes for table `photo`
--
ALTER TABLE `photo`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `publisher`
--
ALTER TABLE `publisher`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `document`
--
ALTER TABLE `document`
  ADD CONSTRAINT `FK4u1bbcqehjkfsq3s4ni83fujj` FOREIGN KEY (`photo_id`) REFERENCES `photo` (`id`),
  ADD CONSTRAINT `FK7bopjhbrlf99vr3ffs4gm5k87` FOREIGN KEY (`publisher_id`) REFERENCES `publisher` (`id`),
  ADD CONSTRAINT `FKdm08gx0272u3a8itdalt354f5` FOREIGN KEY (`author_id`) REFERENCES `author` (`id`);

--
-- Constraints for table `exemplar`
--
ALTER TABLE `exemplar`
  ADD CONSTRAINT `FKerq4cxoaekqq4ygk7safc9nos` FOREIGN KEY (`library_id`) REFERENCES `library` (`id`),
  ADD CONSTRAINT `FKhsfykd16vqktvcctm4k22p174` FOREIGN KEY (`document_id`) REFERENCES `document` (`id`);

--
-- Constraints for table `loan`
--
ALTER TABLE `loan`
  ADD CONSTRAINT `FKfyjd7wjh8wose8rsu6x7ddvb5` FOREIGN KEY (`exemplar_id`) REFERENCES `exemplar` (`id`),
  ADD CONSTRAINT `FKxxm1yc1xty3qn1pthgj8ac4f` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);
SET FOREIGN_KEY_CHECKS=1;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
