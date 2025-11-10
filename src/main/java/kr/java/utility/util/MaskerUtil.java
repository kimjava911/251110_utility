package kr.java.utility.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;

@Component
public class MaskerUtil {
    private final int maskLength; // 설정값을 주입...

    public MaskerUtil(@Value("${mask.length:5}") int maskLength) {
        this.maskLength = maskLength;
    }

    public String maskEmail(String email) {
        Assert.hasText(email, "email 필수");
        int at = email.indexOf('@'); // *@...
        if (at <= 1) return email; // 너무 짧으면 리턴
        String user = email.substring(0, at);
        String domain = email.substring(at);
        if (!StringUtils.hasText(user)) return email;
        String head = user.substring(0, Math.min(maskLength, user.length()));
        return head + "***" + domain;
    }
}
