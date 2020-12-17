package tw.brian.jasypt;

/**
 * @author Brian Su <brian.su@tpisoftware.com>
 * @description:
 * @date: 2020/12/11
 */
public enum CommandType {
    ENCRYPT_ISD_USER("encrypt-isd-user"),
    DECRYPT_ISD_USER("decrypt-isd-user"),
    TEST_ENCRYPT_ISD_USER("test-encrypt-isd-user"),

    ENCRYPT_USER_INFO_APP("encrypt-user-info-app"),
    DECRYPT_USER_INFO_APP("decrypt-user-info-app"),
    TEST_ENCRYPT_USER_INFO_APP("test-encrypt-user-info-app"),

    ENCRYPT_USER_INFO_NEAR("encrypt-user-info-near"),
    DECRYPT_USER_INFO_NEAR("decrypt-user-info-near"),
    TEST_ENCRYPT_USER_INFO_NEAR("test-encrypt-user-info-near");

    private final String commandName;

    CommandType(String commandName) {
        this.commandName = commandName;
    }

    public String getCommandName() {
        return commandName;
    }
}
