USE [Hospital]
GO
/****** Object:  Table [dbo].[patientinfo]    Script Date: 2019/3/6 星期三 17:03:34 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[patientinfo](
	[ID] [nchar](10) NOT NULL,
	[pname] [varchar](20) NULL,
	[age] [int] NULL,
	[sex] [bit] NULL,
	[flag] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[patientinfo] ([ID], [pname], [age], [sex], [flag]) VALUES (N'101       ', N'张三', 18, 1, 1)
INSERT [dbo].[patientinfo] ([ID], [pname], [age], [sex], [flag]) VALUES (N'102       ', N'李四', 20, 0, 1)
