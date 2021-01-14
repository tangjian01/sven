package com.sven.es;

import org.apache.commons.codec.binary.StringUtils;
import org.apache.http.HttpHost;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;

import java.util.Arrays;
import java.util.Objects;

/**
 * @author ：897863952@qq.com
 * @date ：Created in 2021/1/14 14:44
 * @description：
 * @version:
 */
public class EsUtils {
    private static final int TIME_OUT = 5 * 60 * 1000;
    private static final int ADDRESS_LENGTH = 2;
    private static final String HTTP_SCHEME = "http";

    public  RestHighLevelClient getClients(){
        return new RestHighLevelClient(restClientBuilder());
    }
    public  RestClientBuilder restClientBuilder() {
        String ipAddress[] = new String[1];
        ipAddress[0] = "dockerhost:9200";
        HttpHost[] hosts = Arrays.stream(ipAddress)
                .map(this::makeHttpHost)
                .filter(Objects::nonNull)
                .toArray(HttpHost[]::new);
        RestClientBuilder builder = RestClient.builder(hosts);
        //builder.setRequestConfigCallback
        //builder.setHttpClientConfigCallback


        return builder;
    }
    private HttpHost makeHttpHost(String s) {
        String[] address = s.split(":");
        if (address.length == ADDRESS_LENGTH) {
            String ip = address[0];
            int port = Integer.parseInt(address[1]);
            System.err.println(ip+"+"+port);
            return new HttpHost(ip, port, HTTP_SCHEME);
        } else {
            return null;
        }
    }
}
