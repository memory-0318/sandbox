package tw.brian.jasypt.manager;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.encrypt.BytesEncryptor;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Optional;

/**
 * @author Brian Su <brian.su@tpisoftware.com>
 * @description: 加解密模組，主要處理資料可逆加、解密處理，以及密碼不可逆加密處理
 * 若Java JDK版本為Java 8 Update 161前，可能須額外安裝或設定JCE
 * 安裝與設定請參考 https://stackoverflow.com/questions/3862800/invalidkeyexception-illegal-key-size
 * @date: 2020/7/24
 */
@Component
public class EncryptionManager {

    private final BytesEncryptor encryptor;
    private final PasswordEncoder passwdEncoder = new BCryptPasswordEncoder();

    public EncryptionManager(
        @Value("${encrypted.password}") String passwd,
        @Value("${encrypted.salt}") String salt) {
        this.encryptor = Encryptors.stronger(passwd, salt);
    }

    public String encrypt(String valToEncrypt) {
        return Optional.ofNullable(valToEncrypt)
            .map(val -> {
                byte[] byteToEncrypt = val.getBytes(StandardCharsets.UTF_8);
                byte[] encryptByte = this.encryptor.encrypt(byteToEncrypt);
                return Base64.encodeBase64String(encryptByte);
            })
            .orElse(null);
    }

    public String decrypt(String valToDecrypt) {
        return Optional.ofNullable(valToDecrypt)
            .map(val -> {
                byte[] byteToDecrypt = val.getBytes(StandardCharsets.UTF_8);
                byte[] decryptByte = this.encryptor.decrypt(Base64.decodeBase64(byteToDecrypt));
                return new String(decryptByte, StandardCharsets.UTF_8);
            })
            .orElse(null);
    }

    public byte[] encrypt(byte[] byteArrayToEncrypt) {
        return Optional.ofNullable(byteArrayToEncrypt)
            .map(byteArray -> Base64.encodeBase64(this.encryptor.encrypt(byteArrayToEncrypt)))
            .orElse(null);
    }

    public byte[] decrypt(byte[] byteArrayToDecrypt) {
        return Optional.ofNullable(byteArrayToDecrypt)
            .map(byteArray -> this.encryptor.decrypt(Base64.decodeBase64(byteArrayToDecrypt)))
            .orElse(null);
    }

    public String encryptPassword(String passwordToEncrypt) {
        return Optional.ofNullable(passwordToEncrypt)
            .map(password -> this.passwdEncoder.encode(passwordToEncrypt))
            .orElse(null);
    }

    public boolean isPasswordMatch(String rawPassword, String encodedPassword) {
        if (StringUtils.isNoneBlank(rawPassword, encodedPassword)) {
            return this.passwdEncoder.matches(rawPassword, encodedPassword);
        } else {
            return false;
        }
    }
}
