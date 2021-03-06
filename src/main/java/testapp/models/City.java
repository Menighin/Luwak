package testapp.models;

import com.menighin.luwak.core.interfaces.ILuwakModel;

public class City implements ILuwakModel {

	public static final String ID = "id";
	public static final String NAME = "name";
	public static final String COUNTRY = "country";

    private long id;
    private String name;
    private Country country;

    public City() {}

    public City(long id, String name, Country country) {
    	this.id = id;
    	this.name = name;
    	this.country = country;
	}

	public long getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}
}
