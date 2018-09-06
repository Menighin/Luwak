package testapp.repositories;

import com.menighin.luwak.core.interfaces.ILuwakRepository;
import testapp.models.City;
import testapp.models.Country;

import java.util.ArrayList;

public class CityRepository implements ILuwakRepository<City> {

	@Override
	public ArrayList<City> getAll() {

		final Country brazil = new Country(0, "Brazil");
		final Country usa = new Country(1, "United States of America");
		final Country portugal = new Country(2, "Portugal");

		return new ArrayList<City>() {{
			add(new City(0, "Belo Horizonte", brazil));
			add(new City(1, "Sao Paulo", brazil));
			add(new City(2, "Denver", usa));
			add(new City(3, "San Francisco", usa));
			add(new City(4, "Porto", portugal));
			add(new City(5, "Lisboa", portugal));
		}};
	}

}
