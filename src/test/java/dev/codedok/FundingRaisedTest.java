package dev.codedok;
import com.opencsv.exceptions.CsvValidationException;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Unit test for simple App.
 */
public class FundingRaisedTest
{

    /**
     * Rigourous Test :-)
     */
    @Test
    public void testWhereGivenCompany() {
        try {
            Map<String, String> options = new HashMap<String, String> ();
            options.put("company_name", "Facebook");
            assertEquals(FundingRaised.where(options).size(), 7);
        } catch(IOException | CsvValidationException e) {
            System.out.print(e.getMessage());
            System.out.print("error");
        }
    }

    @Test
    public void testWhereGivenCity() {
        try {
            Map<String, String> options = new HashMap<String, String> ();
            options.put("city", "Tempe");
            assertEquals(FundingRaised.where(options).size(), 3);
        } catch(IOException | CsvValidationException e) {
            System.out.print(e.getMessage());
            System.out.print("error");
        }
    }

    @Test
    public void testWhereGivenState() {
        try {
            Map<String, String> options = new HashMap<String, String> ();
            options.put("state", "CA");
            assertEquals(FundingRaised.where(options).size(), 873);
        } catch(IOException | CsvValidationException e) {
            System.out.print(e.getMessage());
            System.out.print("error");
        }
    }

    @Test
    public void testWhereGivenRound() {
        try {
            Map<String, String> options = new HashMap<String, String> ();
            options.put("round", "a");
            assertEquals(FundingRaised.where(options).size(), 582);
        } catch(IOException | CsvValidationException e) {
            System.out.print(e.getMessage());
            System.out.print("error");
        }
    }

    @Test
    public void testMultipleOptions() {
        try {
            Map<String, String> options = new HashMap<String, String> ();
            options.put("round", "a");
            options.put("company_name", "Facebook");
            assertEquals(FundingRaised.where(options).size(), 1);
        } catch(IOException | CsvValidationException e) {
            System.out.print(e.getMessage());
            System.out.print("error");
        }
    }

    @Test
    public void testWhereNotExists() {
        try {
            Map<String, String> options = new HashMap<String, String> ();
            options.put("company_name", "NotFacebook");
            assertEquals(FundingRaised.where(options).size(), 0);
        } catch(IOException | CsvValidationException e) {
            System.out.print(e.getMessage());
            System.out.print("error");
        }
    }

    @Test
    public void testWhereCorrectKeys() {
        try {
            Map<String, String> options = new HashMap<String, String> ();
            options.put("company_name", "Facebook");
            Map<String, String> row = FundingRaised.where(options).get(0);

            assertEquals(row.get("permalink"), "facebook");
            assertEquals(row.get("company_name"), "Facebook");
            assertEquals(row.get("number_employees"), "450");
            assertEquals(row.get("category"), "web");
            assertEquals(row.get("city"), "Palo Alto");
            assertEquals(row.get("state"), "CA");
            assertEquals(row.get("funded_date"), "1-Sep-04");
            assertEquals(row.get("raised_amount"), "500000");
            assertEquals(row.get("round"), "angel");
        } catch(IOException | CsvValidationException e) {
            System.out.print(e.getMessage());
            System.out.print("error");
        }
    }

    @Test
    public void testFindByGivenCompanyName() {
        try {
            Map<String, String> options = new HashMap<String, String> ();
            options.put("company_name", "Facebook");
            Map<String, String> row = FundingRaised.findBy(options);

            assertEquals(row.get("permalink"), "facebook");
            assertEquals(row.get("company_name"), "Facebook");
            assertEquals(row.get("number_employees"), "450");
            assertEquals(row.get("category"), "web");
            assertEquals(row.get("city"), "Palo Alto");
            assertEquals(row.get("state"), "CA");
            assertEquals(row.get("funded_date"), "1-Sep-04");
            assertEquals(row.get("raised_amount"), "500000");
            assertEquals(row.get("round"), "angel");
        } catch(IOException | CsvValidationException e) {
            System.out.print(e.getMessage());
            System.out.print("error");
        } catch(NoSuchEntryException e) {
            System.out.print(e.getMessage());
            System.out.print("error");
        }
    }

    @Test
    public void testFindByGivenState() {
        try {
            Map<String, String> options = new HashMap<String, String> ();
            options.put("state", "CA");
            Map<String, String> row = FundingRaised.findBy(options);

            assertEquals(row.get("permalink"), "digg");
            assertEquals(row.get("company_name"), "Digg");
            assertEquals(row.get("number_employees"), "60");
            assertEquals(row.get("category"), "web");
            assertEquals(row.get("city"), "San Francisco");
            assertEquals(row.get("state"), "CA");
            assertEquals(row.get("funded_date"), "1-Dec-06");
            assertEquals(row.get("raised_amount"), "8500000");
            assertEquals(row.get("round"), "b");
        } catch(IOException | CsvValidationException e) {
            System.out.print(e.getMessage());
            System.out.print("error");
        } catch(NoSuchEntryException e) {
            System.out.print(e.getMessage());
            System.out.print("error");
        }
    }

    @Test
    public void testFindByMultipleOptions() {
        try {
            Map<String, String> options = new HashMap<String, String> ();
            options.put("company_name", "Facebook");
            options.put("round", "c");
            Map<String, String> row = FundingRaised.findBy(options);

            assertEquals(row.get("permalink"), "facebook");
            assertEquals(row.get("company_name"), "Facebook");
            assertEquals(row.get("number_employees"), "450");
            assertEquals(row.get("category"), "web");
            assertEquals(row.get("city"), "Palo Alto");
            assertEquals(row.get("state"), "CA");
            assertEquals(row.get("funded_date"), "1-Oct-07");
            assertEquals(row.get("raised_amount"), "300000000");
            assertEquals(row.get("round"), "c");
        } catch(IOException | CsvValidationException e) {
            System.out.print(e.getMessage());
            System.out.print("error");
        } catch(NoSuchEntryException e) {
            System.out.print(e.getMessage());
            System.out.print("error");
        }
    }

    @Test
    public void testFindByNotExists() {
        try {
            Map<String, String> options = new HashMap<String, String> ();
            options.put("company_name", "NotFacebook");
            options.put("round", "c");
            Map<String, String> row = FundingRaised.findBy(options);
            fail("findBy should throw exception");
        } catch(IOException | CsvValidationException e) {
            System.out.print(e.getMessage());
            System.out.print("error");
        } catch(NoSuchEntryException e) {
        }
    }
}
