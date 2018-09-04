package testapp;

import com.menighin.luwak.LuwakApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import testapp.pages.CityPage;

import javax.annotation.PostConstruct;

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
