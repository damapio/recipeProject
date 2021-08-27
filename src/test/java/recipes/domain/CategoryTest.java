package recipes.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CategoryTest {
	
	Category category;
	
	@BeforeEach
	public void setUp( ) {
		category = new Category();
	}

	@Test
	public void getId() throws Exception {
		Long id = 5L;
		category.setId(id);
		assertEquals(id, category.getId());
	}
}
