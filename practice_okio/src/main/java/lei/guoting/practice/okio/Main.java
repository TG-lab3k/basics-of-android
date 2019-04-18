package lei.guoting.practice.okio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import okio.BufferedSource;
import okio.Okio;

public class Main {

  public static void main(String[] args) {
    log("--------   main   --------");
    readFromFile(new File("/Users/leigt/Downloads/locat-2019-03-06-10-20-33.txt"));
  }


  static void readFromFile(File file) {
    InputStream input;
    try {
      input = new FileInputStream(file);
    } catch (FileNotFoundException ignore) {
      ignore.printStackTrace();
      return;
    }
    BufferedSource source = Okio.buffer(Okio.source(input));

    try {
      while (true) {
        String line = source.readUtf8Line();
        if (line == null) {
          break;
        }
        log(line);
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        source.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  


  static void log(String msg) {
    log("INFO", msg);
  }

  static void log(String tag, String msg) {
    System.out.println(String.format("%s -> %s", tag == null ? "" : tag.toUpperCase(), msg));
  }
}
