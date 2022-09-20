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

### 4. 실행 화면
<details>
<summary>사용자 관련</summary>

  ### 유저 회원가입 화면


  ![board_user_join](https://user-images.githubusercontent.com/54405892/191193853-3d032e00-a6c0-45a7-aa46-9240f5e11f26.png)

  ![board_user_join_error](https://user-images.githubusercontent.com/54405892/191193941-51a3596a-6eaa-4840-86bb-848217ab5205.png)

  ![board_user_error2](https://user-images.githubusercontent.com/54405892/191194223-3fbfde7f-a92e-4895-91b1-82c6e600ee5f.png)

  회원가입시 아이디, 비밀번호, 닉네임 유효성 검사와 중복확인을 진행하고 검사 완료시 회원가입을 진행, 로그인 페이지로 이동합니다.

  ### 유저 로그인 화면

  ![board_user_login](https://user-images.githubusercontent.com/54405892/191194415-b1800f6a-5777-4cf6-a766-e4ff3d165acd.png)

  ![board_user_login_error](https://user-images.githubusercontent.com/54405892/191194468-b0184032-fe98-48da-a581-3ebf27f01cbb.png)

  로그인 실패시 다시 로그인 페이지로 돌아온 후 아이디 비밀번호 확인 알림을 띄어줍니다.

  로그인 성공시 메인 페이지인 게시글 목록 페이지로 이동합니다.

  ### 회원정보화면
  
  ![board_user_info](https://user-images.githubusercontent.com/54405892/191194545-bd58f5bd-60a6-4aac-abf4-3fe2f4b47c40.png)

  ![board_user_info2](https://user-images.githubusercontent.com/54405892/191194561-6d359dab-d15a-406d-96d2-4a9202d327a6.png)

  비밀번호와 닉네임만 변경 할 수 있고

  변경 버튼을 클릭시 비밀번호와 닉네임을 변경 할 수 있습니다.

  비밀번호 변경과 회원탈퇴 진행 시 현재 비밀번호로 인증을 해야 합니다.

  - 비밀번호 변경

      ![modal1](https://user-images.githubusercontent.com/54405892/191194736-558750ec-06d7-4589-b203-c058efb03188.png)
      
      ![modal2](https://user-images.githubusercontent.com/54405892/191194812-2386f8e6-2983-4adb-86f7-163efd367681.png)
      
      ![modal3](https://user-images.githubusercontent.com/54405892/191194822-b4dd5ca0-d7fa-4557-b6b0-98588679d7c9.png)

      ![modal4](https://user-images.githubusercontent.com/54405892/191194838-53867884-4942-4f59-8ed6-812189553e19.png)

  - 닉네임 변경

      ![modal5](https://user-images.githubusercontent.com/54405892/191194851-ae693948-3ba8-41dc-9fa5-ce3154119872.png)

      ![modal6](https://user-images.githubusercontent.com/54405892/191194847-cbe4c407-97ef-4efc-aa89-431176bac3c4.png)
      
      ![modal7](https://user-images.githubusercontent.com/54405892/191194844-516ee630-36e9-4a42-8c1f-ec91b3fd3b4a.png)


  - 회원 탈퇴
      
      ![회원탈퇴1](https://user-images.githubusercontent.com/54405892/191195204-ccb6e91d-d4ff-46e4-a917-0a8b96639a63.png)
      
      ![회원탈퇴2](https://user-images.githubusercontent.com/54405892/191195211-3e8c4473-7594-49c7-8240-5a3356a46d14.png)
      
      회원 탈퇴시에는 작성했던 게시글과 댓글등 모두 지워집니다.

  ### 작성 게시물
  ![board_writeboard](https://user-images.githubusercontent.com/54405892/191195751-1bc05cf6-3267-4c48-acc3-e6e8d0492afd.png)

  ![board_writeboard2](https://user-images.githubusercontent.com/54405892/191195763-eb1d8214-9203-484d-a7c1-04a6e4dbc7a0.png)

  제목을 누르면 해당 게시글로 이동할 수 있고 선택삭제도 가능합니다.

  삭제를 하기전에 Confirm을 통해 한번 더 물어봅니다.

  ### 작성 댓글
  
  ![board_writereply](https://user-images.githubusercontent.com/54405892/191196004-8779943b-836b-4576-876d-adb9a778d8c6.png)

  ![board_writereply2](https://user-images.githubusercontent.com/54405892/191196016-8f7e376d-c43e-4d27-9fcc-ecef8412e962.png)

  댓글도 게시글과 동일하게 댓글을 누르면 게시글로 이동과 선택삭제가 가능합니다.

  게시글이 삭제 된 경우에는 삭제기능만 가능합니다.

</details>

<details>
<summary>게시판 관련</summary>
<div markdown="1">       

### 게시글 목록

![게시글 전체목록](https://user-images.githubusercontent.com/54405892/191196481-9514e926-aba6-41bb-bb9a-0bf654b25812.png)

메인 페이지인 게시글 목록 페이지입니다.

모든 게시물을 페이징 처리로 조회할 수 있고, 검색기능과 새로운 게시글 등록이 가능합니다

![옵션](https://user-images.githubusercontent.com/54405892/191196598-f30cca0a-e1f7-40b1-8a2c-c99c8968ef6d.png)

검색기능에는 제목, 내용, 닉네임, 제목+내용 4가지 옵션이 있습니다.

### 게시글 작성

![게시글 작성1](https://user-images.githubusercontent.com/54405892/191196678-960c8145-83c5-4539-947d-77fada888cc4.png)

로그인을 한 사용자만 게시글 작성을 할 수 있습니다.

### 게시글 상세보기

![게시글 상세보기1](https://user-images.githubusercontent.com/54405892/191196817-077e6c85-4cea-48d4-abe1-1586465b8a02.png)

![게시글 상세보기2](https://user-images.githubusercontent.com/54405892/191196808-cfb7dcc6-2083-48b9-a913-c133bd918cb1.png)

작성자일 경우 수정과 삭제를 할 수 있는 버튼이 생기고 아닐경우에는 안보이도록 처리했습니다.

### 게시글 수정하기

![게시글 수정1](https://user-images.githubusercontent.com/54405892/191196927-73cb7506-18ee-4c67-91e5-081fde0f1363.png)

![게시글 수정2](https://user-images.githubusercontent.com/54405892/191196934-47ac5704-06e1-40ff-9a3e-16622e19e8b4.png)


수정하기 버튼 클릭시 Confirm으로 수정을 완료할 것인지 물어본 다음 확인 누르면 게시글을 수정합니다.

수정이 완료되거나 취소하기 버튼을 누르면 해당 게시글의 상세보기 화면으로 이동합니다.

### 게시글 삭제하기

![게시글 삭제](https://user-images.githubusercontent.com/54405892/191197124-6a3753ed-dead-4535-bf56-1cffa51838e1.png)

![게시글 삭제후](https://user-images.githubusercontent.com/54405892/191197116-9da3ab3b-08bb-497a-810c-becf5e223fc2.png)

게시글 삭제버튼 클릭 시 Confirm으로 삭제할지 확인하고, 삭제 후 전체 게시글 페이지로 이동합니다.

### 게시글 검색

옵션 선택과 검색어를 통해 게시글을 검색합니다.

검색된 게시글이 많을 경우 페이징 처리된 결과를 볼 수 있습니다.

- 제목 검색
   
   ![제목 검색](https://user-images.githubusercontent.com/54405892/191197227-5e58571a-57d8-43d6-a91c-a91ed4ea57a2.png)

- 내용 검색

    ![내용 검색](https://user-images.githubusercontent.com/54405892/191197234-62debb2f-5801-4655-966a-3563d568e39d.png)

- 닉네임 검색

    ![닉네임 검색](https://user-images.githubusercontent.com/54405892/191197235-23f81381-af40-42f7-8f68-8fde2242e24a.png)

- 제목+내용 검색

    ![제목내용검색](https://user-images.githubusercontent.com/54405892/191197231-91600dcf-b65e-4ab7-a7f2-766ae9cc780c.png)

</div>
</details>

<details>
<summary>댓글 관련</summary>
<div markdown="1">

### 댓글 목록
![댓글 목록](https://user-images.githubusercontent.com/54405892/191197466-785e415b-80b9-45e5-ac3a-510523276bd6.png)
![토글버튼](https://user-images.githubusercontent.com/54405892/191197474-f0a541c6-b820-4845-82e6-b61ac1bf98ef.png)


![댓글 목록.png](README%20md%2066cd8edd49d5452fa7b7a161ce5173e4/%25EB%258C%2593%25EA%25B8%2580_%25EB%25AA%25A9%25EB%25A1%259D.png)

댓글과 대댓글을 띄어주고, 댓글일 경우에는 대댓글을 달 수 있는 버튼을 보여줍니다.

![토글버튼.png](README%20md%2066cd8edd49d5452fa7b7a161ce5173e4/%25ED%2586%25A0%25EA%25B8%2580%25EB%25B2%2584%25ED%258A%25BC.png)

본인이 작성한 댓글과 대댓글일 경우에만 수정과 삭제를 할 수 있는 토글버튼 생깁니다.

### 댓글 작성

![비로그인 댓글.png](README%20md%2066cd8edd49d5452fa7b7a161ce5173e4/%25EB%25B9%2584%25EB%25A1%259C%25EA%25B7%25B8%25EC%259D%25B8_%25EB%258C%2593%25EA%25B8%2580.png)

비로그인 일 경우 로그인이 필요하다는 alert 창 띄어준 후 로그인 페이지로 이동합니다.

![댓글 작성.png](README%20md%2066cd8edd49d5452fa7b7a161ce5173e4/%25EB%258C%2593%25EA%25B8%2580_%25EC%259E%2591%25EC%2584%25B1.png)

댓글 작성을 완료하면 댓글 목록을 다시 출력합니다.

![댓글 작성후.png](README%20md%2066cd8edd49d5452fa7b7a161ce5173e4/%25EB%258C%2593%25EA%25B8%2580_%25EC%259E%2591%25EC%2584%25B1%25ED%259B%2584.png)

### 댓글 수정하기

![댓글 수정.png](README%20md%2066cd8edd49d5452fa7b7a161ce5173e4/%25EB%258C%2593%25EA%25B8%2580_%25EC%2588%2598%25EC%25A0%2595.png)

![댓글 수정2.png](README%20md%2066cd8edd49d5452fa7b7a161ce5173e4/%25EB%258C%2593%25EA%25B8%2580_%25EC%2588%2598%25EC%25A0%25952.png)

![댓글 수정3.png](README%20md%2066cd8edd49d5452fa7b7a161ce5173e4/%25EB%258C%2593%25EA%25B8%2580_%25EC%2588%2598%25EC%25A0%25953.png)

댓글 수정 완료시 댓글 목록을 다시 출력합니다.

![댓글 수정4.png](README%20md%2066cd8edd49d5452fa7b7a161ce5173e4/%25EB%258C%2593%25EA%25B8%2580_%25EC%2588%2598%25EC%25A0%25954.png)

한번에 하나의 댓글만 수정이 가능합니다.

댓글을 수정하던중 다른 댓글을 수정하기 위해서는 수정을 끝마치거나 취소 버튼을 누른 후 수정을 해야 합니다.

![답글.gif](README%20md%2066cd8edd49d5452fa7b7a161ce5173e4/%25EB%258B%25B5%25EA%25B8%2580.gif)

답글 달기 역시 한번에 하나의 댓글에만 답글을 추가 할 수 있습니다.

### 댓글 삭제

![댓글 삭제.png](README%20md%2066cd8edd49d5452fa7b7a161ce5173e4/%25EB%258C%2593%25EA%25B8%2580_%25EC%2582%25AD%25EC%25A0%259C.png)

![댓글삭제2.png](README%20md%2066cd8edd49d5452fa7b7a161ce5173e4/%25EB%258C%2593%25EA%25B8%2580%25EC%2582%25AD%25EC%25A0%259C2.png)

댓글 삭제 시 삭제되었다는 alert창 출력후 댓글 목록을 출력합니다.

삭제된 댓글은 ‘삭제된 댓글입니다.’ 라고 표시되고 댓글이 삭제되어도 대댓글은 확인이 가능합니다.
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
