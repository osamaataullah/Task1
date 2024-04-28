    package com.example.config;

    import org.springframework.beans.factory.annotation.Value;
    import org.springframework.stereotype.Component;

    import java.io.FileInputStream;
    import java.io.IOException;
    import java.util.Properties;

    @Component
    public class PropertyFileReader {

        @Value("${user.property.file.path}")
        private String userPropertyFilePath;

        public String getWorkstationForUser(String user) throws IOException {
            Properties properties = new Properties();
            try (FileInputStream fis = new FileInputStream(userPropertyFilePath)) {
                properties.load(fis);
                return properties.getProperty(user);
            }
        }
    }
