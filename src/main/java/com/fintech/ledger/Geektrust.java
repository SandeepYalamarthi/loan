package com.fintech.ledger;

import com.fintech.ledger.processors.FileProcessor;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Hello world!
 */
public class Geektrust {

  public static void main(String[] args) {
    //take file input from console
    String filePath = args[0];

    Path path = Paths.get(filePath);
    FileProcessor fileProcessor = new FileProcessor();
    fileProcessor.parseFile(path);

  }
}
