create table city_master (city_id integer not null, city_name varchar(255), state_id integer, primary key (city_id)) engine=InnoDB;

 create table country_master (country_id integer not null, country_name varchar(255), primary key (country_id)) engine=InnoDB;

 create table state_master(state_id integer not null, state_name varchar(255), country_id integer, primary key (state_id)) engine=InnoDB;

 create table user_dtls (user_id integer not null auto_increment, created_date date, email varchar(255), phno bigint,  pwd varchar(255),update_pwd varchar(255), name varchar(255), updated_date date, city_id integer, country_id integer, state_id integer, primary key (user_id)) engine=InnoDB;

 alter table city_master add constraint FK6p2u50v8fg2y0js6djc6xanit foreign key (state_id) references state_master (state_id);

 alter table state_master add constraint FKghic7mqjt6qb9vq7up7awu0er foreign key (country_id) references country_master (country_id);

 alter table user_dtls add constraint FK29eqyw0gxw5r4f1ommy11nd9i foreign key (city_id) references city_master (city_id);

 alter table user_dtls add constraint FKge8lxibk9q3wf206s600otk61 foreign key (country_id) references country_master (country_id);

 alter table user_dtls add constraint FKs9pywmstsu61hpsaodsg9lwhe foreign key (state_id) references state_master (state_id);


insert into country_master values(1,'India');
insert into country_master values(2,'USA');


insert into state_master values(1,'AP',1);
insert into state_master values(2,'TG',1);

insert into state_master values(3,'RI',2);
insert into state_master values(4,'NJ',2);


insert into city_master values(1,'Guntur',1);
insert into city_master values(2,'Ongole',1);

insert into city_master values(3,'Hyderabad',2);
insert into city_master values(4,'Warangal',2);

insert into city_master values(5,'Providence',3);
insert into city_master values(6,'New Port',3);

insert into city_master values(7,'Jersay',4);
insert into city_master values(8,'Newark',4);
