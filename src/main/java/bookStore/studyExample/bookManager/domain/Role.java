package bookStore.studyExample.bookManager.domain;

import org.springframework.security.core.GrantedAuthority;

//POJO-перечисление, для ролей
public enum Role implements GrantedAuthority {
    USER, ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
