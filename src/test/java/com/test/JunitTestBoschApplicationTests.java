package com.test;

import com.test.model.Category;
import com.test.repository.CategoryRepo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JunitTestBoschApplicationTests {

	@Test
	void contextLoads() {
	}
	@Autowired
	private CategoryRepo categoryRepo;

	@Test
	public void saveData(){
		Category category = new Category();
		category.setCategoryName("Phones");
		category.setDescription("All Phones are in this category");
		categoryRepo.save(category);
		Assertions.assertThat(category.getId()).isEqualTo(1);
	}


}
