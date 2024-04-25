# LEVEL1

# 잡은 물고기의 평균 길이 구하기
SELECT ROUND(AVG(COALESCE(LENGTH, 10)), 2) AS AVERAGE_LENGTH
FROM FISH_INFO;

# 잔챙이 잡은 수 구하기
SELECT COUNT(*) AS FISH_COUNT
FROM FISH_INFO
WHERE LENGTH IS NULL
GROUP BY LENGTH;

# 월별 잡은 물고기 수
SELECT COUNT(EXTRACT(MONTH FROM TIME)) AS FISH_COUNT, EXTRACT(MONTH FROM TIME) AS MONTH
FROM FISH_INFO
GROUP BY MONTH
ORDER BY MONTH;

# 물고기 종류 별 대어 찾기
WITH SUB AS
         (SELECT INFO.FISH_TYPE, MAX(LENGTH) AS LEN, NAME.FISH_NAME
          FROM FISH_INFO INFO
                   INNER JOIN FISH_NAME_INFO NAME
                              ON INFO.FISH_TYPE = NAME.FISH_TYPE
          GROUP BY INFO.FISH_TYPE, NAME.FISH_NAME)
SELECT ID, FISH_NAME, LENGTH
FROM FISH_INFO INFO
         INNER JOIN SUB
                    ON INFO.FISH_TYPE = SUB.FISH_TYPE
                        AND INFO.LENGTH = SUB.LEN;

# 물고기 종류 별 잡은 수 구하기
SELECT COUNT(*) AS FISH_COUNT, FISH_NAME
FROM FISH_INFO INFO
         INNER JOIN FISH_NAME_INFO NAME
                    ON INFO.FISH_TYPE = NAME.FISH_TYPE
GROUP BY INFO.FISH_TYPE
ORDER BY FISH_COUNT DESC;

# 한 해에 잡은 물고기 수
SELECT COUNT(*) AS FISH_COUNT
FROM FISH_INFO
WHERE EXTRACT(YEAR FROM TIME) = 2021;

# 가장 큰 물고기 10마리 구하기
SELECT ID, LENGTH
FROM FISH_INFO
ORDER BY LENGTH DESC, ID
LIMIT 10;

# 특정 물고기를 잡은 총 수 구하기
WITH SUB AS (SELECT COUNT(*) AS FISH_COUNT
             FROM FISH_NAME_INFO NAME
                      INNER JOIN FISH_INFO INFO
                                 ON NAME.FISH_TYPE = INFO.FISH_TYPE
             WHERE FISH_NAME IN ('BASS', 'SNAPPER')
             GROUP BY FISH_NAME)
SELECT SUM(FISH_COUNT) AS FISH_COUNT
FROM SUB;

#특정 조건을 만족하는 물고기별 수와 최대 길이 구하기
SELECT COUNT(*) AS FISH_COUNT, MAX(LENGTH) AS MAX_LENGTH, FISH_TYPE
FROM FISH_INFO
GROUP BY FISH_TYPE
HAVING AVG(COALESCE(LENGTH, 10)) >= 33
ORDER BY FISH_TYPE;

# 잡은 물고기 중 가장 큰 물고기
SELECT CONCAT(LENGTH, 'cm') AS MAX_LENGTH
FROM FISH_INFO
ORDER BY LENGTH desc
LIMIT 1;

# 노선별 평균 역 사이 거리 조회하기
SELECT ROUTE
     , CONCAT(ROUND(SUM(D_BETWEEN_DIST), 1), 'km') AS TOTAL_DISTANCE
     , CONCAT(ROUND(AVG(D_BETWEEN_DIST), 2), 'km') AS AVERAGE_DISTANCE
FROM SUBWAY_DISTANCE
GROUP BY ROUTE
ORDER BY ROUND(SUM(D_BETWEEN_DIST), 1) DESC;


# 연도 별 평균 미세먼지 농도 조회하기
SELECT EXTRACT(YEAR FROM YM)  AS YEAR
     , ROUND(AVG(PM_VAL1), 2) AS PM10
     , ROUND(AVG(PM_VAL2), 2) AS 'PM2.5'
FROM AIR_POLLUTION
WHERE LOCATION2 = '수원'
GROUP BY EXTRACT(YEAR FROM YM)
ORDER BY YEAR;

# 부서별 평균 연봉 조회하기
SELECT EMP.DEPT_ID, DEPT_NAME_EN, ROUND(AVG(SAL), 0) AS AVG_SAL
FROM HR_DEPARTMENT DEPART
         INNER JOIN HR_EMPLOYEES EMP
                    ON EMP.DEPT_ID = DEPART.DEPT_ID
GROUP BY EMP.DEPT_ID
ORDER BY AVG_SAL DESC;

# 연간 평가점수에 해당하는 평가 등급 및 성과금 조회하기
SELECT EMP.EMP_NO,
       EMP_NAME
        ,
       CASE
           WHEN AVG(SCORE) >= 96 THEN 'S'
           WHEN AVG(SCORE) >= 90 THEN 'A'
           WHEN AVG(SCORE) >= 80 THEN 'B'
           ELSE 'C' END     AS GRADE,
       CASE
           WHEN AVG(SCORE) >= 96 THEN SAL * 0.2
           WHEN AVG(SCORE) >= 90 THEN SAL * 0.15
           WHEN AVG(SCORE) >= 80 THEN SAL * 0.1
           ELSE SAL * 0 END AS BONUS
FROM HR_EMPLOYEES EMP
         INNER JOIN HR_GRADE GRADE
                    ON EMP.EMP_NO = GRADE.EMP_NO
GROUP BY EMP.EMP_NO
ORDER BY EMP.EMP_NO;

# 조건에 맞는 사원 정보 조회하기
WITH SUB AS (SELECT EMP_NO, SUM(SCORE) AS SCORE
             FROM HR_GRADE
             GROUP BY EMP_NO
             ORDER BY SCORE DESC
             LIMIT 1)
SELECT SCORE, EMP.EMP_NO, EMP_NAME, POSITION, EMAIL
FROM HR_EMPLOYEES EMP
         INNER JOIN SUB
                    ON EMP.EMP_NO = SUB.EMP_NO;

# 업그레이드 할 수 없는 아이템 구하기
WITH SUB AS (SELECT T1.PARENT_ITEM_ID AS ID
             FROM ITEM_TREE T1
                      INNER JOIN ITEM_TREE T2
                                 ON T1.PARENT_ITEM_ID = T2.PARENT_ITEM_ID
             GROUP BY T1.PARENT_ITEM_ID)
SELECT *
FROM ITEM_INFO INFO;

SELECT *
FROM ITEM_TREE;

# 업그레이드 할 수 없는 아이템 찾기
SELECT TREE.ITEM_ID, ITEM_NAME, RARITY
FROM ITEM_TREE TREE
         INNER JOIN ITEM_INFO INFO
                    ON TREE.ITEM_ID = INFO.ITEM_ID
WHERE TREE.ITEM_ID NOT IN (SELECT PARENT_ITEM_ID FROM ITEM_TREE WHERE PARENT_ITEM_ID IS NOT NULL)
ORDER BY TREE.ITEM_ID DESC;

# 업그레이드 된 아이템 구하기
SELECT TREE.ITEM_ID, ITEM_NAME, RARITY
FROM ITEM_TREE TREE
         INNER JOIN ITEM_INFO INFO
                    ON TREE.ITEM_ID = INFO.ITEM_ID
WHERE TREE.PARENT_ITEM_ID
          IN (SELECT PARENT_ITEM_ID
              FROM ITEM_TREE TREE
                       INNER JOIN ITEM_INFO INFO
                                  ON TREE.PARENT_ITEM_ID = INFO.ITEM_ID
                                      AND INFO.RARITY = 'RARE'
              WHERE PARENT_ITEM_ID IS NOT NULL)
ORDER BY TREE.ITEM_ID DESC;

# ROOT 아이템 구하기
SELECT TREE.ITEM_ID, ITEM_NAME
FROM ITEM_TREE TREE
         INNER JOIN ITEM_INFO INFO
                    ON TREE.PARENT_ITEM_ID IS NULL
                        AND TREE.ITEM_ID = INFO.ITEM_ID
ORDER BY TREE.ITEM_ID;

# 조건에 맞는 아이템들의 가격 총합
SELECT SUM(PRICE) AS TOTAL_PRICE
FROM ITEM_INFO
WHERE RARITY = 'LEGEND'
GROUP BY RARITY;

# Python 개발자 찾기
SELECT ID, EMAIL, FIRST_NAME, LAST_NAME
FROM DEVELOPER_INFOS
WHERE SKILL_1 = 'Python'
   OR SKILL_2 = 'Python'
   OR SKILL_3 = 'Python'
ORDER BY ID;

# 조건에 부합하는 중고거래 댓글 조회하기
SELECT TITLE,
       BOARD.BOARD_ID,
       REPLY_ID,
       REPLY.WRITER_ID,
       REPLY.CONTENTS,
       DATE_FORMAT(REPLY.CREATED_DATE, '%Y-%m-%d') AS CREATED_DATE
FROM USED_GOODS_BOARD BOARD
         INNER JOIN USED_GOODS_REPLY REPLY
                    ON BOARD.BOARD_ID = REPLY.BOARD_ID
                        AND EXTRACT(YEAR_MONTH FROM BOARD.CREATED_DATE) = '202210'
ORDER BY REPLY.CREATED_DATE, TITLE;

# 조건에 부합하는 중고거래 상태 조회하기
SELECT BOARD_ID,
       WRITER_ID,
       TITLE,
       PRICE,
       CASE
           WHEN STATUS = 'SALE' THEN '판매중'
           WHEN STATUS = 'RESERVED' THEN '예약중'
           ELSE '거래완료' END AS STATUS
FROM USED_GOODS_BOARD
WHERE CREATED_DATE = '2022-10-05'
ORDER BY BOARD_ID DESC;

# 조회수가 가장 많은 중고거래 게시판의 첨부파일 조회하기
SELECT CONCAT('/home/grep/src/', FILE.BOARD_ID, '/', FILE.FILE_ID, FILE.FILE_NAME, FILE.FILE_EXT) AS FILE_PATH
FROM USED_GOODS_FILE FILE
WHERE FILE.BOARD_ID = (SELECT BOARD_ID
                       FROM USED_GOODS_BOARD
                       ORDER BY VIEWS DESC
                       LIMIT 1)
ORDER BY FILE.FILE_ID DESC;

# 조건에 맞는 사용자 정보 조회하기
SELECT USER_ID
     , NICKNAME
     , CONCAT(CITY, ' ', STREET_ADDRESS1, ' ', STREET_ADDRESS2)                              AS 전체주소
     , CONCAT(SUBSTRING(TLNO, 1, 3), '-', SUBSTRING(TLNO, 4, 4), '-', SUBSTRING(TLNO, 8, 4)) AS 전화번호
FROM USED_GOODS_BOARD BOARD
         INNER JOIN USED_GOODS_USER USER
                    ON BOARD.WRITER_ID = USER.USER_ID
GROUP BY WRITER_ID, USER.USER_ID
HAVING COUNT(WRITER_ID) >= 3
ORDER BY USER.USER_ID DESC;

# 조건에 맞는 사용자와 총 거래금액 조회하기
SELECT USER.USER_ID, USER.NICKNAME, SUM(PRICE) AS TOTAL_SALES
FROM USED_GOODS_BOARD BOARD
         INNER JOIN USED_GOODS_USER USER
                    ON BOARD.WRITER_ID = USER.USER_ID
WHERE STATUS = 'DONE'
GROUP BY WRITER_ID
HAVING SUM(PRICE) >= 700000
ORDER BY TOTAL_SALES;

# 특정 옵션이 포함된 자동차 리스트 구하기
SELECT *
FROM CAR_RENTAL_COMPANY_CAR
WHERE OPTIONS LIKE '%네비게이션%'
ORDER BY CAR_ID DESC;

# 자동차 평균 대여 기간 구하기
SELECT CAR_ID, ROUND(AVG(DATEDIFF(END_DATE, START_DATE) + 1), 1) AS AVERAGE_DURATION
FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
GROUP BY CAR_ID
HAVING ROUND(AVG(DATEDIFF(END_DATE, START_DATE) + 1), 1) >= 7
ORDER BY AVERAGE_DURATION DESC, CAR_ID DESC;

# 대여 기록이 존재하는 자동차 리스트 구하기
SELECT DISTINCT(CAR.CAR_ID)
FROM CAR_RENTAL_COMPANY_CAR CAR
         INNER JOIN CAR_RENTAL_COMPANY_RENTAL_HISTORY HISTORY
                    ON CAR.CAR_ID = HISTORY.CAR_ID
                        AND CAR.CAR_TYPE = '세단'
                        AND EXTRACT(MONTH FROM START_DATE) = '10'
ORDER BY CAR.CAR_ID DESC;

# 대여중 / 대여가능 구분하기
WITH SUB AS (SELECT CAR_ID
             FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
             WHERE '2022-10-16' BETWEEN START_DATE AND END_DATE)
SELECT DISTINCT(HISTORY.CAR_ID),
               CASE
                   WHEN SUB.CAR_ID IS NULL THEN '대여 가능'
                   ELSE '대여중' END AS AVAILABILITY
FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY HISTORY
         LEFT OUTER JOIN SUB
                         ON HISTORY.CAR_ID = SUB.CAR_ID
ORDER BY HISTORY.CAR_ID DESC;

# 특정기간동안 대여 가능한 자동차
SELECT CAR.CAR_ID,
       CAR.CAR_TYPE,
       FLOOR((CAR.DAILY_FEE - CAR.DAILY_FEE * 0.01 * PLAN.DISCOUNT_RATE) * 30) AS FEE
FROM CAR_RENTAL_COMPANY_CAR CAR
         INNER JOIN CAR_RENTAL_COMPANY_DISCOUNT_PLAN PLAN
                    ON CAR.CAR_TYPE = PLAN.CAR_TYPE
                        AND PLAN.DURATION_TYPE = '30일 이상'
WHERE CAR.CAR_ID NOT IN (SELECT CAR_ID
                         FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
                         WHERE '202211' BETWEEN EXTRACT(YEAR_MONTH FROM START_DATE)
                                   AND EXTRACT(YEAR_MONTH FROM END_DATE))
  AND FLOOR((CAR.DAILY_FEE - CAR.DAILY_FEE * 0.01 * PLAN.DISCOUNT_RATE) * 30) >= 500000
  AND FLOOR((CAR.DAILY_FEE - CAR.DAILY_FEE * 0.01 * PLAN.DISCOUNT_RATE) * 30) < 2000000
  AND CAR.CAR_TYPE IN ('SUV', '세단')
ORDER BY FEE DESC, CAR.CAR_TYPE, CAR.CAR_ID DESC;

# 자동차 대여 기록 별 대여 금액 구하기
WITH SUB AS (SELECT DATEDIFF(END_DATE, START_DATE) + 1                           AS DATE,
                    PLAN.DURATION_TYPE,
                    CAR.CAR_TYPE,
                    HISTORY.HISTORY_ID
                     ,
                    IF(PLAN.PLAN_ID IS NOT NULL
                        , (CAR.DAILY_FEE - CAR.DAILY_FEE * 0.01 * PLAN.DISCOUNT_RATE) *
                          (DATEDIFF(END_DATE, START_DATE) + 1)
                        , CAR.DAILY_FEE * (DATEDIFF(END_DATE, START_DATE) + 1))  AS FEE,
                    ROW_NUMBER() OVER (PARTITION BY HISTORY_ID ORDER BY IF(PLAN.PLAN_ID IS NOT NULL
                        , (CAR.DAILY_FEE - CAR.DAILY_FEE * 0.01 * PLAN.DISCOUNT_RATE) *
                          (DATEDIFF(END_DATE, START_DATE) + 1)
                        , CAR.DAILY_FEE * (DATEDIFF(END_DATE, START_DATE) + 1))) AS RAN
             FROM CAR_RENTAL_COMPANY_CAR CAR
                      INNER JOIN CAR_RENTAL_COMPANY_RENTAL_HISTORY HISTORY
                                 ON CAR.CAR_ID = HISTORY.CAR_ID
                                     AND CAR.CAR_TYPE = '트럭'
                      LEFT OUTER JOIN CAR_RENTAL_COMPANY_DISCOUNT_PLAN PLAN
                                      ON CAR.CAR_TYPE = PLAN.CAR_TYPE
                                          AND
                                         REPLACE(PLAN.DURATION_TYPE, '일 이상', '') <= DATEDIFF(END_DATE, START_DATE) + 1)
SELECT HISTORY_ID, FLOOR(FEE) AS FEE
FROM SUB
WHERE RAN = 1
ORDER BY FEE DESC, HISTORY_ID DESC;

# 대여 횟수가 많은 자동차
WITH SUB AS (SELECT CAR_ID
             FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
             WHERE START_DATE BETWEEN '2022-08-01' AND '2022-10-31'
             GROUP BY CAR_ID
             HAVING COUNT(CAR_ID) >= 5)
SELECT EXTRACT(MONTH FROM START_DATE) AS MONTH, SUB.CAR_ID, COUNT(*) AS RECORDS
FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY HISTORY
         INNER JOIN SUB
                    ON HISTORY.CAR_ID = SUB.CAR_ID
WHERE EXTRACT(MONTH FROM START_DATE) >= 8
  AND EXTRACT(MONTH FROM START_DATE) <= 10
GROUP BY MONTH, SUB.CAR_ID
ORDER BY MONTH, SUB.CAR_ID DESC;

# 장기/ 단기대여 구분하기
SELECT HISTORY_ID
     , CAR_ID
     , DATE_FORMAT(START_DATE, '%Y-%m-%d')                            AS START_DATE
     , DATE_FORMAT(END_DATE, '%Y-%m-%d')                              AS END_DATE
     , IF(DATEDIFF(END_DATE, START_DATE) + 1 >= 30, '장기 대여', '단기 대여') AS RENT_TYPE
FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
WHERE EXTRACT(MONTH FROM START_DATE) = 9
ORDER BY HISTORY_ID DESC;

# 자동차 종류 별 특정 옵션이 포함된 자동차 수 구하기
SELECT CAR_TYPE, COUNT(*) AS CARS
FROM CAR_RENTAL_COMPANY_CAR
WHERE OPTIONS LIKE '%통풍시트%'
   OR OPTIONS LIKE '%열선시트%'
   OR OPTIONS LIKE '%가죽시트%'
GROUP BY CAR_TYPE
ORDER BY CAR_TYPE

# 평균 일일 요금
SELECT ROUND(AVG(DAILY_FEE), 0) AS AVERAGE_FEE
FROM CAR_RENTAL_COMPANY_CAR
WHERE CAR_TYPE = 'SUV';

# 저자 별 카테고리 별 매출액 집계하기
SELECT AU.AUTHOR_ID, AUTHOR_NAME, CATEGORY, SUM(PRICE * SALES.SALES) AS TOTAL_SALES
FROM AUTHOR AU
         INNER JOIN BOOK BO
                    ON AU.AUTHOR_ID = BO.AUTHOR_ID
         INNER JOIN BOOK_SALES SALES
                    ON BO.BOOK_ID = SALES.BOOK_ID
                        AND EXTRACT(MONTH FROM SALES_DATE) = 1
GROUP BY AU.AUTHOR_ID, CATEGORY
ORDER BY AUTHOR_ID, CATEGORY DESC;

# 카테고리 별 도서 판매량 집계하기
SELECT CATEGORY, SUM(SALES) AS TOTAL_SALES
FROM BOOK_SALES SALES
         INNER JOIN BOOK BO
                    ON SALES.BOOK_ID = BO.BOOK_ID
                        AND EXTRACT(YEAR_MONTH FROM SALES_DATE) = '202201'
GROUP BY CATEGORY
ORDER BY CATEGORY;

# 조건에 맞는 도서와 저자 리스트 출력하기
SELECT BO.BOOK_ID, AUTHOR_NAME, DATE_FORMAT(PUBLISHED_DATE, '%Y-%m-%d') AS PUBLISHED_DATE
FROM BOOK BO
         INNER JOIN AUTHOR AU
                    ON BO.AUTHOR_ID = AU.AUTHOR_ID
WHERE CATEGORY = '경제'
ORDER BY PUBLISHED_DATE;

# 조건에 맞는 도서 리스트 출력하기
SELECT BOOK_ID, DATE_FORMAT(PUBLISHED_DATE, '%Y-%m-%d') AS PUBLISHED_DATE
FROM BOOK
WHERE EXTRACT(YEAR FROM PUBLISHED_DATE) = 2021
  AND CATEGORY = '인문'
ORDER BY PUBLISHED_DATE;

# 주문량이 많은 아이스크림들 조회하기
WITH SUB AS (SELECT SUM(TOTAL_ORDER) AS TOTAL_ORDER, FLAVOR
             FROM JULY
             GROUP BY FLAVOR)
SELECT HALF.FLAVOR
FROM FIRST_HALF HALF
         INNER JOIN SUB
                    ON HALF.FLAVOR = SUB.FLAVOR
ORDER BY HALF.TOTAL_ORDER + SUB.TOTAL_ORDER DESC
LIMIT 3;

# 성분으로 구분한 아이스크림 총 주문량
SELECT INGREDIENT_TYPE, SUM(TOTAL_ORDER) AS TOTAL_ORDER
FROM FIRST_HALF HALF
         INNER JOIN ICECREAM_INFO INFO
                    ON HALF.FLAVOR = INFO.FLAVOR
GROUP BY INGREDIENT_TYPE

# 과일로 만든 아이스크림 고르기
SELECT HALF.FLAVOR
FROM FIRST_HALF HALF
         INNER JOIN ICECREAM_INFO INFO
                    ON HALF.FLAVOR = INFO.FLAVOR
                        AND INGREDIENT_TYPE = 'fruit_based'
                        AND HALF.TOTAL_ORDER >= 3000
ORDER BY TOTAL_ORDER DESC

# 인기있는 아이스크림
SELECT FLAVOR
FROM FIRST_HALF
ORDER BY TOTAL_ORDER DESC, SHIPMENT_ID

# 취소되지 않은 진료 예약 조회하기
SELECT APNT_NO, PT_NAME, PA.PT_NO, DO.MCDP_CD, DO.DR_NAME, APNT_YMD
FROM APPOINTMENT APP
         INNER JOIN PATIENT PA
                    ON APP.PT_NO = PA.PT_NO
         INNER JOIN DOCTOR DO
                    ON DO.DR_ID = APP.MDDR_ID
WHERE EXTRACT(YEAR_MONTH FROM APNT_YMD) = 202204
  AND EXTRACT(DAY FROM APNT_YMD) = 13
  AND APNT_CNCL_YN = 'N'
ORDER BY APNT_YMD;

# 흉부외과 또는 일반외과 의사 목록 출력하기
SELECT DR_NAME, DR_ID, MCDP_CD, DATE_FORMAT(HIRE_YMD, '%Y-%m-%d') AS HIRE_YMD
FROM DOCTOR
WHERE MCDP_CD = 'CS'
   OR MCDP_CD = 'GS'
ORDER BY HIRE_YMD DESC, DR_NAME;

# 진료과별 총 예약 횟수 출력하기
SELECT MCDP_CD AS '진료과코드', COUNT(*) AS '5월예약건수'
FROM APPOINTMENT
WHERE EXTRACT(YEAR_MONTH FROM APNT_YMD) = '202205'
GROUP BY MCDP_CD
ORDER BY COUNT(*), MCDP_CD;

# 12세 이하인 여자 환자 목록 출력하기
SELECT PT_NAME, PT_NO, GEND_CD, AGE, IF(TLNO IS NULL, 'NONE', TLNO) AS TLNO
FROM PATIENT
WHERE AGE <= 12
  AND GEND_CD = 'W'
ORDER BY AGE DESC, PT_NAME;

# 가장 비싼 상품 구하기
SELECT PRICE AS MAX_PRICE
FROM PRODUCT
ORDER BY PRICE DESC
LIMIT 1;

# 오프라인/온라인 판매 데이터 통합하기
WITH SUB AS (SELECT DATE_FORMAT(SALES_DATE, '%Y-%m-%d') AS SALES_DATE, PRODUCT_ID, NULL AS USER_ID, SALES_AMOUNT
             FROM OFFLINE_SALE
             WHERE EXTRACT(YEAR_MONTH FROM SALES_DATE) = 202203)
SELECT DATE_FORMAT(SALES_DATE, '%Y-%m-%d') AS SALES_DATE, PRODUCT_ID, USER_ID, SALES_AMOUNT
FROM ONLINE_SALE
WHERE EXTRACT(YEAR_MONTH FROM SALES_DATE) = 202203
UNION
SELECT *
FROM SUB
ORDER BY SALES_DATE, PRODUCT_ID, USER_ID;

# 재구매가 일어난 상품과 회원 리스트 구하기
SELECT USER_ID, PRODUCT_ID
FROM ONLINE_SALE
GROUP BY USER_ID, PRODUCT_ID
HAVING COUNT(*) >= 2
ORDER BY USER_ID, PRODUCT_ID DESC

# 조건에 맞는 회원수 구하기
SELECT COUNT(*) AS USERS
FROM USER_INFO
WHERE AGE >= 20
  AND AGE <= 29
  AND EXTRACT(YEAR FROM JOINED) = 2021;

# 상품 별 오프라인 매출 구하기
SELECT PRODUCT_CODE, SUM(PRICE * SALE.SALES_AMOUNT) AS SALES
FROM PRODUCT P
         INNER JOIN OFFLINE_SALE SALE
                    ON P.PRODUCT_ID = SALE.PRODUCT_ID
GROUP BY PRODUCT_CODE
ORDER BY SALES DESC, PRODUCT_CODE;

# 년, 월, 성별 별 상품 구매 회원 수 구하기
WITH SUB AS (SELECT EXTRACT(YEAR FROM SALES_DATE)  AS YEAR,
                    EXTRACT(MONTH FROM SALES_DATE) AS MONTH,
                    GENDER
             FROM ONLINE_SALE SALE
                      INNER JOIN USER_INFO USER
                                 ON USER.USER_ID = SALE.USER_ID
             WHERE GENDER IS NOT NULL
             GROUP BY EXTRACT(YEAR FROM SALES_DATE), EXTRACT(MONTH FROM SALES_DATE), GENDER, USER.USER_ID)
SELECT *, COUNT(*) AS USERS
FROM SUB
GROUP BY MONTH, GENDER
ORDER BY YEAR, MONTH, GENDER

# 가격대 별 상품 개수 구하기
# CREATE PROCEDURE sample_procedure()
# BEGIN
#     DECLARE counter INT DEFAULT 10000;
#     WHILE counter <= 100000 DO
#             SELECT *
#             FROM PRODUCT
#             WHERE PRICE < COUNTER;
#             SET counter = counter + 10000;
#         END WHILE;
# END;

SELECT FLOOR(PRICE / 10000) * 10000 AS PRICE_GROUP, COUNT(*) AS PRODUCTS
FROM PRODUCT
GROUP BY FLOOR(PRICE / 10000)
ORDER BY PRICE_GROUP

# 카테고리 별 상품 개수 구하기
SELECT SUBSTRING(PRODUCT.PRODUCT_CODE, 1, 2) AS CATEGORY, COUNT(*) AS PRODUCTS
FROM PRODUCT
GROUP BY SUBSTRING(PRODUCT.PRODUCT_CODE, 1, 2)
ORDER BY CATEGORY

# 나이 정보가 없는 회원 수 구하기
SELECT COUNT(*)
FROM USER_INFO
WHERE AGE IS NULL;

# 그룹별 조건에 맞는 식당 목록
WITH SUB AS (SELECT MEMBER_ID
             FROM REST_REVIEW REVIEW
             GROUP BY REVIEW.MEMBER_ID
             ORDER BY COUNT(*) DESC
             LIMIT 1)
SELECT MEMBER_NAME, REVIEW_TEXT, DATE_FORMAT(REVIEW_DATE, '%Y-%m-%d') AS REVIEW_DATE
FROM MEMBER_PROFILE MEMBER
         INNER JOIN SUB
                    ON MEMBER.MEMBER_ID = SUB.MEMBER_ID
         INNER JOIN REST_REVIEW
                    ON REST_REVIEW.MEMBER_ID = SUB.MEMBER_ID
ORDER BY REVIEW_DATE, REVIEW_TEXT;

# 즐겨찾기가 가장 많은 식당 정보 출력하기
WITH SUB AS (SELECT FOOD_TYPE, MAX(FAVORITES) AS FAV
             FROM REST_INFO
             GROUP BY FOOD_TYPE)
SELECT SUB.FOOD_TYPE, REST_ID, REST_NAME, FAVORITES
FROM REST_INFO REST
         INNER JOIN SUB
                    ON REST.FOOD_TYPE = SUB.FOOD_TYPE
                        AND REST.FAVORITES = SUB.FAV
ORDER BY FOOD_TYPE DESC

# 3월에 태어난 여성 회원 목록 출력하기
SELECT MEMBER_ID, MEMBER_NAME, GENDER, DATE_FORMAT(DATE_OF_BIRTH, '%Y-%m-%d')
FROM MEMBER_PROFILE
WHERE EXTRACT(MONTH FROM DATE_OF_BIRTH) = 3
  AND GENDER = 'W'
  AND TLNO IS NOT NULL
ORDER BY MEMBER_ID;

# 서울에 위치한 식당 목록 출력하기
SELECT REVIEW.REST_ID, REST_NAME, FOOD_TYPE, FAVORITES, ADDRESS, ROUND(AVG(REVIEW_SCORE), 2) AS SCORE
FROM REST_INFO INFO
         INNER JOIN REST_REVIEW REVIEW
                    ON INFO.REST_ID = REVIEW.REST_ID
WHERE ADDRESS LIKE '서울%'
GROUP BY REVIEW.REST_ID
ORDER BY SCORE DESC, FAVORITES DESC;

# 5월 식품들의 총매출 조회하기
WITH SUB AS (SELECT PRODUCT.PRODUCT_ID, PRODUCT_NAME, PRICE * AMOUNT AS TOTAL_SALES
             FROM FOOD_ORDER ORDERS
                      INNER JOIN FOOD_PRODUCT PRODUCT
                                 ON ORDERS.PRODUCT_ID = PRODUCT.PRODUCT_ID
             WHERE EXTRACT(YEAR_MONTH FROM PRODUCE_DATE) = 202205)
SELECT PRODUCT_ID, PRODUCT_NAME, SUM(TOTAL_SALES) AS TOTAL_SALES
FROM SUB
GROUP BY PRODUCT_ID
ORDER BY TOTAL_SALES DESC, PRODUCT_ID;

# 식품분류별 가장 비싼 식품의 정보 조회하기
WITH SUB AS (SELECT CATEGORY, MAX(PRICE) AS MAX_PRICE
             FROM FOOD_PRODUCT
             WHERE CATEGORY IN ('과자', '국', '김치', '식용유')
             GROUP BY CATEGORY)
SELECT SUB.CATEGORY, MAX_PRICE, PRODUCT_NAME
FROM FOOD_PRODUCT PRODUCT
         INNER JOIN SUB
                    ON PRODUCT.CATEGORY = SUB.CATEGORY
                        AND PRODUCT.PRICE = SUB.MAX_PRICE
ORDER BY MAX_PRICE DESC;

# 가격이 제일 비싼 식품의 정보 출력하기
SELECT *
FROM FOOD_PRODUCT
ORDER BY PRICE DESC
LIMIT 1;

# 경기도에 위치한 식품창고 목록 출력하기
SELECT WAREHOUSE_ID, WAREHOUSE_NAME, ADDRESS, IF(FREEZER_YN IS NULL OR FREEZER_YN = 'N', 'N', 'Y') AS FREEZER_YN
FROM FOOD_WAREHOUSE
WHERE ADDRESS LIKE '경기%'
ORDER BY WAREHOUSE_ID;

# 조건별로 분류하여 주문상태 출력하기
SELECT ORDER_ID
     , PRODUCT_ID
     , DATE_FORMAT(OUT_DATE, '%Y-%m-%d')                                                 AS OUT_DATE
     , IF(OUT_DATE <= '2022-05-01', '출고완료', IF(OUT_DATE > '2022-05-01', '출고대기', '출고미정')) AS 출고여부
FROM FOOD_ORDER
ORDER BY ORDER_ID;

# 강원도에 위치한 생산공장 목록 출력하기
SELECT FACTORY_ID, FACTORY_NAME, ADDRESS
FROM FOOD_FACTORY
WHERE ADDRESS LIKE '강원도%'
ORDER BY FACTORY_ID;

# 헤비 유저가 소유한 장소
WITH SUB AS (SELECT HOST_ID
             FROM PLACES
             GROUP BY HOST_ID
             HAVING COUNT(*) >= 2)
SELECT ID, NAME, PLACES.HOST_ID
FROM PLACES
         INNER JOIN SUB
                    ON PLACES.HOST_ID = SUB.HOST_ID
ORDER BY ID;

# 우유와 요거트가 담긴 장바구니
WITH SUB AS (SELECT COUNT(*), CART_ID, NAME
             FROM CART_PRODUCTS
             WHERE NAME IN ('Milk', 'Yogurt')
             GROUP BY CART_ID, NAME)
SELECT CART_ID
FROM SUB
GROUP BY CART_ID
HAVING COUNT(*) >= 2
ORDER BY CART_ID;

# 최댓값 구하기
SELECT DATETIME
FROM ANIMAL_INS
ORDER BY DATETIME DESC
LIMIT 1;

# DATETIME에서 DATE로 형 변환
SELECT ANIMAL_ID, NAME, DATE_FORMAT(DATETIME, '%Y-%m-%d') AS '날짜'
FROM ANIMAL_INS
ORDER BY ANIMAL_ID;

# 입양 시각 구하기(1)
SET @HOUR = 8;
SELECT (@HOUR := @HOUR + 1)           AS HOUR,
       (SELECT COUNT(HOUR(DATETIME))
        FROM ANIMAL_OUTS
        WHERE HOUR(DATETIME) = @HOUR) AS COUNT
FROM ANIMAL_OUTS
WHERE @HOUR < 19;

# 입양 시각 구하기(2)
SET @HOUR = -1;
SELECT (@HOUR := @HOUR + 1)           AS HOUR,
       (SELECT COUNT(HOUR(DATETIME))
        FROM ANIMAL_OUTS
        WHERE HOUR(DATETIME) = @HOUR) AS COUNT
FROM ANIMAL_OUTS
WHERE @HOUR < 23;

# 오랜 기간 보호한 동물(2)
SELECT INS.ANIMAL_ID, INS.NAME
FROM ANIMAL_INS INS
         INNER JOIN ANIMAL_OUTS OUTS
                    ON INS.ANIMAL_ID
                        = OUTS.ANIMAL_ID
ORDER BY DATEDIFF(OUTS.DATETIME, INS.DATETIME) DESC
LIMIT 2;

# NULL 처리하기
SELECT ANIMAL_TYPE, IF(NAME IS NULL, 'No name', NAME), SEX_UPON_INTAKE
FROM ANIMAL_INS;

# 중성화 여부 파악하기
SELECT ANIMAL_ID, NAME, IF(SEX_UPON_INTAKE LIKE 'Neutered%' OR SEX_UPON_INTAKE LIKE 'Spayed%', 'O', 'X') AS '중성화'
FROM ANIMAL_INS
ORDER BY ANIMAL_ID;

# 중복 제거하기
SELECT COUNT(DISTINCT (NAME)) AS COUNT
FROM ANIMAL_INS
WHERE NAME IS NOT NULL;

# 이름이 있는 동물의 아이디
SELECT ANIMAL_ID
FROM ANIMAL_INS
WHERE NAME IS NOT NULL
ORDER BY ANIMAL_ID;

# 동물 수 구하기
SELECT COUNT(*) AS COUNT
FROM ANIMAL_INS

# 상위 n개 레코드
SELECT NAME
FROM ANIMAL_INS
ORDER BY DATETIME
LIMIT 1;

# 여러 기준으로 정렬하기
SELECT ANIMAL_ID, NAME, DATETIME
FROM ANIMAL_INS
ORDER BY NAME, DATETIME DESC

# 동물의 아이디와 이름
SELECT ANIMAL_ID, NAME
FROM ANIMAL_INS

# 이름에 el이 들어가는 동물 찾기
SELECT ANIMAL_ID, NAME
FROM ANIMAL_INS
WHERE NAME LIKE '%EL%'
  AND ANIMAL_TYPE = 'DOG'
ORDER BY NAME

# 루시와 엘라 찾기
SELECT ANIMAL_ID, NAME, SEX_UPON_INTAKE
FROM ANIMAL_INS
WHERE NAME IN ('LUCY', 'ELLA', 'PICKLE', 'ROGAN', 'SABRINA', 'MITTY')

# 보호소에서 중성화한 동물
SELECT INS.ANIMAL_ID, INS.ANIMAL_TYPE, INS.NAME
FROM ANIMAL_INS INS
         INNER JOIN ANIMAL_OUTS OUTS
                    ON INS.ANIMAL_ID = OUTS.ANIMAL_ID
                        AND (OUTS.SEX_UPON_OUTCOME LIKE 'Neutered%' OR OUTS.SEX_UPON_OUTCOME LIKE 'Spayed%')
WHERE INS.SEX_UPON_INTAKE LIKE 'Intact%'
ORDER BY ANIMAL_ID;

# 오랜 기간 보호한 동물(1)
SELECT INS.NAME, INS.DATETIME
FROM ANIMAL_INS INS
         LEFT OUTER JOIN ANIMAL_OUTS OUTS
                         ON INS.ANIMAL_ID = OUTS.ANIMAL_ID
WHERE OUTS.ANIMAL_ID IS NULL
ORDER BY INS.DATETIME
LIMIT 3;

# 있었는데요 없었습니다
SELECT OUTS.ANIMAL_ID, OUTS.NAME
FROM ANIMAL_INS INS
         INNER JOIN ANIMAL_OUTS OUTS
                    ON INS.ANIMAL_ID = OUTS.ANIMAL_ID
                        AND OUTS.DATETIME < INS.DATETIME
ORDER BY INS.DATETIME;

# 없어진 기록 찾기
SELECT OUTS.ANIMAL_ID, OUTS.NAME
FROM ANIMAL_INS INS
         RIGHT OUTER JOIN ANIMAL_OUTS OUTS
                          ON INS.ANIMAL_ID = OUTS.ANIMAL_ID
WHERE INS.ANIMAL_ID IS NULL
ORDER BY ANIMAL_ID;

# 동명 동물 수 찾기
SELECT NAME, COUNT(*) AS COUNT
FROM ANIMAL_INS
WHERE NAME IS NOT NULL
GROUP BY NAME
HAVING COUNT(*) >= 2
ORDER BY NAME;

# 고양이와 개는 몇 마리 있을까
SELECT ANIMAL_TYPE, COUNT(*) AS COUNT
FROM ANIMAL_INS
WHERE ANIMAL_TYPE IN ('CAT', 'DOG')
GROUP BY ANIMAL_TYPE
ORDER BY ANIMAL_TYPE;

# 이름이 없는 동물의 아이디
SELECT ANIMAL_ID
FROM ANIMAL_INS
WHERE NAME IS NULL
ORDER BY ANIMAL_ID;

# 최솟값 구하기
SELECT DATETIME
FROM ANIMAL_INS
ORDER BY DATETIME
LIMIT 1;

# 어린 동물 찾기
SELECT ANIMAL_ID, NAME
FROM ANIMAL_INS
WHERE INTAKE_CONDITION != 'AGED'
ORDER BY ANIMAL_ID;

# 아픈 동물 찾기
SELECT ANIMAL_ID, NAME
FROM ANIMAL_INS
WHERE INTAKE_CONDITION = 'SICK'
ORDER BY ANIMAL_ID;

# 역순 정렬하기
SELECT NAME, DATETIME
FROM ANIMAL_INS
ORDER BY ANIMAL_ID DESC;

# 모든 레코드 조회하기
SELECT *
FROM ANIMAL_INS
ORDER BY ANIMAL_ID;

# 조건에 맞는 개발자 찾기
SELECT ID, EMAIL, FIRST_NAME, LAST_NAME
FROM DEVELOPERS
WHERE SKILL_CODE IN (SELECT DEVELOPERS.SKILL_CODE
                     FROM SKILLCODES
                     WHERE DEVELOPERS.SKILL_CODE & SKILLCODES.CODE = SKILLCODES.CODE
                       AND (NAME = 'PYTHON' OR NAME = 'C#'))
ORDER BY ID;

# FrontEnd 개발자 찾기
SELECT ID, EMAIL, FIRST_NAME, LAST_NAME
FROM DEVELOPERS
WHERE SKILL_CODE IN (SELECT DEVELOPERS.SKILL_CODE
                     FROM SKILLCODES
                     WHERE CATEGORY = 'FRONT END'
                       AND DEVELOPERS.SKILL_CODE & SKILLCODES.CODE = SKILLCODES.CODE)
ORDER BY ID;

# 언어별 개발자 분류하기
WITH SUB AS (SELECT CASE
                        WHEN ID IN (SELECT ID
                                    FROM SKILLCODES
                                             INNER JOIN DEVELOPERS
                                                        ON (CATEGORY = 'FRONT END' OR NAME = 'PYTHON')
                                                            AND
                                                           DEVELOPERS.SKILL_CODE & SKILLCODES.CODE = SKILLCODES.CODE
                                    GROUP BY ID
                                    HAVING COUNT(DISTINCT(CATEGORY)) >= 2) THEN 'A'
                        WHEN ID IN (SELECT ID
                                    FROM SKILLCODES
                                             INNER JOIN DEVELOPERS
                                                        ON NAME = 'C#'
                                                            AND
                                                           DEVELOPERS.SKILL_CODE & SKILLCODES.CODE = SKILLCODES.CODE)
                            THEN 'B'
                        WHEN ID IN (SELECT ID
                                    FROM SKILLCODES
                                             INNER JOIN DEVELOPERS
                                                        ON CATEGORY = 'FRONT END'
                                                            AND
                                                           DEVELOPERS.SKILL_CODE & SKILLCODES.CODE = SKILLCODES.CODE)
                            THEN 'C'
                        ELSE 'D' END GRADE,
                    ID,
                    EMAIL
             FROM DEVELOPERS)
SELECT *
FROM SUB
WHERE GRADE != 'D'
ORDER BY GRADE, ID;

# 연도별 대장균 크기의 편차 구하기
WITH SUB AS(
    SELECT MAX(SIZE_OF_COLONY) AS MAX_SIZE, YEAR(DIFFERENTIATION_DATE) AS YEAR
    FROM ECOLI_DATA
    GROUP BY YEAR(DIFFERENTIATION_DATE)
)
SELECT YEAR, MAX_SIZE - SIZE_OF_COLONY AS YEAR_DEV, ID
FROM ECOLI_DATA ECO
INNER JOIN SUB
WHERE YEAR(ECO.DIFFERENTIATION_DATE) = SUB.YEAR
ORDER BY YEAR, YEAR_DEV;

# 분기별 분화된 대장균의 개체 수 구하기
WITH SUB AS (
    SELECT 1 START, 3 END, '1Q' QUARTER
    UNION
    SELECT 4 START, 6 END, '2Q' QUARTER
    UNION
    SELECT 7 START, 9 END, '3Q' QUARTER
    UNION
    SELECT 10 START, 12 END, '4Q' QUARTER
)
SELECT QUARTER, COUNT(*) AS ECOLI_COUNT
FROM ECOLI_DATA ECO
INNER JOIN SUB
ON MONTH(DIFFERENTIATION_DATE) BETWEEN SUB.START AND SUB.END
GROUP BY QUARTER
ORDER BY QUARTER;

# 대장균의 크기에 따라 분류하기 1
SELECT ID, IF(SIZE_OF_COLONY <= 100 OR SIZE_OF_COLONY IS NULL, 'LOW', IF(SIZE_OF_COLONY > 100 AND SIZE_OF_COLONY <= 1000, 'MEDIUM', 'HIGH')) AS SIZE
FROM ECOLI_DATA
ORDER BY ID;

# 대장균들의 자식의 수 구하기
SELECT ECO1.ID, COUNT(ECO2.PARENT_ID) AS CHILD_COUNT
FROM ECOLI_DATA ECO1
LEFT OUTER JOIN  ECOLI_DATA ECO2
ON ECO1.ID = ECO2.PARENT_ID
GROUP BY ECO1.ID, ECO2.PARENT_ID
ORDER BY ID;

# 상품을 구매한 회원 비율 구하기
WITH SUB AS(
    SELECT  YEAR(SALES_DATE) AS YEAR, MONTH(SALES_DATE) AS MONTH, COUNT(DISTINCT (SALE.USER_ID)) AS PUCHASED_USERS
    FROM USER_INFO USER
             INNER JOIN ONLINE_SALE SALE
                        ON YEAR(USER.JOINED) = 2021
                            AND USER.USER_ID = SALE.USER_ID
    GROUP BY YEAR, MONTH
)
SELECT YEAR,MONTH, SUB.PUCHASED_USERS, ROUND((SUB.PUCHASED_USERS / COUNT(*)), 1) AS PUCHASED_RATIO
FROM USER_INFO, SUB
WHERE YEAR(USER_INFO.JOINED) = 2021
GROUP BY YEAR, MONTH
ORDER BY YEAR, MONTH
