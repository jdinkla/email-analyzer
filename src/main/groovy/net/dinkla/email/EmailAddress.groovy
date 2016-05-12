package net.dinkla.email

/**
 * Created by Dinkla on 11.05.2016.
 */
class EmailAddress implements Serializable {

    String orig
    String name
    String email

    // needed by jackson
    EmailAddress() {
    }

    EmailAddress(String orig) {
        this.orig = orig
        def ps = orig.split("<")
        if (ps.size() == 1) {
            name = ""
            email = orig
        } else if (ps.size() == 2) {
            name = ps[0].trim()
            email = ps[1].substring(0, ps[1].size()-1)
        } else {
            throw new RuntimeException("Unknown email format: '$orig'")
        }
    }

    @Override
    public String toString() {
        return "EmailAddress{" +
                "orig='" + orig + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
