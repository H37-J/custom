DECLARE @START INT
DECLARE @END   INT

SET @START = 1
SET @END = 10000

WHILE @START <= @END
BEGIN
INSERT INTO CUSTOMER(CUSTOMER_NAME, EMAIL, MONEY)
VALUES ('hjk' + CONVERT(VARCHAR(100), @START), 'these' + CONVERT(VARCHAR(100), @START) + '@gmail.com', 10000)

    SET @START += 1
END
GO

create proc getById @CUSTOMER_ID bigint
as
select *
from customer
where CUSTOMER_ID = @CUSTOMER_ID
    go;

exec getById 1



CREATE FUNCTION GET_DA
(
    @test VARCHAR(1000)
)
    RETURNS VARCHAR(1000)
AS

BEGIN

    DECLARE @Index bigint

    SET	@index = 0

    WHILE @Index < 10
SELECT	@test = @test + convert(varchar, @index),
          @index = @index + 1

              RETURN @test
END



CREATE PROCEDURE [dbo].[CUSTOMER_BACKUP]
AS
DECLARE @DIR NVARCHAR(50)
DECLARE @WD NVARCHAR(30)
DECLARE @WDDIR NVARCHAR(3)
BEGIN

    SET @WD = (SELECT DATENAME(WEEKDAY, GETDATE()))

    SET @WDDIR = (SELECT
                      CASE WHEN @WD='월요일' THEN 'MON'
                           WHEN @WD='화요일' THEN 'TUE'
                           WHEN @WD='수요일' THEN 'WED'
                           WHEN @WD='목요일' THEN 'THU'
                           WHEN @WD='금요일' THEN 'FRI'
                           WHEN @WD='토요일' THEN 'SAT'
                           WHEN @WD='일요일' THEN 'MON' END)

    --SET @DIR = 'E:\MSSQL\BACKUP\' + @WDDIR + '\AptTax_F_BAK_' + CONVERT(NVARCHAR(10),GETDATE(),112) + '.bak'

    SET @DIR = '~/IdeaProjects/hjk\backup' + CONVERT(NVARCHAR(10),GETDATE(),112) + '.bak'

    IF @WD IN ('월요일','화요일','수요일','목요일','금요일','토요일')	--토요일만 전체백업
BEGIN
            Backup Database shop To Disk=@DIR With Init
END


END


