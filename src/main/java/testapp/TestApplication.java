package testapp;

import com.menighin.luwak.LuwakApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TestApplication extends LuwakApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestApplication.class, args);
	}

}
