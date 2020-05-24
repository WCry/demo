package LuceneDemo;


import com.hankcs.lucene.HanLPAnalyzer;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.WildcardQuery;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.File;
import java.io.IOException;

public class LuceneDemo {

    public static void main(String[] args) throws IOException {
        LuceneDemo luceneMerge = new LuceneDemo();
        //        File descFile=new File(args[0]);
        //        File srcFile=new File(args[1]);
        //        if(descFile.exists()&&srcFile.exists()){
        //            luceneMerge.indexMerge(descFile,srcFile);
        //        }else{
        //            System.out.println("文件路径错误！");
        //        }
        luceneMerge.search(new File("C:\\Users\\zhangxuepei\\Desktop\\bgd\\bgd"));
    }

    public void search(File indexPath) throws IOException {
        Directory fsdDir = FSDirectory.open(indexPath.toPath());
        DirectoryReader indexReader = DirectoryReader.open(fsdDir);
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);
        BooleanQuery.Builder builder = new BooleanQuery.Builder();// boolean组合查询构建器
        Term term = new Term("indexFiled", "*中山市*");
        Query query = new WildcardQuery(term);// 通配符查询
        builder.add(query, BooleanClause.Occur.MUST);
        Query superTypeTermQuery = new TermQuery(new Term("superType", "DISTRICT"));
        builder.add(superTypeTermQuery, BooleanClause.Occur.MUST);
        Query subTypeTermQuery = new TermQuery(new Term("subType", "CITY_LN"));
        builder.add(subTypeTermQuery, BooleanClause.Occur.SHOULD);
        builder.setMinimumNumberShouldMatch(0);
        TopDocs top = indexSearcher.search(builder.build(), 2000);
        for (int i = 0; i < top.totalHits; i++) {
            System.out.print("name:" + indexReader.document(top.scoreDocs[i].doc).get("name"));
            System.out.print(";superType:" + indexReader.document(top.scoreDocs[i].doc).get("superType"));
            System.out.print(";subType:" + indexReader.document(top.scoreDocs[i].doc).get("subType"));
            System.out.println();
        }
    }

    /**
     * 合并不同索引路径下的索引文件
     *
     * @param indexPath 合并目标地址
     * @param otherIndexPath
     */
    public void indexMerge(File indexPath, File otherIndexPath) {
        try {
            Analyzer analyzer = new HanLPAnalyzer();
            IndexWriterConfig conf = new IndexWriterConfig(analyzer);
            conf.setOpenMode(IndexWriterConfig.OpenMode.APPEND);//追加模式添加索引
            Directory fsdDir = FSDirectory.open(indexPath.toPath());
            IndexWriter fsdIndex = new IndexWriter(fsdDir, conf);
            FSDirectory[] fsDirectories = {FSDirectory.open(otherIndexPath.toPath())};
            System.out.println("合并之前Documents:" + fsdIndex.maxDoc());
            fsdIndex.addIndexes(fsDirectories);//合并内存索引到目标地址指定的文件索引
            fsdIndex.commit();
            System.out.println("合并之后Documents:" + fsdIndex.maxDoc());
            System.out.println("合并成功！");
            fsdIndex.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
