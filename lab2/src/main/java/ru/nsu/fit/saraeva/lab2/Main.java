package ru.nsu.fit.saraeva.lab2;

import org.apache.commons.compress.compressors.CompressorException;
import org.apache.commons.compress.compressors.CompressorInputStream;
import org.apache.commons.compress.compressors.CompressorStreamFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.nsu.fit.saraeva.lab2.database.DAOManager;
import ru.nsu.fit.saraeva.lab2.enums.LoggerMessages;
import ru.nsu.fit.saraeva.lab2.openStreetMap.Statistics;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.MessageFormat;

public class Main {
    private static final String FILENAME = "RU-NVS.osm.bz2";
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) throws Exception {
        CompressorInputStream compressorInputStream = new CompressorStreamFactory().createCompressorInputStream(CompressorStreamFactory.BZIP2, new BufferedInputStream(new FileInputStream(FILENAME)));

        long start = System.nanoTime();
        new DAOManager(DAOManager.DAOType.valueOf(args[0]));
        Statistics statistics = new Statistics(compressorInputStream);
        double time = (System.nanoTime() - start) * 0.000000001;
        int rowsPerSecond = (int) ((statistics.getUsers().size() + statistics.getTagNodes().size()) / time);
        logger.info(MessageFormat.format(LoggerMessages.TIMER_MESSAGE.getMessage(), (System.nanoTime() - start) * 0.000000001));
        System.out.println(statistics.getUsers());
        System.out.println(statistics.getTagNodes());
        DAOManager.manager.close();

    }
}
