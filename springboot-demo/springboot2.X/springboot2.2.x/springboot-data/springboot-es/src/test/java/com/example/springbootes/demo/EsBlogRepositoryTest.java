package com.example.springbootes.demo;

import com.example.springbootes.demo.blocg.EsBlog;
import com.example.springbootes.demo.blocg.EsBlogRepository;
import com.example.springbootes.demo.blocg.SpringEsApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = SpringEsApplication.class)
public class EsBlogRepositoryTest {

    @Autowired
    private EsBlogRepository esBlogRepository;

    /**
     * 初始化数据
     */
    @Test
    public void initRepositoryData() {
        esBlogRepository.deleteAll();
        esBlogRepository.save(new EsBlog("登鹤雀楼", "王之涣的登鹤雀楼", "白日依山尽，黄河入海流。欲穷千里目，更上一层楼。"));
        esBlogRepository.save(new EsBlog("相思", "王维的相思", "红豆生南国，春来发几枝。愿君多采撷，此物最相思。"));
        esBlogRepository.save(new EsBlog("静夜思", "李白的静夜思", "床前明月光，疑是地上霜。举头望明月，低头思故乡。"));
    }

    @Test
    public void testEsBlogRepository() {
        // PageRequest已经不用了，改成PageRequest.of()
        Pageable pageable = PageRequest.of(0, 20);
        String title = "思";
        String summary = "相思";
        String content = "相思";
        List<EsBlog> contentBlog = esBlogRepository.findDistinctByEsBlogtttt_Content(content);
        Page<EsBlog> pageBlog = esBlogRepository.
                findDistinctEsBlogByTitleContainingOrSummaryContainingOrContentContaining
                        (title, summary, content, pageable);
        // 使用JUnit断言
        assertThat(pageBlog.getTotalElements()).isEqualTo(2);
        System.out.println("Start--------------------1");
        for (EsBlog esBlog : pageBlog.getContent()) {
            System.out.println(esBlog.toString());
        }
        System.out.println("End--------------------1");
    }

    @Test
    public void testDelete() {
        Iterable<EsBlog> esBlogIterable = esBlogRepository.findAll();
        EsBlog esBlog = esBlogIterable.iterator().next();
        //传入一个对象 实际是更具对象的ID进行删除
        esBlogRepository.delete(esBlog);

    }


}