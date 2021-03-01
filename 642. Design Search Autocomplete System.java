class AutocompleteSystem {
  class Node {
    Map<Character, Node> children;
    Map<String, Integer> freq; // string -> frequency
    boolean isLeaf;
    
    public Node() {
      children = new HashMap<>();
      freq = new HashMap<>();
      isLeaf = false;
    }
  }
  
  class Pair {
    String s;
    int c;
    
    public Pair(String s, int c) {
      this.s = s;
      this.c = c;
    }
  }
  
  Node root;
  StringBuilder currInput;

  public AutocompleteSystem(String[] sentences, int[] times) {
    root = new Node();
    currInput = new StringBuilder();
    
    // build the trie
    for (int i = 0; i < sentences.length; i++) {
      add(sentences[i], times[i]);
    }
  }
  
  // add word to trie
  private void add(String word, int count) {
    Node node = root;
    for (char c : word.toCharArray()) {
      if (!node.children.containsKey(c)) {
        node.children.put(c, new Node());
      }
      node = node.children.get(c); // go to the child node
      node.freq.put(word, node.freq.getOrDefault(word, 0) + count); // update word's frequency
    }
    node.isLeaf = true; // the end of the word
  }

  public List<String> input(char c) {
    // edge case
    if (c == '#') { // add the currInput into trie
      String input = currInput.toString();
      add(input, 1);
      currInput = new StringBuilder(); // reset
      return new ArrayList<>();
    }
    
    currInput.append(c);
    Node node = root; // search the new input
    String str = currInput.toString();
    for (char ch : str.toCharArray()) {
      if (!node.children.containsKey(ch)) return new ArrayList<>();
      node = node.children.get(ch);
    }
    
    // now we are at the last letter of the string
    // sort the entry by frequency, need a max heap to get the top 3 sentences
    PriorityQueue<Pair> maxHeap = new PriorityQueue<>(
      (a, b) -> (a.c == b.c) ? a.s.compareTo(b.s) : b.c - a.c
    );
    for (Map.Entry<String,Integer> entry : node.freq.entrySet()) {
      maxHeap.add(new Pair(entry.getKey(), entry.getValue()));
    }
    
    List<String> res = new ArrayList<>();
    for (int i = 0; i < 3 && !maxHeap.isEmpty(); i++) {
      res.add(maxHeap.poll().s);
    }
    return res;
  }
}

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */