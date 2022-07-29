package com.techelevator.application;

import com.techelevator.ui.UserInput;
import com.techelevator.ui.UserOutput;
import models.Candy;
import models.Drink;
import models.Gum;
import models.Munchy;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VendingMachine extends Inventory{
    private List<Inventory> item;

    Inventory inventory = new Inventory();
    public void run() {
        readFile();

        while (true) {
            UserOutput.displayHomeScreen();
            String choice = UserInput.getHomeScreenOption();
            System.out.println(choice);
            if (choice.equals("display")) {
                // display the items
               UserOutput.displayItems(item);

            } else if (choice.equals("purchase")) {
                Money money = new Money();
            while (true){
                UserOutput.displayPurchaseScreen();
                choice = UserInput.getPurchaseScreenOption(money);
                System.out.println(choice);
                if (choice.equals("Feed Money")) {
                    money.feedMoney();
                } else if (choice.equals("Select Item")){
                    UserOutput.displayItems(item);
                    selectItem(item);
                }
            }
                // make a purchase
            } else if (choice.equals("exit")) {
                // good bye
                break;
            }
        }
    }

    public void readFile() {
        item = new ArrayList<>();
        try {
            Scanner fileInput = new Scanner(new File("vending.csv"));
            while (fileInput.hasNextLine()) {
                String line = fileInput.nextLine();

                String[] lineArray = line.split(",");
                if (lineArray[3].equals("Munchy")) {
                    item.add(new Munchy(lineArray[0], lineArray[1], new BigDecimal(lineArray[2])));
                } else if (lineArray[3].equals("Gum")) {
                    item.add(new Gum(lineArray[0], lineArray[1], new BigDecimal(lineArray[2])));
                } else if (lineArray[3].equals("Drink")) {
                    item.add(new Drink(lineArray[0], lineArray[1], new BigDecimal(lineArray[2])));
                } else if (lineArray[3].equals("Candy")) {
                    item.add(new Candy(lineArray[0], lineArray[1], new BigDecimal(lineArray[2])));
                }
            }
        }catch(FileNotFoundException e){
            System.out.println("File not found");
        }
    }
    public void selectItem(List<Inventory> item) {
        Scanner selectItem = new Scanner(System.in);
        System.out.print("Enter slot code: ");
        String itemSelected = selectItem.nextLine();
        Inventory inventory = new Inventory();
        try {
            Scanner fileInput = new Scanner(new File("vending.csv"));
            while (fileInput.hasNextLine()){
                String line = fileInput.nextLine();
                String[] lineArray = line.split(",");
                for (int i = 0; i < lineArray.length; i++) {
                    if (lineArray[0].contains(itemSelected)) {
                        System.out.print(lineArray[i] + " ");
                    }
                }
            }
        } catch (FileNotFoundException e){
            System.out.println("Item not found");
        }
        }
    }

