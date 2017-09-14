package message.utils;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.InputStreamReader;
import java.util.Vector;

/**
 * Created by apple on 2017/9/14.
 * 在windows下运行cmd命令
 */
public class Util_syscmd {

    /**
     * @param shellCommand
     *
     */
    public Vector execute(String shellCommand){
        try{
            Start(shellCommand);
            Vector vResult=new Vector();
            DataInputStream in=new DataInputStream(p.getInputStream());
            BufferedReader reader=new BufferedReader(new InputStreamReader(in));
            String line;
            do{
                line=reader.readLine();
                if (line==null){
                    break;
                }else{
                    vResult.addElement(line);
                }
            }while(true);
            reader.close();
            return vResult;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    /**
     * @param shellCommand
     *
     */
    public void Start(String shellCommand){
        try{
            if(p!=null){
                kill();
            }
            Runtime sys= Runtime.getRuntime();
            p=sys.exec(shellCommand);
        }catch(Exception e){
            System.out.println(e.toString());
        }
    }
    /**
     kill this process
     */
    public void kill(){
        if(p!=null){
            p.destroy();
            p=null;
        }
    }

    Process p;
}

