package recap.carRental.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarWithDetailDto {

	private int modelYear;
	
	private double dailyPrice;
	
	private String description;
	
	private String brandName;
	
	private String colorName;

}
