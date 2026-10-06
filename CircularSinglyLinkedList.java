import java.util.Scanner;

class CircularSinglyLinkedList {

    static class Node {
        String player;
        Node next;

        Node(String player) {
            this.player = player;
        }
    }

    Node last = null;

    void insert(String player) {
        Node newNode = new Node(player);

        if (last == null) {
            last = newNode;
            last.next = last;
        } else {
            newNode.next = last.next;
            last.next = newNode;
            last = newNode;
        }

        System.out.println(player + " joined the game.");
    }

    void delete(String player) {
        if (last == null) {
            System.out.println("No players in the game.");
            return;
        }

        Node current = last.next;
        Node previous = last;

        do {
            if (current.player.equals(player)) {
                if (current == last && current.next == last) {
                    last = null;
                } else {
                    previous.next = current.next;

                    if (current == last) {
                        last = previous;
                    }
                }

                System.out.println(player + " left the game.");
                return;
            }

            previous = current;
            current = current.next;

        } while (current != last.next);

        System.out.println(player + " not found.");
    }

    void traverse() {
        if (last == null) {
            System.out.println("No players in the game.");
            return;
        }

        Node current = last.next;

        do {
            System.out.print(current.player + " -> ");
            current = current.next;
        } while (current != last.next);

        System.out.println("(back to " + last.next.player + ")");
    }

    void playTurns(int rounds) {
        if (last == null) {
            System.out.println("No players available.");
            return;
        }

        Node current = last.next;

        for (int i = 1; i <= rounds; i++) {
            System.out.println("Turn " + i + ": " + current.player);
            current = current.next;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CircularSinglyLinkedList game = new CircularSinglyLinkedList();

        while (true) {
            System.out.println("\n1. Insert Player");
            System.out.println("2. Delete Player");
            System.out.println("3. Display Players");
            System.out.println("4. Show Turns");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter player name: ");
                    game.insert(sc.nextLine());
                    break;

                case 2:
                    System.out.print("Enter player name to delete: ");
                    game.delete(sc.nextLine());
                    break;

                case 3:
                    game.traverse();
                    break;

                case 4:
                    System.out.print("Enter number of turns: ");
                    game.playTurns(sc.nextInt());
                    break;

                case 5:
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}