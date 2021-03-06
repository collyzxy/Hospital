USE [Hospital]
GO
/****** Object:  Table [dbo].[equipinfo]    Script Date: 2019/3/6 星期三 17:03:34 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[equipinfo](
	[eno] [nchar](10) NOT NULL,
	[region] [varchar](20) NULL,
	[rtime] [int] NULL
) ON [PRIMARY]
GO
INSERT [dbo].[equipinfo] ([eno], [region], [rtime]) VALUES (N'0001      ', N'骨骼', 15)
INSERT [dbo].[equipinfo] ([eno], [region], [rtime]) VALUES (N'0005      ', N'肝脏', 15)
INSERT [dbo].[equipinfo] ([eno], [region], [rtime]) VALUES (N'0002      ', N'胰脏', 10)
INSERT [dbo].[equipinfo] ([eno], [region], [rtime]) VALUES (N'0002      ', N'腹腔', 10)
INSERT [dbo].[equipinfo] ([eno], [region], [rtime]) VALUES (N'0003      ', N'脑部', 10)
INSERT [dbo].[equipinfo] ([eno], [region], [rtime]) VALUES (N'0003      ', N'心脏', 10)
INSERT [dbo].[equipinfo] ([eno], [region], [rtime]) VALUES (N'0003      ', N'胰脏', 10)
INSERT [dbo].[equipinfo] ([eno], [region], [rtime]) VALUES (N'0004      ', N'脑部', 10)
INSERT [dbo].[equipinfo] ([eno], [region], [rtime]) VALUES (N'0004      ', N'心脏', 10)
INSERT [dbo].[equipinfo] ([eno], [region], [rtime]) VALUES (N'0004      ', N'腹腔', 10)
