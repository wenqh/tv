//package com.wqh;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TVMerge {
    private static Stream<String> M3U8_URLS = Stream.of(
//            "https://gh.llkk.cc/https://raw.githubusercontent.com/Kimentanm/aptv/master/m3u/iptv.m3u",
//            "https://gh.llkk.cc/https://raw.githubusercontent.com/develop202/migu_video/refs/heads/main/interface.txt",
//            "https://nos.netease.com/ysf/3d75a78a0fc7ede372c03598d6d10367.m3u",
//            "https://sub.ottiptv.cc/huyayqk.m3u",
//            "https://sub.ottiptv.cc/douyuyqk.m3u",
//            "https://sub.ottiptv.cc/bililive.m3u",
//            "https://sub.ottiptv.cc/yylunbo.m3u",
//            "https://raw.githubusercontent.com/Jsnzkpg/Jsnzkpg/refs/heads/Jsnzkpg/Jsnzkpg1.m3u",

           "https://iptv.yang-1989.eu.org/m3u/Gather.m3u", //https://github.com/YanG-1989/m3u/
           "https://cdn.qd.je/live.m3u", //juli
           //"http://iptv.4666888.xyz/FYTV.m3u", //风云
           "https://raw.githubusercontent.com/wenqh/tv/refs/heads/main/tv-my.m3u" //my
    );

    public static void main(String[] args) throws IOException {
        String all = M3U8_URLS.map(u -> {
            String resp = httpGet(u);
            return resp;
        }).collect(Collectors.joining("\n"));

        Files.writeString(Path.of("tv-all.m3u"), all, StandardCharsets.UTF_8);
    }


    private static String httpGet(String url) {
        HttpClient httpClient = HttpClient.newHttpClient();
        // 构造 GET 请求
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).GET()
                .header("User-Agent", "okhttp/5.1.0").build();

        HttpResponse<String> response;
        try {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(url, e);
        }

        return response.body();
    }
}
