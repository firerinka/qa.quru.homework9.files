package quru.qa;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import quru.qa.domain.Attach;
import quru.qa.domain.Email;

import java.io.IOException;
import java.io.InputStream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class JsonParseTest {
    ClassLoader classLoader = ArchiveParseTest.class.getClassLoader();

    @Test
    void jsonTest() throws IOException {
        InputStream is = classLoader.getResourceAsStream("email.json");
        ObjectMapper objectMapper = new ObjectMapper();
        Email email = objectMapper.readValue(is, Email.class);

        assertThat(email.getSender()).isEqualTo("test@gmail.com");
        assertThat(email.getReceivers())
                .isEqualTo(new String[] {
                        "test1@gmail.com",
                        "test2@gmail.com",
                        "test3@gmail.com"}
                );
        assertThat(email.getSubject()).isEqualTo("test subject");
        assertThat(email.getAttaches().length).isEqualTo(2);
        assertThat(email.IsSpam()).isEqualTo(false);
        assertThat(email.getText()).isEqualTo("Hello! Have a nice day!");

        Attach[] attaches = email.getAttaches();
        assertThat(attaches[0].getId()).isEqualTo(1);
        assertThat(attaches[0].getFileName()).isEqualTo("image1.png");
        assertThat(attaches[0].getSize()).isEqualTo(1000);
        assertThat(attaches[1].getId()).isEqualTo(2);
        assertThat(attaches[1].getFileName()).isEqualTo("text1.txt");
        assertThat(attaches[1].getSize()).isEqualTo(200);
    }
}
