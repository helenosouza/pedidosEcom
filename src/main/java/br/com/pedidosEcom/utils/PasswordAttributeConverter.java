package br.com.pedidosEcom.utils;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Converter
public class PasswordAttributeConverter implements AttributeConverter<String, String> {

    private static final PasswordEncoder passwordEncoder =
            PasswordEncoderFactories.createDelegatingPasswordEncoder();

    @Override
    public String convertToDatabaseColumn(String rawPassword) {
        if (rawPassword == null || rawPassword.isEmpty()) {
            return rawPassword;
        }
        if (rawPassword.startsWith("{bcrypt}")) {
            return rawPassword;
        }
        return passwordEncoder.encode(rawPassword);
    }

    @Override
    public String convertToEntityAttribute(String dbPassword) {
        return dbPassword;
    }
}
