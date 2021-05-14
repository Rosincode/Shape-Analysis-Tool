package nl.thairosi.sat.Utils;

import nl.thairosi.sat.Models.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This ShapeIO class contains all the necessary methods to import and export shapes from and to the application
 */
public class ShapeIO {

    /**
     * Exports all shapes that are present in the database into a java object file
     *
     * @param file   is the name for the object file
     * @param shapes are the shapes in an ArrayList that are to be exported
     */
    public void exportObj(File file, ArrayList<Shape> shapes) {
        try {
            FileOutputStream fileOut = new FileOutputStream(file);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(shapes);
            objectOut.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Exports all shapes that are present in the database into a text file
     *
     * @param file   is the name for the text file
     * @param shapes are the shapes in an ArrayList that are to be exported
     */
    public void exportTxT(File file, ArrayList<Shape> shapes) {
        try {
            PrintWriter printWriter = new PrintWriter(file.getPath());
            for (Shape shape : shapes) {
                printWriter.println(shape);
            }
            printWriter.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Checks if the given file is an object or a text file in order to use the right import method
     *
     * @param file is the name that has to be imported
     * @return the execution of the necessary import method
     * @throws IOException            if importing fails
     * @throws ClassNotFoundException if the classes present in a object file are not found
     */
    public ArrayList<Shape> readShapesFromFile(File file) throws IOException, ClassNotFoundException {
        int indexOf = file.getName().lastIndexOf(".");
        String fileType = file.getName().substring(indexOf);
        if (fileType.equals(".txt")) {
            return importShapesFromTxt(file);
        }
        if (fileType.equals(".obj")) {
            return importShapesFromObj(file);
        }
        return null;
    }

    /**
     * Creates an ArrayList of Shape objects out of a chosen object file
     *
     * @param file is the file where the shapes are to be imported from
     * @return the ArrayList of to be imported shape objects
     * @throws IOException            if importing fails
     * @throws ClassNotFoundException if the classes present in a object file are not found
     */
    private ArrayList<Shape> importShapesFromObj(File file) throws IOException, ClassNotFoundException {
        FileInputStream fileIn = new FileInputStream(file);
        ObjectInputStream objectIn = new ObjectInputStream(fileIn);
        ArrayList<Shape> importedShapes = (ArrayList<Shape>) objectIn.readObject();
        objectIn.close();
        fileIn.close();
        return importedShapes;
    }

    /**
     * Creates an ArrayList of Shape objects out of a chosen text file
     *
     * @param file is the file where the shapes are to be imported from
     * @return the ArrayList of to be imported shape objects
     * @throws FileNotFoundException if the text file is not found
     */
    private ArrayList<Shape> importShapesFromTxt(File file) throws FileNotFoundException {
        ArrayList<Shape> importedShapes = new ArrayList<>();
        Scanner scanner = new Scanner(new File(file.getPath()));
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.isEmpty()) {
                continue;
            }
            String[] parts = line.split(",");
            if (parts.length < 4 ) {
                continue;
            }
            try {
                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                switch (parts[2]) {
                    case "CONE":
                        double heightCone = Double.parseDouble(parts[3]);
                        double diameterCone = Double.parseDouble(parts[4]);
                        Shape cone = new Cone(id, name, ShapeType.CONE, heightCone, diameterCone);
                        importedShapes.add(cone);
                        break;
                    case "CUBE":
                        double edge = Double.parseDouble(parts[3]);
                        Shape cube = new Cube(id, name, ShapeType.CUBE, edge);
                        importedShapes.add(cube);
                        break;
                    case "CYLINDER":
                        double heightCyl = Double.parseDouble(parts[3]);
                        double diameterCyl = Double.parseDouble(parts[4]);
                        Shape cylinder = new Cylinder(id, name, ShapeType.CYLINDER, heightCyl, diameterCyl);
                        importedShapes.add(cylinder);
                        break;
                    case "PYRAMID":
                        double heightPyr = Double.parseDouble(parts[3]);
                        double edgePyr = Double.parseDouble(parts[4]);
                        Shape pyramid = new Pyramid(id, name, ShapeType.PYRAMID, heightPyr, edgePyr);
                        importedShapes.add(pyramid);
                        break;
                    case "SPHERE":
                        double diameterSph = Double.parseDouble(parts[3]);
                        Shape sphere = new Sphere(id, name, ShapeType.SPHERE, diameterSph);
                        importedShapes.add(sphere);
                        break;
                }
            } catch (Exception e) {
                System.out.println("ID: " + parts[0] + " could not be imported");
            }
        }
        return importedShapes;
    }

}
