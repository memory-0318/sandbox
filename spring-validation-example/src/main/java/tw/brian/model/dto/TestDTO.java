package tw.brian.model.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author Brian Su <brian.su@tpisoftware.com>
 * @description: Test資訊傳輸物件
 * @date: 2021/1/25
 */
@Data
public class TestDTO {
    @NotNull
    @Length(min = 2, max = 10, message = "{test.userName.length}")
    private String userName;

    @NotNull
    @Length(min = 6, max = 20, message = "{test.account.length}")
    private String account;

    @NotNull
    @Length(min = 10, message = "{test.password.length}")
    private String password;

    @Min(value = 0, message = "{test.weight.min}")
    private Double weight;
}
