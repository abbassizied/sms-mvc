-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 16, 2024 at 10:31 PM
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
-- Database: `db_sms_2024`
--

-- --------------------------------------------------------

--
-- Table structure for table `chat_messages`
--

CREATE TABLE `chat_messages` (
  `id` bigint(20) NOT NULL,
  `created_at` datetime(6) NOT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `message_content` text DEFAULT NULL,
  `message_type` enum('PRIVATE_CHAT','PUBLIC_CHAT') DEFAULT NULL,
  `to_user_id` bigint(20) DEFAULT NULL,
  `from_user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `contact_messages`
--

CREATE TABLE `contact_messages` (
  `id` bigint(20) NOT NULL,
  `created_at` datetime(6) NOT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `message_content` text DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `first_name` varchar(20) NOT NULL,
  `last_name` varchar(20) NOT NULL,
  `subject` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `customers`
--

CREATE TABLE `customers` (
  `id` bigint(20) NOT NULL,
  `created_at` datetime(6) NOT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `phone` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `images`
--

CREATE TABLE `images` (
  `id` bigint(20) NOT NULL,
  `created_at` datetime(6) NOT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `url` varchar(255) NOT NULL,
  `product_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `images`
--

INSERT INTO `images` (`id`, `created_at`, `updated_at`, `url`, `product_id`) VALUES
(1, '2024-12-16 20:22:58.000000', '2024-12-16 20:22:58.000000', '1734380578185_galaxy-a04e-3-64go-2.jpg', 1),
(2, '2024-12-16 20:22:58.000000', '2024-12-16 20:22:58.000000', '1734380578187_galaxy-a04e-3-64go-3.jpg', 1),
(3, '2024-12-16 20:25:15.000000', '2024-12-16 20:25:15.000000', '1734380715668_galaxy-a05-6128-go-2.jpg', 2),
(4, '2024-12-16 20:25:15.000000', '2024-12-16 20:25:15.000000', '1734380715669_galaxy-a05-6128-go-3.jpg', 2),
(5, '2024-12-16 20:25:15.000000', '2024-12-16 20:25:15.000000', '1734380715672_galaxy-a05-6128-go-4.jpg', 2),
(6, '2024-12-16 20:27:48.000000', '2024-12-16 20:27:48.000000', '1734380868975_samsung-galaxy-z-flip-6-2.jpg', 3),
(7, '2024-12-16 20:27:48.000000', '2024-12-16 20:27:48.000000', '1734380868977_samsung-galaxy-z-flip-6-3.jpg', 3),
(8, '2024-12-16 20:27:48.000000', '2024-12-16 20:27:48.000000', '1734380868978_samsung-galaxy-z-flip-6-4.jpg', 3),
(9, '2024-12-16 20:27:48.000000', '2024-12-16 20:27:48.000000', '1734380868979_samsung-galaxy-z-flip-6-5.jpg', 3),
(10, '2024-12-16 20:27:48.000000', '2024-12-16 20:27:48.000000', '1734380868981_samsung-galaxy-z-flip-6-6.jpg', 3),
(11, '2024-12-16 20:30:47.000000', '2024-12-16 20:30:47.000000', '1734381047472_samsung-galaxy-z-fold-6-2.jpg', 4),
(12, '2024-12-16 20:30:47.000000', '2024-12-16 20:30:47.000000', '1734381047473_samsung-galaxy-z-fold-6-3.jpg', 4),
(13, '2024-12-16 20:30:47.000000', '2024-12-16 20:30:47.000000', '1734381047474_samsung-galaxy-z-fold-6-4.jpg', 4),
(14, '2024-12-16 20:30:47.000000', '2024-12-16 20:30:47.000000', '1734381047476_samsung-galaxy-z-fold-6-5.jpg', 4),
(15, '2024-12-16 20:32:20.000000', '2024-12-16 20:32:20.000000', '1734381140025_galaxy-s24-fe-2.jpg', 5),
(16, '2024-12-16 20:32:20.000000', '2024-12-16 20:32:20.000000', '1734381140027_galaxy-s24-fe-3.jpg', 5),
(17, '2024-12-16 20:41:32.000000', '2024-12-16 20:41:32.000000', '1734381692026_vivre-auto-toyota-hilux-essai-09.jpg', 6),
(18, '2024-12-16 20:47:05.000000', '2024-12-16 20:47:05.000000', '1734382025388_TOYOTA-Yaris-Cross-2021-Neuve-Maroc-13.jpg', 7),
(19, '2024-12-16 20:47:05.000000', '2024-12-16 20:47:05.000000', '1734382025390_TOYOTA-Yaris-Cross-2021-Neuve-Maroc-16.jpg', 7),
(20, '2024-12-16 20:47:05.000000', '2024-12-16 20:47:05.000000', '1734382025391_TOYOTA-Yaris-Cross-2021-Neuve-Maroc-17.jpg', 7),
(21, '2024-12-16 20:55:57.000000', '2024-12-16 20:55:57.000000', '1734382557746_iphone-15-128go-midnight-apple1.jpg', 8),
(22, '2024-12-16 20:55:57.000000', '2024-12-16 20:55:57.000000', '1734382557748_iphone-15-128go-midnight-apple2.jpg', 8),
(23, '2024-12-16 20:55:57.000000', '2024-12-16 20:55:57.000000', '1734382557749_iphone-15-128go-midnight-apple11.jpg', 8),
(24, '2024-12-16 20:58:50.000000', '2024-12-16 20:58:50.000000', '1734382730020_iphone-15-pro-max-512go-blanc-titanium-apple1.jpg', 9),
(25, '2024-12-16 20:58:50.000000', '2024-12-16 20:58:50.000000', '1734382730021_iphone-15-pro-max-512go-blanc-titanium-apple2.jpg', 9),
(26, '2024-12-16 20:58:50.000000', '2024-12-16 20:58:50.000000', '1734382730023_iphone-15-pro-max-512go-blanc-titanium-apple3.jpg', 9),
(27, '2024-12-16 21:00:12.000000', '2024-12-16 21:00:12.000000', '1734382812235_iphone-16-pro-max-256go-blanc-titane-apple1.jpg', 10),
(28, '2024-12-16 21:00:12.000000', '2024-12-16 21:00:12.000000', '1734382812237_iphone-16-pro-max-256go-blanc-titane-apple2.jpg', 10),
(29, '2024-12-16 21:00:12.000000', '2024-12-16 21:00:12.000000', '1734382812239_iphone-16-pro-max-256go-blanc-titane-apple3.jpg', 10),
(30, '2024-12-16 21:09:31.000000', '2024-12-16 21:09:31.000000', '1734383371035_apple-macbook-air-m1-8go-256go-ssd-gris-1.jpg', 11),
(31, '2024-12-16 21:09:31.000000', '2024-12-16 21:09:31.000000', '1734383371037_apple-macbook-air-m1-8go-256go-ssd-gris-3.jpg', 11),
(32, '2024-12-16 21:09:31.000000', '2024-12-16 21:09:31.000000', '1734383371038_apple-macbook-air-m1-8go-256go-ssd-gris-4.jpg', 11),
(33, '2024-12-16 21:11:02.000000', '2024-12-16 21:11:02.000000', '1734383462035_apple-watch-se-gps-44mm-starlight-aluminium.jpg', 12),
(34, '2024-12-16 21:11:02.000000', '2024-12-16 21:11:02.000000', '1734383462037_apple-watch-se-gps-44mm-starlight-aluminium1 (1).jpg', 12),
(35, '2024-12-16 21:11:02.000000', '2024-12-16 21:11:02.000000', '1734383462038_apple-watch-se-gps-44mm-starlight-aluminium1.jpg', 12),
(36, '2024-12-16 21:15:22.000000', '2024-12-16 21:15:22.000000', '1734383722989_download.jpeg', 13);

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `id` bigint(20) NOT NULL,
  `created_at` datetime(6) NOT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `order_date` date NOT NULL,
  `order_status` varchar(255) NOT NULL,
  `total_amount` double NOT NULL,
  `customer_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `order_items`
--

CREATE TABLE `order_items` (
  `id` bigint(20) NOT NULL,
  `created_at` datetime(6) NOT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `quantity` int(11) NOT NULL,
  `order_id` bigint(20) NOT NULL,
  `product_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `privileges`
--

CREATE TABLE `privileges` (
  `id` bigint(20) NOT NULL,
  `created_at` datetime(6) NOT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `name` enum('ROLE_DELETE','ROLE_READ','ROLE_UPDATE','ROLE_WRITE','USER_DELETE','USER_READ','USER_UPDATE','USER_WRITE') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `privileges`
--

INSERT INTO `privileges` (`id`, `created_at`, `updated_at`, `name`) VALUES
(1, '2024-12-16 20:08:14.000000', '2024-12-16 20:08:14.000000', 'USER_READ'),
(2, '2024-12-16 20:08:14.000000', '2024-12-16 20:08:14.000000', 'USER_WRITE'),
(3, '2024-12-16 20:08:14.000000', '2024-12-16 20:08:14.000000', 'USER_UPDATE'),
(4, '2024-12-16 20:08:14.000000', '2024-12-16 20:08:14.000000', 'USER_DELETE'),
(5, '2024-12-16 20:08:14.000000', '2024-12-16 20:08:14.000000', 'ROLE_READ'),
(6, '2024-12-16 20:08:14.000000', '2024-12-16 20:08:14.000000', 'ROLE_WRITE'),
(7, '2024-12-16 20:08:14.000000', '2024-12-16 20:08:14.000000', 'ROLE_UPDATE'),
(8, '2024-12-16 20:08:14.000000', '2024-12-16 20:08:14.000000', 'ROLE_DELETE');

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE `products` (
  `id` bigint(20) NOT NULL,
  `created_at` datetime(6) NOT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `description` text DEFAULT NULL,
  `initial_quantity` int(11) NOT NULL,
  `main_image` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `price` double NOT NULL,
  `quantity` int(11) NOT NULL,
  `supplier_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`id`, `created_at`, `updated_at`, `description`, `initial_quantity`, `main_image`, `name`, `price`, `quantity`, `supplier_id`) VALUES
(1, '2024-12-16 20:22:58.000000', '2024-12-16 20:22:58.000000', '- Écran 6,5 pouces HD+\r\n- Caméra arrière 13.0 MP + 2.0 MP\r\n- Stockage (Go) 64 Go\r\n- Batterie 5000 mAh et charge rapide 15W - Une batterie longue durée qui peut être rechargée rapidement', 300, '1734380578181_galaxy-a04e-3-64go-1.jpg', 'Galaxy A04e (3/64Go)', 489, 300, 1),
(2, '2024-12-16 20:25:15.000000', '2024-12-16 20:25:15.000000', '- 6,7\" HD+ pour une expérience visuelle immersive\r\n- Double caméra multi-rôle avec caméra principale 50MP\r\n- Batterie longue durée de 5 000 mAh pour une utilisation toute la journée avec une charge ultra rapide de 25 W', 20, '1734380715666_galaxy-a05-6128-go-1.jpg', 'Galaxy A05 (6/128 Go)', 549, 20, 1),
(3, '2024-12-16 20:27:48.000000', '2024-12-16 20:27:48.000000', '- Découvrez le Galaxy Z Flip 6, l\'outil d\'expression ultime pour se démarquer. Plus compact et stylé que jamais, il brille par la puissance de Galaxy AI et les capacités photos de son tout nouveau capteur 50MP.\r\n\r\n- Galaxy Z Flip 6 est le plus compact à ce jour avec sa conception pliable unique, qui vient facilement se glisser dans vos sacs et poches les plus contenus\r\n\r\n- Avec Galaxy Z Flip 6 vous pouvez immortaliser des clichés sensationnels de jour comme de nuit. Avec la puissance du moteur ProVisual associé au nouveau mode Nuit, les images photographiées révèlent chaque détail.\r\n\r\n- Couplée au Snapdragon® 8 Gen 3 pour Galaxy, vous avez là assez de puissance pour jouer, Lecture audio jusqu\'à68 h et lecture vidéo jusqu\'à 23h.', 120, '1734380868973_samsung-galaxy-z-flip-6-1.jpg', 'Galaxy Z Flip 6', 4799, 120, 1),
(4, '2024-12-16 20:30:47.000000', '2024-12-16 20:30:47.000000', 'Le Galaxy Z Fold 6 est plus léger et plus fin qui se glisse partout. Il est doté d\'un écran incroyablement lumineux. Grâce à la technologie Vision Booster, vous profitez de vos sessions de jeu, même en plein soleil. Le processeur Snapdragon® 8 Gen 3 est idéal pour gérer vos jeux les plus gourmands. Optimisé pour le Galaxy Z Fold6, vous profitez alors de graphismes ultra réalistes et d\'une fluidité à couper le souffle. Le Galaxy Z Fold6 fusionne optimisations logicielles et matérielles. Sa gestion intelligente de l\'énergie, couplée à sa batterie de 4400mAh, en fait donc un modèle d\'endurance qui vous suivra nuit et jour. Avec ses 3 capteurs et son nouveau moteur ProVisual, le Galaxy Z Fold6 dispose du système photo le plus avancé à ce jour sur un Galaxy Z.', 560, '1734381047470_samsung-galaxy-z-fold-6-1.jpg', 'Galaxy Z Fold6', 7999, 560, 1),
(5, '2024-12-16 20:32:20.000000', '2024-12-16 20:32:20.000000', 'Le Galaxy S24 FE est un nouveau smartphone Samsung Galaxy qui offre des capacités d’IA avancées et de meilleures performances pour un usage quotidien, avec des fonctionnalités pratiques telles que le partage instantané via Quick Share et une protection supplémentaire des données avec Samsung Knox. \r\n\r\nPasser de votre ancien téléphone au Galaxy S24 FE est un processus simple et rapide. Vous pouvez transférer vos données, paramètres et fichiers en quelques minutes grâce à Samsung Smart Switch.\r\n\r\nRedimensionnez-le, retouchez-le avec Photo Assist\r\n\r\nDe superbes portraits en basse lumière avec le moteur ProVisual de Galaxy\r\n\r\nExpérience de jeu hyper réaliste.', 250, '1734381140024_galaxy-s24-fe-1.jpg', 'Galaxy S24 FE', 2990, 250, 1),
(6, '2024-12-16 20:41:32.000000', '2024-12-16 20:41:32.000000', 'Toyota Hilux Double Cabine 2.4 L Diesel D-4D 4x4 BVA', 30, '1734381692024_Toyota_Hilux_MD_0.jpg', 'Toyota Hilux Double Cabine', 92900, 30, 2),
(7, '2024-12-16 20:47:05.000000', '2024-12-16 20:47:05.000000', 'Toyota Yaris Hybride 1.5 L CVT France', 360, '1734382025386_TOYOTA-Yaris-Cross-2021-Neuve-Maroc-12.jpg', 'Toyota Yaris Cross Hybride', 85000, 360, 2),
(8, '2024-12-16 20:55:57.000000', '2024-12-16 20:55:57.000000', 'Écran 6,1\" OLED Super Retina XDR HDR10, Dolby Vision - Résolution: 2556 x 1179 pixels à 460 ppp - Processeur: Puce A16 Bionic (4nm) Hexa-core (2x3,46 GHz Everest + 4x2,02 GHz en dents de scie) - GPU Apple (graphiques 5 cœurs) - Système d\'exploitation: iOS 17 - Mémoire RAM: 6 Go - Stockage: 128Go - Appareil photo Arrière: DualPixels: 48 MP, f/1,6, 26 mm + 12 MP, f/2,4, 13 mm, Zoom numérique jusqu\'à 10x - Appareil Avant: 12 MégaPixels, f/1.9 - Vidéo 4K à 24/25/30/60 ip - Son stéréo - Connectivité: 5G NR, Wi‑Fi 6, Bluetooth 5.3, USB Type-C 2.0 - Autonomie jusqu’à 20 heures de lecture vidéo, Streaming vidéo : Jusqu’à 16 heures, Lecture audio : Jusqu’à 80 heures - Batterie: Li-Ion 3349 mAh - Charge rapide, PD2.0, 50% en 30 min - 15W sans fil (MagSafe) - Face ID - Apple Pay - Siri - NFC - Indice de protection IP68 - Détection des accidents - Appel d’urgence - Couleur: Noir - Garantie: 1 an', 50, '1734382557744_iphone-15-128go-midnight-apple.jpg', 'iPhone 15', 2399, 50, 3),
(9, '2024-12-16 20:58:50.000000', '2024-12-16 20:58:50.000000', 'Écran 6,7\" OLED Super Retina XDR - Résolution: 1290 x 2796 pixels - Processeur: Apple A17 Pro bionic (3nm) Hexa-core (2x3,78 GHz + 4x2,11 GHz) - GPU Apple graphiques 6 cœurs - Système d\'exploitation: iOS 17 - Mémoire RAM: 8Go - Stockage: 512Go - Appareil photo Arrière: Trio Pixels: 48 MP, f/1,78, 24 mm + 12 MP, f/1.78, 48 mm + 12 MP, f/2.2, 13 mm - Appareil Avant: 12 MP,  f/2.8, 120mm , Zoom numérique jusqu\'à 25x - Video 4K à 24/25/30/60 ips, 1080p à 25/30/60/120 ips, gyro-EIS - Connectivité: 5G NR, Wi‑Fi 6, Bluetooth 5.3 et USB Type-C 3.2 Gen 2 - Streaming vidéo : jusqu’à 25 heures, Lecture Vidéo : jusqu’à 29 heures, Lecture Audio : jusqu’à 95 heures - Batterie: Lithium-ion 4441mAh - Charge rapide jusqu’à 50% en 30 min - 15W sans fil (MagSafe) - sans fil Qi jusqu’à 7,5 W -  Face ID - Apple Pay - Siri - NFC - Indice de protection IP68 - Détection des accidents - Appel d’urgence - Couleur: Blanc Titanium - Garantie: 1 an', 29, '1734382730018_iphone-15-pro-max-512go-blanc-titanium-apple.jpg', 'iPhone 15 Pro Max 512Go Blanc Titanium - APPLE', 8099, 29, 3),
(10, '2024-12-16 21:00:12.000000', '2024-12-16 21:00:12.000000', 'Écran 6,9\" OLED Super Retina XDR (2868 x 1320 à 460 ppp) - Design en titane - Processeur: Apple A18 Pro (3 nm) Hexa-core (2x4,04 GHz + 4x2,X GHz) - GPU Apple graphiques 6 cœurs - Système d\'exploitation: iOS 18 - Mémoire RAM: 8Go - Stockage: 256Go - Appareil photo Arrière: Trio Fusion 48 MP f/1,8, 24 mm + 12 MP, f/2,8, 120 mm + 48 MP f/2,2, 13 mm - Zoom numérique jusqu’à 25x - Appareil Avant: 12 MP, f/1.9, 23 mm - Commande de l’appareil photo - Video Dolby Vision 4K - Connectivité: Wi‑Fi, 5G, Bluetooth 5.3 et USB Type-C - Streaming vidéo: jusqu’à 29 heures, Lecture Vidéo : jusqu’à 33 heures, Lecture Audio : jusqu’à 105 heures - Batterie: Li-Ion 4685 mAh - Charge rapide jusqu’à 50% en 30 min - 25W sans fil (MagSafe) - sans fil Qi jusqu’à 7,5 W - Face ID - Apple Pay - Siri - NFC - Indice de protection IP68 - Détection des accidents - SOS d’urgence par satellite - Couleur: Blanc Titane - Garantie: 1 an', 26, '1734382812233_iphone-16-pro-max-256go-blanc-titane-apple.jpg', 'iPhone 16 Pro Max 256Go Blanc Titane - APPLE', 7549, 26, 3),
(11, '2024-12-16 21:09:31.000000', '2024-12-16 21:09:31.000000', 'Écran 13.3\" Retina LED IPS (2560 x 1600 pixels) - Processeur: Apple M1 (CPU 8 coeurs / GPU 7 coeurs / Neural Engine 16 coeurs) - Système d\'exploitation: MacOS - Mémoire RAM: 8 Go - Disque Dur: 256 Go SSD avec Wi-Fi, Bluetooth, 2x Thunderbolt/USB 4, Prise casque 3,5 mm - Capteur Touch ID, Clavier Magic Rétroéclairé - Couleur: Gris - Garantie: 1 an  ', 250, '1734383371034_apple-macbook-air-m1-8go-256go-ssd-gris.jpg', 'APPLE MacBook Air M1 8Go 256Go SSD - Gris', 2999, 250, 3),
(12, '2024-12-16 21:11:02.000000', '2024-12-16 21:11:02.000000', 'Écran Tactile Retina OLED LTPO - Système d\'exploitation: iOS - Puce SiP S8 avec processeur bicœur 64 bits - Boitier en aluminium: 44mm - Connectivité Sans Fil: Bluetooth, WiFi, GPS - Siri - Apple Pay - GymKit - Haut-parleur et micro intégrés - Autonomie: jusqu\'à 18 heures - Capacité: 32 Go - Capteur optique de fréquence cardiaque de 2ᵉ génération - Appel d’urgence - Détection des accidents - Détection des chutes - Boussole - Altimètre toujours activé - Capteur de luminosité ambiante - Résistance à l’eau jusqu’à 50 mètres - Couleur: starlight - Garantie: 1an', 20, '1734383462034_apple-watch-se-gps-44mm-starlight-aluminium (1).jpg', 'APPLE Watch SE GPS 44mm - Starlight Aluminium', 1399, 20, 3),
(13, '2024-12-16 21:15:22.000000', '2024-12-16 21:15:22.000000', 'Ingredient(s) :\r\nWater, sugar, concentrated orange juice, acidity regulator E330, thickeners (E440, E466) Anti-oxygen E300, beta carotene dye E160a, natural flavor\r\nInstruction for use and storage\r\n• Storage before opening: Store in a cool and dry place, and keep away from sunlight.\r\n• Storage after opening: keep cold for 72 hours', 3000, '1734383722988_download (1).jpeg', 'Orange juice drink 20 CL', 6, 3000, 4),
(14, '2024-12-16 21:16:34.000000', '2024-12-16 21:16:34.000000', 'Ingredients\r\nWater, sugar, carbon dioxide, acidity regulator: citric\r\nacid, anti oxygen: ascorbic acid, preservatives\r\n(sodium benzoate, potassium sorbate), natural\r\nflavor.\r\n\r\nInstruction for use and storage\r\nStorage before opening: Store in a cool and dry\r\nplace, and keep away from sunlight.\r\nStorage after opening: keep cold for 72 hours.', 300, '1734383794069_delio.png', 'Carbonated drink flavored Mint 25OML', 6, 300, 4);

-- --------------------------------------------------------

--
-- Table structure for table `purchases`
--

CREATE TABLE `purchases` (
  `id` bigint(20) NOT NULL,
  `created_at` datetime(6) NOT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `total_amount` double NOT NULL,
  `supplier_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `purchases`
--

INSERT INTO `purchases` (`id`, `created_at`, `updated_at`, `total_amount`, `supplier_id`) VALUES
(1, '2024-12-16 21:28:54.000000', '2024-12-16 21:28:54.000000', 92900, 2),
(2, '2024-12-16 21:29:17.000000', '2024-12-16 21:29:17.000000', 192, 4),
(3, '2024-12-16 21:29:49.000000', '2024-12-16 21:29:49.000000', 62930, 1),
(4, '2024-12-16 21:30:03.000000', '2024-12-16 21:30:03.000000', 15192, 3),
(5, '2024-12-16 21:30:15.000000', '2024-12-16 21:30:15.000000', 510000, 2),
(6, '2024-12-16 21:30:29.000000', '2024-12-16 21:30:29.000000', 1508200, 2);

-- --------------------------------------------------------

--
-- Table structure for table `purchase_items`
--

CREATE TABLE `purchase_items` (
  `id` bigint(20) NOT NULL,
  `created_at` datetime(6) NOT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `quantity` int(11) NOT NULL,
  `sub_total_price` double DEFAULT NULL,
  `product_id` bigint(20) NOT NULL,
  `purchase_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `purchase_items`
--

INSERT INTO `purchase_items` (`id`, `created_at`, `updated_at`, `quantity`, `sub_total_price`, `product_id`, `purchase_id`) VALUES
(1, '2024-12-16 21:28:54.000000', '2024-12-16 21:28:54.000000', 1, 92900, 6, 1),
(2, '2024-12-16 21:29:17.000000', '2024-12-16 21:29:17.000000', 20, 120, 14, 2),
(3, '2024-12-16 21:29:17.000000', '2024-12-16 21:29:17.000000', 12, 72, 13, 2),
(4, '2024-12-16 21:29:49.000000', '2024-12-16 21:29:49.000000', 1, 489, 1, 3),
(5, '2024-12-16 21:29:49.000000', '2024-12-16 21:29:49.000000', 2, 1098, 2, 3),
(6, '2024-12-16 21:29:49.000000', '2024-12-16 21:29:49.000000', 3, 14397, 3, 3),
(7, '2024-12-16 21:29:49.000000', '2024-12-16 21:29:49.000000', 4, 31996, 4, 3),
(8, '2024-12-16 21:29:49.000000', '2024-12-16 21:29:49.000000', 5, 14950, 5, 3),
(9, '2024-12-16 21:30:03.000000', '2024-12-16 21:30:03.000000', 4, 9596, 8, 4),
(10, '2024-12-16 21:30:03.000000', '2024-12-16 21:30:03.000000', 4, 5596, 12, 4),
(11, '2024-12-16 21:30:15.000000', '2024-12-16 21:30:15.000000', 6, 510000, 7, 5),
(12, '2024-12-16 21:30:29.000000', '2024-12-16 21:30:29.000000', 8, 743200, 6, 6),
(13, '2024-12-16 21:30:29.000000', '2024-12-16 21:30:29.000000', 9, 765000, 7, 6);

-- --------------------------------------------------------

--
-- Table structure for table `roles`
--

CREATE TABLE `roles` (
  `id` bigint(20) NOT NULL,
  `created_at` datetime(6) NOT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `roles`
--

INSERT INTO `roles` (`id`, `created_at`, `updated_at`, `name`) VALUES
(1, '2024-12-16 20:08:14.000000', '2024-12-16 20:08:14.000000', 'ROLE_SUPER_ADMIN'),
(2, '2024-12-16 20:08:14.000000', '2024-12-16 20:08:14.000000', 'ROLE_USER'),
(3, '2024-12-16 21:27:15.000000', '2024-12-16 21:27:15.000000', 'ROLE_CUSTOMER');

-- --------------------------------------------------------

--
-- Table structure for table `roles_privileges`
--

CREATE TABLE `roles_privileges` (
  `role_id` bigint(20) NOT NULL,
  `privilege_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `roles_privileges`
--

INSERT INTO `roles_privileges` (`role_id`, `privilege_id`) VALUES
(1, 1),
(1, 2),
(1, 3),
(1, 4),
(1, 5),
(1, 6),
(1, 7),
(1, 8),
(3, 1);

-- --------------------------------------------------------

--
-- Table structure for table `suppliers`
--

CREATE TABLE `suppliers` (
  `id` bigint(20) NOT NULL,
  `created_at` datetime(6) NOT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `logo_url` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `phone` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `suppliers`
--

INSERT INTO `suppliers` (`id`, `created_at`, `updated_at`, `address`, `email`, `logo_url`, `name`, `phone`) VALUES
(1, '2024-12-16 20:16:16.000000', '2024-12-16 20:18:52.000000', 'Samsungshop Résidence Hayet, Rue Lac Biwa Berges du Lac, 1053 Tunis Tunisia', 'samsung.tunis@gmail.com', '1734380332045_samsung.jpg', 'Samsung Electronics Co., Ltd.', '71965565'),
(2, '2024-12-16 20:35:41.000000', '2024-12-16 20:35:41.000000', 'Z.I La Marsa (A côté de Promogro)', 'toyota@toyota.com.tn', '1734381341554_toyota-logo.png', 'BSB Toyota', '36404600'),
(3, '2024-12-16 20:51:46.000000', '2024-12-16 20:51:46.000000', 'Centre commercial Manar City-2092 Tunis-Tunisia', 'istore.manar@icloud.com', '1734382306790_apple-logo.png', 'Apple Tunisie', '36363493'),
(4, '2024-12-16 21:12:57.000000', '2024-12-16 21:12:57.000000', 'Immeuble JOUMANA, La Goulette (10,05 km) 2015 Tunis – Tunisie', 'contact@groupedelice.com.tn', '1734383577083_LOGO-MENU.png', 'Délice', '70024500');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` bigint(20) NOT NULL,
  `created_at` datetime(6) NOT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `active` bit(1) NOT NULL,
  `email` varchar(255) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `photo_url` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `created_at`, `updated_at`, `active`, `email`, `first_name`, `last_name`, `password`, `photo_url`) VALUES
(1, '2024-12-16 20:08:14.000000', '2024-12-16 20:08:14.000000', b'1', 'abbassizied@outlook.fr', 'Zied', 'Abbassi', '$2a$10$E7da18FlvR0Cavh.pvL78.L4CGM2yNXbPFqKOWTobVAcL5Kd0kBYC', NULL),
(2, '2024-12-16 21:22:34.000000', '2024-12-16 21:22:34.000000', b'1', 'emily.jones@example.com', 'Emily', 'Jones', '$2a$10$f/dNCDbJOgBCpEA4vqD8memLZzDTh4FDeuekDn2qHtayPLy0pEdtW', NULL),
(3, '2024-12-16 21:23:39.000000', '2024-12-16 21:23:39.000000', b'0', 'michael.smith@example.com', 'Michael', 'Smith', '$2a$10$vPz3MrFVCh9t3Zplh4sAje9Uzj.Gg4ztg1AmlrVTwYkMXqxuRMQvq', NULL),
(4, '2024-12-16 21:24:23.000000', '2024-12-16 21:24:23.000000', b'0', 'sophia.lee@example.com', 'Sophia', 'Lee', '$2a$10$3w9WChFl03C2gwYaJ/ohye4r0DDBttsRPgU1.jsTcAEP4oh8fLZUO', NULL),
(5, '2024-12-16 21:24:59.000000', '2024-12-16 21:24:59.000000', b'1', 'david.brown@example.com', 'David', 'Brown', '$2a$10$EgBwTRs92we43XKVrpUJFuBrQbW1pNXp9yYUJ/aIGrXPFDD9ECYS.', NULL),
(6, '2024-12-16 21:25:42.000000', '2024-12-16 21:25:42.000000', b'1', 'olivia.clark@example.com', 'Olivia', 'Clark', '$2a$10$R1LuOjvS.seEjBv1/sSGDuBPiozJJHxuLVzqTRB3DJ7lGow3obMhS', NULL),
(7, '2024-12-16 21:26:44.000000', '2024-12-16 21:26:44.000000', b'0', 'william.miller@example.com', 'William', 'Miller', '$2a$10$bfOK2fPVACAMjKMGK0ja8eyVqcWQ4tZnmlXJO/OYkK/05n2vdvze6', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `users_roles`
--

CREATE TABLE `users_roles` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users_roles`
--

INSERT INTO `users_roles` (`user_id`, `role_id`) VALUES
(1, 1),
(2, 2),
(3, 2),
(4, 2),
(5, 2),
(6, 2),
(7, 2);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `chat_messages`
--
ALTER TABLE `chat_messages`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKh9kqqevckkn1qo974dctny1fn` (`to_user_id`),
  ADD KEY `FKjso027m9xea0jbskuulqn0gna` (`from_user_id`);

--
-- Indexes for table `contact_messages`
--
ALTER TABLE `contact_messages`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `customers`
--
ALTER TABLE `customers`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `images`
--
ALTER TABLE `images`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKghwsjbjo7mg3iufxruvq6iu3q` (`product_id`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKpxtb8awmi0dk6smoh2vp1litg` (`customer_id`);

--
-- Indexes for table `order_items`
--
ALTER TABLE `order_items`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKbioxgbv59vetrxe0ejfubep1w` (`order_id`),
  ADD KEY `FKocimc7dtr037rh4ls4l95nlfi` (`product_id`);

--
-- Indexes for table `privileges`
--
ALTER TABLE `privileges`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKm2tnonbcaquofx1ccy060ejyc` (`name`);

--
-- Indexes for table `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK6i174ixi9087gcvvut45em7fd` (`supplier_id`);

--
-- Indexes for table `purchases`
--
ALTER TABLE `purchases`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK9ho3w23v5du4x0hrp6rqs1wmh` (`supplier_id`);

--
-- Indexes for table `purchase_items`
--
ALTER TABLE `purchase_items`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKbwtjp8gfcre77l1mxverb6up0` (`product_id`),
  ADD KEY `FKhcski0jcuja0o3vhb7o15yqvi` (`purchase_id`);

--
-- Indexes for table `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKofx66keruapi6vyqpv6f2or37` (`name`);

--
-- Indexes for table `roles_privileges`
--
ALTER TABLE `roles_privileges`
  ADD PRIMARY KEY (`role_id`,`privilege_id`),
  ADD KEY `FK5duhoc7rwt8h06avv41o41cfy` (`privilege_id`);

--
-- Indexes for table `suppliers`
--
ALTER TABLE `suppliers`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK6dotkott2kjsp8vw4d0m25fb7` (`email`);

--
-- Indexes for table `users_roles`
--
ALTER TABLE `users_roles`
  ADD PRIMARY KEY (`user_id`,`role_id`),
  ADD KEY `FKj6m8fwv7oqv74fcehir1a9ffy` (`role_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `chat_messages`
--
ALTER TABLE `chat_messages`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `contact_messages`
--
ALTER TABLE `contact_messages`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `customers`
--
ALTER TABLE `customers`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `images`
--
ALTER TABLE `images`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=37;

--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `order_items`
--
ALTER TABLE `order_items`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `privileges`
--
ALTER TABLE `privileges`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `products`
--
ALTER TABLE `products`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `purchases`
--
ALTER TABLE `purchases`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `purchase_items`
--
ALTER TABLE `purchase_items`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `roles`
--
ALTER TABLE `roles`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `suppliers`
--
ALTER TABLE `suppliers`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `chat_messages`
--
ALTER TABLE `chat_messages`
  ADD CONSTRAINT `FKh9kqqevckkn1qo974dctny1fn` FOREIGN KEY (`to_user_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `FKjso027m9xea0jbskuulqn0gna` FOREIGN KEY (`from_user_id`) REFERENCES `users` (`id`);

--
-- Constraints for table `images`
--
ALTER TABLE `images`
  ADD CONSTRAINT `FKghwsjbjo7mg3iufxruvq6iu3q` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`);

--
-- Constraints for table `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `FKpxtb8awmi0dk6smoh2vp1litg` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`id`);

--
-- Constraints for table `order_items`
--
ALTER TABLE `order_items`
  ADD CONSTRAINT `FKbioxgbv59vetrxe0ejfubep1w` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`),
  ADD CONSTRAINT `FKocimc7dtr037rh4ls4l95nlfi` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`);

--
-- Constraints for table `products`
--
ALTER TABLE `products`
  ADD CONSTRAINT `FK6i174ixi9087gcvvut45em7fd` FOREIGN KEY (`supplier_id`) REFERENCES `suppliers` (`id`);

--
-- Constraints for table `purchases`
--
ALTER TABLE `purchases`
  ADD CONSTRAINT `FK9ho3w23v5du4x0hrp6rqs1wmh` FOREIGN KEY (`supplier_id`) REFERENCES `suppliers` (`id`);

--
-- Constraints for table `purchase_items`
--
ALTER TABLE `purchase_items`
  ADD CONSTRAINT `FKbwtjp8gfcre77l1mxverb6up0` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`),
  ADD CONSTRAINT `FKhcski0jcuja0o3vhb7o15yqvi` FOREIGN KEY (`purchase_id`) REFERENCES `purchases` (`id`);

--
-- Constraints for table `roles_privileges`
--
ALTER TABLE `roles_privileges`
  ADD CONSTRAINT `FK5duhoc7rwt8h06avv41o41cfy` FOREIGN KEY (`privilege_id`) REFERENCES `privileges` (`id`),
  ADD CONSTRAINT `FK629oqwrudgp5u7tewl07ayugj` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`);

--
-- Constraints for table `users_roles`
--
ALTER TABLE `users_roles`
  ADD CONSTRAINT `FK2o0jvgh89lemvvo17cbqvdxaa` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `FKj6m8fwv7oqv74fcehir1a9ffy` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
