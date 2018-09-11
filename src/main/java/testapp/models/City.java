package testapp.models;

import com.menighin.luwak.core.interfaces.ILuwakModel;
import lombok.Getter;
import lombok.Setter;

public class City implements ILuwakModel {

	public static final String ID = "id";
	public static final String NAME = "name";
	public static final String COUNTRY = "country";

    @Getter @Setter
    private int id;

    @Getter @Setter
    private String name;

    @Getter @Setter
    private Country country;

    public City() {}

    public City(int id, String name, Country country) {
    	this.id = id;
    	this.name = name;
    	this.country = country;
	}

}
