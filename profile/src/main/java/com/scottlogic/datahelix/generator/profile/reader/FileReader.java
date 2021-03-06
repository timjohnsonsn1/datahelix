/*
 * Copyright 2019 Scott Logic Ltd
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.scottlogic.datahelix.generator.profile.reader;

import com.google.inject.Inject;
import com.scottlogic.datahelix.generator.common.whitelist.DistributedList;
import com.scottlogic.datahelix.generator.common.whitelist.WeightedElement;

import java.io.File;
import java.util.stream.Collectors;

public class FileReader {
    private final CsvInputStreamReaderFactory csvReaderFactory;

    @Inject
    public FileReader(CsvInputStreamReaderFactory csvReaderFactory) {
        this.csvReaderFactory = csvReaderFactory;
    }

    public DistributedList<Object> setFromFile(File file) {
        CsvInputReader reader = csvReaderFactory.getReaderForFile(file);
        DistributedList<String> names = reader.retrieveLines();

        return new DistributedList<>(
            names.distributedList().stream()
                .map(holder -> new WeightedElement<>((Object) holder.element(), holder.weight()))
                .distinct()
                .collect(Collectors.toList()));
    }

    public DistributedList<String> listFromMapFile(File file, String key) {
        CsvInputReader reader = csvReaderFactory.getReaderForFile(file);
        DistributedList<String> names = reader.retrieveLines(key);

        return new DistributedList<>(
            names.distributedList().stream()
                .map(holder -> new WeightedElement<>(holder.element(), holder.weight()))
                .collect(Collectors.toList()));
    }
}
