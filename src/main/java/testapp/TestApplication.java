package testapp;

import com.menighin.luwak.LuwakApplication;
import com.menighin.luwak.core.interfaces.ILuwakFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import testapp.filters.CityFilter;
import testapp.pages.CityPage;


@SpringBootApplication
public class TestApplication extends LuwakApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestApplication.class, args);
	}


	@Override
	protected void setupApplication() {

		registerPage(CityPage.class);

	}
}
