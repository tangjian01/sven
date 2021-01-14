package com.sven.es;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.bulk.*;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.unit.ByteSizeUnit;
import org.elasticsearch.common.unit.ByteSizeValue;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.*;
import java.net.URL;

/**
 * @author ：897863952@qq.com
 * @date ：Created in 2021/1/14 14:44
 * @description：
 * @version:
 */
@Slf4j
public class main {

    public static void main(String[] args) throws IOException, InterruptedException {
        //create
        //createIndex();

        //insert

        addData();
    }

    public static void addData() throws InterruptedException {
        log.info("开始添加 1 数据... ");
        EsUtils esUtils = new EsUtils();
        RestHighLevelClient clients = esUtils.getClients();
        BulkProcessor.Listener listener = new BulkProcessor.Listener() {
            @Override
            public void beforeBulk(long executionId, BulkRequest request) {
                int numberOfActions = request.numberOfActions();
                log.info("executing bulk [{}] size:{}", executionId, numberOfActions);
            }

            @Override
            public void afterBulk(long executionId, BulkRequest request, BulkResponse response) {
                if (response.hasFailures()) {
                    BulkItemResponse[] responses = response.getItems();
                    for (BulkItemResponse bulkItemResponse : responses) {
                        if (bulkItemResponse.isFailed()) {
                            BulkItemResponse.Failure failure = bulkItemResponse.getFailure();
                            log.error("bulk [{}] has fail, index:{}, id:{}", executionId, failure.getIndex(), failure.getId());
                        }

                    }
                    log.error("bulk [{}] has failure msg:{}", executionId, response.buildFailureMessage());
                } else {
                    log.info("executing bulk [{}] success size:{}", executionId, request.numberOfActions());
                }

            }
            @Override
            public void afterBulk(long executionId, BulkRequest request, Throwable failure) {
                log.error("execute bulk fail", failure);
            }
        };

        BulkProcessor bulkProcessor = BulkProcessor
                .builder(((request, bulkResponseActionListener) -> clients.bulkAsync(request, RequestOptions.DEFAULT, bulkResponseActionListener)), listener)
                //100条数据请求执行一次bulk
                .setBulkActions(100)
                //5mb的数据刷新一次bulk
                .setBulkSize(new ByteSizeValue(5L, ByteSizeUnit.MB))
                //并发请求数量, 0不并发, 1并发允许执行
                .setConcurrentRequests(1)
                //固定10s必须刷新一次
                .setFlushInterval(TimeValue.timeValueSeconds(5L))
                //重试5次，间隔1s
                .setBackoffPolicy(BackoffPolicy.constantBackoff(TimeValue.timeValueSeconds(1L), 5))
                .build();

        for(int i = 10000;i>0;i--){
            JSONObject t = new JSONObject();
            t.put("workOrderNum", System.currentTimeMillis() + "");
            t.put("venderName", "唐剑 "+i);

            UpdateRequest updateRequest = new UpdateRequest();
            updateRequest.index("es_demo_2");
            updateRequest.id(System.currentTimeMillis()+"");
            updateRequest.type("_doc");

            updateRequest.doc(JSON.toJSONString(t), XContentType.JSON).docAsUpsert(true);
            BulkProcessor add = bulkProcessor.add(updateRequest);
            add.flush();
        }

        Thread.sleep(10000);
    }

    public static boolean exsits(RestHighLevelClient clients,String index) throws IOException {
      return  clients.indices().exists( new GetIndexRequest(index),RequestOptions.DEFAULT);
    }

    public static void createIndex() throws IOException {
        EsUtils esUtils = new EsUtils();
        RestHighLevelClient clients = esUtils.getClients();

        CreateIndexRequest indexRequest = new CreateIndexRequest("es_demo_2");

        boolean exsits = exsits(clients, indexRequest.index());
        if(exsits) return;
        //创建索引时创建文件类型映射
        URL resource = main.class.getClassLoader().getResource("es/workorder_template.json");
        File jsonFile = new File(resource.getFile());
        FileReader fileReader = new FileReader(jsonFile);
        Reader reader = new InputStreamReader(new FileInputStream(jsonFile),"utf-8");

        int ch = 0;
        StringBuffer sb = new StringBuffer();
        while ( (ch = reader.read() )!=-1 ){
            sb.append( (char) ch );
        }
        fileReader.close();
        reader.close();
        String str = sb.toString();

        indexRequest.source(str,XContentType.JSON);

        CreateIndexResponse createIndexResponse = clients.indices().create(indexRequest, RequestOptions.DEFAULT);

        clients.close();
    }
}
