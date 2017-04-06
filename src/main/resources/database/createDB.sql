-- DROP DATABASE `internet`;
CREATE SCHEMA IF NOT EXISTS `internet` DEFAULT CHARACTER SET utf8;
USE `internet`;

-- Table `internet`.`users`
CREATE TABLE IF NOT EXISTS `internet`.`users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(100) NOT NULL,
  `password` VARCHAR(150) NOT NULL,
  `is_admin` TINYINT(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `idx_login` (`email` ASC))
ENGINE = InnoDB;

-- Table `internet`.`user_info_content`
CREATE TABLE IF NOT EXISTS `internet`.`users_info` (
  `user_id` INT NOT NULL,
  `surname` VARCHAR(30) NOT NULL,
  `name` VARCHAR(30) NOT NULL,
  `last_name` VARCHAR(30) NULL,
  `mobile_phone` VARCHAR(13) NOT NULL,
  PRIMARY KEY (`user_id`),
  INDEX `surname_idx` (`surname` ASC),
  INDEX `fk_user_info_content_users1_idx` (`user_id` ASC),
  CONSTRAINT `fk_user_info_content_users1`
    FOREIGN KEY (`user_id`)
    REFERENCES `internet`.`users` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

-- Table `internet`.`categories`
CREATE TABLE IF NOT EXISTS `internet`.`categories` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

-- Table `internet`.`products`
CREATE TABLE IF NOT EXISTS `internet`.`products` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `monthly_cost` DECIMAL(5,2) NOT NULL,
  `description` VARCHAR(500) NOT NULL,
  `category_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_products_categories1_idx` (`category_id` ASC),
  CONSTRAINT `fk_products_categories1`
    FOREIGN KEY (`category_id`)
    REFERENCES `internet`.`categories` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB;

-- Table `internet`.`services_list`
CREATE TABLE IF NOT EXISTS `internet`.`services_list` (
  `user_id` INT NOT NULL,
  `product_id` INT NOT NULL,
  PRIMARY KEY (`user_id`, `product_id`),
  INDEX `fk_users_has_products_products1_idx` (`product_id` ASC),
  INDEX `fk_users_has_products_users1_idx` (`user_id` ASC),
  CONSTRAINT `fk_users_has_products_users1`
    FOREIGN KEY (`user_id`)
    REFERENCES `internet`.`users` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_users_has_products_products1`
    FOREIGN KEY (`product_id`)
    REFERENCES `internet`.`products` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

-- Table `internet`.`features`
CREATE TABLE IF NOT EXISTS `internet`.`features` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `product_id` INT NOT NULL,
  `feature` VARCHAR(150) NOT NULL,
  INDEX `fk_features_products1_idx` (`product_id` ASC),
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_features_products1`
    FOREIGN KEY (`product_id`)
    REFERENCES `internet`.`products` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

-- Table `internet`.`subscriptions`
CREATE TABLE IF NOT EXISTS `internet`.`subscriptions` (
  `user_id` INT NOT NULL,
  `category_id` INT NOT NULL,
  PRIMARY KEY (`user_id`, `category_id`),
  INDEX `fk_users_has_categories_categories1_idx` (`category_id` ASC),
  INDEX `fk_users_has_categories_users1_idx` (`user_id` ASC),
  CONSTRAINT `fk_users_has_categories_users1`
    FOREIGN KEY (`user_id`)
    REFERENCES `internet`.`users` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_users_has_categories_categories1`
    FOREIGN KEY (`category_id`)
    REFERENCES `internet`.`categories` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;