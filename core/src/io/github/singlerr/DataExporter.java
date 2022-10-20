package io.github.singlerr;

import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

/***
 * Defining base structures of data exporter.
 * All implemented classes will be used as exporting {@param <T>} to csv file.
 * @author Singlerr
 * @param <T>
 */
public interface DataExporter<T> {

  /***
   * Write your data of {@link T} to {@link CSVWriter}
   * Any mechanisms writing data to csv is okay. Writing with bean or directly.
   * @param writer writer of csv. Passed from {@link com.fastcat.labyrintale.prototype.GameConfiguration}
   */
  void export(CSVWriter writer) throws CsvRequiredFieldEmptyException, CsvDataTypeMismatchException;

  /***
   * Identifying what this class should export.
   * @return class that this will export.
   */
  Class<T> getExportedClass();

  /***
   * Identifying destination file path
   * @return destination file path
   */
  String getTargetFileName();
}
