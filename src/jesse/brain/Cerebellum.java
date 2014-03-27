package jesse.brain;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author xuhao
 */


import java.util.*;
import java.util.concurrent.*;
import static java.util.concurrent.Executors.newFixedThreadPool;

public class Cerebellum 
{
    Medulla body;
    Stack<Task> TaskStack;
    Task Running;
    ExecutorService Execute;
    Cerebellum()
    {
        this.body=new Medulla();
        this.TaskStack=new Stack<>();
        this.Execute=newFixedThreadPool(1);
        this.Setup();
    }
    void addEmergency(Task task)
    {
        //完成抢占任务的分配 
    }
    void addRealtime(Task task)
    {
        //完成实时响应任务分配
    }
    void addStack(Task task)
    {
        //加入栈
        task.cere=this;
        Execute.submit(task);
        Running.shutdown();
        TaskStack.push(Running);
        Running.Status=TaskStatus.Waiting;
    }
    void work()
    {
        Setup();
  
    }
    void Setup()
    {
        //TODO 完成初始化
        body=new Medulla();
    }
    void nextStack()
    {
        
    }

    void WaitMission()
    {
        //阻塞等待

    }
}
