import java.util.*;
public class Trie{
    TrieNode root;

    public Trie(){
        root = new TrieNode('\0', false);
    }

    public void insert(String s){
        int len = s.length();
        char[] charset = s.toCharArray();
        TrieNode curr = root;
        for(int i = 0; i < len; i++){
            curr.count += 1;
            if(curr.links[charset[i]] == null){
                curr.links[charset[i]] = new TrieNode(charset[i], false);
            }
            curr = curr.links[charset[i]];
        }
        curr.fullword = true;
    }

    public boolean search(String s){
        char[] charset = s.toCharArray();
        int len = s.length();
        TrieNode curr = root;
        int i = 0;
        for(i = 0; i < len; i++){
            if(curr == null)
                return false;
            curr = curr.links[charset[i]];
        }
        if(i == len && curr == null)
            return false;
        if(curr != null && !curr.fullword)
            return false;
        return true;
    }

    public static void main (String [] args){
        Trie tr = new Trie();
        tr.insert("family");
        tr.insert("fan");
        System.out.println(tr.search("fam"));
        System.out.println(tr.search("family"));
    }
}
class TrieNode{
    char c;
    TrieNode[] links;
    boolean fullword;
    int count;
    TrieNode(char c, boolean fullword){
        this.c = c;
        links = new TrieNode[256];
        this.fullword = fullword;
        count = 0;
    }
}
