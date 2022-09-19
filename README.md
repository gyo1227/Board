# README.md

# 목차

- [프로젝트 소개](#프로젝트-소개)
- [프로젝트 기능](#프로젝트-기능)
- [사용 기술](#사용-기술)
    - [프론트엔드](#프론트엔드)
    - [백엔드](#백엔드)
- [실행 화면](#실행-화면)
- [구조 및 설계](#구조-및-설계)
    - [패키지 구조](#패키지-구조)
    - [Database 설계](#database-설계)
    - [API 설계](#api-설계)

## 프로젝트 소개

이번 프로젝트를 시작하게 된 계기는 스프링을 한번 배운적이 있지만 다시 공부를 해보기 위해 시작하게 되었습니다.

혼자서 프로젝트 세팅부터 하게된 첫 프로젝트라 공부가 많이 된 프로젝트라고 생각합니다.

## 프로젝트 기능

- 사용자 - 회원가입 및 로그인, 회원 정보 수정, 회원탈퇴, 아이디와 닉네임 중복검사,
    
    작성한 게시글/댓글 관리
    
- 게시판 - CURD 기능, 조회수, 검색기능과 페이징
- 댓글 - CRUD 기능

## 사용 기술

- #### 프론트엔드
    - HTML/CSS
    - JavaScript
    - jQuery
    - Bootstrap

- #### 백엔드
    
    프레임워크 / 라이브러리
    
    - Java 8
    - Spring framework 5.3.22
    - MyBatis
    
    DataBase
    
    - MySQL 8.0.29
    

## 실행 화면

<details>
<summary>사용자 관련</summary>
<div markdown="1">       

</div>
</details>

<details>
<summary>게시판 관련</summary>
<div markdown="1">       

</div>
</details>

<details>
<summary>댓글 관련</summary>
<div markdown="1">       

</div>
</details>

## 구조 및 설계
- #### 패키지 구조
    <details>
    <summary>여기를 눌러주세요</summary>
    <div markdown="1">

    ```
    src
    ┣ main
    ┃ ┣ java
    ┃ ┃ ┗ com
    ┃ ┃ ┃ ┗ example
    ┃ ┃ ┃ ┃ ┗ board
    ┃ ┃ ┃ ┃ ┃ ┣ controller
    ┃ ┃ ┃ ┃ ┃ ┃ ┣ BoardController.java
    ┃ ┃ ┃ ┃ ┃ ┃ ┣ HomeController.java
    ┃ ┃ ┃ ┃ ┃ ┃ ┣ ReplyController.java
    ┃ ┃ ┃ ┃ ┃ ┃ ┗ UserController.java
    ┃ ┃ ┃ ┃ ┃ ┣ dao
    ┃ ┃ ┃ ┃ ┃ ┃ ┣ BoardDAO.java
    ┃ ┃ ┃ ┃ ┃ ┃ ┣ BoardDAOImpl.java
    ┃ ┃ ┃ ┃ ┃ ┃ ┣ ReplyDAO.java
    ┃ ┃ ┃ ┃ ┃ ┃ ┣ ReplyDAOImpl.java
    ┃ ┃ ┃ ┃ ┃ ┃ ┣ UserDAO.java
    ┃ ┃ ┃ ┃ ┃ ┃ ┗ UserDAOImpl.java
    ┃ ┃ ┃ ┃ ┃ ┣ dto
    ┃ ┃ ┃ ┃ ┃ ┃ ┣ BoardDTO.java
    ┃ ┃ ┃ ┃ ┃ ┃ ┣ PageDTO.java
    ┃ ┃ ┃ ┃ ┃ ┃ ┣ ReplyDTO.java
    ┃ ┃ ┃ ┃ ┃ ┃ ┗ UserDTO.java
    ┃ ┃ ┃ ┃ ┃ ┗ service
    ┃ ┃ ┃ ┃ ┃ ┃ ┣ BoardService.java
    ┃ ┃ ┃ ┃ ┃ ┃ ┣ BoardServiceImpl.java
    ┃ ┃ ┃ ┃ ┃ ┃ ┣ ReplyService.java
    ┃ ┃ ┃ ┃ ┃ ┃ ┣ ReplyServiceImpl.java
    ┃ ┃ ┃ ┃ ┃ ┃ ┣ UserService.java
    ┃ ┃ ┃ ┃ ┃ ┃ ┗ UserServiceImpl.java
    ┃ ┣ resources
    ┃ ┃ ┣ mappers
    ┃ ┃ ┃ ┣ BoardMapper.xml
    ┃ ┃ ┃ ┣ ReplyMapper.xml
    ┃ ┃ ┃ ┗ UserMapper.xml
    ┃ ┃ ┗ log4j.xml
    ┃ ┗ webapp
    ┃ ┃ ┣ resources
    ┃ ┃ ┃ ┗ css
    ┃ ┃ ┃ ┃ ┣ board.css
    ┃ ┃ ┃ ┃ ┣ main.css
    ┃ ┃ ┃ ┃ ┗ user.css
    ┃ ┃ ┗ WEB-INF
    ┃ ┃ ┃ ┣ spring
    ┃ ┃ ┃ ┃ ┣ appServlet
    ┃ ┃ ┃ ┃ ┃ ┗ servlet-context.xml
    ┃ ┃ ┃ ┃ ┗ root-context.xml
    ┃ ┃ ┃ ┣ views
    ┃ ┃ ┃ ┃ ┣ ajax
    ┃ ┃ ┃ ┃ ┃ ┗ replyAjax.jsp
    ┃ ┃ ┃ ┃ ┣ board
    ┃ ┃ ┃ ┃ ┃ ┣ list.jsp
    ┃ ┃ ┃ ┃ ┃ ┣ update.jsp
    ┃ ┃ ┃ ┃ ┃ ┣ view.jsp
    ┃ ┃ ┃ ┃ ┃ ┗ write.jsp
    ┃ ┃ ┃ ┃ ┣ includes
    ┃ ┃ ┃ ┃ ┃ ┣ footer.jsp
    ┃ ┃ ┃ ┃ ┃ ┗ header.jsp
    ┃ ┃ ┃ ┃ ┗ user
    ┃ ┃ ┃ ┃ ┃ ┣ boardList.jsp
    ┃ ┃ ┃ ┃ ┃ ┣ info.jsp
    ┃ ┃ ┃ ┃ ┃ ┣ join.jsp
    ┃ ┃ ┃ ┃ ┃ ┣ login.jsp
    ┃ ┃ ┃ ┃ ┃ ┗ replyList.jsp
    ┃ ┃ ┃ ┗ web.xml
    ┗ test
    ┃ ┗ resources
    ┃ ┃ ┗ log4j.xml
    ```

    </div>
    </details>


- #### Database 설계

    ![board_erd](https://user-images.githubusercontent.com/54405892/190950886-7567a4ef-304a-43ba-bd41-b014186cb15e.png)
    
    ![board_users_table](https://user-images.githubusercontent.com/54405892/190950940-88ef428d-cde5-44d8-ae27-d8d95d3241e0.png)
    
    ![board_board_table](https://user-images.githubusercontent.com/54405892/190950958-0408e0be-1b9d-4e78-aabb-71dd9b8c122b.png)
    
    ![board_reply_table](https://user-images.githubusercontent.com/54405892/190950990-eaf332e0-1a5c-4780-ad00-3ef52d1b7d7d.png)

- #### API 설계

    ![user_api](https://user-images.githubusercontent.com/54405892/190951087-45a062c8-54a1-4079-b8bc-25dd72d4e535.png)
    
    ![board_api](https://user-images.githubusercontent.com/54405892/190951089-ab947ac4-424a-4b01-a0dd-eb81319eeb13.png)
    
    ![reply_api](https://user-images.githubusercontent.com/54405892/190951085-6a76d968-7018-4518-a296-a6fff9597483.png)
