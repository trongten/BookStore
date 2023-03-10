USE [master]
GO
/****** Object:  Database [NhaSachTNL]    Script Date: 12/25/2021 11:08:39 AM ******/
CREATE DATABASE [NhaSachTNL]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'NhaSachTNL', FILENAME = N'D:\Microsoft SQL Server\MSSQL12.MSSQLSERVER\MSSQL\DATA\NhaSachTNL.mdf' , SIZE = 4288KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'NhaSachTNL_log', FILENAME = N'D:\Microsoft SQL Server\MSSQL12.MSSQLSERVER\MSSQL\DATA\NhaSachTNL_log.ldf' , SIZE = 1088KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [NhaSachTNL] SET COMPATIBILITY_LEVEL = 120
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [NhaSachTNL].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [NhaSachTNL] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [NhaSachTNL] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [NhaSachTNL] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [NhaSachTNL] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [NhaSachTNL] SET ARITHABORT OFF 
GO
ALTER DATABASE [NhaSachTNL] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [NhaSachTNL] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [NhaSachTNL] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [NhaSachTNL] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [NhaSachTNL] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [NhaSachTNL] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [NhaSachTNL] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [NhaSachTNL] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [NhaSachTNL] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [NhaSachTNL] SET  DISABLE_BROKER 
GO
ALTER DATABASE [NhaSachTNL] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [NhaSachTNL] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [NhaSachTNL] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [NhaSachTNL] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [NhaSachTNL] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [NhaSachTNL] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [NhaSachTNL] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [NhaSachTNL] SET RECOVERY FULL 
GO
ALTER DATABASE [NhaSachTNL] SET  MULTI_USER 
GO
ALTER DATABASE [NhaSachTNL] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [NhaSachTNL] SET DB_CHAINING OFF 
GO
ALTER DATABASE [NhaSachTNL] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [NhaSachTNL] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
ALTER DATABASE [NhaSachTNL] SET DELAYED_DURABILITY = DISABLED 
GO
EXEC sys.sp_db_vardecimal_storage_format N'NhaSachTNL', N'ON'
GO
USE [NhaSachTNL]
GO
/****** Object:  Table [dbo].[CTPhieuDatTruoc]    Script Date: 12/25/2021 11:08:40 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CTPhieuDatTruoc](
	[maphieudat] [nchar](5) NOT NULL,
	[thanhtien] [money] NULL,
	[masanpham] [nchar](5) NOT NULL,
	[soluongsanpham] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[maphieudat] ASC,
	[masanpham] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[CTHoaDon]    Script Date: 12/25/2021 11:08:40 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CTHoaDon](
	[thanhtien] [money] NULL,
	[soluongsanpham] [int] NULL,
	[mahoadon] [nchar](5) NOT NULL,
	[masanpham] [nchar](5) NOT NULL,
 CONSTRAINT [PK__CTHoaDon__6481AFBE86E73838] PRIMARY KEY CLUSTERED 
(
	[mahoadon] ASC,
	[masanpham] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[HoaDon]    Script Date: 12/25/2021 11:08:40 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[HoaDon](
	[mahoadon] [nchar](5) NOT NULL,
	[tongtienhoadon] [money] NULL,
	[ngaylaphoadon] [datetime] NOT NULL,
	[manhanvien] [nchar](5) NOT NULL,
	[makhachhang] [nchar](5) NULL,
 CONSTRAINT [PK_HoaDon1] PRIMARY KEY CLUSTERED 
(
	[mahoadon] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[KhachHang]    Script Date: 12/25/2021 11:08:40 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[KhachHang](
	[makhachhang] [nchar](5) NOT NULL,
	[tenkhachhang] [nvarchar](50) NOT NULL,
	[sodienthoai] [nvarchar](11) NOT NULL,
 CONSTRAINT [PK_KhachHang] PRIMARY KEY CLUSTERED 
(
	[makhachhang] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[LoaiSanPham]    Script Date: 12/25/2021 11:08:40 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[LoaiSanPham](
	[tenloai] [nvarchar](50) NULL,
	[maloai] [nvarchar](10) NOT NULL,
 CONSTRAINT [PK_LoaiSanPham] PRIMARY KEY CLUSTERED 
(
	[maloai] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[NhanVien]    Script Date: 12/25/2021 11:08:40 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NhanVien](
	[manhanvien] [nchar](5) NOT NULL,
	[hoten] [nvarchar](100) NOT NULL,
	[ngaysinh] [date] NULL,
	[diachi] [nvarchar](100) NULL,
	[sochungminh] [nvarchar](50) NULL,
	[trangthailamviec] [bit] NOT NULL,
	[gioitinh] [bit] NULL,
	[ca] [smallint] NOT NULL,
	[maquanly] [nchar](5) NULL,
	[sodienthoai] [nvarchar](15) NULL,
 CONSTRAINT [PK_NhanVien] PRIMARY KEY CLUSTERED 
(
	[manhanvien] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[PhieuDatTruoc]    Script Date: 12/25/2021 11:08:40 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PhieuDatTruoc](
	[maphieudat] [nchar](5) NOT NULL,
	[ngaylap] [datetime] NULL,
	[ngaynhanhang] [datetime] NULL,
	[tongtienphieudat] [money] NULL,
	[manhanvien] [nchar](5) NULL,
	[makhachhang] [nchar](5) NULL,
	[trangthai] [nvarchar](50) NULL,
 CONSTRAINT [PK_PhieuDatTruoc1] PRIMARY KEY CLUSTERED 
(
	[maphieudat] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[SanPham]    Script Date: 12/25/2021 11:08:40 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[SanPham](
	[masanpham] [nchar](5) NOT NULL,
	[tensanpham] [nvarchar](max) NOT NULL,
	[soluong] [int] NOT NULL,
	[giadonvi] [money] NOT NULL,
	[gianhap] [money] NULL,
	[anhsanpham] [nvarchar](100) NOT NULL,
	[xuatxu] [nvarchar](100) NULL,
	[chatlieu] [nvarchar](100) NULL,
	[sotrang] [int] NULL,
	[tentacgia] [nvarchar](max) NULL,
	[nhaxuatban] [nvarchar](max) NULL,
	[nhacungcap] [nvarchar](100) NULL,
	[maloai] [nvarchar](10) NULL,
 CONSTRAINT [PK_SanPham1] PRIMARY KEY CLUSTERED 
(
	[masanpham] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
/****** Object:  Table [dbo].[TaiKhoan]    Script Date: 12/25/2021 11:08:40 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TaiKhoan](
	[tentaikhoan] [nchar](5) NOT NULL,
	[matkhau] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_TaiKhoan] PRIMARY KEY CLUSTERED 
(
	[tentaikhoan] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
INSERT [dbo].[CTPhieuDatTruoc] ([maphieudat], [thanhtien], [masanpham], [soluongsanpham]) VALUES (N'PD001', 831600.0000, N'SP004', 14)
INSERT [dbo].[CTPhieuDatTruoc] ([maphieudat], [thanhtien], [masanpham], [soluongsanpham]) VALUES (N'PD003', 66220.0000, N'SP042', 14)
INSERT [dbo].[CTPhieuDatTruoc] ([maphieudat], [thanhtien], [masanpham], [soluongsanpham]) VALUES (N'PD004', 178200.0000, N'SP004', 3)
INSERT [dbo].[CTPhieuDatTruoc] ([maphieudat], [thanhtien], [masanpham], [soluongsanpham]) VALUES (N'PD005', 330000.0000, N'SP008', 6)
INSERT [dbo].[CTPhieuDatTruoc] ([maphieudat], [thanhtien], [masanpham], [soluongsanpham]) VALUES (N'PD006', 145200.0000, N'SP016', 6)
INSERT [dbo].[CTPhieuDatTruoc] ([maphieudat], [thanhtien], [masanpham], [soluongsanpham]) VALUES (N'PD006', 24200.0000, N'SP017', 1)
INSERT [dbo].[CTPhieuDatTruoc] ([maphieudat], [thanhtien], [masanpham], [soluongsanpham]) VALUES (N'PD006', 24200.0000, N'SP021', 1)
INSERT [dbo].[CTPhieuDatTruoc] ([maphieudat], [thanhtien], [masanpham], [soluongsanpham]) VALUES (N'PD006', 4730.0000, N'SP042', 1)
INSERT [dbo].[CTPhieuDatTruoc] ([maphieudat], [thanhtien], [masanpham], [soluongsanpham]) VALUES (N'PD006', 32340.0000, N'SP045', 3)
INSERT [dbo].[CTPhieuDatTruoc] ([maphieudat], [thanhtien], [masanpham], [soluongsanpham]) VALUES (N'PD006', 44550.0000, N'SP046', 3)
INSERT [dbo].[CTPhieuDatTruoc] ([maphieudat], [thanhtien], [masanpham], [soluongsanpham]) VALUES (N'PD007', 66000.0000, N'SP002', 1)
INSERT [dbo].[CTPhieuDatTruoc] ([maphieudat], [thanhtien], [masanpham], [soluongsanpham]) VALUES (N'PD007', 22000.0000, N'SP005', 1)
INSERT [dbo].[CTPhieuDatTruoc] ([maphieudat], [thanhtien], [masanpham], [soluongsanpham]) VALUES (N'PD007', 24200.0000, N'SP010', 1)
INSERT [dbo].[CTPhieuDatTruoc] ([maphieudat], [thanhtien], [masanpham], [soluongsanpham]) VALUES (N'PD007', 24200.0000, N'SP015', 1)
INSERT [dbo].[CTPhieuDatTruoc] ([maphieudat], [thanhtien], [masanpham], [soluongsanpham]) VALUES (N'PD009', 59400.0000, N'SP004', 1)
INSERT [dbo].[CTPhieuDatTruoc] ([maphieudat], [thanhtien], [masanpham], [soluongsanpham]) VALUES (N'PD009', 49500.0000, N'SP006', 1)
INSERT [dbo].[CTPhieuDatTruoc] ([maphieudat], [thanhtien], [masanpham], [soluongsanpham]) VALUES (N'PD010', 91960.0000, N'SP001', 1)
INSERT [dbo].[CTPhieuDatTruoc] ([maphieudat], [thanhtien], [masanpham], [soluongsanpham]) VALUES (N'PD012', 91960.0000, N'SP001', 1)
INSERT [dbo].[CTPhieuDatTruoc] ([maphieudat], [thanhtien], [masanpham], [soluongsanpham]) VALUES (N'PD013', 59400.0000, N'SP004', 1)
INSERT [dbo].[CTPhieuDatTruoc] ([maphieudat], [thanhtien], [masanpham], [soluongsanpham]) VALUES (N'PD013', 4730.0000, N'SP042', 1)
INSERT [dbo].[CTPhieuDatTruoc] ([maphieudat], [thanhtien], [masanpham], [soluongsanpham]) VALUES (N'PD014', 60500.0000, N'SP003', 1)
INSERT [dbo].[CTPhieuDatTruoc] ([maphieudat], [thanhtien], [masanpham], [soluongsanpham]) VALUES (N'PD014', 49500.0000, N'SP007', 1)
INSERT [dbo].[CTPhieuDatTruoc] ([maphieudat], [thanhtien], [masanpham], [soluongsanpham]) VALUES (N'PD014', 24200.0000, N'SP012', 1)
INSERT [dbo].[CTPhieuDatTruoc] ([maphieudat], [thanhtien], [masanpham], [soluongsanpham]) VALUES (N'PD015', 55000.0000, N'SP008', 1)
INSERT [dbo].[CTPhieuDatTruoc] ([maphieudat], [thanhtien], [masanpham], [soluongsanpham]) VALUES (N'PD016', 183920.0000, N'SP001', 2)
INSERT [dbo].[CTPhieuDatTruoc] ([maphieudat], [thanhtien], [masanpham], [soluongsanpham]) VALUES (N'PD016', 24200.0000, N'SP009', 1)
INSERT [dbo].[CTPhieuDatTruoc] ([maphieudat], [thanhtien], [masanpham], [soluongsanpham]) VALUES (N'PD017', 66000.0000, N'SP002', 1)
INSERT [dbo].[CTPhieuDatTruoc] ([maphieudat], [thanhtien], [masanpham], [soluongsanpham]) VALUES (N'PD017', 60500.0000, N'SP003', 1)
INSERT [dbo].[CTPhieuDatTruoc] ([maphieudat], [thanhtien], [masanpham], [soluongsanpham]) VALUES (N'PD017', 118800.0000, N'SP004', 2)
INSERT [dbo].[CTPhieuDatTruoc] ([maphieudat], [thanhtien], [masanpham], [soluongsanpham]) VALUES (N'PD017', 99000.0000, N'SP007', 2)
INSERT [dbo].[CTPhieuDatTruoc] ([maphieudat], [thanhtien], [masanpham], [soluongsanpham]) VALUES (N'PD017', 24200.0000, N'SP010', 1)
INSERT [dbo].[CTPhieuDatTruoc] ([maphieudat], [thanhtien], [masanpham], [soluongsanpham]) VALUES (N'PD018', 66000.0000, N'SP002', 1)
INSERT [dbo].[CTPhieuDatTruoc] ([maphieudat], [thanhtien], [masanpham], [soluongsanpham]) VALUES (N'PD018', 49500.0000, N'SP006', 1)
INSERT [dbo].[CTPhieuDatTruoc] ([maphieudat], [thanhtien], [masanpham], [soluongsanpham]) VALUES (N'PD018', 55000.0000, N'SP008', 1)
INSERT [dbo].[CTPhieuDatTruoc] ([maphieudat], [thanhtien], [masanpham], [soluongsanpham]) VALUES (N'PD018', 24200.0000, N'SP010', 1)
INSERT [dbo].[CTPhieuDatTruoc] ([maphieudat], [thanhtien], [masanpham], [soluongsanpham]) VALUES (N'PD019', 59400.0000, N'SP004', 1)
INSERT [dbo].[CTPhieuDatTruoc] ([maphieudat], [thanhtien], [masanpham], [soluongsanpham]) VALUES (N'PD019', 22000.0000, N'SP005', 1)
INSERT [dbo].[CTPhieuDatTruoc] ([maphieudat], [thanhtien], [masanpham], [soluongsanpham]) VALUES (N'PD019', 24200.0000, N'SP009', 1)
INSERT [dbo].[CTPhieuDatTruoc] ([maphieudat], [thanhtien], [masanpham], [soluongsanpham]) VALUES (N'PD020', 91960.0000, N'SP001', 9)
INSERT [dbo].[CTPhieuDatTruoc] ([maphieudat], [thanhtien], [masanpham], [soluongsanpham]) VALUES (N'PD020', 66000.0000, N'SP002', 1)
INSERT [dbo].[CTPhieuDatTruoc] ([maphieudat], [thanhtien], [masanpham], [soluongsanpham]) VALUES (N'PD021', 60500.0000, N'SP003', 3)
INSERT [dbo].[CTPhieuDatTruoc] ([maphieudat], [thanhtien], [masanpham], [soluongsanpham]) VALUES (N'PD021', 49500.0000, N'SP006', 1)
INSERT [dbo].[CTPhieuDatTruoc] ([maphieudat], [thanhtien], [masanpham], [soluongsanpham]) VALUES (N'PD021', 49500.0000, N'SP007', 1)
INSERT [dbo].[CTPhieuDatTruoc] ([maphieudat], [thanhtien], [masanpham], [soluongsanpham]) VALUES (N'PD021', 55000.0000, N'SP008', 1)
INSERT [dbo].[CTPhieuDatTruoc] ([maphieudat], [thanhtien], [masanpham], [soluongsanpham]) VALUES (N'PD021', 24200.0000, N'SP009', 2)
INSERT [dbo].[CTPhieuDatTruoc] ([maphieudat], [thanhtien], [masanpham], [soluongsanpham]) VALUES (N'PD021', 24200.0000, N'SP012', 2)
INSERT [dbo].[CTPhieuDatTruoc] ([maphieudat], [thanhtien], [masanpham], [soluongsanpham]) VALUES (N'PD022', 49500.0000, N'SP006', 1)
INSERT [dbo].[CTPhieuDatTruoc] ([maphieudat], [thanhtien], [masanpham], [soluongsanpham]) VALUES (N'PD022', 24200.0000, N'SP010', 2)
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (275880.0000, 3, N'HD001', N'SP001')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (66000.0000, 1, N'HD001', N'SP002')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (297000.0000, 5, N'HD005', N'SP004')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (22550.0000, 5, N'HD008', N'SP040')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (356400.0000, 6, N'HD009', N'SP004')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (49500.0000, 1, N'HD009', N'SP006')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (49500.0000, 1, N'HD009', N'SP007')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (24200.0000, 1, N'HD009', N'SP010')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (24200.0000, 1, N'HD009', N'SP014')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (24200.0000, 1, N'HD009', N'SP021')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (18700.0000, 1, N'HD009', N'SP034')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (18700.0000, 1, N'HD009', N'SP035')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (1100000.0000, 1, N'HD009', N'SP039')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (24200.0000, 1, N'HD010', N'SP009')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (48400.0000, 2, N'HD011', N'SP011')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (99000.0000, 2, N'HD012', N'SP006')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (48400.0000, 2, N'HD013', N'SP013')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (60500.0000, 1, N'HD014', N'SP003')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (55000.0000, 1, N'HD014', N'SP008')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (24200.0000, 1, N'HD014', N'SP010')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (24200.0000, 1, N'HD014', N'SP018')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (24200.0000, 1, N'HD015', N'SP017')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (9020.0000, 2, N'HD015', N'SP040')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (9900.0000, 2, N'HD015', N'SP041')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (220000.0000, 4, N'HD016', N'SP008')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (66000.0000, 1, N'HD017', N'SP002')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (22000.0000, 1, N'HD017', N'SP005')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (99000.0000, 2, N'HD017', N'SP007')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (24200.0000, 1, N'HD017', N'SP010')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (24200.0000, 1, N'HD017', N'SP012')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (59400.0000, 1, N'HD019', N'SP004')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (49500.0000, 1, N'HD019', N'SP007')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (22000.0000, 1, N'HD020', N'SP005')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (49500.0000, 1, N'HD020', N'SP007')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (24200.0000, 1, N'HD020', N'SP010')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (24200.0000, 1, N'HD020', N'SP013')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (60500.0000, 1, N'HD022', N'SP003')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (49500.0000, 1, N'HD022', N'SP006')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (24200.0000, 1, N'HD022', N'SP010')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (66000.0000, 1, N'HD024', N'SP002')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (60500.0000, 1, N'HD024', N'SP003')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (49500.0000, 1, N'HD024', N'SP006')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (60500.0000, 1, N'HD025', N'SP003')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (49500.0000, 1, N'HD025', N'SP007')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (55000.0000, 1, N'HD025', N'SP008')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (60500.0000, 1, N'HD026', N'SP003')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (55000.0000, 1, N'HD026', N'SP008')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (24200.0000, 1, N'HD026', N'SP011')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (24200.0000, 1, N'HD026', N'SP012')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (24200.0000, 1, N'HD026', N'SP016')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (24200.0000, 1, N'HD026', N'SP018')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (49500.0000, 1, N'HD027', N'SP006')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (49500.0000, 1, N'HD027', N'SP007')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (24200.0000, 1, N'HD027', N'SP009')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (24200.0000, 1, N'HD027', N'SP010')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (24200.0000, 1, N'HD027', N'SP015')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (24200.0000, 1, N'HD027', N'SP017')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (24200.0000, 1, N'HD027', N'SP019')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (24200.0000, 1, N'HD027', N'SP021')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (25300.0000, 1, N'HD027', N'SP025')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (22000.0000, 1, N'HD028', N'SP005')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (49500.0000, 1, N'HD028', N'SP006')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (24200.0000, 1, N'HD028', N'SP009')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (24200.0000, 1, N'HD028', N'SP015')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (91960.0000, 1, N'HD029', N'SP001')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (59400.0000, 1, N'HD029', N'SP004')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (24200.0000, 1, N'HD029', N'SP009')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (24200.0000, 1, N'HD029', N'SP010')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (24200.0000, 1, N'HD029', N'SP012')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (24200.0000, 1, N'HD029', N'SP016')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (24200.0000, 1, N'HD029', N'SP023')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (43120.0000, 4, N'HD029', N'SP045')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (49500.0000, 1, N'HD030', N'SP007')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (25300.0000, 1, N'HD030', N'SP025')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (101200.0000, 4, N'HD030', N'SP027')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (18700.0000, 1, N'HD030', N'SP033')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (18700.0000, 1, N'HD030', N'SP036')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (22550.0000, 5, N'HD030', N'SP040')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (24200.0000, 1, N'HD031', N'SP009')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (24200.0000, 1, N'HD032', N'SP013')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (183920.0000, 2, N'HD033', N'SP001')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (25300.0000, 1, N'HD034', N'SP030')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (110000.0000, 1, N'HD034', N'SP038')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (18700.0000, 1, N'HD035', N'SP036')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (66000.0000, 1, N'HD036', N'SP002')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (55000.0000, 1, N'HD036', N'SP008')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (24200.0000, 1, N'HD036', N'SP013')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (24200.0000, 1, N'HD037', N'SP013')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (60500.0000, 1, N'HD038', N'SP003')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (59400.0000, 1, N'HD038', N'SP004')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (49500.0000, 2, N'HD038', N'SP007')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (91960.0000, 1, N'HD039', N'SP001')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (59400.0000, 1, N'HD039', N'SP004')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (22000.0000, 1, N'HD040', N'SP005')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (49500.0000, 1, N'HD040', N'SP007')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (24200.0000, 1, N'HD041', N'SP014')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (24200.0000, 1, N'HD042', N'SP010')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (24200.0000, 1, N'HD042', N'SP015')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (59400.0000, 1, N'HD043', N'SP004')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (24200.0000, 1, N'HD043', N'SP012')
GO
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (14850.0000, 1, N'HD043', N'SP046')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (49500.0000, 1, N'HD044', N'SP007')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (24200.0000, 1, N'HD045', N'SP009')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (59400.0000, 1, N'HD046', N'SP004')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (24200.0000, 1, N'HD046', N'SP010')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (72600.0000, 3, N'HD046', N'SP015')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (49500.0000, 1, N'HD047', N'SP006')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (24200.0000, 1, N'HD047', N'SP010')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (22000.0000, 1, N'HD048', N'SP005')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (24200.0000, 1, N'HD048', N'SP009')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (60500.0000, 1, N'HD049', N'SP003')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (49500.0000, 1, N'HD049', N'SP006')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (55000.0000, 1, N'HD049', N'SP008')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (24200.0000, 1, N'HD049', N'SP010')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (49500.0000, 1, N'HD050', N'SP006')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (24200.0000, 1, N'HD050', N'SP011')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (59400.0000, 1, N'HD051', N'SP004')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (55000.0000, 1, N'HD051', N'SP008')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (91960.0000, 1, N'HD052', N'SP001')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (49500.0000, 1, N'HD052', N'SP007')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (1100000.0000, 1, N'HD052', N'SP039')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (22000.0000, 1, N'HD053', N'SP005')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (66000.0000, 1, N'HD054', N'SP002')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (49500.0000, 1, N'HD054', N'SP007')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (24200.0000, 1, N'HD054', N'SP010')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (66000.0000, 1, N'HD055', N'SP002')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (59400.0000, 1, N'HD055', N'SP004')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (24200.0000, 1, N'HD055', N'SP011')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (24200.0000, 1, N'HD055', N'SP012')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (24200.0000, 1, N'HD056', N'SP009')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (18700.0000, 1, N'HD056', N'SP033')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (60500.0000, 1, N'HD057', N'SP003')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (49500.0000, 1, N'HD057', N'SP007')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (4510.0000, 1, N'HD057', N'SP040')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (14850.0000, 1, N'HD057', N'SP047')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (18700.0000, 1, N'HD058', N'SP036')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (4510.0000, 1, N'HD058', N'SP040')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (10780.0000, 1, N'HD058', N'SP045')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (14850.0000, 1, N'HD058', N'SP046')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (49500.0000, 1, N'HD059', N'SP006')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (24200.0000, 1, N'HD059', N'SP011')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (91960.0000, 1, N'HD060', N'SP001')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (60500.0000, 1, N'HD061', N'SP003')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (55000.0000, 1, N'HD062', N'SP008')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (60500.0000, 1, N'HD063', N'SP003')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (24200.0000, 1, N'HD063', N'SP009')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (59400.0000, 1, N'HD064', N'SP004')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (66000.0000, 1, N'HD065', N'SP002')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (55000.0000, 1, N'HD065', N'SP008')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (24200.0000, 1, N'HD065', N'SP014')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (59400.0000, 1, N'HD066', N'SP004')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (24200.0000, 1, N'HD066', N'SP010')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (59400.0000, 1, N'HD067', N'SP004')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (59400.0000, 1, N'HD068', N'SP004')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (49500.0000, 1, N'HD068', N'SP007')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (22000.0000, 1, N'HD069', N'SP005')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (22000.0000, 1, N'HD071', N'SP005')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (24200.0000, 1, N'HD071', N'SP010')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (66000.0000, 1, N'HD072', N'SP002')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (24200.0000, 1, N'HD072', N'SP009')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (66000.0000, 1, N'HD074', N'SP002')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (55000.0000, 1, N'HD075', N'SP008')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (24200.0000, 1, N'HD075', N'SP012')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (49500.0000, 1, N'HD076', N'SP006')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (49500.0000, 1, N'HD076', N'SP007')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (55000.0000, 2, N'HD076', N'SP008')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (66000.0000, 1, N'HD077', N'SP002')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (59400.0000, 1, N'HD077', N'SP004')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (55000.0000, 1, N'HD077', N'SP008')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (24200.0000, 1, N'HD077', N'SP009')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (91960.0000, 1, N'HD078', N'SP001')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (24200.0000, 1, N'HD078', N'SP010')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (60500.0000, 2, N'HD079', N'SP003')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (22000.0000, 2, N'HD079', N'SP005')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (55000.0000, 1, N'HD079', N'SP008')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (24200.0000, 1, N'HD079', N'SP010')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (24200.0000, 1, N'HD079', N'SP011')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (55000.0000, 1, N'HD080', N'SP008')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (121000.0000, 5, N'HD080', N'SP012')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (247500.0000, 5, N'HD081', N'SP007')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (121000.0000, 5, N'HD081', N'SP011')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (121000.0000, 5, N'HD081', N'SP012')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (66000.0000, 2, N'HD082', N'SP002')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (24200.0000, 1, N'HD083', N'SP013')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (25300.0000, 1, N'HD084', N'SP032')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (18700.0000, 1, N'HD084', N'SP033')
INSERT [dbo].[CTHoaDon] ([thanhtien], [soluongsanpham], [mahoadon], [masanpham]) VALUES (50600.0000, 2, N'HD085', N'SP026')
INSERT [dbo].[HoaDon] ([mahoadon], [tongtienhoadon], [ngaylaphoadon], [manhanvien], [makhachhang]) VALUES (N'HD001', 2508000.0000, CAST(N'2020-01-01 08:00:06.670' AS DateTime), N'NV003', NULL)
INSERT [dbo].[HoaDon] ([mahoadon], [tongtienhoadon], [ngaylaphoadon], [manhanvien], [makhachhang]) VALUES (N'HD005', 297000.0000, CAST(N'2020-01-02 13:08:06.673' AS DateTime), N'NV002', N'KH021')
INSERT [dbo].[HoaDon] ([mahoadon], [tongtienhoadon], [ngaylaphoadon], [manhanvien], [makhachhang]) VALUES (N'HD008', 18040.0000, CAST(N'2020-01-15 20:07:06.677' AS DateTime), N'NV001', NULL)
INSERT [dbo].[HoaDon] ([mahoadon], [tongtienhoadon], [ngaylaphoadon], [manhanvien], [makhachhang]) VALUES (N'HD009', 1546600.0000, CAST(N'2020-01-01 17:10:06.680' AS DateTime), N'NV002', NULL)
INSERT [dbo].[HoaDon] ([mahoadon], [tongtienhoadon], [ngaylaphoadon], [manhanvien], [makhachhang]) VALUES (N'HD010', 24200.0000, CAST(N'2020-02-15 08:09:06.683' AS DateTime), N'NV002', NULL)
INSERT [dbo].[HoaDon] ([mahoadon], [tongtienhoadon], [ngaylaphoadon], [manhanvien], [makhachhang]) VALUES (N'HD011', 48400.0000, CAST(N'2020-02-16 10:00:06.687' AS DateTime), N'NV003', N'KH021')
INSERT [dbo].[HoaDon] ([mahoadon], [tongtienhoadon], [ngaylaphoadon], [manhanvien], [makhachhang]) VALUES (N'HD012', 99000.0000, CAST(N'2020-03-19 07:03:06.690' AS DateTime), N'NV003', NULL)
INSERT [dbo].[HoaDon] ([mahoadon], [tongtienhoadon], [ngaylaphoadon], [manhanvien], [makhachhang]) VALUES (N'HD013', 48400.0000, CAST(N'2020-03-20 10:00:06.693' AS DateTime), N'NV002', NULL)
INSERT [dbo].[HoaDon] ([mahoadon], [tongtienhoadon], [ngaylaphoadon], [manhanvien], [makhachhang]) VALUES (N'HD014', 163900.0000, CAST(N'2020-11-21 13:00:06.697' AS DateTime), N'NV001', N'KH021')
INSERT [dbo].[HoaDon] ([mahoadon], [tongtienhoadon], [ngaylaphoadon], [manhanvien], [makhachhang]) VALUES (N'HD015', 207020.0000, CAST(N'2021-01-01 07:59:06.700' AS DateTime), N'NV002', N'KH022')
INSERT [dbo].[HoaDon] ([mahoadon], [tongtienhoadon], [ngaylaphoadon], [manhanvien], [makhachhang]) VALUES (N'HD016', 220000.0000, CAST(N'2021-01-15 08:20:06.703' AS DateTime), N'NV003', NULL)
INSERT [dbo].[HoaDon] ([mahoadon], [tongtienhoadon], [ngaylaphoadon], [manhanvien], [makhachhang]) VALUES (N'HD017', 185900.0000, CAST(N'2021-01-16 09:31:06.707' AS DateTime), N'NV002', NULL)
INSERT [dbo].[HoaDon] ([mahoadon], [tongtienhoadon], [ngaylaphoadon], [manhanvien], [makhachhang]) VALUES (N'HD018', 185900.0000, CAST(N'2021-02-17 12:10:06.710' AS DateTime), N'NV002', NULL)
INSERT [dbo].[HoaDon] ([mahoadon], [tongtienhoadon], [ngaylaphoadon], [manhanvien], [makhachhang]) VALUES (N'HD019', 108900.0000, CAST(N'2021-05-01 10:00:06.713' AS DateTime), N'NV001', NULL)
INSERT [dbo].[HoaDon] ([mahoadon], [tongtienhoadon], [ngaylaphoadon], [manhanvien], [makhachhang]) VALUES (N'HD020', 119900.0000, CAST(N'2021-06-17 14:22:06.717' AS DateTime), N'NV002', NULL)
INSERT [dbo].[HoaDon] ([mahoadon], [tongtienhoadon], [ngaylaphoadon], [manhanvien], [makhachhang]) VALUES (N'HD022', 134200.0000, CAST(N'2021-12-01 15:46:36.190' AS DateTime), N'NV001', N'KH004')
INSERT [dbo].[HoaDon] ([mahoadon], [tongtienhoadon], [ngaylaphoadon], [manhanvien], [makhachhang]) VALUES (N'HD024', 176000.0000, CAST(N'2021-12-01 15:46:19.280' AS DateTime), N'NV001', N'KH020')
INSERT [dbo].[HoaDon] ([mahoadon], [tongtienhoadon], [ngaylaphoadon], [manhanvien], [makhachhang]) VALUES (N'HD025', 165000.0000, CAST(N'2021-11-30 15:01:30.590' AS DateTime), N'NV001', N'KH009')
INSERT [dbo].[HoaDon] ([mahoadon], [tongtienhoadon], [ngaylaphoadon], [manhanvien], [makhachhang]) VALUES (N'HD026', 139700.0000, CAST(N'2021-11-30 16:10:00.033' AS DateTime), N'NV002', N'KH017')
INSERT [dbo].[HoaDon] ([mahoadon], [tongtienhoadon], [ngaylaphoadon], [manhanvien], [makhachhang]) VALUES (N'HD027', 147400.0000, CAST(N'2021-11-30 17:59:21.550' AS DateTime), N'NV001', N'KH008')
INSERT [dbo].[HoaDon] ([mahoadon], [tongtienhoadon], [ngaylaphoadon], [manhanvien], [makhachhang]) VALUES (N'HD028', 119900.0000, CAST(N'2021-12-01 17:35:14.337' AS DateTime), N'NV001', N'KH002')
INSERT [dbo].[HoaDon] ([mahoadon], [tongtienhoadon], [ngaylaphoadon], [manhanvien], [makhachhang]) VALUES (N'HD029', 315480.0000, CAST(N'2021-12-02 18:22:07.000' AS DateTime), N'NV001', N'KH009')
INSERT [dbo].[HoaDon] ([mahoadon], [tongtienhoadon], [ngaylaphoadon], [manhanvien], [makhachhang]) VALUES (N'HD030', 235950.0000, CAST(N'2021-12-02 19:35:01.013' AS DateTime), N'NV001', N'KH017')
INSERT [dbo].[HoaDon] ([mahoadon], [tongtienhoadon], [ngaylaphoadon], [manhanvien], [makhachhang]) VALUES (N'HD031', 25410.0000, CAST(N'2021-12-10 17:03:40.170' AS DateTime), N'NV001', NULL)
INSERT [dbo].[HoaDon] ([mahoadon], [tongtienhoadon], [ngaylaphoadon], [manhanvien], [makhachhang]) VALUES (N'HD032', 25410.0000, CAST(N'2021-12-10 17:43:32.427' AS DateTime), N'NV001', NULL)
INSERT [dbo].[HoaDon] ([mahoadon], [tongtienhoadon], [ngaylaphoadon], [manhanvien], [makhachhang]) VALUES (N'HD033', 193116.0000, CAST(N'2021-12-10 19:07:18.167' AS DateTime), N'NV003', NULL)
INSERT [dbo].[HoaDon] ([mahoadon], [tongtienhoadon], [ngaylaphoadon], [manhanvien], [makhachhang]) VALUES (N'HD034', 138682.5000, CAST(N'2021-12-10 19:21:54.247' AS DateTime), N'NV001', N'KH004')
INSERT [dbo].[HoaDon] ([mahoadon], [tongtienhoadon], [ngaylaphoadon], [manhanvien], [makhachhang]) VALUES (N'HD035', 19167.5000, CAST(N'2021-12-10 19:27:42.460' AS DateTime), N'NV005', N'KH002')
INSERT [dbo].[HoaDon] ([mahoadon], [tongtienhoadon], [ngaylaphoadon], [manhanvien], [makhachhang]) VALUES (N'HD036', 148830.0000, CAST(N'2021-12-10 19:40:19.753' AS DateTime), N'NV005', N'KH004')
INSERT [dbo].[HoaDon] ([mahoadon], [tongtienhoadon], [ngaylaphoadon], [manhanvien], [makhachhang]) VALUES (N'HD037', 24805.0000, CAST(N'2021-12-10 19:54:30.543' AS DateTime), N'NV003', N'KH014')
INSERT [dbo].[HoaDon] ([mahoadon], [tongtienhoadon], [ngaylaphoadon], [manhanvien], [makhachhang]) VALUES (N'HD038', 224372.5000, CAST(N'2021-12-10 19:01:01.507' AS DateTime), N'NV001', N'KH022')
INSERT [dbo].[HoaDon] ([mahoadon], [tongtienhoadon], [ngaylaphoadon], [manhanvien], [makhachhang]) VALUES (N'HD039', 158928.0000, CAST(N'2021-12-12 11:01:11.453' AS DateTime), N'NV001', NULL)
INSERT [dbo].[HoaDon] ([mahoadon], [tongtienhoadon], [ngaylaphoadon], [manhanvien], [makhachhang]) VALUES (N'HD040', 75075.0000, CAST(N'2021-12-12 11:20:48.443' AS DateTime), N'NV001', NULL)
INSERT [dbo].[HoaDon] ([mahoadon], [tongtienhoadon], [ngaylaphoadon], [manhanvien], [makhachhang]) VALUES (N'HD041', 23595.0000, CAST(N'2021-12-12 11:25:44.667' AS DateTime), N'NV001', NULL)
INSERT [dbo].[HoaDon] ([mahoadon], [tongtienhoadon], [ngaylaphoadon], [manhanvien], [makhachhang]) VALUES (N'HD042', 50820.0000, CAST(N'2021-12-12 11:27:15.107' AS DateTime), N'NV001', NULL)
INSERT [dbo].[HoaDon] ([mahoadon], [tongtienhoadon], [ngaylaphoadon], [manhanvien], [makhachhang]) VALUES (N'HD043', 103372.0000, CAST(N'2021-12-12 11:44:39.407' AS DateTime), N'NV002', NULL)
INSERT [dbo].[HoaDon] ([mahoadon], [tongtienhoadon], [ngaylaphoadon], [manhanvien], [makhachhang]) VALUES (N'HD044', 51975.0000, CAST(N'2021-12-12 11:58:24.237' AS DateTime), N'NV001', NULL)
INSERT [dbo].[HoaDon] ([mahoadon], [tongtienhoadon], [ngaylaphoadon], [manhanvien], [makhachhang]) VALUES (N'HD045', 25410.0000, CAST(N'2021-12-12 12:02:14.380' AS DateTime), N'NV001', NULL)
INSERT [dbo].[HoaDon] ([mahoadon], [tongtienhoadon], [ngaylaphoadon], [manhanvien], [makhachhang]) VALUES (N'HD046', 164010.0000, CAST(N'2021-12-12 12:03:34.473' AS DateTime), N'NV003', NULL)
INSERT [dbo].[HoaDon] ([mahoadon], [tongtienhoadon], [ngaylaphoadon], [manhanvien], [makhachhang]) VALUES (N'HD047', 77385.0000, CAST(N'2021-12-12 12:05:04.803' AS DateTime), N'NV003', NULL)
INSERT [dbo].[HoaDon] ([mahoadon], [tongtienhoadon], [ngaylaphoadon], [manhanvien], [makhachhang]) VALUES (N'HD048', 48510.0000, CAST(N'2021-12-12 12:19:24.040' AS DateTime), N'NV001', N'KH021')
INSERT [dbo].[HoaDon] ([mahoadon], [tongtienhoadon], [ngaylaphoadon], [manhanvien], [makhachhang]) VALUES (N'HD049', 198660.0000, CAST(N'2021-12-12 12:29:48.213' AS DateTime), N'NV001', N'KH021')
INSERT [dbo].[HoaDon] ([mahoadon], [tongtienhoadon], [ngaylaphoadon], [manhanvien], [makhachhang]) VALUES (N'HD050', 77385.0000, CAST(N'2021-12-12 12:33:36.517' AS DateTime), N'NV003', N'KH021')
INSERT [dbo].[HoaDon] ([mahoadon], [tongtienhoadon], [ngaylaphoadon], [manhanvien], [makhachhang]) VALUES (N'HD051', 120120.0000, CAST(N'2021-12-12 12:45:54.707' AS DateTime), N'NV005', NULL)
INSERT [dbo].[HoaDon] ([mahoadon], [tongtienhoadon], [ngaylaphoadon], [manhanvien], [makhachhang]) VALUES (N'HD052', 1303533.0000, CAST(N'2021-12-12 12:46:46.403' AS DateTime), N'NV003', NULL)
INSERT [dbo].[HoaDon] ([mahoadon], [tongtienhoadon], [ngaylaphoadon], [manhanvien], [makhachhang]) VALUES (N'HD053', 23100.0000, CAST(N'2021-12-12 12:50:13.473' AS DateTime), N'NV001', NULL)
INSERT [dbo].[HoaDon] ([mahoadon], [tongtienhoadon], [ngaylaphoadon], [manhanvien], [makhachhang]) VALUES (N'HD054', 146685.0000, CAST(N'2021-12-12 12:51:42.193' AS DateTime), N'NV006', N'KH001')
INSERT [dbo].[HoaDon] ([mahoadon], [tongtienhoadon], [ngaylaphoadon], [manhanvien], [makhachhang]) VALUES (N'HD055', 182490.0000, CAST(N'2021-12-12 12:52:18.667' AS DateTime), N'NV006', NULL)
INSERT [dbo].[HoaDon] ([mahoadon], [tongtienhoadon], [ngaylaphoadon], [manhanvien], [makhachhang]) VALUES (N'HD056', 45045.0000, CAST(N'2021-12-12 12:53:42.897' AS DateTime), N'NV006', NULL)
INSERT [dbo].[HoaDon] ([mahoadon], [tongtienhoadon], [ngaylaphoadon], [manhanvien], [makhachhang]) VALUES (N'HD057', 126126.0000, CAST(N'2021-12-21 15:38:02.490' AS DateTime), N'NV003', N'KH021')
INSERT [dbo].[HoaDon] ([mahoadon], [tongtienhoadon], [ngaylaphoadon], [manhanvien], [makhachhang]) VALUES (N'HD058', 51282.0000, CAST(N'2021-12-21 15:41:18.890' AS DateTime), N'NV003', N'KH021')
INSERT [dbo].[HoaDon] ([mahoadon], [tongtienhoadon], [ngaylaphoadon], [manhanvien], [makhachhang]) VALUES (N'HD059', 77385.0000, CAST(N'2021-12-21 16:00:16.237' AS DateTime), N'NV001', NULL)
INSERT [dbo].[HoaDon] ([mahoadon], [tongtienhoadon], [ngaylaphoadon], [manhanvien], [makhachhang]) VALUES (N'HD060', 96558.0000, CAST(N'2021-12-21 16:05:32.757' AS DateTime), N'NV001', NULL)
INSERT [dbo].[HoaDon] ([mahoadon], [tongtienhoadon], [ngaylaphoadon], [manhanvien], [makhachhang]) VALUES (N'HD061', 63525.0000, CAST(N'2021-12-21 16:07:36.060' AS DateTime), N'NV001', NULL)
INSERT [dbo].[HoaDon] ([mahoadon], [tongtienhoadon], [ngaylaphoadon], [manhanvien], [makhachhang]) VALUES (N'HD062', 57750.0000, CAST(N'2021-12-21 16:57:20.950' AS DateTime), N'NV001', NULL)
INSERT [dbo].[HoaDon] ([mahoadon], [tongtienhoadon], [ngaylaphoadon], [manhanvien], [makhachhang]) VALUES (N'HD063', 88935.0000, CAST(N'2021-12-21 16:58:55.503' AS DateTime), N'NV002', NULL)
INSERT [dbo].[HoaDon] ([mahoadon], [tongtienhoadon], [ngaylaphoadon], [manhanvien], [makhachhang]) VALUES (N'HD064', 62370.0000, CAST(N'2021-12-21 17:00:33.803' AS DateTime), N'NV001', NULL)
INSERT [dbo].[HoaDon] ([mahoadon], [tongtienhoadon], [ngaylaphoadon], [manhanvien], [makhachhang]) VALUES (N'HD065', 152460.0000, CAST(N'2021-12-21 17:08:32.763' AS DateTime), N'NV001', NULL)
INSERT [dbo].[HoaDon] ([mahoadon], [tongtienhoadon], [ngaylaphoadon], [manhanvien], [makhachhang]) VALUES (N'HD066', 87780.0000, CAST(N'2021-12-21 20:08:56.623' AS DateTime), N'NV001', NULL)
INSERT [dbo].[HoaDon] ([mahoadon], [tongtienhoadon], [ngaylaphoadon], [manhanvien], [makhachhang]) VALUES (N'HD067', 62370.0000, CAST(N'2021-12-21 21:19:43.537' AS DateTime), N'NV001', NULL)
INSERT [dbo].[HoaDon] ([mahoadon], [tongtienhoadon], [ngaylaphoadon], [manhanvien], [makhachhang]) VALUES (N'HD068', 114345.0000, CAST(N'2021-12-21 21:23:58.160' AS DateTime), N'NV001', NULL)
INSERT [dbo].[HoaDon] ([mahoadon], [tongtienhoadon], [ngaylaphoadon], [manhanvien], [makhachhang]) VALUES (N'HD069', 23100.0000, CAST(N'2021-12-21 23:02:46.713' AS DateTime), N'NV001', NULL)
INSERT [dbo].[HoaDon] ([mahoadon], [tongtienhoadon], [ngaylaphoadon], [manhanvien], [makhachhang]) VALUES (N'HD071', 71610.0000, CAST(N'2021-12-21 23:04:37.443' AS DateTime), N'NV001', NULL)
INSERT [dbo].[HoaDon] ([mahoadon], [tongtienhoadon], [ngaylaphoadon], [manhanvien], [makhachhang]) VALUES (N'HD072', 94710.0000, CAST(N'2021-12-21 23:20:46.790' AS DateTime), N'NV001', NULL)
INSERT [dbo].[HoaDon] ([mahoadon], [tongtienhoadon], [ngaylaphoadon], [manhanvien], [makhachhang]) VALUES (N'HD074', 64350.0000, CAST(N'2021-12-21 23:27:58.797' AS DateTime), N'NV001', N'KH021')
INSERT [dbo].[HoaDon] ([mahoadon], [tongtienhoadon], [ngaylaphoadon], [manhanvien], [makhachhang]) VALUES (N'HD075', 77220.0000, CAST(N'2021-12-22 15:43:09.770' AS DateTime), N'NV005', N'KH021')
INSERT [dbo].[HoaDon] ([mahoadon], [tongtienhoadon], [ngaylaphoadon], [manhanvien], [makhachhang]) VALUES (N'HD076', 219450.0000, CAST(N'2021-12-22 16:54:46.417' AS DateTime), N'NV001', NULL)
INSERT [dbo].[HoaDon] ([mahoadon], [tongtienhoadon], [ngaylaphoadon], [manhanvien], [makhachhang]) VALUES (N'HD077', 214830.0000, CAST(N'2021-12-22 17:03:03.407' AS DateTime), N'NV001', NULL)
INSERT [dbo].[HoaDon] ([mahoadon], [tongtienhoadon], [ngaylaphoadon], [manhanvien], [makhachhang]) VALUES (N'HD078', 121968.0000, CAST(N'2021-12-22 17:06:09.910' AS DateTime), N'NV005', NULL)
INSERT [dbo].[HoaDon] ([mahoadon], [tongtienhoadon], [ngaylaphoadon], [manhanvien], [makhachhang]) VALUES (N'HD079', 275110.0000, CAST(N'2021-12-22 20:28:17.080' AS DateTime), N'NV001', N'KH004')
INSERT [dbo].[HoaDon] ([mahoadon], [tongtienhoadon], [ngaylaphoadon], [manhanvien], [makhachhang]) VALUES (N'HD080', 184800.0000, CAST(N'2021-12-23 10:31:45.433' AS DateTime), N'NV001', NULL)
INSERT [dbo].[HoaDon] ([mahoadon], [tongtienhoadon], [ngaylaphoadon], [manhanvien], [makhachhang]) VALUES (N'HD081', 513975.0000, CAST(N'2021-12-23 10:33:26.970' AS DateTime), N'NV001', NULL)
INSERT [dbo].[HoaDon] ([mahoadon], [tongtienhoadon], [ngaylaphoadon], [manhanvien], [makhachhang]) VALUES (N'HD082', 138600.0000, CAST(N'2021-12-24 11:30:13.713' AS DateTime), N'NV002', NULL)
INSERT [dbo].[HoaDon] ([mahoadon], [tongtienhoadon], [ngaylaphoadon], [manhanvien], [makhachhang]) VALUES (N'HD083', 25410.0000, CAST(N'2021-12-25 10:51:55.093' AS DateTime), N'NV002', N'KH021')
INSERT [dbo].[HoaDon] ([mahoadon], [tongtienhoadon], [ngaylaphoadon], [manhanvien], [makhachhang]) VALUES (N'HD084', 45100.0000, CAST(N'2021-12-25 10:57:04.613' AS DateTime), N'NV002', N'KH021')
INSERT [dbo].[HoaDon] ([mahoadon], [tongtienhoadon], [ngaylaphoadon], [manhanvien], [makhachhang]) VALUES (N'HD085', 53130.0000, CAST(N'2021-12-25 10:58:42.893' AS DateTime), N'NV002', NULL)
INSERT [dbo].[KhachHang] ([makhachhang], [tenkhachhang], [sodienthoai]) VALUES (N'KH001', N'Giang Duy Thạch', N'0312751890')
INSERT [dbo].[KhachHang] ([makhachhang], [tenkhachhang], [sodienthoai]) VALUES (N'KH002', N'Phạm Tùng Linh', N'0335751891')
INSERT [dbo].[KhachHang] ([makhachhang], [tenkhachhang], [sodienthoai]) VALUES (N'KH003', N'Nguyễn Thái Dương', N'0312351892')
INSERT [dbo].[KhachHang] ([makhachhang], [tenkhachhang], [sodienthoai]) VALUES (N'KH004', N'Nguyễn Hồng Phát', N'0335456893')
INSERT [dbo].[KhachHang] ([makhachhang], [tenkhachhang], [sodienthoai]) VALUES (N'KH005', N'Thảo Hoài Trung', N'0333751894')
INSERT [dbo].[KhachHang] ([makhachhang], [tenkhachhang], [sodienthoai]) VALUES (N'KH006', N'Lưu Quang Đông', N'0333751895')
INSERT [dbo].[KhachHang] ([makhachhang], [tenkhachhang], [sodienthoai]) VALUES (N'KH007', N'Giang Thành Nguyên', N'0336687996')
INSERT [dbo].[KhachHang] ([makhachhang], [tenkhachhang], [sodienthoai]) VALUES (N'KH008', N'Hàn Ngọc Ngạn', N'0334456897')
INSERT [dbo].[KhachHang] ([makhachhang], [tenkhachhang], [sodienthoai]) VALUES (N'KH009', N'Ngô An Nam', N'0333751898')
INSERT [dbo].[KhachHang] ([makhachhang], [tenkhachhang], [sodienthoai]) VALUES (N'KH010', N'Phan Việt Khang', N'0333751899')
INSERT [dbo].[KhachHang] ([makhachhang], [tenkhachhang], [sodienthoai]) VALUES (N'KH011', N'Thủy Xuân Hòa ', N'0333111900')
INSERT [dbo].[KhachHang] ([makhachhang], [tenkhachhang], [sodienthoai]) VALUES (N'KH012', N'Quách Minh Dân', N'0333751901')
INSERT [dbo].[KhachHang] ([makhachhang], [tenkhachhang], [sodienthoai]) VALUES (N'KH013', N'Lâm Quang Ninh', N'0333751902')
INSERT [dbo].[KhachHang] ([makhachhang], [tenkhachhang], [sodienthoai]) VALUES (N'KH014', N'Ngô Bình Minh', N'0333445903')
INSERT [dbo].[KhachHang] ([makhachhang], [tenkhachhang], [sodienthoai]) VALUES (N'KH015', N'Huỳnh Minh Lý', N'0333745604')
INSERT [dbo].[KhachHang] ([makhachhang], [tenkhachhang], [sodienthoai]) VALUES (N'KH016', N'Bùi Quang Ninh', N'0363751905')
INSERT [dbo].[KhachHang] ([makhachhang], [tenkhachhang], [sodienthoai]) VALUES (N'KH017', N'Võ Trung Dũng', N'0334451906')
INSERT [dbo].[KhachHang] ([makhachhang], [tenkhachhang], [sodienthoai]) VALUES (N'KH018', N'Nguyễn Xuân Trường', N'0333771907')
INSERT [dbo].[KhachHang] ([makhachhang], [tenkhachhang], [sodienthoai]) VALUES (N'KH019', N'Mai Sơn Tùng', N'0333454608')
INSERT [dbo].[KhachHang] ([makhachhang], [tenkhachhang], [sodienthoai]) VALUES (N'KH020', N'Nguyễn Hữu Minh', N'0333751909')
INSERT [dbo].[KhachHang] ([makhachhang], [tenkhachhang], [sodienthoai]) VALUES (N'KH021', N'Võ Phước Lưu ', N'0333751890')
INSERT [dbo].[KhachHang] ([makhachhang], [tenkhachhang], [sodienthoai]) VALUES (N'KH022', N'Nguyễn Thị Lan Anh', N'0337418523')
INSERT [dbo].[KhachHang] ([makhachhang], [tenkhachhang], [sodienthoai]) VALUES (N'KH023', N'Võ Minh Đang ', N'0378451795')
INSERT [dbo].[LoaiSanPham] ([tenloai], [maloai]) VALUES (N'Sách', N'SACH')
INSERT [dbo].[LoaiSanPham] ([tenloai], [maloai]) VALUES (N'Văn Phòng Phẩm', N'VANP')
INSERT [dbo].[NhanVien] ([manhanvien], [hoten], [ngaysinh], [diachi], [sochungminh], [trangthailamviec], [gioitinh], [ca], [maquanly], [sodienthoai]) VALUES (N'NV001', N'Nguyễn Phạm Công Nhật', CAST(N'2000-12-22' AS Date), N'Đá Hàng', N'072201005594', 1, 1, 0, NULL, N'0332457899')
INSERT [dbo].[NhanVien] ([manhanvien], [hoten], [ngaysinh], [diachi], [sochungminh], [trangthailamviec], [gioitinh], [ca], [maquanly], [sodienthoai]) VALUES (N'NV002', N'Võ Phước Lưu', CAST(N'2001-02-07' AS Date), N'Thạnh Đức', N'072201002902', 1, 1, 1, N'NV001', N'0333751890')
INSERT [dbo].[NhanVien] ([manhanvien], [hoten], [ngaysinh], [diachi], [sochungminh], [trangthailamviec], [gioitinh], [ca], [maquanly], [sodienthoai]) VALUES (N'NV003', N'Phan Võ Trọng', CAST(N'2001-09-09' AS Date), N'Long An', N'072265467898', 1, 1, 2, N'NV001', N'0396546878')
INSERT [dbo].[NhanVien] ([manhanvien], [hoten], [ngaysinh], [diachi], [sochungminh], [trangthailamviec], [gioitinh], [ca], [maquanly], [sodienthoai]) VALUES (N'NV005', N'Lương Thị Liễu', CAST(N'2003-07-07' AS Date), N'Tây Ninh', N'072265768799', 1, 0, 1, N'NV001', N'0334567879')
INSERT [dbo].[NhanVien] ([manhanvien], [hoten], [ngaysinh], [diachi], [sochungminh], [trangthailamviec], [gioitinh], [ca], [maquanly], [sodienthoai]) VALUES (N'NV006', N'Trần Minh Hiếu', CAST(N'1999-08-22' AS Date), N'Phú Yên', N'072456789412', 1, 1, 1, N'NV001', N'0337845987')
INSERT [dbo].[PhieuDatTruoc] ([maphieudat], [ngaylap], [ngaynhanhang], [tongtienphieudat], [manhanvien], [makhachhang], [trangthai]) VALUES (N'PD001', CAST(N'2021-11-28 00:00:00.000' AS DateTime), CAST(N'2021-01-01 00:00:06.720' AS DateTime), 59400.0000, N'NV001', N'KH003', N'Quá hạn')
INSERT [dbo].[PhieuDatTruoc] ([maphieudat], [ngaylap], [ngaynhanhang], [tongtienphieudat], [manhanvien], [makhachhang], [trangthai]) VALUES (N'PD002', CAST(N'2021-11-28 00:00:00.000' AS DateTime), CAST(N'2021-01-01 00:00:06.723' AS DateTime), 891000.0000, N'NV001', N'KH003', N'Quá hạn')
INSERT [dbo].[PhieuDatTruoc] ([maphieudat], [ngaylap], [ngaynhanhang], [tongtienphieudat], [manhanvien], [makhachhang], [trangthai]) VALUES (N'PD003', CAST(N'2021-11-28 00:00:00.000' AS DateTime), CAST(N'2021-02-01 00:00:06.727' AS DateTime), 957220.0000, N'NV001', N'KH003', N'Quá hạn')
INSERT [dbo].[PhieuDatTruoc] ([maphieudat], [ngaylap], [ngaynhanhang], [tongtienphieudat], [manhanvien], [makhachhang], [trangthai]) VALUES (N'PD004', CAST(N'2021-11-28 00:00:00.000' AS DateTime), CAST(N'2021-01-01 00:00:06.730' AS DateTime), 178200.0000, N'NV001', N'KH005', N'Quá hạn')
INSERT [dbo].[PhieuDatTruoc] ([maphieudat], [ngaylap], [ngaynhanhang], [tongtienphieudat], [manhanvien], [makhachhang], [trangthai]) VALUES (N'PD005', CAST(N'2021-11-28 00:00:00.000' AS DateTime), CAST(N'2021-03-01 00:00:06.733' AS DateTime), 1058200.0000, N'NV001', N'KH005', N'Quá hạn')
INSERT [dbo].[PhieuDatTruoc] ([maphieudat], [ngaylap], [ngaynhanhang], [tongtienphieudat], [manhanvien], [makhachhang], [trangthai]) VALUES (N'PD006', CAST(N'2021-11-28 00:00:00.000' AS DateTime), CAST(N'2021-05-01 00:00:06.737' AS DateTime), 1333420.0000, N'NV001', N'KH005', N'Quá hạn')
INSERT [dbo].[PhieuDatTruoc] ([maphieudat], [ngaylap], [ngaynhanhang], [tongtienphieudat], [manhanvien], [makhachhang], [trangthai]) VALUES (N'PD007', CAST(N'2021-12-01 18:31:58.237' AS DateTime), CAST(N'2021-12-03 07:00:00.000' AS DateTime), 136400.0000, N'NV001', N'KH009', N'Chưa nhận hàng')
INSERT [dbo].[PhieuDatTruoc] ([maphieudat], [ngaylap], [ngaynhanhang], [tongtienphieudat], [manhanvien], [makhachhang], [trangthai]) VALUES (N'PD009', CAST(N'2021-12-10 18:40:41.660' AS DateTime), CAST(N'2021-12-13 07:15:00.000' AS DateTime), 111622.5000, N'NV001', N'KH019', N'Chưa nhận hàng')
INSERT [dbo].[PhieuDatTruoc] ([maphieudat], [ngaylap], [ngaynhanhang], [tongtienphieudat], [manhanvien], [makhachhang], [trangthai]) VALUES (N'PD012', CAST(N'2021-12-10 19:06:12.680' AS DateTime), CAST(N'2021-12-13 12:00:00.000' AS DateTime), 94259.0000, N'NV003', N'KH017', N'Chưa nhận hàng')
INSERT [dbo].[PhieuDatTruoc] ([maphieudat], [ngaylap], [ngaynhanhang], [tongtienphieudat], [manhanvien], [makhachhang], [trangthai]) VALUES (N'PD013', CAST(N'2021-12-10 19:23:52.763' AS DateTime), CAST(N'2021-12-11 11:20:00.000' AS DateTime), 65733.2500, N'NV005', N'KH019', N'Chưa nhận hàng')
INSERT [dbo].[PhieuDatTruoc] ([maphieudat], [ngaylap], [ngaynhanhang], [tongtienphieudat], [manhanvien], [makhachhang], [trangthai]) VALUES (N'PD014', CAST(N'2021-12-10 19:49:40.707' AS DateTime), CAST(N'2021-12-13 15:00:00.000' AS DateTime), 137555.0000, N'NV002', N'KH013', N'Chưa nhận hàng')
INSERT [dbo].[PhieuDatTruoc] ([maphieudat], [ngaylap], [ngaynhanhang], [tongtienphieudat], [manhanvien], [makhachhang], [trangthai]) VALUES (N'PD015', CAST(N'2021-12-10 22:22:40.880' AS DateTime), CAST(N'2021-12-16 10:30:00.000' AS DateTime), 56375.0000, N'NV002', N'KH004', N'Chưa nhận hàng')
INSERT [dbo].[PhieuDatTruoc] ([maphieudat], [ngaylap], [ngaynhanhang], [tongtienphieudat], [manhanvien], [makhachhang], [trangthai]) VALUES (N'PD016', CAST(N'2021-12-11 11:12:55.830' AS DateTime), CAST(N'2021-12-11 00:00:00.000' AS DateTime), 119064.0000, N'NV002', N'KH003', N'Chưa nhận hàng')
INSERT [dbo].[PhieuDatTruoc] ([maphieudat], [ngaylap], [ngaynhanhang], [tongtienphieudat], [manhanvien], [makhachhang], [trangthai]) VALUES (N'PD019', CAST(N'2021-12-11 11:19:43.687' AS DateTime), CAST(N'2021-12-11 00:00:00.000' AS DateTime), 108240.0000, N'NV002', N'KH006', N'Chưa nhận hàng')
INSERT [dbo].[PhieuDatTruoc] ([maphieudat], [ngaylap], [ngaynhanhang], [tongtienphieudat], [manhanvien], [makhachhang], [trangthai]) VALUES (N'PD020', CAST(N'2021-12-11 11:20:34.253' AS DateTime), CAST(N'2021-12-11 00:00:00.000' AS DateTime), 915981.0000, N'NV002', N'KH006', N'Chưa nhận hàng')
INSERT [dbo].[PhieuDatTruoc] ([maphieudat], [ngaylap], [ngaynhanhang], [tongtienphieudat], [manhanvien], [makhachhang], [trangthai]) VALUES (N'PD021', CAST(N'2021-12-22 20:21:16.307' AS DateTime), CAST(N'2021-12-25 10:00:00.000' AS DateTime), 443107.0000, N'NV001', N'KH017', N'Chưa nhận hàng')
INSERT [dbo].[PhieuDatTruoc] ([maphieudat], [ngaylap], [ngaynhanhang], [tongtienphieudat], [manhanvien], [makhachhang], [trangthai]) VALUES (N'PD022', CAST(N'2021-12-22 20:23:10.760' AS DateTime), CAST(N'2021-12-23 10:00:00.000' AS DateTime), 100347.0000, N'NV001', N'KH006', N'Chưa nhận hàng')
INSERT [dbo].[SanPham] ([masanpham], [tensanpham], [soluong], [giadonvi], [gianhap], [anhsanpham], [xuatxu], [chatlieu], [sotrang], [tentacgia], [nhaxuatban], [nhacungcap], [maloai]) VALUES (N'SP001', N'Bữa trưa miễn phí ', 27, 91960.0000, 83600.0000, N'images\sanpham\sp001.jpg', NULL, NULL, 241, N'Rex Ogle', N'Dân Trí', NULL, N'SACH')
INSERT [dbo].[SanPham] ([masanpham], [tensanpham], [soluong], [giadonvi], [gianhap], [anhsanpham], [xuatxu], [chatlieu], [sotrang], [tentacgia], [nhaxuatban], [nhacungcap], [maloai]) VALUES (N'SP002', N'Totto-chan Cô bé bên cửa sổ', 35, 66000.0000, 60000.0000, N'images\sanpham\sp002.jpg', NULL, NULL, 345, N'Kuroyanagi Tetsuko', N'Văn Học', NULL, N'SACH')
INSERT [dbo].[SanPham] ([masanpham], [tensanpham], [soluong], [giadonvi], [gianhap], [anhsanpham], [xuatxu], [chatlieu], [sotrang], [tentacgia], [nhaxuatban], [nhacungcap], [maloai]) VALUES (N'SP003', N'Con bim trắng tai đen', 9, 60500.0000, 55000.0000, N'images\sanpham\sp003.jpg', NULL, NULL, 283, N'Gaviriil Troyepolsky', N'Văn Học', NULL, N'SACH')
INSERT [dbo].[SanPham] ([masanpham], [tensanpham], [soluong], [giadonvi], [gianhap], [anhsanpham], [xuatxu], [chatlieu], [sotrang], [tentacgia], [nhaxuatban], [nhacungcap], [maloai]) VALUES (N'SP004', N'Chuông nguyện hồn ai', 0, 59400.0000, 54000.0000, N'images\sanpham\sp004.jpg', NULL, NULL, 500, N'Ernest Hemingway', N'Dân Trí', NULL, N'SACH')
INSERT [dbo].[SanPham] ([masanpham], [tensanpham], [soluong], [giadonvi], [gianhap], [anhsanpham], [xuatxu], [chatlieu], [sotrang], [tentacgia], [nhaxuatban], [nhacungcap], [maloai]) VALUES (N'SP005', N'Bồ câu không đưa thư', 10, 22000.0000, 20000.0000, N'images\sanpham\sp005.jpg', NULL, NULL, 178, N'Nguyễn Nhật Ánh', N'Dân Trí', NULL, N'SACH')
INSERT [dbo].[SanPham] ([masanpham], [tensanpham], [soluong], [giadonvi], [gianhap], [anhsanpham], [xuatxu], [chatlieu], [sotrang], [tentacgia], [nhaxuatban], [nhacungcap], [maloai]) VALUES (N'SP006', N'10 điều tạo nên số phận', 12, 49500.0000, 45000.0000, N'images\sanpham\sp006.jpg', NULL, NULL, 241, N'David Simon, M.D', N'Tổng hợp Thành Phố Hồ Chí Minh', NULL, N'SACH')
INSERT [dbo].[SanPham] ([masanpham], [tensanpham], [soluong], [giadonvi], [gianhap], [anhsanpham], [xuatxu], [chatlieu], [sotrang], [tentacgia], [nhaxuatban], [nhacungcap], [maloai]) VALUES (N'SP007', N'Tư duy sáng trong lập kế hoạch và giải quyết vấn đề', 9, 49500.0000, 45000.0000, N'images\sanpham\sp007.jpg', NULL, NULL, 241, N'Nguyễn Thành Độ', N'Đại Học Kinh Tế Quốc Dân', NULL, N'SACH')
INSERT [dbo].[SanPham] ([masanpham], [tensanpham], [soluong], [giadonvi], [gianhap], [anhsanpham], [xuatxu], [chatlieu], [sotrang], [tentacgia], [nhaxuatban], [nhacungcap], [maloai]) VALUES (N'SP008', N'Bí quyết của người chiến thắng', 3, 55000.0000, 50000.0000, N'images\sanpham\sp008.jpg', NULL, NULL, 241, N'Shiv Khera', N'Dân Trí', NULL, N'SACH')
INSERT [dbo].[SanPham] ([masanpham], [tensanpham], [soluong], [giadonvi], [gianhap], [anhsanpham], [xuatxu], [chatlieu], [sotrang], [tentacgia], [nhaxuatban], [nhacungcap], [maloai]) VALUES (N'SP009', N'Trạng Quỷnh tập 105', 9, 24200.0000, 22000.0000, N'images\sanpham\sp009.jpg', NULL, NULL, 100, N'Kim Khánh', N'Đồng Nai', NULL, N'SACH')
INSERT [dbo].[SanPham] ([masanpham], [tensanpham], [soluong], [giadonvi], [gianhap], [anhsanpham], [xuatxu], [chatlieu], [sotrang], [tentacgia], [nhaxuatban], [nhacungcap], [maloai]) VALUES (N'SP010', N'Trạng Quỷnh tập 106', 8, 24200.0000, 22000.0000, N'images\sanpham\sp010.jpg', NULL, NULL, 107, N'Kim Khánh', N'Đồng Nai', NULL, N'SACH')
INSERT [dbo].[SanPham] ([masanpham], [tensanpham], [soluong], [giadonvi], [gianhap], [anhsanpham], [xuatxu], [chatlieu], [sotrang], [tentacgia], [nhaxuatban], [nhacungcap], [maloai]) VALUES (N'SP011', N'Trạng Quỷnh tập 107', 10, 24200.0000, 22000.0000, N'images\sanpham\sp011.jpg', NULL, NULL, 101, N'Kim Khánh', N'Đồng Nai', NULL, N'SACH')
INSERT [dbo].[SanPham] ([masanpham], [tensanpham], [soluong], [giadonvi], [gianhap], [anhsanpham], [xuatxu], [chatlieu], [sotrang], [tentacgia], [nhaxuatban], [nhacungcap], [maloai]) VALUES (N'SP012', N'Trạng Quỷnh tập 108', 0, 24200.0000, 22000.0000, N'images\sanpham\sp012.jpg', NULL, NULL, 102, N'Kim Khánh', N'Đồng Nai', NULL, N'SACH')
INSERT [dbo].[SanPham] ([masanpham], [tensanpham], [soluong], [giadonvi], [gianhap], [anhsanpham], [xuatxu], [chatlieu], [sotrang], [tentacgia], [nhaxuatban], [nhacungcap], [maloai]) VALUES (N'SP013', N'Trạng Quỷnh tập 109', 37, 24200.0000, 22000.0000, N'images\sanpham\sp013.jpg', NULL, NULL, 109, N'Kim Khánh', N'Đồng Nai', NULL, N'SACH')
INSERT [dbo].[SanPham] ([masanpham], [tensanpham], [soluong], [giadonvi], [gianhap], [anhsanpham], [xuatxu], [chatlieu], [sotrang], [tentacgia], [nhaxuatban], [nhacungcap], [maloai]) VALUES (N'SP014', N'Trạng Quỷnh tập 110', 46, 24200.0000, 22000.0000, N'images\sanpham\sp014.jpg', NULL, NULL, 101, N'Kim Khánh', N'Đồng Nai', NULL, N'SACH')
INSERT [dbo].[SanPham] ([masanpham], [tensanpham], [soluong], [giadonvi], [gianhap], [anhsanpham], [xuatxu], [chatlieu], [sotrang], [tentacgia], [nhaxuatban], [nhacungcap], [maloai]) VALUES (N'SP015', N'Trạng Quỷnh tập 91', 41, 24200.0000, 22000.0000, N'images\sanpham\sp015.jpg', NULL, NULL, 105, N'Kim Khánh', N'Đồng Nai', NULL, N'SACH')
INSERT [dbo].[SanPham] ([masanpham], [tensanpham], [soluong], [giadonvi], [gianhap], [anhsanpham], [xuatxu], [chatlieu], [sotrang], [tentacgia], [nhaxuatban], [nhacungcap], [maloai]) VALUES (N'SP016', N'Trạng Quỷnh tập 92', 50, 24200.0000, 22000.0000, N'images\sanpham\sp016.jpg', NULL, NULL, 100, N'Kim Khánh', N'Đồng Nai', NULL, N'SACH')
INSERT [dbo].[SanPham] ([masanpham], [tensanpham], [soluong], [giadonvi], [gianhap], [anhsanpham], [xuatxu], [chatlieu], [sotrang], [tentacgia], [nhaxuatban], [nhacungcap], [maloai]) VALUES (N'SP017', N'Trạng Quỷnh tập 93', 54, 24200.0000, 22000.0000, N'images\sanpham\sp017.jpg', NULL, NULL, 105, N'Kim Khánh', N'Đồng Nai', NULL, N'SACH')
INSERT [dbo].[SanPham] ([masanpham], [tensanpham], [soluong], [giadonvi], [gianhap], [anhsanpham], [xuatxu], [chatlieu], [sotrang], [tentacgia], [nhaxuatban], [nhacungcap], [maloai]) VALUES (N'SP018', N'Trạng Quỷnh tập 94', 64, 24200.0000, 22000.0000, N'images\sanpham\sp018.jpg', NULL, NULL, 103, N'Kim Khánh', N'Đồng Nai', NULL, N'SACH')
INSERT [dbo].[SanPham] ([masanpham], [tensanpham], [soluong], [giadonvi], [gianhap], [anhsanpham], [xuatxu], [chatlieu], [sotrang], [tentacgia], [nhaxuatban], [nhacungcap], [maloai]) VALUES (N'SP019', N'Trạng Quỷnh tập 95', 56, 24200.0000, 22000.0000, N'images\sanpham\sp019.jpg', NULL, NULL, 102, N'Kim Khánh', N'Đồng Nai', NULL, N'SACH')
INSERT [dbo].[SanPham] ([masanpham], [tensanpham], [soluong], [giadonvi], [gianhap], [anhsanpham], [xuatxu], [chatlieu], [sotrang], [tentacgia], [nhaxuatban], [nhacungcap], [maloai]) VALUES (N'SP020', N'Trạng Quỷnh tập 96', 64, 24200.0000, 22000.0000, N'images\sanpham\sp020.jpg', NULL, NULL, 104, N'Kim Khánh', N'Đồng Nai', NULL, N'SACH')
INSERT [dbo].[SanPham] ([masanpham], [tensanpham], [soluong], [giadonvi], [gianhap], [anhsanpham], [xuatxu], [chatlieu], [sotrang], [tentacgia], [nhaxuatban], [nhacungcap], [maloai]) VALUES (N'SP021', N'Trạng Quỷnh tập 97', 56, 24200.0000, 22000.0000, N'images\sanpham\sp021.jpg', NULL, NULL, 100, N'Kim Khánh', N'Đồng Nai', NULL, N'SACH')
INSERT [dbo].[SanPham] ([masanpham], [tensanpham], [soluong], [giadonvi], [gianhap], [anhsanpham], [xuatxu], [chatlieu], [sotrang], [tentacgia], [nhaxuatban], [nhacungcap], [maloai]) VALUES (N'SP022', N'Trạng Quỷnh tập 98', 59, 24200.0000, 22000.0000, N'images\sanpham\sp022.jpg', NULL, NULL, 103, N'Kim Khánh', N'Đồng Nai', NULL, N'SACH')
INSERT [dbo].[SanPham] ([masanpham], [tensanpham], [soluong], [giadonvi], [gianhap], [anhsanpham], [xuatxu], [chatlieu], [sotrang], [tentacgia], [nhaxuatban], [nhacungcap], [maloai]) VALUES (N'SP023', N'Trạng Quỷnh tập 99', 56, 24200.0000, 22000.0000, N'images\sanpham\sp023.jpg', NULL, NULL, 101, N'Kim Khánh', N'Đồng Nai', NULL, N'SACH')
INSERT [dbo].[SanPham] ([masanpham], [tensanpham], [soluong], [giadonvi], [gianhap], [anhsanpham], [xuatxu], [chatlieu], [sotrang], [tentacgia], [nhaxuatban], [nhacungcap], [maloai]) VALUES (N'SP024', N'Conan Tập 90', 63, 25300.0000, 23000.0000, N'images\sanpham\sp024.jpg', NULL, NULL, 123, N'Gosho Aoyama', N'Kim Đồng', NULL, N'SACH')
INSERT [dbo].[SanPham] ([masanpham], [tensanpham], [soluong], [giadonvi], [gianhap], [anhsanpham], [xuatxu], [chatlieu], [sotrang], [tentacgia], [nhaxuatban], [nhacungcap], [maloai]) VALUES (N'SP025', N'Conan Tập 91', 59, 25300.0000, 23000.0000, N'images\sanpham\sp025.jpg', NULL, NULL, 134, N'Gosho Aoyama', N'Kim Đồng', NULL, N'SACH')
INSERT [dbo].[SanPham] ([masanpham], [tensanpham], [soluong], [giadonvi], [gianhap], [anhsanpham], [xuatxu], [chatlieu], [sotrang], [tentacgia], [nhaxuatban], [nhacungcap], [maloai]) VALUES (N'SP026', N'Conan Tập 92', 69, 25300.0000, 23000.0000, N'images\sanpham\sp026.jpg', NULL, NULL, 155, N'Gosho Aoyama', N'Kim Đồng', NULL, N'SACH')
INSERT [dbo].[SanPham] ([masanpham], [tensanpham], [soluong], [giadonvi], [gianhap], [anhsanpham], [xuatxu], [chatlieu], [sotrang], [tentacgia], [nhaxuatban], [nhacungcap], [maloai]) VALUES (N'SP027', N'Conan Tập 93', 58, 25300.0000, 23000.0000, N'images\sanpham\sp027.jpg', NULL, NULL, 124, N'Gosho Aoyama', N'Kim Đồng', NULL, N'SACH')
INSERT [dbo].[SanPham] ([masanpham], [tensanpham], [soluong], [giadonvi], [gianhap], [anhsanpham], [xuatxu], [chatlieu], [sotrang], [tentacgia], [nhaxuatban], [nhacungcap], [maloai]) VALUES (N'SP028', N'Conan Tập 94', 64, 25300.0000, 23000.0000, N'images\sanpham\sp028.jpg', NULL, NULL, 125, N'Gosho Aoyama', N'Kim Đồng', NULL, N'SACH')
INSERT [dbo].[SanPham] ([masanpham], [tensanpham], [soluong], [giadonvi], [gianhap], [anhsanpham], [xuatxu], [chatlieu], [sotrang], [tentacgia], [nhaxuatban], [nhacungcap], [maloai]) VALUES (N'SP029', N'Conan Tập 95', 63, 25300.0000, 23000.0000, N'images\sanpham\sp029.jpg', NULL, NULL, 122, N'Gosho Aoyama', N'Kim Đồng', NULL, N'SACH')
INSERT [dbo].[SanPham] ([masanpham], [tensanpham], [soluong], [giadonvi], [gianhap], [anhsanpham], [xuatxu], [chatlieu], [sotrang], [tentacgia], [nhaxuatban], [nhacungcap], [maloai]) VALUES (N'SP030', N'Conan Tập 96', 52, 25300.0000, 23000.0000, N'images\sanpham\sp030.jpg', NULL, NULL, 123, N'Gosho Aoyama', N'Kim Đồng', NULL, N'SACH')
INSERT [dbo].[SanPham] ([masanpham], [tensanpham], [soluong], [giadonvi], [gianhap], [anhsanpham], [xuatxu], [chatlieu], [sotrang], [tentacgia], [nhaxuatban], [nhacungcap], [maloai]) VALUES (N'SP031', N'Conan Tập 97', 64, 25300.0000, 23000.0000, N'images\sanpham\sp031.jpg', NULL, NULL, 120, N'Gosho Aoyama', N'Kim Đồng', NULL, N'SACH')
INSERT [dbo].[SanPham] ([masanpham], [tensanpham], [soluong], [giadonvi], [gianhap], [anhsanpham], [xuatxu], [chatlieu], [sotrang], [tentacgia], [nhaxuatban], [nhacungcap], [maloai]) VALUES (N'SP032', N'Conan Tập 98', 61, 25300.0000, 23000.0000, N'images\sanpham\sp032.jpg', NULL, NULL, 122, N'Gosho Aoyama', N'Kim Đồng', NULL, N'SACH')
INSERT [dbo].[SanPham] ([masanpham], [tensanpham], [soluong], [giadonvi], [gianhap], [anhsanpham], [xuatxu], [chatlieu], [sotrang], [tentacgia], [nhaxuatban], [nhacungcap], [maloai]) VALUES (N'SP033', N'Shin-Cậu bé bút chì Tập 30', 61, 18700.0000, 17000.0000, N'images\sanpham\sp033.jpg', NULL, NULL, 119, N'Yoshito Usui', N'Kim Đồng', NULL, N'SACH')
INSERT [dbo].[SanPham] ([masanpham], [tensanpham], [soluong], [giadonvi], [gianhap], [anhsanpham], [xuatxu], [chatlieu], [sotrang], [tentacgia], [nhaxuatban], [nhacungcap], [maloai]) VALUES (N'SP034', N'Shin-Cậu bé bút chì Tập 31', 54, 18700.0000, 17000.0000, N'images\sanpham\sp034.jpg', NULL, NULL, 124, N'Yoshito Usui', N'Kim Đồng', NULL, N'SACH')
INSERT [dbo].[SanPham] ([masanpham], [tensanpham], [soluong], [giadonvi], [gianhap], [anhsanpham], [xuatxu], [chatlieu], [sotrang], [tentacgia], [nhaxuatban], [nhacungcap], [maloai]) VALUES (N'SP035', N'Shin-Cậu bé bút chì Tập 32', 65, 18700.0000, 17000.0000, N'images\sanpham\sp035.jpg', NULL, NULL, 122, N'Yoshito Usui', N'Kim Đồng', NULL, N'SACH')
INSERT [dbo].[SanPham] ([masanpham], [tensanpham], [soluong], [giadonvi], [gianhap], [anhsanpham], [xuatxu], [chatlieu], [sotrang], [tentacgia], [nhaxuatban], [nhacungcap], [maloai]) VALUES (N'SP036', N'Shin-Cậu bé bút chì Tập 33', 62, 18700.0000, 17000.0000, N'images\sanpham\sp036.jpg', NULL, NULL, 124, N'Yoshito Usui', N'Kim Đồng', NULL, N'SACH')
INSERT [dbo].[SanPham] ([masanpham], [tensanpham], [soluong], [giadonvi], [gianhap], [anhsanpham], [xuatxu], [chatlieu], [sotrang], [tentacgia], [nhaxuatban], [nhacungcap], [maloai]) VALUES (N'SP037', N'Shin-Cậu bé bút chì Tập 34', 67, 18700.0000, 17000.0000, N'images\sanpham\sp037.jpg', NULL, NULL, 130, N'Yoshito Usui', N'Kim Đồng', NULL, N'SACH')
INSERT [dbo].[SanPham] ([masanpham], [tensanpham], [soluong], [giadonvi], [gianhap], [anhsanpham], [xuatxu], [chatlieu], [sotrang], [tentacgia], [nhaxuatban], [nhacungcap], [maloai]) VALUES (N'SP038', N'The Heart of Darkness', 58, 110000.0000, 100000.0000, N'images\sanpham\sp038.jpg', NULL, NULL, 154, N'Joseph Conrad', N'Signet Classic', NULL, N'SACH')
INSERT [dbo].[SanPham] ([masanpham], [tensanpham], [soluong], [giadonvi], [gianhap], [anhsanpham], [xuatxu], [chatlieu], [sotrang], [tentacgia], [nhaxuatban], [nhacungcap], [maloai]) VALUES (N'SP039', N'Harry Porter – The Complete Collection Children UK Edition', 52, 1100000.0000, 1000000.0000, N'images\sanpham\sp039.jpg', NULL, NULL, 4623, N'J.K.Rowling', N'Bloomsbury ', NULL, N'SACH')
INSERT [dbo].[SanPham] ([masanpham], [tensanpham], [soluong], [giadonvi], [gianhap], [anhsanpham], [xuatxu], [chatlieu], [sotrang], [tentacgia], [nhaxuatban], [nhacungcap], [maloai]) VALUES (N'SP040', N'Bút Bi Xanh Thiên Long', 57, 4510.0000, 4100.0000, N'images\sanpham\sp040.jpg', N'Việt Nam', N'Nhựa', 0, NULL, NULL, N'Thiên Long', N'VANP')
INSERT [dbo].[SanPham] ([masanpham], [tensanpham], [soluong], [giadonvi], [gianhap], [anhsanpham], [xuatxu], [chatlieu], [sotrang], [tentacgia], [nhaxuatban], [nhacungcap], [maloai]) VALUES (N'SP041', N'Bút Bi Đỏ Thiên Long', 67, 4950.0000, 4500.0000, N'images\sanpham\sp041.jpg', N'Việt Nam', N'Nhựa', 0, NULL, NULL, N'Thiên Long', N'VANP')
INSERT [dbo].[SanPham] ([masanpham], [tensanpham], [soluong], [giadonvi], [gianhap], [anhsanpham], [xuatxu], [chatlieu], [sotrang], [tentacgia], [nhaxuatban], [nhacungcap], [maloai]) VALUES (N'SP042', N'Bút Bi Đen Thiên Long', 48, 4730.0000, 4300.0000, N'images\sanpham\sp042.jpg', N'Việt Nam', N'Nhựa', 0, NULL, NULL, N'Thiên Long', N'VANP')
INSERT [dbo].[SanPham] ([masanpham], [tensanpham], [soluong], [giadonvi], [gianhap], [anhsanpham], [xuatxu], [chatlieu], [sotrang], [tentacgia], [nhaxuatban], [nhacungcap], [maloai]) VALUES (N'SP043', N'Bút Lông Xanh Thiên Long', 67, 10780.0000, 9800.0000, N'images\sanpham\sp043.jpg', N'Việt Nam', N'Nhựa', 0, NULL, NULL, N'Thiên Long', N'VANP')
INSERT [dbo].[SanPham] ([masanpham], [tensanpham], [soluong], [giadonvi], [gianhap], [anhsanpham], [xuatxu], [chatlieu], [sotrang], [tentacgia], [nhaxuatban], [nhacungcap], [maloai]) VALUES (N'SP044', N'Bút Lông Đỏ Thiên Long', 71, 10780.0000, 9800.0000, N'images\sanpham\sp044.jpg', N'Việt Nam', N'Nhựa', 0, NULL, NULL, N'Thiên Long', N'VANP')
INSERT [dbo].[SanPham] ([masanpham], [tensanpham], [soluong], [giadonvi], [gianhap], [anhsanpham], [xuatxu], [chatlieu], [sotrang], [tentacgia], [nhaxuatban], [nhacungcap], [maloai]) VALUES (N'SP045', N'Bút Lông Đen Thiên Long', 63, 10780.0000, 9800.0000, N'images\sanpham\sp045.jpg', N'Việt Nam', N'Nhựa', 0, NULL, NULL, N'Thiên Long', N'VANP')
INSERT [dbo].[SanPham] ([masanpham], [tensanpham], [soluong], [giadonvi], [gianhap], [anhsanpham], [xuatxu], [chatlieu], [sotrang], [tentacgia], [nhaxuatban], [nhacungcap], [maloai]) VALUES (N'SP046', N'Mực bút máy Tím- Nhỏ', 57, 14850.0000, 13500.0000, N'images\sanpham\sp046.jpg', N'Việt Nam', N'Thủy tinh', 0, NULL, NULL, N'Điểm 10', N'VANP')
INSERT [dbo].[SanPham] ([masanpham], [tensanpham], [soluong], [giadonvi], [gianhap], [anhsanpham], [xuatxu], [chatlieu], [sotrang], [tentacgia], [nhaxuatban], [nhacungcap], [maloai]) VALUES (N'SP047', N'Mực bút máy Tím- Vừa', 60, 14850.0000, 13500.0000, N'images\sanpham\sp046.jpg', N'Việt Nam', N'Thủy tinh', 0, NULL, NULL, N'Điểm 10', N'VANP')
INSERT [dbo].[SanPham] ([masanpham], [tensanpham], [soluong], [giadonvi], [gianhap], [anhsanpham], [xuatxu], [chatlieu], [sotrang], [tentacgia], [nhaxuatban], [nhacungcap], [maloai]) VALUES (N'SP048', N'Mực bút máy Tím- Lớn', 60, 14850.0000, 13500.0000, N'images\sanpham\sp046.jpg', N'Việt Nam', N'Thủy tinh', NULL, NULL, NULL, N'Điểm 10', N'VANP')
INSERT [dbo].[TaiKhoan] ([tentaikhoan], [matkhau]) VALUES (N'NV001', N'1234')
INSERT [dbo].[TaiKhoan] ([tentaikhoan], [matkhau]) VALUES (N'NV002', N'1234')
INSERT [dbo].[TaiKhoan] ([tentaikhoan], [matkhau]) VALUES (N'NV003', N'1234')
INSERT [dbo].[TaiKhoan] ([tentaikhoan], [matkhau]) VALUES (N'NV005', N'1234')
INSERT [dbo].[TaiKhoan] ([tentaikhoan], [matkhau]) VALUES (N'NV006', N'1234')
ALTER TABLE [dbo].[CTPhieuDatTruoc]  WITH NOCHECK ADD  CONSTRAINT [FK_CTPhieuDatTruoc_PhieuDatTruoc1] FOREIGN KEY([maphieudat])
REFERENCES [dbo].[PhieuDatTruoc] ([maphieudat])
GO
ALTER TABLE [dbo].[CTPhieuDatTruoc] CHECK CONSTRAINT [FK_CTPhieuDatTruoc_PhieuDatTruoc1]
GO
ALTER TABLE [dbo].[CTPhieuDatTruoc]  WITH NOCHECK ADD  CONSTRAINT [FK_CTPhieuDatTruoc_SanPham1] FOREIGN KEY([masanpham])
REFERENCES [dbo].[SanPham] ([masanpham])
GO
ALTER TABLE [dbo].[CTPhieuDatTruoc] CHECK CONSTRAINT [FK_CTPhieuDatTruoc_SanPham1]
GO
ALTER TABLE [dbo].[CTHoaDon]  WITH CHECK ADD  CONSTRAINT [FK_CTHoaDon_HoaDon1] FOREIGN KEY([mahoadon])
REFERENCES [dbo].[HoaDon] ([mahoadon])
GO
ALTER TABLE [dbo].[CTHoaDon] CHECK CONSTRAINT [FK_CTHoaDon_HoaDon1]
GO
ALTER TABLE [dbo].[CTHoaDon]  WITH CHECK ADD  CONSTRAINT [FK_CTHoaDon_SanPham1] FOREIGN KEY([masanpham])
REFERENCES [dbo].[SanPham] ([masanpham])
GO
ALTER TABLE [dbo].[CTHoaDon] CHECK CONSTRAINT [FK_CTHoaDon_SanPham1]
GO
ALTER TABLE [dbo].[HoaDon]  WITH CHECK ADD  CONSTRAINT [FK_HoaDon1_KhachHang] FOREIGN KEY([makhachhang])
REFERENCES [dbo].[KhachHang] ([makhachhang])
GO
ALTER TABLE [dbo].[HoaDon] CHECK CONSTRAINT [FK_HoaDon1_KhachHang]
GO
ALTER TABLE [dbo].[HoaDon]  WITH CHECK ADD  CONSTRAINT [FK_HoaDon1_NhanVien] FOREIGN KEY([manhanvien])
REFERENCES [dbo].[NhanVien] ([manhanvien])
GO
ALTER TABLE [dbo].[HoaDon] CHECK CONSTRAINT [FK_HoaDon1_NhanVien]
GO
ALTER TABLE [dbo].[NhanVien]  WITH CHECK ADD  CONSTRAINT [FK_NhanVien_NguoiQuanLy] FOREIGN KEY([maquanly])
REFERENCES [dbo].[NhanVien] ([manhanvien])
GO
ALTER TABLE [dbo].[NhanVien] CHECK CONSTRAINT [FK_NhanVien_NguoiQuanLy]
GO
ALTER TABLE [dbo].[NhanVien]  WITH CHECK ADD  CONSTRAINT [FK_NhanVien_TaiKhoan] FOREIGN KEY([manhanvien])
REFERENCES [dbo].[TaiKhoan] ([tentaikhoan])
GO
ALTER TABLE [dbo].[NhanVien] CHECK CONSTRAINT [FK_NhanVien_TaiKhoan]
GO
ALTER TABLE [dbo].[PhieuDatTruoc]  WITH NOCHECK ADD  CONSTRAINT [FK_PhieuDatTruoc_KhachHang] FOREIGN KEY([makhachhang])
REFERENCES [dbo].[KhachHang] ([makhachhang])
GO
ALTER TABLE [dbo].[PhieuDatTruoc] CHECK CONSTRAINT [FK_PhieuDatTruoc_KhachHang]
GO
ALTER TABLE [dbo].[PhieuDatTruoc]  WITH NOCHECK ADD  CONSTRAINT [FK_PhieuDatTruoc1_NhanVien] FOREIGN KEY([manhanvien])
REFERENCES [dbo].[NhanVien] ([manhanvien])
GO
ALTER TABLE [dbo].[PhieuDatTruoc] CHECK CONSTRAINT [FK_PhieuDatTruoc1_NhanVien]
GO
ALTER TABLE [dbo].[SanPham]  WITH CHECK ADD  CONSTRAINT [FK_SanPham1_LoaiSanPham] FOREIGN KEY([maloai])
REFERENCES [dbo].[LoaiSanPham] ([maloai])
GO
ALTER TABLE [dbo].[SanPham] CHECK CONSTRAINT [FK_SanPham1_LoaiSanPham]
GO
USE [master]
GO
ALTER DATABASE [NhaSachTNL] SET  READ_WRITE 
GO
