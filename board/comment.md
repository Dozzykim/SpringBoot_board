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



1. Reply.java (DTO)
2. Mapper -> xml, java
3. Service -> interface, implements class.