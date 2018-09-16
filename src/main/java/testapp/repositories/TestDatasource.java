package testapp.repositories;

import org.springframework.stereotype.Repository;

@Repository
public class TestDatasource {

	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
