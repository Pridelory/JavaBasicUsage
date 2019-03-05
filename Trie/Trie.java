package com.wangmeng.Trie;

/**
 * Trie树的基本操作
 */
public class Trie {

    private Trie[] children;
    //whether current node is a keyWord
    private boolean isWord;
    /** Initialize your data structure here. */
    public Trie() {
        this.children = new Trie[26];
        this.isWord = false;
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        //curr represents the current node
        Trie curr = this;
        char[] insertedWord = word.toCharArray();
        for (int i = 0; i < insertedWord.length; i++) {
            int offset = insertedWord[i] - 'a';
            if (curr.children[offset] == null) {
                curr.children[offset] = new Trie();
            }
            curr = curr.children[offset];
        }
        curr.isWord = true;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        Trie curr = this;
        char[] insertedWord = word.toCharArray();
        for (int i = 0; i < insertedWord.length; i++) {
            int offset = insertedWord[i] - 'a';
            if (curr.children[offset] == null) {
                return false;
            }
            curr = curr.children[offset];
        }
        if (curr.isWord == true) {
            return true;
        } else {
            return false;
        }
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        Trie curr = this;
        char[] insertedWord = prefix.toCharArray();
        for (int i = 0; i < insertedWord.length; i++) {
            int offset = insertedWord[i] - 'a';
            if (curr.children[offset] == null) {
                return false;
            }
            curr = curr.children[offset];
        }
        return true;
    }
}