package org.jalafoundation.devint34level1.grupo2.example;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import org.jalafoundation.devint34level1.grupo2.example.PhoneBook.Group;

public class JsonUtil {

  private static final String PATH = "./src/org/jalafoundation/devint34level1/grupo2/example/PhoneBook/PhoneBook.json";

  public JsonUtil() {
  }

  public static ArrayList<Group> readJsonFile() {
    Gson gson = new Gson();
    ArrayList<Group> groups = null;
    Type groupListType = new TypeToken<ArrayList<Group>>() {
    }.getType();
    try (JsonReader reader = new JsonReader(new FileReader(PATH))) {
      groups = gson.fromJson(reader, groupListType);
    } catch (IOException exception) {
      exception.printStackTrace();
    }
    return groups;
  }

  public static void writeJsonFile(ArrayList<Group> groups) {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    try (Writer writer = new FileWriter(PATH)) {
      gson.toJson(groups, writer);
    } catch (JsonIOException | IOException exception) {
      exception.printStackTrace();
    }
  }

  public static String getFilePath() {
    String filePath = new File("").getAbsolutePath();
    return filePath + "\\src\\org\\jalafoundation\\devint34level1\\grupo2\\example\\PhoneBook\\PhoneBook.json";
  }
}
