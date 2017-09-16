//: net/mindview/util/OSExecute.java
// Run an operating system command
// and send the output to the console.
package net.mindview.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public class OSExecute {

  /**
   * ָ���ļ�Ŀ¼����������ϵͳ����
   * @param filePath
   * @param command
     */
  public static void command(String filePath, String command) {
    boolean err = false;

    File file = new File(filePath);
    if(!file.isDirectory()) throw new RuntimeException( filePath + " �ļ�Ŀ¼����!" );

    try {
      ProcessBuilder _process =
              new ProcessBuilder(command.split(" "));
      _process.directory(file);
      Process  process =  _process.start();

      BufferedReader results = new BufferedReader(
              new InputStreamReader(process.getInputStream()));
      String s;
      while((s = results.readLine())!= null)
        System.out.println(s);
      BufferedReader errors = new BufferedReader(
              new InputStreamReader(process.getErrorStream()));
      // Report errors and return nonzero value
      // to calling process if there are problems:
      while((s = errors.readLine())!= null) {
        System.err.println(s);
        err = true;
      }
    } catch(Exception e) {
//       Compensate for Windows 2000, which throws an
//       exception for the default command line:
      if(!command.startsWith("CMD /C"))
        command(filePath, "CMD /C " + command);
      else
        throw new RuntimeException(e);

      e.printStackTrace();
    }
    if(err)
      throw new OSExecuteException("Errors executing " +
              command);

  }

  public static void command(String command) {
    boolean err = false;
    try {
      Process process =
        new ProcessBuilder(command.split(" ")).start();
      BufferedReader results = new BufferedReader(
        new InputStreamReader(process.getInputStream()));
      String s;
      while((s = results.readLine())!= null)
        System.out.println(s);
      BufferedReader errors = new BufferedReader(
        new InputStreamReader(process.getErrorStream()));
      // Report errors and return nonzero value
      // to calling process if there are problems:
      while((s = errors.readLine())!= null) {
        System.err.println(s);
        err = true;
      }
    } catch(Exception e) {
      // Compensate for Windows 2000, which throws an
      // exception for the default command line:
      if(!command.startsWith("CMD /C"))
        command("CMD /C " + command);
      else
        throw new RuntimeException(e);
    }
    if(err)
      throw new OSExecuteException("Errors executing " +
        command);
  }
} ///:~
