package gift.converter;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import org.springframework.core.convert.converter.Converter;

public class StringToUrlConverter implements Converter<String, URL> {

    @Override
    public URL convert(String source) {
        try {
            return new URI(source).toURL();
        } catch (MalformedURLException | URISyntaxException e) {
            throw new IllegalArgumentException("유효하지 않은 URL 형식입니다.");
        }
    }
}
