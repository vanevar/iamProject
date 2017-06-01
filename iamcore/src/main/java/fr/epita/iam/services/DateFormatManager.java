package fr.epita.iam.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/**
 * @author vanessavargas
 * Class created to handle Date-String conversion.
 */
public class DateFormatManager {
  private SimpleDateFormat derbyDateFormat;
  private static final Logger LOGGER = LogManager.getLogger(DateFormatManager.class);


  public DateFormatManager() {
    derbyDateFormat = new SimpleDateFormat("yyyy-MM-dd");
  }

  public Date dateFromString (String input) {
    Date result = null;
    try {
      result = this.derbyDateFormat.parse(input);
      //Use the date Object
    } catch (ParseException e) {
      LOGGER.error("Issue with Date conversion.", e);
    }
    return result;
  }
  
  public String stringFromDate(Date input)
  {
    return this.derbyDateFormat.format(input);
  }
}
