package assn05;

import java.util.ArrayList;
import java.util.List;

public class SimpleEmergencyRoom {
    private List<Patient> patients;

    public SimpleEmergencyRoom() {
        patients = new ArrayList<>();
    }

    // TODO (Task 1): dequeue
    public Patient dequeue() {
        if (patients.isEmpty()) {
            return null;
        } else {
            Patient current = patients.get(0);
            int index = 0;
            for (int i = 0; i < patients.size(); i++) {
                if (patients.get(i).compareTo(current) < 0) {
                    current = patients.get(i);
                    index = i;
                }
            }
            patients.remove(index);
            return current;
        }
    	}

    public <V, P> void addPatient(V value, P priority) {
        Patient patient = new Patient(value, (Integer) priority);
        patients.add(patient);
    }

    public <V> void addPatient(V value) {
        Patient patient = new Patient(value);
        patients.add(patient);
    }

    public List getPatients() {
        return patients;
    }

    public int size() {
        return patients.size();
    }

}
