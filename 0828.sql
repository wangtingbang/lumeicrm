ALTER TABLE car_deal ADD vin_no varchar(100) NULL;
ALTER TABLE car_deal ADD qualification tinyint(3) DEFAULT 0 NULL;

CREATE TABLE `car_insurance` (
  `id` varchar(32) NOT NULL,
  `customer_id` varchar(32) DEFAULT NULL,
  `sales_id` varchar(255) DEFAULT NULL,
  `car_deal_id` varchar(255) DEFAULT NULL,
  `status` tinyint(3) DEFAULT NULL,
  `effective_date` date DEFAULT NULL,
  `credit_card_no` varchar(255) DEFAULT NULL,
  `insurance_company` varchar(255) DEFAULT NULL,
  `terms` varchar(255) DEFAULT NULL,
  `vin_no` varchar(255) DEFAULT NULL,
  `price` varchar(255) DEFAULT NULL,
  `case_no` varchar(255) DEFAULT NULL,
  `delete_flag` tinyint(4) DEFAULT NULL,
  `create_user_id` varchar(32) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `update_user_id` varchar(32) DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8