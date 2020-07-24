package com.gf;

import com.gf.entity.Book;
import com.gf.repository.BookRepository;
import org.elasticsearch.common.io.stream.InputStreamStreamInput;
import org.elasticsearch.index.query.QueryBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootElasticsearchApplicationTests {

	@Autowired
	BookRepository bookRepository;
    @Autowired
	ElasticsearchRestTemplate elasticsearchRestTemplate;
	@Test
	public void createIndex2(){
		Book book = new Book();
		book.setId(1);
		book.setBookName("西游记");
		book.setAuthor( "吴承恩" );
		bookRepository.save(book);
	}

	@Test
	public void useFind() {

		List<Book> list = bookRepository.findByBookNameLike( "游" );
		for (Book book : list) {
			System.out.println(book);
		}

	}

}
