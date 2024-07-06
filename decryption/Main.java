import java.util.HashMap;
import java.util.Scanner;
class Node {
    char letter;
    Node next;
    Node prev;

    Node(char letter) {
        this.letter = letter;
        this.next = null;
        this.prev = null;
    }
}

class Rotor {
    Node[] wiring;
    Node front;
    Node back;

    Rotor(String wiring) {
        this.wiring = new Node[26];
        this.front = new Node('A');
        this.back = new Node('A');

        Node current = this.front;
        for (int i = 0; i < wiring.length(); i++) {
            Node newNode = new Node(wiring.charAt(i));
            current.next = newNode;
            newNode.prev = current;
            this.wiring[i] = newNode;
            current = newNode;
        }
        current.next = this.back;
        this.back.prev = current;

        // Connect front and back
        this.front.prev = this.back;
        this.back.next = this.front;
    }

    void rotate() {
        Node temp = this.front;
        this.front = this.front.next;
        this.back = this.back.next;
        this.front.prev = null;
        this.back.next = null;
        temp.prev = this.back;
        this.back.next = temp;
    }

    char encrypt(char input) {
        int index = input - 'A';
        return this.wiring[index].letter;
    }

    char decrypt(char input) {
        for (int i = 0; i < 26; i++) {
            if (this.wiring[i].letter == input) {
                return (char) (i + 'A');
            }
        }
        return input;
    }
}

class Reflector {
    private HashMap<Character, Character> mapping;

    Reflector(String mapping) {
        this.mapping = new HashMap<>();
        for (int i = 0; i < mapping.length(); i += 2) {
            char first = mapping.charAt(i);
            char second = mapping.charAt(i + 1);
            this.mapping.put(first, second);
            this.mapping.put(second, first);
        }
    }

    char reflect(char input) {
        return this.mapping.get(input);
    }
}

class Plugboard {
    private HashMap<Character, Character> mapping;

    Plugboard(String mapping) {
        this.mapping = new HashMap<>();
        for (int i = 0; i < mapping.length(); i += 2) {
            char first = mapping.charAt(i);
            char second = mapping.charAt(i + 1);
            this.mapping.put(first, second);
            this.mapping.put(second, first);
        }
    }

    char swap(char input) {
        return this.mapping.getOrDefault(input, input);
    }
}

class EnigmaMachine {
    private Rotor[] rotors;
    private Reflector reflector;
    private Plugboard plugboard;

    EnigmaMachine(int[] rotorConfigurations, String reflectorConfiguration, String plugboardConfiguration) {
        this.rotors = new Rotor[3];
        for (int i = 0; i < rotors.length; i++) {
            this.rotors[i] = createRotor(rotorConfigurations[i]);
        }
        this.reflector = new Reflector(reflectorConfiguration);
        this.plugboard = new Plugboard(plugboardConfiguration);
    }
    private int nsum(int n){
        if(n<10){
            return n;
        }
        else{
            int s=0;
            while(n>0){
                s=s+n%10;
                n=n/10;
            }
            return nsum(s);
        }
    }
    private Rotor createRotor(int rotorNumber) {
        // Rotor configurations
        String[] rotorConfigs = {
                "EKMFLGDQVZNTOWYHXUSPAIBRCJ",
                "AJDKSIRUXBLHWTMCQGZNPYFVOE",
                "BDFHJLCPRTXVZNYEIWGAKMUSQO",
                "ESOVPZJAYQUIRHXLNFTGKDCMWB",
                "VZBRGITYUPSDNHLXAWMJQOFECK",
                "JPGVOUMFYQBENHZRDKASXLICTW",
                "NZJHGRCXMYSWBOUFAIVLPEKQDT",
                "FKQHTLXOCBJSPDZRAMEWNIUYGV",
                "ROTBALNSKDJFHGCIPEMUVQZYWX",
                "DMTWSILRUYQNKFEJCAZBPGXOHV"
        };
        return new Rotor(rotorConfigs[nsum(rotorNumber)]);
    }

    void rotateRotors() {
        // Rotate the rightmost rotor always
        this.rotors[rotors.length - 1].rotate();

        // Check if middle rotors need to rotate
        for (int i = rotors.length - 1; i > 0; i--) {
            if (this.rotors[i].front.letter == 'A') {
                this.rotors[i - 1].rotate();
            } else {
                break;
            }
        }
    }

    char encrypt(char input) {
        // Pass through plugboard
        char output = this.plugboard.swap(input);

        // Pass through rotors from right to left
        for (int i = rotors.length - 1; i >= 0; i--) {
            output = this.rotors[i].encrypt(output);
        }

        // Reflector
        output = this.reflector.reflect(output);

        // Pass through rotors from left to right (in reverse order)
        for (int i = 0; i < rotors.length; i++) {
            output = this.rotors[i].decrypt(output);
        }

        // Pass through plugboard again
        output = this.plugboard.swap(output);

        // Rotate rotors
        rotateRotors();

        return output;
    }
}

public class Main {
    public static void main(String[] args) {
        // Rotor configurations specified by numbers from 0 to 9
        int[] rotorConfigurations = new int[10];
        Scanner scan=new Scanner(System.in);
        for(int i=0;i<10;i++){
            rotorConfigurations[i]=scan.nextInt();
        }

        // Reflector configuration
        String reflectorConfiguration = "YRUHQSLDPXNGOKMIEBFZCWVJAT";

        // Plugboard configuration
        String plugboardConfiguration = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        // Create Enigma Machine with manually defined rotor configurations
        EnigmaMachine enigmaMachine = new EnigmaMachine(rotorConfigurations, reflectorConfiguration, plugboardConfiguration);

        // Message to encrypt
        System.out.print("Enter encrypted text: ");
        String message = scan.next();

        // Encrypt message
        StringBuilder encryptedMessage = new StringBuilder();
        for (char letter : message.toCharArray()) {
            encryptedMessage.append(enigmaMachine.encrypt(letter));
        }

        System.out.println("Decrypted message: " + encryptedMessage);
    }
}
