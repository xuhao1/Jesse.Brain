/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jesse.brain;

import gnu.io.*;
import java.io.*;
/**
 *
 * @author xuhao
 */
class InputThread implements Runnable
{
    //TODO:分析输入数据
    InputStream in;
    InputThread (InputStream in)
    {
        this.in=in;
    }
    @Override
    public void run()
    {
        try
        {
            while(true)
            {
                if(in.available()>0)
                    System.out.print((char)in.read());
            }
        }
        catch(Exception e)
        {
            //TODO Catch Exception
            System.out.println(e);
        }

    }
}
class Motors
{
    public void setYaw(double x)
    {
        //TODO
    }
    public void setRoll(double x)
    {
        //TODO
    }
    public void setPitch(double x)
    {
        //TODO
    }
    public void setThrrotle(double x)
    {
        //TODO
    }
    protected String MotorOutput(int i,double v)
    {
        return String.printf("$SET:MOTOR_%d:%f;",i,v);
    }
    String MotorOutput()
    {
        //TODO
    }
    public void shutdownMotors()
    {
        setMotor(0,0);
        setMotor(1,0);
        setMotor(2,0);
        setMotor(3,0);
    }
    private void setMotorMode(char mode)
    {
    
        try
        {
            /*   
              ^
            3  0
             \/
             /\ 
            2  1     
            */
            if(mode=='x')
            {
                set_motor(0 ,  45, 1);
                set_motor(1 , 135,-1);
                set_motor(2 ,-135, 1);
                set_motor(3 , -45,-1);
            }
            else
                throw Exception("Unsupported Drone Mode");
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    } 
    Motors (char mode)
    {
        setMotorMode(mode);
    }
}
public class Medulla 
{
    RXTXPort serial;
    //TODO 制定通信协议，完成底层控制与通信代码
    InputThread inpt;
    PrintStream out;
    double[] YawFactor,RollFactor,PitchFactor;
    private Motors motors; 
    void setUpSerial(String dev)
    {
        try{
            serial=new RXTXPort("/dev/tty.usbserial");
            serial.setSerialPortParams( 115200,
                SerialPort.DATABITS_8,
                SerialPort.STOPBITS_1,
                SerialPort.PARITY_NONE );
            InputStream in = serial.getInputStream();
            inpt=new InputThread(in);
            new Thread(inpt).start();
            out = new PrintStream(serial.getOutputStream());
        }
        catch (Exception e)
        {
            System.out.println("Failed to Set up Medulla"+e);
        }
    }
    Medulla()
    {
        setUpSerial();
        motors('x',out);
        //启动守护进程
        new Thread(this);
    }
    public void setYaw(double x)
    {
        //TODO
    }
    public void setRoll(double x)
    {
        //TODO
    }
    public void setPitch(double x)
    {
        //TODO
    }
    public void setThrrotle(double x)
    {
        //TODO
    }
    public void run
    {
        System.out.println('Started Mdulla');
        while(true)
        {
            //TODO Finish the MainLoop 
            //Running In 1000hz,100hz
        }
    }
}
