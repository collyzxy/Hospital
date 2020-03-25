USE [Hospital]
GO
/****** Object:  Table [dbo].[0005]    Script Date: 2019/3/6 星期三 17:03:34 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[0005](
	[adate] [date] NULL,
	[num_am] [int] NULL,
	[num_pm] [int] NULL
) ON [PRIMARY]
GO
INSERT [dbo].[0005] ([adate], [num_am], [num_pm]) VALUES (CAST(N'2019-03-03' AS Date), 0, 0)
INSERT [dbo].[0005] ([adate], [num_am], [num_pm]) VALUES (CAST(N'2019-03-04' AS Date), 0, 0)
