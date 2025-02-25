USE osint_db;

CREATE TABLE found_data (
    host varchar(255) primary key
);

CREATE TABLE ip (
    #id int primary key auto_increment,
    host varchar(255),
    ip varchar(255),
    foreign key (host) references found_data(host)
);
