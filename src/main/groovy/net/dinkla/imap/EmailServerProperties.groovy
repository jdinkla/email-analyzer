package net.dinkla.imap

import org.hibernate.validator.constraints.NotBlank

import javax.validation.constraints.NotNull

/**
 * Created by Dinkla on 10.05.2016.
 */
class EmailServerProperties {

    @NotNull
    String protocol

    @NotNull
    String host

    @NotNull
    @NotBlank
    String user

    @NotNull
    @NotBlank
    String password

    @NotNull
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
                properties.password)
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
