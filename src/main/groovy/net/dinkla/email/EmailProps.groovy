package net.dinkla.email

/**
 * Created by Dinkla on 10.05.2016.
 */
class EmailProps {

    final String protocol
    final String host
    final String user
    final String password

    EmailProps(String protocol, String host, String user, String password) {
        this.protocol = protocol
        this.host = host
        this.user = user
        this.password = password
    }

    // reads the properties from a file
    static EmailProps readFromFile(String filename) {
        Properties properties = new Properties()
        File propertiesFile = new File(filename)
        propertiesFile.withInputStream {
            properties.load(it)
        }
        def ep = new EmailProps(
                properties.protocol,
                properties.host,
                properties.user,
                properties.password)
        return ep
    }

}
