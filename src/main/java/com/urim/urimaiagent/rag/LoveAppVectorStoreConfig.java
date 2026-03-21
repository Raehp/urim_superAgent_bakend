package com.urim.urimaiagent.rag;

import jakarta.annotation.Resource;
import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * 恋爱大师向量数据库配置（初始化基于内存的向量数据库 Bean）
 */
@Configuration
public class LoveAppVectorStoreConfig {

    @Resource
    private LoveAppDocumentLoader loveAppDocumentLoader;

    @Resource
    private MyTokenTextSplitter myTokenTextSplitter;

    @Resource
    private MyKeywordEnricher myKeywordEnricher;


    @Bean
    VectorStore loveAppVectorStore(EmbeddingModel dashscopeEmbeddingModel) {
        SimpleVectorStore simpleVectorStore = SimpleVectorStore.builder(dashscopeEmbeddingModel).build();
        // 文档加载
        List<Document> loadDocuments = loveAppDocumentLoader.loadDocuments();
        // 自主切分文档
//        List<Document> splitCustomized = myTokenTextSplitter.splitCustomized(loadDocuments);
        // 自动补充关键词元信息
        List<Document> enrichDocuments = myKeywordEnricher.enrichDocuments(loadDocuments);
        simpleVectorStore.add(enrichDocuments);
        return simpleVectorStore;
    }
}
