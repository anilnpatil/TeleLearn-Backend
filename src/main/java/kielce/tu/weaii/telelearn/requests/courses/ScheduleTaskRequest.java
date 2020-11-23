package kielce.tu.weaii.telelearn.requests.courses;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.time.Duration;
import java.time.LocalDate;

@Valid
@Getter
public class ScheduleTaskRequest {
    private Long taskId;
    private Long studentId;
    private Duration plannedTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @Valid
    @JsonCreator
    public ScheduleTaskRequest(@JsonProperty(value = "taskId", required = true) Long taskId,
                               @JsonProperty(value = "studentId", required = true) Long studentId,
                               @JsonProperty(value = "date", required = true) LocalDate date,
                               @Min(value = 0, message = "Nieprawidłowa liczba godzin") @JsonProperty(value = "hours", required = true) long hours,
                               @Range(min = 0, max = 60, message = "Nieprawidłowa liczba minut") @JsonProperty(value = "minutes", required = true) long minutes) {
        this.taskId = taskId;
        this.studentId = studentId;
        this.date = date;
        this.plannedTime = Duration.ofHours(hours).plus(Duration.ofMinutes(minutes));
    }
}
