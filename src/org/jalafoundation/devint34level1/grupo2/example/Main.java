package org.jalafoundation.devint34level1.grupo2.example;

public class Main {

  public static void main(String[] args) {
    Menu menu = new Menu();

    boolean terminated = false;

    do {
      terminated = menu.run();
    } while (!terminated);
  }
}
