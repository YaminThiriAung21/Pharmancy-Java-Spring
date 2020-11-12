package pec.dto;





import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class DateRange {

	 
    private String dateFrom;
    private String dateTo;
	 @DateTimeFormat(pattern = "DD-MM-YYYY")
    private LocalDateTime startDate;
	 @DateTimeFormat(pattern = "DD-MM-YYYY")
    private LocalDateTime endDate;  

}