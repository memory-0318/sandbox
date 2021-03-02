package tw.brian.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tw.brian.model.dto.ListTestDTO;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author Brian Su <brian.su@tpisoftware.com>
 * @description:
 * @date: 2021/2/14
 */
@RequestMapping("/list-validation-example")
@RestController
@Validated
public class ListValidationExampleController {
    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addUser(
        @RequestBody @NotNull(message = "input should not be null") List<@Valid ListTestDTO> testDTOList) {
        return ResponseEntity.ok().build();
    }
}
