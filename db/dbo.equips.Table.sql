USE [Hospital]
GO
/****** Object:  Table [dbo].[equips]    Script Date: 2019/3/6 星期三 17:03:34 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[equips](
	[eno] [nchar](10) NOT NULL,
	[etype] [varchar](20) NULL,
	[ename] [varchar](20) NULL,
	[elocation] [varchar](20) NULL,
	[maxnum] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[eno] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[equips] ([eno], [etype], [ename], [elocation], [maxnum]) VALUES (N'0001      ', N'X8086', N'X光机', N'二楼放射科2室', 10)
INSERT [dbo].[equips] ([eno], [etype], [ename], [elocation], [maxnum]) VALUES (N'0002      ', N'B6069', N'B超机1', N'三楼B超1室', 15)
INSERT [dbo].[equips] ([eno], [etype], [ename], [elocation], [maxnum]) VALUES (N'0003      ', N'CT001', N'CT机', N'二楼放射科1室', 15)
INSERT [dbo].[equips] ([eno], [etype], [ename], [elocation], [maxnum]) VALUES (N'0004      ', N'MRI01', N'核磁共振机', N'二楼放射科3室', 15)
INSERT [dbo].[equips] ([eno], [etype], [ename], [elocation], [maxnum]) VALUES (N'0005      ', N'B6070', N'B超机2', N'三楼B超2室', 10)
