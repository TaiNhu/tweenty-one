USE [duantotnghiep]
GO
/****** Object:  Database [duantotnghiep]    Script Date: 12/5/2022 2:31:21 PM ******/
CREATE DATABASE [duantotnghiep]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'duantotnghiep', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\duantotnghiep.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'duantotnghiep_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\duantotnghiep_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [duantotnghiep] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [duantotnghiep].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [duantotnghiep] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [duantotnghiep] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [duantotnghiep] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [duantotnghiep] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [duantotnghiep] SET ARITHABORT OFF 
GO
ALTER DATABASE [duantotnghiep] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [duantotnghiep] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [duantotnghiep] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [duantotnghiep] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [duantotnghiep] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [duantotnghiep] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [duantotnghiep] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [duantotnghiep] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [duantotnghiep] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [duantotnghiep] SET  ENABLE_BROKER 
GO
ALTER DATABASE [duantotnghiep] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [duantotnghiep] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [duantotnghiep] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [duantotnghiep] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [duantotnghiep] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [duantotnghiep] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [duantotnghiep] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [duantotnghiep] SET RECOVERY FULL 
GO
ALTER DATABASE [duantotnghiep] SET  MULTI_USER 
GO
ALTER DATABASE [duantotnghiep] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [duantotnghiep] SET DB_CHAINING OFF 
GO
ALTER DATABASE [duantotnghiep] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [duantotnghiep] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [duantotnghiep] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [duantotnghiep] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
EXEC sys.sp_db_vardecimal_storage_format N'duantotnghiep', N'ON'
GO
ALTER DATABASE [duantotnghiep] SET QUERY_STORE = OFF
GO
USE [duantotnghiep]
GO
/****** Object:  Table [dbo].[anime_list]    Script Date: 12/5/2022 2:31:21 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[anime_list](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[score] [int] NULL,
	[process] [int] NULL,
	[note] [nvarchar](500) NULL,
	[status] [varchar](30) NULL,
	[user_id] [varchar](50) NULL,
	[movie_id] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[categories]    Script Date: 12/5/2022 2:31:21 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[categories](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](100) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[episodes]    Script Date: 12/5/2022 2:31:21 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[episodes](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](100) NULL,
	[link] [nvarchar](200) NULL,
	[episode_number] [int] NULL,
	[type_id] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[movies]    Script Date: 12/5/2022 2:31:21 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[movies](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](100) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[reviews]    Script Date: 12/5/2022 2:31:21 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[reviews](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[content] [nvarchar](200) NULL,
	[recommend] [bit] NULL,
	[movie_id] [int] NULL,
	[user_id] [varchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[type_videos]    Script Date: 12/5/2022 2:31:21 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[type_videos](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](50) NULL,
	[image] [nvarchar](100) NULL,
	[description] [nvarchar](1000) NULL,
	[movie_id] [int] NULL,
	[type_id] [int] NULL,
	[count] [int] NULL,
	[type_name] [nvarchar](100) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[types]    Script Date: 12/5/2022 2:31:21 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[types](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](100) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[users]    Script Date: 12/5/2022 2:31:21 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[users](
	[user_name] [varchar](50) NOT NULL,
	[password] [varchar](20) NULL,
	[image] [nvarchar](100) NULL,
	[email] [varchar](50) NULL,
	[role] [varchar](10) NULL,
PRIMARY KEY CLUSTERED 
(
	[user_name] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[video_genres]    Script Date: 12/5/2022 2:31:21 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[video_genres](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[category_id] [int] NULL,
	[movie_id] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[anime_list] ON 

INSERT [dbo].[anime_list] ([id], [score], [process], [note], [status], [user_id], [movie_id]) VALUES (1, 10, 1, N'Phim hay tuyệt', N'watching', N'test1', 1)
INSERT [dbo].[anime_list] ([id], [score], [process], [note], [status], [user_id], [movie_id]) VALUES (3, 9, 1, N'Phim hành động đặc sắt lắm nha', N'watching', N'test1', 4)
INSERT [dbo].[anime_list] ([id], [score], [process], [note], [status], [user_id], [movie_id]) VALUES (4, 0, 0, NULL, N'watching', N'test1', 8)
INSERT [dbo].[anime_list] ([id], [score], [process], [note], [status], [user_id], [movie_id]) VALUES (5, 0, 0, NULL, N'watching', N'test1', 7)
SET IDENTITY_INSERT [dbo].[anime_list] OFF
GO
SET IDENTITY_INSERT [dbo].[categories] ON 

INSERT [dbo].[categories] ([id], [name]) VALUES (1, N'Action')
INSERT [dbo].[categories] ([id], [name]) VALUES (2, N'Adventure')
INSERT [dbo].[categories] ([id], [name]) VALUES (3, N'Comedy')
INSERT [dbo].[categories] ([id], [name]) VALUES (4, N'Drama
')
INSERT [dbo].[categories] ([id], [name]) VALUES (5, N'Slice of Life')
INSERT [dbo].[categories] ([id], [name]) VALUES (6, N'Fantasy')
INSERT [dbo].[categories] ([id], [name]) VALUES (7, N'Magic')
INSERT [dbo].[categories] ([id], [name]) VALUES (8, N'Supernatural')
INSERT [dbo].[categories] ([id], [name]) VALUES (9, N'Horror')
INSERT [dbo].[categories] ([id], [name]) VALUES (10, N'Mystery')
INSERT [dbo].[categories] ([id], [name]) VALUES (11, N'Psychological')
INSERT [dbo].[categories] ([id], [name]) VALUES (12, N'Romance')
INSERT [dbo].[categories] ([id], [name]) VALUES (13, N'Sci-Fi
')
SET IDENTITY_INSERT [dbo].[categories] OFF
GO
SET IDENTITY_INSERT [dbo].[episodes] ON 

INSERT [dbo].[episodes] ([id], [name], [link], [episode_number], [type_id]) VALUES (1, NULL, N'1.mp4', 1, 1)
INSERT [dbo].[episodes] ([id], [name], [link], [episode_number], [type_id]) VALUES (2, NULL, N'2.mp4', 2, 1)
INSERT [dbo].[episodes] ([id], [name], [link], [episode_number], [type_id]) VALUES (3, NULL, N'3.mp4', 3, 1)
INSERT [dbo].[episodes] ([id], [name], [link], [episode_number], [type_id]) VALUES (4, NULL, N'4.mp4', 4, 1)
INSERT [dbo].[episodes] ([id], [name], [link], [episode_number], [type_id]) VALUES (5, NULL, N'5.mp4', 5, 1)
INSERT [dbo].[episodes] ([id], [name], [link], [episode_number], [type_id]) VALUES (6, NULL, N'6.mp4', 6, 1)
INSERT [dbo].[episodes] ([id], [name], [link], [episode_number], [type_id]) VALUES (7, NULL, N'7.mp4', 7, 1)
INSERT [dbo].[episodes] ([id], [name], [link], [episode_number], [type_id]) VALUES (9, NULL, N'8.mp4', 1, 4)
INSERT [dbo].[episodes] ([id], [name], [link], [episode_number], [type_id]) VALUES (10, NULL, N'9.mp4', 2, 4)
INSERT [dbo].[episodes] ([id], [name], [link], [episode_number], [type_id]) VALUES (11, NULL, N'10.mp4', 3, 4)
INSERT [dbo].[episodes] ([id], [name], [link], [episode_number], [type_id]) VALUES (12, NULL, N'11.mp4', 1, 5)
INSERT [dbo].[episodes] ([id], [name], [link], [episode_number], [type_id]) VALUES (13, NULL, N'12..mp4', 2, 5)
INSERT [dbo].[episodes] ([id], [name], [link], [episode_number], [type_id]) VALUES (14, NULL, N'13.mp4', 3, 5)
INSERT [dbo].[episodes] ([id], [name], [link], [episode_number], [type_id]) VALUES (15, NULL, N'14.mp4', 1, 7)
INSERT [dbo].[episodes] ([id], [name], [link], [episode_number], [type_id]) VALUES (16, NULL, N'15.mp4', 2, 7)
INSERT [dbo].[episodes] ([id], [name], [link], [episode_number], [type_id]) VALUES (17, NULL, N'16.mp4', 3, 7)
INSERT [dbo].[episodes] ([id], [name], [link], [episode_number], [type_id]) VALUES (18, NULL, N'17.mp4', 1, 8)
INSERT [dbo].[episodes] ([id], [name], [link], [episode_number], [type_id]) VALUES (19, NULL, N'18.mp4', 2, 8)
INSERT [dbo].[episodes] ([id], [name], [link], [episode_number], [type_id]) VALUES (20, NULL, N'19.mp4', 3, 8)
SET IDENTITY_INSERT [dbo].[episodes] OFF
GO
SET IDENTITY_INSERT [dbo].[movies] ON 

INSERT [dbo].[movies] ([id], [name]) VALUES (1, N'HOSHI NO SAMIDARE')
INSERT [dbo].[movies] ([id], [name]) VALUES (2, N'SEIKEN DENSETSU: LEGEND OF MANA - THE TEARDROP CRYSTAL')
INSERT [dbo].[movies] ([id], [name]) VALUES (3, N'UCHI NO SHISHOU WA SHIPPO GA NAI')
INSERT [dbo].[movies] ([id], [name]) VALUES (4, N'GOKUSHUFUDOU')
SET IDENTITY_INSERT [dbo].[movies] OFF
GO
SET IDENTITY_INSERT [dbo].[reviews] ON 

INSERT [dbo].[reviews] ([id], [content], [recommend], [movie_id], [user_id]) VALUES (5, N'that te ko muon noi nhieu', 0, 4, N'test1')
INSERT [dbo].[reviews] ([id], [content], [recommend], [movie_id], [user_id]) VALUES (6, N'hay hay', 1, 8, N'test2')
INSERT [dbo].[reviews] ([id], [content], [recommend], [movie_id], [user_id]) VALUES (7, N'xem chan', 0, 8, N'test1')
INSERT [dbo].[reviews] ([id], [content], [recommend], [movie_id], [user_id]) VALUES (8, N'Hay lắm nên xem ha', 1, 8, N'FzlEIevUx0syYPRcqDtEmQ')
INSERT [dbo].[reviews] ([id], [content], [recommend], [movie_id], [user_id]) VALUES (1002, N'Test chức năng ha', 1, 1, N'FzlEIevUx0syYPRcqDtEmQ')
INSERT [dbo].[reviews] ([id], [content], [recommend], [movie_id], [user_id]) VALUES (1003, N'Ổn ổn', 1, 5, N'FzlEIevUx0syYPRcqDtEmQ')
INSERT [dbo].[reviews] ([id], [content], [recommend], [movie_id], [user_id]) VALUES (1004, N'Khoong thichs', 0, 5, N'test1')
INSERT [dbo].[reviews] ([id], [content], [recommend], [movie_id], [user_id]) VALUES (1005, N'Anime xem hay hon live action nhieu. nen xem nha ae', 1, 7, N'test1')
INSERT [dbo].[reviews] ([id], [content], [recommend], [movie_id], [user_id]) VALUES (1006, N'Ái chà test thử phát nào', 1, 1, N'test1')
SET IDENTITY_INSERT [dbo].[reviews] OFF
GO
SET IDENTITY_INSERT [dbo].[type_videos] ON 

INSERT [dbo].[type_videos] ([id], [name], [image], [description], [movie_id], [type_id], [count], [type_name]) VALUES (1, N'HOSHI NO SAMIDARE', N'1.jpg', N'Asamiya Yuuhi là một sinh viên đại học vô cùng bình thường... cho đến ngày một con thằn lằn xuất hiện và yêu cầu cậu giải cứu thế giới. Điều tiếp theo anh ta được tiết lộ chính là anh đã được trao cho một chiếc nhẫn với sức mạnh đặc biệt', 1, 2, 0, N'Phần 1')
INSERT [dbo].[type_videos] ([id], [name], [image], [description], [movie_id], [type_id], [count], [type_name]) VALUES (4, N'SEIKEN DENSETSU: LEGEND OF MANA ', N'2.jpg', N'Bộ anime được dựa trên tựa game Legend of Mana.', 2, 2, 3, N'Phần 1')
INSERT [dbo].[type_videos] ([id], [name], [image], [description], [movie_id], [type_id], [count], [type_name]) VALUES (5, N'Uchi no Shishou wa Shippo ga Nai', N'3.jpg', N'Câu chuyện xoay quanh Mameda, một cô gái tanuki có ước mơ trở thành con người và làm một nghệ nhân rakugo. Để thực hiện điều đó, cô quyết định trở thành người học việc của một nghệ sĩ rakugo có tên Bunko Dakokutei.', 3, 2, 0, N'Phần 1')
INSERT [dbo].[type_videos] ([id], [name], [image], [description], [movie_id], [type_id], [count], [type_name]) VALUES (7, N'GOKUSHUFUDOU', N'ok2.jpg', N'Gokushufudou kể về ông trùm của giới yakuza Nhật Bản là Tatsu, kẻ được mệnh danh là "Tatsu Bất Tử" nay "nghỉ hưu" và lo chuyện bếp núc để vợ chuyên tâm "xông pha" xã hội. Từ một kẻ quen cầm kiếm và khiến giới giang hồ khiếp sợ, nay Tatsu phải chật vật nấu nướng, đi chợ, lau dọn nhà cửa và từ đó, những rắc rối liên tục xảy đến với anh.', 4, 2, 2, N'Phần 1')
INSERT [dbo].[type_videos] ([id], [name], [image], [description], [movie_id], [type_id], [count], [type_name]) VALUES (8, N'GOKUSHUFUDOU (LIVE ACTION)', N'ok.jpg', N'Gokushufudou kể về ông trùm của giới yakuza Nhật Bản là Tatsu, kẻ được mệnh danh là "Tatsu Bất Tử" nay "nghỉ hưu" và lo chuyện bếp núc để vợ chuyên tâm "xông pha" xã hội. Từ một kẻ quen cầm kiếm và khiến giới giang hồ khiếp sợ, nay Tatsu phải chật vật nấu nướng, đi chợ, lau dọn nhà cửa và từ đó, những rắc rối liên tục xảy đến với anh.', 4, 8, 8, N'Live Action')
SET IDENTITY_INSERT [dbo].[type_videos] OFF
GO
SET IDENTITY_INSERT [dbo].[types] ON 

INSERT [dbo].[types] ([id], [name]) VALUES (1, N'Trailer')
INSERT [dbo].[types] ([id], [name]) VALUES (2, N'TV series')
INSERT [dbo].[types] ([id], [name]) VALUES (3, N'Movies')
INSERT [dbo].[types] ([id], [name]) VALUES (4, N'Special')
INSERT [dbo].[types] ([id], [name]) VALUES (5, N'OVA')
INSERT [dbo].[types] ([id], [name]) VALUES (6, N'ONA')
INSERT [dbo].[types] ([id], [name]) VALUES (8, N'Live Action')
SET IDENTITY_INSERT [dbo].[types] OFF
GO
INSERT [dbo].[users] ([user_name], [password], [image], [email], [role]) VALUES (N'admin', N'123', NULL, N'admin@gmail.com', N'ADMIN')
INSERT [dbo].[users] ([user_name], [password], [image], [email], [role]) VALUES (N'FzlEIevUx0syYPRcqDtEmQ', NULL, N'https://lh3.googleusercontent.com/a/ALm5wu1JypiR1-zWQy8GCTGWYEBPf-W3_Cd2jp8igZ7w=s96-c', N'testgamelol1302@gmail.com', N'USER')
INSERT [dbo].[users] ([user_name], [password], [image], [email], [role]) VALUES (N'nhutai', N'123', N'user.png', N'tanpha01677@gmail.com', N'USER')
INSERT [dbo].[users] ([user_name], [password], [image], [email], [role]) VALUES (N'test1', N'pass', N'1670054744105violet.jpg', N'tai@gmail.com', N'USER')
INSERT [dbo].[users] ([user_name], [password], [image], [email], [role]) VALUES (N'test2', N'123', N'user.png', N'fdasfs@gmail.com', N'USER')
INSERT [dbo].[users] ([user_name], [password], [image], [email], [role]) VALUES (N'Vj8OtUm4ElHNUH_bJv9ZTQ', NULL, N'https://lh3.googleusercontent.com/a/ALm5wu05zexsnqBklNIQpeOXwsPCkqG9YJOmseP7SFE=s96-c', N'nhutai1302@gmail.com', N'USER')
GO
SET IDENTITY_INSERT [dbo].[video_genres] ON 

INSERT [dbo].[video_genres] ([id], [category_id], [movie_id]) VALUES (1, 1, 1)
INSERT [dbo].[video_genres] ([id], [category_id], [movie_id]) VALUES (7, 2, 1)
INSERT [dbo].[video_genres] ([id], [category_id], [movie_id]) VALUES (8, 3, 1)
INSERT [dbo].[video_genres] ([id], [category_id], [movie_id]) VALUES (9, 4, 1)
INSERT [dbo].[video_genres] ([id], [category_id], [movie_id]) VALUES (10, 11, 1)
INSERT [dbo].[video_genres] ([id], [category_id], [movie_id]) VALUES (11, 2, 4)
INSERT [dbo].[video_genres] ([id], [category_id], [movie_id]) VALUES (12, 13, 4)
INSERT [dbo].[video_genres] ([id], [category_id], [movie_id]) VALUES (13, 8, 5)
INSERT [dbo].[video_genres] ([id], [category_id], [movie_id]) VALUES (14, 3, 7)
INSERT [dbo].[video_genres] ([id], [category_id], [movie_id]) VALUES (15, 5, 7)
INSERT [dbo].[video_genres] ([id], [category_id], [movie_id]) VALUES (16, 4, 7)
INSERT [dbo].[video_genres] ([id], [category_id], [movie_id]) VALUES (17, 3, 8)
INSERT [dbo].[video_genres] ([id], [category_id], [movie_id]) VALUES (18, 5, 8)
SET IDENTITY_INSERT [dbo].[video_genres] OFF
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__users__AB6E61643F39FB2C]    Script Date: 12/5/2022 2:31:22 PM ******/
ALTER TABLE [dbo].[users] ADD UNIQUE NONCLUSTERED 
(
	[email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
ALTER TABLE [dbo].[anime_list]  WITH CHECK ADD FOREIGN KEY([movie_id])
REFERENCES [dbo].[type_videos] ([id])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[anime_list]  WITH CHECK ADD FOREIGN KEY([user_id])
REFERENCES [dbo].[users] ([user_name])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[episodes]  WITH CHECK ADD FOREIGN KEY([type_id])
REFERENCES [dbo].[type_videos] ([id])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[reviews]  WITH CHECK ADD FOREIGN KEY([movie_id])
REFERENCES [dbo].[type_videos] ([id])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[reviews]  WITH CHECK ADD FOREIGN KEY([user_id])
REFERENCES [dbo].[users] ([user_name])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[type_videos]  WITH CHECK ADD FOREIGN KEY([movie_id])
REFERENCES [dbo].[movies] ([id])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[type_videos]  WITH CHECK ADD FOREIGN KEY([type_id])
REFERENCES [dbo].[types] ([id])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[video_genres]  WITH CHECK ADD FOREIGN KEY([category_id])
REFERENCES [dbo].[categories] ([id])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[video_genres]  WITH CHECK ADD FOREIGN KEY([movie_id])
REFERENCES [dbo].[type_videos] ([id])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
USE [master]
GO
ALTER DATABASE [duantotnghiep] SET  READ_WRITE 
GO

select * from Users
