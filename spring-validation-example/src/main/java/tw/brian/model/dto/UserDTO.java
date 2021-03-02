package tw.brian.model.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author Brian Su <brian.su@tpisoftware.com>
 * @description: User資訊傳輸物件
 * @date: 2021/1/25
 */
@Data
public class UserDTO {
    @NotNull
    @Length(min = 2, max = 10, message = "The length of username(${validatedValue}) must be ranged between {min} and {max}")
    private String userName;

    @NotNull
    @Length(min = 6, max = 20, message = "The length of account(${validatedValue}) must be ranged between {min} and {max}")
    private String account;

    @NotNull
    @Length(min = 10, message = "The length of input password must be greater than {min}")
    private String password;

    @Min(value = 0, message = "The weight（${formatter.format('%5.2f', validatedValue)}) must be greater than {value}")
    private Double weight;
}
