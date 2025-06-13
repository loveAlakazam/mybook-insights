package mybook_insight.io.mybook_insight.application.api.book;

import lombok.AllArgsConstructor;
import mybook_insight.io.mybook_insight.domain.book.BookService;
import mybook_insight.io.mybook_insight.domain.common.BusinessException;
import mybook_insight.io.mybook_insight.domain.common.ErrorCodes;
import mybook_insight.io.mybook_insight.domain.user.UserLoginCommand;
import mybook_insight.io.mybook_insight.domain.user.UserLoginInfo;
import mybook_insight.io.mybook_insight.domain.user.UserRole;
import mybook_insight.io.mybook_insight.domain.user.UserService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookFacade {
    private final UserService userService;
    private final BookService bookService;

    // 관리자 유저가 책을 등록한다.
    public void registerBook() {
        String email="";
        String rawPassword="";
        // 로그인된 유저인지 확인 -> 추후 액세스토큰 필터로 처리
        UserLoginInfo userLoginInfo = userService.login(UserLoginCommand.of(email, rawPassword ) );

        // 유저 관리자 역할 확인
        if(userLoginInfo.getRole() != UserRole.ADMIN) {
            throw new BusinessException(ErrorCodes.ACCESS_DENIED);
        }
        // 책 등록
//        bookService.registerBook();
    }

    // 관리자 유저가 책을 수정한다.
    public void updateBookInformation() {
        // 유저 관리자 역할 확인
        // 책 수정 로직
    }

    // 유저(관리자유저, 일반유저) 가 책을 조회한다.
    public void getBookInformation() {
        // 로그인된 유저인지 확인
        // 책 조회 로직
    }
    // 유저(관리자유저, 일반유저) 가 책 리스트 조회 & 검색한다
    public void getBookList() {
        // 로그인된 유저인지 확인
        // 책 리스트 조회 & 검색 로직
    }

    // 관리자 유저가 책을 삭제한다.
    public void deleteBook() {
        // 책 삭제 로직
    }

}
