package tw.brian.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tw.brian.model.dto.UserDTO;

import javax.validation.constraints.Size;

/**
 * @author Brian Su <brian.su@tpisoftware.com>
 * @description:
 * @date: 2021/1/25
 */
@RequestMapping("/simple-usage")
@RestController
public class SimpleUsageController {
    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addUser(@RequestBody @Validated UserDTO userDTO) {
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "{userId}")
    public ResponseEntity<Void> getUser(
        @PathVariable("userId")
        @Size(min = 1, max = 5, message = "The length of userId must be ranged between {min} and {max}") String userId) {
        return ResponseEntity.ok().build();
    }
}
