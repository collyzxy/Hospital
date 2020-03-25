USE [Hospital]
GO
/****** Object:  Table [dbo].[appointinfo]    Script Date: 2019/3/6 星期三 17:03:34 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[appointinfo](
	[ID] [nchar](10) NULL,
	[rname] [varchar](10) NULL,
	[clocation] [varchar](20) NULL,
	[ctime] [bit] NULL,
	[wait] [int] NULL
) ON [PRIMARY]
GO
