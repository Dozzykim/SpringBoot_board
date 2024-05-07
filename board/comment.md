# 댓글
💻 게시글 조회 화면에서 사용
    - /board/read.html

[댓글 요청 경로 매핑]
CommentController

1. 특정 게시글에 종속된 댓글 목록
    🔗[GET] - /cmmt/{boardNo}
    응답    1. 댓글 목록 데이터를 JSON형식으로 받아오기
            2. 댓글 목록을 처리하는 뷰 HTML을 응답
            3. 
2. 댓글 등록
    🔗[POST] - /cmmt 
3. 댓글 수정
    🔗[PUT] - /cmmt
4. 댓글 삭제
    🔗[DELETE] - /cmmt


[추가 작업]
- 댓글 삭제 확인
- 게시글 종속 삭제
    * 외래키 옵션 지정( ON DELETE CASCADE)
    * 서비스 로직


# 답글
1. 댓글 등록
    - c_no:           AI(자동 증가)
    - parent_no:    no (댓글 번호)

2. 답댓 등록
    - c_no:           AI(자동 증가)
    - parent_no:    부모 댓글의 번호

3. 정렬
    ORDER BY parent_no ASC,
             reg_date ASC

4. UI
    - c_no != parent_no (답글) :  들여쓰기 여백주기

5. 답글 삭제
    - c_no 기준으로 삭제요청
        - 종속된 대댓도 삭제: parent가 삭제요청한 c_no인 답글들을 같이 삭제
        ```
        DELETE FROM comment
        WHERE parent_no = #{parentNo}
        ```


1. Reply.java (DTO)
2. Mapper -> xml, java
3. Service -> interface, implements class.