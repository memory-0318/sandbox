package tw.brian.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tw.brian.model.dto.TestDTO;

/**
 * @author Brian Su <brian.su@tpisoftware.com>
 * @description:
 * @date: 2021/1/25
 */
@RequestMapping("/resource-bundle-example")
@RestController
public class ResourceBundleExampleController {
    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addUser(@RequestBody @Validated TestDTO testDTO) {
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateUser(@RequestBody @Validated TestDTO testDTO) {
        return ResponseEntity.ok().build();
    }
}
