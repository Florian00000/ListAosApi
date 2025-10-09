CREATE DATABASE  IF NOT EXISTS `battlescroll_service` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `battlescroll_service`;
-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: battlescroll_service
-- ------------------------------------------------------
-- Server version	8.0.35

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `aptitude_context`
--

DROP TABLE IF EXISTS `aptitude_context`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `aptitude_context` (
  `id_aptitude_context` bigint NOT NULL AUTO_INCREMENT,
  `is_equal_games` bit(1) NOT NULL,
  `is_optimisation` bit(1) NOT NULL,
  `is_universal` bit(1) NOT NULL,
  `points` int NOT NULL,
  `charter_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id_aptitude_context`),
  KEY `FK3805c6c9yiksy972khdu1l79n` (`charter_id`),
  CONSTRAINT `FK3805c6c9yiksy972khdu1l79n` FOREIGN KEY (`charter_id`) REFERENCES `charter` (`id_charter`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `aptitude_context`
--

LOCK TABLES `aptitude_context` WRITE;
/*!40000 ALTER TABLE `aptitude_context` DISABLE KEYS */;
INSERT INTO `aptitude_context` VALUES (1,_binary '',_binary '\0',_binary '\0',0,1),(5,_binary '',_binary '\0',_binary '\0',0,9),(10,_binary '',_binary '\0',_binary '\0',0,9),(11,_binary '',_binary '\0',_binary '\0',0,9),(14,_binary '',_binary '\0',_binary '\0',0,10),(15,_binary '',_binary '\0',_binary '\0',0,10),(16,_binary '',_binary '\0',_binary '\0',0,10),(17,_binary '',_binary '\0',_binary '\0',0,10),(18,_binary '',_binary '\0',_binary '\0',0,10),(19,_binary '',_binary '\0',_binary '\0',0,11),(20,_binary '',_binary '\0',_binary '\0',0,11),(21,_binary '',_binary '\0',_binary '\0',0,11),(22,_binary '',_binary '\0',_binary '\0',0,14),(23,_binary '',_binary '\0',_binary '\0',0,14),(24,_binary '',_binary '\0',_binary '\0',0,14),(25,_binary '',_binary '\0',_binary '\0',0,15),(26,_binary '',_binary '\0',_binary '\0',0,15),(27,_binary '',_binary '\0',_binary '\0',0,16),(28,_binary '',_binary '\0',_binary '\0',0,16),(29,_binary '',_binary '\0',_binary '\0',0,16),(30,_binary '',_binary '\0',_binary '\0',0,17),(31,_binary '',_binary '\0',_binary '\0',0,17),(32,_binary '',_binary '\0',_binary '\0',0,17),(33,_binary '',_binary '\0',_binary '\0',0,18),(34,_binary '',_binary '\0',_binary '\0',0,18),(35,_binary '',_binary '\0',_binary '\0',0,18),(36,_binary '',_binary '\0',_binary '\0',0,19),(37,_binary '',_binary '\0',_binary '\0',0,20),(38,_binary '',_binary '\0',_binary '\0',0,20),(39,_binary '',_binary '\0',_binary '\0',0,21),(40,_binary '',_binary '\0',_binary '\0',0,21),(41,_binary '',_binary '\0',_binary '\0',0,22),(42,_binary '',_binary '\0',_binary '\0',0,22),(43,_binary '',_binary '\0',_binary '\0',0,23),(44,_binary '',_binary '\0',_binary '\0',0,23),(45,_binary '',_binary '\0',_binary '\0',0,24),(46,_binary '',_binary '\0',_binary '\0',0,25),(47,_binary '',_binary '\0',_binary '\0',0,26),(48,_binary '',_binary '\0',_binary '\0',0,26),(49,_binary '',_binary '\0',_binary '\0',0,27),(50,_binary '',_binary '\0',_binary '\0',0,27),(51,_binary '',_binary '\0',_binary '\0',0,30),(52,_binary '',_binary '\0',_binary '\0',0,31),(53,_binary '',_binary '\0',_binary '\0',0,31),(54,_binary '',_binary '\0',_binary '\0',0,31),(55,_binary '',_binary '\0',_binary '\0',0,31);
/*!40000 ALTER TABLE `aptitude_context` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `aptitude_keyword`
--

DROP TABLE IF EXISTS `aptitude_keyword`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `aptitude_keyword` (
  `id_aptitude` bigint NOT NULL,
  `id_keyword` bigint NOT NULL,
  KEY `FKeja0hr74jph2a6maxm5d8fekx` (`id_keyword`),
  KEY `FK34y4dvia0e76emxy9nvgi9rlw` (`id_aptitude`),
  CONSTRAINT `FK34y4dvia0e76emxy9nvgi9rlw` FOREIGN KEY (`id_aptitude`) REFERENCES `battle_aptitude` (`id_battle_aptitude`),
  CONSTRAINT `FKeja0hr74jph2a6maxm5d8fekx` FOREIGN KEY (`id_keyword`) REFERENCES `keyword` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `aptitude_keyword`
--

LOCK TABLES `aptitude_keyword` WRITE;
/*!40000 ALTER TABLE `aptitude_keyword` DISABLE KEYS */;
INSERT INTO `aptitude_keyword` VALUES (6,3),(7,15),(10,3),(12,15),(14,3),(17,15),(18,3),(22,15),(25,15),(28,15),(39,1),(41,1),(47,34);
/*!40000 ALTER TABLE `aptitude_keyword` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `battle_aptitude`
--

DROP TABLE IF EXISTS `battle_aptitude`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `battle_aptitude` (
  `dtype` varchar(31) NOT NULL,
  `id_battle_aptitude` bigint NOT NULL AUTO_INCREMENT,
  `announcement` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `aptitude_type` enum('ARTEFACT','BATTLEFORMATION','BATTLELINE','HEROICTRAIT','PRAYER','SKILL','SPELL','PASSIVE') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `description` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `effect` varchar(1000) NOT NULL,
  `name` varchar(255) NOT NULL,
  `phase` varchar(255) NOT NULL,
  `is_magical` bit(1) DEFAULT NULL,
  `launch_value` int DEFAULT NULL,
  `aptitude_context_id` bigint DEFAULT NULL,
  `domain_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id_battle_aptitude`),
  UNIQUE KEY `UKaqmwcyrbuse5tkpo9dek2sany` (`aptitude_context_id`),
  KEY `FKmj4vw34cvqfg2in56pjxi0b39` (`domain_id`),
  CONSTRAINT `FK2m94tsj0lc4x3gtlrsvceqq7g` FOREIGN KEY (`aptitude_context_id`) REFERENCES `aptitude_context` (`id_aptitude_context`),
  CONSTRAINT `FKmj4vw34cvqfg2in56pjxi0b39` FOREIGN KEY (`domain_id`) REFERENCES `domain` (`id_domain`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `battle_aptitude`
--

LOCK TABLES `battle_aptitude` WRITE;
/*!40000 ALTER TABLE `battle_aptitude` DISABLE KEYS */;
INSERT INTO `battle_aptitude` VALUES ('BattleAptitude',2,'','PASSIVE','Le pouvoir de la Reine Éternelle pulse à travers la Dame des Sarments et irradie de sa couronne.','Cette unité compte comme un Bois Sauvage Éveillé pour les besoins des aptitudes “Repousse Perpétuelle”, “Arpenter les Sentiers Cachés” et “Frapper et Disparaître”.','Couronne Viride','Passif',NULL,NULL,5,NULL),('MagicPrayer',6,'Faites un jet de lancement de 2D6.','SPELL','La Dame des Sarments canalise la puissance de sa déesse mère, protégeant les Sylvaneths grâce à l’énergie de la nature.','Jusqu’au début de votre prochain tour, les unités Sylvaneth amies ont une Protection (5+) tant qu’elles sont entièrement à 12\' de cette unité.','Aspect de la Reine Eternelle','Votre Phase des Héros',_binary '',7,10,NULL),('BattleAptitude',7,'Choisissez une unité ennemie en mêlée avec cette unité comme cible.','SKILL','Les excroissances cinglantes qui jaillissent du dos de la Dame peuvent enchevêtrer l’ennemi.','Jetez un dé. Sur 2+, choisissez 1 des effets suivant à appliquer jusqu’à la fin du tour : \n Barrière : Soustrayez 1 aux jets de touche pour les attaques de la cible. \n Entrave : Ajoutez 1 aux jets de touche pour les attaques faites par les unités amies qui ciblent l’unité ennemie.','Sarments Sinueux','Une Fois par Tour (Armée), N’importe Quelle Phase de Mêlée',NULL,NULL,11,NULL),('BattleAptitude',8,NULL,'PASSIVE',NULL,'Tant que cette unité a 10 dégâts ou plus, la caractéristique d’Attaques de sa Grande Ramure de Scaradroth est 4.','Stigmates de guerre','Passif',NULL,NULL,14,NULL),('BattleAptitude',9,NULL,'SKILL','l’énergie réparatrice de Ghyran guérit les blessures d’Alarielle.','Choisissez 1 des effets suivants :\n• Ajoutez 1 aux jets de lancement pour cette unité jusqu’à la fin du tour.\n• Soignez (2D3) cette unité.','Vie éclose','N’importe Quelle Phase des Héros',NULL,NULL,15,NULL),('MagicPrayer',10,'Choisissez une unité ennemie visible à 12\" de cette unité comme cible, puis faites un jet de lancement de 2D6.','SPELL','D’un regard, Alarielle transforme l’ennemi en arbre.','Infligez 2D3 dégâts mortels à la cible. Si la cible est détruite par ce sort, vous pouvez aussitôt résoudre les effets du sort “Chant des Arbres”comme si cette unité avait réussi à le lancer.','Métamorphose','Votre Phase des Héros',_binary '',7,16,NULL),('BattleAptitude',11,'Choisissez comme cible une unité Sylvaneth amie qui a été détruite. Cette unité peut utiliser cette aptitude si elle a été détruite, mais elle doit alors en être la cible.','SKILL','La Reine Éternelle est accompagnée du pouvoir de son grand rituel.','Jetez un dé. Sur 4+, placez une unité de remplacement avec la moitié du nombre de figurines de l’unité cible (arrondi au supérieur) entièrement à 9\" d’un Bois Sauvage Éveillé ami et à plus de 9\" des unités ennemies. Si la cible était un Monstre, allouez 6 dégâts à l’unité de remplacement (on ne peut pas faire de jets de protection contre ces dégâts).','Rituel de la Vie','Une Fois par Tour (Armée), Votre Phase de Mouvement',NULL,NULL,17,NULL),('BattleAptitude',12,'Si cette unité a chargé à ce tour, choisissez une unité d’Infanterie ennemie à 1\" d’elle comme cible.','SKILL','Un Scaradroth en charge écrase tout sur son passage.','Jetez un dé. Sur 3+, infligez autant de dégâts mortels à la cible que le résultat du jet.','Bélier de siège vivant','Une Fois par Tour (Armée), N’importe Quelle Phase de Charge',NULL,NULL,18,NULL),('BattleAptitude',13,NULL,'PASSIVE','Pour les Sylvaneths, le son qui émane de la flûte d’un Baladin Chanteguerre est doux et exaltant. Pour leurs ennemis, c’est un assaut bruyant au potentiel dévastateur.','Les unités Sylvaneths amies ont une Protection (6+) tant qu’elles sont entièrement à 12\" de cette unité. Soustrayez 1 aux jets de protection pour les unités ennemies tant qu’elles sont à 12\" de cette unité.','Chant d\'Alarielle','Passif',NULL,NULL,19,NULL),('MagicPrayer',14,'Choisissez un Bois Sauvage Éveillé ami entièrement à 18\" de cette unité, choisissez comme cibles jusqu’à 3 unités ennemies visibles à 9\" de cet élément de terrain, puis faites un jet de lancement de 2D6.','SPELL','Le Baladin Chanteguerre invoque un grand essaim de follets malicieux qui tourbillonnent pour aller attaquer ceux qui lui déplaisent.','Jetez un D3 pour chaque cible. Sur 2+, infligez autant de dégâts mortels à la cible que le résultat du jet.','Lâcher de Follets','Votre Phase des Héros',_binary '',7,20,NULL),('BattleAptitude',15,NULL,'PASSIVE','Les Baladins Chanteguerre sont liés à la nature et puisent leur force dans les forêts.','Ajoutez 1 aux jets de dissipation et aux jets de bannissement pour cette unité tant qu’elle est entièrement à 6\" d’un ou plusieurs Bois Sauvages Éveillés.','Revenants des Bois Sauvages','Passif',NULL,NULL,21,NULL),('BattleAptitude',16,NULL,'SKILL','Les frémifuries sont revigorées par la rage de Drycha, alors que les tressanguilles se nourrissent de son chagrin.','Choisissez 1 des effets suivants à appliquer à cette unité jusqu’à la fin du tour : \nEnragée : Ajoutez 10 à la caractéristique d’Attaques des Frémifuries de cette unité. \nMélancolique: Ajoutez 10 à la caractéristique d’Attaques de l’Essaim de Tressanguilles de cette unité.','Humeur changeante','Une Fois par Tour, N’importe Quelle Phase des Héros',NULL,NULL,22,NULL),('BattleAptitude',17,'Choisissez une unité ennemie en mêlée avec cette unité comme cible.','SKILL','Drycha rejette la tête en arrière et pousse un cri de tourment assourdissant.','Jetez un dé. Sur 2+, divisez par deux le score de contrôle de la cible (arrondi au supérieur) jusqu’à la fin du tour.','Terreur Primordiale','Une Fois par Tour (Armée), N’importe Quelle Phase de Mêlée',NULL,NULL,23,NULL),('MagicPrayer',18,'Choisissez comme cible une unité d’Infanterie ennemie à 12\" du lanceur, puis faites un jet de lancement de 2D6.','SPELL','Drycha entretient des liens particuliers avec les Parias, qui semblent émuler sa fureur.','les effets suivants s’appliquent jusqu’à la fin du tour : \nAjoutez 1 aux jets de touche pour les attaques de mêlée faites par les unités Sylvaneths amies qui ciblent cette unité ennemie. \nEn outre, ajoutez 1 aux jets de blessure pour les attaques de mêlée faites par les unités de Fiel-revenants ou Sylvetorves amies qui ciblent cette unité ennemie.','Chant de Dépit','Votre Phase des Héros',_binary '',6,24,NULL),('BattleAptitude',19,NULL,'SKILL','Ce bouclier peut servir à parer les coups ou à stabiliser la hampe d’une arme d’hast.','Choisissez 1 des effets suivants à appliquer à cette unité jusqu’à la fin du tour : \nPosture Défensive : Cette unité a une Protection (4+) \nPosture Agressive : Ajoutez 1 à la caractéristique d’Attaques des armes de mêlée de cette unité, et ajoutez 1 aux jets de blessure pour les attaques de mêlée de cette unité.','Pelte','N’importe Quelle Phase de Mêlée',NULL,NULL,25,NULL),('BattleAptitude',20,NULL,'PASSIVE','Un Archirevenant a une autorité totale sur les Chasseurs de Kurnoth.','Ajoutez 1 aux jets de blessure pour les attaques de mêlée faites par les unités de Kurnothi amies tant qu’elles sont entièrement à 12\" de cette unité.','Champion de Kurnoth','Passif',NULL,NULL,26,NULL),('BattleAptitude',21,NULL,'PASSIVE',NULL,'Tant que cette unité a 10 dégâts ou plus, la caractéristique d’Attaques de son Épée Gardienne est 3.','Stigmates de guerre','Passif',NULL,NULL,27,NULL),('BattleAptitude',22,'Choisissez un Monstre ennemi en mêlée avec cette unité comme cible.','SKILL','Ces anciens protecteurs des forêts repoussent les bêtes les plus féroces avec leur épée gardienne.','Jetez un dé. Sur 3+, soustrayez 1 à la caractéristique d’Attaques des armes de mêlée de la cible jusqu’à la fin du tour.','Duel Titanesque','Une Fois par Tour (Armée), N’importe Quelle Phase de Mêlée',NULL,NULL,28,NULL),('BattleAptitude',23,NULL,'PASSIVE','Lorsque les bosquets sacrés des Sylvaneths sont menacés, les Esprits de Durthu font preuve de la rage de leur ancêtre.','Ajoutez 1 aux jets de touche pour les attaques de mêlée de cette unité tant que la cible est à 3\" d’un Bois Sauvage Éveillé.','Gardien Colérique','Passif',NULL,NULL,29,NULL),('BattleAptitude',24,NULL,'PASSIVE',NULL,'Tant que cette unité a 10 dégâts ou plus, la caractéristique d’Attaques de ses Attaques de Balayage est 3.','Stigmates de guerre','Passif',NULL,NULL,30,NULL),('BattleAptitude',25,'Choisissez une unité ennemie en mêlée avec cette unité comme cible.','SKILL','Quand un Sylviarque s’abandonne au combat, les vrilles frénétiques qui jaillissent de sa chairécorce enserrent l’ennemi ou le maintiennent à distance sous des coups de fouets cinglants.','Jetez un dé. Sur 3+, soustrayez 1 aux jets de blessure pour les attaques de la cible jusqu’à la fin du tour.','Cingler et enchevêtrer','Une Fois par Tour (Armée), N’importe Quelle Phase de Mêlée',NULL,NULL,31,NULL),('BattleAptitude',26,'Choisissez comme cible une unité ennemie à qui un ou plusieurs dégâts ont été alloués à ce tour par des attaques faites avec les Racines Étrangleuses de cette unité.','SKILL','Les racines étrangleuses serrent et entravent l’ennemi, le laissant à la merci des Sylvaneths vengeurs.','Jetez un dé. Sur 3+, la cible ne peut pas utiliser des aptitudes de Course ou de Repli jusqu’au début de votre prochain tour.','Etreinte enchevêtrée','N’importe Quelle Phase de Tir',NULL,NULL,32,NULL),('BattleAptitude',27,NULL,'PASSIVE',NULL,'Tant que cette unité a 10 dégâts ou plus, la caractéristique d’Attaques de ses Attaques de Balayage est 3.','Stigmates de Guerre','Passif',NULL,NULL,33,NULL),('BattleAptitude',28,'Choisissez une unité ennemie en mêlée avec cette unité comme cible.','SKILL','Le sol tremble alors que cet esprit des forêts piétine le sol et fait tomber l’ennemi à la renverse.','Jetez un dé. Sur 4+, la cible a Frappe en Dernier jusqu’à la fin du tour.','Trembleterre','Une Fois par Tour (Armée), N’importe Quelle Phase de Mêlée',NULL,NULL,34,NULL),('BattleAptitude',29,'Choisissez un Bois Sauvage Éveillé ami visible entièrement à 18\" de cette unité comme cible.','SKILL','Sur ordre du Sylviarque Vénérable, les arbres des bois sauvages s’animent et attaquent avec leurs branches.','Jetez un D3. Sur 2+, infligez autant de dégâts mortels que le résultat du jet à chaque unité ennemie à portée de mêlée de la cible.','Eveiller le Bois','Une Fois par Tour (Armée), Votre Phase des Héros',NULL,NULL,35,NULL),('BattleAptitude',30,'Si cette unité est entièrement à 6\" d’un ou plusieurs Bois Sauvages Éveillés amis, choisissez comme cible une unité Sylvaneth amie entièrement à 12\" de cette unité.','SKILL','L’instinct protecteur des Branchanteresses envers les forêts est tel qu’elles entrent dans une colère noire si un bois sauvage est menacé.','Jetez un dé. Sur 3+, la cible a Frappe en Premmier jusqu’à la fin du tour.','Furie des Forêts','Une Fois par Tour (Armée), Phase de Mêlée Ennemie',NULL,NULL,36,NULL),('BattleAptitude',31,NULL,'PASSIVE','Ces guerriers combattent en formations plus relâchées afin de tirer avantage du terrain dans leurs domaines arborés.','Cette unité a une portée de cohésion de 2\".','Combattants des forêts','Passif',NULL,NULL,37,NULL),('BattleAptitude',32,NULL,'SKILL','Ces symbiotes insectoïdes peuvent se détacher pour gêner les attaques de l’adversaire le temps que leur maître se mette en sécurité.','Si cette unité a utilisé une aptitude de Tir à cette phase, cette unité peut se déplacer d’un D6\". Elle ne peut pas se déplacer en mêlée en effectuant ce mouvement.','Zéphrites','Une Fois par Tour, N’importe Quelle Phase de Tir',NULL,NULL,38,NULL),('BattleAptitude',33,NULL,'PASSIVE','Les Chasseurs de Kurnoth entendent la volonté d’Alarielle par le truchement des vibrations mystiques qui circulent dans les noeuds des racines des royaumes.','Tant que cette unité conteste un objectif, les unités Sylvaneths amies qui contestent cet objectif ont une Protection (6+).','Envoyés de la rein Eternelle','Passif',NULL,NULL,39,NULL),('BattleAptitude',34,'Si cette unité a chargé à ce tour, choisissez une unité ennemie qui est en mêlée avec cette unité comme cible.','SKILL','Les Chasseurs de Kurnoth se servent de leur taille et de leur poids pour piétiner l’adversaire.','Jetez un dé pour chaque figurine de cette unité. Pour chaque 3+, infligez 1 dégât mortel à la cible.','Piétinement','N’importe Quelle Phase de Mêlée',NULL,NULL,40,NULL),('BattleAptitude',35,NULL,'PASSIVE','Les Chasseurs de Kurnoth entendent la volonté d’Alarielle par le truchement des vibrations mystiques qui circulent dans les noeuds des racines des royaumes.','Tant que cette unité conteste un objectif, les unités Sylvaneths amies qui contestent cet objectif ont une Potection (6+).','Envoyés de la Reine Eternelle','Passif',NULL,NULL,41,NULL),('BattleAptitude',36,'Choisissez comme cible une unité ennemie qui a chargé à ce tour et qui est en mêlée avec cette unité.','SKILL','Les Chasseurs de Kurnoth peuvent faire pousser des buissons épineux qui clouent l’ennemi sur place et l’écorchent vif s’il tente de se mouvoir.','Jetez un dé pour chaque figurine de cette unité. Pour chaque 3+, infligez 1 dégât mortel à la cible.','Hallier de Ronces','N’importe Quelle Phase de Mêlée',NULL,NULL,42,NULL),('BattleAptitude',37,NULL,'PASSIVE','Les Chasseurs de Kurnoth entendent la volonté d’Alarielle par le truchement des vibrations mystiques qui circulent dans les noeuds des racines des royaumes.','Tant que cette unité conteste un objectif, les unités Sylvaneths amies qui contestent cet objectif ont une Potection (6+).','Envoyés de la Reine Eternelle','Passif',NULL,NULL,43,NULL),('BattleAptitude',38,NULL,'SKILL','Les Chasseurs de Kurnoth prennent le temps de viser une cible prioritaire avant de lâcher leurs flèches avec une précision fatale.','Pour le restant du tour, cette unité peut ignorer les effets de l’aptitude “Héros Gardé” (Règles de Base, 25.0) au moment de choisir les cibles de ses attaques de tir.','Visée Sûre','Votre Phase de Tir',NULL,NULL,44,NULL),('BattleAptitude',39,NULL,'SKILL','Les Sylve-revenants se meuvent avec grâce, en suivant leur joueur de syringe le long des sentiers spirituels pour frapper l’ennemi à l’endroit le plus crucial.','Si cette unité n’est pas en mêlée, retirez-la du champ de bataille, et placez-la à nouveau sur le champ de bataille à plus de 9\" des unités ennemies.','Danse de la Syringe des sentes','Une Fois par Tour (Armée), Votre Phase de Mouvement',NULL,NULL,45,NULL),('BattleAptitude',40,'Choisissez une unité d’Infanterie ennemie en mêlée avec cette unité comme cible.','SKILL','Les Fiel-revenants débitent des malédictions dans un flux discordant de langages insondables.','Jetez un dé. Sur 3+, la cible ne peut pas utiliser d’ordres jusqu’à la fin du tour.','Malice Débridée','N’importe Quelle Phase de Mêlée',NULL,NULL,46,NULL),('BattleAptitude',41,NULL,'SKILL','Voyageant par les racines des royaumes et les lignes telluriques en quête de proies, les Guivres des Clairières peuvent s’enfouir sans le sol et resurgir ailleurs.','Si cette Manifestation n’est pas en mêlée, retirez-la du champ de bataille et placez-la à nouveau sur le champ de bataille à plus de 9\" des unités ennemies qui ne sont pas des Sorciers et à plus de 3\" des Sorciers ennemis.','Enfouissage','Votre Phase de Mouvement',NULL,NULL,47,NULL),('BattleAptitude',42,'Choisissez un Sorcier ennemi en mêlée avec cette Manifestation comme cible.','SKILL','La Guivre des Clairières jaillit d’en dessous pour protéger les racines des royaumes de la magie hostile.','Soustrayez 1 aux jets de lancement, jets de dissipation et jets de bannissement pour la cible jusqu’à la fin de votre prochain tour.','Nourri d\'énergie mystique','Fin de N\'importe Quel Tour',NULL,NULL,48,NULL),('BattleAptitude',43,'Choisissez une unité ennemie à 3\" de cette Manifestation comme cible.','SKILL','Là où se rend cet esprit maléfique, ses racines rouvrent les tombes des batailles passées, et ramènent à la surface les dépouilles des fosses communes.','Pour le restant du tour, soustrayez autant au score de contrôle de la cible que le nombre d’unités amies qui ont été détruites à cette bataille (sans inclure les Manifastations ni les Terrains de Faction).','Terreur Vengeresse','Fin de N\'importe Quel Tour',NULL,NULL,49,NULL),('BattleAptitude',44,NULL,'PASSIVE',NULL,'Cette Manifestation compte comme un Bois Sauvage Éveillé pour les aptitudes Repousse Perpétuelle ; Arpenter les Sentiers Cachés ; Frapper et Disparaître.','Saule Rampeur','Passif',NULL,NULL,50,NULL),('BattleAptitude',45,'Choisissez une unité Sylvaneth amie visible à 3\" de cette Manifestation comme cible.','SKILL','Les fiellessaims bourdonnent de magie ghyranite, qui émane de leur ruche et coule de leurs mandibules. Quand ces créatures sont convoquées par leurs maîtres Sylvaneths, elles sortent de leur nid en nuages occultants pour prodiguer leur morsure bienfaitrice.','Jetez un dé. Sur 2+, choisissez 1 des effets suivants à appliquer à la cible jusqu’à la fin du tour : \nVenin Revitalisant : Ajoutez 1 aux jets de course et aux jets de charge pour la cible. \nNuée Protectrice : La cible a une Protection (6+).','La Ruche se nourrit','Votre Phase des Héros',NULL,NULL,51,NULL),('BattleAptitude',46,'Choisissez comme cibles toutes les unités ennemies à portée de mêlée de cet élément de terrain.','SKILL','Les esprits qui hantent ces bois sont aisément courroucés par les intrus.','Jetez un dé pour chaque cible. Sur 4+, infligez D3 dégâts mortels à la cible.','Esprits Sylvains Vengeurs','Une Fois par Tour (Armée), N’importe Quelle Phase de Mêlée',NULL,NULL,52,NULL),('BattleAptitude',47,NULL,'SKILL','Les bois sauvages dans lesquels vivent les Sylvaneths possèdent un esprit primitif amer. Ils s’agitent dans le seul but de repousser les intrus hors du territoire boisé de leurs cousins. Avec leurs branches et leurs racines, ils frapperont, lacéreront et écraseront tous ceux qui s’approchent, en abreuvant le sol du sang de leurs victimes.','Placez cet élément de terrain entièrement en territoire ami, à plus de 3\" de tous les objectifs et des autres éléments de terrain. Cet élément de terrain a à présent été déployé.','Déploiement de bois sauvage','Phase de Déploiement',NULL,NULL,53,NULL),('BattleAptitude',48,NULL,'PASSIVE','Seuls les Sylvaneths peuvent voir à plus de quelques pas dans ce bosquet inquiétant.','Les unités ennemies peuvent être ciblées par des attaques de tir faites par des unités Sylvaneths amies même si elles sont derrière ou entièrement sur cet élément de terrain (c’est une exception au Terrain, 1.2).','Végétation envahissante','Passif',NULL,NULL,54,NULL),('BattleAptitude',49,NULL,'PASSIVE','Les Bois Sauvages Éveillés varient grandement en taille, mais ils poussent très rapidement en répondant à l’appel des Sylvaneths.','Chaque fois que vous placez un Bois Sauvage Éveillé, vous pouvez placer 1-3 pièces de décor (Terrain, 1.6). Si vous placez plus de 1 pièce de décor, les pointes aux extrémités de chaque socle doivent se toucher en formant un cercle. Pour chaque pièce de décor de cet élément de décor qui est placée après la première: \n• Ajoutez 2 à la caractéristique de Santé de cet élément de terrain. \n• Augmentez de 3\" la portée des aptitudes amies qui requièrent des figurines amies qu’elles soient entièrement à 6\" de cet élément de terrain.','Croissance Rapide','Passif',NULL,NULL,55,NULL);
/*!40000 ALTER TABLE `battle_aptitude` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `charter`
--

DROP TABLE IF EXISTS `charter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `charter` (
  `type_charter` varchar(31) NOT NULL,
  `id_charter` bigint NOT NULL AUTO_INCREMENT,
  `image_path` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `version_id` int DEFAULT NULL,
  PRIMARY KEY (`id_charter`),
  UNIQUE KEY `uc_charter_name` (`name`),
  KEY `FKi4oyr40p86ys6m2le87alm73e` (`version_id`),
  CONSTRAINT `FKi4oyr40p86ys6m2le87alm73e` FOREIGN KEY (`version_id`) REFERENCES `version` (`id_version`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `charter`
--

LOCK TABLES `charter` WRITE;
/*!40000 ALTER TABLE `charter` DISABLE KEYS */;
INSERT INTO `charter` VALUES ('FACTION',1,'/images/0e875fdf-6622-4535-ba08-2154562b84e9-sylvanethTest.png','Sylvaneth',1),('UNITY',9,'/images/20f78b2e-2c55-4549-b69f-318071b38db4-dameSerments.png','La Dame des Sarments',1),('UNITY',10,'/images/c5608781-9cd5-4c6c-8e71-cbec02cb6f91-Alarielle.jpg','Alarielle la Reine Eternelle',1),('UNITY',11,'/images/d534072e-6b43-45c4-9fba-046c24d1e784-baldin_chanteguerre_figurine.jpg','Baladin Chanteguerre',1),('FACTION',12,'/images/114c546a-b5fc-4c80-805c-2f305d69e7a8-battletomSkavens.png','Skavens',1),('UNITY',13,'/images/2a030d56-11c1-4157-b9a6-9889846c7aa7-467px-Warhammer_Thanquol_Book_1_cover_001.jpg','Thanquol',1),('UNITY',14,'/images/00fed4dc-eb5d-406d-9758-df1e9a1818da-Drycha.jpg','Drycha Hamadreth',1),('UNITY',15,'/images/6249326e-72d7-4666-b815-99b4324ff18e-Archi-Revenant.jpg','Archi-Revenant',1),('UNITY',16,'/images/9d26d490-1fb1-4d20-b010-306e4edd55da-Durthu.jpg','Esprit de Durthu',1),('UNITY',17,'/images/25c989f7-93e3-4b37-b1e2-eb269aaefd75-99120204013_SylvanethTreelord01.jpg','Sylviarque',1),('UNITY',18,'/images/879a1a5f-a401-428d-bbe2-bc8c4a5e692f-99120204013_SylvanethTreelordAncient01.jpg','Sylviarque Vénérable',1),('UNITY',19,'/images/54bbb396-3166-4554-9b7f-789494aa1dad-branchanteresse.jpg','Branchanteresse',1),('UNITY',20,'/images/3548c825-b1e0-4b59-8200-6a11335c1ced-archers_diaphanes.jpg','Archers Diaphanes',1),('UNITY',21,'/images/46302074-8c71-470e-9555-b23b36780025-872px-Kurnoth_Hunter_M01-1246767158.jpg','Chasseurs de Kurnoth (épée)',1),('UNITY',22,'/images/d8dfe4ed-d9ca-463d-bae5-6cac590bfdba-sylvaneth_kurnoth_hunters_-_warhammer_age_of_sigmar-1836704754.jpg','Chasseurs de Kurnoth (faux)',1),('UNITY',23,'/images/9f1960f7-13af-46c2-a320-0a1b3ba18ece-sylvaneth_kurnoth_hunters_-_warhammer_age_of_sigmar-778982099.jpg','Chasseurs de Kurnoth (arcs)',1),('UNITY',24,'/images/e7b0a04f-1d2e-4c9f-8f4c-9dffe0935825-sylvaneth_tree-revenants_spite-revenants_-_warhammer_age_of_sigmar-3007931260.jpg','Sylve-Revenants',1),('UNITY',25,'/images/9932c259-070f-4f27-b1bd-b8682a297aad-sylvaneth_tree-revenants_spite-revenants_-_warhammer_age_of_sigmar-2294673094.jpg','Fiel-Revenants',1),('UNITY',26,'/images/63c4ab4e-97a7-4397-a7ad-c96ac009f3ed-guivre_clairières.jpg','Guivre des Clairières',1),('UNITY',27,'/images/1ff01933-4b8f-465b-bf43-6f8e6efddf95-cracine.jpg','Crâcine Vengeresse',1),('UNITY',30,'/images/7bd1810b-a6eb-4c8e-b91b-54aad343ff20-rucher.jpg','Ruche de Fiellessaim',1),('UNITY',31,'/images/40e0d713-c3d1-45d4-b51b-b0d03ba5fbfb-99120204023_AwakenedWyldwood01.jpg','Bois Sauvage Eveillé',1);
/*!40000 ALTER TABLE `charter` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `charter_keyword`
--

DROP TABLE IF EXISTS `charter_keyword`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `charter_keyword` (
  `id_charter` bigint NOT NULL,
  `id_keyword` bigint NOT NULL,
  KEY `FKlt88o28gaki1ohh1peku7ftsv` (`id_keyword`),
  KEY `FK2phuuy7nefta79hp2dk4y4s6j` (`id_charter`),
  CONSTRAINT `FK2phuuy7nefta79hp2dk4y4s6j` FOREIGN KEY (`id_charter`) REFERENCES `charter` (`id_charter`),
  CONSTRAINT `FKlt88o28gaki1ohh1peku7ftsv` FOREIGN KEY (`id_keyword`) REFERENCES `keyword` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `charter_keyword`
--

LOCK TABLES `charter_keyword` WRITE;
/*!40000 ALTER TABLE `charter_keyword` DISABLE KEYS */;
INSERT INTO `charter_keyword` VALUES (9,7),(9,8),(9,6),(9,9),(9,10),(10,7),(10,8),(10,6),(10,9),(10,11),(10,12),(10,13),(11,6),(11,10),(11,14),(11,12),(11,13),(14,8),(14,6),(14,9),(14,18),(15,6),(15,14),(15,12),(16,6),(16,9),(17,6),(17,9),(18,6),(18,9),(18,18),(19,6),(19,14),(19,18),(20,14),(20,22),(20,12),(21,14),(21,22),(22,14),(22,22),(23,14),(23,22),(24,14),(24,22),(24,24),(24,25),(25,14),(25,22),(26,27),(26,28),(26,13),(27,27),(27,28),(27,12),(27,13),(30,27),(30,28),(30,13),(31,29),(31,30),(31,31),(31,32),(31,33);
/*!40000 ALTER TABLE `charter_keyword` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `domain`
--

DROP TABLE IF EXISTS `domain`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `domain` (
  `id_domain` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `is_magical_domain` bit(1) NOT NULL,
  `name` varchar(255) NOT NULL,
  `domain_context_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id_domain`),
  UNIQUE KEY `UKga2sqp4lboblqv6oks9oryd9q` (`name`),
  UNIQUE KEY `UKepw4n2rhqvf175mow6pei9f87` (`domain_context_id`),
  CONSTRAINT `FKb4jwhyjnhhff0cai22hyg8kys` FOREIGN KEY (`domain_context_id`) REFERENCES `aptitude_context` (`id_aptitude_context`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `domain`
--

LOCK TABLES `domain` WRITE;
/*!40000 ALTER TABLE `domain` DISABLE KEYS */;
INSERT INTO `domain` VALUES (1,NULL,_binary '','Domaine du Grans-Bois',1);
/*!40000 ALTER TABLE `domain` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `faction`
--

DROP TABLE IF EXISTS `faction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `faction` (
  `alliance` enum('CHAOS','DEATH','DESTRUCTION','GENERAL','ORDER') NOT NULL,
  `id_charter` bigint NOT NULL,
  PRIMARY KEY (`id_charter`),
  CONSTRAINT `FKt06a5apsd0631t9vpt263adnm` FOREIGN KEY (`id_charter`) REFERENCES `charter` (`id_charter`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `faction`
--

LOCK TABLES `faction` WRITE;
/*!40000 ALTER TABLE `faction` DISABLE KEYS */;
INSERT INTO `faction` VALUES ('ORDER',1),('CHAOS',12);
/*!40000 ALTER TABLE `faction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `keyword`
--

DROP TABLE IF EXISTS `keyword`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `keyword` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKhvq9bm3mbguqoicyv02g5crjs` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `keyword`
--

LOCK TABLES `keyword` WRITE;
/*!40000 ALTER TABLE `keyword` DISABLE KEYS */;
INSERT INTO `keyword` VALUES (20,'Anti-Charge (+1 Perf.)'),(23,'Anti-Héros (+1 Perf.)'),(17,'Anti-Infanterie (+1 Perf.)'),(19,'Anti-Monstre (+1 Perf.)'),(26,'Anti-Sorcier (+1 Perf.)'),(1,'Base'),(22,'Champion'),(5,'Compagnon'),(31,'Couvert'),(21,'Crit (Blessure Auto.)'),(4,'Crit (Mortel)'),(34,'Déploiement de Tarrain'),(6,'Héros'),(2,'Illimité'),(14,'Infanterie'),(32,'Instable'),(7,'Maître de guerre'),(27,'Manifestation'),(9,'Monstre'),(24,'Musicien (1/5)'),(33,'Occultant'),(25,'Porte-étandard (1/5)'),(30,'Protection (5+)'),(13,'Protection (6+)'),(15,'Saccage'),(18,'Sorcier (1)'),(10,'Sorcier (2)'),(11,'Sorcier (3)'),(3,'Sort'),(28,'Sort Persistant'),(29,'Terrain de Faction'),(16,'Tir en Mêlée'),(8,'Unique'),(12,'Vol');
/*!40000 ALTER TABLE `keyword` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `organize`
--

DROP TABLE IF EXISTS `organize`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `organize` (
  `id_renowned` bigint NOT NULL,
  `id_unity` bigint NOT NULL,
  KEY `FK9nravn1ceka3hn7nxilrbtc08` (`id_unity`),
  KEY `FKkf8atishr124tkc3kiahjhtw0` (`id_renowned`),
  CONSTRAINT `FK9nravn1ceka3hn7nxilrbtc08` FOREIGN KEY (`id_unity`) REFERENCES `unity` (`id_charter`),
  CONSTRAINT `FKkf8atishr124tkc3kiahjhtw0` FOREIGN KEY (`id_renowned`) REFERENCES `renowned_regiment` (`id_charter`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `organize`
--

LOCK TABLES `organize` WRITE;
/*!40000 ALTER TABLE `organize` DISABLE KEYS */;
/*!40000 ALTER TABLE `organize` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `renowned_faction`
--

DROP TABLE IF EXISTS `renowned_faction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `renowned_faction` (
  `id_renowned` bigint NOT NULL,
  `id_faction` bigint NOT NULL,
  KEY `FK5h80yysnk6qgptcp7o6l08duo` (`id_faction`),
  KEY `FKgfs4x3d3l5qm1y0hrk1778087` (`id_renowned`),
  CONSTRAINT `FK5h80yysnk6qgptcp7o6l08duo` FOREIGN KEY (`id_faction`) REFERENCES `faction` (`id_charter`),
  CONSTRAINT `FKgfs4x3d3l5qm1y0hrk1778087` FOREIGN KEY (`id_renowned`) REFERENCES `renowned_regiment` (`id_charter`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `renowned_faction`
--

LOCK TABLES `renowned_faction` WRITE;
/*!40000 ALTER TABLE `renowned_faction` DISABLE KEYS */;
/*!40000 ALTER TABLE `renowned_faction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `renowned_regiment`
--

DROP TABLE IF EXISTS `renowned_regiment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `renowned_regiment` (
  `description` varchar(255) NOT NULL,
  `is_army_of_renown` bit(1) NOT NULL,
  `points` int NOT NULL,
  `id_charter` bigint NOT NULL,
  PRIMARY KEY (`id_charter`),
  CONSTRAINT `FKfoa60vvxlgl36gte8o0t8xx03` FOREIGN KEY (`id_charter`) REFERENCES `charter` (`id_charter`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `renowned_regiment`
--

LOCK TABLES `renowned_regiment` WRITE;
/*!40000 ALTER TABLE `renowned_regiment` DISABLE KEYS */;
/*!40000 ALTER TABLE `renowned_regiment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `unity`
--

DROP TABLE IF EXISTS `unity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `unity` (
  `control` int NOT NULL,
  `health` int NOT NULL,
  `movement` int NOT NULL,
  `points` int NOT NULL,
  `save` int NOT NULL,
  `id_charter` bigint NOT NULL,
  `faction_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id_charter`),
  KEY `FKagpnnu9ovjv6ogkveykc1lujh` (`faction_id`),
  CONSTRAINT `FKagpnnu9ovjv6ogkveykc1lujh` FOREIGN KEY (`faction_id`) REFERENCES `faction` (`id_charter`),
  CONSTRAINT `FKhc2944oo57rrt586b3grvkua2` FOREIGN KEY (`id_charter`) REFERENCES `charter` (`id_charter`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `unity`
--

LOCK TABLES `unity` WRITE;
/*!40000 ALTER TABLE `unity` DISABLE KEYS */;
INSERT INTO `unity` VALUES (5,8,8,250,3,9,1),(5,16,14,680,3,10,1),(2,7,6,200,5,11,1),(5,14,8,360,4,13,12),(5,10,9,240,3,14,1),(2,6,12,120,4,15,1),(5,14,5,320,3,16,1),(5,14,5,210,3,17,1),(5,14,5,230,3,18,1),(2,5,6,110,5,19,1),(1,2,12,120,5,20,1),(2,5,5,210,4,21,1),(2,5,5,190,4,22,1),(2,5,5,200,4,23,1),(1,2,6,90,5,24,1),(1,2,5,80,5,25,1),(7,7,8,0,4,26,1),(7,8,8,0,4,27,1),(7,7,0,0,6,30,1),(0,8,0,0,4,31,1);
/*!40000 ALTER TABLE `unity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `version`
--

DROP TABLE IF EXISTS `version`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `version` (
  `id_version` int NOT NULL AUTO_INCREMENT,
  `version_name` varchar(255) NOT NULL,
  PRIMARY KEY (`id_version`),
  UNIQUE KEY `uc_version_name` (`version_name`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `version`
--

LOCK TABLES `version` WRITE;
/*!40000 ALTER TABLE `version` DISABLE KEYS */;
INSERT INTO `version` VALUES (1,'v4');
/*!40000 ALTER TABLE `version` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `weapon`
--

DROP TABLE IF EXISTS `weapon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `weapon` (
  `id_weapon` bigint NOT NULL AUTO_INCREMENT,
  `attacks` int NOT NULL,
  `damage` varchar(255) NOT NULL,
  `is_shooting_weapon` bit(1) NOT NULL,
  `weapon_name` varchar(255) NOT NULL,
  `perforation` int NOT NULL,
  `ranged` int NOT NULL,
  `touch` int NOT NULL,
  `wound` int NOT NULL,
  `id_unity` bigint DEFAULT NULL,
  PRIMARY KEY (`id_weapon`),
  KEY `FKf6lwjib7nlslail6dnu0n5p5f` (`id_unity`),
  CONSTRAINT `FKf6lwjib7nlslail6dnu0n5p5f` FOREIGN KEY (`id_unity`) REFERENCES `unity` (`id_charter`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `weapon`
--

LOCK TABLES `weapon` WRITE;
/*!40000 ALTER TABLE `weapon` DISABLE KEYS */;
INSERT INTO `weapon` VALUES (6,1,'D6',_binary '','Rage de Kurnotheal',1,12,2,2,9),(7,6,'2',_binary '\0','Rage de Kurnotheal et Serments Cinglants',2,0,3,3,9),(8,1,'4',_binary '','Lance de Kurnoth',2,12,2,3,10),(9,5,'2',_binary '\0','Lance de Kurnoth',2,0,3,3,10),(10,6,'4',_binary '\0','Grande Ramure de Scaradroth',2,0,4,2,10),(11,5,'D3',_binary '\0','Lianes Perçantes et  Fauchon Spirituel',1,0,3,3,11),(12,6,'1',_binary '','Projecteurs à Maleflamme sur Mesure',2,10,2,3,13),(13,4,'D3',_binary '\0','Bâton du Rat Cornu',1,0,4,4,13),(14,6,'3',_binary '\0','Braseros à Maleflamme',2,0,4,2,13),(15,10,'1',_binary '','Frémifuries',1,12,4,4,14),(16,5,'2',_binary '\0','Serres Déchirantes',1,0,3,2,14),(17,10,'1',_binary '\0','Essaim de Tressanguilles',1,0,4,4,14),(18,5,'2',_binary '\0','Lame de Revenant et Pince Caudale',1,0,3,4,15),(19,5,'2',_binary '','Souffle Verdoyant',1,12,4,3,16),(20,4,'5',_binary '\0','Epée Gardienne',2,0,3,2,16),(21,2,'3',_binary '\0','Enormes Griffes Empaleuses',2,0,4,2,16),(22,3,'2',_binary '','Racine étrangleuses',1,10,3,2,17),(23,5,'2',_binary '\0','Attaque de Ballayage',1,0,4,2,17),(24,2,'3',_binary '\0','Enormes Griffes Empaleuses',2,0,4,2,17),(25,4,'D3',_binary '','Sceptre de Liances Tueuses',1,18,4,3,18),(26,5,'2',_binary '\0','Attaque de Ballayage',1,0,4,2,18),(27,2,'3',_binary '\0','Enormes Griffes Empaleuses',2,0,4,2,18),(28,6,'1',_binary '','Nuiée de Fiel-follets',1,12,4,4,19),(29,3,'D3',_binary '\0','Faux de Vert-bois et Aprelarve',1,0,3,4,19),(30,2,'1',_binary '','Arc Diaphane',1,12,3,3,20),(31,1,'1',_binary '\0','Serres Cruelles',0,0,3,4,20),(32,4,'2',_binary '\0','Longue Epée de Kurnoth',1,0,3,3,21),(33,3,'3',_binary '\0','Faux de Kurnoth',1,0,3,3,22),(34,2,'2',_binary '','Grand Arc de Kurnoth',1,18,3,3,23),(35,3,'1',_binary '\0','Apres Griffes',0,0,3,3,23),(36,2,'1',_binary '\0','Lame et Haste de Protecteur',1,0,3,4,24),(37,3,'1',_binary '\0','Serres Cruelles et Crocs',0,0,3,4,25),(38,6,'D3',_binary '\0','Mandibules Puissantes et Carapace Acérée',1,0,4,2,26),(39,3,'D3',_binary '\0','Masse Traînante',1,0,4,2,27);
/*!40000 ALTER TABLE `weapon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `weapon_keyword`
--

DROP TABLE IF EXISTS `weapon_keyword`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `weapon_keyword` (
  `id_weapon` bigint NOT NULL,
  `id_keyword` bigint NOT NULL,
  KEY `FKl85lptvv5wcjr0555we5kjmqh` (`id_keyword`),
  KEY `FK37f85vn9ss2umifdbxldmtiup` (`id_weapon`),
  CONSTRAINT `FK37f85vn9ss2umifdbxldmtiup` FOREIGN KEY (`id_weapon`) REFERENCES `weapon` (`id_weapon`),
  CONSTRAINT `FKl85lptvv5wcjr0555we5kjmqh` FOREIGN KEY (`id_keyword`) REFERENCES `keyword` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `weapon_keyword`
--

LOCK TABLES `weapon_keyword` WRITE;
/*!40000 ALTER TABLE `weapon_keyword` DISABLE KEYS */;
INSERT INTO `weapon_keyword` VALUES (7,4),(10,5),(12,5),(14,5),(15,5),(15,16),(16,17),(17,17),(17,5),(20,19),(21,4),(22,16),(23,20),(24,4),(26,20),(27,4),(28,21),(28,5),(30,21),(30,16),(32,4),(33,20),(34,23),(37,4),(38,26);
/*!40000 ALTER TABLE `weapon_keyword` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-10-09 12:21:16
