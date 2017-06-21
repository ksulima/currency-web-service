CREATE TABLE `currencyarchive` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` varchar(12) DEFAULT NULL,
  `base` varchar(12) DEFAULT NULL,
  `currency` varchar(12) DEFAULT NULL,
  `rate` varchar(12) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=74 DEFAULT CHARSET=utf8;



CREATE TABLE `currency_dict` (
  `dict_id` int(11) NOT NULL AUTO_INCREMENT,
  `base_code` varchar(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`dict_id`),
  UNIQUE KEY `base_code` (`base_code`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci
;

ALTER TABLE `currency_dict` ADD INDEX `base_code` (`base_code`);



CREATE TABLE `currency_rates` (
  `currency_id` int(11) NOT NULL AUTO_INCREMENT,
  `date` varchar(11) NOT NULL,
  `dict_id_fk` int(11) NOT NULL,
  `base_code_fk` varchar(11) NOT NULL,
  `currency` varchar(11) NOT NULL,
  `rate` varchar(11) NOT NULL,
  PRIMARY KEY (`currency_id`, `date`, `currency`),
  KEY `fk_dict_id` (`dict_id_fk`),
  KEY `fk_base_code` (`base_code_fk`),
  CONSTRAINT `fk_dict_id` FOREIGN KEY (`dict_id_fk`) REFERENCES `currency_dict` (`dict_id`),
  CONSTRAINT `fk_base_code` FOREIGN KEY (`base_code_fk`) REFERENCES `currency_dict` (`base_code`)
  )ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci
;


CREATE TABLE `currency_rates` (
  `currency_id` int(11) NOT NULL AUTO_INCREMENT,
  `date` varchar(11) NOT NULL,
  `dict_id_fk` int(11) NOT NULL,
  `base_code_fk` varchar(11) NOT NULL,
  `currency` varchar(11) NOT NULL,
  `rate` varchar(11) NOT NULL,
  PRIMARY KEY (`currency_id`),
  UNIQUE KEY  (`date` ,`currency`, `base_code_fk`),
  KEY `fk_dict_id` (`dict_id_fk`),
  KEY `fk_base_code` (`base_code_fk`),
  CONSTRAINT `fk_dict_id` FOREIGN KEY (`dict_id_fk`) REFERENCES `currency_dict` (`dict_id`),
  CONSTRAINT `fk_base_code` FOREIGN KEY (`base_code_fk`) REFERENCES `currency_dict` (`base_code`)
  )ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci
;




SHOW ENGINE INNODB STATUS;
SHOW FULL COLUMNS FROM currency_rates;
SHOW INDEXES from currency_rates;