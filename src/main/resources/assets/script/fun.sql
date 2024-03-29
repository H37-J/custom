WITH RECURSIVE fibonacci (n, fib_n, next_fib_n) AS
                   (SELECT 1, 0, 1
                    UNION ALL
                    SELECT n + 1, next_fib_n, fib_n + next_fib_n
                    FROM fibonacci
                    WHERE n < 10)
SELECT *
FROM fibonacci;



SELECT ADDDATE(START_DATE, 1), START_DATE
FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY;

SELECT CURRENT_USER(), CURRENT_USER, CURDATE();

SELECT DATE_FORMAT(CURDATE(), '%W %M %Y');
SELECT DATE_FORMAT(CURDATE(), '%H:%i:%s');

SELECT EXTRACT(YEAR FROM '2019-07-02');
SELECT EXTRACT(YEAR_MONTH FROM '2019-07-02 01:02:03');
SELECT EXTRACT(DAY_MINUTE FROM '2019-07-02 01:02:03');
SELECT EXTRACT(MICROSECOND
               FROM '2003-01-02 10:30:00.000123');

SELECT FIND_IN_SET('b','a,b,c,d');

SELECT GREATEST(1,2,3);

SELECT CAR_ID, COUNT(CAR_ID)
FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
WHERE START_DATE >= '2022-10-16'
GROUP BY CAR_ID WITH ROLLUP;

SELECT INSTR('ABCTEST', 'TEST');

SELECT LEFT('TEST', 2);

SELECT LENGTH('TEST');

SELECT MD5('TEST');

SELECT
    CAR_ID,
    ROW_NUMBER() OVER w AS 'ROW',
    RANK()       OVER w AS 'RANK',
    DENSE_RANK() OVER w AS 'DENSE_RANK'
FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
WINDOW w AS (ORDER BY CAR_ID);

SELECT REPLACE('TEST', 'S', 'T');

SELECT RPAD('HI', 5, '?');

SELECT SCHEMA();

SELECT *, ROW_COUNT() FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY;

SELECT POW(2, 2), SQRT(4);

CREATE TABLE TEST (
    ID INTEGER PRIMARY KEY AUTO_INCREMENT,
    TEXT VARCHAR(100) NULL,
    CREATE_DATE DATE NULL
);

DROP TABLE TEST;

INSERT INTO TEST(TEXT, CREATE_DATE) VALUES('TEST', '2022-10-16');

SELECT GREATEST(ID, TEXT) FROM TEST;

INSERT INTO TEST(ID, TEXT, CREATE_DATE) VALUE(1, 'TEST', '2022-10-16')
ON DUPLICATE KEY UPDATE TEXT = VALUES(TEXT)
