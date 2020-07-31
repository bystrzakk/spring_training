package test.demo.spring.core.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookReservationDto {
    @NotEmpty
    @NotNull
    private String bookIsbn;
    @NotEmpty
    @NotNull
    private String firstName;
    @NotEmpty
    @NotNull
    private String lastName;
    @NotNull(message = "Please provide customer phone number!")
    private Long phone;
    private String email;
    @JsonIgnore
    private LocalDate reservedFrom;
    @JsonIgnore
    private LocalDate reservedTo;

}
