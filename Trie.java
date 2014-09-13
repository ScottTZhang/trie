import java.util.*;
public class Trie{
    TrieNode root;

    public Trie(){
        root = new TrieNode();
    }

    public void insert(String s){
        if(isWord(s))
            return;
        TrieNode curr = root;
        for(int i = 0; i < s.length(); i++){
            if(curr.set[s.charAt(i)] == null){
                curr.setNode((int)s.charAt(i), false);
            }
            curr = curr.set[s.charAt(i)];
            curr.count++;
        }
        curr.fullword = true;
    }

    public boolean isWord(String s){
        TrieNode curr = root;
        int i = 0;
        for(i = 0; i < s.length(); i++){
            if(curr == null)
                return false;
            curr = curr.set[s.charAt(i)];
        }
        if(i == s.length() && curr == null)
            return false;
        if(curr != null && !curr.fullword)
            return false;
        return true;
    }

    public boolean isPrefix(String s){
        TrieNode curr = root;
        int i = 0;
        for(i = 0; i < s.length(); i++){
            if(curr == null)
                return false;
            curr = curr.set[s.charAt(i)];
        }
        if(i == s.length() && curr == null)
            return false;
        return true;
    }

    public boolean delete(String s){
        TrieNode curr = root;
        boolean canFind = isWord(s);
        if(!canFind)
            return false;
        else{
            Stack<TrieNode> stack = new Stack<TrieNode>();
            for(int i = 0; i < s.length(); i++){
                curr = curr.set[s.charAt(i)];
                stack.push(curr);
            }
            curr.fullword = false;
            //while(stack.size() > 0){
                //curr = stack.pop();
                //curr.count--;
                //if(curr.count == 0)
                    //curr = null;
            //}
            return true;
        }
    }

    public void printTrie(){
        TrieNode curr = root;
        ArrayList<String> res = new ArrayList<String>();
        StringBuilder sb = new StringBuilder();
        dfs(res, curr, sb);
        System.out.println(res);
    }
    void dfs(ArrayList<String> res, TrieNode node, StringBuilder pre){
        if(node.fullword == true){
            res.add(pre.toString());
            return;
        }
        for(int i = 0; i < 256; i++){
            if(node.set[i] != null){
                pre.append((char)i);
                dfs(res, node.set[i], pre);
                pre.deleteCharAt(pre.length() - 1);
            }
        }
    }

    public static void main (String [] args){
        Trie tr = new Trie();
        tr.insert("family");
        tr.insert("fan");
        System.out.println(tr.isWord("fam"));
        System.out.println(tr.isPrefix("fam"));
        System.out.println(tr.isWord("family"));
        tr.insert("abc");
        tr.insert("abe");
        tr.printTrie();
        System.out.println(tr.delete("abd"));
        System.out.println(tr.delete("abe"));
        System.out.println(tr.delete("abe"));
        tr.printTrie();
    }
}
class TrieNode{
    TrieNode[] set;
    boolean fullword;
    int count;
    TrieNode(){
        set = new TrieNode[256];
        count = 0;
    }
    void setNode(int c, boolean f){
        set[c] = new TrieNode();
        fullword = f;
    }
}
