package net.dinkla.imap

import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

/**
 * Created by Dinkla on 10.05.2016.
 */
class EmailServerProperties {

    @NotNull
    @Size(min=2, max=40)
    String protocol

    @NotNull
    @Size(min=1, max=40)
    String host

    @NotNull
    @Size(min=1, max=40)
    String user

    @NotNull
    @Size(min=1, max=40)
    String password

    @NotNull
    @Size(min=1, max=40)
    String folder

    EmailServerProperties() {
    }

    EmailServerProperties(String protocol, String host, String user, String password, String folder) {
        this.protocol = protocol
        this.host = host
        this.user = user
        this.password = password
        this.folder = folder
    }

    // reads the properties from a file
    static EmailServerProperties readFromFile(String filename) {
        Properties properties = new Properties()
        File propertiesFile = new File(filename)
        propertiesFile.withInputStream {
            properties.load(it)
        }
        def ep = new EmailServerProperties(
                properties.protocol,
                properties.host,
                properties.user,
                properties.password,
                properties.folder)
        return ep
    }

    @Override
    public String toString() {
        return "EmailServerProperties{" +
                "protocol='" + protocol + '\'' +
                ", host='" + host + '\'' +
                ", user='" + user + '\'' +
                ", password='" + password + '\'' +
                ", folder='" + folder + '\'' +
                '}';
    }
}
