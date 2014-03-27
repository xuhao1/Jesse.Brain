/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jesse.brain;

/**
 *
 * @author xuhao
 */
enum TaskClass
{
    Takeoff,
    Hover,
    Landing,
    FlyTo,
    KeepStatus,
    Pending
}
enum TaskStatus
{
    Finished,
    Teminated,
    Waiting,
    Running
}
class PendingTask extends Task
{
    PendingTask(Medulla body)
    {
        this.tclass=TaskClass.Pending;
        this.Status=TaskStatus.Waiting;
        this.body=body;
    }
    TaskStatus Work(Medulla a)
    {
       //TODO  
        return TaskStatus.Finished;
    }
}
public abstract class Task implements Runnable 
{
    TaskClass tclass;
    TaskStatus Status;
    Medulla body;
    abstract TaskStatus Work(Medulla a);
    boolean shutdownSignal=false;
    Cerebellum cere;
    void shutdown()
    {
        shutdownSignal=true;
    }
    boolean isShutdown()
    {
        return shutdownSignal;
    }
    TaskStatus getStatus()
    {
        return this.Status;
    }
    @Override
    public void run()
    {
        this.Status=TaskStatus.Running;
        Status=this.Work(body);
        cere.nextStack();
    }

}
