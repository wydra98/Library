package models;

import java.util.ArrayList;

public class UserLibrary extends User implements Comparable<UserLibrary>{

    private ArrayList<Publication> userPublication;

    public UserLibrary(String name, String surname, String pesel){
        super(name, surname, pesel);
        userPublication = new ArrayList<>();
    }

    public void addPublication(Publication pub){
        userPublication.add(pub);
    }

    public void removePublication(Publication pub){
        userPublication.remove(pub);
    }



    @Override
    public String toString() {
        String tmp = super.toString() + '\n';
        for (Publication pub : userPublication) {
            tmp += pub.toString() + '\n';
        }
        return tmp;
    }

    @Override
    public int compareTo(UserLibrary o) {
        return getPesel().compareTo(o.getPesel());
    }


}
