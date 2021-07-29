drop sequence if exists categories_seq;
drop sequence if exists products_seq;
drop sequence if exists users_seq;
drop table if exists public.ratings;
drop table if exists public.products;
drop table if exists public.categories;
drop table if exists public.user_role;
drop table if exists public.users;
drop table if exists public.roles;

-- Create user and related tables

create table users(
	id numeric
	constraint id_nn not null
	, username VARCHAR(30)
	constraint username_nn not null
	, fullname VARCHAR(50)
	, email VARCHAR(30)
	constraint user_email_nn not null
	, password text
	constraint user_password_nn not null
	, phonenum text
	, status text
	constraint user_status_nn not null
	, address text
	, constraint user_id_pk primary key (id)
	);
	

alter table users
add constraint user_email_un unique (email);

alter table users
add constraint user_username_un unique (username);

create table roles(
	id numeric
	constraint role_id_nn not null
	, type text
	constraint role_type_nn not null
	, constraint role_id_pk primary key (id)
	);
	
create table user_role(
	userid numeric
	constraint user_id_nn not null
	, roleid numeric
	constraint role_id_nn not null
	, constraint user_role_pk primary key (userid, roleid)
);

alter table user_role
add constraint user_role_fk foreign key (userid) references users(id); 

alter table user_role
add constraint role_user_fk foreign key (roleid) references roles(id); 

-- create product and related tables 

create table categories(
	id numeric
	constraint category_id_nn not null
	, name text
	constraint category_id_nn not null
	, description text
	, constraint category_id_pk primary key (id)
);

create table products(
	id numeric
	constraint product_id_nn not null
	, name varchar(100)
	constraint product_name_nn not null
	, description text
	, rate decimal
	, price decimal
	, categoryid numeric
	constraint product_categoryid_nn not null
	, createddate date
	, updateddate date
	, thumbnail bytea,
	, status text
	constraint product_status_nn not null
	, constraint product_id_pk primary key (id)
);

alter table products 
add constraint prod_cat_fk foreign key (categoryid) references categories(id);


create table ratings(
	userid numeric
	constraint rating_userid_nn not null
	, productid numeric
	constraint rating_productid_nn not null
	, rate decimal
	constraint rating_rate_nn not null
	, comment text
	, date date
	constraint rating_date_nn not null	
	, constraint rating_pk primary key (userid, productid)
);

alter table ratings 
add constraint rate_user_fk foreign key (userid) references users(id);

alter table ratings 
add constraint rate_prod_fk foreign key (productid) references products(id);

-- create sequences

create sequence users_seq
 start with 1
 owned by users.id;
 
create sequence products_seq
 start with 1
 owned by products.id ;
 
create sequence categories_seq
 start with 1
 owned by categories.id; 

