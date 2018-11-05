use mysql;

CREATE USER `guest`@`localhost` IDENTIFIED BY 'guest123';

GRANT ALL ON *.* TO `guest`@`localhost` WITH GRANT OPTION;

create user 'guest'@'127.0.0.1' identified by 'guest123';

GRANT EXECUTE,INSERT,SELECT,UPDATE ON demo.* TO 'guest'@'%';

create DATABASE demo;