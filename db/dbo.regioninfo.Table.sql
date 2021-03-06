USE [Hospital]
GO
/****** Object:  Table [dbo].[regioninfo]    Script Date: 2019/3/6 星期三 17:03:34 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[regioninfo](
	[rname] [nchar](10) NOT NULL,
	[equips] [nchar](10) NOT NULL,
	[condition] [nchar](10) NULL,
	[rpriority] [int] NULL
) ON [PRIMARY]
GO
INSERT [dbo].[regioninfo] ([rname], [equips], [condition], [rpriority]) VALUES (N'脑部        ', N'0003 0004 ', N'无         ', 0)
INSERT [dbo].[regioninfo] ([rname], [equips], [condition], [rpriority]) VALUES (N'心脏        ', N'0003 0004 ', N'无         ', 0)
INSERT [dbo].[regioninfo] ([rname], [equips], [condition], [rpriority]) VALUES (N'骨骼        ', N'0001      ', N'无         ', 0)
INSERT [dbo].[regioninfo] ([rname], [equips], [condition], [rpriority]) VALUES (N'肝脏        ', N'0005      ', N'空腹        ', 1)
INSERT [dbo].[regioninfo] ([rname], [equips], [condition], [rpriority]) VALUES (N'胰脏        ', N'0002 0003 ', N'饱腹        ', 0)
INSERT [dbo].[regioninfo] ([rname], [equips], [condition], [rpriority]) VALUES (N'腹腔        ', N'0002 0004 ', N'憋尿        ', 0)
