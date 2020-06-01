DROP TABLE IF EXISTS car_order;
 
CREATE TABLE car_order (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL,
  phone_number VARCHAR(15) NOT NULL,
  modelo VARCHAR(80) NOT NULL,
  grupo_modelo VARCHAR(80) NOT NULL,
  anno_modelo VARCHAR(4) NOT NULL,
  color VARCHAR(250) DEFAULT NULL,
  prs VARCHAR(250) DEFAULT NULL,
  commercial_number VARCHAR(250) DEFAULT NULL
);
