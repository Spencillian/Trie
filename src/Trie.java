import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public class Trie {
    public TrieNode root; // Declare root node


    // Constructor
    public Trie(){
        root = new TrieNode(); // Initialize root node
    }


    /**
     * Inserts a word into the trie
     * @param value the word being inserted into the trie
     * @param weight measure of how often the word is used (currently not fully implemented)
     */
    public void insert(String value, long weight){
        String[] word = value.split(""); // Turn the value into a String array

        TrieNode cursorNode = root; // Initialize a cursor for looking through the tree

        for(String s : word){ // Loop through all letters in the word
            if(cursorNode.nodeExistsWithKey(s)){ // Check if the node has children with the right letter key
                cursorNode = cursorNode.getNode(s); // If it does, set the cursor to that node
            }else{
                cursorNode = cursorNode.addNode(s, -1); // Else add a new node to the cursor
            }
        }

        cursorNode.setValue(value); // Set the value of the final node
    }


    /**
     * Delete a prefix from the trie
     * @param prefix the prefix that is to be removed from the tree
     * @return True if prefix is removed ? False if prefix is not in trie
     */
    public boolean delete(String prefix){
        String[] word = prefix.split(""); // Turn the prefix into a String array for looping

        TrieNode cursorNode = root; // Initialize a cursor for traversing the tree

        TrieNode preen = null; // Stores where the trie branch should be cut
        String preenChr = null; // Stores the character of the TrieNode branch being cut

        for(String s : word){ // Loop over every character in the word
            if(cursorNode.nodeExistsWithKey(s)){ // Check if a node exists with a key that is the current letter in the word
                if(cursorNode.isBranch()){ // If the node is a branch (has two or more branches)
                    preen = cursorNode; // Set the preen to cursorNode
                    preenChr = s; // Set the preenChr to the letter in the word
                    // (this is so that later the parent can dereference the child with this key)
                }

                cursorNode = cursorNode.getNode(s); // Travel one link down the trie
            }else{
                return false; // return false if the prefix is not in the trie
            }
        }

        if(preen == null){ // If the preen location is null and the word is in the trie
            root.destroyChild(word[0]); // Remove the branch with the first letter of the word
        }else {
            preen.destroyChild(preenChr); // Else destroy child with the preen key
        }

        return true;
    }


    /**
     * Search the trie to see if a word exists
     * @param word specified word that needs to be found
     * @return True if the word is in the trie ? False if not
     */
    public boolean find(String word){
        String[] str = word.split(""); // Turn word into a String[]

        TrieNode cursorNode = root; // Initialize a cursor for traversing the trie

        for(String s : str){ // Loop through the word letter by letter
            if(cursorNode.nodeExistsWithKey(s)){ // Check if a node exists with a key that matches the letter
                cursorNode = cursorNode.getNode(s); // Travel down the trie
            }else{
                return false; // If letter not in trie return false
            }
        }

        return cursorNode.getValue() != null; // return false if word is in the trie but has no value, otherwise true
    }

    /**
     * Turns the trie into a semi-readable string
     * @return string representation of the trie
     */
    public String toString(){
        StringBuilder builder = new StringBuilder(); // Initialize a StringBuilder

        // Initialize arrays to store the nodes on the current and next layer of the trie
        ArrayList<TrieNode> currLine = new ArrayList<>();
        ArrayList<TrieNode> nextLine = new ArrayList<>();

        // Add the root node the current array
        currLine.add(root);

        while(!currLine.isEmpty()){ // Keep looping until there are no more nodes added to the current array
            for(TrieNode node : currLine){ // Loop through every node in the current array

                if(node.getChildren().isEmpty()){ // If the node has no children, no information is added to the string
                    continue;
                }

                if(node != root){ // If the node is not root, specify the node key
                    builder.append(" ").append(node.getKey()).append(" >");
                }

                builder.append(" [ "); // Signifying the children of a node

                Set<Map.Entry<String, TrieNode>> entrySet = node.getChildren().entrySet();
                for(Map.Entry<String, TrieNode> entry : entrySet){ // Loop through the children in that node

                    String key = entry.getKey();
                    builder.append(key).append(" "); // Add the key of the children to the string

                    TrieNode val = entry.getValue();
                    nextLine.add(val); // Add the node to the next set of nodes to be looked through
                }

                builder.append("]"); // Balance the delimiter
            }
            currLine = (ArrayList<TrieNode>) nextLine.clone(); // Set the current line to next line
            nextLine.clear(); // clear the next line for the next pass
            builder.append('\n'); // Begin the next line of the string
        }

        return builder.toString(); // Turn the accumulation in to a string and return it
    }
}
