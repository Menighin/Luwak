package testapp;

import com.menighin.luwak.AbstractLuwakApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import testapp.pages.CityPage;
import testapp.pages.CountryCityMasterDetailPage;


@SpringBootApplication
public class TestApplication extends AbstractLuwakApplication {

	@Autowired
	private CityPage cityPage;

	public static void main(String[] args) {
		SpringApplication.run(TestApplication.class, args);
	}


	@Override
	protected void setupApplication() {

		registerPage(CityPage.class);
		registerPage(CountryCityMasterDetailPage.class);

	}
}
