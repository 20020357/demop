package com.tuananh.entities;

public class Word {
	private String wordTarget;
	private String wordExplain;

	public String getWordTarget() {
		return wordTarget;
	}
	
	public void setWordTarget(String wordTarget) {
		this.wordTarget = wordTarget;
	}

	public String getWordExplain() {
		return wordExplain;
	}
	
	public void setWordExplain(String wordExplain) {
		this.wordExplain = wordExplain;
	}

	public Word(String wordTarget, String wordExplain) {
		this.wordTarget = wordTarget;
		this.wordExplain = wordExplain;
	}

  public void printWord() {
    System.out.println("| " + getWordTarget() + "\t" + "\t| " + getWordExplain());
  }
}