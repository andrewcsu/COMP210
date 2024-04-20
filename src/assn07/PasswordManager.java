package assn07;

import java.util.*;

import static java.lang.Math.abs;

@SuppressWarnings("unchecked")
public class PasswordManager<K,V> implements Map<K,V> {
    private static final String MASTER_PASSWORD = "password321";
    private Account<K, V>[] _passwords;

    public PasswordManager() {
        _passwords = (Account<K, V>[]) new Account[50];
    }

    // TODO: put
    @Override
    public void put(K key, V value) {
        Account<K,V> accnt = new Account<K,V>(key, value);
        int hash = key.hashCode();
        int index = abs(hash) % _passwords.length;

        if (_passwords[index] == null) {
            _passwords[index] = accnt;
//            System.out.println(key + " " + value);
        } else {
            Account<K, V> curr = _passwords[index];
            while (curr != null && curr.getNext() != null) {
                curr = curr.getNext();
            }
            if (curr != null & curr.getWebsite() != key) {
                curr.setNext(accnt);
//                System.out.println(key + " " + value);
            }
        }
    }

    // TODO: get
    @Override
    public V get(K key) {
        int hash = key.hashCode();
        int index = abs(hash) % _passwords.length;
        if (_passwords[index] != null) {
            Account<K, V> curr = _passwords[index];
            while (curr != null && !curr.getWebsite().equals(key)) {
                curr = curr.getNext();
            }
            if (curr != null && curr.getWebsite().equals(key)) {
                return curr.getPassword();
            }
        }
        return null;
    }

    // TODO: size
    @Override
    public int size() {
        int size = 0;
        for (int i = 0; i < _passwords.length; i++) {
            if (_passwords[i] != null) {
                Account<K, V> curr = _passwords[i];
                size++;
                while (curr.getNext() != null) {
                    size++;
                    curr = curr.getNext();
                }
            }
        }
        return size;
    }

    // TODO: keySet
    @Override
    public Set<K> keySet() {
        Set<K> ret = new HashSet<K>();
        for (int i = 0; i < _passwords.length; i++) {
            if (_passwords[i] != null) {
                Account<K, V> curr = _passwords[i];
                ret.add(curr.getWebsite());
                while (curr.getNext() != null) {
                    curr = curr.getNext();
                    ret.add(curr.getWebsite());
                }
            }
        }
        return ret;
    }

    // TODO: remove
    @Override
    public V remove(K key) {
        int hash = key.hashCode();
        int index = abs(hash) % _passwords.length;
        V password = null;

        if (_passwords[index] != null && !_passwords[index].getWebsite().equals(key)) {
            Account<K, V> curr = _passwords[index];
            while (curr.getNext() != null && !curr.getNext().getWebsite().equals(key)) {
                curr = curr.getNext();
            }
            if (curr != null && curr.getNext().getWebsite().equals(key)) {
                Account<K, V> temp = curr.getNext();
                password = temp.getPassword();
                curr.setNext(curr.getNext().getNext());
                return password;
            }
        } else if (_passwords[index] != null) {
            password = _passwords[index].getPassword();
            _passwords[index] = _passwords[index].getNext();
            return password;
        }

        return password;
    }

    // TODO: checkDuplicate
    @Override
    public List<K> checkDuplicate(V value) {
        List<K> ret = new ArrayList<>();
        for (int i = 0; i < _passwords.length; i++) {
            if (_passwords[i] != null) {
                Account<K, V> curr = _passwords[i];
                if (curr.getPassword() == value && !ret.contains(curr.getWebsite()) ) {
                    ret.add(curr.getWebsite());
                }
                while (curr != null) {
                    if (curr.getPassword().equals(value) && !ret.contains(curr.getWebsite())) {
                        ret.add(curr.getWebsite());
                    }
                    curr = curr.getNext();
                }
            }
        }
        if (ret.isEmpty()) { return null; }
        return ret;
    }

    // TODO: checkMasterPassword
    @Override
    public boolean checkMasterPassword(String enteredPassword) {
        return MASTER_PASSWORD.equals(enteredPassword);
    }

    @Override
    public String generatesafeRandomPassword(int length) {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = length;
        Random random = new Random();

        // TODO: Ensure the minimum length is 4
        if (length < 4) { length = 4; }

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString;
    }

    /*
    Used for testing, do not change
     */
    public Account[] getPasswords() {
        return _passwords;
    }
}
