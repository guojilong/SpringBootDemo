CREATE TABLE IF NOT EXISTS USER (
`uid` INT UNSIGNED AUTO_INCREMENT,
`name` VARCHAR (20),
`age`  INT (5) ,
`pass` VARCHAR (20),
PRIMARY  KEY (`uid`)

)ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT  INTO USER (name,age,pass) values ("张三",13,"pwd");
INSERT  INTO USER (name,age,pass) values ("李四",18,"pwd");
SELECT * FROM USER ;