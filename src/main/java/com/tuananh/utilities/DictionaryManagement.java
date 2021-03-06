package com.tuananh.utilities;

import com.tuananh.entities.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class DictionaryManagement {
  private static final Scanner scanner = new Scanner(System.in);
  
  public void insertFromCommandline() {
    System.out.println("Enter the number of words that you want to add to the dictionary: ");
    int numberOfVocabulary = scanner.nextInt();
    scanner.nextLine();

    for (int i = 0; i < numberOfVocabulary; i++) {
      System.out.println("Please enter the word target: ");
      String wordTarget = scanner.nextLine();
      System.out.println("Please enter the word explain: ");
      String wordExplain = scanner.nextLine();

      Word word = new Word(wordTarget, wordExplain);
      VariableManagement.D.listWords.add(word);
    }
  }

  public void insertFromFile() {
    try {
      File dictionary = new File("src/main/java/com/tuananh/resource/input dictionary.txt");
      BufferedReader reader = new BufferedReader(new FileReader(dictionary));

      String data = "";
      try {
        while ((data = reader.readLine()) != null) {
          if (data.equals("")) {
            continue;
          }
          String[] temp = data.split("\t");
          String wordTarget = temp[0];
          String wordExplain = temp[1];
          Word word = new Word(wordTarget, wordExplain);
          VariableManagement.D.listWords.add(word);
        }
        reader.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  public void dictionaryExportToFile() {
    File dictionary = new File("src/main/java/com/tuananh/resource/output dictionary.txt");
    try {
      BufferedWriter writer = new BufferedWriter(new FileWriter(dictionary));
      for (int i = 0; i < VariableManagement.D.listWords.size(); i++) {
        String word = VariableManagement.D.listWords.get(i).getWordTarget() + "\t" + VariableManagement.D.listWords.get(i).getWordExplain();
        writer.write(word + "\n");
      }
      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  public Word dictionaryLookup() {
    String word = scanner.nextLine();

    Word result = null;
    for (int i = 0; i < VariableManagement.D.listWords.size(); i++) {
      String target = VariableManagement.D.listWords.get(i).getWordTarget();
      if (word.equals(target)) {
        result = VariableManagement.D.listWords.get(i);
        break;
      }
    }

    return result;
  }

  public void addWord() {
    System.out.println("Enter both target and explain of the word that you want to add to the dictionary:");
    
    String wordTarget = scanner.nextLine();
    String wordExplain = scanner.nextLine();
    Word word = new Word(wordTarget, wordExplain);
    VariableManagement.D.listWords.add(word);

    word.printWord();
  }

  public void dictionaryEdit() {
    System.out.println("Enter the case that you want to edit: (If input isn't an integer, you will error dictionary !)");
    System.out.println("  1, Edit word target.");
    System.out.println("  2, Edit word explain.");
    System.out.println("  Other inputs integer: The default is to edit word both target and explain.");
    
    int caseNumber = 0;
    caseNumber = scanner.nextInt();
    scanner.nextLine();

    System.out.println("Enter the word that you want to edit:");
    Word wordEdit = dictionaryLookup();
    if (wordEdit != null) {
      String newWordTarget = "";
      String newWordExplain = "";
      switch (caseNumber) {
        case 1:
          System.out.println("Enter the new word target:");
          newWordTarget = scanner.nextLine();
          wordEdit.setWordTarget(newWordTarget);
          break;
        case 2:
          System.out.println("Enter the new word explain:");
          newWordExplain = scanner.nextLine();
          wordEdit.setWordExplain(newWordExplain);
          break;
        default:
          System.out.println("Enter the new word both target and explain:");
          newWordTarget = scanner.nextLine();
          newWordExplain = scanner.nextLine();
          wordEdit.setWordTarget(newWordTarget);
          wordEdit.setWordExplain(newWordExplain);
          break;
      }
      wordEdit.printWord();
    } else {
      System.out.println("This word doesn't exist in dictionary, so you can't edit it.");
    }
  }

  public void deleteData() {
    System.out.println("Enter the word that you want to delete:");
    Word wordDelete = dictionaryLookup();
    if (wordDelete != null) {
      VariableManagement.D.listWords.remove(wordDelete);
      System.out.println(wordDelete.getWordTarget() + "deleted !");
    } else {
      System.out.println("This word doesn't exist in dictionay, so you can't delete it.");
    }
  }
}