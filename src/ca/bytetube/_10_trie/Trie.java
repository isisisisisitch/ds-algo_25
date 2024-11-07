package ca.bytetube._10_trie;

/**
 * 1.给n个字符串，在这n个字符串中是否存在以"xxx"作为前缀的单词
 * 2.给n个字符串，在这n个字符串包含"xxx"作为前缀出现的次数
 * 3.给n个字符串，求以"xxx"结尾的单词出现的次数
 */
public class Trie {
    TrieNode root;


    public Trie() {
        root = new TrieNode();
    }

    private static class TrieNode {
        int path;//有多少条路径到达过这个节点
        int end;//有多少个字符串以这个节点为终点
        TrieNode[] nexts;//可走的路

        public TrieNode() {
            path = 0;
            end = 0;
            nexts = new TrieNode[26];//只支持小写字母
        }


    }


    public void add(String word) {
        if (word == null) return;

        char[] chs = word.toCharArray();
        TrieNode node = root;
        int index = 0;
        for (int i = 0; i < chs.length; i++) {
            index = chs[i] - 'a';
            if (node.nexts[index] == null) {
                node.nexts[index] = new TrieNode();
            }

            node = node.nexts[index];
            node.path++;

        }

        node.end++;

    }

    public void delete(String word) {
        if (search(word) != 0) {
            char[] chs = word.toCharArray();
            TrieNode node = root;
            int index = 0;
            for (int i = 0; i < chs.length; i++) {
                index = chs[i] - 'a';
                if (--node.nexts[index].path == 0) {//当遇到0之后，不需要继续向下遍历，剩余部分的字符串会被gc回收
                    node.nexts[index] = null;
                    return;
                }

                node = node.nexts[index];

            }

            node.end--;

        }
    }

    public int search(String word) {//查询一个word出现的次数
        if (word == null) return 0;

        char[] chs = word.toCharArray();
        TrieNode node = root;
        int index = 0;
        for (int i = 0; i < chs.length; i++) {
            index = chs[i] - 'a';
            if (node.nexts[index] == null) {
                return 0;
            }

            node = node.nexts[index];

        }

        return node.end;

    }

    public int prefix(String pre) {//查询一个word出现的次数
        if (pre == null) return 0;

        char[] chs = pre.toCharArray();
        TrieNode node = root;
        int index = 0;
        for (int i = 0; i < chs.length; i++) {
            index = chs[i] - 'a';
            if (node.nexts[index] == null) {
                return 0;
            }

            node = node.nexts[index];
        }
        return node.path;

    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.add("abc");
        trie.add("abc");
        trie.add("kgftr");
        trie.add("kgftwq");

        System.out.println(trie.search("abc"));
        trie.delete("abc");
        System.out.println(trie.search("abc"));
        trie.delete("kgftwq");
        System.out.println(trie.search("kgftr"));
    }


}
