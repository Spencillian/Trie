import java.lang.reflect.Array;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args){

        Trie thing = new Trie(); // Initialize Trie

        // Add some words
        thing.insert("word", -1);
        thing.insert("wack", -1);
        thing.insert("book", -1);
        thing.insert("bone", -1);
        System.out.println(thing); // Test the print function

        // Test for find() function
        System.out.println(thing.find("book")); // true
        System.out.println(thing.find("boo")); // false

        // Test for delete()
        thing.delete("boo");
        System.out.println(thing);

        // Test insert()
        thing.insert("free", -1);
        thing.insert("freedom", -1);
        System.out.println(thing);

        // Test delete()
        thing.delete("free");
        System.out.println(thing);

        // Test delete()
        thing.delete("b");
        System.out.println(thing);

        // Insert some more test words
        thing.insert("book", -1);
        thing.insert("bomb", -1);
        thing.insert("bag", -1);
        thing.insert("bike", -1);

        // Test getWords()
        ArrayList<String> words = thing.root.getWords();

        // Print out each word
        for(String s : words){
            System.out.println(s);
        }
    }
}
