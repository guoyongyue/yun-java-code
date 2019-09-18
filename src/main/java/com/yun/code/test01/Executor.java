package com.yun.code.test01;/*
package code.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class Executor {
    public static void main(String[] args) {
        // 添加 I/O 任务
        List<Callable<String>> ioCallableTasks = new ArrayList<>();
        ioCallableTasks.add(JsonService::getPosts);
        ioCallableTasks.add(JsonService::getComments);
        ioCallableTasks.add(JsonService::getAlbums);
        ioCallableTasks.add(JsonService::getPhotos);

// 调用所有并行任务
        ExecutorService ioExecutorService = CustomThreads.getExecutorService(ioPoolSize);
        List<Future<String>> futuresOfIOTasks = ioExecutorService.invokeAll(ioCallableTasks);

// 获取 I/O  操作(阻塞调用)结果
        String posts = futuresOfIOTasks.get(0).get();
        String comments = futuresOfIOTasks.get(1).get();
        String albums = futuresOfIOTasks.get(2).get();
        String photos = futuresOfIOTasks.get(3).get();

// 合并响应(内存中的任务是此操作的一部分)
        String postsAndCommentsOfRandomUser = ResponseUtil.getPostsAndCommentsOfRandomUser(userId, posts, comments);
        String albumsAndPhotosOfRandomUser = ResponseUtil.getAlbumsAndPhotosOfRandomUser(userId, albums, photos);
    }
}
*/
