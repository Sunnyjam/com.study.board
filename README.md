## 개발환경
1. IDE:Intellij IDEACommunity
2. Spring Boot
3. mySQL
4. Spring Data JPA
5. Thymeleaf

________________________

## 게시판 주요기능
1. 게시글 작성폼(/board/write)
2. 게시글 작성처리(/board/writepro)
3. 게시글 리스트(/board/list) 
4. 게시글 수정(/board/modify/{id})
    - 게시글 리스트 상세페이지에서 수정버튼 클릭
    - 서버에서 해당 게시글의 정보를 가지고 수정 화면 출력
    - 제목과 내용을 수정 입력 받아서 서버로 요청
5. 게시글 수정 처리(/board/update/{id})
    - 수정을 입력 받은 후 게시물 리스트 화면으로 출력
6. 글삭제(/board/delete)
7. 게시글 작성 시 메세지 띄우기(/board/writepro)
   - 게시글 작성 완료시 "글 작성이 완료되었습니다." 메세지를 띄운다. 
   - 리스트 화면으로 이동("searchUrl", "/board/list")
8. 게시판에서 파일 업로드
   - 파일의 이름을 저장하기 위해 컬럼을 사용
   - 파일에 저장된 경로를 지정한다.(경로: projectPath, 파일명의 변수명: file)
   - 업로드한 파일 다운받기(th:href="@{${board.filepath}}")
9. 페이징처리(/board/paging) (0-> 1페이지)
- 게시물의 정렬 순서(가장 마지막에 쓴 게시물은 맨 위로)
- 변수 사용
  - nowPage(현재페이지)
  - startPage(블럭에서 보여줄 시작 페이지)
  - endPage(블럭에서 보여줄 마지마 페이지)
- 클릭 한 페이지 앞으로 4개, 뒤로 5개가 출력된다.(클릭한 페이지는 빨간색)
10. 검색 기능(JPA Repository)
- 정확하게 키워드가 일치하는 데이터만 검색
    - finfBy(컬럼이름)은 컬럼에서 키워드를 넣어서 찾아준다.
- 키워드가 포함된 모든 데이터 검색
    - findBy(컬럼이름)Containig은 컬럼에서 키워드가 포함된 것을 찾아준다.
- searchKeyword한 부분에서 다음 페이지로 넘겼을 경우 게시물리스트의 다음페이지가 아닌
  searchKeyword한 페이지의 다음페이지도 이동한다.

   (searchKeyword = ${param.searchKeyword})
    
