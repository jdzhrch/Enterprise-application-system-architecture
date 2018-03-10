import java.util.*;
import javax.jws.*;

@WebService
public class Warehouse {
	public Warehouse() {
		prices = new HashMap<String, Double>();
		prices.put("Blackwell Toaster", 24.95);
		prices.put("ZapXpress Microwave Oven", 49.95);
	}

	public double getPrice(@WebParam(name = "description") String description) {
		Double price = prices.get(description);
		return price == null ? 0 : price;
	}

	private Map<String, Double> prices;
}