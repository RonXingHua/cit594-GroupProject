package edu.upenn.cit594.data;

import java.io.File;
import java.util.ArrayList;

public interface Reader<E>{
    public ArrayList<E> readFromFile(File fileName);
}
