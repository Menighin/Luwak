package testapp.models;

import com.menighin.luwak.core.interfaces.ILuwakModel;
import lombok.Getter;
import lombok.Setter;

public class City implements ILuwakModel {

    @Getter @Setter
    private int id;

    @Getter @Setter
    private String name;

    @Getter @Setter
    private Country country;

}
