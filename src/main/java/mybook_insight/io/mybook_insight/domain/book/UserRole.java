package mybook_insight.io.mybook_insight.domain.book;

public enum UserRole {
    GENERAL("일반사용자"),   // 일반회원
    ADMIN("관리자");       // 관리자회원

    private final String description;
    UserRole(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
