package testapp;

import com.menighin.luwak.AbstractLuwakApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import testapp.pages.CityPage;


@SpringBootApplication
public class TestApplication extends AbstractLuwakApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestApplication.class, args);
	}


	@Override
	protected void setupApplication() {

		registerPage(CityPage.class);

	}
}
