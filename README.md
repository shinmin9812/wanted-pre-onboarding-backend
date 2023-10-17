# 원티드 프리온보딩 백엔드 인턴십 챌린지

## 지원자
### 신민석

---
## 사용 언어 및 프레임워크, DB

### Java & Spring, MySQL

---
## 코드 & 커밋 컨벤션
### 코드 컨벤션
- 파일 이름 : 대문자로 시작, 카멜 케이스
- 패키지 이름 : 소문자
- 함수는 동사형태
- 코드 들여쓰기 및 공백은 IntelliJ 기본 정렬 사용

### 커밋 컨벤션
![commit_convention](https://github.com/shinmin9812/wanted-pre-onboarding-backend/assets/83534757/598cf417-a8ee-4065-926e-d8f807571fce)


---
## 요구사항 분석
### ☆ 요구사항 
#### 1. 채용공고를 등록합니다. (회사는 채용공고를 등록합니다.)
#### 2. 채용공고를 수정합니다. (회사는 채용공고를 수정합니다.(회사 id 이외 모두 수정 가능합니다.))
#### 3. 채용공고를 삭제합니다. (DB에서 삭제됩니다.)
#### 4. 채용공고 목록을 가져옵니다. (사용자는 채용공고 목록을 확인할 수 있습니다.)
#### 4-2. 채용공고 검색 기능 구현
#### 5. 채용 상세 페이지를 가져옵니다. (채용내용 추가, 해당 회사가 올린 다른 채용공고 포함)
#### 6. 채용공고에 지원합니다. (사용자는 1회 채용공고에 지원합니다.)

### ☆ 요구사항 분석
#### - 채용공고를 기반으로 한 CRUD 프로젝트
#### - 사용자, 회사, 채용공고, 지원현황 총 4개의 테이블 필요
#### - 채용공고 목록 조회 시 회사 정보까지 포함하여 조회
#### - 채용공고 상세 조회 시 회사 정보와 채용공고 내용(content)까지 포함하여 조회
#### - 채용공고 검색 시 키워드 하나로 회사명 또는 사용기술 검색
#### - 채용공고 지원 시 사용자가 해당 채용공고를 지원했는지 지원여부 파악 필요

---
## 구현 과정
### ER 모델 작성
![wanted_db](https://github.com/shinmin9812/wanted-pre-onboarding-backend/assets/83534757/706c79a5-989f-4216-9fa2-e09a405b44f7)
#### - 한 회사는 여러 개의 채용공고를 가질 수 있다.
#### - 한 사용자는 여러 채용공고에 지원할 수 있고, 한 채용공고는 여러 사용자를 지원받을 수 있다. (단, 사용자는 이미 지원한 채용공고에는 지원할 수 없다.)

### 도메인
 Apply, Company, Member, Recruit
### 레포지토리
 Apply, Member, Recruit
### 서비스
 Apply, Recruit 
### 컨트롤러
 Apply, Recruit
### DTO
 RecruitRequestDto(채용공고 등록, 수정), RecruitCompanyResponseDto(채용공고 목록 조회, 검색), RecruitResponseDto(채용공고 상세 조회)
### 예외처리
 ExistsApplyException(중복 지원 시), GlobalControllerExceptionHandler, HttpErrorInfo, NotFoundException(해당 엔티티가 없을 때)
### 테스트
 ApplyControllerTest, RecruitControllerTest, ApplyServiceTest, RecruitServiceTest

### API 명세
| HTTP 메소드    | URI            | 설명  |                                                 |
|-------------|----------------|-----|-------------------------------------------------|
| **Recruit** |||                                                 |
| POST        | /recruits/create | 채용공고 등록 | RequestBody(body)                               |
| PATCH       | /recruits/{recruit_id} | 채용공고 수정 | PathVariable(id), RequestBody(body)             |
| DELETE      | /recruits/{recruit_id} | 채용공고 삭제 | PathVariable(id)                                |
| GET         | /recruits      | 채용공고 목록 조회 |                                                 |
| GET         | /recruits/{recruit_id} | 채용공고 상세 조회 | PathVariable(id)                                |
| GET         | /recruits/search | 채용공고 검색 | RequestParam(keyword)                           |
| **Apply**   |           |     |                                                 |
| GET         | /applies       | 채용공고 지원 | RequestParam(memberId), RequestParam(recruitId) |

### 실행 시 필요한 파일
- resource 밑에 application-db.properties 파일 생성 후 사용해야 합니다!
```
# DB 이름 wanted
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/wanted?useSSL=false&useUnicode=true&serverTimezone=Asia/Seoul
spring.datasource.username=root
spring.datasource.password= MySQL 비밀번호
```