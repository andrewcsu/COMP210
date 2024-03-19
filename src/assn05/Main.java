package assn05;

public class Main {

    public static void main(String[] args) {
//        testP1();
//        testP2();
//        testP3();
        testP4();
    }

    // test Part 1
    public static void testP1(){
        SimpleEmergencyRoom simplePQ = new SimpleEmergencyRoom();
        simplePQ.addPatient(1,2);
        simplePQ.addPatient(2,3);
        simplePQ.addPatient(3,5);
        simplePQ.addPatient(4,1);
        simplePQ.addPatient(5,4);
        simplePQ.addPatient(6,8);
        simplePQ.addPatient(7,9);
        simplePQ.addPatient(8,11);
        simplePQ.addPatient(9,10);
        System.out.println(simplePQ.dequeue().getValue());
        System.out.println(simplePQ.dequeue().getValue());
    }

    // test Part 2
    public static void testP2(){
        MaxBinHeapER complexER = new MaxBinHeapER();
        complexER.enqueue(1,2);
        complexER.enqueue(6,8);
        complexER.enqueue(7,9);
        complexER.enqueue(8,11);
        complexER.enqueue(9,10);
        complexER.enqueue(2,3);
        complexER.enqueue(3,5);
        complexER.enqueue(4,1);
        complexER.enqueue(5,4);
        System.out.println(complexER.size());

        Prioritized[] arrayTest = complexER.getAsArray();
        for (int i=0; i<arrayTest.length; i++){
            System.out.print(arrayTest[i].getValue());
            System.out.print(", ");
            System.out.println(arrayTest[i].getPriority());
        }
        for (int i=0; i<arrayTest.length; i++){
            System.out.println(complexER.dequeue());
        }


    }

    /*
    Part 3
     */
    public static void testP3(){
        MaxBinHeapER transfer = new MaxBinHeapER(makePatients());
        Prioritized[] arr = transfer.getAsArray();
        for(int i = 0; i < transfer.size(); i++) {
            System.out.println("Value: " + arr[i].getValue()
                    + ", Priority: " + arr[i].getPriority());
        }
    }

    /*
    Part 4
     */
    public static void testP4() {
        double[] results = compareRuntimes();
        System.out.println("Simple PQ: " + results[0] + " ns, " + results[1] + " ms");
        System.out.println("Complex PQ: " + results[2] + " ns, " + results[3] + " ms");

    }

    public static void fillER(MaxBinHeapER complexER) {
        for(int i = 0; i < 100000; i++) {
            complexER.enqueue(i);
        }
    }
    public static void fillER(SimpleEmergencyRoom simpleER) {
        for(int i = 0; i < 100000; i++) {
            simpleER.addPatient(i);
        }
    }

    public static Patient[] makePatients() {
        Patient[] patients = new Patient[10];
        for(int i = 0; i < 10; i++) {
            patients[i] = new Patient(i);
        }
        return patients;
    }

    public static double[] compareRuntimes() {
        // Array which you will populate as part of Part 4
        double[] results = new double[4];

        SimpleEmergencyRoom simplePQ = new SimpleEmergencyRoom();
        fillER(simplePQ);

        // Code for (Task 4.1) Here
        long start = System.nanoTime();
        for (int i =0; i<simplePQ.size(); i++) {
            simplePQ.dequeue();
        }
        long end = System.nanoTime();
        results[0] = (end - start);
        results[1] = (end - start) / 100000.0;

        MaxBinHeapER binHeap = new MaxBinHeapER();
        fillER(binHeap);

        // Code for (Task 4.2) Here
        start = System.nanoTime();
        for (int i =0; i<binHeap.size(); i++) {
            binHeap.dequeue();
        }
        end = System.nanoTime();
        results[2] = (end - start);
        results[3] = (end - start) / 100000.0;
        return results;
    }

}
