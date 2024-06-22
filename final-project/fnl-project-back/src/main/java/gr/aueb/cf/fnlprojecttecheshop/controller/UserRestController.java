package gr.aueb.cf.fnlprojecttecheshop.controller;

import gr.aueb.cf.fnlprojecttecheshop.dto.UserInsertDTO;
import gr.aueb.cf.fnlprojecttecheshop.dto.UserReadOnlyDTO;
import gr.aueb.cf.fnlprojecttecheshop.mapper.UserMapper;
import gr.aueb.cf.fnlprojecttecheshop.model.User;
import gr.aueb.cf.fnlprojecttecheshop.service.IUserService;
import gr.aueb.cf.fnlprojecttecheshop.service.exceptions.UserAlreadyExistsException;
import gr.aueb.cf.fnlprojecttecheshop.validator.UserInsertValidator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@CrossOrigin(origins = "*")
@Controller
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserRestController {

    private final IUserService userService;
    private final UserInsertValidator insertValidator;

    Logger logger = LoggerFactory.getLogger(UserRestController.class);

    @Operation(summary = "Add a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User Created",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UserReadOnlyDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid Input was given", content = @Content),
            @ApiResponse(responseCode = "503", description = "Service Unavailable", content = @Content)
    })
    @PostMapping("/users/add")
    public ResponseEntity<UserReadOnlyDTO> addUser(@Valid @RequestBody UserInsertDTO dto,
                                                   BindingResult bindingResult) {
        insertValidator.validate(dto, bindingResult);
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {
            User user = userService.insertUser(dto);
            UserReadOnlyDTO userReadOnlyDTO = UserMapper.mapToReadOnlyDTO(user);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(userReadOnlyDTO.getId())
                    .toUri();
            return ResponseEntity.created(location).body(userReadOnlyDTO);
        } catch (UserAlreadyExistsException e) {
            return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

}
