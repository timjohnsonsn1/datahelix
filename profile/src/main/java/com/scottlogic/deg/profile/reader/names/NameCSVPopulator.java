package com.scottlogic.deg.profile.reader.names;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class NameCSVPopulator implements NamePopulator<String> {

    @Override
    public Set<NameFrequencyHolder> retrieveNames(String input) {
        return readFromFile(pathFromClasspath(input));
    }

    private InputStream pathFromClasspath(String classPath) {
        return Optional.ofNullable(this.getClass()
            .getClassLoader()
            .getResourceAsStream(classPath)
        ).orElseThrow(() -> new IllegalArgumentException("Path not found on classpath."));
    }

    private Set<NameFrequencyHolder> readFromFile(InputStream is) {
        try {
            return CSVParser.parse(
                is,
                Charset.defaultCharset(),
                CSVFormat.DEFAULT)
                .getRecords()
                .stream()
                .map(record -> new NameFrequencyHolder(record.get(0), Integer.valueOf(record.get(1))))
                .collect(Collectors.toSet());
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

}