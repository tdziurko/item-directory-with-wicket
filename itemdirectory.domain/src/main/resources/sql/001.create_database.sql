create database item_directory character set utf8 collate utf8_polish_ci;

GRANT ALL PRIVILEGES ON item_directory.* TO item@localhost IDENTIFIED BY 'item';
GRANT ALL PRIVILEGES ON item_directory.* TO item@"%" IDENTIFIED BY 'item';

flush privileges;