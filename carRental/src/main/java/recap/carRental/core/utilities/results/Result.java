package recap.carRental.core.utilities.results;

public class Result { //Result burada soyut sınıf görevi görüyor, tek başına kullanılmıyor. Bu yüzden ekstra interface ihtiyacı yok.
	private boolean success;
	private String message;
	
	public Result(boolean success) {
		this.success = success;
	}
	
	public Result(boolean success, String message) {
		this(success);
		this.message = message;
	}
	
	public boolean isSuccess() {
		return this.success;
	}
	public String getMessage() {
		return this.message;
	}
}
