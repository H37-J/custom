### NESTED LOOP JOIN

인덱스에 의한 랜덤 액세스에 기반, 즉 대량의 데이터 처리에는 적합하지 않음
Driving Table 은 데이터가 적거나 where 절 조건으로 row 의 숫자를 줄일 수 있는 테이블이어야 한다.
Driven Table 에는 조인을 위한 적절한 인덱스가 생성되어 있어야 한다.
선행 테이블의 결과를 통해 후행 테이블을 액세스 할 때 랜덤 I/O가 발생한다.

### **Driving Table, Driven Table

Driving Table 은 특정 두 테이블을 조회 할때 먼저 엑세스 되는 테이블이다, 나중에 액세스 되는 테이블은 
Driven Table 이라고 한다.**



