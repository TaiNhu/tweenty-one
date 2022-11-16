create database duantotnghiep
go
use duantotnghiep
go
create table users(
	user_name varchar(50) primary key,
	password varchar(20),
	image nvarchar(100),
	email varchar(50) unique,
	role varchar(10)
)
go

create table movies(
	id int identity(1, 1) primary key,
	name nvarchar(100)
	)
go
create table types(
	id int identity(1, 1) primary key,
	name nvarchar(100)
	)
go

create table categories (
	id int identity(1, 1) primary key,
	name nvarchar(100)
	)


	go

create table type_videos (
	id int identity(1, 1) primary key,
	name nvarchar(50),
	image nvarchar(100),
	description nvarchar(200),
	movie_id int references movies(id)  on delete cascade on update cascade,
	type_id int references types(id)  on delete cascade on update cascade
)

go 

create table video_genres(
	id int identity(1, 1) primary key,
	movie_id int references categories(id) on delete cascade on update cascade,
	category_id int references type_videos(id) on delete cascade on update cascade
)
go

create table episodes(
	id int identity(1, 1) primary key,
	name nvarchar(100),
	link nvarchar(200),
	episode_number int,
	type_id int references type_videos(id) on delete cascade on update cascade
	)

go
create table reviews(
	id int identity(1, 1) primary key,
	content nvarchar(200),
	recommend bit,
	movie_id int references type_videos(id)  on delete cascade on update cascade,
	user_id varchar(50) references users(user_name)  on delete cascade on update cascade
	)
go 

create table anime_list(
	id int identity(1, 1) primary key,
	score int,
	process int,
	note nvarchar(500),
	status varchar(30),
	user_id varchar(50) references users(user_name) on delete cascade on update cascade,
	movie_id int references type_videos(id)  on delete cascade on update cascade
	)
