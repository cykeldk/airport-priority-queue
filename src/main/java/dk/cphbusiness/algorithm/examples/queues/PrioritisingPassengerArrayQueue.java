/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.cphbusiness.algorithm.examples.queues;

import dk.cphbusiness.airport.template.Passenger;

/**
 *
 * @author Michael
 */
public class PrioritisingPassengerArrayQueue implements PriorityQueue {

    Passenger[] pass;
    int last;
    int root;
    int pointer = 1;
    int size = 0;

    public PrioritisingPassengerArrayQueue(int capacity) {
        pass = new Passenger[capacity];
    }

    @Override
    public void enqueue(Object item) {
        Passenger p = (Passenger) item;
        size++;
        
        pass[size] = p;
        swapUp(size);
        
    }

    @Override
    public Object dequeue() {
        Passenger temp = pass[1];
        
        swap(1, size);
        size--;
        heapify(1);
        return temp;
    }

    @Override
    public Object peek() {
        return pass[1];
    }

    @Override
    public int size() {
        return size;
    }

    private void swapUp(int index) {
        if (index == 1) {
            return;
        }
        Passenger current = pass[index];
        if (current.compareTo(getParent(index)) < 0) {
            
            swap(index, index / 2);
            
            swapUp(index / 2);
        }
//        System.out.println("done swapping up");
        // am i right child?
        if (index%2!=0){
//            System.out.println("is right child..");
            if(current.compareTo(pass[index-1]) < 0){
//                System.out.println("swapping left");
                swap(index, index-1);
                swapUp(index-1);
            }
        }
    }

    private void swap(int one, int two) {
        if (one==two) return;
        Passenger temp = pass[one];
        pass[one] = pass[two];
        pass[two] = temp;
        System.out.println("SWAPPED: " + pass[one] + " at index: [" + one + "] WITH " + pass[two] + " at index: [" + two + "]");
        System.out.println("it's " + (pass[one] == pass[two]) + " that they are equal");
    }

    private Passenger getParent(int index) {
        return pass[index / 2];
    }

    private Passenger leftChild(int index) {
        return pass[index * 2];
    }

    private Passenger rightChild(int index) {
        return pass[index * 2 + 1];
    }

    private void heapify(int index) {
//        System.out.println("heapifying" + pass[index] + " at index:" + index);
        Passenger current = pass[index];
//        System.out.println("current passenger: " + current);
        int greaterChild = 0;
        
        if (leftChild(index) == null) {
            return;
        }
        if (rightChild(index) != null) {
            greaterChild = (pass[index * 2].compareTo(pass[index * 2 + 1]) > 0 ? index * 2 : index * 2 + 1);
            if (current.compareTo(pass[greaterChild])<0) {
                swap(greaterChild, index);
                heapify(greaterChild);
                return;
            }
        }
        
        if (current.compareTo(leftChild(index)) < 0) {
            swap(index * 2, index);
            heapify(index * 2);
            return;
        }
//        System.out.println("done heapifying " + pass[index] + " from index " + index);
    }
    
    public void printQueue(){
        System.out.println("\n-------------printing passengers list: -----------");
        System.out.println("index 0: " + pass[0]);
        System.out.println("array length: " + pass.length);
        for (int i = 1; i <= size; i++) {
            System.out.println(pass[i] + " at index [" + i + "]");
            
        }
        System.out.println("-----------done printing passengers list----------\n");
    }
}
