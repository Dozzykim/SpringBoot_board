-- Active: 1714007450769@@127.0.0.1@3306@spring
-- 댓글
-- * 게시글(board)에 종속된 테이블

CREATE TABLE comment (
    c_no         INT NOT NULL AUTO_INCREMENT PRIMARY KEY,   -- 댓글번호
    board_no        INT NOT NULL,                        -- 종속되는 글번호
    parent_no       INT NOT NULL,                       -- 부모댓글 번호
    writer          VARCHAR(100) NOT NULL,              -- 작성자
    content         TEXT NOT NULL,                      -- 내용
    reg_date        TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,   -- 등록일자
    upd_date        TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP    -- 수정일자
);

-- 댓글 샘플데이터
-- 글번호: 249
INSERT INTO comment(board_no, parent_no, writer, content)
VALUES (249, 0, '김조은', '야야옹')
      ,(249, 0, '김조은', '야야야옹')
      ,(249, 0, '김조은', '야야야야옹')
      ,(249, 0, '김조은', '야야야야야옹')
      ,(249, 0, '김조은', '야야야야야야옹')
      ,(249, 0, '김조은', '냐옹')
;

SELECT *
FROM comment;