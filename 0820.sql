ALTER TABLE customer ADD city varchar(100) NULL;
ALTER TABLE customer ADD state varchar(100) NULL;
ALTER TABLE car_deal ADD remark varchar(255) NULL;

CREATE TABLE sequence (  
         name VARCHAR(50) NOT NULL,
         current_value INT NOT NULL,  
         increment INT NOT NULL DEFAULT 1,  
         PRIMARY KEY (name)  
) ENGINE=InnoDB;