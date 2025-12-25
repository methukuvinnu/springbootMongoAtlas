package com.example.vinnu.controller;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FetchApiClass {
    static List<Post> fetchPosts() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(java.net.URI.create("https://coderbyte.com/api/challenges/json/all-posts"))
                .GET()
                .build();
        HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());
        List<Post>  posts = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        List<Post> posts1 = mapper.readValue(response.body().toString(),
                mapper.getTypeFactory().constructCollectionType(List.class, Post.class));
        return posts1;
    }
    public static List<Map<String,Integer>> formatPosts( List<Post> posts){
        List<Map<String,Integer>> mapList = new ArrayList<>();
        for (Post post: posts){
            Map<String,Integer> map = Map.of(
                    "userId",post.getUserId(),
                    "numberOfPosts",post.getId()
            );
            mapList.add(map);
        }
        return mapList;
    }
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("Hi Vinod");
        List<Post> posts = fetchPosts();
        System.out.println("posts size :" +posts.size());
        posts.forEach(System.out::println);


        List<Map<String,Integer>> mapList = formatPosts(posts);
        System.out.println("Formatted Posts:" +mapList);
    }
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Post {
             int userId;
             int id;

            public int getUserId() {
                return userId;
            }
            private void setUserId(int userId) {
                this.userId = userId;
            }
            public int getId() {
                return id;
            }
            private void setId(int id) {
                this.id = id;
            }
            @Override
        public String toString() {
            return "Post [userId=" + userId + ", id=" + id + "]";
        }

    }
}
