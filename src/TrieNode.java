import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class TrieNode {
    public String value; // A string representing the word at the end of a branch, if not the end then null
    public long weight; // A long representing how often a word is used
    public HashMap<String, TrieNode> children; // Hashmap of the children of this node


    private boolean isBranch = false; // A flag for if this node is a branch (has more than one node attached)
    private String key = null; // The key of this TrieNode


    // Additive constructor
    public TrieNode(String value, long weight){
        this.children = new HashMap<>(); // Initialize hashmap
        this.value = value; // Initialize value
        this.weight = -1; //TODO: Remove initialization after weight is implemented
    }


    // Root constructor
    public TrieNode(){
        this.children = new HashMap<>(); // Initialize hashmap
        this.value = null; // Initializes value as null
        this.weight = -1; // Initialize weight as -1
    }


    /**
     * Function to get all the words at and below the TrieNode recursively
     * @return ArrayList of words in the trie
     */
    public ArrayList<String> getWords(){
        ArrayList<String> result = new ArrayList<>(); // ArrayList to which all the words will be added to

        if(this.getValue() != null){ // If a value has been defined for this node
            result.add(this.getValue()); // Add it to the ArrayList
        }

        Set<Map.Entry<String, TrieNode>> entrySet = this.getChildren().entrySet();
        for(Map.Entry<String, TrieNode> entry : entrySet){ // Loop through all the children of this node
            TrieNode node = entry.getValue();
            result.addAll(node.getWords()); // Add the words from their children recursively
        }

        return result;
    }


    // Get the child node with the key chr
    public TrieNode getNode(String chr){
        return children.get(chr);
    }


    // Check to see if a child node exists with the key chr
    public boolean nodeExistsWithKey(String chr){
        return children.containsKey(chr);
    }


    // Adds a node to this TrieNode with key chr and a weight
    public TrieNode addNode(String chr, long weight){
        TrieNode temp = new TrieNode(null, weight); // Initialize the new node and store a reference
        temp.setKey(chr); // Set the key of the node
        children.put(chr, temp); // Add the newly created node to the hashmap
        if(children.size() >= 2){ // Check if adding a child will make this node a branch
            this.isBranch = true; // Set isBranch to true if so
        }
        return temp; // Return the newly created node
    }


    // Returns the hashmap containing the children of this node
    public HashMap<String, TrieNode> getChildren() {
        return children;
    }


    // Dereferences the a child given a key chr
    public void destroyChild(String chr){
        children.remove(chr); // Child absolutely rekt
    }


    // Set the value word of the node
    public void setValue(String value){
        this.value = value;
    }


    // Get the value word of the node
    public String getValue(){
        return value;
    }


    // Set the key of the child (for child based looping/recursive logic)
    public void setKey(String key) {
        this.key = key;
    }


    // Get the key of the child (for child based looping/recursive logic)
    public String getKey() {
        return key;
    }


    // Returns if this node is branch or not
    public boolean isBranch(){
        return isBranch;
    }
}
