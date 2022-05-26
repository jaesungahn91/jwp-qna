package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static qna.domain.UserTest.JAVAJIGI;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @DisplayName("User 저장")
    @Test
    void save() {
        final User actual = userRepository.save(new User("javajigi", "password", "name", "javajigi@slipp.net"));

        assertThat(actual.getId()).isNotNull();
    }

    @DisplayName("User id로 User 조회")
    @Test
    void findByUserId() {
        final User expected = userRepository.save(new User("javajigi", "password", "name", "javajigi@slipp.net"));

        Optional<User> actual = userRepository.findByUserId(expected.getUserId());

        assertThat(actual).isPresent();
        assertThat(actual.get()).isEqualTo(expected);
    }


    @DisplayName("User 수정")
    @Test
    void updateUser() {
        final User user = userRepository.save(new User("javajigi", "password", "name", "javajigi@slipp.net"));
        userRepository.flush();

        String expected = "자바지기";
        user.changeName(expected);
        Optional<User> actual = userRepository.findByName(expected);

        assertThat(actual).isPresent();
        assertThat(actual.get().getName()).isEqualTo(expected);
    }

}