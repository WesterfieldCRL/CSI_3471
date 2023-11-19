
import edu.baylor.cs.csi3471.*;
import org.junit.jupiter.api.*;

import java.io.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class MakeTest {

    private ArrayList<Make> makes;
    private static final String FILE_NAME = "vehiclesMini.csv";
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private PrintStream filePrintStream;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));

        try {
            // Specify the file path where you want to save the output
            File outputFile = new File("output.txt");
            filePrintStream = new PrintStream(new FileOutputStream(outputFile));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);

        // Close the file stream
        if (filePrintStream != null) {
            filePrintStream.close();
        }
    }

    @BeforeEach
    public void setUp() throws FileNotFoundException {
        Make.makeIdentification = 0;
        ModelSettings.modelSettingsIdentification = 0;
        makes = Tester.loadCSV(FILE_NAME);
    }

    @Test
    public void testEquals() {
        Make testMake = makes.get(0);
        assertSame(makes.get(0), testMake);
    }

    @Test
    public void testPrintFilterModel() {
        for (Make out : makes)
        {
            out.printFilter("model", "Mustang GT");
        }

        String validData = "Make{makeName='Shelby', makeID= 55, modelSettings:\n" +
                "{mpg={city=15, combined=17, avg=22}, cylinders=8, displacement=4.6, " +
                "fuelType='Premium', model='Mustang GT', transmission='Manual 5-spd', " +
                "VClass='Two Seaters', year=2008, modelSettingsID=9382}}";

        String printedData = outContent.toString().trim();

        assertEquals(validData, printedData);

    }

    @Test
    public void testPrintFilterMakes() {
        //System.setOut(filePrintStream);

        for (Make out : makes)
        {
            out.printFilter("model", "Shelby");
        }

        String printedData = outContent.toString().trim();

        String expectedData = "Make{makeName='Ford', makeID= 17, modelSettings:\n" +
                "{mpg={city=14, combined=16, avg=21}, cylinders=8, displacement=5.2, fuelType='Premium', model='Shelby GT350 Mustang', transmission='Manual 6-spd', VClass='Subcompact Cars', year=2016, modelSettingsID=18573}\n" +
                "{mpg={city=14, combined=16, avg=21}, cylinders=8, displacement=5.2, fuelType='Premium', model='Shelby GT350 Mustang', transmission='Manual 6-spd', VClass='Subcompact Cars', year=2017, modelSettingsID=19217}\n" +
                "{mpg={city=14, combined=16, avg=21}, cylinders=8, displacement=5.2, fuelType='Premium', model='Shelby GT350 Mustang', transmission='Manual 6-spd', VClass='Subcompact Cars', year=2018, modelSettingsID=21325}\n" +
                "{mpg={city=14, combined=16, avg=21}, cylinders=8, displacement=5.2, fuelType='Premium', model='Shelby GT350 Mustang', transmission='Manual 6-spd', VClass='Subcompact Cars', year=2019, modelSettingsID=22877}}";
        assertEquals(expectedData, printedData);
    }


    @Test
    public void testPrintNumModels() {

        makes.sort(new Tester.sortByName());

        for (Make out : makes)
        {
            out.printNumModels();
        }

        String validData =
                "Acura;2000;7\n" +"Acura;2001;9\n" + "Acura;2002;9\n" + "Acura;2003;10\n" + "Acura;2004;11\n" + "Acura;2005;11\n" + "Acura;2006;9\n" + "Acura;2007;8\n" + "Acura;2008;8\n" + "Acura;2009;7\n" + "Acura;2010;11\n" + "Acura;2011;12\n" + "Acura;2012;12\n" + "Acura;2013;14\n" + "Acura;2014;16\n" + "Acura;2015;10\n" + "Acura;2016;12\n" + "Acura;2017;14\n" + "Acura;2018;16\n" +
                "Alfa Romeo;2009;1\n" + "Alfa Romeo;2015;1\n" + "Alfa Romeo;2016;1\n" + "Alfa Romeo;2017;4\n" + "Alfa Romeo;2018;6\n" +
                "Aston Martin;2000;4\n" + "Aston Martin;2002;1\n" + "Aston Martin;2003;9\n" + "Aston Martin;2004;1\n" + "Aston Martin;2005;5\n" + "Aston Martin;2006;6\n" + "Aston Martin;2007;6\n" + "Aston Martin;2008;6\n" + "Aston Martin;2009;6\n" + "Aston Martin;2010;7\n" + "Aston Martin;2011;9\n" + "Aston Martin;2012;11\n" + "Aston Martin;2013;5\n" + "Aston Martin;2014;7\n" + "Aston Martin;2015;10\n" + "Aston Martin;2016;10\n" + "Aston Martin;2017;5\n" + "Aston Martin;2018;4\n" +
                "Audi;2000;26\n" + "Audi;2001;31\n" + "Audi;2002;32\n" + "Audi;2003;30\n" + "Audi;2004;36\n" + "Audi;2005;41\n" + "Audi;2006;35\n" + "Audi;2007;35\n" + "Audi;2008;48\n" + "Audi;2009;41\n" + "Audi;2010;35\n" + "Audi;2011;38\n" + "Audi;2012;38\n" + "Audi;2013;44\n" + "Audi;2014;48\n" + "Audi;2015;55\n" + "Audi;2016;43\n" + "Audi;2017;40\n" + "Audi;2018;44\n" +
                "Bentley;2000;6\n" + "Bentley;2001;5\n" + "Bentley;2002;1\n" + "Bentley;2003;5\n" + "Bentley;2004;3\n" + "Bentley;2005;3\n" + "Bentley;2006;4\n" + "Bentley;2007;6\n" + "Bentley;2008;6\n" + "Bentley;2009;7\n" + "Bentley;2010;6\n" + "Bentley;2011;5\n" + "Bentley;2012;6\n" + "Bentley;2013;9\n" + "Bentley;2014;7\n" + "Bentley;2015;8\n" + "Bentley;2016;7\n" + "Bentley;2017;11\n" + "Bentley;2018;6\n" +
                "BMW;2000;39\n" + "BMW;2001;49\n" + "BMW;2002;46\n" + "BMW;2003;51\n" + "BMW;2004;64\n" + "BMW;2005;59\n" + "BMW;2006;63\n" + "BMW;2007;65\n" + "BMW;2008;76\n" + "BMW;2009;74\n" + "BMW;2010;83\n" + "BMW;2011;84\n" + "BMW;2012;85\n" + "BMW;2013;100\n" + "BMW;2014;98\n" + "BMW;2015;121\n" + "BMW;2016;112\n" + "BMW;2017;105\n" + "BMW;2018;105\n" + "BMW Alpina;2003;1\n" + "BMW Alpina;2007;1\n" +
                "Bugatti;2006;1\n" + "Bugatti;2008;1\n" + "Bugatti;2010;1\n" + "Bugatti;2011;1\n" + "Bugatti;2012;1\n" + "Bugatti;2013;1\n" + "Bugatti;2014;1\n" + "Bugatti;2015;1\n" + "Bugatti;2018;1\n" +
                "Buick;2000;6\n" + "Buick;2001;6\n" + "Buick;2002;8\n" + "Buick;2003;8\n" + "Buick;2004;13\n" + "Buick;2005;16\n" + "Buick;2006;15\n" + "Buick;2007;12\n" + "Buick;2008;9\n" + "Buick;2009;7\n" + "Buick;2010;9\n" + "Buick;2011;10\n" + "Buick;2012;14\n" + "Buick;2013;18\n" + "Buick;2014;16\n" + "Buick;2015;16\n" + "Buick;2016;20\n" + "Buick;2017;16\n" + "Buick;2018;17\n" +
                "BYD;2012;1\n" + "BYD;2013;1\n" + "BYD;2014;1\n" + "BYD;2016;1\n" + "BYD;2017;1\n" +
                "Cadillac;2000;5\n" + "Cadillac;2001;4\n" + "Cadillac;2002;7\n" + "Cadillac;2003;10\n" + "Cadillac;2004;18\n" + "Cadillac;2005;23\n" + "Cadillac;2006;25\n" + "Cadillac;2007;22\n" + "Cadillac;2008;23\n" + "Cadillac;2009;24\n" + "Cadillac;2010;27\n" + "Cadillac;2011;29\n" + "Cadillac;2012;22\n" + "Cadillac;2013;34\n" + "Cadillac;2014;35\n" + "Cadillac;2015;29\n" + "Cadillac;2016;28\n" + "Cadillac;2017;27\n" + "Cadillac;2018;26\n" +
                "Chevrolet;2000;79\n" + "Chevrolet;2001;73\n" + "Chevrolet;2002;84\n" + "Chevrolet;2003;94\n" + "Chevrolet;2004;109\n" + "Chevrolet;2005;108\n" + "Chevrolet;2006;103\n" + "Chevrolet;2007;112\n" + "Chevrolet;2008;98\n" + "Chevrolet;2009;109\n" + "Chevrolet;2010;88\n" + "Chevrolet;2011;90\n" + "Chevrolet;2012;95\n" + "Chevrolet;2013;81\n" + "Chevrolet;2014;76\n" + "Chevrolet;2015;92\n" + "Chevrolet;2016;89\n" + "Chevrolet;2017;87\n" + "Chevrolet;2018;88\n" +
                "Chrysler;2000;14\n" + "Chrysler;2001;21\n" + "Chrysler;2002;25\n" + "Chrysler;2003;27\n" + "Chrysler;2004;27\n" + "Chrysler;2005;35\n" + "Chrysler;2006;30\n" + "Chrysler;2007;30\n" + "Chrysler;2008;41\n" + "Chrysler;2009;23\n" + "Chrysler;2010;16\n" + "Chrysler;2011;11\n" + "Chrysler;2012;12\n" + "Chrysler;2013;13\n" + "Chrysler;2014;14\n" + "Chrysler;2015;13\n" + "Chrysler;2016;11\n" + "Chrysler;2017;12\n" + "Chrysler;2018;8\n" +
                "CODA Automotive;2012;1\n" +
                "Daewoo;2000;10\n" +"Daewoo;2001;10\n" +"Daewoo;2002;10\n" +"Daewoo;2003;6\n" +
                "Dodge;2000;52\n" +"Dodge;2001;58\n" +"Dodge;2002;61\n" +"Dodge;2003;53\n" +"Dodge;2004;44\n" +"Dodge;2005;45\n" +"Dodge;2006;44\n" +"Dodge;2007;47\n" +"Dodge;2008;65\n" +"Dodge;2009;50\n" +"Dodge;2010;39\n" +"Dodge;2011;37\n" +"Dodge;2012;30\n" +"Dodge;2013;35\n" +"Dodge;2014;35\n" +"Dodge;2015;35\n" +"Dodge;2016;37\n" +"Dodge;2017;28\n" +"Dodge;2018;28\n" +
                "Ferrari;2000;5\n" +"Ferrari;2001;5\n" +"Ferrari;2002;6\n" +"Ferrari;2003;8\n" +"Ferrari;2004;5\n" +"Ferrari;2005;8\n" +"Ferrari;2006;6\n" +"Ferrari;2007;6\n" +"Ferrari;2008;6\n" +"Ferrari;2009;7\n" +"Ferrari;2010;6\n" +"Ferrari;2011;8\n" +"Ferrari;2012;4\n" +"Ferrari;2013;7\n" +"Ferrari;2014;10\n" +"Ferrari;2015;12\n" +"Ferrari;2016;12\n" +"Ferrari;2017;11\n" +"Ferrari;2018;9\n" +
                "Fiat;2012;4\n" +"Fiat;2013;5\n" +"Fiat;2014;7\n" +"Fiat;2015;10\n" +"Fiat;2016;12\n" +"Fiat;2017;13\n" +"Fiat;2018;11\n" +
                "Ford;2000;78\n" +"Ford;2001;94\n" +"Ford;2002;86\n" +"Ford;2003;90\n" +"Ford;2004;79\n" +"Ford;2005;67\n" +"Ford;2006;63\n" +"Ford;2007;64\n" +"Ford;2008;54\n" +"Ford;2009;49\n" +"Ford;2010;56\n" +"Ford;2011;80\n" +"Ford;2012;78\n" +"Ford;2013;82\n" +"Ford;2014;87\n" +"Ford;2015;77\n" +"Ford;2016;88\n" +"Ford;2017;99\n" +"Ford;2018;110\n" +
                "Genesis;2017;7\n" +"Genesis;2018;10\n" +
                "GMC;2000;42\n" +"GMC;2001;39\n" +"GMC;2002;48\n" +"GMC;2003;60\n" +"GMC;2004;74\n" +"GMC;2005;73\n" +"GMC;2006;61\n" +"GMC;2007;69\n" +"GMC;2008;57\n" +"GMC;2009;69\n" +"GMC;2010;50\n" +"GMC;2011;62\n" +"GMC;2012;65\n" +"GMC;2013;47\n" +"GMC;2014;36\n" +"GMC;2015;51\n" +"GMC;2016;43\n" +"GMC;2017;47\n" +"GMC;2018;44\n" +
                "Honda;2000;24\n" +"Honda;2001;24\n" +"Honda;2002;23\n" +"Honda;2003;27\n" +"Honda;2004;27\n" +"Honda;2005;29\n" +"Honda;2006;25\n" +"Honda;2007;25\n" +"Honda;2008;27\n" +"Honda;2009;27\n" +"Honda;2010;30\n" +"Honda;2011;31\n" +"Honda;2012;30\n" +"Honda;2013;30\n" +"Honda;2014;29\n" +"Honda;2015;27\n" +"Honda;2016;29\n" +"Honda;2017;42\n" +"Honda;2018;44\n" +
                "Hummer;2006;2\n" +"Hummer;2007;2\n" +"Hummer;2008;3\n" +"Hummer;2009;6\n" +
                "Hyundai;2000;12\n" +"Hyundai;2001;17\n" +"Hyundai;2002;19\n" +"Hyundai;2003;24\n" +"Hyundai;2004;23\n" +"Hyundai;2005;27\n" +"Hyundai;2006;24\n" +"Hyundai;2007;28\n" +"Hyundai;2008;28\n" +"Hyundai;2009;27\n" +"Hyundai;2010;31\n" +"Hyundai;2011;34\n" +"Hyundai;2012;35\n" +"Hyundai;2013;38\n" +"Hyundai;2014;38\n" +"Hyundai;2015;43\n" +"Hyundai;2016;44\n" +"Hyundai;2017;43\n" +"Hyundai;2018;43\n" +
                "Infiniti;2000;5\n" +"Infiniti;2001;6\n" +"Infiniti;2002;6\n" +"Infiniti;2003;11\n" +"Infiniti;2004;13\n" +"Infiniti;2005;10\n" +"Infiniti;2006;14\n" +"Infiniti;2007;13\n" +"Infiniti;2008;16\n" +"Infiniti;2009;19\n" +"Infiniti;2010;19\n" +"Infiniti;2011;18\n" +"Infiniti;2012;22\n" +"Infiniti;2013;22\n" +"Infiniti;2014;29\n" +"Infiniti;2015;31\n" +"Infiniti;2016;25\n" +"Infiniti;2017;31\n" +"Infiniti;2018;25\n" +
                "Isuzu;2000;20\n" +"Isuzu;2001;19\n" +"Isuzu;2002;18\n" +"Isuzu;2003;15\n" +"Isuzu;2004;14\n" +"Isuzu;2005;6\n" +"Isuzu;2006;9\n" +"Isuzu;2007;8\n" +"Isuzu;2008;7\n" +
                "Jaguar;2000;11\n" +"Jaguar;2001;11\n" +"Jaguar;2002;15\n" +"Jaguar;2003;17\n" +"Jaguar;2004;13\n" +"Jaguar;2005;21\n" +"Jaguar;2006;16\n" +"Jaguar;2007;14\n" +"Jaguar;2008;14\n" +"Jaguar;2009;11\n" +"Jaguar;2010;9\n" +"Jaguar;2011;10\n" +"Jaguar;2012;10\n" +"Jaguar;2013;19\n" +"Jaguar;2014;20\n" +"Jaguar;2015;23\n" +"Jaguar;2016;21\n" +"Jaguar;2017;29\n" +"Jaguar;2018;43\n" +"Jaguar;2019;43\n" +
                "Jeep;2000;15\n" +"Jeep;2001;12\n" +"Jeep;2002;12\n" +"Jeep;2003;14\n" +"Jeep;2004;14\n" +"Jeep;2005;16\n" +"Jeep;2006;21\n" +"Jeep;2007;32\n" +"Jeep;2008;34\n" +"Jeep;2009;35\n" +"Jeep;2010;27\n" +"Jeep;2011;24\n" +"Jeep;2012;25\n" +"Jeep;2013;23\n" +"Jeep;2014;33\n" +"Jeep;2015;37\n" +"Jeep;2016;36\n" +"Jeep;2017;40\n" +"Jeep;2018;36\n" +
                "Kia;2000;6\n" +"Kia;2001;16\n" +"Kia;2002;13\n" +"Kia;2003;11\n" +"Kia;2004;18\n" +"Kia;2005;19\n" +"Kia;2006;21\n" +"Kia;2007;20\n" +"Kia;2008;20\n" +"Kia;2009;24\n" +"Kia;2010;26\n" +"Kia;2011;37\n" +"Kia;2012;37\n" +"Kia;2013;36\n" +"Kia;2014;35\n" +"Kia;2015;38\n" +"Kia;2016;42\n" +"Kia;2017;45\n" +"Kia;2018;47\n" +"Kia;2019;38\n" +
                "Lamborghini;2001;1\n" +"Lamborghini;2002;2\n" +"Lamborghini;2003;2\n" +"Lamborghini;2004;4\n" +"Lamborghini;2005;4\n" +"Lamborghini;2006;6\n" +"Lamborghini;2007;8\n" +"Lamborghini;2008;9\n" +"Lamborghini;2009;8\n" +"Lamborghini;2010;9\n" +"Lamborghini;2011;4\n" +"Lamborghini;2012;5\n" +"Lamborghini;2013;6\n" +"Lamborghini;2014;7\n" +"Lamborghini;2015;4\n" +"Lamborghini;2016;4\n" +"Lamborghini;2017;8\n" +"Lamborghini;2018;8\n" +
                "Land Rover;2000;3\n" +"Land Rover;2001;3\n" +"Land Rover;2002;3\n" +"Land Rover;2003;4\n" +"Land Rover;2004;3\n" +"Land Rover;2005;4\n" +"Land Rover;2006;6\n" +"Land Rover;2007;6\n" +"Land Rover;2008;6\n" +"Land Rover;2009;6\n" +"Land Rover;2010;5\n" +"Land Rover;2011;6\n" +"Land Rover;2012;7\n" +"Land Rover;2013;7\n" +"Land Rover;2014;13\n" +"Land Rover;2015;11\n" +"Land Rover;2016;12\n" +"Land Rover;2017;12\n" +"Land Rover;2018;20\n" +"Land Rover;2019;21\n" +
                "Lexus;2000;9\n" +"Lexus;2001;8\n" +"Lexus;2002;10\n" +"Lexus;2003;11\n" +"Lexus;2004;13\n" +"Lexus;2005;13\n" +"Lexus;2006;18\n" +"Lexus;2007;20\n" +"Lexus;2008;22\n" +"Lexus;2009;22\n" +"Lexus;2010;23\n" +"Lexus;2011;24\n" +"Lexus;2012;20\n" +"Lexus;2013;23\n" +"Lexus;2014;25\n" +"Lexus;2015;32\n" +"Lexus;2016;35\n" +"Lexus;2017;32\n" +"Lexus;2018;36\n" +
                "Lincoln;2000;8\n" +"Lincoln;2001;9\n" +"Lincoln;2002;10\n" +"Lincoln;2003;9\n" +"Lincoln;2004;8\n" +"Lincoln;2005;8\n" +"Lincoln;2006;8\n" +"Lincoln;2007;9\n" +"Lincoln;2008;9\n" +"Lincoln;2009;8\n" +"Lincoln;2010;11\n" +"Lincoln;2011;16\n" +"Lincoln;2012;12\n" +"Lincoln;2013;16\n" +"Lincoln;2014;16\n" +"Lincoln;2015;21\n" +"Lincoln;2016;23\n" +"Lincoln;2017;27\n" +"Lincoln;2018;26\n" +
                "Lotus;2000;1\n" +"Lotus;2001;1\n" +"Lotus;2002;1\n" +"Lotus;2003;1\n" +"Lotus;2004;1\n" +"Lotus;2005;1\n" +"Lotus;2006;1\n" +"Lotus;2007;2\n" +"Lotus;2008;2\n" +"Lotus;2009;2\n" +"Lotus;2010;3\n" +"Lotus;2011;6\n" +"Lotus;2012;4\n" +"Lotus;2013;4\n" +"Lotus;2014;3\n" +
                "Maserati;2002;4\n" +"Maserati;2003;4\n" +"Maserati;2004;4\n" +"Maserati;2005;5\n" +"Maserati;2006;3\n" +"Maserati;2007;2\n" +"Maserati;2008;2\n" +"Maserati;2009;2\n" +"Maserati;2010;5\n" +"Maserati;2011;5\n" +"Maserati;2012;3\n" +"Maserati;2013;3\n" +"Maserati;2014;6\n" +"Maserati;2015;6\n" +"Maserati;2016;8\n" +"Maserati;2017;10\n" +"Maserati;2018;10\n" +
                "Maybach;2004;2\n" +"Maybach;2005;2\n" +"Maybach;2006;3\n" +"Maybach;2007;4\n" +"Maybach;2008;4\n" +"Maybach;2009;4\n" +"Maybach;2010;3\n" +"Maybach;2011;4\n" +
                "Mazda;2000;24\n" +"Mazda;2001;31\n" +"Mazda;2002;28\n" +"Mazda;2003;27\n" +"Mazda;2004;30\n" +"Mazda;2005;31\n" +"Mazda;2006;34\n" +"Mazda;2007;30\n" +"Mazda;2008;34\n" +"Mazda;2009;30\n" +"Mazda;2010;27\n" +"Mazda;2011;27\n" +"Mazda;2012;24\n" +"Mazda;2013;24\n" +"Mazda;2014;25\n" +"Mazda;2015;24\n" +"Mazda;2016;24\n" +"Mazda;2017;23\n" +"Mazda;2018;21\n" +
                "McLaren Automotive;2012;1\n" +"McLaren Automotive;2014;3\n" +"McLaren Automotive;2015;3\n" +"McLaren Automotive;2016;4\n" +"McLaren Automotive;2017;2\n" +
                "Mercedes-Benz;2000;24\n" +"Mercedes-Benz;2001;31\n" +"Mercedes-Benz;2002;38\n" +"Mercedes-Benz;2003;51\n" +"Mercedes-Benz;2004;56\n" +"Mercedes-Benz;2005;52\n" +"Mercedes-Benz;2006;53\n" +"Mercedes-Benz;2007;50\n" +"Mercedes-Benz;2008;52\n" +"Mercedes-Benz;2009;48\n" +"Mercedes-Benz;2010;46\n" +"Mercedes-Benz;2011;53\n" +"Mercedes-Benz;2012;61\n" +"Mercedes-Benz;2013;74\n" +"Mercedes-Benz;2014;83\n" +"Mercedes-Benz;2015;80\n" +"Mercedes-Benz;2016;78\n" +"Mercedes-Benz;2017;84\n" +"Mercedes-Benz;2018;92\n" +"Mercedes-Benz;2019;91\n" +
                "Mercury;2000;16\n" +"Mercury;2001;15\n" +"Mercury;2002;17\n" +"Mercury;2003;11\n" +"Mercury;2004;15\n" +"Mercury;2005;21\n" +"Mercury;2006;18\n" +"Mercury;2007;18\n" +"Mercury;2008;17\n" +"Mercury;2009;17\n" +"Mercury;2010;18\n" +
                "MINI;2002;1\n" +"MINI;2003;3\n" +"MINI;2004;3\n" +"MINI;2005;8\n" +"MINI;2006;9\n" +"MINI;2007;8\n" +"MINI;2008;13\n" +"MINI;2009;15\n" +"MINI;2010;15\n" +"MINI;2011;21\n" +"MINI;2012;31\n" +"MINI;2013;49\n" +"MINI;2014;46\n" +"MINI;2015;44\n" +"MINI;2016;34\n" +"MINI;2017;33\n" +"MINI;2018;36\n" +
                "Mitsubishi;2000;22\n" +"Mitsubishi;2001;26\n" +"Mitsubishi;2002;31\n" +"Mitsubishi;2003;29\n" +"Mitsubishi;2004;28\n" +"Mitsubishi;2005;28\n" +"Mitsubishi;2006;28\n" +"Mitsubishi;2007;24\n" +"Mitsubishi;2008;27\n" +"Mitsubishi;2009;32\n" +"Mitsubishi;2010;24\n" +"Mitsubishi;2011;27\n" +"Mitsubishi;2012;24\n" +"Mitsubishi;2013;18\n" +"Mitsubishi;2014;19\n" +"Mitsubishi;2015;18\n" +"Mitsubishi;2016;14\n" +"Mitsubishi;2017;16\n" +"Mitsubishi;2018;17\n" +
                "Mobility Ventures LLC;2014;2\n" +"Mobility Ventures LLC;2015;1\n" +
                "Morgan;2002;2\n" +
                "Nissan;2000;26\n" +"Nissan;2001;30\n" +"Nissan;2002;36\n" +"Nissan;2003;38\n" +"Nissan;2004;42\n" +"Nissan;2005;38\n" +"Nissan;2006;37\n" +"Nissan;2007;40\n" +"Nissan;2008;46\n" +"Nissan;2009;58\n" +"Nissan;2010;51\n" +"Nissan;2011;54\n" +"Nissan;2012;53\n" +"Nissan;2013;48\n" +"Nissan;2014;51\n" +"Nissan;2015;50\n" +"Nissan;2016;40\n" +"Nissan;2017;51\n" +"Nissan;2018;46\n" +
                "Oldsmobile;2000;6\n" +"Oldsmobile;2001;9\n" +"Oldsmobile;2002;10\n" +"Oldsmobile;2003;8\n" +
                "Pagani;2014;1\n" +"Pagani;2016;1\n" +
                "Plymouth;2000;11\n" +
                "Pontiac;2000;18\n" +"Pontiac;2001;20\n" +"Pontiac;2002;21\n" +"Pontiac;2003;19\n" +"Pontiac;2004;21\n" +"Pontiac;2005;27\n" +"Pontiac;2006;26\n" +"Pontiac;2007;22\n" +"Pontiac;2008;24\n" +"Pontiac;2009;33\n" +
                "Porsche;2000;8\n" +"Porsche;2001;10\n" +"Porsche;2002;17\n" +"Porsche;2003;31\n" +"Porsche;2004;36\n" +"Porsche;2005;54\n" +"Porsche;2006;27\n" +"Porsche;2007;38\n" +"Porsche;2008;43\n" +"Porsche;2009;40\n" +"Porsche;2010;48\n" +"Porsche;2011;55\n" +"Porsche;2012;67\n" +"Porsche;2013;41\n" +"Porsche;2014;52\n" +"Porsche;2015;61\n" +"Porsche;2016;71\n" +"Porsche;2017;61\n" +"Porsche;2018;80\n" +
                "Ram;2012;1\n" +"Ram;2013;11\n" +"Ram;2014;11\n" +"Ram;2015;12\n" +"Ram;2016;8\n" +"Ram;2017;8\n" +"Ram;2018;8\n" +
                "Rolls-Royce;2000;1\n" +"Rolls-Royce;2001;3\n" +"Rolls-Royce;2002;2\n" +"Rolls-Royce;2004;1\n" +"Rolls-Royce;2005;1\n" +"Rolls-Royce;2006;1\n" +"Rolls-Royce;2007;2\n" +"Rolls-Royce;2008;3\n" +"Rolls-Royce;2009;4\n" +"Rolls-Royce;2010;5\n" +"Rolls-Royce;2011;5\n" +"Rolls-Royce;2012;6\n" +"Rolls-Royce;2013;6\n" +"Rolls-Royce;2014;7\n" +"Rolls-Royce;2015;7\n" +"Rolls-Royce;2016;8\n" +"Rolls-Royce;2017;8\n" +"Rolls-Royce;2018;6\n" +
                "Roush Performance;2001;2\n" +"Roush Performance;2002;2\n" +"Roush Performance;2003;2\n" +"Roush Performance;2004;1\n" +"Roush Performance;2006;8\n" +"Roush Performance;2007;8\n" +"Roush Performance;2008;8\n" +"Roush Performance;2009;2\n" +"Roush Performance;2010;2\n" +"Roush Performance;2011;1\n" +"Roush Performance;2012;2\n" +"Roush Performance;2013;2\n" +"Roush Performance;2014;7\n" +"Roush Performance;2015;2\n" +"Roush Performance;2016;4\n" +"Roush Performance;2017;4\n" +
                "Saab;2000;20\n" +"Saab;2001;20\n" +"Saab;2002;16\n" +"Saab;2003;16\n" +"Saab;2004;17\n" +"Saab;2005;16\n" +"Saab;2006;22\n" +"Saab;2007;18\n" +"Saab;2008;23\n" +"Saab;2009;27\n" +"Saab;2010;13\n" +"Saab;2011;16\n" +
                "Saleen Performance;2008;3\n" +
                "Saturn;2000;16\n" +"Saturn;2001;17\n" +"Saturn;2002;18\n" +"Saturn;2003;14\n" +"Saturn;2004;13\n" +"Saturn;2005;13\n" +"Saturn;2006;12\n" +"Saturn;2007;21\n" +"Saturn;2008;21\n" +"Saturn;2009;21\n" +
                "Scion;2004;4\n" +"Scion;2005;6\n" +"Scion;2006;6\n" +"Scion;2007;2\n" +"Scion;2008;6\n" +"Scion;2009;6\n" +"Scion;2010;6\n" +"Scion;2011;7\n" +"Scion;2012;7\n" +"Scion;2013;10\n" +"Scion;2014;9\n" +"Scion;2015;7\n" +
                "smart;2008;2\n" +"smart;2009;2\n" +"smart;2010;2\n" +"smart;2011;4\n" +"smart;2012;2\n" +"smart;2013;4\n" +"smart;2014;4\n" +"smart;2015;4\n" +"smart;2016;4\n" +"smart;2017;6\n" +"smart;2018;2\n" +
                "Spyker;2005;3\n" +"Spyker;2006;3\n" +"Spyker;2007;4\n" +"Spyker;2009;1\n" +"Spyker;2010;1\n" +
                "SRT;2013;1\n" +
                "Subaru;2000;8\n" +"Subaru;2001;13\n" +"Subaru;2002;16\n" +"Subaru;2003;20\n" +"Subaru;2004;25\n" +"Subaru;2005;31\n" +"Subaru;2006;33\n" +"Subaru;2007;30\n" +"Subaru;2008;25\n" +"Subaru;2009;24\n" +"Subaru;2010;20\n" +"Subaru;2011;19\n" +"Subaru;2012;19\n" +"Subaru;2013;22\n" +"Subaru;2014;23\n" +"Subaru;2015;21\n" +"Subaru;2016;21\n" +"Subaru;2017;22\n" +"Subaru;2018;24\n" +
                "Suzuki;2000;26\n" +"Suzuki;2001;28\n" +"Suzuki;2002;26\n" +"Suzuki;2003;22\n" +"Suzuki;2004;23\n" +"Suzuki;2005;25\n" +"Suzuki;2006;23\n" +"Suzuki;2007;19\n" +"Suzuki;2008;18\n" +"Suzuki;2009;20\n" +"Suzuki;2010;26\n" +"Suzuki;2011;23\n" +"Suzuki;2012;21\n" +
                "Tesla;2012;1\n" +"Tesla;2013;3\n" +"Tesla;2014;3\n" +"Tesla;2015;8\n" +"Tesla;2016;18\n" +"Tesla;2017;15\n" +"Tesla;2018;11\n" +
                "Toyota;2000;55\n" +"Toyota;2001;53\n" +"Toyota;2002;51\n" +"Toyota;2003;58\n" +"Toyota;2004;56\n" +"Toyota;2005;53\n" +"Toyota;2006;47\n" +"Toyota;2007;50\n" +"Toyota;2008;49\n" +"Toyota;2009;57\n" +"Toyota;2010;56\n" +"Toyota;2011;58\n" +"Toyota;2012;58\n" +"Toyota;2013;58\n" +"Toyota;2014;57\n" +"Toyota;2015;52\n" +"Toyota;2016;49\n" +"Toyota;2017;58\n" +"Toyota;2018;59\n" +"Toyota;2019;61\n" +
                "Volkswagen;2000;39\n" +"Volkswagen;2001;41\n" +"Volkswagen;2002;50\n" +"Volkswagen;2003;49\n" +"Volkswagen;2004;58\n" +"Volkswagen;2005;54\n" +"Volkswagen;2006;31\n" +"Volkswagen;2007;26\n" +"Volkswagen;2008;28\n" +"Volkswagen;2009;36\n" +"Volkswagen;2010;34\n" +"Volkswagen;2011;28\n" +"Volkswagen;2012;38\n" +"Volkswagen;2013;47\n" +"Volkswagen;2014;49\n" +"Volkswagen;2015;46\n" +"Volkswagen;2016;35\n" +"Volkswagen;2017;30\n" +"Volkswagen;2018;30\n" +
                "Volvo;2000;23\n" +"Volvo;2001;24\n" +"Volvo;2002;22\n" +"Volvo;2003;23\n" +"Volvo;2004;31\n" +"Volvo;2005;36\n" +"Volvo;2006;39\n" +"Volvo;2007;40\n" +"Volvo;2008;33\n" +"Volvo;2009;33\n" +"Volvo;2010;29\n" +"Volvo;2011;18\n" +"Volvo;2012;15\n" +"Volvo;2013;16\n" +"Volvo;2014;13\n" +"Volvo;2015;21\n" +"Volvo;2016;27\n" +"Volvo;2017;22\n" +"Volvo;2018;27\n" +
                "VPG;2011;2\n" +"VPG;2012;2";


        String printedData = outContent.toString().trim();

        assertEquals(validData, printedData);
    }
}
