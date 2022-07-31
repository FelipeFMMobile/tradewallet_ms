CREATE TABLE `stock` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `symbol` varchar(10) NOT NULL,
  `name` varchar(255) NOT NULL,
  `average` double DEFAULT NULL,
  `price` double DEFAULT NULL,
  `last_price` double DEFAULT NULL,
  `maximum` double DEFAULT NULL,
  `minimum` double DEFAULT NULL,
  `open` double DEFAULT NULL,
  `updated` datetime(6) DEFAULT NULL,
  `variantion` double DEFAULT NULL,
  `volum` varchar(255) DEFAULT NULL,
  `semaphore` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`)
);