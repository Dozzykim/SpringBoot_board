# 액션
- 조회수
    : 단순히 게시글 조회 시, 조회 수 증가

    ```
        UPDATE board
            SET views = views + 1
        WHERE no = #{no}

    ```

- 조회수/좋아요/싫어요/하트
    board   10번글  view        김조은  2024-01-05
    board   10번글  view        김조은  2024-01-03
    board   10번글  view        김조은  2024-01-01
    board   10번글  like        김조은
    board   10번글  dislike     김조은
    board   10번글  heart       김조은