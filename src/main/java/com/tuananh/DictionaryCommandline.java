package com.tuananh;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import com.tuananh.entities.*;

public class DictionaryCommandline {
	public void showAllWords() {
		System.out.println("No" + "\t| English" + "\t" + "\t| Vietnamese");

		for (int i = 0; i < VariableManagement.D.listWords.size(); i++) {
      System.out.print((i+1) + "\t");
      VariableManagement.D.listWords.get(i).printWord();
		}
	}

	public void dictionaryBasic() {
		VariableManagement.DM.insertFromCommandline();
		showAllWords();
	}

  public void dictionaryAdvanced() {
    VariableManagement.DM.insertFromFile();
    showAllWords();
    
    System.out.println("Enter the word that you want to find: ");
    Word wordFind = VariableManagement.DM.dictionaryLookup();
    if (wordFind != null) {
      wordFind.printWord();
    } else {
      System.out.println("This word doesn't exist in dictionary");
    }
    
    VariableManagement.DM.addWord();
    VariableManagement.DM.dictionaryEdit();
    VariableManagement.DM.deleteData();

    showAllWords();
    dictionarySearcher();
    dictionaryExportToFile();
  }

  public void dictionarySearcher() {
    System.out.println("Enter the word that you want to search: ");
    Scanner scanner = new Scanner(System.in);
    String word = scanner.nextLine();
    scanner.close();
    
    for (int i = 0; i < VariableManagement.D.listWords.size(); i++) {
      Word temp = VariableManagement.D.listWords.get(i);
      int count = 0;
      for (int j = 0; j < word.length(); j++) {
        if (word.charAt(j) == temp.getWordTarget().charAt(j)) {
          count++;
        } else {
          break;
        }
      }
      if (count == word.length()) {
        temp.printWord();
      }
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
	public static void main(String[] args) {
		DictionaryCommandline test = new DictionaryCommandline();
    test.dictionaryAdvanced();
	}
}