package com.tuananh;

import java.util.Scanner;
import com.tuananh.entities.*;

public class DictionaryCommandline {
	private static final Scanner scanner = new Scanner(System.in);
  
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
  }

  public void dictionarySearcher() {
    System.out.println("Enter the letters that you want to search: ");
    String word = scanner.nextLine();
    
    if (VariableManagement.D.listWords.size() == 0) {
      System.out.println("Dictionary is empty, so you can't search it !");
      return;
    }

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
  
	public static void main(String[] args) {
		DictionaryCommandline test = new DictionaryCommandline();
    // test.dictionaryBasic();
    // test.dictionaryAdvanced();
    System.out.println("Please choose feature which you want to use:");
    System.out.println("  1, Enter dictionary data from the keyboard.");
    System.out.println("  2, Show dictionary data.");
    System.out.println("  3, Get dictionary data from file.");
    System.out.println("  4, Look up the dictionary using the command line.");
    System.out.println("  5, Add word to dictionary using the command line.");
    System.out.println("  6, Edit word in dictionary using the command line.");
    System.out.println("  7, Delete word.");
    System.out.println("  8, Search for words starting with the following letters in the dictionary.");
    System.out.println("  9, Export dictionary data to file.");
    System.out.println("  Other inputs integer: The default is to exit the program.");
    boolean run = true;
    while (run) {
      Scanner scanner = new Scanner(System.in);
      int number = scanner.nextInt();

      switch (number) {
        case 1:
          VariableManagement.DM.insertFromCommandline();
          break;
        case 2:
          test.showAllWords();          
          break;
        case 3:
          VariableManagement.DM.insertFromFile();
          System.out.println("Got dictionary data from file !");
          break;
        case 4:
          System.out.println("Enter the word that you want to find:");
          Word wordFind = VariableManagement.DM.dictionaryLookup();
          if (wordFind != null) {
            wordFind.printWord();
          } else {
            System.out.println("This word doesn't exist in dictionary");
          }
          break;
        case 5:
          VariableManagement.DM.addWord();
          break;
        case 6:
          VariableManagement.DM.dictionaryEdit();
          break;
        case 7:
          VariableManagement.DM.deleteData();
          break;
        case 8:
          test.dictionarySearcher();
          break;
        case 9:
          VariableManagement.DM.dictionaryExportToFile();
          System.out.println("Exported to file !");
          break;
        default:
          run = false;
          scanner.close();
          break;
      }
    }
	}
}