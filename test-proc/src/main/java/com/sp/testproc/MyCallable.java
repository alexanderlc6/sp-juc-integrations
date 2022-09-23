package com.sp.testproc;

//import com.zhongan.experiment.service.AbtService;
//import com.zhongan.experiment.zkConfig.CuratorZookeeperClient;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * Author: yunqing
 * Date: 2018/9/19
 * Description:任务处理逻辑
 */
public class MyCallable implements Callable{
//    @Autowired
//    CuratorZookeeperClient curatorZookeeperClient;
//
//    @Autowired
//    AbtService abtService;

    private List<String> list ;
    @Override
    public Object call() throws Exception {
        List<String> listReturn = new ArrayList<>();
        //模拟对数据处理，然后返回
        for(int i = 0;i < list.size();i++){
            listReturn.add(list.get(i)+"：处理时间："+System.currentTimeMillis()+"---:处理线程："+Thread.currentThread());
        }

        return listReturn;
    }

    public void setList(List<String> list) {
        this.list = list;
    }
}

