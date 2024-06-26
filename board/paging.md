## 페이지 객체 정의
    - 페이징 처리를 위한 필수값과 수식을 정의

## 페이지 단위로 쿼리 적용
    - MySQL: LIMIT(시작 index, 페이지당 게시글 수)
    
```
    페이지 번호 : 1

    SELECT *
    FROM board
    LIMIT (0, 10)

    => 게시글 목록에서 0번째 index부터 10개의 데이터만 조회
```
```
    페이지 번호 : 5

    SELECT *
    FROM board
    LIMIT (40, 10)

    => 게시글 목록에서 40번째 index부터 10개의 데이터만 조회
```

## 게시글 목록 뷰
- 페이지 단위로 목록 출력
- 페이지 번호 목록 출력 (페이지네이션)

## 페이징 처리 수식

- 현재 페이지 번호      : page
- 페이지 당 게시글 수   : rows
- 노출 페이지 개수      : pageCount
- 데이터 개수           : totalCount

### 필요한 계산
- 시작, 끝 번호 계산
    - 시작 번호: (현재페이지-1 / 노출 페이지 갯수) * 노출 페이지 개수 + 1
    - 시작 번호: ((page-1) / pageCount) * pageCount + 1
        - 현재페이지에 -1 하는 이유 : 현재 페이지가 끝페이지인 경우,
          시작페이지 계산이 해당 노출 페이지의 시작이 아니라 다음 노출페이지의 시작번호가 나옴.

    - 끝 번호: ((page-1) / pageCount + 1 ) * pageCount

    - (page-1) / pageCount) : 몇번대인지 알려줌...

    - 첫 번호 = 1 (항상 고정)
    - 마지막 번호 = (totalCount -1 / pageCount) / rows + 1
        - totalCount에 -1 하는 이유: rows로 딱 떨어지는 경우 +1 하게되면 계산이 잘못됨..

<hr>

### 시작, 끝 번호 계산 예시
### 시작 번호 예시
- 노출 페이지 수: 10
    - 현재 번호: 16 --> 시작 번호: 11
    - 현재 번호: 37 --> 시작 번호: 31
    - 현재 번호: 123 ->  시작 번호: 121

-  노출페이지 수: 15

### 끝 번호 예시
- 노출 페이지 수: 10
    - 현재 번호: 15 --> 끝 번호: 20
    - 현재 번호: 37 --> 끝 번호: 40
    - 현재 번호: 123 -> 끝 번호: 130

### 마지막 번호 예시
- 노출 페이지 수: 
    - 전체 데이터 개수: 1 --> 마지막 번호: 
    - 전체 데이터 개수:  --> 마지막 번호: 
    - 전체 데이터 개수:  --> 마지막 번호: 








